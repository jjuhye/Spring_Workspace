create database board_mybatis;
use board_mybatis;
CREATE TABLE board(
	num INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    writer VARCHAR(20),
    email VARCHAR(20),
    subject VARCHAR(20),
    password VARCHAR(20),
    reg_date DATETIME,
    ref INT,
    re_step INT,
    re_level INT,
    readcount INT,
    content VARCHAR(100)
);
drop table board;
SELECT * FROM board;
INSERT INTO board (writer, email, subject, password, reg_date, ref, re_step, re_level, readcount, content)
VALUES('qwer', 'qwer@naver.com', 'test1', '1234', now(), 1, 1, 1, 0, 'test1');

INSERT INTO board (writer, email, subject, password, reg_date, ref, re_step, re_level, readcount, content)
VALUES('abcd', 'abcd@naver.com', 'test2', '1234', now(), 2, 1, 1, 0, 'test2');


