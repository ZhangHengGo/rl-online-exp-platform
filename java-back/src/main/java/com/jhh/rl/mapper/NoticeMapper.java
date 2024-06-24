package com.jhh.rl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jhh.rl.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface NoticeMapper extends BaseMapper<Notice> {
}
