package com.jhh.rl.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UpdateEmailRequset {

    @NotNull
    @Email  // 使用 javax.validation.constraints.Email 校验邮箱格式
    private String email;

    @NotNull
    @JsonProperty("user_id") // 保持 JSON 中的 user_id 映射到 userId
    private Integer userId;
}
