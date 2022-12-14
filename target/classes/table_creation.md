create table users (
id serial primary key not null,
email varchar (50) not null unique,
password varchar (50) not null
);

create table accounts (
id serial primary key not null,
name varchar (50) not null,
balance decimal,
description varchar (500),
creation_date timestamp with time zone default current_timestamp,
user_id int references users (id)
);

create table transactions(
id serial primary key not null,
amount decimal,
description varchar (500),
type varchar (50),
account_id int references accounts (id)
)