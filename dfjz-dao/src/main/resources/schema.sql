DROP DATABASE IF EXISTS dfjz;

CREATE DATABASE dfjz
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;

USE dfjz;

-- ----------------------------
--  Table structure for article
-- ----------------------------
DROP TABLE
IF EXISTS article;

CREATE TABLE article
(
  id            BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  title         VARCHAR(64)                           NOT NULL
  COMMENT '文章标题',
  category_code VARCHAR(16)                           NOT NULL
  COMMENT '栏目代码',
  category_name VARCHAR(32)                           NOT NULL
  COMMENT '栏目名称',
  content       LONGTEXT                              NOT NULL
  COMMENT '文章内容',
  visit_count   INTEGER(11)                           NOT NULL                    DEFAULT 0
  COMMENT '访问量',
  comment_count INTEGER(11)                           NOT NULL                    DEFAULT 0
  COMMENT '评论量',
  is_comment    TINYINT                               NOT NULL                    DEFAULT 1
  COMMENT '是否允许评论:{0:不允许, 1:允许}',
  is_stick      TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '是否置顶:{0:未置顶, 1:已置顶}',
  is_deleted    TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time  TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time  TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '文章表';
CREATE INDEX id_category_code
  ON article (category_code);
CREATE INDEX id_created_time
  ON article (created_time);

-- ----------------------------
--  Table structure for visit
-- ----------------------------
DROP TABLE
IF EXISTS visit;

CREATE TABLE visit
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  article_id   BIGINT(20)                            NOT NULL
  COMMENT '文章ID',
  ip           VARCHAR(15)                           NOT NULL
  COMMENT 'IP地址',
  code         VARCHAR(32)                           NOT NULL
  COMMENT '响应码',
  msg          VARCHAR(64)                           NOT NULL                    DEFAULT ''
  COMMENT '响应消息',
  country      VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '国家',
  area         VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '地区',
  region       VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '省份',
  city         VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '城市',
  isp          VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '运营商',
  is_deleted   TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '访问记录表';
CREATE INDEX id_article_id
  ON visit (article_id);
CREATE INDEX id_created_time
  ON visit (created_time);

-- ----------------------------
--  Table structure for comment
-- ----------------------------
DROP TABLE
IF EXISTS comment;

CREATE TABLE comment
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  article_id   BIGINT(20)                            NOT NULL
  COMMENT '文章ID',
  content      VARCHAR(512)                          NOT NULL
  COMMENT '评论内容',
  username     VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '评论者姓名',
  email        VARCHAR(64)                           NOT NULL                    DEFAULT ''
  COMMENT '评论者邮箱',
  ip           VARCHAR(15)                           NOT NULL                    DEFAULT ''
  COMMENT '评论者IP',
  city         VARCHAR(16)                           NOT NULL                    DEFAULT ''
  COMMENT '评论者所在城市',
  is_deleted   TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '评论表';
CREATE INDEX id_article_id
  ON comment (article_id);
CREATE INDEX id_created_time
  ON comment (created_time);

-- ----------------------------
--  Table structure for category
-- ----------------------------
DROP TABLE
IF EXISTS category;

CREATE TABLE category
(
  id            BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  code          VARCHAR(16)                           NOT NULL
  COMMENT '栏目代码',
  name          VARCHAR(32)                           NOT NULL
  COMMENT '栏目名称',
  sort          INTEGER(11)                           NOT NULL
  COMMENT '栏目排序',
  article_count INTEGER(11)                           NOT NULL                    DEFAULT 0
  COMMENT '文章数量',
  is_deleted    TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time  TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time  TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '栏目表';
CREATE UNIQUE INDEX uid_code
  ON category (code);
CREATE INDEX id_created_time
  ON category (created_time);

INSERT INTO category (code, name, sort)
VALUES
  ('java', 'Java后台', 0),
  ('web', 'Web前端', 1),
  ('db', '数据库', 2),
  ('code', '代码片段', 3),
  ('linux', '系统运维', 4),
  ('other', '综合', 5);

