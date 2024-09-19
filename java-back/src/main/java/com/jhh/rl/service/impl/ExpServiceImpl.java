package com.jhh.rl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jhh.rl.dto.request.RegExp;
import com.jhh.rl.dto.response.ExpEntry;
import com.jhh.rl.entity.*;
import com.jhh.rl.mapper.*;
import com.jhh.rl.service.ExpService;
import com.jhh.rl.utils.ChannelUtil;
import com.jhh.rl.utils.Result;
import com.sun.org.apache.xpath.internal.objects.XNull;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpServiceImpl implements ExpService {

    @Resource
    private ExperimentMapper experimentMapper;

    @Resource
    private AlgorithmMapper algorithmMapper;

    @Resource
    private ImageMapper imageMapper;

    @Resource
    private ContainerMapper containerMapper;

    @Resource
    private EnvironmentMapper environmentMapper;

    @Resource
    private ExpAndContainerMapper expAndContainerMapper;

    @Resource
    private ImageAndContainerMapper imageAndContainerMapper;

    @Resource
    private ImageAndEnvAndAlgMapper imageAndEnvAndAlgMapper;

    @Resource
    private UserAndContainerMapper userAndContainerMapper;

    @Resource
    private  ChannelUtil channelUtil;


    @Override
    public Result<List<HashMap<String, Object>>> getExpList(Integer userId, String expName) {
        // 查询实验表的条件
        QueryWrapper<UserAndContainer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<UserAndContainer> userAndContainerList = userAndContainerMapper.selectList(queryWrapper);

        // 找到实验表的数据，检查是否为空
        if (userAndContainerList.isEmpty()) {
            return Result.ok("没有找到任何实验记录");
        } else {
            List<HashMap<String, Object>> resultList = new ArrayList<>();
            for(UserAndContainer userAndContainer : userAndContainerList){
                Integer containerId = userAndContainer.getContainerId();
                // 查找容器
                Container container = containerMapper.selectById(containerId);
                String containerName = container.getName();

                // 查找镜像
                ImageAndContainer imageAndContainer = imageAndContainerMapper.
                        selectOne(new QueryWrapper<ImageAndContainer>().eq("container_id", containerId));
                Integer imageId = imageAndContainer.getImageId();
                Image image = imageMapper.selectById(imageId);
                String imageName = image.getName();

                ImageAndEnvAndAlg imageAndEnvAndAlg = imageAndEnvAndAlgMapper.
                        selectOne(new QueryWrapper<ImageAndEnvAndAlg>().eq("image_id", imageId));

                // 查找算法
                Integer algId = imageAndEnvAndAlg.getAlgId();
                Algorithm algorithm = algorithmMapper.selectById(algId);
                String algorithmName = algorithm.getName();

                // 查找环境
                Integer envId = imageAndEnvAndAlg.getEnvId();
                Environment environment = environmentMapper.selectById(envId);
                String experimentName = environment.getName();

                // 查找实验
                QueryWrapper<ExpAndContainer> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("container_id", containerId);
                List<ExpAndContainer> expAndContainerList = expAndContainerMapper.selectList(queryWrapper1);

                if (expAndContainerList.isEmpty()) {
                    return Result.ok("没有找到任何实验记录");
                } else {
                    for(ExpAndContainer expAndContainer : expAndContainerList){
                        Integer expId = expAndContainer.getExpId();
                        QueryWrapper<Experiment> queryWrapper2 = new QueryWrapper<>();
                        queryWrapper2.eq("id", expId);
                        System.out.println(expId);
                        Experiment exp = experimentMapper.selectOne(queryWrapper2);
                        if(null != exp){
                            HashMap<String, Object> entry = new HashMap<>();
                            entry.put("id", exp.getId());
                            entry.put("userId", userId);
                            entry.put("name", exp.getName());
                            entry.put("createTime", exp.getCreateTime());
                            entry.put("status", exp.getStatus());
                            entry.put("dataPath", exp.getDataPath());
                            entry.put("note", exp.getNote());
                            entry.put("containerId", containerId);
                            entry.put("containerName", containerName);
                            entry.put("algId", algId);
                            entry.put("algName", algorithmName);
                            entry.put("envId", envId);
                            entry.put("envName", experimentName);
                            entry.put("imageName", imageName);
                            resultList.add(entry);
                        }
                    }
                }
            }
            return Result.ok("ok", resultList);
        }


    }

    @Override
    public Result registerExp(RegExp exp){

        Integer userId = exp.getUserId();
        Integer containerId = exp.getContainerId();
        String name = exp.getExpName();
        String note = exp.getExpNote();
        String createTime = "今年";
        String path = userId.toString() + "/" + containerId.toString() + "/" + name;

        Experiment experiment = new Experiment();
        experiment.setName(name);
        experiment.setNote(note);
        experiment.setStatus(1);
        experiment.setCreateTime(createTime);
        experiment.setDataPath(path);
        experimentMapper.insert(experiment);

        ExpAndContainer expAndContainer = new ExpAndContainer();
        expAndContainer.setContainerId(containerId);
        expAndContainer.setExpId(experiment.getId());
        expAndContainerMapper.insert(expAndContainer);

//        System.out.println(channelUtil.mkDir("test"));
//        Experiment experiment = new Experiment();
        return Result.ok("注册实验成功");


    }
}
