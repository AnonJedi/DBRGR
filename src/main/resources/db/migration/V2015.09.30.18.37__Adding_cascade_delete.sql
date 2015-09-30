ALTER TABLE student_course DROP CONSTRAINT student_course_student_id_fkey;
ALTER TABLE student_course ADD FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE CASCADE;
ALTER TABLE student_course DROP CONSTRAINT student_course_course_id_fkey;
ALTER TABLE student_course ADD FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE;