package com.csii.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csii.common.entity.Menu;
import com.csii.common.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户 Id 查询菜单
     */
    List<Menu> selectMenuByUserId(@Param("userId") Long userId);
}
