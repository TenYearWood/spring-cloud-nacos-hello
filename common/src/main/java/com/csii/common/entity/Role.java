package com.csii.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Role {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;
}
