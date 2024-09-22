package com.jhh.rl.entity;

import lombok.Data;

/**
 * @Author: clp
 * @Date: 2024/9/18 - 09 - 18 - 21:16
 * @Description: com.jhh.rl.entity
 * @version: 1.0
 */
@Data
public class Loss {

    private int episode;

    private float loss;
}
