-- MC用户Token
CREATE TABLE `mc_user_login` (
	`user_id` BIGINT(20) NOT NULL,
	`mobile` VARCHAR(50) NULL DEFAULT NULL,
	`token` VARCHAR(100) NOT NULL COMMENT 'token',
	`expire_time` DATETIME NULL DEFAULT NULL COMMENT '过期时间',
	`update_time` DATETIME NULL DEFAULT NULL COMMENT '更新时间',
	PRIMARY KEY (`user_id`),
	UNIQUE INDEX `token` (`token`)
)
COMMENT='MC用户Token'
ENGINE=InnoDB
;

-- MC用户Token
CREATE TABLE `mc_user_login` (
	`user_id` BIGINT(20) NOT NULL,
	`token` VARCHAR(100) NOT NULL COMMENT 'token',
	`expire_time` DATETIME NULL DEFAULT NULL COMMENT '过期时间',
	`update_time` DATETIME NULL DEFAULT NULL COMMENT '更新时间',
	PRIMARY KEY (`user_id`),
	UNIQUE INDEX `token` (`token`)
)
COMMENT='MC用户Token'
ENGINE=InnoDB
;

-- MC用户
CREATE TABLE `mc_user` (
	`user_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(50) NOT NULL COMMENT '用户名',
	`nickname` VARCHAR(50) NOT NULL COMMENT '昵称',
	`password` VARCHAR(100) NOT NULL COMMENT '密码',
	`salt` VARCHAR(50) NULL DEFAULT NULL COMMENT '盐',
	`wechat_id` VARCHAR(100) NULL DEFAULT NULL COMMENT '微信平台标识',
	`qq_id` VARCHAR(100) NULL DEFAULT NULL COMMENT 'QQ平台标识',
	`alipay_id` VARCHAR(100) NULL DEFAULT NULL COMMENT '支付宝平台标识',
	`id_card` VARCHAR(50) NULL DEFAULT NULL COMMENT '身份证号码',
	`email` VARCHAR(200) NULL DEFAULT NULL COMMENT '邮箱',
	`mobile` VARCHAR(20) NOT NULL COMMENT '手机号',
	`sex` VARCHAR(4) NULL DEFAULT NULL COMMENT '性别',
	`birthday` VARCHAR(100) NULL DEFAULT NULL COMMENT '出生日期',
	`head_url` VARCHAR(400) NULL DEFAULT NULL COMMENT '头像地址',
	`head_url_thumb` VARCHAR(400) NULL DEFAULT NULL COMMENT '头像缩略图地址',
	`status` TINYINT(4) NOT NULL COMMENT '状态  0：禁用   1：正常',
	`create_user_id` BIGINT(20) NULL DEFAULT NULL COMMENT '创建者ID',
	`create_time` DATETIME NULL DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`user_id`),
	UNIQUE INDEX `username` (`username`),
	UNIQUE INDEX `mobile` (`mobile`)
)
COMMENT='MC用户'
ENGINE=InnoDB

-- MC主题
CREATE TABLE `mc_topic` (
	`top_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`top_name` VARCHAR(50) NOT NULL COMMENT '主题名称',
	`top_messages` VARCHAR(100) NULL DEFAULT NULL COMMENT '消息数',
	`top_producers` VARCHAR(100) NULL DEFAULT NULL COMMENT '生产数',
	`top_consumers` VARCHAR(100) NULL DEFAULT NULL COMMENT '消费数',
	`top_producers_size` VARCHAR(100) NULL DEFAULT NULL COMMENT '单位bytes：生产数据大小',
	`top_consumers_size` VARCHAR(100) NULL DEFAULT NULL COMMENT '单位bytes：消费数据大小',
	`create_user_id` VARCHAR(100) NULL DEFAULT NULL COMMENT 'mc_user_id',
	`create_time` DATETIME NULL DEFAULT NULL COMMENT '创建时间',
	`status` TINYINT(4) NOT NULL COMMENT '状态  0：禁用   1：正常',
	PRIMARY KEY (`top_id`)
)
COMMENT='MC主题'
ENGINE=InnoDB
;


