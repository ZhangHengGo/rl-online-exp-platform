package com.jhh.rl.controller;

import com.jhh.rl.dto.request.RegExp;
import com.jhh.rl.entity.Container;
import com.jhh.rl.service.ContainerService;
import com.jhh.rl.service.ExpService;
import com.jhh.rl.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContainerController {

    @Autowired
    private ContainerService containerService;

    @GetMapping("/container/getcontainerlist")
    public Result<List<Container>> registerExp(@RequestParam Integer userId)
    {
        return containerService.getContainerList(userId);
    }
}
