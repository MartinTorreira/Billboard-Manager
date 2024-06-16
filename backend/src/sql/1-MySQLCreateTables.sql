DROP TABLE OrderTable;
DROP TABLE Session;
DROP TABLE Movie;
DROP TABLE User;
DROP TABLE Room;


CREATE TABLE User (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userName VARCHAR(60) COLLATE latin1_bin NOT NULL,
    password VARCHAR(60) NOT NULL, 
    firstName VARCHAR(60) NOT NULL,
    lastName VARCHAR(60) NOT NULL, 
    email VARCHAR(60) NOT NULL,
    role TINYINT NOT NULL,
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT UserNameUniqueKey UNIQUE (userName)
) ENGINE = InnoDB;

CREATE INDEX UserIndexByUserName ON User (userName);

CREATE TABLE Movie (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      title VARCHAR(60) COLLATE latin1_bin NOT NULL,
                      summary VARCHAR(4000) NOT NULL,
                      durationMinutes INTEGER NOT NULL,
                      CONSTRAINT MoviePK PRIMARY KEY (id),
                      CONSTRAINT MovieTitleUniqueKey UNIQUE (title)
) ENGINE = InnoDB;

CREATE INDEX MovieIndexByTitle ON Movie (title);

CREATE TABLE Room (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       roomName VARCHAR(60) COLLATE latin1_bin NOT NULL,
                       capacity INTEGER,
                       CONSTRAINT RoomPK PRIMARY KEY (id),
                       CONSTRAINT RoomNameUniqueKey UNIQUE (roomName)
) ENGINE = InnoDB;

CREATE INDEX RoomIndexByRoomName ON Room (roomName);


CREATE TABLE Session (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      roomId BIGINT NOT NULL,
                      movieId BIGINT NOT NULL,
                      dateTime DATETIME NOT NULL,
                      availableTickets INTEGER NOT NULL,
                      ticketPrice DECIMAL NOT NULL,
                      version INTEGER,
                      CONSTRAINT SessionPK PRIMARY KEY (id),
                      CONSTRAINT fkSessionRoom FOREIGN KEY (roomId) REFERENCES Room (id) ON DELETE CASCADE,
                      CONSTRAINT fkSessionMovie FOREIGN KEY (movieId) REFERENCES Movie (id) ON DELETE CASCADE
) ENGINE = InnoDB;


CREATE TABLE OrderTable (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            userId BIGINT NOT NULL,
                            sessionId BIGINT NOT NULL,
                            date DATETIME NOT NULL,
                            creditCard VARCHAR(16) NOT NULL,
                            numberOfTickets INTEGER NOT NULL,
                            delivered boolean,
                            CONSTRAINT OrderPK PRIMARY KEY (id),
                            CONSTRAINT OrderUserIdFK FOREIGN KEY(userId) REFERENCES User (id),
                            CONSTRAINT OrderSessionIdFK FOREIGN KEY(sessionId) REFERENCES Session (id)
                            --CONSTRAINT validTickets CHECK (numberOfTickets >= 0 AND numberOfTickets <= 10 )
) ENGINE = InnoDB;