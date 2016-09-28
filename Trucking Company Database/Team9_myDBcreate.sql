create table Employee
( EName VARCHAR(30) NOT NULL,
  Title VARCHAR(20) Default 'Driver'
	Check(Title='Driver' or Title='Operator'),
  Hourly_Pay int,
  EAddress VARCHAR(40),
  Division VARCHAR(10) Default 'Fluid'
	Check(Division='Fluid' or Division='Operations'
	or Division='Dirt'),
  DOB varchar(10),
  SSN VARCHAR(11) NOT NULL PRIMARY KEY,
  Start_Date VARCHAR(10));


create table Customer
( CName VARCHAR(30) NOT NULL PRIMARY KEY,
  CAddress VARCHAR(40),
  Phone VARCHAR(20));


create table Job
( JType VARCHAR(14) Default 'water hauling'
	Check(JType='water hauling' or JType='location build' or JType='oil transfer' or JType='dirt hauling' or JType='pit building') NOT NULL,
  Ticket# int NOT NULL PRIMARY KEY,
  Hours int,
  Cost float,
  Name VARCHAR(30),
  Job_Date VARCHAR(10),
  Fuel_Used int,
  FOREIGN KEY(Name) REFERENCES Customer(CName) on delete cascade);


create table Truck
( T# int NOT NULL PRIMARY KEY,
  Driver_SSN VARCHAR(11),
  Miles int,
  FOREIGN KEY(Driver_SSN) REFERENCES Employee(SSN) on delete cascade);


create table Equipment
( Equipment# int NOT NULL PRIMARY KEY,
  EType VARCHAR(16) Default 'Dozer'
	Check(EType='Dozer' or EType='Motor Grader' or EType='Backhoe' or EType='Front End Loader' or EType='Packer') NOT NULL,
  Operator_SSN VARCHAR(11),
  FOREIGN KEY(Operator_SSN) REFERENCES Employee(SSN) on delete cascade);


create table Trucking_Job
( Well VARCHAR(30),
  TJob# int NOT NULL,
  FOREIGN KEY(TJob#) REFERENCES Job(Ticket#) on delete cascade,
  Truck# int,
  FOREIGN KEY(Truck#) REFERENCES Truck(T#) on delete cascade);


create table Construction_Job
( Location VARCHAR(30),
  CJob# int NOT NULL,
  FOREIGN KEY(CJob#) REFERENCES Job(Ticket#) on delete cascade,
  Equip# int,
  FOREIGN KEY(Equip#) REFERENCES Equipment(Equipment#) on delete cascade);
