package com.jhh.rl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("container")
public class Container {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer imageId;


}
