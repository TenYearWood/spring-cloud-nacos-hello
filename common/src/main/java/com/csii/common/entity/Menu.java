package com.csii.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Menu {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String url;

    private Long parentId;

    private String permission;
}
