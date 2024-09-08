//package com.jhh.rl.controller;
//
//import com.jhh.rl.dto.request.chart.GetExpDatasRequest;
//import com.jhh.rl.dto.response.chart.GetExpListResponse;
//import com.jhh.rl.entity.AlgData;
//import com.jhh.rl.entity.Datas;
//import com.jhh.rl.service.ChartService;
//import com.jhh.rl.utils.Result;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class ChartController {
//
//    @Autowired
//    private ChartService chartService;
//
//    @GetMapping("/chart/getalgdatas/{algId}")
//    public Result<List<AlgData>> getAlgDatas(@PathVariable("algId") Integer algId)
//    {
//        return chartService.getAlgDatas(algId);
//    }
//
//    @GetMapping("/chart/getexpsdata/{expIdList}/{algDataId}")
//    public Result<double[][]> getExpData(@PathVariable("expIdList") List<Integer> expIdList, @PathVariable("algDataId") Integer algDataId)
//    {
//        return chartService.getExpsData(expIdList, algDataId);
//    }
//
//    @GetMapping("/chart/getexplist/{expId}/{algId}")
//    public Result<List<GetExpListResponse>> getExpList(@PathVariable("expId") Integer expId, @PathVariable("algId") Integer algId)
//    {
//        return chartService.getExpList(expId, algId);
//    }
//
//    @PostMapping("/chart/getexpdatas")
//    public Result<double[][]> getExpList(@RequestBody GetExpDatasRequest req)
//    {
//        return chartService.getExpDatas(req);
//    }
//
//    @GetMapping("/chart/getechartslist/{expId}/{episodeId}")
//    public Result<Datas> getEchatsList(@PathVariable("expId") Integer expId, @PathVariable("episodeId") Integer episodeId)
//    {
//        return chartService.getEchatsList(expId, episodeId);
//    }
//}