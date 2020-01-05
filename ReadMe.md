

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