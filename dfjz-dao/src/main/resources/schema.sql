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
CREATE UNIQUE INDEX uid_type_pcode_code
  ON dictionary (type, pcode, code);
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

INSERT INTO dictionary
(type, code, name, sort)
VALUES
  ('ASCLL', '0', 'NUT', 0),
  ('ASCLL', '1', 'SOH', 1),
  ('ASCLL', '2', 'STX', 2),
  ('ASCLL', '3', 'ETX', 3),
  ('ASCLL', '4', 'EOT', 4),
  ('ASCLL', '5', 'ENQ', 5),
  ('ASCLL', '6', 'ACK', 6),
  ('ASCLL', '7', 'BEL', 7),
  ('ASCLL', '8', 'BS', 8),
  ('ASCLL', '9', 'HT', 9),
  ('ASCLL', '10', 'LF', 10),
  ('ASCLL', '11', 'VT', 11),
  ('ASCLL', '12', 'FF', 12),
  ('ASCLL', '13', 'CR', 13),
  ('ASCLL', '14', 'SO', 14),
  ('ASCLL', '15', 'SI', 15),
  ('ASCLL', '16', 'DLE', 16),
  ('ASCLL', '17', 'DCI', 17),
  ('ASCLL', '18', 'DC2', 18),
  ('ASCLL', '19', 'DC3', 19),
  ('ASCLL', '20', 'DC4', 20),
  ('ASCLL', '21', 'NAK', 21),
  ('ASCLL', '22', 'SYN', 22),
  ('ASCLL', '23', 'TB', 23),
  ('ASCLL', '24', 'CAN', 24),
  ('ASCLL', '25', 'EM', 25),
  ('ASCLL', '26', 'SUB', 26),
  ('ASCLL', '27', 'ESC', 27),
  ('ASCLL', '28', 'FS', 28),
  ('ASCLL', '29', 'GS', 29),
  ('ASCLL', '30', 'RS', 30),
  ('ASCLL', '31', 'US', 31),
  ('ASCLL', '32', '(space)', 32),
  ('ASCLL', '33', '!', 33),
  ('ASCLL', '34', '"', 34),
  ('ASCLL', '35', '#', 35),
  ('ASCLL', '36', '$', 36),
  ('ASCLL', '37', '%', 37),
  ('ASCLL', '38', '&', 38),
  ('ASCLL', '39', ',', 39),
  ('ASCLL', '40', '(', 40),
  ('ASCLL', '41', ')', 41),
  ('ASCLL', '42', '*', 42),
  ('ASCLL', '43', '+', 43),
  ('ASCLL', '44', ',', 44),
  ('ASCLL', '45', '-', 45),
  ('ASCLL', '46', '.', 46),
  ('ASCLL', '47', '/', 47),
  ('ASCLL', '48', '0', 48),
  ('ASCLL', '49', '1', 49),
  ('ASCLL', '50', '2', 50),
  ('ASCLL', '51', '3', 51),
  ('ASCLL', '52', '4', 52),
  ('ASCLL', '53', '5', 53),
  ('ASCLL', '54', '6', 54),
  ('ASCLL', '55', '7', 55),
  ('ASCLL', '56', '8', 56),
  ('ASCLL', '57', '9', 57),
  ('ASCLL', '58', ':', 58),
  ('ASCLL', '59', ';', 59),
  ('ASCLL', '60', '<', 60),
  ('ASCLL', '61', '=', 61),
  ('ASCLL', '62', '>', 62),
  ('ASCLL', '63', '?', 63),
  ('ASCLL', '64', '@', 64),
  ('ASCLL', '65', 'A', 65),
  ('ASCLL', '66', 'B', 66),
  ('ASCLL', '67', 'C', 67),
  ('ASCLL', '68', 'D', 68),
  ('ASCLL', '69', 'E', 69),
  ('ASCLL', '70', 'F', 70),
  ('ASCLL', '71', 'G', 71),
  ('ASCLL', '72', 'H', 72),
  ('ASCLL', '73', 'I', 73),
  ('ASCLL', '74', 'J', 74),
  ('ASCLL', '75', 'K', 75),
  ('ASCLL', '76', 'L', 76),
  ('ASCLL', '77', 'M', 77),
  ('ASCLL', '78', 'N', 78),
  ('ASCLL', '79', 'O', 79),
  ('ASCLL', '80', 'P', 80),
  ('ASCLL', '81', 'Q', 81),
  ('ASCLL', '82', 'R', 82),
  ('ASCLL', '83', 'S', 83),
  ('ASCLL', '84', 'T', 84),
  ('ASCLL', '85', 'U', 85),
  ('ASCLL', '86', 'V', 86),
  ('ASCLL', '87', 'W', 87),
  ('ASCLL', '88', 'X', 88),
  ('ASCLL', '89', 'Y', 89),
  ('ASCLL', '90', 'Z', 90),
  ('ASCLL', '91', '[', 91),
  ('ASCLL', '92', '/', 92),
  ('ASCLL', '93', ']', 93),
  ('ASCLL', '94', '^', 94),
  ('ASCLL', '95', '_', 95),
  ('ASCLL', '96', '、', 96),
  ('ASCLL', '97', 'a', 97),
  ('ASCLL', '98', 'b', 98),
  ('ASCLL', '99', 'c', 99),
  ('ASCLL', '100', 'd', 100),
  ('ASCLL', '101', 'e', 101),
  ('ASCLL', '102', 'f', 102),
  ('ASCLL', '103', 'g', 103),
  ('ASCLL', '104', 'h', 104),
  ('ASCLL', '105', 'i', 105),
  ('ASCLL', '106', 'j', 106),
  ('ASCLL', '107', 'k', 107),
  ('ASCLL', '108', 'l', 108),
  ('ASCLL', '109', 'm', 109),
  ('ASCLL', '110', 'n', 110),
  ('ASCLL', '111', 'o', 111),
  ('ASCLL', '112', 'p', 112),
  ('ASCLL', '113', 'q', 113),
  ('ASCLL', '114', 'r', 114),
  ('ASCLL', '115', 's', 115),
  ('ASCLL', '116', 't', 116),
  ('ASCLL', '117', 'u', 117),
  ('ASCLL', '118', 'v', 118),
  ('ASCLL', '119', 'w', 119),
  ('ASCLL', '120', 'x', 120),
  ('ASCLL', '121', 'y', 121),
  ('ASCLL', '122', 'z', 122),
  ('ASCLL', '123', '{', 123),
  ('ASCLL', '124', '|', 124),
  ('ASCLL', '125', '}', 125),
  ('ASCLL', '126', '`', 126),
  ('ASCLL', '127', 'DEL', 127);