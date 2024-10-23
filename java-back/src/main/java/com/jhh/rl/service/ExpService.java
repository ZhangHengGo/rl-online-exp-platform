package com.jhh.rl.service;

import com.jhh.rl.dto.request.RegExp;
import com.jhh.rl.dto.response.ExpEntry;
import com.jhh.rl.utils.Result;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

public interface ExpService {

    Result<HashMap<String, Object>> getExpList(Integer userId,
                                               Integer pageIndex,
                                               Integer pageSize,
                                               String expName,
                                               String containerName,
                                               String expNote,
                                               String envName,
                                               String expStatus);

    Result registerExp(RegExp exp);

}
