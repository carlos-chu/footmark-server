/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : 127.0.0.1:3306
Source Database       : footmark

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2015-12-11 19:26:24
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
  `status` tinyint(2) NOT NULL COMMENT '0：正常，1：发布者删除，2：实效期到期，3：接受用户删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accepted_footmark
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '评论者',
  `source_id` bigint(20) NOT NULL COMMENT '评论源的id',
  `source_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '评论源的类型，0：脚印，1：评论',
  `content` varchar(500) DEFAULT NULL COMMENT '评论的内容',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0 : 正常，1 : 删除，2 : 举报',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for coordinate
-- ----------------------------
DROP TABLE IF EXISTS `coordinate`;
CREATE TABLE `coordinate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `longitude` varchar(15) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(15) DEFAULT NULL COMMENT '纬度',
  `height` double DEFAULT '1.5' COMMENT '高度',
  `location` varchar(100) DEFAULT NULL COMMENT '位置描述',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0 : 正常，1 : 删除',
  PRIMARY KEY (`id`),
  KEY `idx_location` (`location`),
  KEY `idx_longitude` (`longitude`),
  KEY `idx_latitude` (`latitude`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coordinate
-- ----------------------------
INSERT INTO `coordinate` VALUES ('1', '116.454059', '39.978367', '1.5', '太阳宫', null, '2015-08-31 11:32:45', '0');
INSERT INTO `coordinate` VALUES ('2', '116.44561', '39.982753', '1.5', '芍药居', null, '2015-08-31 11:33:33', '0');
INSERT INTO `coordinate` VALUES ('3', '116.46382', '39.967355', '1.5', '三元桥', null, '2015-08-31 11:40:07', '0');
INSERT INTO `coordinate` VALUES ('5', '0.0', '0.0', '0', null, '2015-12-10 18:52:05', null, '0');
INSERT INTO `coordinate` VALUES ('6', '116.483731', '39.918967', '0', null, '2015-12-11 10:17:02', null, '0');
INSERT INTO `coordinate` VALUES ('8', '116.483748', '39.918838', '0', '在阳光100国际公寓附近', '2015-12-11 15:12:14', null, '0');
INSERT INTO `coordinate` VALUES ('9', '116.483835', '39.919063', '0', '在温特莱中心-A座附近', '2015-12-11 16:40:16', null, '0');
INSERT INTO `coordinate` VALUES ('10', '116.483835', '39.919063', '0', '在温特莱中心-A座附近', '2015-12-11 16:41:18', null, '0');
INSERT INTO `coordinate` VALUES ('11', '116.483835', '39.919063', '0', '在温特莱中心-A座附近', '2015-12-11 16:44:09', null, '0');
INSERT INTO `coordinate` VALUES ('12', '116.483778', '39.921309', '0', null, '2015-12-11 16:46:26', null, '0');
INSERT INTO `coordinate` VALUES ('13', '116.484424', '39.918238', '0', null, '2015-12-11 16:46:38', null, '0');
INSERT INTO `coordinate` VALUES ('14', '116.483778', '39.921309', '0', null, '2015-12-11 16:48:32', null, '0');
INSERT INTO `coordinate` VALUES ('15', '116.483738', '39.91906', '0', '在温特莱中心-A座附近', '2015-12-11 16:50:39', null, '0');
INSERT INTO `coordinate` VALUES ('16', '116.483738', '39.91906', '0', '在温特莱中心-A座附近', '2015-12-11 16:52:10', null, '0');
INSERT INTO `coordinate` VALUES ('17', '116.483738', '39.91906', '0', '在温特莱中心-A座附近', '2015-12-11 16:58:15', null, '0');
INSERT INTO `coordinate` VALUES ('18', '116.483738', '39.91906', '0', '在温特莱中心-A座附近', '2015-12-11 17:39:03', null, '0');
INSERT INTO `coordinate` VALUES ('19', '116.483763', '39.9191', '0', '在温特莱中心-A座附近', '2015-12-11 17:39:09', null, '0');

-- ----------------------------
-- Table structure for counter
-- ----------------------------
DROP TABLE IF EXISTS `counter`;
CREATE TABLE `counter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '主体的用户id',
  `source_id` bigint(20) NOT NULL COMMENT '计数服务源id',
  `source_type` tinyint(2) NOT NULL COMMENT '计数服务源 1：脚印，2：评论，3：用户主页',
  `counter_type` tinyint(2) NOT NULL COMMENT '计数类型 1：点赞， 2：评论',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态，0：正常，1：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of counter
-- ----------------------------

-- ----------------------------
-- Table structure for footmark
-- ----------------------------
DROP TABLE IF EXISTS `footmark`;
CREATE TABLE `footmark` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `user_id` bigint(20) NOT NULL COMMENT '用户的id',
  `coordinate_id` bigint(20) NOT NULL COMMENT '坐标的id',
  `accepter_category` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0 : all，1 : famale，2 : male',
  `gender` tinyint(2) DEFAULT NULL COMMENT '冗余一个发布者的性别，方便过滤，1：男，2：女',
  `effective_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '有效时长，单位为分钟，0：永久，any : 分钟',
  `type` tinyint(2) DEFAULT '0' COMMENT '发布脚印的类型，0：用户发布，1：系统发布',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0 : 正常，1 : 用户删除，2：实效到期，3 : 强制删除，4 : 举报待审核，5：举报通过',
  PRIMARY KEY (`id`),
  KEY `idx_userid` (`user_id`),
  KEY `idx_coordinateid` (`coordinate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of footmark
-- ----------------------------
INSERT INTO `footmark` VALUES ('2', null, '您logo给啊', '7', '0', '1', null, '0', '0', '2015-12-10 18:52:05', null, '0');
INSERT INTO `footmark` VALUES ('3', null, '额你咯破婆婆婆婆', '7', '0', '1', null, '0', '0', '2015-12-11 10:17:01', null, '0');
INSERT INTO `footmark` VALUES ('4', null, '明明明明', '7', '8', '1', null, '0', '0', '2015-12-11 15:12:11', null, '0');
INSERT INTO `footmark` VALUES ('5', null, 'face发了咳咳咳', '7', '9', '1', null, '0', '0', '2015-12-11 16:40:14', null, '0');
INSERT INTO `footmark` VALUES ('6', null, '龙茗路', '7', '10', '1', null, '0', '0', '2015-12-11 16:41:11', null, '0');
INSERT INTO `footmark` VALUES ('7', null, '哦里咯JOJO咯JOJO', '7', '11', '1', null, '0', '0', '2015-12-11 16:44:09', null, '0');
INSERT INTO `footmark` VALUES ('8', null, '怕来PK去老K了', '7', '12', '1', null, '0', '0', '2015-12-11 16:46:26', null, '0');
INSERT INTO `footmark` VALUES ('9', null, '空军建军节', '7', '13', '1', null, '0', '0', '2015-12-11 16:46:38', null, '0');
INSERT INTO `footmark` VALUES ('10', null, 'lol魔金', '7', '14', '1', null, '0', '0', '2015-12-11 16:48:32', null, '0');
INSERT INTO `footmark` VALUES ('11', null, '管理会计好', '7', '15', '1', null, '0', '0', '2015-12-11 16:50:39', null, '0');
INSERT INTO `footmark` VALUES ('12', null, '啊啊啊吧', '7', '16', '1', null, '0', '0', '2015-12-11 16:52:10', null, '0');
INSERT INTO `footmark` VALUES ('13', null, '哈哈哈1啊', '7', '17', '1', null, '0', '0', '2015-12-11 16:58:15', null, '0');
INSERT INTO `footmark` VALUES ('14', null, '五天哦JOJO', '7', '18', '1', null, '0', '0', '2015-12-11 17:39:03', null, '0');
INSERT INTO `footmark` VALUES ('15', null, '哦思敏咯', '7', '19', '1', null, '0', '0', '2015-12-11 17:39:09', null, '0');

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
-- Records of multi_media
-- ----------------------------

-- ----------------------------
-- Table structure for source_multi
-- ----------------------------
DROP TABLE IF EXISTS `source_multi`;
CREATE TABLE `source_multi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `source_id` bigint(20) NOT NULL COMMENT '多媒体修饰的主体的id,如footmarkId、commentId',
  `multi_id` bigint(20) NOT NULL COMMENT '多媒体实体id',
  `type` tinyint(2) NOT NULL COMMENT '1 : 足迹，2 : 评论',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0：正常，1：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of source_multi
-- ----------------------------

-- ----------------------------
-- Table structure for user_friend
-- ----------------------------
DROP TABLE IF EXISTS `user_friend`;
CREATE TABLE `user_friend` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `friend_id` bigint(20) NOT NULL,
  `accept_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'friend接受状态，0：未接受，1：已接受，2：拒绝',
  `relation_type` tinyint(2) NOT NULL COMMENT '关系类型，1：主动加好友，2：被加好友，3：加入黑名单，4：被设置为黑名单',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0 : 正常，1 : 解除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_friend
-- ----------------------------

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_no` varchar(20) DEFAULT NULL COMMENT '脚印号-账号',
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_telnum` (`tel_num`) USING BTREE,
  UNIQUE KEY `uniq_account_no` (`account_no`),
  KEY `idx_password` (`password`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('7', null, '小强', null, '18301103258', null, '2614834a9e21b0302d01024bc70fc0c6', 'test', '1', '2015-11-16 15:20:05', null, '0');

-- ----------------------------
-- Table structure for user_setting
-- ----------------------------
DROP TABLE IF EXISTS `user_setting`;
CREATE TABLE `user_setting` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `keyword` tinyint(2) NOT NULL COMMENT '设置的key',
  `value` int(11) NOT NULL COMMENT '设置的值',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态，0：正常，2：失效',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_userid_key` (`user_id`,`keyword`),
  KEY `idx_userid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_setting
-- ----------------------------
INSERT INTO `user_setting` VALUES ('1', '1', '1', '1', '2015-10-20 14:52:43', null, '0');
