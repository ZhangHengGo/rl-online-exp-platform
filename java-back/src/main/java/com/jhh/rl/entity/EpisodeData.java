package com.jhh.rl.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("episode_data")
public class EpisodeData implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;


    private Integer expId;


    private Integer episodeId;

    private String data;
}