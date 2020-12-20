create table account (
    id serial primary key,
    first_name text,
    other_name text,
    created_date timestamp,
    date_of_birth date,
    age integer,
    salary decimal
);


select * from account
where
order
group
