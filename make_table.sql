-- Shops Table
CREATE TABLE Shops (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    owner_name VARCHAR(50)
);

-- Products Table
CREATE TABLE Products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    shop_id INT,
    category ENUM('ELECTRONICS', 'FOOD', 'HEALTH', 'CLEANING'),
    name VARCHAR(50),
    price DECIMAL(10, 2),
    stock INT,
    FOREIGN KEY (shop_id) REFERENCES Shops(id)
);

-- Customers Table
CREATE TABLE Customers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    balance DECIMAL(10, 2)
);