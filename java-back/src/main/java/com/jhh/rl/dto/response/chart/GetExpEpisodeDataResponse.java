package com.jhh.rl.dto.response.chart;

import lombok.Data;

@Data
public class GetExpEpisodeDataResponse {
    String dataName;
    Integer dataType;
    String data;

    public GetExpEpisodeDataResponse(String dataName, Integer dataType, String data) {
        this.dataName = dataName;
        this.dataType = dataType;
        this.data = data;
    }
}
