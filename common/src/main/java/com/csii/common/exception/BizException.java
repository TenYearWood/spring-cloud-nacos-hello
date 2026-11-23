package com.csii.common.exception;

import lombok.Data;

/**
 * @Description:
 * @Author: chengyu
 * @Date: 2026/11/23 11:04
 */
@Data
public class BizException extends RuntimeException {

    private String code;

    private String message;

    public BizException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}