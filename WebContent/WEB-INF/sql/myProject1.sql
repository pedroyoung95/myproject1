-----회원 테이블
DROP TABLE register;

CREATE TABLE register(
    registerid VARCHAR2(50) PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    password VARCHAR2(50) NOT NULL,
    regdate DATE NOT NULL
);
SELECT * FROM register;


-----게시글 테이블
--DROP TABLE content;

CREATE TABLE content(
    content_no NUMBER GENERATED AS IDENTITY,
    writer_id VARCHAR2(50) NOT NULL,
    writer_name VARCHAR2(50) NOT NULL,
    title VARCHAR2(255) NOT NULL,
    body VARCHAR2(4000),
    regdate DATE NOT NULL,
    moddate DATE NOT NULL,
    read_cnt NUMBER,
    PRIMARY KEY(content_no)
);

SELECT * FROM content;

-----댓글
--DROP TABLE reply;
--ROLLBACK;

CREATE TABLE reply(
    replyid NUMBER GENERATED AS IDENTITY,
    registerid VARCHAR2(50) NOT NULL,
    content_no NUMBER NOT NULL,
    body VARCHAR2(1000) NOT NULL,
    regdate DATE NOT NULL,
    moddate DATE NOT NULL,
    PRIMARY KEY(replyid)
);
SELECT * FROM reply;