-- Shops Table
CREATE TABLE Shops (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    owner_name VARCHAR(50)
);

INSERT INTO Shops (name, owner_name)
VALUES
    ('ShopA', 'Carlo'),
    ('ShopB', 'Para'),
    ('ShopC', 'Malo');

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

-- ShopA
INSERT INTO Products (shop_id, category, name, price, stock)
VALUES
    (1, 'ELECTRONICS', 'Computer', 500.00, 7),
    (1, 'CLEANING', 'Detergent', 10.00, 21),
    (1, 'FOOD', 'Apple', 2.20, 231);

-- ShopB
INSERT INTO Products (shop_id, category, name, price, stock)
VALUES
    (2, 'ELECTRONICS', 'Computer', 550.00, 6),
    (2, 'HEALTH', 'Medicine', 10.00, 78),
    (2, 'FOOD', 'Banana', 2.10, 194);

-- ShopC
INSERT INTO Products (shop_id, category, name, price, stock)
VALUES
    (3, 'CLEANING', 'Detergent', 12.00, 24),
    (3, 'HEALTH', 'Medicine', 11.00, 82),
    (3, 'FOOD', 'Pear', 2.50, 253);

-- Customers Table
CREATE TABLE Customers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    balance DECIMAL(10, 2)
);
INSERT INTO Customers (first_name, last_name, balance)
VALUES 
    ('Enrico', 'Drago', 24.52),
    ('Enrico', 'Drago', 12.71);
