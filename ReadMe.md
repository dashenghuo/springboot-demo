

CREATE TABLE `t_zb_op_log` (
  `id` bigint(50) unsigned NOT NULL AUTO_INCREMENT COMMENT '操作日志id',
  `module` varchar(100) NOT NULL DEFAULT '' COMMENT '操作模块',
  `content` varchar(300) NOT NULL DEFAULT '' COMMENT '操作内容',
  `ip` varchar(100) NOT NULL DEFAULT '' COMMENT '操作iP地址',
  `result` varchar(100) NOT NULL DEFAULT '' COMMENT '操作结果',
  `log_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `creator` varchar(100) NOT NULL DEFAULT '' COMMENT '创建者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10156 DEFAULT CHARSET=utf8 COMMENT='操作日志表';

CREATE TABLE `t_file_modified_time` (
  `id` bigint(50) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `file_path` varchar(500) NOT NULL DEFAULT '' COMMENT '文件目录',
  `file_name` varchar(300) NOT NULL DEFAULT '' COMMENT '文件名字',
  `file_modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '文件最近修改时间',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '该记录创建时间',
  `creator` varchar(100) NOT NULL DEFAULT '' COMMENT '该记录创建者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10156 DEFAULT CHARSET=utf8 COMMENT='文件最近修改时间表';


ctl:  入参  filename   oplogId
      出参   resultmap   key-value  file  文件记录
                        key-value  oplog    日志记录
                        
功能：根据账户密码 获取 用户的信息，并将其权限信息（/api/...，就是用户的界面请求uri）存入redis，
     后期可以针对界面的uri请求做权限控制

步骤：
1 建表： 权限表、角色表、角色权限关联表、用户表，一个用户有一种角色、角色包含了多种权限
权限表： id、uri权限（/api/...，就是用户的界面请求）   
角色表：id、角色名字    
角色权限关联表：id、角色id、权限id
用户表：id、用户名、密码、角色id等      
   
2 Controller、Service、Mybatis、Redis等
功能可以逐步实现： 
a）根据用户账户密码查询该用户信息（用户表）   
b）根据用户角色id查询角色及其对应的权限
c）将用户的权限存入redis（set类型，key：用户id，value：权限集合Set） 
d）返回给前端（用户id、用户名、用户角色名）                
  
CREATE TABLE `t_user` (
  `id` bigint(50) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `username` varchar(500) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(300) NOT NULL DEFAULT '' COMMENT '密码',
  `role_id` bigint(50) unsigned NOT NULL DEFAULT '0' COMMENT '角色id',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '该记录创建时间',
  `creator` varchar(100) NOT NULL DEFAULT '' COMMENT '该记录创建者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

CREATE TABLE `t_role` (
  `id` bigint(50) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `rolename` varchar(500) NOT NULL DEFAULT '' COMMENT '角色名字',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '该记录创建时间',
  `creator` varchar(100) NOT NULL DEFAULT '' COMMENT '该记录创建者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8 COMMENT='角色表';
  
CREATE TABLE `t_auth` (
  `id` bigint(50) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `uri_auth` varchar(500) NOT NULL DEFAULT '' COMMENT 'uri权限',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '该记录创建时间',
  `creator` varchar(100) NOT NULL DEFAULT '' COMMENT '该记录创建者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8 COMMENT='权限表';

CREATE TABLE `t_role_auth` (
  `id` bigint(50) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `role_id` bigint(50) unsigned NOT NULL DEFAULT '0' COMMENT '角色id',
  `auth_id` bigint(50) unsigned NOT NULL DEFAULT '0' COMMENT '权限id',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '该记录创建时间',
  `creator` varchar(100) NOT NULL DEFAULT '' COMMENT '该记录创建者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10158 DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';







                    