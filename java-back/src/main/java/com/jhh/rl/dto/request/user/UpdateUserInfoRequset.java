package com.jhh.rl.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class UpdateUserInfoRequset {

    @JsonProperty("user_id")
    private Integer userId;

    private String password;

    @JsonProperty("user_status")
    private String userStatus;
}

