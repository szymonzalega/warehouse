DROP TABLE IF EXISTS company;

CREATE TABLE company(
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  mailAddress VARCHAR(250) DEFAULT NULL
);

INSERT INTO company (name, mailAddress) VALUES
  ('Aliko', 'Dangote'),
  ('Bill', 'Gates'),
  ('Folrunsho', 'Billionaire Oil Magnate');