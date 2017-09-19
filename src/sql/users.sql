drop table if exists users;

create table users (
	id BIGSERIAL primary key not null
	,first_name VARCHAR(255) not null
	,last_name  VARCHAR(255) not null
	,email VARCHAR(255) unique not null
	,password VARCHAR(255) not null
);