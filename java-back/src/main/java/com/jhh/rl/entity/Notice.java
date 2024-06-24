package com.jhh.rl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Notice implements Serializable {
    @TableId(type = IdType.AUTO)
    @JsonProperty("notice_id")
    private Integer id;

    private String noticeTitle;

    private String noticeDetail;

    private String createTime;

    private String isDelete;
}
