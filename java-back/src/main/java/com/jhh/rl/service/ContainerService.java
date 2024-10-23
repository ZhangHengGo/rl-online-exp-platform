package com.jhh.rl.service;

import com.jhh.rl.dto.request.CreateContainer;
import com.jhh.rl.dto.request.RegExp;
import com.jhh.rl.entity.Container;
import com.jhh.rl.utils.Result;

import java.util.List;

public interface ContainerService {

    Result<List<Container>> getContainerList(Integer userId);

    Result create(CreateContainer createContainer);

    Result start(Integer id);

    Result stop(Integer id);

    Result delete(Integer id);
}
