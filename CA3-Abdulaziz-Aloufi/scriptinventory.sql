CREATE DATABASE InventoryManagement;

USE InventoryManagement;

CREATE TABLE Customer (
CustomerID int not null primary key,
CustomerFName varchar(20) not null,
CustomerLName varchar(20) not null,
CustomerAddress varchar(128) not null,
CustomerPhone varchar(20) not null, 
CustomerEmail varchar(30) not null
);

CREATE TABLE Category (
CategoryID int not null primary key auto_increment,
CategoryName varchar(20) not null
);

CREATE TABLE Product (
ProductID int not null primary key,
ProductCategory int not null,
ProductName varchar(20) not null,
ProductPrice numeric(6,2) not null,
foreign key(ProductCategory) references Category(CategoryID)
);

CREATE TABLE Purchase (
PurchaseID int not null primary key auto_increment, 
CustomerID int not null,
ProductID int not null,
PurchaseStatus varchar(20) not null,
foreign key(CustomerID) references Customer(CustomerID),
foreign key(ProductID) references Product(ProductID)
);

INSERT INTO Customer(CustomerID, CustomerFName, CustomerLName, CustomerAddress, CustomerPhone, CustomerEmail)
VALUES
(211, 'Finn', 'Foster', '56041 Sammy Highway Suite 135 Port Magnusview', '905-494-8946', 'finn.foster@shopping.com'),
(212, 'Kaleb', 'Fleming', '3672 Owen Trace Apt. 384 Ileneburgh', '525-410-2345', 'kaleb.fleming@shopping.com'),
(213, 'Valerie', 'Schultz', '3850 Boyd Inlet Apt. 975 Lake Rosalind', '546-659-6193', 'valerie.schultsz@shopping.com'),
(214, 'Amaya', 'Bailey', '13771 Ondricka Groves Suite 760 New Myrlshire', '454-731-8331', 'amaya.bailey@shopping.com'),
(215, 'Chase', 'Harrison', '9038 Mary Estates Apt. 918 McCulloughmouth', '475-587-7242', 'chase.harrison@shopping.com');

INSERT INTO Category(CategoryName)
VALUES
('Clothes'),
('Shoes'),
('Accessories');

INSERT INTO Product(ProductID, ProductName, ProductCategory, ProductPrice)
VALUES
(100301, 'Winter Jacket', 1, 69.99),
(100302, 'Tank Top', 1, 22.49),
(100303, 'Stripped Shirt', 1, 16.99),
(100401, 'Ankle Boots', 2, 56.49),
(100402, 'Runner Sneakers', 2, 44.99),
(100403, 'Studded Sandals', 2, 32.99),
(100404, 'Flip Flops', 2, 14.99),
(100501, 'Backpack', 3, 25.45),
(100502, 'Sunglasses', 3, 29.45),
(100503, 'Scarf', 3, 18.99);

INSERT INTO Purchase(CustomerID, ProductID, PurchaseStatus)
VALUES
(215, 100301, 'Processed'),
(215, 100403, 'Pending'),
(211, 100402, 'Canceled'),
(212, 100502, 'Processed'),
(212, 100401, 'Pending'),
(213, 100302, 'Pending'),
(213, 100501, 'Canceled'),
(213, 100301, 'Processed'),
(214, 100403, 'Processed'),
(214, 100503, 'Pending');


