drop table if exists customer;
create table customer(id serial primary key, first varchar(255) not null, last varchar(255) not null);
