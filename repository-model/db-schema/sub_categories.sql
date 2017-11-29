create table sub_categories
	(id int not null,
	name varchar(255) not null,
	category_id int not null)
	
		
ALTER TABLE sub_categories ADD PRIMARY KEY (id)

ALTER TABLE sub_categories MODIFY COLUMN id INT AUTO_INCREMENT

ALTER TABLE sub_categories ADD FOREIGN KEY (category_id) REFERENCES categories(id)

SELECT * FROM sub_categories

ALTER TABLE sub_categories ADD INDEX (id)

ALTER TABLE sub_categories ADD INDEX (category_id)