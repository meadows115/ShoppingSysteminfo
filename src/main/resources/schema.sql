/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  meani898
 * Created: 6/08/2019
 */

CREATE TABLE Product(
    productID INT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(500) NOT NULL,
    category VARCHAR(50) NOT NULL,
    listPrice DECIMAL(18,2) NOT NULL,
    quantityInStock INT NOT NULL,
    CONSTRAINT Product_PK PRIMARY KEY (productID)
);

CREATE TABLE Customer(
	customerID INT auto_increment(1000),
	username VARCHAR(50) NOT NULL unique,
	firstName VARCHAR(100) NOT NULL,
	surname VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL,
	emailAddress VARCHAR(100) NOT NULL,
	shippingAddress VARCHAR(100) NOT NULL,
	CONSTRAINT Customer_PK PRIMARY KEY (customerID)
);

CREATE TABLE Sale(
	saleID INT auto_increment(10000),
	date DATE NOT NULL,
	status VARCHAR(100),
   customer_ID INT NOT NULL,
	CONSTRAINT Sale_PK PRIMARY KEY (saleID),
	CONSTRAINT Sale_Customer_FK FOREIGN KEY(customer_ID) references Customer (customerID) 
);


CREATE TABLE SaleItem(
	quantityPurchased INT NOT NULL,
	salePrice DECIMAL(18,2) NOT NULL,
	productID INT NOT NULL,
	saleID INT NOT NULL,
	CONSTRAINT SALEITEM_PK PRIMARY KEY (productID, saleID),
	CONSTRAINT PRODUCTID_FK FOREIGN KEY (productID) references Product,
	CONSTRAINT SALEID_FK FOREIGN KEY(saleID) references Sale
);
