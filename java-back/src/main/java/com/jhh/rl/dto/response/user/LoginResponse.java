package com.jhh.rl.dto.response.user;

import lombok.Data;

@Data
public class LoginResponse {
    private Integer userId;

    private String username;

    private String identity;

    private String account;

    private String password;

    private String userStatus;

    private String createTime;

    private String loginTime;
}
