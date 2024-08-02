-- Script to insert dummy dev data into the database.
-- https://bcrypt.online/

-- NOTE: You first need to register two users(ROLE:USER) into the system before running this scirpt.
-- Replace the id here(IF NOT 1 AND 2) with the first user id you want to have ownership of the orders.
-- Password @Tester1,@Tester2,@Tester3,@Tester4 ,@Tester5  --Base64

INSERT INTO local_user (email, password, firstname, lastname,username,role) VALUES ('a@f.nl', '$2y$10$yCh6UQLdhLs8ij4pV4JUOOdeYDvj0xMtcMizXAlGDGzXsV6vd9eni', 'Petr', 'Bnnn','User1','USER');
INSERT INTO local_user (email, password, firstname, lastname,username,role) VALUES ('b@f.nl', '$2y$10$Gv4vPGpLqkU9lAv5hKvPZOXD3gjKbsf.UMO447D.EwFxdQuPaLpMK', 'John', 'Bnnn','User2','USER');
INSERT INTO local_user (email, password, firstname, lastname,username,role) VALUES ('c@f.nl', '$2y$10$88cI0hGZaniemm9mvFGtuOYOC64kgy7DI9AW6I6/zYvmVStzy57Eu', 'Mary', 'Bnnn','User3','USER');
INSERT INTO local_user (email, password, firstname, lastname,username,role) VALUES ('d@f.nl', '$2y$10$8pQDlwxTvfj5a2DEI6M6eOZiFrHo7yW2lIpIAdZz2n0qOA/NcBPK6', 'Tom', 'Bnnn','User4','ADMIN');
INSERT INTO local_user (email, password, firstname, lastname,username,role) VALUES ('e@f.nl', '$2y$10$vaRsidW8jZxbMewZBXaLbeqRsyxqt7csT.DzyaGbNhaCdKzbwjCni', 'Ben', 'Bnnn','User5','MODERATOR');

SET @userId1 = 1;
-- Replace the id here with the second user id you want to have ownership of the orders.
SET @userId2 = 2;
-- Replace the id here with the third user id you want to have ownership of the orders.
SET @userId3 = 3;

DELETE FROM web_order_quantities where id >= 1;
DELETE FROM web_order where id >= 1;
DELETE FROM inventory where id >= 1;
DELETE FROM product where id >= 1;
DELETE FROM address where id >= 1;

INSERT INTO product (name, description, price, category) VALUES ('Product #1', 'Product one description.', 5.50, 'BOOKS');
INSERT INTO product (name, description, price, category) VALUES ('Product #2', 'Product two description.', 10.56, 'ELECTRONICS');
INSERT INTO product (name, description, price, category) VALUES ('Product #3', 'Product three short description.', 2.74,  'SPORT');
INSERT INTO product (name, description, price, category) VALUES ('Product #4', 'Product four short description.', 15.69, 'BOOKS');
INSERT INTO product (name, description, price, category) VALUES ('Product #5', 'Product five short description.', 42.59, 'ELECTRONICS');

SET @product1 = 0;
SET @product2 = 0;
SET @product3 = 0;
SET @product4 = 0;
SET @product5 = 0;

SELECT @product1 := id FROM product WHERE name = 'Product #1';
SELECT @product2 := id FROM product WHERE name = 'Product #2';
SELECT @product3 := id FROM product WHERE name = 'Product #3';
SELECT @product4 := id FROM product WHERE name = 'Product #4';
SELECT @product5 := id FROM product WHERE name = 'Product #5';

INSERT INTO inventory (product_id, quantity) VALUES (@product1, 5);
INSERT INTO inventory (product_id, quantity) VALUES (@product2, 8);
INSERT INTO inventory (product_id, quantity) VALUES (@product3, 12);
INSERT INTO inventory (product_id, quantity) VALUES (@product4, 73);
INSERT INTO inventory (product_id, quantity) VALUES (@product5, 2);

INSERT INTO address (address_line_1, city, country, user_id) VALUES ('123 Tester Hill', 'Testerton', 'UK', @userId1);
INSERT INTO address (address_line_1, city, country, user_id) VALUES ('312 Spring Boot', 'Hibernate', 'USA', @userId2);
INSERT INTO address (address_line_1, city, country, user_id) VALUES ('456 Kruistraatt', 'Groningen', 'NL', @userId3);

SET @address1 = 0;
SET @address2 = 0;



SELECT @address1 := id FROM address WHERE user_id = @userId1 ORDER BY id DESC LIMIT 1;
SELECT @address2 := id FROM address WHERE user_id = @userId2 ORDER BY id DESC LIMIT 1;


INSERT INTO web_orders (address_id, user_id) VALUES (@address1, @userId1);
INSERT INTO web_orders (address_id, user_id) VALUES (@address1, @userId1);
INSERT INTO web_orders (address_id, user_id) VALUES (@address1, @userId1);
INSERT INTO web_orders (address_id, user_id) VALUES (@address2, @userId2);
INSERT INTO web_orders (address_id, user_id) VALUES (@address2, @userId2);

SET @order1 = 0;
SET @order2 = 0;
SET @order3 = 0;
SET @order4 = 0;
SET @order5 = 0;

SELECT @order1 := id FROM web_orders WHERE address_id = @address1 AND user_id = @userId1 ORDER BY id DESC LIMIT 1;
SELECT @order2 := id FROM web_orders WHERE address_id = @address1 AND user_id = @userId1 ORDER BY id DESC LIMIT 1 OFFSET 1;
SELECT @order3 := id FROM web_orders WHERE address_id = @address1 AND user_id = @userId1 ORDER BY id DESC LIMIT 1 OFFSET 2;
SELECT @order4 := id FROM web_orders WHERE address_id = @address2 AND user_id = @userId2 ORDER BY id DESC LIMIT 1;
SELECT @order5 := id FROM web_orders WHERE address_id = @address2 AND user_id = @userId2 ORDER BY id DESC LIMIT 1 OFFSET 1;

INSERT INTO web_order_quantities (order_id, product_id, quantity) VALUES (@order1, @product1, 5);
INSERT INTO web_order_quantities (order_id, product_id, quantity) VALUES (@order1, @product2, 5);
INSERT INTO web_order_quantities (order_id, product_id, quantity) VALUES (@order2, @product3, 5);
INSERT INTO web_order_quantities (order_id, product_id, quantity) VALUES (@order2, @product2, 5);
INSERT INTO web_order_quantities (order_id, product_id, quantity) VALUES (@order2, @product5, 5);
INSERT INTO web_order_quantities (order_id, product_id, quantity) VALUES (@order3, @product3, 5);
INSERT INTO web_order_quantities (order_id, product_id, quantity) VALUES (@order4, @product4, 5);
INSERT INTO web_order_quantities (order_id, product_id, quantity) VALUES (@order4, @product2, 5);
INSERT INTO web_order_quantities (order_id, product_id, quantity) VALUES (@order5, @product3, 5);
INSERT INTO web_order_quantities (order_id, product_id, quantity) VALUES (@order5, @product1, 5);