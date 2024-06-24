package com.jhh.rl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable; //序列化和反序列化

@Data
public class NoticeReadHistory implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer noticeId;

    private String readStatus;

    private String readTime;
}
