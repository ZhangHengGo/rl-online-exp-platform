/*
 Navicat Premium Data Transfer

 Source Server         : newrl
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : rl

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 24/06/2024 19:13:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for alg_data
-- ----------------------------
DROP TABLE IF EXISTS `alg_data`;
CREATE TABLE `alg_data`  (
  `id` int NOT NULL,
  `data_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `alg_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of alg_data
-- ----------------------------
INSERT INTO `alg_data` VALUES (1, 'a', 1);
INSERT INTO `alg_data` VALUES (2, 'b', 1);
INSERT INTO `alg_data` VALUES (3, 'c', 1);

-- ----------------------------
-- Table structure for container
-- ----------------------------
DROP TABLE IF EXISTS `container`;
CREATE TABLE `container`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `image_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of container
-- ----------------------------
INSERT INTO `container` VALUES (1, 'container1', 1);
INSERT INTO `container` VALUES (2, 'container2', 1);
INSERT INTO `container` VALUES (3, 'container3', 1);

-- ----------------------------
-- Table structure for datas
-- ----------------------------
DROP TABLE IF EXISTS `datas`;
CREATE TABLE `datas`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `exp_id` int NULL DEFAULT NULL,
  `episode_id` int NULL DEFAULT NULL,
  `shap` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `movement_decision` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `qvalue` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `value_function` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `reward_signal` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `learning_curve` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of datas
-- ----------------------------
INSERT INTO `datas` VALUES (1, 3, 1, '100,110,23,45,67,68', '0,1,1,1,0,0,1,0,1,1,0,0,1,0,1,1,0,1,0,0,1,1', '[0,0,0.1],[0,1,0.2],[0,2,0.3],[0,3,0.4],[0,4,0.2],[0,5,0.9],[0,6,0.6],[0,7,0.4],[0,8,0.5],[0,9,0.5],[0,10,0.2],[0,11,0.1],[0,12,0.4],[0,13,0.1],[0,14,0.1],[0,15,0.3],[0,16,0.4],[0,17,0.6],[0,18,0.4],[0,19,0.4],[0,20,0.3],[0,21,3],[1,0,7],[1,1,0],[1,2,0],[1,3,0],[1,4,0],[1,5,0],[1,6,0],[1,0,0.2279],[1,1,0.227982223],[1,2,0.3],[1,3,0.4],[1,4,0.2],[1,5,0.6],[1,6,0.2],[1,7,0.8],[1,8,0.4],[1,9,0.3],[1,10,0.2],[1,11,0.5],[1,11,0.4],[1,12,1],[1,13,0.5],[1,14,0.7],[1,15,0.2],[1,16,0.7],[1,17,0.2],[1,18,0.7],[1,19,0.2],[1,20,0.7]', '2.196279287,2.278737545,2.192631483,2.275805235,2.190392017,2.274099827,2.189518929,2.273584127,2.349901199,2.273584127,2.349901199,2.272446871,2.190495491,2.274968863,2.194493771,2.274968863,2.190392017,2.274099827,2.189518929,2.273584127,2.349901199,', '2,4,6,8,10,12,14', '56, 67, 224, 134, 90, 147, 260');
INSERT INTO `datas` VALUES (2, 3, 2, '100,110,23,45,67,68', '100,110,23,45,67,68', '[0,0,0.1],[0,1,0.2],[0,2,0.3],[0,3,0.4],[0,4,0.2],[0,5,0.9],[0,6,0.6],[0,7,0.4],[0,8,0.5],[0,9,0.5],[0,10,0.2],[0,11,0.1],[0,12,0.4],[0,13,0.1],[0,14,0.1],[0,15,0.3],[0,16,0.4],[0,17,0.6],[0,18,0.4],[0,19,0.4],[0,20,0.3],[0,21,3],[1,0,7],[1,1,0],[1,2,0],[1,3,0],[1,4,0],[1,5,0],[1,6,0],[1,0,0.2279],[1,1,0.2],[1,2,0.3],[1,3,0.4],[1,4,0.2],[1,5,0.6],[1,6,0.2],[1,7,0.8],[1,8,0.4],[1,9,0.3],[1,10,0.2],[1,11,0.5],[1,11,0.4],[1,12,1],[1,13,0.5],[1,14,0.7],[1,15,0.2],[1,16,0.7],[1,17,0.2],[1,18,0.7],[1,19,0.2],[1,20,0.7],[1,19,0.2],[1,20,0.7]', '100,110,23,45,67,68', '2,4,6,8,10,12,14', '56, 67, 224, 134, 90, 147, 260');
INSERT INTO `datas` VALUES (3, 1, 1, '100,110,23,45,67,68', '0,1,1,1,0,0,1,0,1,1,0,0,1,0,1,1,0,1,0,0,1,1', '[0,0,0.1],[0,1,0.2],[0,2,0.3],[0,3,0.4],[0,4,0.2],[0,5,0.9],[0,6,0.6],[0,7,0.4],[0,8,0.5],[0,9,0.5],[0,10,0.2],[0,11,0.1],[0,12,0.4],[0,13,0.1],[0,14,0.1],[0,15,0.3],[0,16,0.4],[0,17,0.6],[0,18,0.4],[0,19,0.4],[0,20,0.3],[0,21,3],[1,0,7],[1,1,0],[1,2,0],[1,3,0],[1,4,0],[1,5,0],[1,6,0],[1,0,0.2279],[1,1,0.2],[1,2,0.3],[1,3,0.4],[1,4,0.2],[1,5,0.6],[1,6,0.2],[1,7,0.8],[1,8,0.4],[1,9,0.3],[1,10,0.2],[1,11,0.5],[1,11,0.4],[1,12,1],[1,13,0.5],[1,14,0.7],[1,15,0.2],[1,16,0.7],[1,17,0.2],[1,18,0.7],[1,19,0.2],[1,20,0.7]', '2.196279287,2.278737545,2.192631483,2.275805235,2.190392017,2.274099827,2.189518929,2.273584127,2.349901199,2.273584127,2.349901199,2.272446871,2.190495491,2.274968863,2.194493771,2.274968863,2.190392017,2.274099827,2.189518929,2.273584127,2.349901199,', '2,4,6,8,10,12,14', '56, 67, 224, 134, 90, 147, 260');
INSERT INTO `datas` VALUES (4, 2, 1, '100,110,23,45,67,68', '0,1,1,1,0,0,1,0,1,1,0,0,1,0,1,1,0,1,0,0,1,1', '[0,0,0.1],[0,1,0.2],[0,2,0.3],[0,3,0.4],[0,4,0.2],[0,5,0.9],[0,6,0.6],[0,7,0.4],[0,8,0.5],[0,9,0.5],[0,10,0.2],[0,11,0.1],[0,12,0.4],[0,13,0.1],[0,14,0.1],[0,15,0.3],[0,16,0.4],[0,17,0.6],[0,18,0.4],[0,19,0.4],[0,20,0.3],[0,21,3],[1,0,7],[1,1,0],[1,2,0],[1,3,0],[1,4,0],[1,5,0],[1,6,0],[1,0,0.2279],[1,1,0.227982223],[1,2,0.3],[1,3,0.4],[1,4,0.2],[1,5,0.6],[1,6,0.2],[1,7,0.8],[1,8,0.4],[1,9,0.3],[1,10,0.2],[1,11,0.5],[1,11,0.4],[1,12,1],[1,13,0.5],[1,14,0.7],[1,15,0.2],[1,16,0.7],[1,17,0.2],[1,18,0.7],[1,19,0.2],[1,20,0.7]', '2.196279287,2.278737545,2.192631483,2.275805235,2.190392017,2.274099827,2.189518929,2.273584127,2.349901199,2.273584127,2.349901199,2.272446871,2.190495491,2.274968863,2.194493771,2.274968863,2.190392017,2.274099827,2.189518929,2.273584127,2.349901199,', '2,4,6,8,10,12,14', '56, 67, 224, 134, 90, 147, 260');
INSERT INTO `datas` VALUES (5, 1, 2, '10,180,80,49,67,68', '0,1,1,1,0,0,1,0,1,1,0,0,1,0,1,1,0,1,0,0,1,1', '[0,0,0.1],[0,1,0.2],[0,2,0.3],[0,3,0.4],[0,4,0.2],[0,5,0.9],[0,6,0.6],[0,7,0.4],[0,8,0.5],[0,9,0.5],[0,10,0.2],[0,11,0.1],[0,12,0.4],[0,13,0.1],[0,14,0.1],[0,15,0.3],[0,16,0.4],[0,17,0.6],[0,18,0.4],[0,19,0.4],[0,20,0.3],[0,21,3],[1,0,7],[1,1,0],[1,2,0],[1,3,0],[1,4,0],[1,5,0],[1,6,0],[1,0,0.2279],[1,1,0.227982223],[1,2,0.3],[1,3,0.4],[1,4,0.2],[1,5,0.6],[1,6,0.2],[1,7,0.8],[1,8,0.4],[1,9,0.3],[1,10,0.2],[1,11,0.5],[1,11,0.4],[1,12,1],[1,13,0.5],[1,14,0.7],[1,15,0.2],[1,16,0.7],[1,17,0.2],[1,18,0.7],[1,19,0.2],[1,20,0.7]', '2.196279287,2.278737545,2.192631483,2.275805235,2.190392017,2.274099827,2.189518929,2.273584127,2.349901199,2.273584127,2.349901199,2.272446871,2.190495491,2.274968863,2.194493771,2.274968863,2.190392017,2.274099827,2.189518929,2.273584127,2.349901199,', '2,4,6,8,10,12,14', '56, 67, 224, 134, 90, 147, 260');
INSERT INTO `datas` VALUES (6, 3, 3, '10,160,213,45,67,68', '100,110,23,45,67,68', '[0,0,0.1],[0,1,0.2],[0,2,0.3],[0,3,0.4],[0,4,0.2],[0,5,0.9],[0,6,0.6],[0,7,0.4],[0,8,0.5],[0,9,0.5],[0,10,0.2],[0,11,0.1],[0,12,0.4],[0,13,0.1],[0,14,0.1],[0,15,0.3],[0,16,0.4],[0,17,0.6],[0,18,0.4],[0,19,0.4],[0,20,0.3],[0,21,3],[1,0,7],[1,1,0],[1,2,0],[1,3,0],[1,4,0],[1,5,0],[1,6,0],[1,0,0.2279],[1,1,0.227982223],[1,2,0.3],[1,3,0.4],[1,4,0.2],[1,5,0.6],[1,6,0.2],[1,7,0.8],[1,8,0.4],[1,9,0.3],[1,10,0.2],[1,11,0.5],[1,11,0.4],[1,12,1],[1,13,0.5],[1,14,0.7],[1,15,0.2],[1,16,0.7],[1,17,0.2],[1,18,0.7],', '100,110,23,45,67,68', '2,4,6,8,10,12,14', '56, 67, 224, 134, 90, 147, 260');
INSERT INTO `datas` VALUES (7, 1, 3, '60,10,83,49,67,68', '0,1,1,1,0,0,1,0,1,1,0,0,1,0,1,1,0,1,0,0,1,1', '[0,0,0.1],[0,1,0.2],[0,2,0.3],[0,3,0.4],[0,4,0.2],[0,5,0.9],[0,6,0.6],[0,7,0.4],[0,8,0.5],[0,9,0.5],[0,10,0.2],[0,11,0.1],[0,12,0.4],[0,13,0.1],[0,14,0.1],[0,15,0.3],[0,16,0.4],[0,17,0.6],[0,18,0.4],[0,19,0.4],[0,20,0.3],[0,21,3],[1,0,7],[1,1,0],[1,2,0],[1,3,0],[1,4,0],[1,5,0],[1,6,0],[1,0,0.2279],[1,1,0.227982223],[1,2,0.3],[1,3,0.4],[1,4,0.2],[1,5,0.6],[1,6,0.2],[1,7,0.8],[1,8,0.4],[1,9,0.3],[1,10,0.2],[1,11,0.5],[1,11,0.4],[1,12,1],[1,13,0.5],[1,14,0.7],[1,15,0.2],[1,16,0.7],[1,17,0.2],[1,18,0.7],[1,19,0.2],[1,20,0.7]', '2.196279287,2.278737545,2.192631483,2.275805235,2.190392017,2.274099827,2.189518929,2.273584127,2.349901199,2.273584127,2.349901199,2.272446871,2.190495491,2.274968863,2.194493771,2.274968863,2.190392017,2.274099827,2.189518929,2.273584127,2.349901199,', '2,4,6,8,10,12,14', '56, 67, 224, 134, 90, 147, 260');

-- ----------------------------
-- Table structure for echarts
-- ----------------------------
DROP TABLE IF EXISTS `echarts`;
CREATE TABLE `echarts`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `search_seat_count` bigint NULL DEFAULT 0,
  `login_count` bigint NULL DEFAULT 0,
  `one_floor_count` bigint NULL DEFAULT 0,
  `two_floor_count` bigint NULL DEFAULT 0,
  `three_floor_count` bigint NULL DEFAULT 0,
  `four_floor_count` bigint NULL DEFAULT 0,
  `five_floor_count` bigint NULL DEFAULT 0,
  `nine_time_count` bigint NULL DEFAULT 0,
  `ten_time_count` bigint NULL DEFAULT 0,
  `eleven_time_count` bigint NULL DEFAULT 0,
  `twelve_time_count` bigint NULL DEFAULT 0,
  `thirteen_time_count` bigint NULL DEFAULT 0,
  `fourteen_time_count` bigint NULL DEFAULT 0,
  `fifteen_time_count` bigint NULL DEFAULT 0,
  `sixteen_time_count` bigint NULL DEFAULT 0,
  `seventeen_time_count` bigint NULL DEFAULT 0,
  `eighteen_time_count` bigint NULL DEFAULT 0,
  `nineteen_time_count` bigint NULL DEFAULT 0,
  `twenty_time_count` bigint NULL DEFAULT 0,
  `twenty_one_time_count` bigint NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of echarts
-- ----------------------------
INSERT INTO `echarts` VALUES (1, 2, 24, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

-- ----------------------------
-- Table structure for env
-- ----------------------------
DROP TABLE IF EXISTS `env`;
CREATE TABLE `env`  (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` tinyint NULL DEFAULT NULL COMMENT '0：单智能体 1：多智能体',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of env
-- ----------------------------
INSERT INTO `env` VALUES (1, '环境1', 0);

-- ----------------------------
-- Table structure for env_parameter
-- ----------------------------
DROP TABLE IF EXISTS `env_parameter`;
CREATE TABLE `env_parameter`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` tinyint NULL DEFAULT NULL COMMENT '0：数值型 1：字符型 2：布尔型 3：节点型',
  `env_id` int NULL DEFAULT NULL,
  `parent_id` int NULL DEFAULT NULL,
  `min` double NULL DEFAULT NULL,
  `max` double NULL DEFAULT NULL,
  `options` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of env_parameter
-- ----------------------------
INSERT INTO `env_parameter` VALUES (1, 'seed', 0, 1, NULL, 1, 5, NULL);
INSERT INTO `env_parameter` VALUES (2, 'alpha', 0, 1, NULL, 1, 5, NULL);
INSERT INTO `env_parameter` VALUES (3, 'agent', 3, 1, NULL, NULL, NULL, NULL);
INSERT INTO `env_parameter` VALUES (4, 'recurrent', 1, 1, 3, NULL, NULL, 'aa,bb');
INSERT INTO `env_parameter` VALUES (5, 'entity_scheme', 1, 1, 3, NULL, NULL, 'cc,dd');
INSERT INTO `env_parameter` VALUES (6, 'mix', 2, 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for episode_data
-- ----------------------------
DROP TABLE IF EXISTS `episode_data`;
CREATE TABLE `episode_data`  (
  `id` int NOT NULL,
  `exp_id` int NULL DEFAULT NULL,
  `episode_id` int NULL DEFAULT NULL,
  `data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of episode_data
-- ----------------------------

-- ----------------------------
-- Table structure for exp_data
-- ----------------------------
DROP TABLE IF EXISTS `exp_data`;
CREATE TABLE `exp_data`  (
  `id` int NOT NULL,
  `alg_data_id` int NULL DEFAULT NULL,
  `exp_id` int NULL DEFAULT NULL,
  `data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exp_data
-- ----------------------------
INSERT INTO `exp_data` VALUES (1, 1, 1, '1,2,3');
INSERT INTO `exp_data` VALUES (2, 2, 1, '4,5,6');
INSERT INTO `exp_data` VALUES (3, 3, 1, '7,8,9');
INSERT INTO `exp_data` VALUES (4, 1, 2, '11,12,13');
INSERT INTO `exp_data` VALUES (5, 2, 2, '14,15,16');
INSERT INTO `exp_data` VALUES (6, 3, 2, '17,18,19');

-- ----------------------------
-- Table structure for exp_parameter
-- ----------------------------
DROP TABLE IF EXISTS `exp_parameter`;
CREATE TABLE `exp_parameter`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `exp_id` int NULL DEFAULT NULL,
  `num_value` float NULL DEFAULT NULL,
  `string_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `parent` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of exp_parameter
-- ----------------------------

-- ----------------------------
-- Table structure for experiment
-- ----------------------------
DROP TABLE IF EXISTS `experiment`;
CREATE TABLE `experiment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `alg_id` int NULL DEFAULT NULL,
  `creat_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL COMMENT '0：运行中 1：已完成',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of experiment
-- ----------------------------
INSERT INTO `experiment` VALUES (1, 1, 1, NULL, NULL, '实验1');
INSERT INTO `experiment` VALUES (2, 1, 1, NULL, NULL, '实验2');
INSERT INTO `experiment` VALUES (3, 1, 1, NULL, NULL, '实验3');
INSERT INTO `experiment` VALUES (4, 1, 1, NULL, NULL, '实验4');

-- ----------------------------
-- Table structure for experiment0
-- ----------------------------
DROP TABLE IF EXISTS `experiment0`;
CREATE TABLE `experiment0`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `seed` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `env_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `learning_rate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `input_size` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of experiment0
-- ----------------------------
INSERT INTO `experiment0` VALUES (1, 0, '实验2', '正常', '188', 'CartPole-v1', '0.001', '1500', 'default exp info', '2023-09-11 19:22:09', '2023-12-03 22:00:53');
INSERT INTO `experiment0` VALUES (2, 0, '实验1', '正常', '44', 'CartPole-v1', '0.001', '1500', 'default exp info', '2023-09-11 20:16:20', '2023-12-03 22:00:57');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `create_time` datetime NOT NULL,
  `notice_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `notice_detail` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `is_delete` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, 0, '2023-06-30 21:02:02', '通知一', '请同学们尽快完成实验更改实验状态', NULL);
INSERT INTO `notice` VALUES (2, 0, '2023-07-02 11:22:46', '通知二', '这是第二个通知啦啦啦啦啦啦11111', NULL);
INSERT INTO `notice` VALUES (3, 0, '2023-09-11 14:13:15', '新通知', '这是新通知111111111111', NULL);

-- ----------------------------
-- Table structure for notice_read_history
-- ----------------------------
DROP TABLE IF EXISTS `notice_read_history`;
CREATE TABLE `notice_read_history`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `notice_id` int NOT NULL,
  `read_status` tinyint NULL DEFAULT NULL,
  `read_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice_read_history
-- ----------------------------
INSERT INTO `notice_read_history` VALUES (3, 2, 2, NULL, NULL);
INSERT INTO `notice_read_history` VALUES (4, 3, 2, NULL, NULL);
INSERT INTO `notice_read_history` VALUES (5, 2, 3, NULL, NULL);
INSERT INTO `notice_read_history` VALUES (6, 3, 3, NULL, NULL);

-- ----------------------------
-- Table structure for reserve_seat
-- ----------------------------
DROP TABLE IF EXISTS `reserve_seat`;
CREATE TABLE `reserve_seat`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `seat_id` int NOT NULL,
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `seat_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `begin_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `end_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_delete` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of reserve_seat
-- ----------------------------

-- ----------------------------
-- Table structure for seat
-- ----------------------------
DROP TABLE IF EXISTS `seat`;
CREATE TABLE `seat`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `seat_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `seat_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '空闲中',
  `use_count` bigint NULL DEFAULT 0,
  `create_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_delete` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of seat
-- ----------------------------

-- ----------------------------
-- Table structure for student_info
-- ----------------------------
DROP TABLE IF EXISTS `student_info`;
CREATE TABLE `student_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student_info
-- ----------------------------
INSERT INTO `student_info` VALUES (4, '花花', '女');
INSERT INTO `student_info` VALUES (5, '嘻嘻', '女');

-- ----------------------------
-- Table structure for test_detail
-- ----------------------------
DROP TABLE IF EXISTS `test_detail`;
CREATE TABLE `test_detail`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `parent_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `test_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `test_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `seed` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `env_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `learning_rate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `input_size` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `episode` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of test_detail
-- ----------------------------
INSERT INTO `test_detail` VALUES (1, '2', 'test', '小张', '第一次实验', '正常', '1', '2', '3', '4', '第一次实验', '2023-10-15 22:48:20', '2023-12-15 10:53:02', 3);
INSERT INTO `test_detail` VALUES (2, '1', 'test', '小王', '第一次实验', '正常', '2', '3', '3', '4', '第一次实验', '2023-10-15 22:49:54', '2023-12-15 10:53:05', 5);
INSERT INTO `test_detail` VALUES (3, '1', 'test', '小张', '第二次实验', '正常', '1', '2', '3', '4', '第一次实验', '2023-10-15 22:48:20', '2023-12-15 10:53:09', 4);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `identity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '用户',
  `create_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `login_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account`(`account` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456789', 'admin', NULL, '管理员', NULL, '2024-06-15 14:13:18', '1');
INSERT INTO `user` VALUES (2, 'test', '25f9e794323b453885f5181f1b624d0b', 'test', '1234@qq.com', '用户', NULL, '2024-03-09 08:00:09', '1');
INSERT INTO `user` VALUES (3, '123456', '25f9e794323b453885f5181f1b624d0b', '小我那个', NULL, '用户', '2023-07-02 10:58:19', NULL, '0');

-- ----------------------------
-- Table structure for x_axis
-- ----------------------------
DROP TABLE IF EXISTS `x_axis`;
CREATE TABLE `x_axis`  (
  `id` int NOT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'value category time log',
  `data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '字符串：[a, b, c]',
  `exp_id` int NULL DEFAULT NULL COMMENT '对应的实验id',
  `data_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据名称，如：q值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of x_axis
-- ----------------------------

-- ----------------------------
-- Table structure for y_axis
-- ----------------------------
DROP TABLE IF EXISTS `y_axis`;
CREATE TABLE `y_axis`  (
  `id` int NOT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'value category',
  `data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `x_axis_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of y_axis
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
