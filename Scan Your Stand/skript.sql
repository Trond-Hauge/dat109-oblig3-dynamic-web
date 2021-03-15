DROP SCHEMA IF EXISTS ScanYourStand CASCADE;
CREATE SCHEMA ScanYourStand;
SET search_path = ScanYourStand;

CREATE TABLE Stand
(
-- Assuming length of standID is always 4
standID			CHARACTER (4) CHECK (char_length(standID) = 4),
standName		CHARACTER VARYING (20),
CONSTRAINT StandPK PRIMARY KEY (standID)
);

INSERT INTO Stand (standID, standName) VALUES
('M123', 'Stand1'),
('B123', 'Stand2');

CREATE TABLE Vote
(
--Assuming length of phone is always 8
phone		CHARACTER (8) CHECK (char_length(phone) = 8),
standID		CHARACTER VARYING (10),
points		INTEGER CHECK (0 <= points AND points <= 5),
CONSTRAINT VotePK PRIMARY KEY (phone, standID),
CONSTRAINT StandFK FOREIGN KEY (standID) REFERENCES stand (standID)
);

INSERT INTO Vote (phone, standID, points) VALUES
('97084676', 'M123', 5),
('95991519', 'M123', 2),
('97084676', 'B123', 4);

CREATE TABLE Admin
(
username		CHARACTER VARYING (20),
hashedPassword	CHARACTER VARYING (50), --Assuming no set length
CONSTRAINT AdminPK PRIMARY KEY (username)
);