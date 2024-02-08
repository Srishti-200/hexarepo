/*  --------------------------------------------------TASK 1 -------------------------------------------------------------*/
-- User Table: 
create table User(UserID int primary key, 
Uname varchar(255) , Email VARCHAR(255) UNIQUE NOT NULL,
Password varchar(255),ContactNumber varchar(255),Address text);

-- Courier Table 
create table Couriers(CourierID int primary key, UserID int,FOREIGN KEY (UserID) REFERENCES User(UserID),ServiceID int ,FOREIGN KEY (ServiceID) REFERENCES Courierservices(ServiceID),
Sendername varchar(255),Senderadderss varchar(255),ReceiverName VARCHAR(255), ReceiverAddress TEXT, 
Weight DECIMAL(5, 2), Status VARCHAR(50), TrackingNumber VARCHAR(20) UNIQUE, DeliveryDate DATE);
  
-- CourierServices Table 
create table CourierServices (ServiceID INT PRIMARY KEY, ServiceName VARCHAR(100), Cost DECIMAL(8, 2)); 

-- Employee Table
 create table Employee (EmployeeID INT PRIMARY KEY, Name VARCHAR(255), Email VARCHAR(255) UNIQUE, 
 ContactNumber VARCHAR(20), Role VARCHAR(50), Salary DECIMAL(10, 2));

-- Location Table
create table Location (LocationID INT PRIMARY KEY, LocationName VARCHAR(100), Address TEXT);

-- Payment Table
create table Payment (PaymentID INT PRIMARY KEY,  Amount DECIMAL(10, 2), PaymentDate DATE, 
CourierID int, FOREIGN KEY (CourierID) REFERENCES Couriers(CourierID),
LocationID int ,FOREIGN KEY (LocationID) REFERENCES Location(LocationID));

-- Order table 
CREATE TABLE orders (UserID int,OrderID INT PRIMARY KEY,EmployeeID int,FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID),
 Quantity INT, OrderDate DATE, Deliverydate DATE, TotalAmount DECIMAL(10, 2),Handeledemployee varchar(255),deliveredlocation varchar(255));
ALTER TABLE orders ADD COLUMN status varchar(255);

 INSERT INTO User (UserID, Uname, Email, Password, ContactNumber, Address) VALUES
    (1, 'John Doe', 'john@gmail.com', 'doe123', '136457921', '123 Main St'),
    (2, 'John Smith', 'josm@gmail.com', 'dmith456', '9876543210', '45 park avane '),
    (3, 'Priya', 'priya@gmail.com', 'pgre789', '9040584507', '789 Pine road'),
    (4, 'Kim', 'kim29@gmail.com', 'ksj636', '7366452179', '321 ramraj nagar'),
    (5, 'Ria', 'riaeva@remail.com', '56333cv', '9333414509', '654 Birch road'),
	(6, 'Zeba', 'zebs13@remail.com', 'passwxyz', '8697451203', '654 shastra street');
insert into User values (7, 'Harini', 'har12@remail.com', 'haren', '8697451452', '695 Gandhi street');
    
insert INTO Couriers (CourierID,UserID,ServiceID, SenderName, Senderadderss, ReceiverName, ReceiverAddress, Weight, Status, TrackingNumber, DeliveryDate)VALUES
    (1001,5,111, 'Ria','654 Birch road' , 'John Smith', '45 park avane ', 3.5, 'In Transit', 'AS9036', '2024-01-15'),
    (2002,3,222,'John Smith', '45 park avane ', 'Kim', '321 ramraj nagar', 2.0, 'Delivered', 'SK4569', '2024-01-10'),
    (3003,6,333,'Zeba' , '654 shastra street', 'Ria','654 Birch road' , 1.8, 'Pending', 'VB5563', '2024-01-17');
insert into Couriers values(4004,7,111,'harini' , '695 Gandhi street', 'John Doe','123 Main St' , 1.8, 'Delivered', 'YG786', '2024-01-05');
update Couriers SET DeliveryDate='2024-01-13' WHERE CourierID = 4004;
update Couriers SET DeliveryDate='2024-01-13' WHERE CourierID = 2002;
alter table Couriers add couriertype varchar(255);    
update Couriers SET couriertype='Standard Delivery' WHERE CourierID = 4004;
update Couriers SET couriertype='Express Delivery' WHERE CourierID = 2002;
update Couriers SET couriertype='Standard Delivery' WHERE CourierID = 1001;
update Couriers SET couriertype='Same-Day Delivery' WHERE CourierID = 3003;
update Couriers SET senderadderss='654 Birch road'where courierid=4004;
insert into CourierServices (ServiceID, ServiceName, Cost) VALUES
    (111, 'Standard Delivery', 10.99),
    (222, 'Express Delivery', 20.99),
    (333, 'Same-Day Delivery', 25.99);
   /* (4, 'Overnight Delivery', 25.99),
    (5, 'International Delivery', 50.99);*/
