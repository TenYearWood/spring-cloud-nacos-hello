package com.csii.config;

import com.csii.common.dao.MenuMapper;
import com.csii.common.dao.RoleMapper;
import com.csii.common.entity.Menu;
import com.csii.common.entity.Role;
import com.csii.common.entity.UserEntity;
import com.csii.common.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@Slf4j
public  class UserDetailsServerImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("执行了UserDetailsServerImpl中的loadUserByUsername方法");

        UserEntity userEntity = userService.selectByName(username);

        // 查询数据库判断用户是否存在，如果不存在，抛出异常
        if (Objects.isNull(userEntity)) {
            log.error("用户不存在");
            throw new UsernameNotFoundException("用户不存在");
        }

        // 获取用户角色、菜单列表
        List<Role> roles = roleMapper.selectRoleByUserId(userEntity.getId());
        List<Menu> menus = menuMapper.selectMenuByUserId(userEntity.getId());
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        // 处理角色
        for (Role role:roles){
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getName());
            grantedAuthorityList.add(simpleGrantedAuthority);
        }
        // 处理权限
        for (Menu menu:menus){
            grantedAuthorityList.add(new SimpleGrantedAuthority(menu.getPermission()));
        }

        // 把查询出来的密码（注册时已经加密过）进行解析，或者直接把密码放到构造方法中
        String password = passwordEncoder.encode(userEntity.getPassword());

        return new User(username, password, grantedAuthorityList);
    }
}