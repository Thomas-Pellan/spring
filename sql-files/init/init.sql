-- File for new db initialization / Db Creation. Needs a default schema
CREATE TABLE brand (
       id INT AUTO_INCREMENT NOT NULL,
       name VARCHAR (50) NOT NULL,
       PRIMARY KEY (id),
       UNIQUE KEY(name)
) ENGINE=INNODB;

CREATE TABLE article (
     id INT AUTO_INCREMENT NOT NULL,
     name VARCHAR (50) NOT NULL,
     brand INT,s
     description VARCHAR(255) DEFAULT NULL,
     PRIMARY KEY (id),
     UNIQUE KEY(name),
     FOREIGN KEY (brand) REFERENCES brand(id)
) ENGINE=INNODB;

CREATE TABLE user (
      id INT AUTO_INCREMENT NOT NULL,
      username VARCHAR (100) NOT NULL,
      password VARCHAR (250) NOT NULL,
      active BOOLEAN DEFAULT FALSE,
      PRIMARY KEY (id),
      UNIQUE KEY(username)
) ENGINE=INNODB;

CREATE TABLE configuration (
      id INT AUTO_INCREMENT NOT NULL,
      property_key VARCHAR (100) NOT NULL,
      property_value VARCHAR (250) NOT NULL,
      PRIMARY KEY (id),
      UNIQUE KEY(property_key)
) ENGINE=INNODB;

-- Init with mandatory values for this to work
INSERT IGNORE INTO configuration (property_key, property_value)
VALUES ("openFood_url_list" , "https://static.openfoodfacts.org/data/delta/index.txt");
INSERT IGNORE INTO configuration (property_key, property_value)
VALUES ("openFood_url_file_query" , "https://static.openfoodfacts.org/data/delta/");