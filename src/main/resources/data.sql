INSERT INTO user (CITY, PASSWORD, STATE, STREET, USERNAME, ZIP, EMAIL, F_NAME, L_NAME) VALUES
('testcity','password','testState','testStreet','ADMIN','11111','testemail','John','Doe'),
('Miami','soup','Florida','Park Pl','Taio Cruz','69420','cool@gmail.com','Amanda','Lady'),
('Syracuse','abcd','NY','Main St','JoeSmoe','100','mail@gmail.com','Joseph','Campanelli');

INSERT INTO restaurant (PASSWORD,USERNAME,R_NAME) VALUES
('pass1','firstuser','Mcdonalds'),
('wordpass','Adam','chipotle'),
('pass','realRestaurant','TotallyRealFood');

INSERT INTO menu_item (DESCRIPTION,NAME,PRICE,RESTAURANTID) VALUES
('Its soup', 'Soup',10.0,2),
('Its tacos', 'Taco',7.0,2),
('Its Water', 'Water',1.0,2),
('Its Pizza', 'Pizza',4.0,1),
('Its (not) Spagetti', 'Spagetti',14.0,3),
('Its bleach', 'Soup',99.0,3);
