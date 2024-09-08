package com.jhh.rl.controller.TestRequestClass;

import lombok.Data;

@Data
public class UpdateTestInfo {
    private Integer test_id;
    private String test_name;
    private String user_name;
    private String test_status;
}
