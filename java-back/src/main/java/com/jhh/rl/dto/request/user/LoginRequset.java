package com.jhh.rl.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginRequset {

    @NotNull(message = "Account cannot be null")
    @JsonProperty("username")
    private String account;

    @NotNull(message = "Password cannot be null")
    private String password;
}