--liquibase formatted sql

--changeset abinash(create):1721405189607
--preconditions onFail:WARN
create table company (
    id int primary key,
    address varchar(255)
);

--other element