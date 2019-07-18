
create table parking_lot
(
    id       int auto_increment primary key not null,
    capacity integer check (capacity >= 0),
    name     varchar(30) unique,
    position varchar(255)
);