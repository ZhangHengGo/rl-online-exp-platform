package com.jhh.rl.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("container_port")
public class ContainerAndPort implements Serializable {
    private Integer internalPort;
    private Integer externalPort;
    private String containerId;
}
