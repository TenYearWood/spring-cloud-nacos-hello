package com.csii.config;

import com.csii.handler.MyAccessDeniedHandler;
import com.csii.handler.MyAuthenticationFailureHandler;
import com.csii.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单提交
        http.formLogin()
                // 对应表单中的 input标签中name属性，必须是username和password（默认），如果想换成别的在这里设置
                .usernameParameter("username")
                .passwordParameter("password")

                // 自定义登录页面路径
                .loginPage("/login.html")
                // 当发现是login请求时，去执行UserDetailsServerImpl，必须和html表单的请求路径一样
                .loginProcessingUrl("/login")
                // 登录成功后跳转到指定controller路径,必须是post请求
                //.successForwardUrl("/toMain")
                .successHandler(new MyAuthenticationSuccessHandler("http://www.baidu.com"))
                // 登录失败后跳转到指定controller路径,必须是post请求
                //.failureForwardUrl("/toError");
                .failureHandler(new MyAuthenticationFailureHandler());

        // 授权认证
        http.authorizeRequests()
                // 放开登录页面（这个一定要写在前面，不然报错）
                .antMatchers("/login.html").permitAll()
                .antMatchers("/error.html").permitAll()
                .antMatchers("/css/**", "/js/**", "/images/**").permitAll() // 放行静态资源
                // 拥有admin权限才能访问admin.html
                .antMatchers("/admin.html").hasAnyAuthority("admin")
                // 所有请求都需要认证
                .anyRequest().authenticated();

        // 关闭csrf保护，类似防火墙
        http.csrf().disable();

    }
}