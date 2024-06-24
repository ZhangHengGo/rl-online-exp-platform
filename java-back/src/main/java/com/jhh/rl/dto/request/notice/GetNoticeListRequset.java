package com.jhh.rl.dto.request.notice;

import lombok.Data;

@Data
public class GetNoticeListRequset {
    private Integer page_no;
    private Integer page_size;
    private String notice_title;
}
