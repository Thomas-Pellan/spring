-- Brand sample information
INSERT INTO brand (name) VALUES ('Example Brand 1');
INSERT INTO brand (name) VALUES ('Example Brand 2');
INSERT INTO brand (name) VALUES ('Example Brand 3');

-- Article sample information
INSERT INTO article (name, brand, description)
VALUES ("Article example 1", (SELECT id FROM brand WHERE name = 'Example Brand 1'), 'This is a description for article 1');
INSERT INTO article (name, brand, description)
VALUES ("Article example 2", (SELECT id FROM brand WHERE name = 'Example Brand 1'), 'This is a description for article 2 on brand 1');
INSERT INTO article (name, brand, description)
VALUES ("Article example 3", (SELECT id FROM brand WHERE name = 'Example Brand 1'), 'This is a description for article 3 on brand 1');
INSERT INTO article (name, brand, description)
VALUES ("Article example 4", (SELECT id FROM brand WHERE name = 'Example Brand 2'), 'This is a description for article 4 on brand 2');