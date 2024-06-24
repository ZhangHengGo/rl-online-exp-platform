package com.jhh.rl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("alg")
public class Alg {

    @TableId(type = IdType.AUTO)
    private Integer id;


}
