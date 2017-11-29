drop table if exists vendor_services

create table vendor_services
	(vendor_id int not null,
	service_id int not null)
	

ALTER TABLE vendor_services ADD FOREIGN KEY (vendor_id) REFERENCES vendors(id)

ALTER TABLE vendor_services ADD FOREIGN KEY (service_id) REFERENCES services(id)


ALTER TABLE vendor_services ADD INDEX (service_id)
ALTER TABLE vendor_services ADD INDEX (vendor_id)

select * from vendor_services