insert into Employee (EmployeeID, Name, Email, ContactNumber, Role, Salary) VALUES
    (1, 'Soobin', 'soobin15@gmail.com', '12223344560', 'Manager', 60000.00),
    (2, 'Yuna', 'yusa222@gmail.com', '6523142096', 'Courier Staff', 450000.00),
    (3, 'Ara', 'Aran23@gmail.com', '9092567175', 'Customer Service', 25000.00),
    (4, 'Ezhil', 'ezhi90@gmail.com', '8681901136', 'Driver', 15000.00),
    (5, 'Lisa', 'lalisa65@gmail.com', '7553612030', 'Warehouse Staff', 30000.00);
insert into Employee values(6, 'John Amrit','jhnm123@gmail.com',6536524260,'Staff',46000.00);
insert into Employee values(7,'Bravod John','brav234@gmail.com',7854963201,'Driver',15000.00); 
update employee set role='Staff' where employeeID =2;
INSERT INTO Location (LocationID, LocationName, Address) VALUES
    (1, 'Shop', '45 park avane '),
    (2, 'Office','321 ramraj nagar');
    INSERT INTO Location (LocationID, LocationName, Address)
VALUES (3, 'Store', '789 Pine Rd'),(4, 'office', '321 Elm Ln');
INSERT INTO Location (LocationID, LocationName, Address)
VALUES  (5, 'Office', '654 Birch Blvd');
insert into Payment (PaymentID, Amount, PaymentDate,CourierID, LocationID) values
    (1,  15.99, '2024-01-15',1001,1),
    (2,  25.99, '2024-01-16',2002,2);
  insert into Payment values (3, 52.99, '2024-01-17',3003,3);
  insert into payment values (4, 60.99, '2024-01-18',4004,4);
   /* (5, 5, 5, 40.99, '2024-01-19');   */
   UPDATE Payment SET Amount = 2500.00 WHERE PaymentID=1;
   UPDATE Payment SET Amount = 7000.00 WHERE PaymentID=2;
   UPDATE Payment SET Amount = 6542.00 WHERE PaymentID=3;
   UPDATE Payment SET Amount =3264.00 WHERE PaymentID=4;
   UPDATE Payment SET Amount =1000.00 WHERE PaymentID=5;
   UPDATE Payment SET LocationID =1 WHERE PaymentID=4;
   UPDATE payment SET PaymentDate='2024-01-17' WHERE paymentID=4;
   select * from payment;
   insert into orders (userID, OrderID ,EmployeeID, Quantity , OrderDate , Deliverydate , TotalAmount ,Handeledemployee,deliveredlocation,status) values
   (5,1,2,3.5,'2023-12-15','2024-01-15', 15.99,'Yuna','park avane','intransit'),
   (2,2,6,2.0,'2023-12-05','2024-01-10',25.99,'John Amrit','321 ramraj nagar','delivered');
   drop table user;
   drop table location;
   drop table payment;
   drop table couriers;
   drop table courierservices;
   drop table orders;
   
-----------------------------------------------------------------------------------------------------------------------------------------------
/*------------------------------------------------------------TASK 2-SELECT,WHERE--------------------------------------------------------------*/
	  /* 1. List all customers: */
 SELECT * FROM User;
          /* 2.List all orders for a specific customer */
 SELECT *from Couriers WHERE CourierID = 1;
		   /* 3. List all couriers */
 select * from Couriers;
		   /* 4.List all packages for a specific order: */ 
SELECT *from  Couriers WHERE CourierID = 3;
       -- 5. List all deliveries for a specific courier:
SELECT *from  Location WHERE LocationName='Office';
        -- 6. List all undelivered packages: 
 select * from Couriers where status='pending';
        -- 7. List all packages that are scheduled for delivery today: 
select * from Couriers where DeliveryDate = curdate();
        -- 8. List all packages with a specific status: 
select * from Couriers where status ='Delivered';
	    -- 9. Calculate the total number of packages for each courier. 
select CourierID,COUNT(*) as totalpackages from Couriers group by CourierID;
   /* 10. Find the average delivery time for each courier --------*/
