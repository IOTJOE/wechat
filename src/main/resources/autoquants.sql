/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : autoquants

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 12/24/2018 09:28:15 AM
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
--  Table structure for `t_trader_by_day`
-- ----------------------------
DROP TABLE IF EXISTS `t_trader_by_day`;
CREATE TABLE `t_trader_by_day` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserAccount` varchar(20) DEFAULT NULL,
  `TransDate` date DEFAULT NULL,
  `StockId` varchar(20) DEFAULT NULL,
  `Price` varchar(20) DEFAULT NULL,
  `Amount` int(20) DEFAULT NULL,
  `Direction` int(10) DEFAULT NULL,
  `PriceType` int(10) DEFAULT NULL,
  `MarketType` int(10) DEFAULT NULL,
  `Status` int(2) DEFAULT '1',
  `TransId` varchar(20) DEFAULT NULL,
  `Source` varchar(100) DEFAULT NULL,
  `Message` varchar(200) DEFAULT NULL,
  `Des` varchar(200) DEFAULT NULL,
  `SuccessAmount` int(10) DEFAULT NULL,
  `RevokeAmount` int(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_trader_by_day`
-- ----------------------------
BEGIN;
INSERT INTO `t_trader_by_day` VALUES ('162', '43429990', '2018-12-24', '002195', '4.13', '100', '2', '0', '2', '2', '978341997', '', '', '下单成功', '0', '0'), ('163', '43429990', '2018-12-24', '603890', '13.97', '100', '3', '0', '2', '0', '', '同花顺', '提交失败：[603890:13.970]超过涨跌限制。13.5-11.04。', '下单失败', '0', '0'), ('164', '201130022743', '2018-12-24', '000517', '2.74', '9800', '2', '0', '2', '2', '3881', '', '', '下单成功', '0', '0');
COMMIT;

-- ----------------------------
--  Table structure for `t_trader_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_trader_log`;
CREATE TABLE `t_trader_log` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserAccount` varchar(20) DEFAULT NULL,
  `TransDate` date DEFAULT NULL,
  `StockId` varchar(20) DEFAULT NULL,
  `Action` varchar(20) DEFAULT NULL,
  `PriceType` int(20) DEFAULT NULL,
  `Price` varchar(20) DEFAULT NULL,
  `Type` varchar(20) DEFAULT NULL,
  `Amount` int(20) DEFAULT NULL,
  `SuccessAmount` int(10) DEFAULT NULL,
  `RevokeAmount` int(10) DEFAULT NULL,
  `Transid` varchar(20) DEFAULT NULL,
  `Source` varchar(20) DEFAULT NULL,
  `MarketType` int(10) DEFAULT NULL,
  `Message` varchar(50) DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL,
  `Direction` int(10) DEFAULT NULL,
  `DayId` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=270 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_trader_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_trader_user`;
CREATE TABLE `t_trader_user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) DEFAULT NULL,
  `Broker` varchar(20) DEFAULT NULL,
  `UserAccount` varchar(20) DEFAULT NULL,
  `FileName` varchar(20) DEFAULT NULL,
  `ServerIp` varchar(200) DEFAULT NULL,
  `AvailableAmount` varchar(20) DEFAULT NULL,
  `TotalAmount` varchar(20) DEFAULT NULL,
  `TransIp` varchar(200) DEFAULT NULL,
  `Status` int(2) DEFAULT NULL,
  `AutoBuyStatus` int(2) DEFAULT '0',
  `AutoSoldStatus` int(2) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_trader_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_trader_user` VALUES ('1', '姜忠坷', '同花顺测试', '43429990', 'TradePlan_Test1.csv', 'http://localhost:8088', null, null, 'http://192.168.130.139:8888', '1', '1', '1'), ('2', '姜忠坷', '东方财富', '320400009783', '320400009783', 'http://localhost:8088', null, null, 'http://192.168.130.139:8888', '1', '0', '1'), ('3', '姜忠坷', '通达信模拟', '18516550566', '18516550566', 'http://localhost:8088', null, null, 'http://192.168.130.139:8888', '1', '0', '1'), ('4', '基金融资680', '山西证券', '887038888', 'TradePlan_LX1.csv', 'http://joemercy.xicp.net', null, null, 'http://iotjoe.eicp.net', '1', '0', '1'), ('5', '联储159', '联储证券', '97001528', 'TradePlan_LX2.csv', 'http://joemercy.xicp.net', null, null, 'http://iotjoe.eicp.net', '1', '0', '1'), ('6', '银河56', '银河证券', '201130022743', 'TradePlan_LX3.csv', 'http://joemercy.xicp.net', null, null, 'http://iotjoe.eicp.net', '1', '1', '0'), ('7', '融资', '海通证券', '1880150785', 'TradePlan_LX4.csv', 'http://joemercy.xicp.net', null, null, 'http://iotjoe.eicp.net', '1', '0', '1');
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
