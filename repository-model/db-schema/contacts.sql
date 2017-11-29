drop table if exists contacts

create table contacts
	(id int not null,
	vendor_id int not null,
	name varchar(255),
	phone varchar(50),
	mobile varchar(50),
	email varchar(255),
	website varchar(255),
	facebook varchar(255),
	twitter varchar(255))



ALTER TABLE contacts ADD PRIMARY KEY (id)

ALTER TABLE contacts MODIFY COLUMN id INT AUTO_INCREMENT

ALTER TABLE contacts ADD FOREIGN KEY (vendor_id) REFERENCES vendors(id)

select * from contacts


ALTER TABLE contacts ADD INDEX (id)


ALTER TABLE contacts ADD INDEX (vendor_id)