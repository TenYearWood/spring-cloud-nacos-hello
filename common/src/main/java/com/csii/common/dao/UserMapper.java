package com.csii.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csii.common.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 根据姓名模糊查询
     */
    List<UserEntity> selectByNameLike(@Param("name") String name);
}
