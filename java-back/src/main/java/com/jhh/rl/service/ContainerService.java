package com.jhh.rl.service;

import com.jhh.rl.dto.request.RegExp;
import com.jhh.rl.entity.Container;
import com.jhh.rl.utils.Result;

import java.util.List;

public interface ContainerService {

    Result<List<Container>> getContainerList(Integer userId);
}
