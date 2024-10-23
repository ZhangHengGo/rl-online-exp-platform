package com.jhh.rl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jhh.rl.entity.*;
import com.jhh.rl.mapper.*;
import com.jhh.rl.service.ImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: clp
 * @Date: 2024/10/23 - 10 - 23 - 15:09
 * @Description: com.jhh.rl.service.impl
 * @version: 1.0
 */
@Service
public class ImageServiceImpl implements ImageService {
    @Resource
    private ImageMapper imageMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ImageAndEnvAndAlgMapper imageAndEnvAndAlgMapper;

    @Resource
    private EnvironmentMapper environmentMapper;

    @Resource
    private AlgorithmMapper algorithmMapper;
    @Override
    public List<ImageAndEnv> getImages(Integer userId){
//        QueryWrapper<Image> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_id", userId);
        List<Image> imageList = imageMapper.selectAll();
        List<ImageAndEnv> imageAndEnvList = new ArrayList<>();
        for(Image image: imageList){
            User createUser = userMapper.selectById(image.getCreateUserId());
            User makeUser = userMapper.selectById(image.getMakeUserId());
            ImageAndEnvAndAlg imageAndEnvAndAlg = imageAndEnvAndAlgMapper.selectById(image.getId());
            Environment environment = environmentMapper.selectById(imageAndEnvAndAlg.getEnvId());
            Algorithm algorithm = algorithmMapper.selectById(imageAndEnvAndAlg.getAlgId());
            ImageAndEnv imageAndEnv = new ImageAndEnv();
            imageAndEnv.setId(image.getId());
            imageAndEnv.setVersion(image.getVersion());
            imageAndEnv.setCreateTime(image.getCreateTime());
            imageAndEnv.setNote(image.getNote());
            imageAndEnv.setCreateUserName(createUser.getUsername());
            imageAndEnv.setMakeUserName(makeUser.getUsername());
            imageAndEnv.setEnvName(environment.getName());
            imageAndEnv.setAlgName(algorithm.getName());
            imageAndEnvList.add(imageAndEnv);
        }
        return imageAndEnvList;
    }
}
