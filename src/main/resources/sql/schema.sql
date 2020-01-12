create table user(
    user_num INT IDENTITY(1,1) primary key ,
    id varchar(20) not null unique,
    email varchar2(50) not null unique,
    password varchar2(100) not null,
    nickname varchar2(15) not null unique,
    registered_at DATETIME default (CURRENT_TIMESTAMP),
    updated_at DATETIME default (CURRENT_TIMESTAMP)
);
create table user_auth(
    user_num INT IDENTITY(1,1) not null primary key,
    authority varchar2(50) not null,
    registered_at DATETIME default (CURRENT_TIMESTAMP),
    updated_at DATETIME default (CURRENT_TIMESTAMP)
);
create table board(
    board_num INT IDENTITY(1,1) primary key,
    title varchar2(40) not null,
    content varchar2(1500),
    user_num INT CONSTRAINT board_email_fk REFERENCES user(user_num) on delete set null,
    registered_at DATETIME default (CURRENT_TIMESTAMP),
    updated_at DATETIME default (CURRENT_TIMESTAMP)
);
create table reply(
    reply_num INT IDENTITY(1,1) primary key,
    board_num INT CONSTRAINT reply_board_num_fk REFERENCES board(board_num) on delete set null,
    user_num INT CONSTRAINT reply_user_num_fk REFERENCES user(user_num) on delete set null,
    content varchar2(500) not null,
    registered_at DATETIME default (CURRENT_TIMESTAMP),
    updated_at DATETIME default (CURRENT_TIMESTAMP)
);