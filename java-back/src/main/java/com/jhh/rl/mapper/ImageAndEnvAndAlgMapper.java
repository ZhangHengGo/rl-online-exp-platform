package com.jhh.rl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jhh.rl.entity.ImageAndContainer;
import com.jhh.rl.entity.ImageAndEnvAndAlg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ImageAndEnvAndAlgMapper extends BaseMapper<ImageAndEnvAndAlg> {
}