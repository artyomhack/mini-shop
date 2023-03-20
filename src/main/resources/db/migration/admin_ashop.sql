INSERT INTO users(id, archive, username, email,number, password, role, cart_id )
                               VALUES (1, false, 'antonka', 'anton@gmail.com', '12345678910', '1234', 'ADMIN', null);

ALTER sequence user_seq RESTART WITH 2;