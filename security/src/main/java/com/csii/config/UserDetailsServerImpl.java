package com.csii.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public  class UserDetailsServerImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("执行了UserDetailsServerImpl中的loadUserByUsername方法");

        // 1.查询数据库判断用户是否存在，如果不存在，抛出异常
        if (!"admin".equals(username)){
            log.error("用户名不存在");
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 2.把查询出来的密码（注册时已经加密过）进行解析，或者直接把密码放到构造方法中
        String password = passwordEncoder.encode("123");

        // admin表示拥有管理员权限，normal表示拥有普通权限
        return new User(username, password,
                AuthorityUtils.commaSeparatedStringToAuthorityList("" + "admin,normal"));
    }
}