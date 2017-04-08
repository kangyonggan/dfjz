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
  COMMENT '访问者IP',
  ip_addr      VARCHAR(32)                           NOT NULL
  COMMENT 'ip所在地',
  is_deleted   TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '访问记录表';
CREATE UNIQUE INDEX uid_article_ip
  ON visit (article_id, ip);
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
  pid          BIGINT(20)                            NOT NULL                    DEFAULT 0
  COMMENT '父评论ID',
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
  pcode         VARCHAR(16)                           NOT NULL                    DEFAULT ''
  COMMENT '父栏目代码',
  picture       VARCHAR(128)                          NOT NULL                    DEFAULT ''
  COMMENT '栏目头图',
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
CREATE UNIQUE INDEX uid_pcode_code
  ON category (pcode, code);
CREATE INDEX id_created_time
  ON category (created_time);

-- ----------------------------
--  Table structure for dictionary
-- ----------------------------
DROP TABLE
IF EXISTS dictionary;

CREATE TABLE dictionary
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  type         VARCHAR(16)                           NOT NULL
  COMMENT '类型',
  code         VARCHAR(16)                           NOT NULL
  COMMENT '代码',
  name         VARCHAR(32)                           NOT NULL
  COMMENT '名称',
  sort         INTEGER(11)                           NOT NULL
  COMMENT '排序',
  pcode        VARCHAR(16)                           NOT NULL                    DEFAULT ''
  COMMENT '父代码',
  is_deleted   TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '字典表';
CREATE UNIQUE INDEX uid_type
  ON dictionary (type);
CREATE UNIQUE INDEX uid_pcode_code
  ON dictionary (pcode, code);
CREATE INDEX id_created_time
  ON dictionary (created_time);

