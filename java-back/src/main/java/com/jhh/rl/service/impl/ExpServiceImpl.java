package com.jhh.rl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jhh.rl.dto.request.RegExp;
import com.jhh.rl.entity.*;
import com.jhh.rl.mapper.*;
import com.jhh.rl.service.ExpService;
import com.jhh.rl.utils.ChannelUtil;
import com.jhh.rl.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    public Result<HashMap<String, Object>> getExpList(Integer userId,
                                                      Integer pageIndex,
                                                      Integer pageSize,
                                                      String expName,
                                                      String containerName,
                                                      String expNote,
                                                      String envName,
                                                      String expStatus) {
        HashMap<String, Object> result = new HashMap<>();
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
                String containerName0 = container.getName();

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
                            entry.put("expName", exp.getName());
                            entry.put("createTime", exp.getCreateTime());
                            entry.put("status", exp.getStatus());
                            entry.put("dataPath", exp.getDataPath());
                            entry.put("note", exp.getNote());
                            entry.put("containerId", containerId);
                            entry.put("containerName", containerName0);
                            entry.put("algId", algId);
                            entry.put("algName", algorithmName);
                            entry.put("envId", envId);
                            entry.put("envName", experimentName);
                            entry.put("imageName", imageName);
                            boolean sign = true;
                            if (containerName != null && !containerName.isEmpty()) {
                                if (containerName0 != null && !containerName0.contains(containerName)) {
                                    sign = false;
                                }
                            }
                            if (expName != null && !expName.isEmpty()) {
                                if (exp.getName() != null && !exp.getName().contains(expName)) {
                                    sign = false;
                                }
                            }
                            if (expName != null && !expName.isEmpty()) {
                                if (exp.getName() != null && !exp.getName().contains(expName)) {
                                    sign = false;
                                }
                            }
                            if (expNote != null && !expNote.isEmpty()) {
                                if (exp.getNote() != null && !exp.getNote().contains(expNote)) {
                                    sign = false;
                                }
                            }
                            if (envName != null && !envName.isEmpty()) {
                                if (experimentName != null && !experimentName.contains(envName)) {
                                    sign = false;
                                }
                            }
                            // TODO
//                            if (expStatus != null && !expStatus.isEmpty()) {
//                                if (Objects.equals(exp.getStatus(), expStatus)) {
//                                    sign = false;
//                                }
//                            }
                            if (sign){
                                resultList.add(entry);
                            }
                        }
                    }
                    result.put("list", paginate(resultList, pageIndex, pageSize));
                    result.put("total", resultList.size());
                }
            }
            return Result.ok("ok", result);
        }


    }

    @Override
    public Result registerExp(RegExp exp){
        Integer userId = exp.getUserId();
        Integer containerId = exp.getContainerId();
        String name = exp.getExpName();
        String note = exp.getNote();
        // 时间
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String createTime = now.format(formatter);

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

        channelUtil.downloadFileFromRemote(name);

        return Result.ok("注册实验成功");
    }

    public static List<HashMap<String, Object>> paginate(List<HashMap<String, Object>> resultList, Integer pageIndex, Integer pageSize) {
        // 计算起始索引
        int startIndex = (pageIndex - 1) * pageSize;
        // 计算结束索引，确保不超出 resultList 大小
        int endIndex = Math.min(startIndex + pageSize, resultList.size());

        // 检查起始索引是否合法
        if (startIndex > resultList.size()) {
            return Collections.emptyList();  // 如果起始索引超出结果集大小，返回空列表
        }

        // 返回分页后的子列表
        return resultList.subList(startIndex, endIndex);
    }
}
