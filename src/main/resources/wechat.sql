 /*
 Navicat Premium Data Transfer

 Source Server         : 测试服务器
 Source Server Type    : MySQL
 Source Server Version : 50550
 Source Host           : 121.40.158.114
 Source Database       : wechat

 Target Server Type    : MySQL
 Target Server Version : 50550
 File Encoding         : utf-8

 Date: 09/26/2017 10:17:00 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_local_message_template`
-- ----------------------------
DROP TABLE IF EXISTS `t_local_message_template`;
CREATE TABLE `t_local_message_template` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Code` varchar(256) DEFAULT NULL,
  `Remark` varchar(256) DEFAULT NULL,
  `Status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_local_message_template`
-- ----------------------------
BEGIN;
INSERT INTO `t_local_message_template` VALUES ('1', 'DD_001', 'ea', b'1');
COMMIT;

-- ----------------------------
--  Table structure for `t_message_template`
-- ----------------------------
DROP TABLE IF EXISTS `t_message_template`;
CREATE TABLE `t_message_template` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `WeChatTemplateId` varchar(256) DEFAULT NULL,
  `ShortMessageId` varchar(256) DEFAULT NULL,
  `WechatTopColor` varchar(20) DEFAULT NULL,
  `Url` varchar(128) DEFAULT NULL,
  `Status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_message_template`
-- ----------------------------
BEGIN;
INSERT INTO `t_message_template` VALUES ('1', '订单', 'yoiqyiuqyoeiyqiuweyoq', '1', '#000000', null, b'1'), ('2', '保金所还款完成通知', '8ws2u9gi7_hIxLDA-ogRpJCRC14rCqIJwx0u7RfBBwg', null, '#000000', 'https://www.baojinsuo.com', b'1');
COMMIT;

-- ----------------------------
--  Table structure for `t_notice_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_notice_user`;
CREATE TABLE `t_notice_user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LocalMessageTemplateId` bigint(20) DEFAULT NULL,
  `MessageId` bigint(20) DEFAULT NULL,
  `WeChatId` bigint(20) DEFAULT NULL,
  `UserId` bigint(20) DEFAULT NULL,
  `Status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_notice_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_notice_user` VALUES ('1', '1', '1', '2', '1', b'1'), ('2', '1', '1', '1', '2', b'1'), ('3', '1', '1', '1', '3', b'1');
COMMIT;

-- ----------------------------
--  Table structure for `t_receiver`
-- ----------------------------
DROP TABLE IF EXISTS `t_receiver`;
CREATE TABLE `t_receiver` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(126) DEFAULT NULL,
  `Phone` varchar(126) DEFAULT NULL,
  `OpenId` varchar(256) DEFAULT NULL,
  `Status` int(2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_receiver`
-- ----------------------------
BEGIN;
INSERT INTO `t_receiver` VALUES ('1', 'joe', '18516550566', 'olHwSuGqKjprKz7GiZChnFTHieQo', '1'), ('2', 'kk', '12631726371', 'open_11232837', '1'), ('8', '姜忠坷', '18516550566', 'uhaduihaiudhuidshsa', '0'), ('9', 'asd', '18617656789', '22uhaduihaiudhuidsh', '0'), ('10', 'jai', '18617656781', 'uhaduihaiudhuidshasd', '0');
COMMIT;

-- ----------------------------
--  Table structure for `t_wechat_template_detail`
-- ----------------------------
DROP TABLE IF EXISTS `t_wechat_template_detail`;
CREATE TABLE `t_wechat_template_detail` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(256) DEFAULT NULL,
  `WeChatTemplateID` bigint(20) DEFAULT NULL,
  `WeChatTemplateKey` varchar(256) DEFAULT NULL,
  `LocalReceiveKey` varchar(256) DEFAULT NULL,
  `WeChatMessageColor` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_wechat_template_detail`
-- ----------------------------
BEGIN;
INSERT INTO `t_wechat_template_detail` VALUES ('1', '名字', '1', 'key', 'key1', '#878126'), ('2', '电话', '1', 'phone', 'phone1', '#615267'), ('3', '欢迎消息', '2', 'first', 'first', '#000000'), ('4', '产品名称', '2', 'keyword1', 'productName', '#000000'), ('5', '本金', '2', 'keyword2', 'repaymentPrincipal', '#000000'), ('6', '利息', '2', 'keyword3', 'repaymentInterest', '#000000'), ('7', '时间', '2', 'keyword4', 'repaymentTime', '#000000'), ('8', '备注', '2', 'remark', 'remark', '#000000');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
