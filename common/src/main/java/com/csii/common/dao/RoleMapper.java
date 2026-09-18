package com.csii.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csii.common.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户 Id 查询用户角色
     */
    List<Role> selectRoleByUserId(@Param("userId") Long userId);
}
