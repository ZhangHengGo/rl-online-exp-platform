package com.jhh.rl.service;

import com.jhh.rl.dto.request.RegExp;
import com.jhh.rl.dto.response.ExpEntry;
import com.jhh.rl.utils.Result;

import java.util.HashMap;
import java.util.List;

public interface ExpService {

    Result<List<HashMap<String, Object>>> getExpList(Integer userId, String expName);

    Result registerExp(RegExp exp);

}
