CREATE DATABASE IF NOT EXISTS geek_mall_1 DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;
use geek_mall_1;

CREATE TABLE `t_order_0` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单唯一id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `status` smallint(2) NOT NULL COMMENT '订单状态 0 未支付 1 已支付 3 取消订单 4 删除订单',
  `price` int(20) DEFAULT '0' COMMENT '价格',
  `decimal_places` int(11) DEFAULT '0' COMMENT '小数位 -100 即price除以100',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_order_1` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单唯一id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `status` smallint(2) NOT NULL COMMENT '订单状态 0 未支付 1 已支付 3 取消订单 4 删除订单',
  `price` int(20) DEFAULT '0' COMMENT '价格',
  `decimal_places` int(11) DEFAULT '0' COMMENT '小数位 -100 即price除以100',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_order_2` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单唯一id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `status` smallint(2) NOT NULL COMMENT '订单状态 0 未支付 1 已支付 3 取消订单 4 删除订单',
  `price` int(20) DEFAULT '0' COMMENT '价格',
  `decimal_places` int(11) DEFAULT '0' COMMENT '小数位 -100 即price除以100',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_order_3` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单唯一id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `status` smallint(2) NOT NULL COMMENT '订单状态 0 未支付 1 已支付 3 取消订单 4 删除订单',
  `price` int(20) DEFAULT '0' COMMENT '价格',
  `decimal_places` int(11) DEFAULT '0' COMMENT '小数位 -100 即price除以100',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_order_4` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单唯一id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `status` smallint(2) NOT NULL COMMENT '订单状态 0 未支付 1 已支付 3 取消订单 4 删除订单',
  `price` int(20) DEFAULT '0' COMMENT '价格',
  `decimal_places` int(11) DEFAULT '0' COMMENT '小数位 -100 即price除以100',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_order_5` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单唯一id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `status` smallint(2) NOT NULL COMMENT '订单状态 0 未支付 1 已支付 3 取消订单 4 删除订单',
  `price` int(20) DEFAULT '0' COMMENT '价格',
  `decimal_places` int(11) DEFAULT '0' COMMENT '小数位 -100 即price除以100',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_order_6` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单唯一id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `status` smallint(2) NOT NULL COMMENT '订单状态 0 未支付 1 已支付 3 取消订单 4 删除订单',
  `price` int(20) DEFAULT '0' COMMENT '价格',
  `decimal_places` int(11) DEFAULT '0' COMMENT '小数位 -100 即price除以100',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_order_7` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单唯一id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `status` smallint(2) NOT NULL COMMENT '订单状态 0 未支付 1 已支付 3 取消订单 4 删除订单',
  `price` int(20) DEFAULT '0' COMMENT '价格',
  `decimal_places` int(11) DEFAULT '0' COMMENT '小数位 -100 即price除以100',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_order_8` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单唯一id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `status` smallint(2) NOT NULL COMMENT '订单状态 0 未支付 1 已支付 3 取消订单 4 删除订单',
  `price` int(20) DEFAULT '0' COMMENT '价格',
  `decimal_places` int(11) DEFAULT '0' COMMENT '小数位 -100 即price除以100',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_order_9` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单唯一id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `status` smallint(2) NOT NULL COMMENT '订单状态 0 未支付 1 已支付 3 取消订单 4 删除订单',
  `price` int(20) DEFAULT '0' COMMENT '价格',
  `decimal_places` int(11) DEFAULT '0' COMMENT '小数位 -100 即price除以100',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_order_10` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单唯一id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `status` smallint(2) NOT NULL COMMENT '订单状态 0 未支付 1 已支付 3 取消订单 4 删除订单',
  `price` int(20) DEFAULT '0' COMMENT '价格',
  `decimal_places` int(11) DEFAULT '0' COMMENT '小数位 -100 即price除以100',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_order_11` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单唯一id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `status` smallint(2) NOT NULL COMMENT '订单状态 0 未支付 1 已支付 3 取消订单 4 删除订单',
  `price` int(20) DEFAULT '0' COMMENT '价格',
  `decimal_places` int(11) DEFAULT '0' COMMENT '小数位 -100 即price除以100',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_order_12` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单唯一id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `status` smallint(2) NOT NULL COMMENT '订单状态 0 未支付 1 已支付 3 取消订单 4 删除订单',
  `price` int(20) DEFAULT '0' COMMENT '价格',
  `decimal_places` int(11) DEFAULT '0' COMMENT '小数位 -100 即price除以100',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_order_13` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单唯一id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `status` smallint(2) NOT NULL COMMENT '订单状态 0 未支付 1 已支付 3 取消订单 4 删除订单',
  `price` int(20) DEFAULT '0' COMMENT '价格',
  `decimal_places` int(11) DEFAULT '0' COMMENT '小数位 -100 即price除以100',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_order_14` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单唯一id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `status` smallint(2) NOT NULL COMMENT '订单状态 0 未支付 1 已支付 3 取消订单 4 删除订单',
  `price` int(20) DEFAULT '0' COMMENT '价格',
  `decimal_places` int(11) DEFAULT '0' COMMENT '小数位 -100 即price除以100',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_order_15` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单唯一id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `status` smallint(2) NOT NULL COMMENT '订单状态 0 未支付 1 已支付 3 取消订单 4 删除订单',
  `price` int(20) DEFAULT '0' COMMENT '价格',
  `decimal_places` int(11) DEFAULT '0' COMMENT '小数位 -100 即price除以100',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;