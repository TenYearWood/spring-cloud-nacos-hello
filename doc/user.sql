CREATE TABLE `t_user` (
`id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
`username` varchar(30) NOT NULL COMMENT '用户名',
`password` varchar(100) NOT NULL COMMENT '密码',
`status` TINYINT NOT NULL DEFAULT 0 COMMENT '用户状态标识：0-正常、1-已注销、-1-锁定',
`remark` varchar(255) DEFAULT NULL COMMENT '备注',
`create_time` datetime NOT NULL COMMENT '创建时间',
`update_time` datetime DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表';

--角色表
create table t_role(
id bigint primary key auto_increment,
name varchar(20)
);

--用户角色表
create table t_role_user(
  uid bigint not null,
  rid bigint not null,
  PRIMARY KEY (uid, rid)
);

--菜单表
create table t_menu(
id bigint primary key auto_increment,
name varchar(20),
url varchar(100),
parent_id bigint,
permission varchar(20)
);

--角色菜单表
create table t_role_menu(
  rid bigint,
  mid bigint,
  PRIMARY KEY (rid, mid)
);