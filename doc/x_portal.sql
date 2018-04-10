# Host: localhost  (Version 5.5.28)
# Date: 2018-04-11 01:07:13
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "mc_address"
#

DROP TABLE IF EXISTS `mc_address`;
CREATE TABLE `mc_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(8) DEFAULT '0' COMMENT '分类',
  `coordinate` varchar(100) DEFAULT '0' COMMENT '卫星坐标',
  `is_defined` tinyint(4) DEFAULT '0' COMMENT '是否自定义：0自定义，1划分',
  `def_address` varchar(400) DEFAULT NULL COMMENT '自定义地址',
  `undef_nation` varchar(8) DEFAULT '86' COMMENT '国',
  `undef_province` varchar(8) DEFAULT NULL COMMENT '省',
  `undef_city` varchar(8) DEFAULT NULL COMMENT '市',
  `undef_county` varchar(8) DEFAULT NULL COMMENT '县',
  `undef_town` varchar(8) DEFAULT NULL COMMENT '乡',
  `undef_village` varchar(8) DEFAULT NULL COMMENT '村',
  `undef_house` varchar(8) DEFAULT NULL COMMENT '户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='详细地址';

#
# Data for table "mc_address"
#


#
# Structure for table "mc_device"
#

DROP TABLE IF EXISTS `mc_device`;
CREATE TABLE `mc_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dev_type` varchar(6) NOT NULL COMMENT '设备类别',
  `dev_macid` varchar(100) NOT NULL COMMENT 'mac地址-唯一标识',
  `dev_name_zh` varchar(100) DEFAULT NULL COMMENT '设备中文名称',
  `dev_name_en` varchar(100) DEFAULT NULL COMMENT '设备英文名称',
  `user_id` bigint(20) DEFAULT NULL COMMENT 'MC用户',
  `address_id` bigint(20) DEFAULT NULL COMMENT '关联address表id',
  `dev_info_id` varchar(6) DEFAULT NULL COMMENT '设备详细参数信息',
  `dev_status` varchar(6) DEFAULT '0' COMMENT '设备状态',
  `dev_mode` varchar(6) DEFAULT NULL COMMENT '设备模式',
  `electricity` varchar(10) DEFAULT '0' COMMENT '电量%',
  `status_wifi` varchar(6) DEFAULT '0' COMMENT 'WIFI状态',
  `status_bluetooth` varchar(6) DEFAULT '0' COMMENT '蓝牙状态',
  `status_voice` varchar(4) DEFAULT '0' COMMENT '声音状态',
  `status_switch` varchar(4) DEFAULT '0' COMMENT '开关状态',
  `public_extend_param1` varchar(100) DEFAULT NULL COMMENT '公共扩展参数1',
  `public_extend_param2` varchar(100) DEFAULT NULL COMMENT '公共扩展参数2',
  `public_extend_param3` varchar(100) DEFAULT NULL COMMENT '公共扩展参数3',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='MC设备 （此表只记录公共参数）';

#
# Data for table "mc_device"
#


#
# Structure for table "mc_device_info"
#

DROP TABLE IF EXISTS `mc_device_info`;
CREATE TABLE `mc_device_info` (
  `dev_id` bigint(20) NOT NULL COMMENT '设备标识',
  `parameter1` varchar(50) DEFAULT NULL COMMENT '参数',
  `parameter2` varchar(50) DEFAULT NULL COMMENT '参数',
  `parameter3` varchar(50) DEFAULT NULL COMMENT '参数',
  `parameter4` varchar(50) DEFAULT NULL COMMENT '参数',
  `parameter5` varchar(50) DEFAULT NULL COMMENT '参数',
  `parameter6` varchar(50) DEFAULT NULL COMMENT '参数',
  `parameter7` varchar(50) DEFAULT NULL COMMENT '参数',
  `parameter8` varchar(50) DEFAULT NULL COMMENT '参数',
  `parameter9` varchar(50) DEFAULT NULL COMMENT '参数',
  `parameter10` varchar(50) DEFAULT NULL COMMENT '参数',
  `parameter11` varchar(50) DEFAULT NULL COMMENT '参数',
  `parameter12` varchar(50) DEFAULT NULL COMMENT '参数',
  `parameter13` varchar(50) DEFAULT NULL COMMENT '参数',
  `parameter14` varchar(50) DEFAULT NULL COMMENT '参数',
  `parameter15` varchar(50) DEFAULT NULL COMMENT '参数',
  `parameter16` varchar(50) DEFAULT NULL COMMENT '参数',
  `parameter17` varchar(50) DEFAULT NULL COMMENT '参数',
  `parameter18` varchar(50) DEFAULT NULL COMMENT '参数',
  `parameter19` varchar(50) DEFAULT NULL COMMENT '参数',
  UNIQUE KEY `dev_id` (`dev_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='MC设备参数记录:根据类别区分';

