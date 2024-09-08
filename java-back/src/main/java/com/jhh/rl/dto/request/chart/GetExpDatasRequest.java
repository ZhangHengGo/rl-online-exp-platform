package com.jhh.rl.dto.request.chart;

import lombok.Data;

import java.util.List;

@Data
public class GetExpDatasRequest {
    Integer expId;
    List<Integer> showedAlgDataIds;
}
