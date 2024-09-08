package com.jhh.rl.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FeedbackRequset {

    @NotNull(message = "Account cannot be null")
    private String account;

    @NotNull(message = "Username cannot be null")
    private String username;

    @NotNull(message = "Feedback information cannot be null")
    @JsonProperty("feedback_info")
    private String feedbackInfo;
}