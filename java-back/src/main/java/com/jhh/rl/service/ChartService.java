package com.jhh.rl.service;
import com.jhh.rl.dto.request.chart.GetExpDatasRequest;
import com.jhh.rl.dto.response.chart.GetExpListResponse;
import com.jhh.rl.entity.AlgData;
import com.jhh.rl.entity.Datas;
import com.jhh.rl.entity.ExpData;
import com.jhh.rl.utils.Result;

import java.util.List;

public interface ChartService {
    Result<List<AlgData>> getAlgDatas(Integer expId);

    Result<double[][]> getExpsData(List<Integer> expIdList, Integer algDataId);

    Result<List<GetExpListResponse>> getExpList(Integer expId, Integer algId);

    Result<double[][]> getExpDatas(GetExpDatasRequest req);

    Result<Datas> getEchatsList(Integer expId, Integer episodeId);
}
