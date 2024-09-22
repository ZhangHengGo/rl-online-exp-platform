package com.jhh.rl.controller;

import com.jhh.rl.dto.request.RegExp;
import com.jhh.rl.dto.response.ExpEntry;
import com.jhh.rl.entity.Container;
import com.jhh.rl.service.ExpService;
import com.jhh.rl.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class ExpController {

    @Autowired
    private ExpService expService;

    @GetMapping("/exp/getexplist")
    public Result<List<HashMap<String, Object>>> getContainerList(@RequestParam Integer userId, @RequestParam(required = false) String expName)
    {
        return expService.getExpList(userId, expName);
    }

    @PostMapping("/exp/registerexp")
    public Result registerExp(@RequestBody RegExp exp)
    {
        return expService.registerExp(exp);
    }

}