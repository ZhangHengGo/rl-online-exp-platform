package com.jhh.rl.controller;

import com.jhh.rl.controller.DTO.TestDTO;
import com.jhh.rl.controller.TestRequestClass.AddTest;
import com.jhh.rl.controller.TestRequestClass.TestList;
import com.jhh.rl.controller.TestRequestClass.UpdateTestInfo;
import com.jhh.rl.service.ExperimentService;
import com.jhh.rl.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {
//    @Resource
//    private ExperimentService testService;
//
//    @PostMapping("/front/test/addtest")
//    public Result addtest(@RequestBody AddTest addTest) {return testService.addtest(addTest);}
//
//    @PostMapping("/front/test/gettestlist")
//    public Result<List<TestDTO>> gettestlits(@RequestBody TestList testList){return testService.gettestlist(testList);}
//
//    @PostMapping("/front/test/updatetestinfo")
//    public Result updatetestinfo(@RequestBody UpdateTestInfo updateTestInfo){return testService.updatetestinfo(updateTestInfo);}
}