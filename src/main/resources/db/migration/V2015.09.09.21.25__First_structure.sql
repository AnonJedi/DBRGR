CREATE TABLE department (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE chair (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  department_id INT(5) REFERENCES department(id)
);

CREATE TABLE `group`(
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  department_id INT(5) REFERENCES department(id)
);

CREATE TABLE student (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  is_head BOOLEAN DEFAULT 0,
  group_id INT(5) REFERENCES `group`(id),
  chair_id INT(5) REFERENCES chair(id)
);