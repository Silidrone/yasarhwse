-- Use this template for your se2222 project
-- Before submission be sure that your file is named like [your_ID].sql
-- You will get minus 20% of total score for each day after due date
-- Name:Muhamed Cicak
-- ID:21070006208

-- 1. Create and use a schema for your project named SE2222_[your_ID]
CREATE SCHEMA SE2222_21070006208;
USE SE2222_21070006208;

-- 1. Definitions:
-- All table definitions of your project including any constraints
-- Before each table creation give a short explanation of the table

-- The table stores registered users, which may or may not be verified yet. Users are uniquely identified by their email (primary key).
CREATE TABLE users(
    email VARCHAR (255) PRIMARY KEY,
    password VARCHAR (255),
    name VARCHAR (20),
    surname VARCHAR (20),
    is_verified BOOLEAN
);

-- Addresses recorded so far in the system. Uniquely identified by the address field (primary key).
CREATE TABLE addresses(
    address VARCHAR (255) PRIMARY KEY,
    postal_code VARCHAR (5)
);

-- Contact info for users, a user may have more than one contact info. Uniquely identified by the field id (primary key).
CREATE TABLE contact_info(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    phone_number VARCHAR (15),
    address VARCHAR (255),
    email VARCHAR (255),
    CONSTRAINT fk_contact_info_addresses FOREIGN KEY (address) REFERENCES addresses (address),
    CONSTRAINT fk_contact_info_users FOREIGN KEY (email) REFERENCES users (email)
);

-- Baskets are uniquely identified by their primary key: id. The baskets contain items added by the user, and the "notify" field serves as a flag to indicate whether the system should notify the user in the event of any changes to the items within the basket.
CREATE TABLE baskets(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    filter JSON,
    order_asc BOOLEAN,
    notify BOOLEAN
);

-- Users' credit cards. Credit cards are uniquely identified by the "number" attribute which is the primary key for this table. A user may have more than one credit card.
CREATE TABLE credit_cards(
    number VARCHAR(19) PRIMARY KEY,
    month TINYINT UNSIGNED NOT NULL,
    year INTEGER UNSIGNED NOT NULL,
    cvv VARCHAR(3) NOT NULL,
    owner_email VARCHAR (255),
    CONSTRAINT fk_credit_cards_users FOREIGN KEY (owner_email) REFERENCES users (email)
); 

-- Coupons available in the system for use and their discount percentages. Coupons are uniquely identified by their code (primary key).
CREATE TABLE coupons(
    code VARCHAR(10) PRIMARY KEY,
    discount_percentage DECIMAL(5, 2)
);

-- Categories (they are nested within themselves, i.e. a category may have children cateogries and may have a parent category). Categories are uniquely identified by their id field (primary key).
CREATE TABLE categories(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(500) NOT NULL,
    image_path VARCHAR(256),
    description VARCHAR(2000),
    parent_id INTEGER,
    CONSTRAINT fk_categories_categories FOREIGN KEY (parent_id) REFERENCES categories (id)
);

-- Items table, stock field represents how many pieces of the item are left in the stock. Items are uniquely identified by their id field (primary key).
CREATE TABLE items(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(500),
    image_path VARCHAR(256),
    price DECIMAL(10, 2),
    stock INTEGER
);

-- Any payment made by any user together with details of when it was made and which coupon was applied (NULL if none). Uniquely identified by id primary key.
CREATE TABLE payment_history(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    credit_card_number VARCHAR(19),
    basket_id INTEGER,
    coupon_code VARCHAR(10),
    date TIMESTAMP NOT NULL,
    CONSTRAINT fk_payment_history_credit_cards FOREIGN KEY (credit_card_number) REFERENCES credit_cards (number),
    CONSTRAINT fk_payment_history_baskets FOREIGN KEY (basket_id) REFERENCES baskets (id),
    CONSTRAINT fk_payment_history_coupons FOREIGN KEY (coupon_code) REFERENCES coupons (code)
);

