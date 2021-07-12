/*
Navicat MySQL Data Transfer

Source Server         : online
Source Server Version : 50505
Source Host           : 120.77.80.75:3306
Source Database       : glc_edu_dev

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2021-01-10 19:01:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for edu_emp_data
-- ----------------------------
DROP TABLE IF EXISTS `edu_emp_data`;
CREATE TABLE `edu_emp_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `batch_id` int(11) DEFAULT NULL,
  `departmen_name` char(50) DEFAULT NULL COMMENT '部门名称',
  `duty_description` char(50) DEFAULT NULL COMMENT '工作内容',
  `employee_number` double DEFAULT NULL COMMENT '人员数据',
  `employee_salary` double DEFAULT NULL COMMENT '人员工资',
  `process_capacity` double DEFAULT NULL COMMENT '单个人员处理能力',
  `cellStyleMap` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;
