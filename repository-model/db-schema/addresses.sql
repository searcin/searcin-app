drop table if exists addresses

create table addresses
	(id int not null,
	vendor_id int not null,
	area_id int not null,
	door_no varchar(255),
	building varchar(255),
	nearby varchar(255),
	address1 varchar(255),
	address2 varchar(255),
	pincode int,
	lat DECIMAL(10, 8), 
	lng DECIMAL(11, 8))
	
alter table addresses add primary key(id)

alter table addresses modify column id int auto_increment

ALTER TABLE addresses ADD FOREIGN KEY (vendor_id) REFERENCES vendors(id)

ALTER TABLE addresses ADD FOREIGN KEY (area_id) REFERENCES areas(id)


ALTER TABLE addresses ADD INDEX (id)

ALTER TABLE addresses ADD INDEX (vendor_id)

ALTER TABLE addresses ADD INDEX (area_id)

select * from addresses