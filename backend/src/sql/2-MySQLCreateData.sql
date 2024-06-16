-- ----------------------------------------------------------------------------
-- Put here INSERT statements for inserting data required by the application
-- in the "paproject" database.
-------------------------------------------------------------------------------

INSERT INTO  User(userName, password, firstName, lastName, email, role)
    VALUES ('viewer',
            '$2a$10$8o34vbwlRURkBGETvQzr8OCuPrk52E.j2ilm4KGKPrwNR89eNV/YG',
            'Miguel',
            'Rodríguez',
            'miguel@example.com',
            '0');

INSERT INTO  User(userName, password, firstName, lastName, email, role)
    VALUES ('ticketseller',
            '$2a$10$8o34vbwlRURkBGETvQzr8OCuPrk52E.j2ilm4KGKPrwNR89eNV/YG',
            'Martin',
            'Torreira',
            'martin@example.com',
            '1');



--room (session 1)
INSERT INTO Room(roomName, capacity)
    VALUES ('Trafalgar Law', 50);

--room (session 2)
INSERT INTO Room(roomName, capacity)
    VALUES ('Ctds room', 9);





--movie (session 1)
INSERT INTO Movie(title, summary, durationMinutes)
    VALUES ('Blade Runner',
            'En el siglo XXI, una corporación desarrolla clones humanos para ser utilizados.\
            como esclavos en colonias fuera de la Tierra, identificados como replicantes. En 2019,\
            un ex oficial de policía es contratado para cazar a un grupo de clones fugitivos\
            viviendo encubierto en Los Ángeles...',
            117);

--movie (sesion 2)
INSERT INTO Movie(title, summary, durationMinutes)
    VALUES ('Alien',
            'En un futuro lejano, la tripulación de la nave espacial comercial Nostromo\
             están de camino a casa cuando reciben una llamada de socorro de una luna lejana.\
             La tripulación tiene la obligación de investigar y la nave espacial desciende sobre el\
             luna despues...',
            120);



------------------------------------------------------------------------------------------------
--movie (sesion 2)
INSERT INTO Movie(title, summary, durationMinutes)
VALUES ('The Zodiac',
        'En los años sesenta y setenta, un asesino en serie se burla de la policía y los\
         medios de comunicación enviándoles cartas y mensajes encriptados. Los investigadores\
         de la policía y reporteros del periódico se obsesionan con el hecho de encontrar\
         al elusivo asesino en serie',
        120);


--movie (sesion 2)
INSERT INTO Movie(title, summary, durationMinutes)
VALUES ('Apocalypse Now',
        'Durante la Guerra de Vietnam, el capitán Willard recibe la misión secreta\
         de invadir el país vecino a Laos y matar a un coronel americano enloquecido,\
         que lidera su propio ejército en lo más profundo de la jungla',
        120);




-- DIA 0 ----------------------------------------------------------------------------
INSERT INTO Session(roomId, movieId, dateTime, availableTickets, ticketPrice, version)
    VALUES (1, 1, DATE_ADD(DATE(NOW()), INTERVAL '0 00:05' DAY_MINUTE), 50, 10, 0);

INSERT INTO Session(roomId, movieId, dateTime, availableTickets, ticketPrice, version)
    VALUES (2, 2, DATE_ADD(DATE(NOW()), INTERVAL '0 23:55' DAY_MINUTE), 9, 8, 0);


-- DIA 1 ----------------------------------------------------------------------------
INSERT INTO Session(roomId, movieId, dateTime, availableTickets, ticketPrice, version)
VALUES (1, 1, DATE_ADD(DATE(NOW()), INTERVAL '1 00:05' DAY_MINUTE), 50, 10, 0);

INSERT INTO Session(roomId, movieId, dateTime, availableTickets, ticketPrice, version)
VALUES (2, 2, DATE_ADD(DATE(NOW()), INTERVAL '1 23:55' DAY_MINUTE), 9, 8, 0);


-- DIA 2 ----------------------------------------------------------------------------
INSERT INTO Session(roomId, movieId, dateTime, availableTickets, ticketPrice, version)
VALUES (1, 1, DATE_ADD(DATE(NOW()), INTERVAL '2 00:05' DAY_MINUTE), 50, 10, 0);

--session 2 (23:55)
INSERT INTO Session(roomId, movieId, dateTime, availableTickets, ticketPrice, version)
VALUES (2, 2, DATE_ADD(DATE(NOW()), INTERVAL '2 23:55' DAY_MINUTE), 9, 8, 0);

-- DIA 3 ----------------------------------------------------------------------------
INSERT INTO Session(roomId, movieId, dateTime, availableTickets, ticketPrice, version)
VALUES (1, 1, DATE_ADD(DATE(NOW()), INTERVAL '3 00:05' DAY_MINUTE), 50, 10, 0);

INSERT INTO Session(roomId, movieId, dateTime, availableTickets, ticketPrice, version)
VALUES (2, 2, DATE_ADD(DATE(NOW()), INTERVAL '3 23:55' DAY_MINUTE), 9, 8, 0);

-- DIA 4 ----------------------------------------------------------------------------
INSERT INTO Session(roomId, movieId, dateTime, availableTickets, ticketPrice, version)
VALUES (1, 1, DATE_ADD(DATE(NOW()), INTERVAL '4 00:05' DAY_MINUTE), 50, 10, 0);

INSERT INTO Session(roomId, movieId, dateTime, availableTickets, ticketPrice, version)
VALUES (2, 2, DATE_ADD(DATE(NOW()), INTERVAL '4 23:55' DAY_MINUTE), 9, 8, 0);


-- DIA 5 ----------------------------------------------------------------------------
INSERT INTO Session(roomId, movieId, dateTime, availableTickets, ticketPrice, version)
VALUES (1, 1, DATE_ADD(DATE(NOW()), INTERVAL '5 00:05' DAY_MINUTE), 50, 10, 0);

INSERT INTO Session(roomId, movieId, dateTime, availableTickets, ticketPrice, version)
VALUES (2, 2, DATE_ADD(DATE(NOW()), INTERVAL '5 23:55' DAY_MINUTE), 9, 8, 0);

-- DIA 6 ----------------------------------------------------------------------------
INSERT INTO Session(roomId, movieId, dateTime, availableTickets, ticketPrice, version)
VALUES (1, 1, DATE_ADD(DATE(NOW()), INTERVAL '6 00:05' DAY_MINUTE), 50, 10, 0);

INSERT INTO Session(roomId, movieId, dateTime, availableTickets, ticketPrice, version)
VALUES (2, 2, DATE_ADD(DATE(NOW()), INTERVAL '6 23:55' DAY_MINUTE), 9, 8, 0);





INSERT INTO OrderTable(userId, sessionId, date, creditCard, numberOfTickets, delivered )
    VALUES (1, 1, '2023-10-03 18:30:00', '1111222233334444', 2, FALSE);

INSERT INTO OrderTable(userId, sessionId, date, creditCard, numberOfTickets, delivered )
    VALUES (1, 1, '2023-10-04 20:30:00', '5555666677778888', 1, FALSE);









