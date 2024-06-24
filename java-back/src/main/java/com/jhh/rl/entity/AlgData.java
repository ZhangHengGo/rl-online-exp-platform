package com.jhh.rl.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("alg_data")
public class AlgData implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;


    private String dataName;


    private Integer algId;
}
