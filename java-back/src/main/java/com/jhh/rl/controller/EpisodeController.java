//package com.jhh.rl.controller;
//
//import com.jhh.rl.entity.Container;
//import com.jhh.rl.service.AlgService;
//import com.jhh.rl.service.EpisodeService;
//import com.jhh.rl.utils.Result;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class EpisodeController {
//    @Autowired
//    private EpisodeService episodeService;
//
//    @GetMapping("/chart/getexpepisodedata/{expId}/{episodeId}")
//    public Result<List<Map<String, Object>>> getExpEpisodeData(@PathVariable("expId") Integer expId, @PathVariable("episodeId") Integer episodeId)
//    {
//        return episodeService.getExpEpisodeData(expId, episodeId);
//    }
//    @GetMapping("/chart/test/{expId}/{episodeId}")
//    public Result<Map<String, Object>> test(@PathVariable("expId") Integer expId, @PathVariable("episodeId") Integer episodeId)
//    {
//        return episodeService.test(expId, episodeId);
//    }
//}
