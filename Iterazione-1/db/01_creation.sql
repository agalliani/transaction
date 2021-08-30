use transaction;

create table proposal(
id int not null primary key auto_increment,
name varchar(90),
description varchar(250),
email varchar(99) not null,
recipient varchar(99) not null,
status enum('CONFIRMED', 'DECLINED', 'PENDING')
);