INSERT INTO auth_data (email, name, phone, login, password, role) VALUES 
('test1', 'test1@test.com', 'test1', 'test1', 'test1', 'USER'),
('test2', 'test2@test.com', 'test2', 'test2', 'test2', 'USER'),
('test3', 'test3@test.com', 'test3', 'test3', 'test3', 'USER'),
('test4', 'test4@test.com', 'test4', 'test4', 'test4', 'USER');
INSERT INTO orders(date, user_id) VALUES 
('2024-11-28 20:00:00', '1'),
('2024-11-28 19:00:00', '1'),
('2024-11-28 18:00:00', '1'),
('2024-11-28 20:00:00', '2'),
('2024-11-28 20:00:00', '3'),
('2024-11-28 20:00:00', '4'),
('2024-11-28 12:00:00', '2'),
('2024-11-28 13:00:00', '3'),
('2024-11-28 15:00:00', '4')