package com.jhh.rl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data // 自动生成getter和setter方法，以及hashCode、equals和toString方法
@TableName("user") //指定这个类对应的数据库表名为user
public class User implements Serializable {
    @TableId(type = IdType.AUTO) // 指定这个属性是数据库表的主键，并且主键的生成策略是自增
    @JsonProperty("user_id") // 用于在序列化和反序列化时，将这个属性映射到JSON对象的user_id字段
    private Integer id;

    private String username;

    private String account;

    private String password;

    private String identity;

    @JsonProperty("user_status")
    private String userStatus;

    @JsonProperty("create_time")
    private String createTime;

    @JsonProperty("login_time")
    private String loginTime;

    private String email;


}
