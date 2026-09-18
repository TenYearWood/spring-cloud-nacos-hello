package com.csii.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@TableName("t_user")
@Getter
@Setter
public class UserEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;
    private String password;
    private Integer status;
    private String remark;
    private Date createTime;
    private Date updateTime;
}
