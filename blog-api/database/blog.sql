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

 Date: 01/09/2020 10:11:19
*/

SET NAMES utf8mb4;
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
) ENGINE = InnoDB AUTO_INCREMENT = 121 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 3072 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_blog_article
-- ----------------------------
INSERT INTO `t_blog_article` VALUES (1, '这是一个测试', '# 标题一\n好人一生平安\n# 标题二\n一人一个山', 2, '2020-04-04 13:17:04', '2020-04-04 13:26:17');
INSERT INTO `t_blog_article` VALUES (2, '关于页面', '关于页面存档，勿删', 1, '2020-04-03 23:32:14', '2020-04-04 11:08:56');
INSERT INTO `t_blog_article` VALUES (3, '如何用 es6+ 写出优雅的 js 代码', '<h2 id=\\\"前言\\\">前言</h2><p>这是本人的学习的记录，因为最近在准备面试，很多情况下会被提问到：请简述 <code>mvvm</code> ?<br>一般情况下我可能这么答：<code>mvvm</code> 是视图和逻辑的一个分离，是<code>model view view-model</code> 的缩写，通过虚拟dom的方式实现双向数据绑定（我随便答得）</p><p>那么问题来了，你知道 <code>mvvm</code> 是怎么实现的？\r\n', 279, '2020-04-15 22:31:01', '2020-04-09 22:31:04');
INSERT INTO `t_blog_article` VALUES (4, '测试 1', '<h2 id=\\\"前言\\\">前言</h2><p>这是本人的学习的记录，因为最近在准备面试，很多情况下会被提问到：请简述 <code>mvvm</code> ?<br>一般情况下我可能这么答：<code>mvvm</code> 是视图和逻辑的一个分离，是<code>model view view-model</code> 的缩写，通过虚拟dom的方式实现双向数据绑定（我随便答得）</p><p>那么问题来了，你知道 <code>mvvm</code> 是怎么实现的？\r\n', 279, '2019-01-15 22:31:01', '2020-04-09 22:31:04');
INSERT INTO `t_blog_article` VALUES (5, '测试22', '<h2 id=\\\"前言\\\">前言</h2><p>这是本人的学习的记录，因为最近在准备面试，很多情况下会被提问到：请简述 <code>mvvm</code> ?<br>一般情况下我可能这么答：<code>mvvm</code> 是视图和逻辑的一个分离，是<code>model view view-model</code> 的缩写，通过虚拟dom的方式实现双向数据绑定（我随便答得）</p><p>那么问题来了，你知道 <code>mvvm</code> 是怎么实现的？\r\n', 279, '2020-04-15 22:31:01', '2020-04-09 22:31:04');
INSERT INTO `t_blog_article` VALUES (6, '如何用 es6+ 写出优雅的 js 代码1111', '<h2 id=\\\"前言\\\">前言</h2><p>这是本人的学习的记录，因为最近在准备面试，很多情况下会被提问到：请简述 <code>mvvm</code> ?<br>一般情况下我可能这么答：<code>mvvm</code> 是视图和逻辑的一个分离，是<code>model view view-model</code> 的缩写，通过虚拟dom的方式实现双向数据绑定（我随便答得）</p><p>那么问题来了，你知道 <code>mvvm</code> 是怎么实现的？\r\n', 279, '2020-04-15 22:31:01', '2020-04-09 22:31:04');
INSERT INTO `t_blog_article` VALUES (7, '如何用 es6+ 写出优雅的 js 代码1111222', '<h2 id=\\\"前言\\\">前言</h2><p>这是本人的学习的记录，因为最近在准备面试，很多情况下会被提问到：请简述 <code>mvvm</code> ?<br>一般情况下我可能这么答：<code>mvvm</code> 是视图和逻辑的一个分离，是<code>model view view-model</code> 的缩写，通过虚拟dom的方式实现双向数据绑定（我随便答得）</p><p>那么问题来了，你知道 <code>mvvm</code> 是怎么实现的？\r\n', 279, '2020-04-15 22:31:01', '2020-04-09 22:31:04');
INSERT INTO `t_blog_article` VALUES (8, '如何用 es6+ 写出优雅的 js 代码11112222', '<h2 id=\\\"前言\\\">前言</h2><p>这是本人的学习的记录，因为最近在准备面试，很多情况下会被提问到：请简述 <code>mvvm</code> ?<br>一般情况下我可能这么答：<code>mvvm</code> 是视图和逻辑的一个分离，是<code>model view view-model</code> 的缩写，通过虚拟dom的方式实现双向数据绑定（我随便答得）</p><p>那么问题来了，你知道 <code>mvvm</code> 是怎么实现的？\r\n', 279, '2020-04-15 22:31:01', '2020-04-09 22:31:04');
INSERT INTO `t_blog_article` VALUES (9, '111222', '<h2 id=\\\"前言\\\">前言</h2><p>这是本人的学习的记录，因为最近在准备面试，很多情况下会被提问到：请简述 <code>mvvm</code> ?<br>一般情况下我可能这么答：<code>mvvm</code> 是视图和逻辑的一个分离，是<code>model view view-model</code> 的缩写，通过虚拟dom的方式实现双向数据绑定（我随便答得）</p><p>那么问题来了，你知道 <code>mvvm</code> 是怎么实现的？\r\n', 279, '2020-04-15 22:31:01', '2020-04-09 22:31:04');
INSERT INTO `t_blog_article` VALUES (10, '3333', '<h2 id=\\\"前言\\\">前言</h2><p>这是本人的学习的记录，因为最近在准备面试，很多情况下会被提问到：请简述 <code>mvvm</code> ?<br>一般情况下我可能这么答：<code>mvvm</code> 是视图和逻辑的一个分离，是<code>model view view-model</code> 的缩写，通过虚拟dom的方式实现双向数据绑定（我随便答得）</p><p>那么问题来了，你知道 <code>mvvm</code> 是怎么实现的？\r\n', 279, '2020-04-15 22:31:01', '2020-04-09 22:31:04');
INSERT INTO `t_blog_article` VALUES (11, '33码1111', '<h2 id=\"前言\">前言</h2><p>这是本人的学习的记录，因为最近在准备面试，很多情况下会被提问到：请简述 <code>mvvm</code> ?<br>一般情况下我可能这么答：<code>mvvm</code> 是视图和逻辑的一个分离，是<code>model view view-model</code> 的缩写，通过虚拟dom的方式实现双向数据绑定（我随便答得）</p><p>那么问题来了，你知道 <code>mvvm</code> 是怎么实现的？', 279, '2020-04-15 22:31:01', '2020-04-09 22:31:04');
INSERT INTO `t_blog_article` VALUES (12, '文章', '<p>在前面的一些小节中，我们已经使用到的图像描述符匹配相关的函数，在OpenCV中主要提供了暴力匹配、以及FLANN匹配函数库。</p>\r\n            <h1 id=\"1-暴力匹配以及优化交叉匹配、knn匹配\">一 暴力匹配以及优化(交叉匹配、KNN匹配)</h1>\r\n            <p>暴力匹配即两两匹配。该算法不涉及优化，假设从图片A中提取了m个特征描述符，从B图片提取了n个特征描述符。对于A中m个特征描述符的任意一个都需要和B中的n个特征描述符进行比较。每次比较都会给出一个距离值，然后将得到的距离进行排序，取距离最近的一个作为匹配点。这种方法简单粗暴，其结果也是显而易见的，通过前几节的演示案例，我们知道有大量的错误匹配，这就需要使用一些机制来过滤掉错误的匹配。比如我们对匹配点按照距离来排序，并指定一个距离阈值，过滤掉一些匹配距离较远的点。<br>![<a href=\"https://img2018.cnblogs.com/blog/1328274/201809/1328274-20180914151318761-1478858833.png\">https://img2018.cnblogs.com/blog/1328274/201809/1328274-20180914151318761-1478858833.png</a>)</p>\r\n            <h2 id=\"1.1-暴力匹配以及优化交叉匹配、knn匹配\">1.1 暴力匹配以及优化(交叉匹配、KNN匹配)</h2>\r\n            <p>暴力匹配即两两匹配。该算法不涉及优化，假设从图片A中提取了m个特征描述符，从B图片提取了n个特征描述符。对于A中m个特征描述符的任意一个都需要和B中的n个特征描述符进行比较。每次比较都会给出一个距离值，然后将得到的距离进行排序，取距离最近的一个作为匹配点。这种方法简单粗暴，其结果也是显而易见的，通过前几节的演示案例，我们知道有大量的错误匹配，这就需要使用一些机制来过滤掉错误的匹配。比如我们对匹配点按照距离来排序，并指定一个距离阈值，过滤掉一些匹配距离较远的点。<br>![<a href=\"https://img2018.cnblogs.com/blog/1328274/201809/1328274-20180914151318761-1478858833.png\">https://img2018.cnblogs.com/blog/1328274/201809/1328274-20180914151318761-1478858833.png</a>)</p>\r\n            <h2 id=\"1.2-暴力匹配以及优化交叉匹配、knn匹配\">1.2 暴力匹配以及优化(交叉匹配、KNN匹配)</h2>\r\n            <p>暴力匹配即两两匹配。该算法不涉及优化，假设从图片A中提取了m个特征描述符，从B图片提取了n个特征描述符。对于A中m个特征描述符的任意一个都需要和B中的n个特征描述符进行比较。每次比较都会给出一个距离值，然后将得到的距离进行排序，取距离最近的一个作为匹配点。这种方法简单粗暴，其结果也是显而易见的，通过前几节的演示案例，我们知道有大量的错误匹配，这就需要使用一些机制来过滤掉错误的匹配。比如我们对匹配点按照距离来排序，并指定一个距离阈值，过滤掉一些匹配距离较远的点。<br>![<a href=\"https://img2018.cnblogs.com/blog/1328274/201809/1328274-20180914151318761-1478858833.png\">https://img2018.cnblogs.com/blog/1328274/201809/1328274-20180914151318761-1478858833.png</a>)</p>\r\n            <h2 id=\"1.3-暴力匹配以及优化交叉匹配、knn匹配\">1.3 暴力匹配以及优化(交叉匹配、KNN匹配)</h2>\r\n            <p>暴力匹配即两两匹配。该算法不涉及优化，假设从图片A中提取了m个特征描述符，从B图片提取了n个特征描述符。对于A中m个特征描述符的任意一个都需要和B中的n个特征描述符进行比较。每次比较都会给出一个距离值，然后将得到的距离进行排序，取距离最近的一个作为匹配点。这种方法简单粗暴，其结果也是显而易见的，通过前几节的演示案例，我们知道有大量的错误匹配，这就需要使用一些机制来过滤掉错误的匹配。比如我们对匹配点按照距离来排序，并指定一个距离阈值，过滤掉一些匹配距离较远的点。<br>![<a href=\"https://img2018.cnblogs.com/blog/1328274/201809/1328274-20180914151318761-1478858833.png\">https://img2018.cnblogs.com/blog/1328274/201809/1328274-20180914151318761-1478858833.png</a>)</p>\r\n            <h2 id=\"1.4-暴力匹配以及优化交叉匹配、knn匹配\">1.4 暴力匹配以及优化(交叉匹配、KNN匹配)</h2>\r\n            <p>暴力匹配即两两匹配。该算法不涉及优化，假设从图片A中提取了m个特征描述符，从B图片提取了n个特征描述符。对于A中m个特征描述符的任意一个都需要和B中的n个特征描述符进行比较。每次比较都会给出一个距离值，然后将得到的距离进行排序，取距离最近的一个作为匹配点。这种方法简单粗暴，其结果也是显而易见的，通过前几节的演示案例，我们知道有大量的错误匹配，这就需要使用一些机制来过滤掉错误的匹配。比如我们对匹配点按照距离来排序，并指定一个距离阈值，过滤掉一些匹配距离较远的点。<br>![<a href=\"https://img2018.cnblogs.com/blog/1328274/201809/1328274-20180914151318761-1478858833.png\">https://img2018.cnblogs.com/blog/1328274/201809/1328274-20180914151318761-1478858833.png</a>)</p>\r\n            <h2 id=\"1.5-暴力匹配以及优化交叉匹配、knn匹配\">1.5 暴力匹配以及优化(交叉匹配、KNN匹配)</h2>\r\n            <p>暴力匹配即两两匹配。该算法不涉及优化，假设从图片A中提取了m个特征描述符，从B图片提取了n个特征描述符。对于A中m个特征描述符的任意一个都需要和B中的n个特征描述符进行比较。每次比较都会给出一个距离值，然后将得到的距离进行排序，取距离最近的一个作为匹配点。这种方法简单粗暴，其结果也是显而易见的，通过前几节的演示案例，我们知道有大量的错误匹配，这就需要使用一些机制来过滤掉错误的匹配。比如我们对匹配点按照距离来排序，并指定一个距离阈值，过滤掉一些匹配距离较远的点。<br>![<a href=\"https://img2018.cnblogs.com/blog/1328274/201809/1328274-20180914151318761-1478858833.png\">https://img2018.cnblogs.com/blog/1328274/201809/1328274-20180914151318761-1478858833.png</a>)</p>\r\n            ', 1, '2020-05-14 15:55:23', '2020-05-14 15:55:28');
INSERT INTO `t_blog_article` VALUES (112, 'asssas', 'sasasas', 0, '2020-05-17 19:13:08', NULL);
INSERT INTO `t_blog_article` VALUES (113, 'asssssssss', 'asaaaaaaaaaa', 0, '2020-05-17 19:16:15', NULL);
INSERT INTO `t_blog_article` VALUES (114, '窗前明月光', '**我是一个小乌龟**\n****上色', 0, '2020-05-17 19:17:43', NULL);
INSERT INTO `t_blog_article` VALUES (115, '测试', '# **啊啊啊啊啊啊啊啊啊啊啊啊**\n## ****钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱七星****\n### 水水水水水水水水水水水水水水水水水水水\n### 是顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶', 0, '2020-05-17 19:20:37', NULL);
INSERT INTO `t_blog_article` VALUES (116, '大数据平台400版本发版', '# 发版内容\n## 新增功能\n新增功能1\n新增功能2\n新增功能3\n新增功能4\n## 修复bug\n修复bug1\n修复bug2\n修复bug3\n', 0, '2020-08-26 12:12:07', '2020-08-28 15:03:58');

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 3072 kB' ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 3072 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_blog_comment
-- ----------------------------
INSERT INTO `t_blog_comment` VALUES (1, 1, '评论1', '2020-04-16 20:33:34', '2020-04-16 20:33:36', 1);
INSERT INTO `t_blog_comment` VALUES (2, 12, '评论测试', '2020-05-14 18:59:55', '2020-05-13 18:59:58', 1);
INSERT INTO `t_blog_comment` VALUES (3, 12, '评论测试', '2020-05-14 18:59:55', '2020-05-13 18:59:58', 2);
INSERT INTO `t_blog_comment` VALUES (4, 12, '我来测试', '2020-05-04 13:04:37', NULL, 1);
INSERT INTO `t_blog_comment` VALUES (6, 12, '新增一条评论', '2020-05-04 14:52:56', NULL, 1);
INSERT INTO `t_blog_comment` VALUES (7, 11, '你是一个垃圾', '2020-05-05 19:20:16', NULL, 1);

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 3072 kB' ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 3072 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_blog_reply
-- ----------------------------
INSERT INTO `t_blog_reply` VALUES (1, '回复1', '2020-04-16 20:33:13', '2020-04-16 20:33:10', 1, 1, 1);
INSERT INTO `t_blog_reply` VALUES (4, '我来评论', '2020-05-04 15:17:12', NULL, 12, 6, 1);

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
INSERT INTO `t_blog_request_path` VALUES (1, '/user/list1', '用户查询接口');

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
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 3072 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_blog_tag
-- ----------------------------
INSERT INTO `t_blog_tag` VALUES (1, 'java', 1);
INSERT INTO `t_blog_tag` VALUES (2, 'c++', 2);
INSERT INTO `t_blog_tag` VALUES (3, 'a', 3);
INSERT INTO `t_blog_tag` VALUES (4, 'aaaaaaaaaaaaaaaaaaaaaaaaaa', 4);
INSERT INTO `t_blog_tag` VALUES (5, 'asdddddddddddddddddddddddddddddsssffffffffffffffffff这是一个测hi是是是是飒飒阿萨飒飒飒飒阿萨撒水水水水水水水水水水水水水水水水水水', 4);
INSERT INTO `t_blog_tag` VALUES (6, 'c++', 10);
INSERT INTO `t_blog_tag` VALUES (7, 'c++', 112);
INSERT INTO `t_blog_tag` VALUES (8, 'c++', 113);
INSERT INTO `t_blog_tag` VALUES (9, 'c++', 114);
INSERT INTO `t_blog_tag` VALUES (10, 'java', 115);
INSERT INTO `t_blog_tag` VALUES (11, 'aaaaaaaaaaaaaaaaaaaaaaaaaa', 115);
INSERT INTO `t_blog_tag` VALUES (12, 'asdddddddddddddddddddddddddddddsssffffffffffffffffff这是一个测hi是是是是飒飒阿萨飒飒飒飒阿萨撒水水水水水水水水水水水水水水水水水水', 115);
INSERT INTO `t_blog_tag` VALUES (16, 'c++', 116);

