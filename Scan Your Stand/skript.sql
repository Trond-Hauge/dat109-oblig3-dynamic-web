DROP SCHEMA IF EXISTS ScanYourStand CASCADE;
CREATE SCHEMA ScanYourStand;
SET search_path = ScanYourStand;

CREATE TABLE Project
(
projectNumber	CHARACTER (3) CHECK (char_length(projectNumber) = 3),
projectName		CHARACTER VARYING (20),
CONSTRAINT ProjectPK PRIMARY KEY (projectNumber)
);

INSERT INTO Project (projectNumber, projectName) VALUES
('M13', 'Project 1'),
('B12', 'Project 2');

CREATE TABLE Vote
(
phone			CHARACTER (8) CHECK (char_length(phone) = 8),
projectNumber	CHARACTER (3) CHECK (char_length(projectNumber) = 3),
points			INTEGER CHECK (0 <= points AND points <= 5),
CONSTRAINT VotePK PRIMARY KEY (phone, projectNumber),
CONSTRAINT ProjectFK FOREIGN KEY (projectNumber) REFERENCES Project (projectNumber)
);

INSERT INTO Vote (phone, projectNumber, points) VALUES
('97084676', 'M13', 5),
('95991519', 'M13', 2),
('97084676', 'B12', 4);

CREATE TABLE Admin
(
username		CHARACTER VARYING (20),
hashedPassword	CHARACTER VARYING (64), 
CONSTRAINT AdminPK PRIMARY KEY (username)
);

CREATE TABLE Exhibition
(
id				SERIAL,
name			CHARACTER VARYING (20),
isActive		BOOLEAN,
start			DATE,
stop			DATE,
CONSTRAINT ExhibitionPK PRIMARY KEY (id)
);

INSERT INTO Exhibition (name, isActive, start, stop) VALUES
('EXPO 2021', true, '2021-08-04', '2021-08-08'),
('EXPO 2020', false, '2020-08-01', '2020-08-04');