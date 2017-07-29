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
  name         VARCHAR(1024)                         NOT NULL
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
CREATE INDEX id_type
  ON dictionary (type);
CREATE INDEX id_created_time
  ON dictionary (created_time);

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
  description   VARCHAR(64)                           NOT NULL
  COMMENT '描述',
  picture       VARCHAR(128)                          NOT NULL
  COMMENT '封面图片地址',
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

INSERT INTO category (code, name, description, picture, sort)
VALUES
  ('java', 'Java后台', 'Java、Spring、MyBatis、Redis、Dubbo等', '/static/app/images/wx/java.png', 0),
  ('web', 'Web前端', 'Html、Css、js、jQuery、Bootstrap等', '/static/app/images/wx/web.png', 1),
  ('db', '数据库', 'MySQL、主从复制、读写分离等', '/static/app/images/wx/db.png', 2),
  ('code', '代码片段', '经常使用但又记不住的或者太长的代码片段', '/static/app/images/wx/code.png', 3),
  ('linux', '系统运维', 'Linux下各种软件的安装、环境变量的配置、以及使用等', '/static/app/images/wx/linux.png', 4),
  ('other', '综合', 'SSH免密登录、内网穿透、网站升级到Https等', '/static/app/images/wx/other.png', 5);

-- ----------------------------
--  Table structure for book
-- ----------------------------
DROP TABLE
IF EXISTS book;

CREATE TABLE book
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  name         VARCHAR(64)                           NOT NULL
  COMMENT '书名',
  author       VARCHAR(32)                           NOT NULL
  COMMENT '作者',
  intro        VARCHAR(512)                          NOT NULL
  COMMENT '简介',
  picture      VARCHAR(256)                          NOT NULL                    DEFAULT ''
  COMMENT '首图',
  url          INTEGER                               NOT NULL
  COMMENT '地址',
  is_deleted   TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '书籍表';
CREATE UNIQUE INDEX id_UNIQUE
  ON book (id);
CREATE UNIQUE INDEX url_UNIQUE
  ON book (url);
CREATE INDEX author_ix
  ON book (author);

INSERT INTO book (name, author, intro, picture, url)
  VALUE ('不死武尊', '妖月夜', '一个落魄的少年偶得吞天神诀，可吞噬一切天地元气为己用，从此逆天破命，一雪前耻，凭借着神奇的生命武魂，他凝聚不死之身，横扫九天十地，雄霸天下！',
         'http://www.biquge.cn/cover/1/1337/1337s.jpg', '1337');

INSERT INTO book (name, author, intro, picture, url)
  VALUE ('逆天邪神', '火星引力', '掌天毒之珠，承邪神之血，修逆天之力，一代邪神，君临天下！', 'http://www.biquge.cn/cover/2/2722/2722s.jpg', '2722');

INSERT INTO book (name, author, intro, picture, url)
  VALUE ('绝世战魂', '极品妖孽', '家族少主，天生废魂，在机缘巧合之下，觉醒了太古神秘的战神之魂，从此之后，一路逆袭，邂逅仙姿美女，碾压九界天才，无所不战，无所不胜！',
         'http://www.biquge.cn/cover/18/18612/18612s.jpg', '18612');

INSERT INTO book (name, author, intro, picture, url)
  VALUE ('嫡女重生记', '六月浩雪',
         '在家是小透明，嫁人后是摆设，最后葬身火海尸骨无存，这是韩玉熙上辈子的写照。重活一世，韩玉熙努力上进，只愿不再做陪衬与花瓶，然后觅得如意郎君，平安富贵过一生。可惜事与愿违，嫁了个身负血海深仇的郎君，韩玉熙的人生开始翻天覆地，但她新的人生却是好事多磨，苦尽甘来。',
         'http://www.biquge.cn/cover/14/14141/14141s.jpg', '14141');

-- ----------------------------
--  Table structure for repository
-- ----------------------------
DROP TABLE
IF EXISTS repository;

CREATE TABLE repository
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  question     VARCHAR(256)                          NOT NULL
  COMMENT '问题',
  answer       LONGTEXT                              NOT NULL
  COMMENT '答案',
  weight       INTEGER                               NOT NULL                    DEFAULT 0
  COMMENT '权重',
  is_deleted   TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除:{0:未删除, 1:已删除}',
  created_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '知识库表';
CREATE UNIQUE INDEX id_UNIQUE
  ON repository (id);
CREATE UNIQUE INDEX question_UNIQUE
  ON repository (question);

INSERT INTO repository
(question, answer, weight)
VALUES
  ('你叫什么名字？', '我叫智能小胖', 9999);