-- A many-to-many relationship between baskets and users. Since a user may only have the same basket once, this table has a composite primary key (basket_id, email).
CREATE TABLE baskets_users(
    basket_id INTEGER,
    email VARCHAR (255),
    CONSTRAINT pk_baskets_users PRIMARY KEY (basket_id, email), 
    CONSTRAINT fk_baskets_users_baskets FOREIGN KEY (basket_id) REFERENCES baskets (id),
    CONSTRAINT fk_baskets_users_users FOREIGN KEY (email) REFERENCES users (email)
);

-- A many-to-many relationship between baskets and items. Since a basket may have the same item more than once, this table doesn't use a composite primary key and it uses an id primary key to uniquely identify it's records.
CREATE TABLE baskets_items(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    basket_id INTEGER,
    item_id INTEGER,
    CONSTRAINT fk_baskets_items_baskets FOREIGN KEY (basket_id) REFERENCES baskets (id),
    CONSTRAINT fk_baskets_items_items FOREIGN KEY (item_id) REFERENCES items (id)
);

-- A many-to-many relationship between categories and items. Since a category may only have the same item once, this table has a composite primary key (category_id, item_id)
CREATE TABLE categories_items(
    category_id INTEGER,
    item_id INTEGER,
    CONSTRAINT pk_categories_items PRIMARY KEY (category_id, item_id),
    CONSTRAINT fk_categories_items_categories FOREIGN KEY (category_id) REFERENCES categories (id),
    CONSTRAINT fk_categories_items_items FOREIGN KEY (item_id) REFERENCES items (id)
);

-- 2. Insertions(Data manipulation):
-- For each table, add enough number of rows to make your queries in step three to produce meaningful result sets.

-- The passwords are stored as bcrypt hash values.
INSERT INTO users (email, password, name, surname, is_verified) VALUES
    ('john@example.com', '$2y$10$zcr.NYG8fEZOoDTG9jJYy.ViJ0M2zXx70Y2kN4.3m7wKQOqZrQ28W', 'John', 'Doe', true), 
    ('alice@example.com', '$2y$10$FOSGOLRVRm6FafW0h12Q7uNJGE6j3l7OoG6.Esc.x54yjNdm.AhJ6', 'Alice', 'Smith', true), 
    ('emma@example.com', '$2y$10$q4nyMzTfVvKkkW6KMCzEO.ti34mNlmekwIlJuv68KdrA3XHhS6H8y', 'Emma', 'Johnson', true),
    ('michael@example.com', '$2y$10$Zm8mWTwF0L9/DcGyTyXv9uZSWPCd8tt6sdw/VDLUjCUD2WqCXzvua', 'Michael', 'Smith', true),
    ('lisa@example.com', '$2y$10$uE3bkXTAbq4erjwjpyj99u2AEiQ1v1tQhT6IKsK5bLEk4Y2IMxt8W', 'Lisa', 'Davis', false);

INSERT INTO addresses (address, postal_code) VALUES
    ('123 Main St', '12345'),
    ('456 Elm St', '67890'),
    ('789 Oak St', '54321'),
    ('987 Maple Ave', '09876'),
    ('321 Pine Rd', '56789');


INSERT INTO contact_info (phone_number, address, email) VALUES
    ('123-456-7890', '123 Main St', 'john@example.com'),
    ('888-888-8888', '321 Pine Rd', 'john@example.com'),
    ('987-654-3210', '456 Elm St', 'alice@example.com'),
    ('555-555-5555', '789 Oak St', 'emma@example.com'),
    ('444-444-4444', '987 Maple Ave', 'michael@example.com');

INSERT INTO baskets (filter, order_asc, notify) VALUES
    ('{"min_price": "500", "max_price": 1000}', true, true),
    (NULL, false, false),
    (NULL, true, false);

INSERT INTO credit_cards (number, month, year, cvv, owner_email) VALUES
    ('1111222233334444', 12, 2024, '123', 'john@example.com'),
    ('5555666677778888', 10, 2023, '456', 'alice@example.com'),
    ('9999888877776666', 9, 2025, '789', 'emma@example.com'),
    ('4444333322221111', 11, 2022, '987', 'michael@example.com'),
    ('7777666655554444', 8, 2023, '321', 'michael@example.com');

