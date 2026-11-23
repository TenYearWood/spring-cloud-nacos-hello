package com.csii.common.vo;

import com.csii.common.enums.ECodeEnum;
import lombok.Data;

@Data
public class Result<T> {

    private String code;
    private String message;
    private T data;

    public Result(T data) {
        this.code = ECodeEnum.SUCCESS.getCode();
        this.message = ECodeEnum.SUCCESS.getMessage();
        this.data = data;
    }

    public Result(String code, String msg, T data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    public static <T> Result<T> ok() {
        return Result.ok(null);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<T>(data);
    }

    public static <T> Result<T> fail(String code, String message) {
        return fail(code, message, null);
    }

    public static <T> Result<T> fail(String code, String message, T data) {
        Result<T> result = new Result<>(data);
        result.code = code;
        result.message = message;
        return result;
    }
}