-- ----------------------------
-- Table structure for t_blog_user
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_user`;
CREATE TABLE `t_blog_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '通过 bcrypt 加密后的密码',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮件',
  `notice` tinyint(1) NOT NULL DEFAULT 1 COMMENT '邮件通知：0不通知，1通知',
  `github` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT 'github账号',
  `disabledDiscuss` tinyint(1) NOT NULL DEFAULT 0 COMMENT '禁言：0不禁言，1禁言',
  `createdAt` datetime(0) DEFAULT NULL,
  `updatedAt` datetime(0) DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'logo',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 3072 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_blog_user
-- ----------------------------
INSERT INTO `t_blog_user` VALUES (3, 'liuyan', 'FDyjVY6P87Su04nUfUGQSw==', '18151521911@163.com', 1, NULL, 1, '2020-04-15 21:52:39', '2020-05-10 18:12:48', NULL);
INSERT INTO `t_blog_user` VALUES (4, 'zhengyang4', 'FDyjVY6P87Su04nUfUGQSw==', '18151521911@163.com', 1, NULL, 1, '2020-05-10 18:57:51', '2020-05-10 19:44:51', NULL);
INSERT INTO `t_blog_user` VALUES (5, 'zhengyang1', 'FDyjVY6P87Su04nUfUGQSw==', '18151521911@163.com', 1, NULL, 0, '2020-05-10 18:53:24', '2020-05-10 20:08:49', NULL);
INSERT INTO `t_blog_user` VALUES (6, 'zhengyang2', 'FDyjVY6P87Su04nUfUGQSw==', '18151521911@163.com', 1, NULL, 0, '2020-05-10 18:55:31', NULL, NULL);
INSERT INTO `t_blog_user` VALUES (8, 'zhengyang5', 'FDyjVY6P87Su04nUfUGQSw==', '18151521911@163.com', 0, NULL, 1, '2020-05-10 18:57:51', '2020-05-10 19:44:53', NULL);
INSERT INTO `t_blog_user` VALUES (9, 'zhengyang6', 'FDyjVY6P87Su04nUfUGQSw==', '18151521911@163.com', 1, NULL, 1, '2020-05-10 18:57:51', '2020-05-10 20:39:26', NULL);
INSERT INTO `t_blog_user` VALUES (10, 'zhengyang7', 'FDyjVY6P87Su04nUfUGQSw==', '18151521911@163.com', 1, NULL, 0, '2020-05-10 18:57:51', '2020-05-10 20:08:43', NULL);
INSERT INTO `t_blog_user` VALUES (11, 'zhengyang8', 'FDyjVY6P87Su04nUfUGQSw==', '18151521911@163.com', 1, NULL, 0, '2020-05-10 18:57:51', '2020-07-31 18:56:34', NULL);
INSERT INTO `t_blog_user` VALUES (12, 'zy', 'FDyjVY6P87Su04nUfUGQSw==', 'Gw@082033', 0, 'Zhengyang550', 0, '2020-05-11 23:20:04', '2020-07-31 18:56:32', 'https://avatars3.githubusercontent.com/u/52303982?v=4');
INSERT INTO `t_blog_user` VALUES (14, 'admin', 'y/cMFgrDdaKaaHP77bj3mg==', '18151521911@163.com', 0, NULL, 0, '2020-05-12 22:14:45', '2020-07-31 18:56:33', NULL);

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
INSERT INTO `t_blog_user_role_mapping` VALUES (1, 1);
INSERT INTO `t_blog_user_role_mapping` VALUES (2, 1);

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime(0) NOT NULL,
  `log_modified` datetime(0) NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
