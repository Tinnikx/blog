CREATE TABLE `user` (
     id BIGINT auto_increment NOT NULL primary key,
     uuid varchar(100) NOT NULL unique,
     email varchar(100) NOT NULL unique,
     nickname varchar(100) NOT NULL unique,
     password varchar(100) NOT NULL,
     active_status BOOL default true not null,
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
     last_login_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
     source varchar(100) DEFAULT 'STANDARD' not null
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `role` (
     id INT auto_increment NOT NULL primary key,
     name varchar(100) NOT NULL unique
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

insert into role(name) values ('ROLE_SUPER');
insert into role(name) values ('ROLE_ADMIN');
insert into role(name) values ('ROLE_VIP');
insert into role(name) values ('ROLE_STANDARD');


CREATE TABLE `user_role` (
     user_uuid varchar(100) NOT NULL,
     role_id varchar(100) NOT NULL
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `request_permission` (
     id BIGINT auto_increment NOT NULL primary key,
     user_uuid varchar(100) NOT NULL,
     reason varchar(100) NOT NULL,
     status varchar(100) NOT NULL,
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
     updated_at TIMESTAMP DEFAULT NULL,
     reject_reason varchar(100) DEFAULT NULL,
     executor_uuid varchar(100) DEFAULT NULL
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;
