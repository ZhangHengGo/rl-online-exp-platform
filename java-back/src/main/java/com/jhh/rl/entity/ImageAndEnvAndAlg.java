package com.jhh.rl.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("image_env_alg")
public class ImageAndEnvAndAlg implements Serializable {
    private Integer imageId;

    private Integer envId;

    private Integer algId;

}
