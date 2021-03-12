DROP SCHEMA IF EXISTS ScanYourStand CASCADE;
CREATE SCHEMA ScanYourStand;
SET search_path = ScanYourStand;

CREATE TABLE Stand
(
standID		VARCHAR(10) UNIQUE,
standName	VARCHAR(20)	
);

INSERT INTO Stand (standName) VALUES
('Stand1'),
('Stand2');