#
# Data for table "mc_device_info"
#


#
# Structure for table "mc_msg"
#

DROP TABLE IF EXISTS `mc_msg`;
CREATE TABLE `mc_msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `top_id` bigint(20) NOT NULL COMMENT '所属topic',
  `msg_type` bigint(20) NOT NULL DEFAULT '0' COMMENT '消息分类',
  `request_qos` varchar(4000) DEFAULT NULL COMMENT '消息报文',
  `suback` varchar(4000) DEFAULT NULL COMMENT '返回报文',
  `msg_size` varchar(4000) DEFAULT '0' COMMENT '单位：bytes',
  `time_producer` datetime DEFAULT NULL COMMENT '生成时间',
  `time_consumer` datetime DEFAULT NULL COMMENT '消费时间',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态  0：消费   1：生产',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='MC消息';

#
# Data for table "mc_msg"
#


#
# Structure for table "mc_shard_msg"
#

DROP TABLE IF EXISTS `mc_shard_msg`;
CREATE TABLE `mc_shard_msg` (
  `top_id` bigint(20) NOT NULL,
  `shard_value` varchar(50) NOT NULL COMMENT '散列值'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='MC消息=分表=mc_msg_{shard_value}';

#
# Data for table "mc_shard_msg"
#


#
# Structure for table "mc_subscribe"
#

DROP TABLE IF EXISTS `mc_subscribe`;
CREATE TABLE `mc_subscribe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sub_user_id` bigint(20) NOT NULL COMMENT '订阅用户',
  `sub_topic_id` bigint(20) NOT NULL COMMENT '订阅主题',
  `sub_time` datetime DEFAULT NULL COMMENT '订阅时间',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态  0：禁用   1：正常',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sub_user_id` (`sub_user_id`),
  UNIQUE KEY `sub_topic_id` (`sub_topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='MC订阅';

#
# Data for table "mc_subscribe"
#


#
# Structure for table "mc_topic"
#

DROP TABLE IF EXISTS `mc_topic`;
CREATE TABLE `mc_topic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '主题名称',
  `messages` varchar(100) DEFAULT '0' COMMENT '消息数',
  `num_producers` varchar(100) DEFAULT '0' COMMENT '生产数',
  `num_consumers` varchar(100) DEFAULT '0' COMMENT '消费数',
  `size_producers` varchar(100) DEFAULT '0' COMMENT '单位bytes：生产数据大小',
  `size_consumers` varchar(100) DEFAULT '0' COMMENT '单位bytes：消费数据大小',
  `user_id` varchar(100) DEFAULT NULL COMMENT 'mc_user_id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态  0：禁用   1：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='MC主题';

#
# Data for table "mc_topic"
#


#
# Structure for table "mc_user"
#

DROP TABLE IF EXISTS `mc_user`;
CREATE TABLE `mc_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `nickname` varchar(50) NOT NULL COMMENT '昵称',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐',
  `wechat_id` varchar(100) DEFAULT NULL COMMENT '微信平台标识',
  `qq_id` varchar(100) DEFAULT NULL COMMENT 'QQ平台标识',
  `alipay_id` varchar(100) DEFAULT NULL COMMENT '支付宝平台标识',
  `id_card` varchar(50) DEFAULT NULL COMMENT '身份证号码',
  `email` varchar(200) DEFAULT '@126.com' COMMENT '邮箱',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `sex` varchar(4) DEFAULT '1' COMMENT '性别',
  `birthday` varchar(100) DEFAULT NULL COMMENT '出生日期',
  `head_url` varchar(400) DEFAULT NULL COMMENT '头像地址',
  `head_url_thumb` varchar(400) DEFAULT NULL COMMENT '头像缩略图地址',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `mobile` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='MC用户';

#
# Data for table "mc_user"
#


#
# Structure for table "mc_user_login"
#

DROP TABLE IF EXISTS `mc_user_login`;
CREATE TABLE `mc_user_login` (
  `user_id` bigint(20) NOT NULL,
  `mobile` varchar(50) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='MC用户Token';

#
# Data for table "mc_user_login"
#


#
# Structure for table "qrtz_calendars"
#

DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "qrtz_calendars"
#


#
# Structure for table "qrtz_fired_triggers"
#

DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "qrtz_fired_triggers"
#


#
# Structure for table "qrtz_job_details"
#

DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "qrtz_job_details"
#

INSERT INTO `qrtz_job_details` VALUES ('RenrenScheduler','TASK_1','DEFAULT',NULL,'com.micro.boot.modules.job.utils.ScheduleJob','0','0','0','0',X'ACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597400B57B226A6F624964223A312C226265616E4E616D65223A22746573745461736B222C226D6574686F644E616D65223A227465737431222C22706172616D73223A2274657374222C2263726F6E45787072657373696F6E223A223020302F3330202A202A202A203F222C22737461747573223A302C2272656D61726B223A22E69C89E58F82E695B0E6B58BE8AF95222C2263726561746554696D65223A2244656320312C20323031362031313A31363A343620504D227D7800'),('RenrenScheduler','TASK_2','DEFAULT',NULL,'com.micro.boot.modules.job.utils.ScheduleJob','0','0','0','0',X'ACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597400A47B226A6F624964223A322C226265616E4E616D65223A22746573745461736B222C226D6574686F644E616D65223A227465737432222C2263726F6E45787072657373696F6E223A223020302F3330202A202A202A203F222C22737461747573223A312C2272656D61726B223A22E697A0E58F82E695B0E6B58BE8AF95222C2263726561746554696D65223A2244656320332C203230313620323A35353A353620504D227D7800');

#
# Structure for table "qrtz_locks"
#

DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "qrtz_locks"
#

INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler','STATE_ACCESS'),('RenrenScheduler','TRIGGER_ACCESS');

#
# Structure for table "qrtz_paused_trigger_grps"
#

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "qrtz_paused_trigger_grps"
#


#
# Structure for table "qrtz_scheduler_state"
#

DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "qrtz_scheduler_state"
#

INSERT INTO `qrtz_scheduler_state` VALUES ('RenrenScheduler','NKNSBZG1HD80PKQ1522883074403',1522883522573,15000);

#
# Structure for table "qrtz_triggers"
#

DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "qrtz_triggers"
#

INSERT INTO `qrtz_triggers` VALUES ('RenrenScheduler','TASK_1','DEFAULT','TASK_1','DEFAULT',NULL,1522884600000,-1,5,'WAITING','CRON',1521733831000,0,NULL,2,X'ACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597400D87B226A6F624964223A312C226265616E4E616D65223A22746573745461736B222C226D6574686F644E616D65223A2274657374222C22706172616D73223A22636F6D2E6D6963726F2E626F6F742E6D6F64756C65732E6A6F622E7461736B2E546573745461736B222C2263726F6E45787072657373696F6E223A223020302F3330202A202A202A203F222C22737461747573223A302C2272656D61726B223A22E69C89E58F82E695B0E6B58BE8AF95222C2263726561746554696D65223A2244656320312C20323031362031313A31363A343620504D227D7800'),('RenrenScheduler','TASK_2','DEFAULT','TASK_2','DEFAULT',NULL,1521733860000,-1,5,'PAUSED','CRON',1521733831000,0,NULL,2,X'ACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597400A17B226A6F624964223A322C226265616E4E616D65223A22746573745461736B222C226D6574686F644E616D65223A227465737432222C2263726F6E45787072657373696F6E223A2230202A202A202A202A203F222C22737461747573223A312C2272656D61726B223A22E697A0E58F82E695B0E6B58BE8AF95222C2263726561746554696D65223A2244656320332C203230313620323A35353A353620504D227D7800');

#
# Structure for table "qrtz_simprop_triggers"
#

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "qrtz_simprop_triggers"
#


#
# Structure for table "qrtz_simple_triggers"
#

DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "qrtz_simple_triggers"
#


#
# Structure for table "qrtz_cron_triggers"
#

DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "qrtz_cron_triggers"
#

INSERT INTO `qrtz_cron_triggers` VALUES ('RenrenScheduler','TASK_1','DEFAULT','0 0/30 * * * ?','GMT+08:00'),('RenrenScheduler','TASK_2','DEFAULT','0 * * * * ?','GMT+08:00');

#
# Structure for table "qrtz_blob_triggers"
#

DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "qrtz_blob_triggers"
#


#
# Structure for table "schedule_job"
#

DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='定时任务';

#
# Data for table "schedule_job"
#

INSERT INTO `schedule_job` VALUES (1,'testTask','test','com.micro.boot.modules.job.task.TestTask','0 0/30 * * * ?',0,'有参数测试','2016-12-01 23:16:46'),(2,'testTask','test2',NULL,'0 * * * * ?',1,'无参数测试','2016-12-03 14:55:56');

#
# Structure for table "schedule_job_log"
#

DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

#
# Data for table "schedule_job_log"
#

INSERT INTO `schedule_job_log` VALUES (1,1,'testTask','test1','test',1,'java.lang.NoSuchMethodException: com.micro.boot.modules.job.task.TestTask.test1(java.lang.String)',3,'2018-03-24 15:30:00'),(2,1,'testTask','test1','test',1,'java.lang.NoSuchMethodException: com.micro.boot.modules.job.task.TestTask.test1(java.lang.String)',3,'2018-03-25 12:30:12'),(3,1,'testTask','test1','test',1,'java.lang.NoSuchMethodException: com.micro.boot.modules.job.task.TestTask.test1(java.lang.String)',4,'2018-03-25 13:00:00'),(4,1,'testTask','test1','test',1,'java.lang.NoSuchMethodException: com.micro.boot.modules.job.task.TestTask.test1(java.lang.String)',3,'2018-03-25 14:00:00'),(5,1,'testTask','test1','test',1,'java.lang.NoSuchMethodException: com.micro.boot.modules.job.task.TestTask.test1(java.lang.String)',328,'2018-03-25 20:00:00'),(6,1,'testTask','test1','test',1,'java.lang.NoSuchMethodException: com.micro.boot.modules.job.task.TestTask.test1(java.lang.String)',326,'2018-03-30 00:30:00'),(7,1,'testTask','test1','test',1,'java.lang.NoSuchMethodException: com.micro.boot.modules.job.task.TestTask.test1(java.lang.String)',4,'2018-03-30 01:47:57'),(8,1,'testTask','test1','test',1,'java.lang.NoSuchMethodException: com.micro.boot.modules.job.task.TestTask.test1(java.lang.String)',37329,'2018-03-30 01:47:48'),(9,2,'testTask','test2',NULL,0,NULL,5,'2018-03-30 01:55:00'),(10,2,'testTask','test2',NULL,0,NULL,2,'2018-03-30 01:56:00'),(11,2,'testTask','test2',NULL,0,NULL,5,'2018-03-30 01:57:00'),(12,2,'testTask','test2',NULL,0,NULL,2,'2018-03-30 01:58:00'),(13,2,'testTask','test2',NULL,0,NULL,1,'2018-03-30 01:59:00'),(14,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1052,'2018-03-30 02:00:00'),(15,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1067,'2018-03-30 23:30:00'),(16,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1029,'2018-03-31 00:00:00'),(17,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1016,'2018-03-31 00:30:00'),(18,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1021,'2018-03-31 01:00:00'),(19,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1018,'2018-03-31 01:30:00'),(20,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1012,'2018-03-31 02:00:00'),(21,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1026,'2018-03-31 13:30:00'),(22,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1017,'2018-03-31 14:30:00'),(23,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1039,'2018-03-31 15:00:00'),(24,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1029,'2018-03-31 15:30:00'),(25,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1039,'2018-04-01 16:30:00'),(26,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1025,'2018-04-01 17:00:00'),(27,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1015,'2018-04-01 17:30:00'),(28,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1039,'2018-04-01 18:00:00'),(29,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1036,'2018-04-01 23:00:00'),(30,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1019,'2018-04-01 23:30:00'),(31,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1014,'2018-04-02 00:00:00'),(32,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1023,'2018-04-02 00:30:00'),(33,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1037,'2018-04-02 01:00:00'),(34,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1026,'2018-04-03 00:00:00'),(35,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1021,'2018-04-03 00:30:00'),(36,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1010,'2018-04-03 01:00:00'),(37,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1038,'2018-04-03 22:30:00'),(38,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1059,'2018-04-05 01:30:00'),(39,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1033,'2018-04-05 02:00:00'),(40,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1060,'2018-04-05 03:00:00'),(41,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1016,'2018-04-05 04:00:00'),(42,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1014,'2018-04-05 04:30:00'),(43,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1036,'2018-04-05 05:04:05'),(44,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1036,'2018-04-05 05:30:00'),(45,1,'testTask','test','com.micro.boot.modules.job.task.TestTask',0,NULL,1013,'2018-04-05 06:30:00');

#
# Structure for table "sys_config"
#

DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(50) DEFAULT NULL COMMENT 'key',
  `value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`key`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

#
# Data for table "sys_config"
#

INSERT INTO `sys_config` VALUES (1,'CLOUD_STORAGE_CONFIG_KEY','{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}',0,'云存储配置信息'),(2,'消息过期天数','90',1,'90天');

#
# Structure for table "sys_log"
#

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='系统日志';

#
# Data for table "sys_log"
#

INSERT INTO `sys_log` VALUES (1,'admin','保存配置','com.micro.boot.modules.sys.controller.SysConfigController.save()','{\"key\":\"消息过期天数\",\"value\":\"90\",\"remark\":\"90天\"}',163,'0:0:0:0:0:0:0:1','2018-03-25 19:43:51'),(2,'admin','恢复定时任务','com.micro.boot.modules.job.controller.ScheduleJobController.resume()','[2]',70,'0:0:0:0:0:0:0:1','2018-03-30 01:46:41'),(3,'admin','暂停定时任务','com.micro.boot.modules.job.controller.ScheduleJobController.pause()','[2]',22,'0:0:0:0:0:0:0:1','2018-03-30 01:47:11'),(4,'admin','恢复定时任务','com.micro.boot.modules.job.controller.ScheduleJobController.resume()','[2]',12,'0:0:0:0:0:0:0:1','2018-03-30 01:47:28'),(5,'admin','立即执行任务','com.micro.boot.modules.job.controller.ScheduleJobController.run()','[1]',19,'0:0:0:0:0:0:0:1','2018-03-30 01:47:34'),(6,'admin','立即执行任务','com.micro.boot.modules.job.controller.ScheduleJobController.run()','[1]',11,'0:0:0:0:0:0:0:1','2018-03-30 01:47:48'),(7,'admin','修改定时任务','com.micro.boot.modules.job.controller.ScheduleJobController.update()','{\"jobId\":2,\"beanName\":\"testTask\",\"methodName\":\"test2\",\"cronExpression\":\"0 * * * * ?\",\"status\":0,\"remark\":\"无参数测试\",\"createTime\":\"Dec 3, 2016 2:55:56 PM\"}',105,'0:0:0:0:0:0:0:1','2018-03-30 01:54:05'),(8,'admin','恢复定时任务','com.micro.boot.modules.job.controller.ScheduleJobController.resume()','[2]',17,'0:0:0:0:0:0:0:1','2018-03-30 01:54:30'),(9,'admin','修改定时任务','com.micro.boot.modules.job.controller.ScheduleJobController.update()','{\"jobId\":1,\"beanName\":\"testTask\",\"methodName\":\"test\",\"params\":\"com.micro.boot.modules.job.task.TestTask\",\"cronExpression\":\"0 0/30 * * * ?\",\"status\":0,\"remark\":\"有参数测试\",\"createTime\":\"Dec 1, 2016 11:16:46 PM\"}',30,'0:0:0:0:0:0:0:1','2018-03-30 01:58:52'),(10,'admin','恢复定时任务','com.micro.boot.modules.job.controller.ScheduleJobController.resume()','[1]',23,'0:0:0:0:0:0:0:1','2018-03-30 01:58:59'),(11,'admin','暂停定时任务','com.micro.boot.modules.job.controller.ScheduleJobController.pause()','[2]',11,'0:0:0:0:0:0:0:1','2018-03-30 01:59:09');

#
# Structure for table "sys_menu"
#

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

#
# Data for table "sys_menu"
#

INSERT INTO `sys_menu` VALUES (1,0,'系统管理',NULL,NULL,0,'fa fa-cog',0),(2,1,'管理员列表','modules/sys/user.html',NULL,1,'fa fa-user',1),(3,1,'角色管理','modules/sys/role.html',NULL,1,'fa fa-user-secret',2),(4,1,'菜单管理','modules/sys/menu.html',NULL,1,'fa fa-th-list',3),(5,1,'SQL监控','druid/sql.html',NULL,1,'fa fa-bug',4),(6,1,'定时任务','modules/job/schedule.html',NULL,1,'fa fa-tasks',5),(7,6,'查看',NULL,'sys:schedule:list,sys:schedule:info',2,NULL,0),(8,6,'新增',NULL,'sys:schedule:save',2,NULL,0),(9,6,'修改',NULL,'sys:schedule:update',2,NULL,0),(10,6,'删除',NULL,'sys:schedule:delete',2,NULL,0),(11,6,'暂停',NULL,'sys:schedule:pause',2,NULL,0),(12,6,'恢复',NULL,'sys:schedule:resume',2,NULL,0),(13,6,'立即执行',NULL,'sys:schedule:run',2,NULL,0),(14,6,'日志列表',NULL,'sys:schedule:log',2,NULL,0),(15,2,'查看',NULL,'sys:user:list,sys:user:info',2,NULL,0),(16,2,'新增',NULL,'sys:user:save,sys:role:select',2,NULL,0),(17,2,'修改',NULL,'sys:user:update,sys:role:select',2,NULL,0),(18,2,'删除',NULL,'sys:user:delete',2,NULL,0),(19,3,'查看',NULL,'sys:role:list,sys:role:info',2,NULL,0),(20,3,'新增',NULL,'sys:role:save,sys:menu:list',2,NULL,0),(21,3,'修改',NULL,'sys:role:update,sys:menu:list',2,NULL,0),(22,3,'删除',NULL,'sys:role:delete',2,NULL,0),(23,4,'查看',NULL,'sys:menu:list,sys:menu:info',2,NULL,0),(24,4,'新增',NULL,'sys:menu:save,sys:menu:select',2,NULL,0),(25,4,'修改',NULL,'sys:menu:update,sys:menu:select',2,NULL,0),(26,4,'删除',NULL,'sys:menu:delete',2,NULL,0),(27,1,'参数管理','modules/sys/config.html','sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete',1,'fa fa-sun-o',6),(29,1,'系统日志','modules/sys/log.html','sys:log:list',1,'fa fa-file-text-o',7),(30,1,'文件上传','modules/oss/oss.html','sys:oss:all',1,'fa fa-file-image-o',6);

#
# Structure for table "sys_oss"
#

DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件上传';

#
# Data for table "sys_oss"
#


#
# Structure for table "sys_role"
#

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

#
# Data for table "sys_role"
#


#
# Structure for table "sys_role_menu"
#

DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

#
# Data for table "sys_role_menu"
#


#
# Structure for table "sys_user"
#

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

#
# Data for table "sys_user"
#

INSERT INTO `sys_user` VALUES (1,'admin','9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d','YzcmCZNvbXocrsz9dm8e','418206020@qq.com','15094011640',1,1,'2018-01-18 11:11:11');

#
# Structure for table "sys_user_role"
#

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

#
# Data for table "sys_user_role"
#


#
# Structure for table "sys_user_token"
#

DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户Token';

#
# Data for table "sys_user_token"
#

INSERT INTO `sys_user_token` VALUES (1,'0976a7be3a4af929628cbc5278c4853f','2018-03-30 13:46:16','2018-03-30 01:46:16');
