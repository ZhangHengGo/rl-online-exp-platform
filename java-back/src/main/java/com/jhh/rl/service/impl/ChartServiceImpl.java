//package com.jhh.rl.service.impl;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.jhh.rl.dto.request.chart.GetExpDatasRequest;
//import com.jhh.rl.dto.response.chart.GetExpListResponse;
//import com.jhh.rl.entity.AlgData;
//import com.jhh.rl.entity.Datas;
//import com.jhh.rl.entity.ExpData;
//import com.jhh.rl.entity.Experiment;
//import com.jhh.rl.mapper.AlgDataMapper;
//import com.jhh.rl.mapper.DatasMapper;
//import com.jhh.rl.mapper.ExpDataMapper;
//import com.jhh.rl.mapper.ExperimentMapper;
//import com.jhh.rl.service.ChartService;
//import com.jhh.rl.utils.Result;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class ChartServiceImpl implements ChartService {
//    @Resource
//    private AlgDataMapper algDataMapper;
//    @Resource
//    private ExpDataMapper expDataMapper;
//    @Resource
//    private ExperimentMapper experimentMapper;
//    @Resource
//    private DatasMapper datasMapper;
//    @Override
//    public Result<List<AlgData>> getAlgDatas(Integer algId) {
//        QueryWrapper<AlgData> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("alg_id", algId);
//        List<AlgData> algDataList = algDataMapper.selectList(queryWrapper);
//        return Result.ok("查询成功", algDataList);
//    }
//
//    @Override
//    public Result<Datas> getEchatsList(Integer expId, Integer episodeId) {
//        QueryWrapper<Datas> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("exp_id", expId).eq("episode_id", episodeId);
//        Datas datas = datasMapper.selectOne(queryWrapper);
//        return Result.ok("查询成功", datas);
//    }
//
//    @Override
//    public Result<double[][]> getExpsData(List<Integer> expIdList, Integer algDataId) {
//        double[][] datas = new double[expIdList.size()][];
//        for (int i = 0; i < expIdList.size(); i++) {
//            QueryWrapper<ExpData> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("exp_id", expIdList.get(i)).eq("alg_data_id", algDataId);
//            List<ExpData> expDataList = expDataMapper.selectList(queryWrapper);
//            if (expDataList.isEmpty()) {
//                datas[i] = new double[0];  // 或者返回空数组
//            }
//            else {
//                ExpData expData = expDataList.get(0);
//                try {
//                    double[] data = convertToDoubleArray(expData.getData());
//                    datas[i] = data;
//                } catch (IllegalArgumentException e) {
//                    return Result.fail("数据转换错误: " + e.getMessage());
//                }
//            }
//        }
//        return Result.ok("查询成功", datas);
//
//    }
//
//    @Override
//    public Result<List<GetExpListResponse>> getExpList(Integer expId, Integer algId) {
//        QueryWrapper<Experiment> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("alg_id", algId).ne("id", expId);
//        List<Experiment> expList = experimentMapper.selectList(queryWrapper);
//        List<GetExpListResponse> responseList = expList.stream()
//                .map(exp -> new GetExpListResponse(exp.getId(), exp.getName()))
//                .collect(Collectors.toList());
//        return Result.ok("查询成功", responseList);
//    }
//
//    @Override
//    public Result<double[][]> getExpDatas(GetExpDatasRequest req) {
//        // 根据请求的ID数量动态创建数组大小
//        double[][] datas = new double[req.getShowedAlgDataIds().size()][];
//        QueryWrapper<ExpData> queryWrapper = new QueryWrapper<>();
//
//        for (int i = 0; i < req.getShowedAlgDataIds().size(); i++) {
//            // 为每次循环清除查询条件，避免条件累积
//            queryWrapper.clear();
//            queryWrapper.eq("exp_id", req.getExpId()).eq("alg_data_id", req.getShowedAlgDataIds().get(i));
//            List<ExpData> expDataList = expDataMapper.selectList(queryWrapper);
//
//            if (expDataList.isEmpty()) {
//                // 如果查询结果为空，可以选择返回一个空数组或进行其他处理
//                datas[i] = new double[0]; // 分配一个空的double数组，表示无数据
//            } else {
//                // 取第一个查询结果
//                ExpData expData = expDataList.get(0);
//                try {
//                    // 尝试将字符串转换为double数组
//                    double[] data = convertToDoubleArray(expData.getData());
//                    datas[i] = data;
//                } catch (IllegalArgumentException e) {
//                    // 如果转换失败，可以返回错误信息或处理异常
//                    return Result.fail("数据转换错误: " + e.getMessage());
//                }
//            }
//        }
//        return Result.ok("查询成功", datas);
//    }
//
//
//    public double[] convertToDoubleArray(String expData) throws IllegalArgumentException {
//        if (expData == null) {
//            throw new IllegalArgumentException("Input string is null");
//        }
//
//        // 使用逗号分割字符串
//        String[] parts = expData.split(",");
//        double[] doubleArray = new double[parts.length];
//
//        for (int i = 0; i < parts.length; i++) {
//            try {
//                doubleArray[i] = Double.parseDouble(parts[i]);
//            } catch (NumberFormatException e) {
//                throw new IllegalArgumentException("Input string contains non-numeric values", e);
//            }
//        }
//
//        return doubleArray;
//    }
//}
