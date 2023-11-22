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

INSERT INTO `customers` (`name`, `surname`, `balance`, `address`, `email`)
VALUES
    ('Enrico', 'Drago', 24.52, 'Viale dei Tigli 16', 'enrico.drago3@gmail.com'),
    ('Sara', 'Coppola', 64.45, '', ''),
    ('Andrea', 'Zora', 121.71, '', '');

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

INSERT INTO `carts` (`customer_id`) VALUES
    (1), -- Associa al cliente con ID 1
    (2), -- Associa al cliente con ID 2
    (3); -- Associa al cliente con ID 3

-- Inserisci ordini
INSERT INTO `orders` (`customer_id`) VALUES
    (1), -- Associa al cliente con ID 1
    (2), -- Associa al cliente con ID 2
    (3); -- Associa al cliente con ID 3

-- Inserisci associazioni prodotto-negozio
INSERT INTO `product_to_shop` (`product_id`, `shop_id`) VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (4, 2),
    (5, 2),
    (6, 2),
    (7, 3),
    (8, 3),
    (9, 3);

INSERT INTO `product_reviews` (`reviewer_id`, `rating`, `comment`, `creation_date`, `update_date`, `product_id`)
VALUES
    (1, 4.21, 'Good stuff!', CURDATE(), CURDATE(), 1),
    (2, 3.62, 'I liked it.', CURDATE(), CURDATE(), 4),
    (3, 2.21, 'Disappointing performance..', CURDATE(), CURDATE(), 6);

INSERT INTO `shop_reviews` (`reviewer_id`, `rating`, `comment`, `creation_date`, `update_date`, `shop_id`)
VALUES
    (1, 4.15, 'Good shop!', CURDATE(), CURDATE(), 2),
    (2, 3.38, 'I liked this shop.', CURDATE(), CURDATE(), 2),
    (3, 2.35, 'Disappointing service..', CURDATE(), CURDATE(), 1);
