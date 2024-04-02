CREATE TABLE channel (
  id               bigint(20) unsigned AUTO_INCREMENT NOT NULL COMMENT '自增主键ID', 
  create_time      datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time      datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间', 
  code             varchar(32)                        NOT NULL UNIQUE COMMENT '编码', 
  name             varchar(100)                       NOT NULL COMMENT '名称', 
  PRIMARY KEY (id),
  UNIQUE KEY uniq_code (code)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '渠道';

CREATE TABLE points_config (
  id               bigint(20) unsigned AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
  create_time      datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time      datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间', 
  channel_code     varchar(32)                        NOT NULL COMMENT '渠道编码', 
  name             varchar(100)                       NOT NULL COMMENT '名称', 
  description      varchar(200)                                DEFAULT NULL COMMENT '描述', 
  status           smallint(6)                        NOT NULL DEFAULT 0 COMMENT '状态，取值：0-禁用 1-启用', 
  type             smallint(6)                        NOT NULL COMMENT '类型，取值：1-永久积分 2-活动积分', 
  expiration_rule  json                                        DEFAULT NULL COMMENT '过期规则', 
  stock            bigint(20)                                  DEFAULT NULL COMMENT '库存', 
  PRIMARY KEY (id), 
  KEY idx_channel_code (channel_code)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '积分配置';

CREATE TABLE user_points (
  id                bigint(20) unsigned AUTO_INCREMENT NOT NULL COMMENT '自增主键ID', 
  create_time       datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time       datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间', 
  user_id           bigint(20)                         NOT NULL UNIQUE COMMENT '用户ID', 
  total             bigint(20)                         NOT NULL DEFAULT 0 COMMENT '总数', 
  used              bigint(20)                         NOT NULL DEFAULT 0 COMMENT '已使用数量', 
  expired           bigint(20)                         NOT NULL DEFAULT 0 COMMENT '已过期数量', 
  PRIMARY KEY (id),
  UNIQUE KEY uniq_user_id (user_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '用户积分';

CREATE TABLE user_points_detail (
  id               bigint(20) unsigned AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
  create_time      datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time      datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间', 
  user_id          bigint(20)                         NOT NULL COMMENT '用户ID', 
  config_id        bigint(20)                         NOT NULL COMMENT '配置ID', 
  channel_code     varchar(32)                        NOT NULL COMMENT '渠道编码', 
  expire_time      datetime                                    DEFAULT NULL COMMENT '过期时间', 
  number           bigint(20)                         NOT NULL DEFAULT 0 COMMENT '数量', 
  source           varchar(20)                        NOT NULL COMMENT '来源', 
  unique_source_id varchar(64)                        NOT NULL COMMENT '唯一来源ID', 
  biz_id           bigint(20)                         NOT NULL COMMENT '业务ID', 
  biz_description  varchar(255)                       NOT NULL COMMENT '业务描述', 
  PRIMARY KEY (id), 
  KEY idx_user_config_channel (user_id, config_id, channel_code),
  UNIQUE KEY uniq_user_source_id (user_id, source, unique_source_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '用户积分明细';

CREATE TABLE user_points_log (
  id               bigint(20) unsigned AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
  create_time      datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time      datetime                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间', 
  user_id          bigint(20)                         NOT NULL COMMENT '用户ID', 
  config_id        bigint(20)                         NOT NULL COMMENT '配置ID', 
  channel_code     varchar(32)                        NOT NULL COMMENT '渠道编码', 
  type             smallint(6)                        NOT NULL COMMENT '类型，取值：1-发放 2-使用 3-回退 4-回收', 
  number           bigint(20)                         NOT NULL COMMENT '数量', 
  source           varchar(20)                        NOT NULL COMMENT '来源', 
  unique_source_id varchar(64)                        NOT NULL COMMENT '唯一来源ID',
  biz_id           bigint(20)                         NOT NULL COMMENT '业务ID', 
  biz_description  varchar(255)                       NOT NULL COMMENT '业务描述', 
  detail           json                                        DEFAULT NULL COMMENT '用户积分明细', 
  PRIMARY KEY (id),
  KEY idx_user_config_channel (user_id, config_id, channel_code),
  UNIQUE KEY uniq_user_source_id (user_id, source, unique_source_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '用户积分日志';
