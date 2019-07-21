alter table parking_order  MODIFY COLUMN closed_time TIMESTAMP;
alter table parking_order  MODIFY COLUMN created_time TIMESTAMP;

insert into parking_lot values (1, 30, 'lot-1', 'position-1');
insert into parking_lot values (2, 40, 'lot-2', 'position-2');
insert into parking_order values (1, '383830', systimestamp, null, 'orderNo001', 1, 1);
insert into parking_order values (2, '548011', systimestamp, null, 'orderNo002', 1, 1);
insert into parking_order values (3, '123456', systimestamp, null, 'orderNo001', 1, 2);
insert into parking_order values (4, '201907', systimestamp, null, 'orderNo002', 1, 2);