select UserID,AVG(DATEDIFF(DeliveryDate, OrderDate)) AS AvgDeliveryTime FROM orders 
WHERE DeliveryDate IS NOT NULL AND OrderDate IS NOT NULL GROUP BY UserID;
   -- 11. List all packages with a specific weight range: 
select * from Couriers where weight between 1.0 and 2.0;
      -- 12. Retrieve employees whose names contain 'John' 
select * from Employee where Name LIKE '%John%'; 
       -- 13. Retrieve all courier records with payments greater than $50.
select *from payment where Amount > 50;
-----------------------------------------------------------------------------------------------------------------------------------------------
/*-------------------------------------Task 3: GroupBy, Aggregate Functions, Having, Order By, where-----------------------------------------*/
        -- 14. Find the total number of couriers handled by each employee. 
SELECT  Handeledemployee, COUNT(OrderID) AS TotalOrdersHandled FROM orders GROUP BY Handeledemployee;
		--  15. Calculate the total revenue generated by each location 
SELECT l.LocationID,l.LocationName, SUM(p.Amount) AS TotalRevenue FROM Location l
JOIN Payment p ON l.LocationID = p.LocationID GROUP BY l.LocationID, l.LocationName;
		-- 16. Find the total number of couriers delivered to each location. 
SELECT  deliveredlocation,COUNT(OrderID) AS TotalOrdersDelivered
FROM orders WHERE status = 'Delivered' GROUP BY deliveredlocation;
	   -- 17. Find the courier with the highest average delivery time: 
SELECT c.OrderID, AVG(DATEDIFF(c.DeliveryDate, c.OrderDate)) AS AvgDeliveryTime
FROM orders c GROUP BY c.OrderID
ORDER BY AvgDeliveryTime DESC LIMIT 1;
       -- 18. Find Locations with Total Payments Less Than a Certain Amount 
SELECT loc.LocationID, loc.LocationName, SUM(p.Amount) AS TotalPayments,c.courierid,p.paymentid
FROM Location loc
JOIN Payment p ON loc.LocationID = p.LocationID
Join couriers c on p.courierid = c.courierid
GROUP BY loc.LocationID, loc.LocationName,p.amount,c.courierid,p.paymentid
HAVING TotalPayments < 5000; 
        -- 19. Calculate Total Payments per Location 
SELECT l.LocationID,l.LocationName, SUM(p.Amount) AS TotalPayments
FROM Location l JOIN Payment p ON l.LocationID = p.LocationID
GROUP BY l.LocationID, l.LocationName;
	  -- 20. Retrieve couriers who have received payments totaling more than $1000 in a specific location (LocationID = X): 
select  c.CourierID,c.SenderName,c.ReceiverName,p.LocationID,SUM(p.Amount) AS TotalPayments
from Couriers c
JOIN Payment p ON c.CourierID = p.CourierID WHERE p.LocationID = 2 
GROUP BY c.CourierID, c.SenderName, c.ReceiverName, p.LocationID HAVING TotalPayments > 1000;
      -- 21. Retrieve couriers who have received payments totaling more than $1000 after a certain date (PaymentDate > 'YYYY-MM-DD'): 
SELECT c.CourierID, c.SenderName, c.ReceiverName
FROM Couriers c
JOIN Payment p ON c.CourierID = p.CourierID
WHERE p.PaymentDate > '2024-01-01' AND p.Amount > 1000; 
	 -- 22. Retrieve locations where the total amount received is more than $5000 before a certain date (PaymentDate > 'YYYY-MM-DD') 
SELECT c.CourierID, c.SenderName, c.ReceiverName
FROM Couriers c
JOIN Payment p ON c.CourierID = p.CourierID
WHERE p.PaymentDate > '2024-01-01' AND p.Amount > 5000; 
-------------------------------------------------------------------------------------------------------------------------------------------------
/* ----------------------------Task 4: Inner Join,Full Outer Join, Cross Join, Left Outer Join,Right Outer Join--------------------------------*/
       -- 23. Retrieve Payments with Courier Information 
select p.paymentID,p.amount,c.courierID,p.paymentDate,c.senderAdderss,c.receiverAddress 
from payment p join couriers c on p.courierID=c.CourierID;
	   -- 24. Retrieve Payments with Location Information 
select p.paymentID,p.amount,p.paymentDate,l.LocationID
from payment p join location l on p.locationID=l.LocationID;
       -- 25. Retrieve Payments with Courier and Location Information 
