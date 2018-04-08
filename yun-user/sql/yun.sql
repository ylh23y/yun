/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.17-log : Database - yun
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yun` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `yun`;

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` varchar(64) NOT NULL COMMENT '主键id',
  `parentId` varchar(64) DEFAULT NULL COMMENT '父id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `css` varchar(30) DEFAULT NULL COMMENT 'css样式',
  `href` varchar(255) DEFAULT NULL COMMENT '菜单url对应的路径',
  `type` int(1) NOT NULL COMMENT '权限类型：1：菜单 2：按钮',
  `permission` varchar(1000) DEFAULT NULL COMMENT '权限的路径',
  `sort` int(4) NOT NULL COMMENT '排序',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限资源表';

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` varchar(64) NOT NULL COMMENT '主键id',
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '就是描述',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `roleId` varchar(64) NOT NULL COMMENT '关联sys_role表id',
  `permissionId` varchar(64) NOT NULL COMMENT '关联sys_permission表id',
  PRIMARY KEY (`roleId`,`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `sys_role_user` */

DROP TABLE IF EXISTS `sys_role_user`;

CREATE TABLE `sys_role_user` (
  `userId` varchar(64) NOT NULL COMMENT '关联sys_user表id',
  `roleId` varchar(64) NOT NULL COMMENT '关联sys_role表id',
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` varchar(64) NOT NULL COMMENT '主键id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(60) NOT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '匿名',
  `headImgUrl` varchar(255) DEFAULT NULL COMMENT '头像url',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `telephone` varchar(30) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `last_password_reset_date` timestamp NULL DEFAULT NULL COMMENT '最后重置密码时间',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `sex` int(1) DEFAULT NULL COMMENT '性别 0：男  1；女  2：保密',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态(0：ENABLED:启用;  1:DISABLED:禁用; 2:DELETE:逻辑删除)',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
