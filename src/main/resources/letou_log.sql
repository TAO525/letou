/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-01-07 21:41:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for letou_log
-- ----------------------------
DROP TABLE IF EXISTS `letou_log`;
CREATE TABLE `letou_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `c1` char(2) DEFAULT NULL,
  `c2` char(2) DEFAULT NULL,
  `c3` char(2) DEFAULT NULL,
  `c4` char(2) DEFAULT NULL,
  `c5` char(2) DEFAULT NULL,
  `c6` char(2) DEFAULT NULL,
  `s1` char(2) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(400) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2205 DEFAULT CHARSET=utf8 COMMENT='双色球碰撞组数据';
