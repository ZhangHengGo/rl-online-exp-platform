package com.jhh.rl.dto.request.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetUserListRequset {
    @NotNull
    private Integer page_no;
    @NotNull
    private Integer page_size;
    @NotNull
    private String account;
    @NotNull
    private String username;
    @NotNull
    private String user_status;
}
