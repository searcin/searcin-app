create table areas
	(id int not null,
	name varchar(255) not null)

ALTER TABLE areas ADD PRIMARY KEY (id)

ALTER TABLE areas MODIFY COLUMN id INT AUTO_INCREMENT


select * from areas


ALTER TABLE areas ADD INDEX (id)