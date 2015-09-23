CREATE TABLE department (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE chair (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  department_id INTEGER REFERENCES department(id)
);

CREATE TABLE class (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  department_id INTEGER REFERENCES department(id)
);

CREATE TABLE student (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  is_head BOOLEAN DEFAULT FALSE,
  group_id INTEGER REFERENCES class(id),
  chair_id INTEGER REFERENCES chair(id)
);