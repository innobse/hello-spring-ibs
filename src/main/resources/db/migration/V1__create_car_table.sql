create table cars
(
    id serial not null,
    fabric varchar(32),
    model varchar(32),
    year int,
    vin varchar(16)
);

create unique index cars_id_uindex
	on cars (id);

alter table cars
    add constraint cars_pk
        primary key (id);

