INSERT INTO `shops` (`shop_name`, `owner_name`)
VALUES
    ('ShopA', 'Carlo'),
    ('ShopB', 'Para'),
    ('ShopC', 'Malo');

INSERT INTO `products` (`category`, `name`, `manufacturer`, `price`)
VALUES
    ('COMPUTER_AND_ACCESSORIES', 'Computer', 'Asus', 500.00),
    ('CLEANING_SUPPLIES', 'Detergent', 'Dove', 10.00),
    ('FOOD_AND_BEVERAGES', 'Apple', 'Emperor', 2.20),
    ('COMPUTER_AND_ACCESSORIES', 'Computer', 'Razer', 550.00),
    ('HEALTHCARE_PRODUCTS', 'Medicine', 'DrJohnson', 10.00),
    ('FOOD_AND_BEVERAGES', 'Banana', 'Chiquita', 2.10),
    ('CLEANING_SUPPLIES', 'Detergent', 'Fresh&Clean', 12.00),
    ('HEALTHCARE_PRODUCTS', 'Medicine', 'Moment', 11.00),
    ('FOOD_AND_BEVERAGES', 'Pear', 'BioFruits', 2.50);

INSERT INTO `customers` (`first_name`, `last_name`, `balance`)
VALUES
    ('Enrico', 'Drago', 24.52),
    ('Sara', 'Coppola', 64.45),
    ('Andrea', 'Zora', 121.71);

INSERT INTO `product_to_shop` (`product_id`, `shop_id`)
VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (4, 2),
    (5, 2),
    (6, 2),
    (7, 3),
    (8, 3),
    (9, 3);
