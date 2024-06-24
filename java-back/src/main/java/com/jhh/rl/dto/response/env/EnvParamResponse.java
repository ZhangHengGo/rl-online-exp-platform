package com.jhh.rl.dto.response.env;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class EnvParamResponse {

    Integer type;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Double min;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Double max;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String[] options;
}
