------------1.All employees who make less than $17/hr get a $1/hr raise-----------
update Employee
set hourly_pay=hourly_pay + 1
where SSN in
	(select SSN
	 from Employee
	 where hourly_pay < 17);
commit;

-----------2.New customers using the company for work needed done----------------
insert into Customer values('Texaco', '737 Hathaway Dr', '817-542-5789');
insert into Customer values('Execon', '3324 West Hwy 7', '580-874-5329');
insert into Customer values('Samsonite', 998 Hampton Pl', '214-820-0014');
insert into Customer values('Trenton', '2342 Jameson Ave', '806-214-3674');
commit;

-----------3.The company wants to get rid of all trucks that have over 300,000 miles-------
delete from Truck
where Miles in
	(select Miles
	 from Truck
	 where Miles>300000);
commit;