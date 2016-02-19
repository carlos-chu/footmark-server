/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : 127.0.0.1:3306
Source Database       : footmark

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2015-08-25 18:11:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for accepted_footmark
-- ----------------------------
DROP TABLE IF EXISTS `accepted_footmark`;
CREATE TABLE `accepted_footmark` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL COMMENT '接受者user_id',
  `footmark_id` bigint(20) NOT NULL COMMENT '接受的footmark的id',
  `accept_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '接受的类型，0：普通接受，1：漫游接受，2：历史地点主动推送，3：发布者定向推送',
  `read_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '阅读状态，0：未读，1：已读，2：忽略',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0：正常，1：接受用户删除，2：发布者删除，3：实效期到期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '评论者',
  `footmark_id` bigint(20) NOT NULL COMMENT 'footmark的id',
  `content` varchar(500) DEFAULT NULL COMMENT '评论的内容',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0 : 正常，1 : 举报，2 : 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for coordinate
-- ----------------------------
DROP TABLE IF EXISTS `coordinate`;
CREATE TABLE `coordinate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `longitude` varchar(15) NOT NULL COMMENT '经度',
  `latitude` varchar(15) NOT NULL COMMENT '纬度',
  `height` varchar(10) DEFAULT NULL COMMENT '高度',
  `location` varchar(50) DEFAULT NULL COMMENT '位置描述',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(2) DEFAULT '0' COMMENT '0 : 正常，1 : 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for multi_media
-- ----------------------------
DROP TABLE IF EXISTS `multi_media`;
CREATE TABLE `multi_media` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` tinyint(2) DEFAULT NULL COMMENT '1 : 语音，2 : 图片，3 : 视频',
  `url` varchar(100) DEFAULT NULL COMMENT '多媒体的本应用地址',
  `outside_url` varchar(100) DEFAULT NULL COMMENT '外部地址',
  `ext_name` varchar(10) DEFAULT NULL COMMENT '扩展名',
  `file_name` varchar(50) DEFAULT NULL COMMENT '文件名',
  `bucket` varchar(36) DEFAULT NULL COMMENT '存储桶的名字,针对阿里云',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0 : 正常，1 : 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for source_multi
-- ----------------------------
DROP TABLE IF EXISTS `source_multi`;
CREATE TABLE `source_multi` (
  `id` bigint(20) NOT NULL,
  `source_id` bigint(20) DEFAULT NULL,
  `multi_id` bigint(20) DEFAULT NULL,
  `type` tinyint(2) NOT NULL COMMENT '1 : 足迹，2 : 评论',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0：正常，1：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_footmark
-- ----------------------------
DROP TABLE IF EXISTS `user_footmark`;
CREATE TABLE `user_footmark` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `user_id` bigint(20) NOT NULL COMMENT '用户的id',
  `coordinate_id` bigint(20) NOT NULL COMMENT '坐标的id',
  `accepter_category` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0 : all，1 : famale，2 : male，3 : 特定的人',
  `effective_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '有效时长，单位为分钟，0：永久，any : 分钟',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0 : 正常，1 : 用户删除，2 : 举报，3：实效到期，4 : 强制删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_friend
-- ----------------------------
DROP TABLE IF EXISTS `user_friend`;
CREATE TABLE `user_friend` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `friend_id` bigint(20) NOT NULL,
  `accept_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'friend接受状态，0：未接受，1：已接受，2：拒绝',
  `relation_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '关系类型，0：单方面，1：互为好友',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0 : 正常，1 : 解除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别，1：男，2：女',
  `tel_num` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(25) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `head_url` varchar(100) DEFAULT NULL,
  `level` tinyint(2) NOT NULL COMMENT '级别',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0 : 正常，1 : 冻结，2 : 注销',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_setting
-- ----------------------------
DROP TABLE IF EXISTS `user_setting`;
CREATE TABLE `user_setting` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `key` tinyint(2) NOT NULL,
  `value` tinyint(2) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态，0：正常，2：失效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
