package com.jhh.rl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jhh.rl.entity.AlgData;
import com.jhh.rl.entity.Container;
import com.jhh.rl.mapper.AlgDataMapper;
import com.jhh.rl.mapper.ContainerMapper;
import com.jhh.rl.service.AlgService;
import com.jhh.rl.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AlgServiceImpl  implements AlgService {


    @Resource
    private ContainerMapper containerMapper;

    @Override
    public Result<List<Container>> getContainerList() {

        QueryWrapper<Container> queryWrapper = new QueryWrapper<>();
        List<Container> containerList = containerMapper.selectList(queryWrapper);
        return Result.ok("查询成功", containerList);
    }
}
