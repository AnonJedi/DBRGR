package rgr.core.mappers;

import org.apache.ibatis.annotations.*;
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

    @Select("SELECT *\n" +
            "FROM department")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name")
    })
    public List<Department> getAllDepartments();

    @Select("SELECT *\n" +
            "FROM chair")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "department_id", property = "departmentId")
    })
    public List<Chair> getAllChairs();

    @Select("SELECT *\n" +
            "FROM class")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "department_id", property = "departmentId")
    })
    public List<Class> getAllClasses();

    @Select("SELECT *\n" +
            "FROM student")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "firstname", property = "firstname"),
            @Result(column = "lastname", property = "lastname"),
            @Result(column = "is_head", property = "isHead"),
            @Result(column = "class_id", property = "classId"),
            @Result(column = "chair_id", property = "chairId")
    })
    public List<Student> getAllStudents();

    @Select("SELECT *\n" +
            "FROM course")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "lecturer_id", property = "lecturerId")
    })
    public List<Course> getAllCourses();

    @Select("SELECT *\n" +
            "FROM lecturer")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "firstname", property = "firstname"),
            @Result(column = "lastname", property = "lastname"),
            @Result(column = "chair_id", property = "chairId")
    })
    public List<Lecturer> getAllLecturer();

    @Select("SELECT student.lastname, course.name\n" +
            "FROM student, course, student_course\n" +
            "WHERE student.id=student_id AND course.id=course_id")
    @Results({
            @Result(column = "student.lastname", property = "lastname"),
            @Result(column = "course.name", property = "name")
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
            "   (#{name}, #{departmentId})")
    public void insertChair(InsertForm form);

    @Insert("INSERT INTO lecturer\n" +
            "   (firstname, lastname, chair_id)\n" +
            "VALUES\n" +
            "   (#{firstname}, #{lastname}, #{chairId})")
    public void insertLecturer(InsertForm form);

    @Insert("INSERT INTO class\n" +
            "   (name, department_id)\n" +
            "VALUES\n" +
            "   (#{name}, #{departmentId})")
    public void insertClass(InsertForm form);

    @Insert("INSERT INTO student\n" +
            "   (firstname, lastname, class_id, chair_id)\n" +
            "VALUES\n" +
            "   (#{firstname}, #{lastname}, #{classId}, #{chairId})")
    public void insertStudent(InsertForm form);

    @Insert("INSERT INTO course\n" +
            "   (name, lecturer_id)\n" +
            "VALUES\n" +
            "   (#{name}, #{lecturerId})")
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
    public void deleteDepartment(InsertForm form);

    @Delete("DELETE FROM chair\n" +
            "WHERE name=#{name} AND department_id=#{departmentId}")
    public void deleteChair(InsertForm form);

    @Delete("DELETE FROM lecturer\n" +
            "WHERE firstname=#{firstname} AND lastname=#{lastname}")
    public void deleteLecturer(InsertForm form);

    @Delete("DELETE FROM class\n" +
            "WHERE name=#{name} AND department_id=#{departmentId}")
    public void deleteClass(InsertForm form);

    @Delete("DELETE FROM student\n" +
            "WHERE firstname=#{firstname} AND lastname=#{lastname} AND class_id=#{classId}")
    public void deleteStudent(InsertForm form);

    @Delete("DELETE FROM course\n" +
            "WHERE name=#{name} AND lecturer_id=#{lecturerId}")
    public void deleteCourse(InsertForm form);

    @Delete("DELETE FROM student_course\n" +
            "WHERE student_id=(SELECT id\n" +
            "   FROM student\n" +
            "   WHERE lastname=#{lastname})" +
            "AND course_id=(SELECT id\n" +
            "   FROM course\n" +
            "   WHERE name=#{name})")
    public void deleteStdCrs(InsertForm form);


    @Update("UPDATE department\n" +
            "SET name=#{name}\n" +
            "WHERE name=#{oldName}")
    public void updateDepartment(UpdateForm form);

    @Update("UPDATE chair\n" +
            "SET name=#{name}, department_id=#{departmentId}\n" +
            "WHERE name=#{oldName} AND department_id=#{oldDepartmentId}")
    public void updateChair(UpdateForm form);

    @Update("UPDATE lecturer\n" +
            "SET firstname=#{firstname}, lastname=#{lastname}, chair_id=#{chairId}\n" +
            "WHERE firstname=#{oldFirstname} AND lastname=#{oldLastname} AND chair_id=#{oldChairId}")
    public void updateLecturer(UpdateForm form);

    @Update("UPDATE class\n" +
            "SET name=#{name}, department_id=#{departmentId}\n" +
            "WHERE name=#{oldName} AND department_id=#{oldDepartmentId}")
    public void updateClass(UpdateForm form);

    @Update("UPDATE student\n" +
            "SET firstname=#{firstname}, lastname=#{lastname}, class_id=#{classId}, chair_id=#{chairId}, is_head=#{isHead}\n" +
            "WHERE firstname=#{oldFirstname} AND lastname=#{oldLastname} AND class_id=#{oldClassId} AND chair_id=#{oldChairId} AND is_head=#{oldIsHead}")
    public void updateStudent(UpdateForm form);

    @Update("UPDATE course\n" +
            "SET name=#{name}, lecturer_id=#{lecturerId}\n" +
            "WHERE name=#{oldName} AND lecturer_id=#{oldLecturerId}")
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
}
