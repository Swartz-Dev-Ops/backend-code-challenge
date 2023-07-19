create table User
(
    id        bigint              not null primary key,
    firstName varchar(255)        not null,
    lastName  varchar(255)        not null,
    username  varchar(255) unique not null,
    password  varchar(255)        not null -- WHAT!? NOT ENCRYPTED!? ;-)
);

insert into User
    (id, firstName, lastName, username, password)
values (1, 'Phil', 'Ingwell', 'PhilIngwell', 'Password123') ,
    (2, 'Anna', 'Conda', 'AnnaConda', 'Password234');

CREATE TABLE Address 
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    address1 VARCHAR(255) NOT NULL,
    address2 VARCHAR(255),
    city VARCHAR(255) NOT NULL,
    state VARCHAR(100) NOT NULL,
    postal VARCHAR(10) NOT NULL
);

INSERT INTO Address
    (id, address1, address2, city, state, postal)
VALUES (1, '123 Street', '', 'Fenton', 'MI', '48430') ,
    (2, '123 Road', '', 'Holland', 'OH', '43528');

ALTER TABLE User 
ADD COLUMN address_id INT UNIQUE;

ALTER TABLE User
ADD FOREIGN KEY (address_id) REFERENCES Address(id);

UPDATE User
SET User.address_id = 1
WHERE User.id = 2;

UPDATE User
SET User.address_id = 2
WHERE User.id = 1;