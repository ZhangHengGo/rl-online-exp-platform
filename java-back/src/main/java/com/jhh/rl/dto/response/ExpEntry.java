package com.jhh.rl.dto.response;

import lombok.Data;

@Data
public class ExpEntry {
    private Integer userId;
    private String userName;
    private String epxName;
    private String dataPath;
    private Integer imageId;
    private String imageName;
    private Integer algId;
    private String algName;
    private Integer envId;
    private String envName;
    private String createTime;
    private String note;
}
