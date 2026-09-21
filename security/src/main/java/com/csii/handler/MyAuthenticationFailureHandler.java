package com.csii.handler;

import com.csii.common.util.ResponseUtil;
import com.csii.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败的Handler
 */
@Slf4j
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("onAuthenticationFailure", exception);
        Result<String> result = Result.failed(HttpServletResponse.SC_FORBIDDEN, exception.getMessage());
        if (exception instanceof LockedException) {
            result.setMessage("账户被锁定，请联系管理员!");
        } else if (exception instanceof CredentialsExpiredException) {
            result.setMessage("密码过期，请联系管理员!");
        } else if (exception instanceof AccountExpiredException) {
            result.setMessage("账户过期，请联系管理员!");
        } else if (exception instanceof DisabledException) {
            result.setMessage("账户被禁用，请联系管理员!");
        } else if (exception instanceof BadCredentialsException) {
            result.setMessage("用户名或者密码输入错误，请重新输入!");
        }
        ResponseUtil.write(response, result);
    }
}
