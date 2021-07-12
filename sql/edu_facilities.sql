/*
Navicat MySQL Data Transfer

Source Server         : online
Source Server Version : 50505
Source Host           : 120.77.80.75:3306
Source Database       : glc_edu_dev

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2021-01-10 19:01:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for edu_facilities
-- ----------------------------
DROP TABLE IF EXISTS `edu_facilities`;
CREATE TABLE `edu_facilities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `batch_id` bigint(20) DEFAULT NULL,
  `number` int(10) DEFAULT NULL COMMENT '编号',
  `facilities_name` varchar(255) DEFAULT NULL COMMENT '设施功能区名称',
  `facilities_area` int(10) DEFAULT NULL COMMENT '设施功能区面积-平米',
  `equipment_ability` varchar(255) DEFAULT NULL COMMENT '单个设备处理能力（单品/秒）',
  `facilities_price` int(10) DEFAULT NULL COMMENT '设施单价（元\\平米\\天）',
  `worker_name` varchar(255) DEFAULT NULL COMMENT '工种名称',
  `facilities_worker` int(10) DEFAULT NULL COMMENT '功能区所需工作人员数量',
  `work_wage` int(10) DEFAULT NULL COMMENT '人员工资（元\\天\\人）',
  `worker_ability` varchar(255) DEFAULT NULL COMMENT '人员处理能力（单品/秒）',
  `equipment_name` varchar(255) DEFAULT NULL COMMENT '功能区设备名称',
  `equipment_number` int(10) DEFAULT NULL COMMENT '功能区设备数量（个）（货架为：立方米）',
  `equipment_price` int(10) DEFAULT NULL COMMENT '设备单价（元\\个）（货架为：元/立方米）',
  `operator_id` int(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='设施功能区';
