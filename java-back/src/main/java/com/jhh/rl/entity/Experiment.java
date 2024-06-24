package com.jhh.rl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Experiment implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer algId;

    private String creatTime;

    private Integer status;

    private String name;

}
