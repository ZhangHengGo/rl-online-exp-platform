package com.jhh.rl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jhh.rl.entity.ImageAndContainer;
import com.jhh.rl.entity.ImageAndEnvAndAlg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface ImageAndEnvAndAlgMapper extends BaseMapper<ImageAndEnvAndAlg> {

    @Select("SELECT * FROM image_env_alg WHERE image_id = #{imageId}")
    ImageAndEnvAndAlg selectByImageId(@Param("imageId") int imageId);
}