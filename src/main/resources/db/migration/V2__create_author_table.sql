CREATE SEQUENCE s_person_id START WITH 1;

CREATE TABLE person (
  id INT NOT NULL,
  name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  date_of_birth DATE NOT NULL,

  CONSTRAINT pk_t_author PRIMARY KEY (ID)
);