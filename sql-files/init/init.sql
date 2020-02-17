-- File for new db initialization / Db Creation. Needs a default schema
CREATE TABLE brand (
       id INT AUTO_INCREMENT NOT NULL,
       name VARCHAR (50) NOT NULL,
       PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE article (
     id INT AUTO_INCREMENT NOT NULL,
     name VARCHAR (50) NOT NULL,
     brand INT,
     description VARCHAR(255) DEFAULT NULL,
     PRIMARY KEY (id),
     FOREIGN KEY (brand) REFERENCES brand(id)
) ENGINE=INNODB;

CREATE TABLE user (
      id INT AUTO_INCREMENT NOT NULL,
      username VARCHAR (100) NOT NULL,
      password VARCHAR (250) NOT NULL,
      active BOOLEAN DEFAULT FALSE,
      PRIMARY KEY (id)
) ENGINE=INNODB;