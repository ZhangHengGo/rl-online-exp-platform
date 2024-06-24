package com.jhh.rl.service;

import com.jhh.rl.entity.Container;
import com.jhh.rl.utils.Result;

import java.util.List;

public interface AlgService {
    Result<List<Container>> getContainerList();
}
