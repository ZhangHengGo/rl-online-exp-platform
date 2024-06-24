package com.jhh.rl.controller;

import com.jhh.rl.entity.Container;
import com.jhh.rl.service.AlgService;
import com.jhh.rl.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlgController {

    @Autowired
    private AlgService algService;

    @GetMapping("/alg/getcontainerlist")
    public Result<List<Container>> getContainerList()
    {
        return algService.getContainerList();
    }

}