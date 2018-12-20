/*
Navicat MySQL Data Transfer

Source Server         : local-server
Source Server Version : 50703
Source Host           : 127.0.0.1:3306
Source Database       : baihua

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2018-12-20 14:55:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bas_ad
-- ----------------------------
DROP TABLE IF EXISTS `bas_ad`;
CREATE TABLE `bas_ad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` text COMMENT '名称',
  `image` varchar(50) DEFAULT NULL COMMENT '图片',
  `pos_id` bigint(20) DEFAULT NULL COMMENT '广告位编号',
  `pos_name` varchar(20) DEFAULT NULL COMMENT '广告位名称',
  `terminal` smallint(6) DEFAULT NULL COMMENT '端 1 医生 2 患者',
  `status` smallint(6) DEFAULT NULL COMMENT '状态 1 显示 0 隐藏',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '删除状态 1 已删除 0 正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='广告';


-- ----------------------------
-- Table structure for bas_ad_position
-- ----------------------------
DROP TABLE IF EXISTS `bas_ad_position`;
CREATE TABLE `bas_ad_position` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `image_proportion` varchar(10) DEFAULT NULL COMMENT '图片比例',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '删除状态 1 已删除 0 正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='广告位';


-- ----------------------------
-- Table structure for bas_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `bas_dictionary`;
CREATE TABLE `bas_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `group_id` bigint(20) DEFAULT NULL COMMENT '所属条目编号 为0表示组',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '删除状态 1 已删除 0 正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='字典';


-- ----------------------------
-- Table structure for bas_disease_category
-- ----------------------------
DROP TABLE IF EXISTS `bas_disease_category`;
CREATE TABLE `bas_disease_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `describe` varchar(200) DEFAULT NULL COMMENT '描述',
  `p_id` bigint(20) DEFAULT NULL COMMENT '父级编号',
  `p_name` varchar(50) DEFAULT NULL COMMENT '父级名称',
  `p_ids` varchar(20) DEFAULT NULL COMMENT '所有父级编号，从下而上以,分割',
  `p_names` varchar(200) DEFAULT NULL COMMENT '所有父级名称，从下而上以,分割',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '删除状态 1 已删除 0 正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='疾病分类';

-- ----------------------------
-- Records of bas_disease_category
-- ----------------------------

-- ----------------------------
-- Table structure for bas_health_examination
-- ----------------------------
DROP TABLE IF EXISTS `bas_health_examination`;
CREATE TABLE `bas_health_examination` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(10) DEFAULT NULL COMMENT '名称',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `sort` smallint(6) DEFAULT NULL COMMENT '序列',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '删除状态 1 已删除 0 正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='检查';

-- ----------------------------
-- Table structure for bas_hospital
-- ----------------------------
DROP TABLE IF EXISTS `bas_hospital`;
CREATE TABLE `bas_hospital` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `total_amount` decimal(10,2) DEFAULT NULL COMMENT '总流水',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '删除状态 1 已删除 0 正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='医院';


-- ----------------------------
-- Table structure for bas_office
-- ----------------------------
DROP TABLE IF EXISTS `bas_office`;
CREATE TABLE `bas_office` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '删除状态 1 已删除 0 正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='科室';

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for ser_adept
-- ----------------------------
DROP TABLE IF EXISTS `ser_adept`;
CREATE TABLE `ser_adept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `describe` varchar(100) DEFAULT NULL COMMENT '描述',
  `ordered` smallint(6) DEFAULT NULL COMMENT '排序',
  `doc_id` bigint(20) DEFAULT NULL COMMENT '医生编号',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '删除状态 1 已删除 0 正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1075598187751825412 DEFAULT CHARSET=utf8mb4 COMMENT='擅长领域';

-- ----------------------------
-- Table structure for ser_cash_flow
-- ----------------------------
DROP TABLE IF EXISTS `ser_cash_flow`;
CREATE TABLE `ser_cash_flow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `type` smallint(6) DEFAULT NULL COMMENT '付款方式 1 微信 2 支付宝',
  `reg_id` bigint(20) DEFAULT NULL COMMENT '挂号编号',
  `hos_id` bigint(20) DEFAULT NULL COMMENT '医院编号',
  `pat_id` bigint(20) DEFAULT NULL COMMENT '患者编号',
  `doc_id` bigint(20) DEFAULT NULL COMMENT '医生编号',
  `third_id` varchar(100) DEFAULT NULL COMMENT '第三方支付编号',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '删除状态 1 已删除 0 正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='现金流水';

-- ----------------------------
-- Table structure for ser_comment
-- ----------------------------
DROP TABLE IF EXISTS `ser_comment`;
CREATE TABLE `ser_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `content` varchar(300) DEFAULT NULL COMMENT '评论内容',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '评论人',
  `patient_photo` varchar(300) DEFAULT NULL COMMENT '评论人照片',
  `patient_account` varchar(20) DEFAULT NULL COMMENT '评论人账号',
  `doctor_id` bigint(20) DEFAULT NULL COMMENT '医生编号',
  `reg_id` bigint(20) DEFAULT NULL COMMENT '挂号编号',
  `status` smallint(6) DEFAULT NULL COMMENT '审核状态 1 未审核 2 已通过 3 拒绝',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '删除状态 1 已删除 0 正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1075301192214532099 DEFAULT CHARSET=utf8mb4 COMMENT='评论';

-- ----------------------------
-- Table structure for ser_inquiry
-- ----------------------------
DROP TABLE IF EXISTS `ser_inquiry`;
CREATE TABLE `ser_inquiry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别:1 男 0 女',
  `age` smallint(6) DEFAULT NULL COMMENT '年龄',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `blood_type` smallint(6) DEFAULT NULL COMMENT '血型',
  `diet` smallint(6) DEFAULT NULL COMMENT '饮食',
  `sleep` smallint(6) DEFAULT NULL COMMENT '睡眠',
  `character_describe` varchar(500) DEFAULT NULL COMMENT '文字描述',
  `voice_describe` text COMMENT '语音描述',
  `image` varchar(500) DEFAULT NULL COMMENT '照片',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '患者编号',
  `doctor_id` bigint(20) DEFAULT NULL COMMENT '医生编号',
  `response` varchar(500) DEFAULT NULL COMMENT '诊断结果',
  `advice` varchar(100) DEFAULT NULL COMMENT '医嘱',
  `exa_ids` varchar(20) DEFAULT NULL COMMENT '检查项编号 以,隔开',
  `exa_content` varchar(500) DEFAULT NULL COMMENT '检查项内容 (json)',
  `exa_fee` decimal(10,2) DEFAULT NULL COMMENT '检查总价',
  `status` smallint(6) DEFAULT NULL COMMENT '问诊状态 1 等待抢单 2 已抢单 3 已回复',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '删除状态 1 已删除 0 正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1074855124976795650 DEFAULT CHARSET=utf8mb4 COMMENT='问诊';

-- ----------------------------
-- Table structure for ser_inquiry_match
-- ----------------------------
DROP TABLE IF EXISTS `ser_inquiry_match`;
CREATE TABLE `ser_inquiry_match` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `pat_id` bigint(20) DEFAULT NULL COMMENT '患者编号',
  `doc_id` bigint(20) DEFAULT NULL COMMENT '医生编号',
  `inquiry_id` bigint(20) DEFAULT NULL COMMENT '问诊编号',
  `match_num` int(11) DEFAULT NULL COMMENT '匹配度',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '删除状态 1 已删除 0 正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会话历史';

-- ----------------------------
-- Records of ser_inquiry_match
-- ----------------------------

-- ----------------------------
-- Table structure for ser_registration
-- ----------------------------
DROP TABLE IF EXISTS `ser_registration`;
CREATE TABLE `ser_registration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别:1 男 2 女',
  `age` smallint(6) DEFAULT NULL COMMENT '年龄',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `visit_time` date DEFAULT NULL COMMENT '挂号时间',
  `time_part` tinyint(4) DEFAULT NULL COMMENT '时间区间   0 上午 1 下午',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '患者编号',
  `doctor_id` bigint(20) DEFAULT NULL COMMENT '医生编号',
  `schedule_id` bigint(20) DEFAULT NULL COMMENT '出诊时间表编号',
  `fee` decimal(10,2) DEFAULT NULL COMMENT '费用',
  `status` smallint(6) DEFAULT NULL COMMENT '状态 1 未支付 2 已支付',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '删除状态 1 已删除 0 正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1075243957669142530 DEFAULT CHARSET=utf8mb4 COMMENT='挂号';

-- ----------------------------
-- Table structure for ser_schedule
-- ----------------------------
DROP TABLE IF EXISTS `ser_schedule`;
CREATE TABLE `ser_schedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `date` date DEFAULT NULL COMMENT '出诊日期',
  `morning_begin` time DEFAULT NULL COMMENT '上午开始时间',
  `monring_end` time DEFAULT NULL COMMENT '上午结束时间',
  `morning_total_num` smallint(6) DEFAULT NULL COMMENT '上午接诊人数',
  `morning_remain_num` smallint(6) DEFAULT NULL COMMENT '上午接诊剩余人数',
  `afternoon_begin` time DEFAULT NULL COMMENT '下午开始时间',
  `afternoon_end` time DEFAULT NULL COMMENT '下午结束时间',
  `afternoon_total_num` smallint(6) DEFAULT NULL COMMENT '下午接诊人数',
  `afternoon_remain_num` smallint(6) DEFAULT NULL COMMENT '下午接诊剩余人数',
  `doctor_id` bigint(20) DEFAULT NULL COMMENT '医生编号',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '删除状态 1 已删除 0 正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1075328363259768834 DEFAULT CHARSET=utf8mb4 COMMENT='出诊时间表';

-- ----------------------------
-- Table structure for ser_session
-- ----------------------------
DROP TABLE IF EXISTS `ser_session`;
CREATE TABLE `ser_session` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `pat_id` bigint(20) DEFAULT NULL COMMENT '患者编号',
  `doc_id` bigint(20) DEFAULT NULL COMMENT '医生编号',
  `record` varchar(50) DEFAULT NULL COMMENT '聊天记录',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '删除状态 1 已删除 0 正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会话历史';

-- ----------------------------
-- Records of ser_session
-- ----------------------------

-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统验证码';


-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', '0', '云存储配置信息');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'system', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '管理员列表', 'sys/user', null, '1', 'admin', '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'sys/role', null, '1', 'role', '2');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'sys/menu', null, '1', 'menu', '3');
INSERT INTO `sys_menu` VALUES ('5', '1', 'SQL监控', 'http://localhost:8080/renren-fast/druid/sql.html', null, '1', 'sql', '4');
INSERT INTO `sys_menu` VALUES ('6', '1', '定时任务', 'job/schedule', null, '1', 'job', '5');
INSERT INTO `sys_menu` VALUES ('7', '6', '查看', null, 'sys:schedule:list,sys:schedule:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('8', '6', '新增', null, 'sys:schedule:save', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('9', '6', '修改', null, 'sys:schedule:update', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('10', '6', '删除', null, 'sys:schedule:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('11', '6', '暂停', null, 'sys:schedule:pause', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('12', '6', '恢复', null, 'sys:schedule:resume', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('13', '6', '立即执行', null, 'sys:schedule:run', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('14', '6', '日志列表', null, 'sys:schedule:log', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:list', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:list', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('27', '1', '参数管理', 'sys/config', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', '1', 'config', '6');
INSERT INTO `sys_menu` VALUES ('29', '1', '系统日志', 'sys/log', 'sys:log:list', '1', 'log', '7');
INSERT INTO `sys_menu` VALUES ('30', '1', '文件上传', 'oss/oss', 'sys:oss:all', '1', 'oss', '6');

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件上传';

-- ----------------------------
-- Records of sys_oss
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', '1', '1', '2016-11-11 11:11:11');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户Token';

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------

-- ----------------------------
-- Table structure for us_account
-- ----------------------------
DROP TABLE IF EXISTS `us_account`;
CREATE TABLE `us_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `account` varchar(20) DEFAULT NULL COMMENT '账号',
  `pwd` char(64) DEFAULT NULL COMMENT '密码',
  `salt` char(50) DEFAULT NULL COMMENT '盐',
  `type` smallint(6) DEFAULT NULL COMMENT '账户类型:1 医生 2 患者',
  `s_id` bigint(20) DEFAULT NULL COMMENT '账户主体编号',
  `s_name` varchar(10) DEFAULT NULL COMMENT '账户主体名称',
  `status` smallint(6) DEFAULT NULL COMMENT '账户状态 1 待激活 2 正常 3 冻结',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '删除状态 1 已删除 0 正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1075336264825286658 DEFAULT CHARSET=utf8mb4 COMMENT='账号';

-- ----------------------------
-- Table structure for us_doctor
-- ----------------------------
DROP TABLE IF EXISTS `us_doctor`;
CREATE TABLE `us_doctor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别:1 男 0 女',
  `off_id` bigint(20) DEFAULT NULL COMMENT '科室编号',
  `off_name` varchar(50) DEFAULT NULL COMMENT '科室名称',
  `position_id` bigint(20) DEFAULT NULL COMMENT '职位编号',
  `position_name` varchar(100) DEFAULT NULL COMMENT '职位名称',
  `hos_id` bigint(20) DEFAULT NULL COMMENT '医院编号',
  `hos_name` varchar(100) DEFAULT NULL COMMENT '医院名称',
  `photo` varchar(400) DEFAULT NULL COMMENT '个人照片',
  `physician_licence` varchar(150) DEFAULT NULL COMMENT '医师资格证',
  `identity_card` varchar(300) DEFAULT NULL COMMENT '身份证 正反以,分割',
  `major` varchar(100) DEFAULT NULL COMMENT '主治',
  `experience` varchar(500) DEFAULT NULL COMMENT '治疗经历',
  `registration_fee` decimal(10,2) DEFAULT NULL COMMENT '挂号费',
  `appointment_num` smallint(6) DEFAULT NULL COMMENT '预约数量',
  `status` smallint(6) DEFAULT NULL COMMENT '状态 1 未审核 2 审核',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '删除状态 1 已删除 0 正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1075336264762372098 DEFAULT CHARSET=utf8mb4 COMMENT='医生';


-- ----------------------------
-- Table structure for us_patient
-- ----------------------------
DROP TABLE IF EXISTS `us_patient`;
CREATE TABLE `us_patient` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别:1 男 0 女',
  `photo` varchar(200) DEFAULT NULL COMMENT '个人照片',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '删除状态 1 已删除 0 正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='患者';

-- ----------------------------
-- Table structure for us_token
-- ----------------------------
DROP TABLE IF EXISTS `us_token`;
CREATE TABLE `us_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `token` varchar(32) DEFAULT NULL COMMENT 'token',
  `account_id` bigint(20) DEFAULT NULL COMMENT '用户账号',
  `expire_time` timestamp NULL DEFAULT NULL COMMENT '过期时间',
  `last_time` timestamp NULL DEFAULT NULL COMMENT '最后访问时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



