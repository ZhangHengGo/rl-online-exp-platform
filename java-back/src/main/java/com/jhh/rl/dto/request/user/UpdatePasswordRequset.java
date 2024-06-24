package com.jhh.rl.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdatePasswordRequset {

    @NotNull
    //@Size(min = 8, message = "New password must be at least 8 characters long")
    @JsonProperty("newpwd")
    private String newPassword;

    @NotNull
    @JsonProperty("oldpwd")
    private String oldPassword;

    @NotNull
    @JsonProperty("user_id")
    private Integer userId;
}
