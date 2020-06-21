/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 18/04/2020 17:45:49
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blog_article
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_article`;
CREATE TABLE `t_blog_article`  (
                                   `id` int(11) NOT NULL AUTO_INCREMENT,
                                   `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                   `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                   `viewCount` int(11) DEFAULT 0,
                                   `createdAt` datetime(0) DEFAULT NULL,
                                   `updatedAt` datetime(0) DEFAULT NULL,
                                   PRIMARY KEY (`id`) USING BTREE,
                                   UNIQUE INDEX `title`(`title`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 3072 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_blog_article
-- ----------------------------
INSERT INTO `t_blog_article` VALUES (-1, '关于页面', '关于页面存档，勿删', 1, '2020-04-03 23:32:14', '2020-04-04 11:08:56');
INSERT INTO `t_blog_article` VALUES (1, '这是一个测试', '# 标题一\n好人一生平安\n# 标题二\n一人一个山', 2, '2020-04-04 13:17:04', '2020-04-04 13:26:17');

-- ----------------------------
-- Table structure for t_blog_category
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_category`;
CREATE TABLE `t_blog_category`  (
                                    `id` int(11) NOT NULL AUTO_INCREMENT,
                                    `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                    `articleId` int(11) DEFAULT NULL,
                                    PRIMARY KEY (`id`) USING BTREE,
                                    INDEX `articleId`(`articleId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 3072 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_comment`;
CREATE TABLE `t_blog_comment`  (
                                   `id` int(11) NOT NULL AUTO_INCREMENT,
                                   `articleId` int(11) DEFAULT NULL,
                                   `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                   `createdAt` datetime(0) DEFAULT NULL,
                                   `updatedAt` datetime(0) DEFAULT NULL,
                                   `userId` int(11) DEFAULT NULL,
                                   PRIMARY KEY (`id`) USING BTREE,
                                   INDEX `articleId`(`articleId`) USING BTREE,
                                   INDEX `userId`(`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 3072 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_blog_ip
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_ip`;
CREATE TABLE `t_blog_ip`  (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `ip` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                              `auth` tinyint(1) DEFAULT 1,
                              `userId` int(11) DEFAULT NULL,
                              PRIMARY KEY (`id`) USING BTREE,
                              INDEX `userId`(`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 3072 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_blog_privilege
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_privilege`;
CREATE TABLE `t_blog_privilege`  (
                                     `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                     `privilegeCode` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限code',
                                     `privilegeName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限名',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blog_privilege
-- ----------------------------
INSERT INTO `t_blog_privilege` VALUES (1, 'menu_user', '用户管理');
INSERT INTO `t_blog_privilege` VALUES (2, 'interface_create_user', '创建用户');
INSERT INTO `t_blog_privilege` VALUES (3, 'interface_query_user', '查看用户');
INSERT INTO `t_blog_privilege` VALUES (4, 'interface_delete_user', '删除用户');
INSERT INTO `t_blog_privilege` VALUES (5, 'interface_modify_user', '修改用户');

-- ----------------------------
-- Table structure for t_blog_reply
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_reply`;
CREATE TABLE `t_blog_reply`  (
                                 `id` int(11) NOT NULL AUTO_INCREMENT,
                                 `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                 `createdAt` datetime(0) DEFAULT NULL,
                                 `updatedAt` datetime(0) DEFAULT NULL,
                                 `articleId` int(11) DEFAULT NULL,
                                 `commentId` int(11) DEFAULT NULL,
                                 `userId` int(11) DEFAULT NULL,
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `articleId`(`articleId`) USING BTREE,
                                 INDEX `userId`(`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 3072 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_blog_request_path
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_request_path`;
CREATE TABLE `t_blog_request_path`  (
                                        `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                        `url` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '请求路径',
                                        `description` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '路径描述',
                                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '请求路径' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blog_request_path
-- ----------------------------
INSERT INTO `t_blog_request_path` VALUES (1, '/user/list', '用户查询接口');

-- ----------------------------
-- Table structure for t_blog_request_path_privilege_mapping
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_request_path_privilege_mapping`;
CREATE TABLE `t_blog_request_path_privilege_mapping`  (
                                                          `id` int(11) DEFAULT NULL COMMENT '主键id',
                                                          `urlId` int(11) DEFAULT NULL COMMENT '请求路径id',
                                                          `privilegeId` int(11) DEFAULT NULL COMMENT '权限id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '路径权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blog_request_path_privilege_mapping
-- ----------------------------
INSERT INTO `t_blog_request_path_privilege_mapping` VALUES (1, 1, 3);

-- ----------------------------
-- Table structure for t_blog_role
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_role`;
CREATE TABLE `t_blog_role`  (
                                `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                `roleName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
                                `description` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色说明',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blog_role
-- ----------------------------
INSERT INTO `t_blog_role` VALUES (1, '管理员', NULL);

-- ----------------------------
-- Table structure for t_blog_role_privilege_mapping
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_role_privilege_mapping`;
CREATE TABLE `t_blog_role_privilege_mapping`  (
                                                  `roleId` int(11) NOT NULL COMMENT '角色id',
                                                  `privilegeId` int(11) NOT NULL COMMENT '权限id'
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限关系表' ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of t_blog_role_privilege_mapping
-- ----------------------------
INSERT INTO `t_blog_role_privilege_mapping` VALUES (1, 1);
INSERT INTO `t_blog_role_privilege_mapping` VALUES (1, 2);
INSERT INTO `t_blog_role_privilege_mapping` VALUES (1, 3);

-- ----------------------------
-- Table structure for t_blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_tag`;
CREATE TABLE `t_blog_tag`  (
                               `id` int(11) NOT NULL AUTO_INCREMENT,
                               `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                               `articleId` int(11) DEFAULT NULL,
                               PRIMARY KEY (`id`) USING BTREE,
                               INDEX `articleId`(`articleId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 3072 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_blog_user
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_user`;
CREATE TABLE `t_blog_user`  (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
                                `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '通过 bcrypt 加密后的密码',
                                `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
                                `notice` tinyint(1) DEFAULT 1,
                                `github` text CHARACTER SET utf8 COLLATE utf8_general_ci,
                                `disabledDiscuss` tinyint(1) DEFAULT 0,
                                `createdAt` datetime(0) DEFAULT NULL,
                                `updatedAt` datetime(0) DEFAULT NULL,
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 3072 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_blog_user
-- ----------------------------
INSERT INTO `t_blog_user` VALUES (3, 'zhengyang', 'FO7AXTFi0MinEzacOHU6pQ==', '18151521911@163.com', 1, 'Zhengyang550#github.com', 1, '2020-04-15 21:52:39', NULL);

-- ----------------------------
-- Table structure for t_blog_user_role_mapping
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_user_role_mapping`;
CREATE TABLE `t_blog_user_role_mapping`  (
                                             `userId` int(11) NOT NULL COMMENT '用户id',
                                             `roleId` int(11) NOT NULL COMMENT '角色id'
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关系表' ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of t_blog_user_role_mapping
-- ----------------------------
INSERT INTO `t_blog_user_role_mapping` VALUES (3, 1);

SET FOREIGN_KEY_CHECKS = 1;
