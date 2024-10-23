package com.jhh.rl.dto.response;

import lombok.Data;

/**
 * @Author: clp
 * @Date: 2024/10/23 - 10 - 23 - 15:25
 * @Description: com.jhh.rl.entity
 * @version: 1.0
 */
@Data
public class ImageEntry {
    private Integer id;

    private String version;

    private Integer makeUserId;

    private Integer createUserId;

    private String createTime;

    private String note;

    private String makeUserName;

    private String createUserName;

    private String envName;

    private String algName;

}
