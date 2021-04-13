DROP SCHEMA IF EXISTS ScanYourStand CASCADE;
CREATE SCHEMA ScanYourStand;
SET search_path = ScanYourStand;

CREATE TABLE Exhibition
(
id				SERIAL,
name			CHARACTER VARYING (20),
isActive		BOOLEAN,
start			TIMESTAMP,
stop			TIMESTAMP,
CONSTRAINT ExhibitionPK PRIMARY KEY (id)
);

CREATE TABLE Project
(
projectNumber	CHARACTER (3) CHECK (char_length(projectNumber) = 3),
projectName		CHARACTER VARYING (20),
exhibitionId	INTEGER NOT NULL,
CONSTRAINT ExhibitionFK FOREIGN KEY (exhibitionId) REFERENCES Exhibition (id),
CONSTRAINT ProjectPK PRIMARY KEY (projectNumber)
);

INSERT INTO 
Exhibition (name, isActive, start, stop) 
VALUES
('EXPO 2021', true, '2021-08-04', '2021-08-08'),
('EXPO 2020', false, '2020-08-01', '2020-08-04');

INSERT INTO 
Project (projectNumber, projectName, exhibitionId) 
VALUES
('M13', 'Project 1', 1),
('B12', 'Project 2', 1);

CREATE TABLE Vote
(
phone			CHARACTER (30),
projectNumber	CHARACTER (3) CHECK (char_length(projectNumber) = 3),
points			INTEGER CHECK (0 <= points AND points <= 5),
CONSTRAINT VotePK PRIMARY KEY (phone, projectNumber),
CONSTRAINT ProjectFK FOREIGN KEY (projectNumber) REFERENCES Project (projectNumber)
);

CREATE TABLE Admin
(
username		CHARACTER VARYING (20),
hashedPassword	CHARACTER VARYING (64), 
CONSTRAINT AdminPK PRIMARY KEY (username)
);