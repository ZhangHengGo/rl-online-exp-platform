package com.jhh.rl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("env_parameter")
public class EnvParam implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer type;

    @JsonProperty("env_id")
    private Integer envId;

    @JsonProperty("parent_id")
    private Integer parentId;

    private Double min;

    private Double max;

    private String options;
}