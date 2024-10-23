package com.jhh.rl.controller;

import com.jhh.rl.dto.request.CreateContainer;
import com.jhh.rl.dto.request.RegExp;
import com.jhh.rl.entity.Container;
import com.jhh.rl.service.ContainerService;
import com.jhh.rl.service.ExpService;
import com.jhh.rl.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/container")
public class ContainerController {

    @Autowired
    private ContainerService containerService;

    @GetMapping("/getcontainerlist")
    public Result<List<Container>> registerExp(@RequestParam Integer userId)
    {
        return containerService.getContainerList(userId);
    }

    // 创建容器
    @PostMapping("/create")
    public Result add(@RequestBody CreateContainer createContainer)
    {
//        SysUser user = (SysUser) session.getAttribute("user");
//        if (user.getRole()==2) {
//            containerAddObject.setUserid(user.getId());
//        }
        return containerService.create(createContainer);
    }

    // 删除容器
    @PostMapping("/delete")
    public Result delete(@RequestParam Integer containerId) {
        return containerService.delete(containerId);
    }

    // 启动容器
    @PostMapping("/start")
    public Result start(@RequestParam Integer containerId){
        return containerService.start(containerId);
    }

    // 停止容器
    @PostMapping("/stop")
    public Result stop(@RequestParam Integer containerId){
        return containerService.stop(containerId);
    }

}
