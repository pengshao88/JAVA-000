/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : geek_time

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2020-11-24 23:39:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(20) NOT NULL COMMENT '商品Id',
  `goods_name` varchar(255) NOT NULL COMMENT '商品名称',
  `description` varchar(1024) DEFAULT NULL COMMENT '商品描述',
  `images` varchar(1024) DEFAULT NULL COMMENT '商品图片',
  `price` bigint(11) DEFAULT '0' COMMENT '商品价格',
  `decimal_places` int(11) DEFAULT '0' COMMENT '小数位 -100 即price除以100',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `goodsId` (`goods_id`),
  KEY `goodsName` (`goods_name`(191))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for goods_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `goods_snapshot`;
CREATE TABLE `goods_snapshot` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(20) NOT NULL COMMENT '商品id',
  `price` bigint(11) NOT NULL COMMENT '商品价格',
  `decimal_places` int(11) NOT NULL COMMENT '小数位 -100 即price除以100',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `goodsId` (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of goods_snapshot
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单唯一id',
  `status` smallint(2) NOT NULL COMMENT '订单状态 0 未支付 1 已支付 3 取消订单 4 删除订单',
  `price` bigint(20) DEFAULT '0' COMMENT '价格',
  `decimal_places` int(11) DEFAULT '0' COMMENT '小数位 -100 即price除以100',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL COMMENT '用户唯一id',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `phone` int(11) NOT NULL COMMENT '手机号',
  `address` varchar(512) DEFAULT NULL COMMENT '用户地址',
  `age` smallint(4) DEFAULT '0' COMMENT '年龄',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别 0 女 1 男',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId` (`user_id`),
  KEY `phone` (`phone`),
  KEY `userName` (`user_name`(191)) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
