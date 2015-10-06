package rgr.core.mappers;

import org.apache.ibatis.annotations.*;
import rgr.core.domain.DeleteForm;
import rgr.core.domain.InsertForm;
import rgr.core.domain.UpdateForm;
import rgr.core.domain.UserImpl;
import rgr.web.domain.model.*;
import rgr.web.domain.model.Class;

import java.util.List;
import java.util.Optional;

public interface Mapper {

    @Insert("INSERT INTO users\n" +
            "   (username, password)\n" +
            "VALUES\n" +
            "   (#{username}, #{password})")
    public void createUser(UserImpl user);

    @Select("SELECT *\n" +
            "FROM users\n" +
            "WHERE username=#{username}")
    @Results({
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password")}
    )
    public UserImpl checkUser(UserImpl user);

    @Select("SELECT name FROM department")
    public List<String> getDep();

    @Select("SELECT name FROM chair")
    public List<String> getCh();

    @Select("SELECT lastname FROM lecturer")
    public List<String> getLec();

    @Select("SELECT name FROM class")
    public List<String> getCl();

    @Select("SELECT lastname FROM student")
    public List<String> getSt();

    @Select("SELECT name FROM course")
    public List<String> getCrs();

    @Select("SELECT *\n" +
            "FROM department")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name")
    })
    public List<Department> getAllDepartments();

    @Select("SELECT chair.id as id, chair.name as name, department.name as dn\n" +
            "FROM chair LEFT JOIN department\n" +
            "ON chair.department_id=department.id")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "dn", property = "departmentName")
    })
    public List<Chair> getAllChairs();

    @Select("SELECT class.id as id, class.name as name, department.name as dn\n" +
            "FROM class LEFT JOIN department\n" +
            "ON class.department_id=department.id")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "dn", property = "departmentName")
    })
    public List<Class> getAllClasses();

    @Select("SELECT student.id as id, firstname, lastname, is_head, class.name as cn, chair.name as chn\n" +
            "FROM student\n" +
            "LEFT JOIN class ON class.id=student.class_id\n" +
            "LEFT JOIN chair ON chair.id=student.chair_id")
    @Results({
            @Result(column = "student.id", property = "id"),
            @Result(column = "student.firstname", property = "firstname"),
            @Result(column = "student.lastname", property = "lastname"),
            @Result(column = "student.is_head", property = "isHead"),
            @Result(column = "cn", property = "className"),
            @Result(column = "chn", property = "chairName")
    })
    public List<Student> getAllStudents();

    @Select("SELECT course.id as id, course.name as name, lecturer.lastname as ln\n" +
            "FROM course LEFT JOIN lecturer\n" +
            "ON lecturer.id=course.lecturer_id")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "ln", property = "lecturerName")
    })
    public List<Course> getAllCourses();

    @Select("SELECT lecturer.id as id, firstname, lastname, name\n" +
            "FROM lecturer LEFT JOIN chair\n" +
            "ON chair.id=lecturer.chair_id")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "firstname", property = "firstname"),
            @Result(column = "lastname", property = "lastname"),
            @Result(column = "name", property = "chairName")
    })
    public List<Lecturer> getAllLecturer();

    @Select("SELECT lastname, name\n" +
            "FROM student, course, student_course\n" +
            "WHERE student.id=student_id AND course.id=course_id")
    @Results({
            @Result(column = "lastname", property = "lastname"),
            @Result(column = "name", property = "name")
    })
    public List<StudentCourse> getAllStdCrs();

    @Insert("INSERT INTO department\n" +
            "   (name)\n" +
            "VALUES\n" +
            "   (#{name})")
    public void insertDepartment(InsertForm form);

    @Insert("INSERT INTO chair\n" +
            "   (name, department_id)\n" +
            "VALUES\n" +
            "   (#{name}, (SELECT id\n" +
            "   FROM department\n" +
            "   WHERE name=#{department}))")
    public void insertChair(InsertForm form);

    @Insert("INSERT INTO lecturer\n" +
            "   (firstname, lastname, chair_id)\n" +
            "VALUES\n" +
            "   (#{firstname}, #{lastname}, (SELECT id\n" +
            "   FROM chair\n" +
            "   WHERE name=#{chair}))")
    public void insertLecturer(InsertForm form);

    @Insert("INSERT INTO class\n" +
            "   (name, department_id)\n" +
            "VALUES\n" +
            "   (#{name}, (SELECT id\n" +
            "   FROM department\n" +
            "   WHERE name=#{department}))")
    public void insertClass(InsertForm form);

    @Insert("INSERT INTO student\n" +
            "   (firstname, lastname, class_id, chair_id)\n" +
            "VALUES\n" +
            "   (#{firstname}, #{lastname}, (SELECT id\n" +
            "   FROM class\n" +
            "   WHERE name=#{clas}), (SELECT id\n" +
            "   FROM chair\n" +
            "   WHERE name=#{chair}))")
    public void insertStudent(InsertForm form);

    @Insert("INSERT INTO course\n" +
            "   (name, lecturer_id)\n" +
            "VALUES\n" +
            "   (#{name}, (SELECT id\n" +
            "   FROM lecturer\n" +
            "   WHERE lastname=#{lastname}))")
    public void insertCourse(InsertForm form);

    @Insert("INSERT INTO student_course\n" +
            "   (student_id, course_id)\n" +
            "VALUES\n" +
            "   ((SELECT id\n" +
            "   FROM student\n" +
            "   WHERE lastname=#{lastname}),\n" +
            "   (SELECT id\n" +
            "   FROM course\n" +
            "   WHERE name=#{name}))")
    public void insertStdCrs(InsertForm form);


    @Delete("DELETE FROM department\n" +
            "WHERE name=#{name}")
    public void deleteDepartment(DeleteForm form);

    @Delete("DELETE FROM chair\n" +
            "WHERE name=#{name} AND department_id=(SELECT id\n" +
            "   FROM department\n" +
            "   WHERE name=#{department})")
    public void deleteChair(DeleteForm form);

    @Delete("DELETE FROM lecturer\n" +
            "WHERE firstname=#{firstname} AND lastname=#{lastname}")
    public void deleteLecturer(DeleteForm form);

    @Delete("DELETE FROM class\n" +
            "WHERE name=#{name} AND department_id=(SELECT id\n" +
            "   FROM department\n" +
            "   WHERE name=#{department})")
    public void deleteClass(DeleteForm form);

    @Delete("DELETE FROM student\n" +
            "WHERE firstname=#{firstname} AND lastname=#{lastname} AND class_id=(SELECT id\n" +
            "   FROM class\n" +
            "   WHERE name=#{clas})")
    public void deleteStudent(DeleteForm form);

    @Delete("DELETE FROM course\n" +
            "WHERE name=#{name} AND lecturer_id=(SELECT id\n" +
            "   FROM lecturer\n" +
            "   WHERE lastname=#{lastname})")
    public void deleteCourse(DeleteForm form);

    @Delete("DELETE FROM student_course\n" +
            "WHERE student_id=(SELECT id\n" +
            "   FROM student\n" +
            "   WHERE lastname=#{lastname})" +
            "AND course_id=(SELECT id\n" +
            "   FROM course\n" +
            "   WHERE name=#{name})")
    public void deleteStdCrs(DeleteForm form);


    @Update("UPDATE department\n" +
            "SET name=#{name}\n" +
            "WHERE name=#{oldName}")
    public void updateDepartment(UpdateForm form);

    @Update("UPDATE chair\n" +
            "SET name=#{name}, department_id=(SELECT id\n" +
            "   FROM department\n" +
            "   WHERE name=#{department})\n" +
            "WHERE name=#{oldName} AND department_id=(SELECT id\n" +
            "   FROM department\n" +
            "   WHERE name=#{oldDepartment})")
    public void updateChair(UpdateForm form);

    @Update("UPDATE lecturer\n" +
            "SET firstname=#{firstname}, lastname=#{lastname}, chair_id=(SELECT id\n" +
            "   FROM chair\n" +
            "   WHERE name=#{chair})\n" +
            "WHERE firstname=#{oldFirstname} AND lastname=#{oldLastname} AND chair_id=(SELECT id\n" +
            "   FROM chair\n" +
            "   WHERE name=#{oldChair})")
    public void updateLecturer(UpdateForm form);

    @Update("UPDATE class\n" +
            "SET name=#{name}, department_id=(SELECT id\n" +
            "   FROM department\n" +
            "   WHERE name=#{department})\n" +
            "WHERE name=#{oldName} AND department_id=(SELECT id\n" +
            "   FROM department\n" +
            "   WHERE name=#{oldDepartment})")
    public void updateClass(UpdateForm form);

    @Update("UPDATE student\n" +
            "SET firstname=#{firstname}, lastname=#{lastname}, class_id=(SELECT id\n" +
            "   FROM class\n" +
            "   WHERE name=#{clas}), chair_id=(SELECT id\n" +
            "   FROM chair\n" +
            "   WHERE name=#{chair}), is_head=#{isHead}\n" +
            "WHERE firstname=#{oldFirstname} AND lastname=#{oldLastname} AND class_id=(SELECT id\n" +
            "   FROM class\n" +
            "   WHERE name=#{oldClas}) AND chair_id=(SELECT id\n" +
            "   FROM chair\n" +
            "   WHERE name=#{oldChair}) AND is_head=#{oldIsHead}")
    public void updateStudent(UpdateForm form);

    @Update("UPDATE course\n" +
            "SET name=#{name}, lecturer_id=(SELECT id\n" +
            "   FROM lecturer\n" +
            "   WHERE lastname=#{lastname})\n" +
            "WHERE name=#{oldName} AND lecturer_id=(SELECT id\n" +
            "   FROM lecturer\n" +
            "   WHERE lastname=#{oldLastname})")
    public void updateCourse(UpdateForm form);

    @Update("UPDATE student_course\n" +
            "SET student_id=(SELECT id\n" +
            "   FROM student\n" +
            "   WHERE lastname=#{lastname}),\n" +
            "   course_id=(SELECT id\n" +
            "   FROM course\n" +
            "   WHERE name=#{name})\n" +
            "WHERE student_id=(SELECT id\n" +
            "   FROM student\n" +
            "   WHERE lastname=#{oldLastname})\n" +
            "AND course_id=(SELECT id\n" +
            "   FROM course\n" +
            "   WHERE name=#{oldName})")
    public void updateStdCrs(UpdateForm form);

    @Select("SELECT firstname as f, lastname as l, chair.name as n\n" +
            "FROM lecturer INNER JOIN chair\n" +
            "ON chair.id=lecturer.chair_id\n" +
            "INNER JOIN department\n" +
            "ON department.name=#{str} AND department.id=chair.department_id")
    @Results(value = {
            @Result(column = "f", property = "firstname"),
            @Result(column = "l", property = "lastname"),
            @Result(column = "n", property = "home")
    })
    public List<SpecialSeveralColumn> getSpecial1(String str);

    @Select("SELECT student.firstname as f, student.lastname as l, class.name as c\n" +
            "FROM student INNER JOIN student_course\n" +
            "ON student.id=student_course.student_id\n" +
            "INNER JOIN course\n" +
            "ON course.id=student_course.course_id\n" +
            "RIGHT JOIN lecturer\n" +
            "ON lecturer.id=course.lecturer_id AND lecturer.lastname=#{str}\n" +
            "INNER JOIN class\n" +
            "ON class.id=student.class_id")
    @Results(value = {
            @Result(column = "f", property = "firstname"),
            @Result(column = "l", property = "lastname"),
            @Result(column = "c", property = "home")
    })
    public List<SpecialSeveralColumn> getSpecial2(String str);

    @Select("SELECT count(student.id) \n" +
            "FROM student RIGHT JOIN class \n" +
            "ON student.class_id=class.id\n" +
            "INNER JOIN department\n" +
            "ON department.id=class.department_id AND department.name=#{str}")
    public int getSpecial3(String str);

    @Select("SELECT student.firstname as f, student.lastname as l, class.name as n\n" +
            "FROM student INNER JOIN class\n" +
            "ON student.class_id=class.id\n" +
            "INNER JOIN department\n" +
            "ON department.id=class.department_id AND department.name=#{str}")
    @Results(value = {
            @Result(column = "f", property = "firstname"),
            @Result(column = "l", property = "lastname"),
            @Result(column = "n", property = "home")
    })
    public List<SpecialSeveralColumn> getSpecial4(String str);
}