select p.paymentID,p.amount,p.paymentDate,c.courierID,c.senderAdderss,c.receiverAddress ,l.LocationID
from payment p join location l on p.locationID=l.LocationID 
join couriers c on p.CourierID=c.CourierID;
	   -- 26. List all payments with courier details 
select p.paymentID,p.amount,c.courierID,p.paymentDate,c.senderAdderss,c.receiverAddress,c.Status,c.TrackingNumber,c.Weight 
from payment p join couriers c on p.courierID=c.CourierID;
       -- 27. Total payments received for each courier 
SELECT c.sendername, c.courierID, COUNT(p.paymentID) AS total_payments
FROM Payment p
LEFT JOIN Couriers c ON p.courierID = c.courierID
GROUP BY c.courierID,c.sendername;
	  -- 28. List payments made on a specific date
select p.paymentID,p.PaymentDate,c.courierid
from payment p join couriers c on p.CourierID=c.CourierID
WHERE p.PaymentDate = '2024-01-17';
      -- 29. Get Courier Information for Each Payment 
SELECT p.paymentid, c.CourierID,c.couriertype,c.DeliveryDate,c.Senderadderss,c.Status,c.Weight,c.TrackingNumber,(c.courierid) AS paymentdetails
FROM Payment p
LEFT JOIN couriers c ON p.CourierID= c.CourierID;
     -- 30. Get Payment Details with Location 
select p.paymentid,p.paymentdate,l.locationid,l.locationname,l.address
from location l
join payment p on l.LocationID=p.LocationID;
     -- 31. Calculating Total Payments for Each Courier 
select c.courierid,c.SenderName, c.ReceiverName,sum(p.amount)AS total
from couriers c
join payment p on c.courierid=p.CourierID
GROUP BY c.CourierID, c.SenderName, c.ReceiverName;
     -- 32. List Payments Within a Date Range 
select p.paymentID,p.PaymentDate,c.courierid,c.couriertype
from payment p join couriers c on p.CourierID=c.CourierID
where p.PaymentDate BETWEEN '2024-01-10' AND '2024-01-16';
     -- 33. Retrieve a list of all users and their corresponding courier records, including cases where there are no matches on either side
select u.uname,u.email,u.password,u.contactnumber,u.address,c.courierid,c.ReceiverAddress,c.senderadderss,c.status,c.weight
from user u
left JOIN couriers c ON u.UserID = c.UserID;
      --  34. Retrieve a list of all couriers and their corresponding services, including cases where there are no matches on either side 
select c.courierid,c.status,c.DeliveryDate,cs.serviceid,cs.servicename,cs.cost
from courierservices cs
left join couriers c ON c.serviceid=cs.serviceid;
      -- 35. Retrieve a list of all couriers and their corresponding payments, including cases where there are no matches on either side 
select c.courierid,c.status,c.DeliveryDate,p.paymentid,p.paymentdate,p.amount
from payment p
left join couriers c ON c.courierid=p.courierid;
      -- 36. List all users and all courier services, showing all possible combinations. 
select u.userid,u.uname,cs.serviceid,cs.servicename
from user u
cross join courierservices cs;
    -- 37. List all employees and all locations, showing all possible combinations: 
select l.locationid,l.locationname,l.address,e.employeeid,e.name,e.role,e.salary
from employee e
cross join location l;
    -- 38. Retrieve a list of couriers and their corresponding sender information (if available) 
select c.courierid,c.sendername,c.senderadderss,u.userid ,u.email,u.password,u.contactnumber,u.address
from couriers c
left join user u on c.UserID=u.UserID;
    -- 39. Retrieve a list of couriers and their corresponding receiver information (if available): 
select c.courierid,c.ReceiverName,c.ReceiverAddress,u.userid,u.email,u.contactnumber,u.password
from couriers c
left join User u ON c.Receivername = u.Uname;
    -- 40. Retrieve a list of couriers along with the courier service details (if available):
select c.courierid,c.status,c.deliverydate,cs.ServiceID,cs.servicename
from couriers c
left join courierservices cs on c.serviceid=cs.serviceID; 
    -- 41. Retrieve a list of employees and the number of couriers assigned to each employee: 
select e.EmployeeID, e.Name , e.Email , e.ContactNumber,e.Role,e.Salary,COUNT(o.OrderID) AS NumberOfOrdersAssigned
from Employee e
LEFT JOIN Orders o ON e.EmployeeID = o.EmployeeID
GROUP BY  e.EmployeeID, e.Name, e.Email, e.ContactNumber, e.Role, e.Salary;
    -- 42. Retrieve a list of locations and the total payment amount received at each location: 
