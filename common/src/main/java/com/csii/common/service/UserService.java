package com.csii.common.service;

import com.csii.common.entity.UserEntity;

public interface UserService {

    UserEntity selectByName(String username);
}
