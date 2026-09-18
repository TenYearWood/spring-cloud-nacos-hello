package com.csii.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义403处理Handler
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(
                "<html>" +
                        "<body>" +
                        "<div style='width:800px;text-align:center;margin:auto;font-size:24px'>" +
                        "权限不足，请联系管理员" +
                        "</div>" +
                        "</body>" +
                        "</html>"

        );
        response.getWriter().flush();//刷新缓冲区
    }
}