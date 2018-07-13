create table users (
     id bigserial primary key,
     email_id varchar(255) not null unique,
    name varchar(50) not null
);

create index users_index_email_id on users(email_id);
create index users_name on users(name);
