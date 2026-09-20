package com.csii.handler;

import cn.hutool.json.JSONUtil;
import com.csii.common.vo.Result;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;


/**
 * 自定义处理器（适合前后端分离，或者跳转其他页面）
 */
@Slf4j
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        Collection<? extends GrantedAuthority> authorities = ((UserDetails) authentication.getPrincipal()).getAuthorities();
        StringBuffer sb = new StringBuffer();
        for  (GrantedAuthority grantedAuthority : authorities) {
            sb.append(grantedAuthority.getAuthority()).append(",");
        }

        String jwt = Jwts.builder()
                .claim("authorities", sb.toString())
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, "csii@123")
                .compact();

        Result<String> result = Result.ok(jwt);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(JSONUtil.toJsonStr(result));
        out.flush();
        out.close();
    }


}
