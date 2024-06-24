package com.jhh.rl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@TableName("datas")
public class Datas {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer expId;

    private Integer episodeId;
    @JsonProperty("shap")
    private String shap;
    @JsonProperty("movement_decision")
    private  String movementDecision;
    @JsonProperty("qvalue")
    private String qvalue;
    @JsonProperty("value_function")
    private String valueFunction;
    @JsonProperty("reward_signal")
    private String rewardSignal;
    @JsonProperty("learning_curve")
    private String learningCurve;
}
