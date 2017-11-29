create table roles
	(id int not null,
	name varchar(50) not null)
	
alter table roles add primary key (id)

alter table roles modify column id int auto_increment

insert into roles (`name`) value ("ROLE_ADMIN")
insert into roles (`name`) value ("ROLE_USER")
insert into roles (`name`) value ("ROLE_VENDOR")

ALTER TABLE roles ADD INDEX (id)

select * from roles