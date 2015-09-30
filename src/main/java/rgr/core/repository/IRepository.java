package rgr.core.repository;

import org.apache.ibatis.annotations.Insert;
import rgr.core.domain.DeleteForm;
import rgr.core.domain.InsertForm;
import rgr.core.domain.UpdateForm;
import rgr.core.domain.UserImpl;
import rgr.web.domain.model.*;
import rgr.web.domain.model.Class;

import java.util.List;
import java.util.Optional;

/**
 * Created by vik on 09.09.15.
 */
public interface IRepository {

    public void createUser(UserImpl user);
    public Optional<UserImpl> checkUser(UserImpl user);

    public List<String> getDep();
    public List<String> getCh();
    public List<String> getLec();
    public List<String> getCl();
    public List<String> getSt();
    public List<String> getCrs();

    public List<Department> getAllDepartments();
    public List<Chair> getAllChairs();
    public List<Class> getAllClasses();
    public List<Student> getAllStudents();
    public List<Course> getAllCourses();
    public List<Lecturer> getAllLecturers();
    public List<StudentCourse> getAllStdCrs();

    public void insertDepartment(InsertForm form);
    public void insertChair(InsertForm form);
    public void insertLecturer(InsertForm form);
    public void insertClass(InsertForm form);
    public void insertStudent(InsertForm form);
    public void insertCourse(InsertForm form);
    public void insertStdCrs(InsertForm form);

    public void deleteDepartment(DeleteForm form);
    public void deleteChair(DeleteForm form);
    public void deleteLecturer(DeleteForm form);
    public void deleteClass(DeleteForm form);
    public void deleteStudent(DeleteForm form);
    public void deleteCourse(DeleteForm form);
    public void deleteStdCrs(DeleteForm form);

    public void updateDepartment(UpdateForm form);
    public void updateChair(UpdateForm form);
    public void updateLecturer(UpdateForm form);
    public void updateClass(UpdateForm form);
    public void updateStudent(UpdateForm form);
    public void updateCourse(UpdateForm form);
    public void updateStdCrs(UpdateForm form);
}
