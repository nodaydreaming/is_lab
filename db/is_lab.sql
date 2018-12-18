/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : is_lab

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2018-12-18 22:33:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for competition
-- ----------------------------
DROP TABLE IF EXISTS `competition`;
CREATE TABLE `competition` (
  `competitionId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `worksName` varchar(255) DEFAULT NULL,
  `awardLevel` varchar(255) DEFAULT NULL,
  `instructor` varchar(255) DEFAULT NULL,
  `teamMember` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`competitionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of competition
-- ----------------------------

-- ----------------------------
-- Table structure for culture
-- ----------------------------
DROP TABLE IF EXISTS `culture`;
CREATE TABLE `culture` (
  `cultureId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `content` text,
  `type` int(2) DEFAULT NULL,
  PRIMARY KEY (`cultureId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of culture
-- ----------------------------

-- ----------------------------
-- Table structure for introduction
-- ----------------------------
DROP TABLE IF EXISTS `introduction`;
CREATE TABLE `introduction` (
  `introductionId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`introductionId`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of introduction
-- ----------------------------
INSERT INTO `introduction` VALUES ('1', 'libIntro', '<p><br/></p><p><span style=\"font-size:16px;font-family:宋体\">&nbsp;&nbsp;近年来，斯诺登“棱镜门”、苹果公司“泄密门”等事件的曝出，使得信息安全问题受到了前所未有的关注。网络与信息安全已经成为影响国家安全、社会稳定、企业发展的重要因素。2015年，“网络空间安全”获批国家一级学科。然而与此同时，我国信息安全人才紧缺，专业人才缺口达50万。</span></p><p><span style=\"font-size:16px;font-family:宋体\"><br/></span></p><p><span style=\"font-size:16px;font-family:宋体\">&nbsp;&nbsp;</span><span style=\"font-size:16px;font-family:宋体\">杭州国际服务工程学院网络与信息安全实验室，长期致力于提高学生的信息安全意识，培养学生的网络攻防实践能力，锻炼学生的实践应用能力，鼓励学生对信息安全技术进行创新和应用。实验室在培养学生能力的同时，不断提供各种平台，包括各类专业性学科竞赛，外出交流学习，公司实习实训等，给学生展现自我的机会。</span></p><p><span style=\"font-size:16px;font-family:宋体\"><br/></span></p><p style=\"text-indent:32px\"><span style=\"font-size:16px;font-family:宋体\">自2012年成立以来，实验室累计培养学生60余名。学生参与发表论文10余篇，申请发明专利10余项，完成各类科技开发项目近10项。参与多届全国大学生信息安全竞赛，并连续多年在该竞赛中取得奖项。从实验室毕业的学生大多任职于国内知名的信息安全及IT企业，例如阿里巴巴网络技术有限公司、华途软件有限公司、华澜微科技有限公司、安恒信息技术有限公司、浙江大华技术股份有限公司、海康威视等公司，也有不少出国深造或到政府、学校等机构就职。</span></p><p style=\"text-indent:32px\"><span style=\"font-size:16px;font-family:宋体\"><br/></span></p><p><span style=\"font-size:16px;font-family:宋体\">&nbsp;&nbsp;</span><span style=\"font-size:16px;font-family:宋体\">热忱欢迎对网络与信息安全相关研究与开发有浓厚兴趣，学习能力强，能够坚持，做事踏实的同学们加入我们实验室大家庭！</span></p>');
INSERT INTO `introduction` VALUES ('2', 'awards', '<p>杭州师范大学网络与信息安全实验室自成立以为，取得不错的的成绩。</p>');
INSERT INTO `introduction` VALUES ('3', 'achievementSummary', '<p>123456</p>');
INSERT INTO `introduction` VALUES ('4', 'rule', '<p>规章制度。</p><p>1.</p><p>2.</p><p>3.</p>');

-- ----------------------------
-- Table structure for link
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link` (
  `linkId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  PRIMARY KEY (`linkId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of link
-- ----------------------------

-- ----------------------------
-- Table structure for patent
-- ----------------------------
DROP TABLE IF EXISTS `patent`;
CREATE TABLE `patent` (
  `patentId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `principal` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`patentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patent
-- ----------------------------

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `projectId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(11) DEFAULT NULL,
  `startdate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `enddate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `principal` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`projectId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('1', '项目1', '123456', '2018-12-05 00:00:00', '2018-12-13 00:00:00', 'gyj', '1');
INSERT INTO `project` VALUES ('2', 'xiangmu2', '123456789', '2018-12-05 00:00:00', '2018-12-13 00:00:00', 'aaa', '2');
INSERT INTO `project` VALUES ('3', '12', '123', '2014-02-04 00:00:00', '2018-06-12 00:00:00', '1', '1');
INSERT INTO `project` VALUES ('4', '123', '1231313', '2014-12-09 00:00:00', '2018-05-16 00:00:00', '111', '22');

-- ----------------------------
-- Table structure for research
-- ----------------------------
DROP TABLE IF EXISTS `research`;
CREATE TABLE `research` (
  `researchId` int(11) NOT NULL AUTO_INCREMENT,
  `research_direction` varchar(255) DEFAULT NULL,
  `introduction` varchar(255) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  PRIMARY KEY (`researchId`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of research
-- ----------------------------
INSERT INTO `research` VALUES ('15', '车联网安全', '车联网安全的简介', '15');
INSERT INTO `research` VALUES ('16', '区块链应用', '区块链应用的简介', '16');
INSERT INTO `research` VALUES ('21', '123456', '121313213', '21');
INSERT INTO `research` VALUES ('22', 'sadsa ', 'xdvcvxcvcxv', '22');
INSERT INTO `research` VALUES ('23', '111', '11', '23');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `studentId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `homepage` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `post` varchar(255) DEFAULT NULL,
  `salary` varchar(255) DEFAULT NULL,
  `intro` varchar(255) DEFAULT NULL,
  `type` int(2) DEFAULT NULL,
  PRIMARY KEY (`studentId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacherId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `en_name` varchar(255) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `research_area` varchar(255) DEFAULT NULL,
  `degree` varchar(255) DEFAULT NULL,
  `intro` text,
  `priority` int(11) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`teacherId`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('7', 'guoyajie', '/is_lab/upload/teacher/20181218204033106288.JPG', null, '男', null, '1772780065@qq.com', null, '123456', '123456789', '00000000007');
INSERT INTO `teacher` VALUES ('8', 'guoyajie', '/is_lab/upload/teacher/20181218204221292177.png', null, '男', null, '111111@qq.com', null, '1231231', '456789', '00000000008');
INSERT INTO `teacher` VALUES ('9', '123456', '/is_lab/upload/teacher/20181218204339489714.JPG', null, '男', null, '123456@qq.com', null, '学生', '12315465132', '00000000009');
INSERT INTO `teacher` VALUES ('10', 'sdfds', '/is_lab/upload/teacher/20181218213835893060.JPG', null, '女', null, 'dsfs@qq.com', null, 'dsf', '12345', '00000000010');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `photo` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', null, 'admin', '691F8A43BC1C32FCB9C48F65BEB5CD65691407DB', '男', 'nodaydreaming', '1772780065@qq.com', '13396567238', '2');
INSERT INTO `user` VALUES ('2', null, 'test', '861BD1D2B795325AB4DD57561656C14277F9B8F6', '男', 'testName', null, '123456789', '1');

-- ----------------------------
-- Table structure for works
-- ----------------------------
DROP TABLE IF EXISTS `works`;
CREATE TABLE `works` (
  `worksId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `intro` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`worksId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of works
-- ----------------------------
