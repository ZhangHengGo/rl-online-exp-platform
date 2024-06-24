package com.jhh.rl.dto.response.notice;

import lombok.Data;

@Data
public class GetNoticeListResponse {
    private Integer notice_id;

    private String notice_title;

    private String notice_detail;

    private String create_time;

    private Integer user_id;

    private Integer read_notice_id;

    private String is_read;

}
