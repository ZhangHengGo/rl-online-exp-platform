package com.jhh.rl.controller;

import com.jhh.rl.dto.request.chart.GetExpDatasRequest;
import com.jhh.rl.dto.response.chart.GetExpListResponse;
//import com.jhh.rl.entity.AlgData;
//import com.jhh.rl.entity.Datas;
import com.jhh.rl.entity.*;
import com.jhh.rl.service.ChartService;
import com.jhh.rl.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//
@RestController
public class ChartController {

    @Autowired
    private ChartService chartService;
    @PostMapping("/chart/getChartReward")
    public Result getChartReward(@RequestParam Integer experimentId){
        List<Reward> chartReward = chartService.getChartReward(experimentId);
        return Result.ok("ok", chartReward);
    }
    @PostMapping("/chart/getChartLoss")
    public Result getChartLoss(@RequestParam Integer experimentId){
        List<Loss> chartLoss = chartService.getChartLoss(experimentId);
        return Result.ok("ok", chartLoss);
    }
    @PostMapping("/chart/getChartAction")
    public Result getChartAction(@RequestParam Integer episodeId){
        List<Action> chartAction = chartService.getChartAction(episodeId);
        return Result.ok("ok", chartAction);
    }
    @PostMapping("/chart/getChartValue")
    public Result getChartValue(@RequestParam Integer episodeId){
        List<Value> chartValue = chartService.getChartValue(episodeId);
        return Result.ok("ok", chartValue);
    }
    @PostMapping("/chart/getChartQValue")
    public Result getChartQValue(@RequestParam Integer episodeId){
        QValue chartQValue = chartService.getChartQValue(episodeId);
        return Result.ok("ok", chartQValue);
    }

    private final String imageFolderPath = "java-back/src/main/resources/folder/images"; // 图片存储文件夹的路径

    @GetMapping("/images/list")
    public List<String> listAllImages() {
        File folder = new File(imageFolderPath);
        // 只返回jpg, png等常见图片格式的文件名
        return Arrays.stream(folder.listFiles())
                .filter(file -> file.isFile() && (file.getName().endsWith(".jpg") || file.getName().endsWith(".png")))
                .map(File::getName)
                .collect(Collectors.toList());
    }

    private final Path imageLocation = Paths.get("java-back/src/main/resources/folder/images"); // 你需要替换为实际路径

    // 获取特定图片内容
    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
        try {
            // 构建图片文件路径
            Path file = imageLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            // 检查文件是否存在且可读
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource); // 返回图片资源
            } else {
                return ResponseEntity.notFound().build(); // 如果图片不存在，返回404
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build(); // 如果路径有问题，返回400错误
        }
    }

//    @GetMapping("/chart/getalgdatas/{algId}")
//    public Result<List<AlgData>> getAlgDatas(@PathVariable("algId") Integer algId)
//    {
//        return chartService.getAlgDatas(algId);
//    }

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
}