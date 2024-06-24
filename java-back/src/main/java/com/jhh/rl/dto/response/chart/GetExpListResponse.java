package com.jhh.rl.dto.response.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class GetExpListResponse {

    Integer expId;

    String expName;

    public GetExpListResponse(Integer expId, String expName) {
        this.expId = expId;
        this.expName = expName;
    }
}