select l.LocationID,l.LocationName,p.paymentid,SUM(p.Amount) AS TotalPaymentAmount
from Location l
LEFT JOIN Payment p ON l.LocationID = p.LocationID
group by  l.LocationID, l.LocationName,p.paymentid;
   -- 43. Retrieve all couriers sent by the same sender (based on SenderName). 
select * from couriers where Sendername='Ria';
   -- 44. List all employees who share the same role. 
select e1.EmployeeID AS EmployeeID1, e1.Name AS Name1, e2.EmployeeID AS EmployeeID2, e2.Name AS Name2, e1.Role
from Employee e1
INNER JOIN Employee e2 ON e1.Role = e2.Role AND e1.EmployeeID < e2.EmployeeID; -- e1.EmployeeID < e2.EmployeeID to avoid duplicate pairs
         -- 45. Retrieve all payments made for couriers sent from the same location. 
select p.PaymentID,p.CourierID,p.Amount,p.PaymentDate,l.LocationID,l.LocationName
from Payment p
JOIN Couriers c ON p.CourierID = c.CourierID
JOIN location l ON p.LocationID = l.LocationID;
			-- 46. Retrieve all couriers sent from the same location (based on SenderAddress). 
select CourierID,SenderName,SenderAdderss,ReceiverName,ReceiverAddress,Weight,Status,TrackingNumber,DeliveryDate,couriertype
FROM couriers
WHERE Senderadderss IN ( SELECT Senderadderss FROM couriers GROUP BY Senderadderss HAVING COUNT(*) > 1);
         -- 47. List employees and the number of couriers they have delivered: 
select e.employeeid,e.name,e.Email,e.ContactNumber,e.role,e.Salary,COUNT(o.orderid) AS NumberOfCouriersDelivered
from employee e
left join orders o on e.EmployeeID=o.EmployeeID
GROUP BY e.EmployeeID, e.Name, e.Email, e.ContactNumber, e.Role, e.Salary;
         -- 48. Find couriers that were paid an amount greater than the cost of their respective courier services
select c.CourierID,c.SenderName,c.ReceiverName,c.Weight,c.Status,c.TrackingNumber,
c.DeliveryDate,p.Amount AS PaymentAmount,cs.Cost AS CourierServiceCost
from Couriers c
JOIN Payment p ON c.CourierID = p.CourierID
JOIN CourierServices cs ON c.ServiceID = cs.ServiceID
where p.Amount > cs.Cost;
--------------------------------------------------------------------------------------------------------------------------------------------------
 /*-----------------------------Scope: Inner Queries, Non Equi Joins, Equi joins,Exist,Any,All----------------------------*/ 
       -- 49. Find couriers that have a weight greater than the average weight of all couriers 
select c.CourierID,c.SenderName,c.ReceiverName,c.Weight,c.Status, c.TrackingNumber, c.DeliveryDate
from couriers c
JOIN (SELECT AVG(Weight) AS AvgWeight FROM couriers) AS avgCourier
ON c.Weight > avgCourier.AvgWeight;
		-- 50. Find the names of all employees who have a salary greater than the average salary: 
select e.Name,e.Salary
from Employee e
JOIN (SELECT AVG(Salary) AS AvgSalary FROM Employee) AS avgEmployee
ON e.Salary > avgEmployee.AvgSalary;
         -- 51. Find the total cost of all courier services where the cost is less than the maximum cost 
select SUM(Cost) AS TotalCost from CourierServices where Cost < ( select MAX(Cost) from CourierServices);
		-- 52. Find all couriers that have been paid for 
select c.CourierID,c.Status,c.DeliveryDate
from Couriers c join Payment p ON c.CourierID = p.CourierID;
       -- 53. Find the locations where the maximum payment amount was made 
select l.LocationID, l.LocationName, l.Address,
(SELECT MAX(p.Amount) FROM Payment p WHERE p.LocationID = l.LocationID) AS MaxPaymentAmount
from Location l
ORDER BY MaxPaymentAmount DESC LIMIT 1;
          -- 54. Find all couriers whose weight is greater than the weight of all couriers sent by a specific sender (e.g., 'SenderName'):
select CourierID,SenderName,ReceiverName,Weight,Status,TrackingNumber,DeliveryDate
from Couriers where Weight > (SELECT MAX(c2.Weight)FROM Couriers c2 WHERE c2.SenderName = 'Zeba');
-------------------------------------------------------------------------------------------------------------------------------------------------















