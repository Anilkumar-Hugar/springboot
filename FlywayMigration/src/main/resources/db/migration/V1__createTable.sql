CREATE TABLE person
(
    id integer NOT NULL,
    city varchar(255),
    name varchar(255) ,
    pincode integer,
    CONSTRAINT person_pkey PRIMARY KEY (id)
)