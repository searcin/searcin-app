DROP TABLE if exists categories  

CREATE TABLE categories	(id int not null,	
name varchar(255) not null)
	
ALTER TABLE categories ADD PRIMARY KEY (id)


ALTER TABLE categories MODIFY COLUMN id INT AUTO_INCREMENT


ALTER TABLE categories ADD INDEX (id)

SELECT * FROM categories