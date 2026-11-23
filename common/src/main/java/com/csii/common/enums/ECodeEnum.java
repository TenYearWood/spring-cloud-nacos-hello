package com.csii.common.enums;

import lombok.Getter;

/**
 * @Description: 错误枚举类
 * @Author: chengyu
 * @Date: 2026/11/23 11:10
 */
@Getter
public enum ECodeEnum {
    SUCCESS("200", "操作成功"),
    BUSINESS_FAILURE("400", "业务失败"),
    FORBIDDEN("401", "没有访问权限"),
    SERVER_ERROR("500", "服务内部错误"),
    PARAM_ERROR("501", "请求参数异常"),
    FALLBACK("502", "服务器暂忙，请稍后重试!");

    private final String code;

    private final String message;

    ECodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}