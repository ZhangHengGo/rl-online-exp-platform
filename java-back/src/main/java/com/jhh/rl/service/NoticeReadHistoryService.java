package com.jhh.rl.service;

import com.jhh.rl.utils.Result;

public interface NoticeReadHistoryService {
    Result<Void> setRead(Integer user_id,Integer notice_id);

    Result<Void> setAllRead(Integer user_id);
}
