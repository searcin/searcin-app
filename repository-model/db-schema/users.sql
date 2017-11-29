drop table if exists users

create table users
	(id int not null,
	name varchar(255) not null,
	username varchar(255) not null,
	password varchar(255) not null)
	
alter table users add primary key (id)

alter table users modify column id int auto_increment

select * from users

insert into users (`name`,`username`,`password`) values ("Gautham Manivannan","gautham","gautham123")
insert into users (`name`,`username`,`password`) values ("Dinesh Babu","dinesh","dinesh123")
insert into users (`name`,`username`,`password`) values ("Anonymous","user","user123")