package com.jhh.rl.dto.response.user;

import lombok.Data;

@Data
public class LoginResponse {
    private Integer user_id;

    private String username;

    private String identity;

    private String account;

    private String password;

    private String user_status;

    private String create_time;

    private String login_time;
}
