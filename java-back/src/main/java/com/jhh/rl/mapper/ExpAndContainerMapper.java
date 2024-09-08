package com.jhh.rl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jhh.rl.entity.Algorithm;
import com.jhh.rl.entity.ExpAndContainer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ExpAndContainerMapper extends BaseMapper<ExpAndContainer> {
}