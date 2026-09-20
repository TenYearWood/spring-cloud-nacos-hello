package com.csii.filter;

import cn.hutool.core.util.StrUtil;
import com.csii.common.util.ResponseUtil;
import com.csii.common.vo.Result;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Slf4j
public class JwtFilter extends BasicAuthenticationFilter {

    public JwtFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("authorization");
        if (StrUtil.isBlank(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        try {
            // 解析token
            Claims claims = Jwts.parser()
                    .setSigningKey("csii@123")
                    .parseClaimsJws(header.replace("Bearer ", ""))
                    .getBody();
            // 获取用户名
            String username = claims.getSubject();
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
            User principal = new User(username, "", authorities);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(principal, null, authorities));
        } catch (ExpiredJwtException e) {
            ResponseUtil.out(response, Result.failed(401, "登录已失效，请重新登录"));
            return;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ResponseUtil.out(response, Result.failed(500, "解析token错误"));
            return;
        }

        chain.doFilter(request, response);
    }
}
