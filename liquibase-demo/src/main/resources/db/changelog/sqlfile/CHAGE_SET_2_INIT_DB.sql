-- 创建用户表
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '姓名',
  `address` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 插入一条数据
INSERT INTO `person`(name, address, create_time) VALUES('eden', '上海', now());