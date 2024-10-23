package com.jhh.rl.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("container")
public class Container implements Serializable {
    @TableId(type = IdType.AUTO)
    private String id;

    private String name;

    private Integer userId;

    private String createTime;

    private Integer status;
}
