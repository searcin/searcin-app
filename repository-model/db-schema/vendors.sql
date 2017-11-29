drop table if exists vendors

create table vendors
	(id int not null,
	name varchar(255) not null,
	description text,
	category_id int not null,
	sub_category_id int not null,
	class_range_id int not null,
	created_on datetime)
	
SHOW CREATE TABLE vendors;

delete from vendors

ALTER TABLE vendors ADD PRIMARY KEY (id)

ALTER TABLE vendors ADD COLUMN class_range_id int

ALTER TABLE vendors MODIFY COLUMN id INT AUTO_INCREMENT

UPDATE vendors SET class_range = 1

select * from vendors

alter table vendors drop `class_range`

drop table class_range

desc vendors

ALTER TABLE vendors ADD FOREIGN KEY (category_id) REFERENCES categories(id)

ALTER TABLE vendors ADD FOREIGN KEY (sub_category_id) REFERENCES sub_categories(id)

ALTER TABLE vendors ADD FOREIGN KEY (class_range_id) REFERENCES class_range(id)

ALTER TABLE vendors ADD INDEX (id)

ALTER TABLE vendors ADD INDEX (category_id)

ALTER TABLE vendors ADD INDEX (sub_category_id)

ALTER TABLE vendors ADD INDEX (class_range_id)

SELECT * FROM vendors

desc vendors

ALTER TABLE vendors DROP FOREIGN KEY `vendors_ibfk_4`

ALTER TABLE vendors
  DROP COLUMN class_range