package com.jhh.rl.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jhh.rl.dto.request.RegExp;
import com.jhh.rl.dto.response.ExpEntry;
import com.jhh.rl.entity.Container;
import com.jhh.rl.service.ExpService;
import com.jhh.rl.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.List;

@RestController
public class ExpController {

    @Autowired
    private ExpService expService;

    @GetMapping("/exp/getexplist")
    public Result<HashMap<String, Object>> getContainerList(@RequestParam(required = false) Integer userId,
                                                            @RequestParam(required = false) Integer pageIndex,
                                                            @RequestParam(required = false) Integer pageSize,
                                                            @RequestParam(required = false) String expName,
                                                            @RequestParam(required = false) String containerName,
                                                            @RequestParam(required = false) String expNote,
                                                            @RequestParam(required = false) String envName,
                                                            @RequestParam(required = false) String expStatus)
    {
        return expService.getExpList(1, pageIndex, pageSize, expName, containerName, expNote, envName, expStatus);
    }

    @PostMapping("/exp/registerexp")
    public Result registerExp(@RequestBody RegExp exp)
    {
        return expService.registerExp(exp);
    }

}