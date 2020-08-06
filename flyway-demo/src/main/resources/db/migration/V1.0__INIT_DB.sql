-- 创建用户表
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 插入一条数据
INSERT INTO `user`(username, password, create_time) VALUES('eden', '123456', now());