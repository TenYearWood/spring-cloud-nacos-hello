package com.csii.common.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.csii.common.constant.Constants;
import com.csii.common.dao.UserMapper;
import com.csii.common.entity.UserEntity;
import com.csii.common.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity selectByName(String username) {
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getUsername, username);
        queryWrapper.eq(UserEntity::getStatus, Constants.CODE_ZERO);
        List<UserEntity> userEntities = userMapper.selectList(queryWrapper);
        return CollUtil.isNotEmpty(userEntities) ? userEntities.get(0) : null;
    }
}
