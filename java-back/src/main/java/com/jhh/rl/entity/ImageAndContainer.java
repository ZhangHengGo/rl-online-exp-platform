package com.jhh.rl.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("image_container")
public class ImageAndContainer implements Serializable {

    private Integer imageId;

    private Integer containerId;
}
