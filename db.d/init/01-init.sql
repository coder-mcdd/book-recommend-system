create table if not exists tb_account
(
    id            int auto_increment comment 'id'
        primary key,
    username      varchar(255) null comment '用户名',
    password      varchar(255) null comment '密码',
    email         varchar(255) null comment '邮箱',
    role          varchar(255) null comment '角色',
    avatar        varchar(255) null comment '头像',
    register_time datetime     null comment '注册时间',
    constraint unique_email
        unique (email),
    constraint unique_username
        unique (username)
)
    row_format = DYNAMIC;


create table if not exists tb_book
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    title            VARCHAR(255) NOT NULL,
    author           VARCHAR(255) NOT NULL,
    publisher        VARCHAR(255),
    publication_date DATE,
    genre            VARCHAR(100),
    isbn             VARCHAR(20),
    cover_image      VARCHAR(255),
    summary          TEXT,
    rating           BIGINT
);

