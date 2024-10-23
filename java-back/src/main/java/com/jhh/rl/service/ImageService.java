package com.jhh.rl.service;

import com.jhh.rl.entity.Image;
import com.jhh.rl.entity.ImageAndEnv;

import java.util.List;

/**
 * @Author: clp
 * @Date: 2024/10/23 - 10 - 23 - 15:07
 * @Description: com.jhh.rl.service
 * @version: 1.0
 */
public interface ImageService {
    List<ImageAndEnv> getImages(Integer userId);
}
