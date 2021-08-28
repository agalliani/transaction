CREATE DATABASE tododb;
USE tododb;

CREATE TABLE todo (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(250),
    summary VARCHAR(250),
    description VARCHAR(500)
);

