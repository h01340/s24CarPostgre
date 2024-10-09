CREATE TABLE owner
(
id BIGSERIAL NOT NULL PRIMARY KEY
, firstname VARCHAR(100) NOT NULL
, lastname VARCHAR(100) NOT NULL
, city VARCHAR(50) 
, ssn VARCHAR(15) NOT NULL
, yob BIGINT
);


INSERT INTO owner (firstname, lastname, city, ssn, yob) 
VALUES ('Maria', 'Marison', 'Manse', '150574-113I', 1974)
, ('Iines', 'Ankka', 'Ankkalinna', '110140-111', 1940)
, ('Aku', 'Ankka', 'Ankkalinna', '130140-113', 1940)
, ('Minnie', 'Minison', 'Turku', '250170-111M',1970);


CREATE TABLE car (
id BIGSERIAL NOT NULL PRIMARY KEY
, brand VARCHAR(50) NOT NULL
, model VARCHAR(50) NOT NULL
, color VARCHAR(50) NOT NULL
, register_number VARCHAR(50) NOT NULL
, production_year INT
, price BIGINT
, ownerid BIGINT
, FOREIGN KEY (ownerid) REFERENCES owner(id));

INSERT INTO car (model, brand, color, register_number, production_year, price, ownerid) 
VALUES ('Ford', 'Taunus', 'Red', 'xxx-111', 1979, 5000, 1), 
('Ford', 'Escort', 'blue', 'abc-111', 1999, 8000, 1), 
('Volkswagen','Golf', 'red', 'yyy-222', 1990, 7000, 1);


CREATE TABLE application_user
(
id BIGSERIAL NOT NULL PRIMARY KEY
, firstname VARCHAR(100) NOT NULL
, lastname VARCHAR(100) NOT NULL
, role  VARCHAR(100) NOT NULL
,username VARCHAR(250) NOT NULL
,password_hash VARCHAR(250) NOT NULL);

INSERT INTO application_user (firstname, lastname, username, password_hash, role) 
VALUES ('Minna', 'Pellikka', 'user', '$2a$10$1DTvwpXVBArGFixHBuzVJObjTuXhIOkx5pse6KsYs8/C2ckxnGEou', 'USER'), 
('Kurko', 'Kurkonen', 'admin', '$2a$10$cDZgyF4xaPMmmoRW3OVcmuf.8o2YSx8.M7CeRKqi.1PVw.t3E8uEC', 'ADMIN');


SELECT * FROM car;
SELECT * FROM owner;
SELECT * FROM application_user;