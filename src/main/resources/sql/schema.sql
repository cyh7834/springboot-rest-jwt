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
