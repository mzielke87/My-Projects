---------1.Find name and amount spent by each customer------------
select C.CName, sum(J.Cost) as Total_Spent
from Customer C, Job J
where C.CName=J.Name
group by C.CName;

--------2.Find the hours worked by each employee-----------------
select sum(J.hours) as Hours_Worked, E.EName
from Employee E, Job J
where J.hours in ((select T.Driver_SSN, TJ.Truck#, TJ.TJob#, J.Ticket#
	 from Trucking_Job TJ, Truck T, Job J
	 where TJ.Truck#=T.T# AND T.Driver_SSN=E.SSN AND TJ.TJob#=J.Ticket#) join  
		( select E.Operator_SSN, CJ.Equip#, CJ.CJob#, J.Ticket#
		  from Construction_Job CJ, Equipment E, Job J
		  where CJ.Equip#=E.Equipment# AND E.Operator_SSN=E.SSN AND CJ.CJob#=J.Ticket#))
group by E.EName;

--------3.Find the jobs each equipment has worked---------
select E.Equipment#, J.Ticket#
from Equipment E, Job J, Construction_Job CJ
where CJ.Equip#=E.Equipment# AND CJ.Job#=J.Ticket#
group by J.Ticket#;

--------4.Find every employee that makes more than $20/hr-------
select EName, Hourly_Pay
from Employee
group by EName
having count(Hourly_Pay)>20;

--------5.Find the jobs each truck has worked---------
select T.T#, J.Ticket#
from Truck T, Job J, Trucking_Job TJ
where TJ.Truck#=T.T# AND TJ.Job#=J.Ticket#
group by J.Ticket#;

--------6.Find type of job at each contruction jobs location-------
select J.Type, CJ.Location
from Job J, Construction_Job CJ
where CJ.CJob#=J.Ticket#
group by CJ.Location;

--------7.Find type of job at each trucking jobs well-------
select J.Type, TJ.Well
from Job J, Trucking_Job TJ
where TJ.TJob#=J.Ticket#
group by TJ.Well;