package com.jhh.rl.entity;

import lombok.Data;

/**
 * @Author: clp
 * @Date: 2024/9/18 - 09 - 18 - 18:51
 * @Description: com.jhh.rl.entity
 * @version: 1.0
 */

@Data
public class Reward {

    private int episode;

    private float reward;
}
