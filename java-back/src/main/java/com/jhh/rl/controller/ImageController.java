package com.jhh.rl.controller;

import com.jhh.rl.dto.response.ImageEntry;
import com.jhh.rl.entity.Reward;
import com.jhh.rl.service.ImageService;
import com.jhh.rl.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: clp
 * @Date: 2024/10/23 - 10 - 23 - 14:50
 * @Description: com.jhh.rl.controller
 * @version: 1.0
 */
@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/image/list")
    public Result getChartReward(@RequestParam(required = false) Integer userId, @RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        List<ImageEntry> imageAndEnvList = imageService.getImages(userId);
        return Result.ok("ok", imageAndEnvList);
    }
}
