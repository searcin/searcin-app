drop table if exists services

create table services
	(id int not null,
	name varchar(255) not null,
	description text)
	
alter table services add primary key(id)

alter table services modify column id int auto_increment

select * from services

ALTER TABLE services ADD INDEX (id)