DROP SCHEMA IF EXISTS ScanYourStand CASCADE;
CREATE SCHEMA ScanYourStand;
SET search_path = ScanYourStand;

CREATE TABLE Stand
(
standID		SERIAL UNIQUE,
standName	VARCHAR(20)	
);

INSERT INTO Stand (standName) VALUES
('Stand1'),
('Stand2');