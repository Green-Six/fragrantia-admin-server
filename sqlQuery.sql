create database fragrantiatest;
drop database fragrantiatest;
show databases;

drop table ADMIN;
use fragrantia;
use fragrantiatest;
select database();

select * from ADMIN;
select * from NOTICE;
-- delete from NOTICE
-- where admin_id is NULL; 
delete from ADMIN;

insert into NOTICE(title, content, created_at, admin_id)
select title, content, CURRENT_TIMESTAMP, admin_id from NOTICE;

select count(id) from NOTICE;


create table ADMIN (
  id int Primary Key AUTO_INCREMENT,
  email varchar(30) not null unique,
  password varchar(300) not null,
  name varchar(5) not null,
  branch varchar(10) not null,
  created_at date not null
);

create table NOTICE (
  id int Primary Key AUTO_INCREMENT,
  title varchar(30) not null,
  content varchar(500) not null,
  created_at date not null,
  view int,
  admin_id int, 
  foreign key (admin_id) references ADMIN(id)
);

create table PRODUCT (
  id int Primary Key AUTO_INCREMENT,
  name varchar(30) not null,
  price int not null,
  category varchar(20) not null,
  detail varchar(500) not null,
  created_at date not null,
  file varchar(100) not null,
  view integer,
  admin_id int,
  foreign key (admin_id) references ADMIN (id)
);

create table STORE (
  id int Primary Key AUTO_INCREMENT,
  latitude double,
  longitude double,
  zip int not null,
  address varchar(30) not null,
  address_detail varchar(100) not null,
  name varchar(15) not null,
  detail varchar(500) not null,
  phone int,
  file varchar(100) not null
);

create table CUSTOMER_SERVICE (
  id int Primary Key AUTO_INCREMENT,
  title varchar(30) not null,
  category varchar(20) not null,
  name varchar(5) not null,
  email varchar(30) not null,
  detail varchar(500) not null,
  created_at date not null,
  file varchar(100) not null
);


-- drop table STORE;

-- insert into member(name) values('spring');
-- insert into member(name) values('spring2');

-- delete from member where id=15;

