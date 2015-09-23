CREATE TABLE lecturer (
    id SERIAL PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    chair_id INTEGER REFERENCES chair(id)
);

CREATE TABLE course (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    lecturer_id INTEGER REFERENCES lecturer(id)
);

CREATE TABLE student_course (
    student_id INTEGER REFERENCES student(id),
    course_id INTEGER REFERENCES course(id)
);