package com.jhh.rl.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegExp {

    @NotNull
    private Integer userId;

    @NotNull
    private String expNote;

    @NotNull
    private String expName;

    @NotNull
    private Integer containerId;

    @NotNull
    private String containerName;
}
