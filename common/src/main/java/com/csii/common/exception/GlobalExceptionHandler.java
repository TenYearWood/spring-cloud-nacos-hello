package com.csii.common.exception;

import com.csii.common.enums.ECodeEnum;
import com.csii.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @Description: 全局异常处理
 * 异常处理器按异常类继承层级的精确度匹配，越具体的越优先。如果两个方法的异常类型有父子关系，Spring 会自动选更精确那个；
 * 但如果两个 handler 的异常类型没有继承关系、且都能匹配到同一个异常（比如 RuntimeException 和 IllegalStateException 同时存在，抛 IllegalStateException），会优先精确的那个。
 * 建议：每个异常类型只写一个 handler，兜底的 Exception 只放一个。
 * @Author: chengyu
 * @Date: 2026/11/23 11:03
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务自定义异常
     */
    @ExceptionHandler(BizException.class)
    public Result<Void> handleBizException(BizException e) {
        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 参数类型不匹配，如 id 传了 "abc
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<Void> handleMismatch(MethodArgumentTypeMismatchException e) {
        return Result.fail("400", "参数 " + e.getName() + " 类型错误");
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> handleAll(Exception e) {
        log.error(e.getMessage(), e);
        return Result.fail(ECodeEnum.SERVER_ERROR.getCode(), ECodeEnum.SERVER_ERROR.getMessage());
    }
}