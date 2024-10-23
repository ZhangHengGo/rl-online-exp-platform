package com.jhh.rl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jhh.rl.dto.request.CreateContainer;
import com.jhh.rl.dto.request.RegExp;
import com.jhh.rl.entity.*;
import com.jhh.rl.mapper.ContainerAndPortMapper;
import com.jhh.rl.mapper.ContainerMapper;
import com.jhh.rl.mapper.ImageMapper;
import com.jhh.rl.mapper.UserAndContainerMapper;
import com.jhh.rl.service.ContainerService;
import com.jhh.rl.service.ExperimentService;
import com.jhh.rl.utils.ChannelUtil;
import com.jhh.rl.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ContainerServiceImpl implements ContainerService {


    @Resource
    private UserAndContainerMapper userAndContainerMapper;

    @Resource
    private ContainerMapper containerMapper;

    @Resource
    private ImageMapper imageMapper;

    @Resource
    private ChannelUtil channelUtil;

    @Resource
    private ContainerAndPortMapper containerAndPortMapper;

    public Result<List<Container>> getContainerList(Integer userId){

        // 查找
        QueryWrapper<UserAndContainer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<UserAndContainer> userAndContainerList = userAndContainerMapper.selectList(queryWrapper);
        // 处理
        if (userAndContainerList != null && !userAndContainerList.isEmpty()){
            List<Container> resultList = new ArrayList<>();
            for(UserAndContainer userContainer : userAndContainerList){
                HashMap<String, Object> entry = new HashMap<>();
                Integer containerId = userContainer.getContainerId();
                Container container = containerMapper.selectById(containerId);
                resultList.add(container);
            }
            return Result.ok("查询容器列表成功", resultList);
        }
        else {
            return Result.fail("未找到容器列表");
        }
    }


    @Override
    public Result create(CreateContainer createContainer) {
        QueryWrapper<UserAndContainer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", createContainer.getUserId());

        // SysContainer sysContainer = sysContainerMapper.selectOne(queryWrapper);

        Long count = userAndContainerMapper.selectCount(queryWrapper);
        // 容器数量限制
        if (4 <= count) {
            return Result.fail("每个用户最多创建4个容器！");
        }

        // ports现在是PortMapping对象的列表
        // 取出端口号
        List<PortMapping> portMappings = createContainer.getPortList();
        Set<Integer> uniqueExternalPorts = new HashSet<>();
        Set<Integer> uniqueInternalPorts = new HashSet<>();
        Set<Integer> duplicatePorts = new HashSet<>();

        // 检查重复的外部端口和内部端口
        for (PortMapping portMapping : portMappings) {
            if (!uniqueExternalPorts.add(portMapping.getExternal())) {
                duplicatePorts.add(portMapping.getExternal());
            }
            if (!uniqueInternalPorts.add(portMapping.getInternal())) {
                duplicatePorts.add(portMapping.getInternal());
            }
        }

        if (!duplicatePorts.isEmpty()) {
            return Result.fail("输入端口号重复");
        }

        // 检查容器外部端口是否已经被占用
        for (PortMapping portMapping : portMappings) {
            QueryWrapper<ContainerAndPort> queryWrapper1 = new QueryWrapper<>();
            Integer externalPort = portMapping.getExternal();
            if (externalPort < 1024) {
                return Result.fail("不允许使用该范围端口!");
            }
            queryWrapper1.eq("port", externalPort);
            Long count1 = containerAndPortMapper.selectCount(queryWrapper1);

            // 端口占用检测
            if (count1 != 0) {
                return Result.fail("端口已被占用！");
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Container container = new Container();
        container.setName(createContainer.getName());
        container.setUserId(createContainer.getUserId());
        container.setStatus(0);
        // 生成的容器id这里写死了，后期需要改

        // int imageid = createContainer.getImageid();
        Image im = imageMapper.selectById(createContainer.getImageId());
        // 拿到extraConfig，即额外配置中的内容
        String config = createContainer.getExtraConfig();
        for(PortMapping portMapping : portMappings){
            config += " -p " + portMapping.getExternal() + ":" + portMapping.getInternal() + " ";
        }
        // runDocker函数已经将docker命令拼接好，把config填进去就好了
        String containerId = channelUtil.runDocker(config, container.getName(), im.getVersion());
        ; // 生成容器ID的逻辑需要根据实际需求修改
        containerId = containerId.substring(0, 12);
        // System.out.println(dockerService.runDocker(8014,"wdd",0));
        // String dockerID = dockerService.runDocker(sysContainer.getPort(),
        // sysContainer.getContainerName(), sysContainer.getVersionId()); // 异步启动容器
        // sysContainer.setContainerId(dockerID.substring(0,12));
        // System.out.println(dockerID);
        container.setId(containerId);
        // container.setVersionId(createContainer.getImageId()); 应该有

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        container.setCreateTime(sdf.format(new Date()));

        int insertResult = containerMapper.insert(container);

        if (insertResult == 0) {
            channelUtil.rmDocker(container.getName());
            return Result.fail("容器创建失败");
        }

        // 为容器添加端口
        for (Integer port : uniqueExternalPorts) {
            ContainerAndPort containerAndPort = new ContainerAndPort();
            containerAndPort.setContainerId(containerId);
            containerAndPort.setPort(port);

            int portInsertResult = containerAndPortMapper.insert(containerAndPort);

            if (portInsertResult == 0) {
                return Result.fail("端口创建失败");
            }
        }
        return Result.ok("添加成功");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }


    @Override
    public Result start(Integer id) {
        Container container = containerMapper.selectById(id);
        if (null == container) {
            return Result.fail("容器不存在！");
        }
        channelUtil.startDocker(container.getName());
        containerMapper.updateContainerStatus(1, id);
        return Result.ok("启动容器成功");
    }

    @Override
    public Result stop(Integer id) {

        Container container = containerMapper.selectById(id);
        if (null == container) {
            return Result.fail("容器不存在！");
        }
        channelUtil.stopDocker(container.getName());
        containerMapper.updateContainerStatus(2, id);
        return Result.ok("停止容器成功");
    }
    @Override
    public Result delete(Integer id) {

        Container container = containerMapper.selectById(id);
        int i = 0;
        i = containerMapper.deleteById(id);
        if (i == 0) {
            return Result.fail("删除失败！");
        }
        channelUtil.rmDocker(container.getName());
        return Result.ok("");
    }


}
