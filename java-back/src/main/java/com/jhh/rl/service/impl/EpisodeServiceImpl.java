package com.jhh.rl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jhh.rl.entity.AlgData;
import com.jhh.rl.entity.ExpData;
import com.jhh.rl.entity.Experiment;
import com.jhh.rl.mapper.AlgDataMapper;
import com.jhh.rl.mapper.EnvParamMapper;
import com.jhh.rl.mapper.ExpDataMapper;
import com.jhh.rl.mapper.ExperimentMapper;
import com.jhh.rl.service.EpisodeService;
import com.jhh.rl.utils.Result;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EpisodeServiceImpl implements EpisodeService {

    @Resource
    private EnvParamMapper envParamMapper;
    @Resource
    private AlgDataMapper algDataMapper;
    @Resource
    private ExpDataMapper expDataMapper;
    @Resource
    private ExperimentMapper experimentMapper;

    @Override
    public Result<List<Map<String, Object>>> getExpEpisodeData(Integer expId, Integer episodeId) {

        QueryWrapper<Experiment> expQueryWrapper = new QueryWrapper<>();
        expQueryWrapper.select("alg_id").eq("exp_id", expId);
        Experiment experiment = experimentMapper.selectOne(expQueryWrapper);
        if(experiment != null){
            Integer algId = experiment.getAlgId();
            QueryWrapper<AlgData> algDataqueryWrapper = new QueryWrapper<>();
            algDataqueryWrapper.eq("alg_id", algId);
            List<AlgData> algDataList = algDataMapper.selectList(algDataqueryWrapper);
            List<Map<String, Object>> response = new ArrayList<>();
            for(AlgData algData:algDataList){
                Integer algDataId = algData.getId();
                QueryWrapper<ExpData> expDataqueryWrapper = new QueryWrapper<>();
                expDataqueryWrapper.eq("id", expId).eq("alg_data_id", algDataId);
                ExpData expData = expDataMapper.selectOne(expDataqueryWrapper);
                Map<String, Object> detail = new HashMap<>();
                detail.put("name", algData.getDataName());
                detail.put("data", expData.getData());
                detail.put("type", 1);
                response.add(detail);
            }
            return Result.ok("查询轨迹信息成功", response);
        }
        return Result.fail("查询轨迹信息成功");


    }

    @Override
    public Result<Map<String, Object>> test(Integer expId, Integer episodeId){
        String filePath = "D:\\xianshangpingtai\\RL-app\\src\\assets\\navigation.csv"; // 替换为实际文件路径
        int episodeToFind = episodeId; // 设置你需要查找的episode_i

        return Result.ok("ok",readCSV(filePath, episodeToFind));
    }

    private static Map<String, Object> readCSV(String filePath, int episodeToFind) {
//        try (Reader reader = new FileReader(filePath)) {
//            Iterable<CSVRecord> records = CSVFormat.DEFAULT
//                    .withFirstRecordAsHeader()
//                    .parse(reader);
//
//            List<Double> qValues0 = new ArrayList<>();
//            List<Double> qValues1 = new ArrayList<>();
//            List<Integer> actions = new ArrayList<>();
//            List<Double> targetsQvalues = new ArrayList<>();
//
//            for (CSVRecord record : records) {
//                int episode_i = Integer.parseInt(record.get("episode_i"));
//                if (episode_i == episodeToFind) {
//                    String episodeReward = record.get("episode_reward");
//                    if (episodeReward != null && !episodeReward.trim().isEmpty()){
//                        break;
//                    }
//                    double qValue0 = Double.parseDouble(record.get("Qvalue[0]"));
//                    double qValue1 = Double.parseDouble(record.get("Qvalue[1]"));
//                    int action = Integer.parseInt(record.get("action"));
//                    double targetsQvalue = Double.parseDouble(record.get("targets_Qvalue"));
//
//                    qValues0.add(qValue0);
//                    qValues1.add(qValue1);
//                    actions.add(action);
//                    targetsQvalues.add(targetsQvalue);
//                }
//            }
//            List<List<Double>> heatmapData = createHeatmapData(qValues0, qValues1);
//            Map<String, Object> result = new HashMap<>();
//            result.put("qvalue", heatmapData);
//            result.put("action", actions);
//            result.put("targetsQvalue", targetsQvalues);
//            System.out.println(result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Map<String, Object> result = new HashMap<>();
        try (Reader reader = new FileReader(filePath)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(reader);

            List<Double> targetValue = new ArrayList<>();
            List<Double> entropy = new ArrayList<>();
            List<Double> episodeReward = new ArrayList<>();
            List<Double> trainLoss = new ArrayList<>();
            List<Double> policy_loss = new ArrayList<>();
            List<Double> value_loss = new ArrayList<>();

            for (CSVRecord record : records) {
                int episode_i = Integer.parseInt(record.get("episode_i"));
                if (episode_i == episodeToFind) {
                    String tv = record.get("targets_Qvalue");
                    String tv2 = record.get("entropy");
                    if ((tv == null || tv.trim().isEmpty()) && (tv2 == null || tv2.trim().isEmpty())){
                        break;
                    }
                    if(tv != null && !tv.trim().isEmpty()){
                        double targetsQvalue = Double.parseDouble(record.get("targets_Qvalue"));
                        targetValue.add(targetsQvalue);
                    }
                    if(tv2 != null && !tv2.trim().isEmpty()){
                        double e = Double.parseDouble(record.get("entropy"));
                        entropy.add(e);
                    }
                }
            }
            for (CSVRecord record : records) {
                String tv = record.get("targets_Qvalue");
                System.out.println("1");
                System.out.println(tv);
                String tv2 = record.get("entropy");
                if ((tv == null || tv.trim().isEmpty()) ){

                    String episode_reward = record.get("episode_reward");
                    System.out.println(episode_reward);
                    if(episode_reward != null && !episode_reward.trim().isEmpty()){
                        episodeReward.add(Double.parseDouble(episode_reward));
                    }

                    String loss = record.get("train/loss");
                    System.out.println(loss);
                    if(loss != null && !loss.trim().isEmpty()){
                        trainLoss.add(Double.parseDouble(loss));
                    }
                    String pl = record.get("policy_loss");
                    System.out.println(pl);
                    if(pl != null && !pl.trim().isEmpty()){
                        policy_loss.add(Double.parseDouble(pl));
                    }

                    String vl = record.get("value_loss");
                    if(vl != null && !vl.trim().isEmpty()){
                        value_loss.add(Double.parseDouble(vl));
                    }
                    if(record.get("episode_i").equals("252")){
                        break;
                    }
                }

            }
            result.put("targetsQvalue", targetValue); // time
            // episode
            result.put("episodeReward", episodeReward);
            result.put("trainLoss", trainLoss);
            result.put("entropy", entropy);
            result.put("policy_loss", policy_loss);
            result.put("value_loss", value_loss);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }

    private static List<List<Double>> createHeatmapData(List<Double> qValues0, List<Double> qValues1) {
        List<List<Double>> heatmapData = new ArrayList<>();

        for (int i = 0; i < qValues0.size(); i++) {
            // 添加 Qvalue[0] 数据
            List<Double> entry0 = new ArrayList<>();
            entry0.add((double) i);  // 行索引
            entry0.add(0.0);         // 列索引，0 代表 Qvalue[0]
            entry0.add(qValues0.get(i));
            heatmapData.add(entry0);

            // 添加 Qvalue[1] 数据
            List<Double> entry1 = new ArrayList<>();
            entry1.add((double) i);  // 行索引
            entry1.add(1.0);         // 列索引，1 代表 Qvalue[1]
            entry1.add(qValues1.get(i));
            heatmapData.add(entry1);
        }

        return heatmapData;
    }
}
