create table class_range
	(id int not null,
	name varchar(255) not null)
	
	
	
alter table class_range add primary key(id)


ALTER TABLE class_range MODIFY COLUMN id INT AUTO_INCREMENT

ALTER TABLE class_range ADD INDEX (id)

insert into class_range (name) values ("A+")
insert into class_range (name) values ("A")
insert into class_range (name) values ("B")
insert into class_range (name) values ("C")
insert into class_range (name) values ("D")
insert into class_range (name) values ("E")
insert into class_range (name) values ("F")

select * from class_range