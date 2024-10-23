package com.jhh.rl.dto.request;

import com.jhh.rl.entity.PortMapping;
import lombok.Data;

import java.util.List;

@Data
public class CreateContainer {
    private String name;
    private Integer userId;
    private Integer imageId;
    private List<PortMapping> portMappingList;
    private String extraConfig;
}
