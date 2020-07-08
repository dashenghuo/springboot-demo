

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