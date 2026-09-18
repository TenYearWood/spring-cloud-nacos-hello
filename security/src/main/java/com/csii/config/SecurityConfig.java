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

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单提交
        http.formLogin()
                // 对应表单中的 input标签中name属性，必须是username和password（默认），如果想换成别的在这里设置
                .usernameParameter("username")
                .passwordParameter("password")

                // 自定义登录页面路径
                .loginPage("/login.html")
                /**
                 * 设置登录接口地址，这个接口不是真实存在的，还是用的security给我们提供的，之所以要有这个配置，是login.html中form表单提交的登录地址是这个
                 * 当发现是toLogin请求时，去执行UserDetailsServerImpl，必须和html表单的请求路径一样
                 */
                .loginProcessingUrl("/toLogin")
                //登录成功之后跳转到这个请求上
                .defaultSuccessUrl("/toMain")
                // 登录成功后跳转到指定controller路径,必须是post请求
                .successForwardUrl("/toMain")
                //.successHandler(new MyAuthenticationSuccessHandler("http://www.baidu.com"))
                // 登录失败后跳转到指定controller路径,必须是post请求
                //.failureForwardUrl("/toError");
                .failureHandler(new MyAuthenticationFailureHandler());

        // 授权认证
        http.authorizeRequests()
                // 放开登录页面（这个一定要写在前面，不然报错）
                .antMatchers("/login.html").permitAll()
                .antMatchers("/error.html").permitAll()
                // 所有的静态资源允许匿名访问
                .antMatchers(
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/fonts/**",
                        "/favicon.ico").anonymous()
                .antMatchers(
                        "/**/*.js",
                        "/profile/**"
                ).permitAll()
                // 需要用户带有管理员角色才可以访问/findAll接口
                .antMatchers("/findAll").hasRole("管理员")
                .antMatchers("/find").hasRole("管理员")
                /*
                 * 要用户具备menu:user这个接口的许可，才可以访问
                 * 虽然我限制了find接口必须具备管理员权限才能访问，但是我还设置了只要具有menu:user菜单许可即可访问。
                 * 也就是两个判断条件我满足了一个就能访问。
                 */
                .antMatchers("/find").hasAuthority("menu:user")
                // 所有请求都需要登录认证
                .anyRequest().authenticated();

        // 配置没有权限访问错误处理器。(权限不足等)
        http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);
        // 退出，这里的/logout请求是和前端的接口约定，是security给我们提供的，退出成功后跳转到登录页
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login.html").permitAll();

        // 关闭csrf保护，类似防火墙
        http.csrf().disable();

    }
}