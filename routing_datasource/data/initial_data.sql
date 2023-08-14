-- 接続先1
CREATE DATABASE db1;

CREATE TABLE table1 (
  id int PRIMARY KEY AUTO_INCREMENT,
  name varchar(32) NOT NULL
);

INSERT INTO table1(name) VALUES('名前1');

-- 接続先2
CREATE DATABASE db2;

CREATE TABLE table1 (
  id int PRIMARY KEY AUTO_INCREMENT,
  name varchar(32) NOT NULL
);

INSERT INTO table1(name) VALUES('name1');
