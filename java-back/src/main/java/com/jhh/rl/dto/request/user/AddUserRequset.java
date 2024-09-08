package com.jhh.rl.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddUserRequset {

    @NotNull(message = "Account cannot be null")
    private String account;

    @NotNull(message = "Password cannot be null")
    private String password;

    @NotNull(message = "Confirmed password cannot be null")
    @JsonProperty("adduserpwd1")
    private String confirmedPassword;

    @NotNull(message = "User status cannot be null")
    @JsonProperty("user_status")
    private String userStatus;

    @NotNull(message = "Username cannot be null")
    private String username;
}
