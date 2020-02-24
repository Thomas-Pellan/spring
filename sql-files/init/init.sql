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
     brand INT,
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

CREATE TABLE open_article (
      id INT AUTO_INCREMENT NOT NULL,
      ean_code VARCHAR (100) NOT NULL,
      product_name VARCHAR (100) DEFAULT NULL,
      created DATETIME NOT NULL,
      last_modified DATETIME NOT NULL,
      nutrition_grade VARCHAR (100) DEFAULT NULL,
      PRIMARY KEY (id),
      UNIQUE KEY(ean_code)
) ENGINE=INNODB;

CREATE TABLE open_ingredient (
     id INT AUTO_INCREMENT NOT NULL,
     vegetarian BOOLEAN,
     vegan BOOLEAN,
     palm_oil BOOLEAN,
     code VARCHAR (250),
     label VARCHAR (250),
     locale INT,
     PRIMARY KEY (id),
     UNIQUE KEY(code),
     FOREIGN KEY (locale) REFERENCES locale(id)
) ENGINE=INNODB;

CREATE TABLE open_ingredient_ranking (
     id INT AUTO_INCREMENT NOT NULL,
     ranking INT,
     open_article INT NOT NULL,
     open_ingredient INT NOT NULL,
     PRIMARY KEY (id),
     UNIQUE KEY(ranking, open_article, open_ingredient),
     FOREIGN KEY (open_article) REFERENCES open_article(id),
     FOREIGN KEY (open_ingredient) REFERENCES open_ingredient(id)
) ENGINE=INNODB;

CREATE TABLE importer (
      id INT AUTO_INCREMENT NOT NULL,
      status VARCHAR (100) NOT NULL,
      start_date DATETIME NOT NULL,
      end_date DATETIME DEFAULT NULL,
      log VARCHAR (250) DEFAULT NULL,
      PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE importer_file (
      id INT AUTO_INCREMENT NOT NULL,
      name VARCHAR (100) NOT NULL,
      status VARCHAR (100) NOT NULL,
      md5_checksum VARCHAR (250) DEFAULT NULL,
      importer INT NOT NULL,
      PRIMARY KEY (id),
      FOREIGN KEY (importer) REFERENCES importer(id)
) ENGINE=INNODB;

CREATE TABLE locale (
        id INT AUTO_INCREMENT NOT NULL,
        label VARCHAR (4) NOT NULL,
        PRIMARY KEY (id),
        UNIQUE KEY(label)
) ENGINE=INNODB;

-- Init with mandatory values for this to work
INSERT IGNORE INTO configuration (property_key, property_value)
VALUES ("openFood_url_list" , "https://static.openfoodfacts.org/data/delta/index.txt");
INSERT IGNORE INTO configuration (property_key, property_value)
VALUES ("openFood_url_file_query" , "https://static.openfoodfacts.org/data/delta/");