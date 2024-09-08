package com.jhh.rl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data // 自动生成getter和setter方法，以及hashCode、equals和toString方法
@TableName("image") //指定这个类对应的数据库表名为user
public class Image implements Serializable {
    @TableId(type = IdType.AUTO) // 指定这个属性是数据库表的主键，并且主键的生成策略是自增
    private Integer id;

    private String name;

    private String version;

    private Integer makeUserId;

    private Integer createUserId;

    private String createTime;

    private String note;


}
