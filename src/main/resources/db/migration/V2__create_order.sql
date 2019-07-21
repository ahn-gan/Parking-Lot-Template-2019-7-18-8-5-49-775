create table parking_order
(
    id             bigint primary key not null,
    car_no         varchar(255),
    closed_time    date,
    created_time   date,
    order_no       varchar(255),
    status         int default 1,
    parking_lot_id bigint
);

alter table parking_order
    add constraint FK_PARKING_LOT_ID foreign key (parking_lot_id) references parking_lot(id);