package com.jhh.rl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jhh.rl.dto.request.RegExp;
import com.jhh.rl.entity.Container;
import com.jhh.rl.entity.Experiment;
import com.jhh.rl.entity.ImageAndEnvAndAlg;
import com.jhh.rl.entity.UserAndContainer;
import com.jhh.rl.mapper.ContainerMapper;
import com.jhh.rl.mapper.UserAndContainerMapper;
import com.jhh.rl.service.ContainerService;
import com.jhh.rl.service.ExperimentService;
import com.jhh.rl.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ContainerServiceImpl implements ContainerService {


    @Resource
    private UserAndContainerMapper userAndContainerMapper;

    @Resource
    private ContainerMapper containerMapper;

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

}
