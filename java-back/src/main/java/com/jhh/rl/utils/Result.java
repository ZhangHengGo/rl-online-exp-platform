package com.jhh.rl.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class Result<T> {
    private Integer code; // 状态码

    private String msg; // 解释信息


    @JsonInclude(JsonInclude.Include.NON_NULL) // 该注解表示只有不为NULL才包含在JSON中
    private T data; // 具体数据


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer count; // 数据的数量

    public Result(Integer code, String msg, T data, Integer count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }

    private Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public static <T> Result<T> ok(String msg, T data) {
        return new Result<>(0, msg, data);
    }

    public static <T> Result<T> ok(String msg, T data, Integer count) {
        return new Result<>(0, msg, data, count);
    }


    public static <T> Result<T> ok(String msg) {
        return new Result<>(0, msg);
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<>(1, msg);
    }


}
