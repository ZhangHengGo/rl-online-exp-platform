package com.jhh.rl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jhh.rl.dto.response.ExpEntry;
import com.jhh.rl.entity.*;
import com.jhh.rl.mapper.*;
import com.jhh.rl.service.ExpService;
import com.jhh.rl.utils.Result;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
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

    @Override
    public Result<List<HashMap<String, Object>>> getExpList(Integer userId, String expName) {
        // 查询实验表的条件
        QueryWrapper<Experiment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        // 判断查询结果是否为空
        if (expName != null && !expName.isEmpty()) {
            queryWrapper.like("name", expName);
        }
        List<Experiment> experimentList = experimentMapper.selectList(queryWrapper);

        // 找到实验表的数据，检查是否为空
        if (experimentList.isEmpty()) {
            return Result.ok("没有找到任何实验记录");
        } else {
            List<HashMap<String, Object>> resultList = new ArrayList<>();
            for(Experiment exp : experimentList){
                QueryWrapper<ExpAndContainer> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("exp_id", exp.getId());
                ExpAndContainer expAndContainer = expAndContainerMapper.selectOne(queryWrapper1);
                Integer containerId = expAndContainer.getContainerId();

                QueryWrapper<ImageAndContainer> queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.eq("container_id", containerId);
                ImageAndContainer imageAndContainer = imageAndContainerMapper.selectOne(queryWrapper2);
                Integer imageId = imageAndContainer.getImageId();

                QueryWrapper<ImageAndEnvAndAlg> queryWrapper3 = new QueryWrapper<>();
                queryWrapper3.eq("image_id", imageId);
                ImageAndEnvAndAlg imageAndEnvAndAlg = imageAndEnvAndAlgMapper.selectOne(queryWrapper3);
                Integer envId = imageAndEnvAndAlg.getEnvId();
                Integer algId = imageAndEnvAndAlg.getAlgId();

                QueryWrapper<Container> queryWrapper4 = new QueryWrapper<>();
                queryWrapper4.eq("id", containerId);
                Container container = containerMapper.selectOne(queryWrapper4);
                String containerName = container.getName();

                QueryWrapper<Image> queryWrapper5 = new QueryWrapper<>();
                queryWrapper5.eq("id", imageId);
                Image image = imageMapper.selectOne(queryWrapper5);
                String imageName = image.getName();

                QueryWrapper<Environment> queryWrapper6 = new QueryWrapper<>();
                queryWrapper6.eq("id", envId);
                Environment environment = environmentMapper.selectOne(queryWrapper6);
                String envName = environment.getName();

                QueryWrapper<Algorithm> queryWrapper7 = new QueryWrapper<>();
                queryWrapper7.eq("id", algId);
                Algorithm algorithm = algorithmMapper.selectOne(queryWrapper7);
                String algName = algorithm.getName();

                HashMap<String, Object> entry = new HashMap<>();
                entry.put("id", exp.getId());
                entry.put("userId", exp.getUserId());
                entry.put("name", exp.getName());
                entry.put("createTime", exp.getCreateTime());
                entry.put("status", exp.getStatus());
                entry.put("dataPath", exp.getDataPath());
                entry.put("note", exp.getNote());
                entry.put("containerId", containerId);
                entry.put("containerName", containerName);
                entry.put("algId", algId);
                entry.put("algName", algName);
                entry.put("envId", envId);
                entry.put("envName", envName);

                resultList.add(entry);
            }
            return Result.ok("ok", resultList);
        }


    }
}
