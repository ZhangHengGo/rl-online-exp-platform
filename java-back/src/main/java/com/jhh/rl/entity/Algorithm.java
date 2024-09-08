package com.jhh.rl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("algorithm")
public class Algorithm implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String note;

    private Integer userId;

    private String createTime;

}