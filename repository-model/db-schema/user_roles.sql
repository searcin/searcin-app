create table user_roles
	(user_id int not null,
	role_id int not null)
	


ALTER TABLE user_roles ADD FOREIGN KEY (user_id) REFERENCES users(id)

ALTER TABLE user_roles ADD FOREIGN KEY (role_id) REFERENCES roles(id)

insert into user_roles (`user_id`,`role_id`) values (3,2)

select * from user_roles