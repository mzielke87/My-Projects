-------------Script to insert sample data into all tables-------------------
----------Customer---------------
insert into Customer values('Apache', '123 Fake St', '580-772-1234');
insert into Customer values('Chaparral', '8000 Hwy 3', '817-384-5681');
insert into Customer values('Mewbourne', '712 Ave H', '972-922-1490');
insert into Customer values('Midstates', '8123 Beaver Ave', '580-706-1123');
insert into Customer values('Enervest', '800 Hurly St', '866-665-3407');
insert into Customer values('Overflow', '991 Ave B', '580-542-8865');
insert into Customer values('BP', '2200 Perryton Rd', '580-789-5744');


----------Job-------------------
insert into Job values('water hauling', 56782, 2, 200, 'Mewbourne', '20141014', 20);
insert into Job values('oil transfer', 12592, 3, 300, 'Midstates', '20140902', 30);
insert into Job values('pit building', 66732, 12, 1500, 'Chaparral', '20140904', 40);
insert into Job values('water hauling', 78112, 2, 200, 'Mewbourne', '20141001', 25);
insert into Job values('dirt hauling', 67234, 6, 800, 'Overflow', '20141018', 35);
insert into Job values('water hauling', 90517, 2, 200, 'Mewbourne', '20140920', 45);
insert into Job values('location build', 75844, 24, 5000, 'Apache', '20140924', 25);


----------Employee--------------
insert into Employee values('Scott', 'Driver', 16, '7000 Ave G', 'Fluid', '19870914', '123-45-6789', '20140102');
insert into Employee values('James', 'Driver', 17, 'Rt 1 Box 7', 'Fluid', '19680822', '234-56-7890', '20140222');
insert into Employee values('Dave', 'Operator', 18, '2425 Balko Rd', 'Dirt', '19850204', '345-67-8901', '20140511');
insert into Employee values('Kenny', 'Driver', 19, '512 Ave H', 'Fluid', '19551127', '456-78-9012', '20131112');
insert into Employee values('Steve', 'Driver', 20, '7117 Apollo St', 'Fluid', '19770219', '567-89-0123', '20120512');
insert into Employee values('Juan', 'Driver', 15, '82 Hwy 9', 'Fluid', '19880622', '678-90-1234', '20140303');
insert into Employee values('Cody', 'Operator', 20, '7182 Liberal Rd', 'Dirt', '19820819', '789-01-2345', '20130910');

----------Equipment------------
insert into Equipment values(1, 'Dozer', '234-56-7890');
insert into Equipment values(2, 'Backhoe', '678-90-1234');
insert into Equipment values(3, 'Dozer', '345-67-8901');
insert into Equipment values(4, 'Motor Grader', '567-89-0123');
insert into Equipment values(5, 'Front End Loader', '345-67-8901');
insert into Equipment values(6, 'Packer', '123-45-6789');
insert into Equipment values(7, 'Dozer', '456-78-9012');

---------Truck-----------------
insert into Truck values(1, '789-01-2345', 400212);
insert into Truck values(2, '123-45-6789', 52145);
insert into Truck values(3, '456-78-9012', 3204);
insert into Truck values(4, '234-56-7890', 16879);
insert into Truck values(5, '789-01-2345', 309521);
insert into Truck values(6, '345-67-8901', 85600);
insert into Truck values(7, '567-89-0123', 8414);

---------Trucking_Job----------
insert into Trucking_Job values('Mcgaraugh #1', 56782, 1);
insert into Trucking_Job values('Alpine', 12592, 2);
insert into Trucking_Job values('Evans', 78112, 3);
insert into Trucking_Job values('Frontier', 67234, 4);
insert into Trucking_Job values('Jordan', 90517, 5);
insert into Trucking_Job values('Haskin', 12345, 6);
insert into Trucking_Job values('Northwest', 66778, 7);

---------Construction_Job-------
insert into Construction_Job values('Richardson', 66732, 1);
insert into Construction_Job values('Pioneer', 75844, 2);
insert into Construction_Job values('Sanford', 66777, 3);
insert into Construction_Job values('Neuhaus', 81212, 4);
insert into Construction_Job values('Phillips', 14567, 5);
insert into Construction_Job values('McCarthy', 77878, 6);
insert into Construction_Job values('Fasken', 40410, 7);