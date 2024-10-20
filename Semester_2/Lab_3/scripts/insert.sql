-- Insert into application table
INSERT INTO application (id, departure, departure_time, destination, destination_time)
VALUES (1, 'New York', '2024-09-29 08:00:00', 'Los Angeles', '2024-09-29 20:00:00'),
       (2, 'Chicago', '2024-09-30 09:00:00', 'Houston', '2024-09-30 14:30:00'),
       (3, 'Miami', '2024-09-30 12:00:00', 'Orlando', '2024-09-30 13:30:00'),
       (4, 'San Francisco', '2024-10-01 10:00:00', 'Seattle', '2024-10-01 18:00:00'),
       (5, 'Boston', '2024-10-01 11:00:00', 'Washington D.C.', '2024-10-01 16:00:00'),
       (6, 'Dallas', '2024-10-02 07:30:00', 'Austin', '2024-10-02 09:00:00'),
       (7, 'Denver', '2024-10-03 06:00:00', 'Salt Lake City', '2024-10-03 09:30:00'),
       (8, 'Las Vegas', '2024-10-03 13:00:00', 'Phoenix', '2024-10-03 16:30:00'),
       (9, 'Atlanta', '2024-10-04 08:00:00', 'Nashville', '2024-10-04 11:00:00'),
       (10, 'Detroit', '2024-10-05 05:00:00', 'Cleveland', '2024-10-05 06:30:00');

-- Insert into park_user table
INSERT INTO park_user (id, login, password, name, surname, birthdate)
VALUES (1, 'user1', 'password1','John', 'Doe', '1980-01-15'),
       (2, 'user2', 'password2', 'Jane', 'Smith', '1975-03-22'),
       (3, 'user3', 'password3', 'Robert', 'Johnson', '1983-07-30'),
       (4, 'user4', 'password4', 'Emily', 'Davis', '1990-05-14'),
       (5, 'user5', 'password5', 'Michael', 'Wilson', '1985-11-20'),
       (6, 'user6', 'password6', 'Sarah', 'Taylor', '1988-02-10'),
       (7, 'user7', 'password7', 'David', 'Clark', '1992-08-05'),
       (8, 'user8', 'password8', 'Jessica', 'Moore', '1981-04-17'),
       (9, 'user9', 'password9', 'Thomas', 'Lewis', '1979-12-11'),
       (10, 'user10', 'password10', 'Karen', 'Walker', '1986-06-23');

-- Insert into car table
INSERT INTO car (id, driver_id, fix_state, state_number)
VALUES (1, 1, 'Operational', 'ABC123'),
       (2, 2, 'Under Repair', 'DEF456'),
       (3, 3, 'Operational', 'GHI789'),
       (4, 4, 'Operational', 'JKL101'),
       (5, 5, 'Operational', 'MNO112'),
       (6, 6, 'Needs Service', 'PQR131'),
       (7, 7, 'Operational', 'STU145'),
       (8, 8, 'Operational', 'VWX167'),
       (9, 9, 'Operational', 'YZA188'),
       (10, 10, 'Operational', 'BCD209');

-- Insert into trip table
INSERT INTO trip (id, departure, departure_time, destination, destination_time, driver_id, car_id, is_completed)
VALUES (1, 'New York', '2024-09-29', 'Los Angeles', '2024-09-30', 1, 1, TRUE),
       (2, 'Chicago', '2024-09-30', 'Houston', '2024-09-30', 2, 2, FALSE),
       (3, 'Miami', '2024-09-30', 'Orlando', '2024-10-01', 3, 3, TRUE),
       (4, 'San Francisco', '2024-10-01', 'Seattle', '2024-10-01', 4, 4, FALSE),
       (5, 'Boston', '2024-10-01', 'Washington D.C.', '2024-10-02', 5, 5, TRUE),
       (6, 'Dallas', '2024-10-02', 'Austin', '2024-10-03', 6, 6, TRUE),
       (7, 'Denver', '2024-10-03', 'Salt Lake City', '2024-10-04', 7, 7, FALSE),
       (8, 'Las Vegas', '2024-10-03', 'Phoenix', '2024-10-05', 8, 8, TRUE),
       (9, 'Atlanta', '2024-10-04', 'Nashville', '2024-10-06', 9, 9, TRUE),
       (10, 'Detroit', '2024-10-05', 'Cleveland', '2024-10-07', 10, 10, FALSE);
