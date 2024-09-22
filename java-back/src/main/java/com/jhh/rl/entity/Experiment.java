package com.jhh.rl.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("experiment")
public class Experiment implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String createTime;

    private Integer status;

    private String dataPath;

    private String note;
}
