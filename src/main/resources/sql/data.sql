insert into user values(1, 'id1', 'admin@mymail.com', '{bcrypt}$2a$10$vGx7dnqWIUrHXjFG8BpF6u1qRvmZkS3TzrSJr4l7GniPdRm4ExEX.', 'admin', GETDATE(), GETDATE());
insert into user values(2, 'id2', 'mymail2@mymail.com', '{bcrypt}$2a$10$vGx7dnqWIUrHXjFG8BpF6u1qRvmZkS3TzrSJr4l7GniPdRm4ExEX.', 'simple2', GETDATE(), GETDATE());
insert into user values(3, 'id3', 'mymail3@mymail.com', '{bcrypt}$2a$10$vGx7dnqWIUrHXjFG8BpF6u1qRvmZkS3TzrSJr4l7GniPdRm4ExEX.', 'simple3', GETDATE(), GETDATE());

insert into user_auth values(1, 'ROLE_ADMIN', GETDATE(), GETDATE());
insert into user_auth values(2, 'ROLE_USER', GETDATE(), GETDATE());
insert into user_auth values(3, 'ROLE_USER', GETDATE(), GETDATE());

insert into board values(1, 'ADMIN 작성', 'ADMIN 작성', 1, GETDATE(), GETDATE());
insert into board values(2, 'USER 작성', 'USER 작성', 2, GETDATE(), GETDATE());

insert into reply values(1, 1, 1, 'ADMIN 댓글', GETDATE(), GETDATE());
insert into reply values(2, 1, 2, 'USER 댓글', GETDATE(), GETDATE());