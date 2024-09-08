//package com.jhh.rl.controller;
//
//
//import com.jhh.rl.service.EnvService;
//import com.jhh.rl.utils.Result;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import java.util.Map;
//
//@RestController
//public class EnvController {
//
//    @Autowired
//    private EnvService envService;
//
//    @GetMapping("/env/param")
//    public Result<Map<String, Object>> getEnvParams(@RequestParam("envId") Integer envId)
//    {
//        return envService.getEnvParams(envId);
//    }
//}
