drop table if exists `order`,payment,orderitem;

create table `order`(
	id bigint not null auto_increment primary key,
	customerid varchar(256) not null,
	sellerid varchar(256) not null,
	description varchar(512),
	totalprice decimal not null,
	createtime timestamp not null,
	liveduration integer default 0,
	callbackurl varchar(512),
	state varchar(256) not null
);

create table orderitem(
	id bigint not null auto_increment primary key,
	name varchar(256) default 'Unknown',
	externalid varchar(256) not null,
	orderid bigint,
	foreign key (orderid) references `order`(id)
);

create table payment(
	id bigint not null auto_increment primary key,
	amount decimal not null,
	paytime timestamp not null,
	fromaccount varchar(256) not null,
	toaccount varchar(256) not null,
	referencetext varchar(512),
	note varchar(512),
	state varchar(256) not null,
	`type` varchar(256) not null,
	orderid bigint,
	foreign key (orderid) references `order`(id)
);