-- MC订阅（主题和客户关系）
CREATE TABLE `mc_subscribe` (
	`sub_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`sub_user_id` BIGINT(20) NOT NULL COMMENT '订阅用户',
	`sub_topic_id` BIGINT(20) NOT NULL COMMENT '订阅主题',
	`sub_time` DATETIME NULL DEFAULT NULL COMMENT '订阅时间',
	`status` TINYINT(4) NULL DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
	PRIMARY KEY (`sub_id`),
	UNIQUE INDEX `sub_user_id` (`sub_user_id`),
	UNIQUE INDEX `sub_topic_id` (`sub_topic_id`)
)
COMMENT='MC订阅'
ENGINE=InnoDB
;

---message存表，如果存采用垂直分表，算法topic_id%100=shard散列字典：mc_msg_aa:理论上支持散列字典值*100个用户
--当topic订阅超越100（系统在达到80%时执行脚本）自动创建表【insert判断表是否存在。避免瓶颈】
-- MC散列值
CREATE TABLE `mc_shard_msg` (
	`top_id` BIGINT(20) NOT NULL,
	`shard_value` VARCHAR(50) NOT NULL COMMENT '散列值'
)
COMMENT='MC消息=分表=mc_msg_{shard_value}'
ENGINE=InnoDB
;

-- MC消息
CREATE TABLE `mc_msg_xx1` (
	`msg_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`top_id` BIGINT(20) NOT NULL COMMENT '所属topic',
	`msg_type` BIGINT(20) NOT NULL COMMENT '消息分类',
	`request_qos` VARCHAR(4000) NULL DEFAULT NULL COMMENT '消息报文',
	`suback` VARCHAR(4000) NULL DEFAULT NULL COMMENT '返回报文',
	`msg_size` VARCHAR(4000) NULL DEFAULT NULL COMMENT '单位：bytes',
	`time_producer` DATETIME NULL DEFAULT NULL COMMENT '生成时间',
	`time_consumer` DATETIME NULL DEFAULT NULL COMMENT '消费时间',
	`status` TINYINT(4) NULL DEFAULT '0' COMMENT '状态  0：消费   1：生产',
	PRIMARY KEY (`msg_id`)
)
COMMENT='MC消息'
ENGINE=InnoDB
;

-- MC设备 （此表只记录公共参数）
CREATE TABLE `mc_device` (
	`dev_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`dev_name_en` VARCHAR(100) NOT NULL COMMENT '设备英文名称',
	`dev_name_zh` VARCHAR(100) NULL DEFAULT NULL COMMENT '设备中文名称',
	`user_id` BIGINT(20) NOT NULL COMMENT 'MC用户',
	`dev_type` VARCHAR(6) NOT NULL COMMENT '设备类别',
	`dev_info_id` VARCHAR(6) NULL DEFAULT NULL COMMENT '设备详细参数信息',
	`dev_status` VARCHAR(6) NOT NULL COMMENT '设备状态',
	`dev_mode` VARCHAR(6) NOT NULL COMMENT '设备模式',
	`electricity` VARCHAR(10) NULL DEFAULT NULL COMMENT '电量%',
	`status_wifi` VARCHAR(6) NULL DEFAULT NULL COMMENT 'WIFI状态',
	`status_bluetooth` VARCHAR(6) NULL DEFAULT NULL COMMENT '蓝牙状态',
	`status_voice` VARCHAR(4) NULL DEFAULT NULL COMMENT '声音状态',
	`status_switch` VARCHAR(4) NULL DEFAULT NULL COMMENT '开关状态',
	`create_time` DATETIME NULL DEFAULT NULL COMMENT '创建时间',
	`public_extend_param1` VARCHAR(100) NULL DEFAULT NULL COMMENT '公共扩展参数1',
	`public_extend_param2` VARCHAR(100) NULL DEFAULT NULL COMMENT '公共扩展参数2',
	`public_extend_param3` VARCHAR(100) NULL DEFAULT NULL COMMENT '公共扩展参数3',
	PRIMARY KEY (`dev_id`)
)
COMMENT='MC设备 （此表只记录公共参数）'
ENGINE=InnoDB
;


-- MC设备参数记录:根据类别区分
CREATE TABLE `mc_device_info` (
  `dev_id` BIGINT(20) NOT NULL COMMENT '设备标识',
  `parameter1` varchar(50) COMMENT '参数',
  `parameter2` varchar(50) COMMENT '参数',
  `parameter3` varchar(50) COMMENT '参数',
  `parameter4` varchar(50) COMMENT '参数',
  `parameter5` varchar(50) COMMENT '参数',
  `parameter6` varchar(50) COMMENT '参数',
  `parameter7` varchar(50) COMMENT '参数',
  `parameter8` varchar(50) COMMENT '参数',
  `parameter9` varchar(50) COMMENT '参数',
  `parameter10` varchar(50) COMMENT '参数',
  `parameter11` varchar(50) COMMENT '参数',
  `parameter12` varchar(50) COMMENT '参数',
  `parameter13` varchar(50) COMMENT '参数',
  `parameter14` varchar(50) COMMENT '参数',
  `parameter15` varchar(50) COMMENT '参数',
   `parameter16` varchar(50) COMMENT '参数',
  `parameter17` varchar(50) COMMENT '参数',
  `parameter18` varchar(50) COMMENT '参数',
  `parameter19` varchar(50) COMMENT '参数',
  UNIQUE KEY (`dev_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='MC设备参数记录:根据类别区分';