INSERT INTO coupons (code, discount_percentage) VALUES
    ('123abc', 10.00),
    ('456dfg', 20.00),
    ('789hij', 15.00);

INSERT INTO categories (id, title, image_path, description, parent_id) VALUES
    (1, 'Electronics', '/images/electronics.jpg', 'Electronics category', NULL),
    (2, 'Clothing', '/images/clothing.jpg', 'Clothing category', NULL),
    (3, 'Books', '/images/books.jpg', 'Books category', NULL),
    (4, 'Novels', NULL, 'Novels category', 3);

INSERT INTO items (name, image_path, price, stock) VALUES
    ('Laptop', '/images/laptop.jpg', 999.99, 5),
    ('Mouse', NULL, 19.99, 10),
    ('Keyboard', NULL, 29.99, 20),
    ('The Little Prince', '/images/thelittleprince.jpg', 49.99, 8),
    ('Jeans', '/images/jeans.jpg', 39.99, 15),
    ('T-Shirt', '/images/tshirt.jpg', 14.99, 30);

INSERT INTO payment_history (credit_card_number, basket_id, coupon_code, date)
VALUES
    ('1111222233334444', 1, '123abc', '2023-05-18 10:00:00'),
    ('5555666677778888', 2, '456dfg', '2023-05-18 11:30:00'),
    ('9999888877776666', 3, '789hij', '2023-05-18 12:15:00'),
    ('4444333322221111', 1, NULL, '2023-05-18 13:45:00'),
    ('7777666655554444', 2, '789hij', '2023-05-18 14:30:00');

INSERT INTO baskets_users (basket_id, email) VALUES
    (1, 'john@example.com'),
    (2, 'michael@example.com'),
    (2, 'alice@example.com'),
    (3, 'emma@example.com'),
    (1, 'michael@example.com');

INSERT INTO baskets_items (basket_id, item_id) VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 3),
    (2, 1),
    (2, 3);

INSERT INTO categories_items (category_id, item_id) VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (3, 4),
    (2, 5),
    (2, 6);

-- 3. Queries:
-- Write 5 queries with explanations 
-- Write 5 queries. Your queries should do a task that is meaningful in your selected context (project topic). 
-- At least one that joins two or more tables X
-- At least one that include group functions X
-- At least one with one or more sub-query(es) X
-- At least one update X
-- At least one delete X
-- At least one include arithmetic functions X (I assume this is SUM, AVG etc.)
-- At least one uses alias X

-- 1. Retrieve the names of all items in the baskets of a user with the email 'michael@example.com', along with the number of times each item is to be purchased, the number of items in the stock and the item's price per piece, with the items being ordered by price from lowest to highest.
SELECT name, price, stock, COUNT(ii.item_id) FROM (SELECT item_id FROM baskets_items WHERE basket_id IN (SELECT basket_id FROM baskets_users WHERE email='michael@example.com')) AS ii INNER JOIN items ON ii.item_id=items.id GROUP BY name, price, stock ORDER BY price ASC;
-- 2. Retrieve all ids of baskets that are "shared baskets", that is owned by more than one user, and also next to basket_id there should be users_count to indicate how many users it is owned by.
SELECT basket_id, COUNT(email) as users_count FROM baskets_users GROUP BY basket_id HAVING users_count > 1;
-- 3. Retrieve the total money spent so far by each user, taking into account any discounts that were applied during the purchases.
SELECT owner_email as user_email, SUM(PRICE * (100 - COALESCE(discount_percentage, 0)) / 100) as money_spent from credit_cards inner join payment_history on credit_cards.number=payment_history.credit_card_number inner join baskets_items on payment_history.basket_id=baskets_items.basket_id inner join items on baskets_items.item_id=items.id left join coupons on payment_history.coupon_code=coupons.code GROUP BY owner_email;
-- 4. Update the stock to 0 for all items belonging to a category with the name 'Electronics'
UPDATE items INNER JOIN categories_items on items.id=categories_items.item_id INNER JOIN categories on categories_items.category_id=categories.id SET items.stock=0 WHERE categories.title='Electronics';
-- 5. Delete all users that are not verified
DELETE FROM users WHERE is_verified=false;