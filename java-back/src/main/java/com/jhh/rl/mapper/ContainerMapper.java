package com.jhh.rl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jhh.rl.entity.Container;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ContainerMapper extends BaseMapper<Container> {

    Integer updateContainerStatus(@Param(value = "status") Integer status,
                         @Param(value = "id") Integer id);
}