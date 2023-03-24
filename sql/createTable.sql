CREATE TABLE board (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       content TEXT,
                       writer VARCHAR(50) NOT NULL,
                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                       updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO board (title, content, writer)
VALUES ('스프링부트', '첫 번째 게시글의 내용입니다.', 'hwan'),
       ('mvc 패턴', '두 번째 게시글의 내용입니다.', 'rok'),
       ('게시판 만들기', '세 번째 게시글의 내용입니다.', 'lyn');
