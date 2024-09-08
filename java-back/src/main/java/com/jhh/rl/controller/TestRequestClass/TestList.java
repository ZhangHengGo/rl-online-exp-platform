package com.jhh.rl.controller.TestRequestClass;

import lombok.Data;

@Data
public class TestList {
    private Integer page_no;
    private Integer page_size;
    private String account;
    private String username;
    private String test_status;
    private String create_name;
}
