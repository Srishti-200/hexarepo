
create database mysql3;
use mysql3;
  -- customers table
create table Customers(customer_id int primary key,cust_name varchar(255),cust_email VARCHAR(255) UNIQUE NOT NULL,password varchar(255));
  -- Products table
create table Products(product_id int primary key,prod_name varchar(255),prod_price int,prod_desp varchar(255),stock_quantity int);
     -- cart table
create table cart(cart_id int primary key,customer_id int, FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
product_id int,FOREIGN KEY (product_id) REFERENCES Products(product_id),Quantity int);
	 -- orders table
create table Orders(order_id int primary key,customer_id int,FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),order_date DATE,total_price int,shipping_address text);
	   -- orderitems table
create table orderitems(order_item_id int primary key,order_id int, foreign key (order_id) references Orders (order_id ),
product_id int,FOREIGN KEY (product_id) REFERENCES Products(product_id),Quantity int);
INSERT INTO customers VALUES
(1, 'Shra', 'shra1234@gmail.com', 'shras5'),
(2, 'Jennie', 'jesn56@gmail.com', 'jumba65'),
(3, 'Ramesh', 'ram456@gmail.com', 'pass3'),
(4, 'Ankita', 'alice@gmail.com', 'anki89'),
(5, 'Dualipa', 'lipa00@gmail.com', 'pa6925'),
(6, 'Selena', 'sel003@gmail.com', 'gomez003');
INSERT INTO products VALUES
(101, 'Laptops', 50000.00, 'AC-powered personal computer', 50),
(102, 'Smartphones', 20000.99, ' cellular telephone with an integrated computer', 80),
(103, 'Earphones', 850.99, 'equipment used to listen music and audio', 200),
(104, 'Television', 89000.99, '720p resolution, offers good clarity for smaller screens.', 450),
(105, 'Tablets', 59000.99, 'screens 7 inches (18 cm) or larger', 215),
(106, 'Monitors', 3500.99, 'high-definition (HD), 2K or 4K UHD.', 315);
INSERT INTO cart VALUES
(1, 1, 101, 2),
(2, 1, 102, 1),
(3, 2, 103, 3),
(4, 3, 104, 2),
(5, 4, 105, 1),
(6, 5, 101, 4);
INSERT INTO orders VALUES
(1, 1, '2024-01-18', 110000.98, '123 Main St, Sydney'),
(2, 2, '2024-01-19', 24301.94, '456 Oak St, Town'),
(3, 3, '2024-01-20', 28951.95, '4th park avanue'),
(4, 4, '2024-01-21', 124003.94, '87th Park street'),
(5, 5, '2024-01-22', 143503.92, '15th global Nagar'),
(6, 6, '2024-01-23', 11041.94, '11th main round');
INSERT INTO orderitems VALUES
(1, 1, 101, 2),
(2, 1, 102, 1),
(3, 2, 103, 3),
(4, 3, 104, 2),
(5, 4, 105, 1),
(6, 5, 106, 4);
select * from customers;

