package rgr.core.repository;

import org.springframework.beans.factory.annotation.Autowired;
import rgr.core.domain.DeleteForm;
import rgr.core.domain.InsertForm;
import rgr.core.domain.UpdateForm;
import rgr.core.domain.UserImpl;
import rgr.core.mappers.Mapper;
import rgr.web.domain.model.*;
import rgr.web.domain.model.Class;

import java.util.List;
import java.util.Optional;

/**
 * Created by vik on 09.09.15.
 */

@org.springframework.stereotype.Repository
public class Repository implements IRepository {

    @Autowired
    private Mapper mapper;

    @Override
    public void createUser(UserImpl user) {
        mapper.createUser(user);
    }

    @Override
    public Optional<UserImpl> checkUser(UserImpl user) {
        return Optional.ofNullable(mapper.checkUser(user));
    }


    @Override
    public List<String> getDep() {
        return mapper.getDep();
    }

    @Override
    public List<String> getCh() {
        return mapper.getCh();
    }

    @Override
    public List<String> getLec() {
        return mapper.getLec();
    }

    @Override
    public List<String> getCl() {
        return mapper.getCl();
    }

    @Override
    public List<String> getSt() {
        return mapper.getSt();
    }

    @Override
    public List<String> getCrs() {
        return mapper.getCrs();
    }

    @Override
    public List<Department> getAllDepartments() {
        return mapper.getAllDepartments();
    }

    @Override
    public List<Chair> getAllChairs() {
        return mapper.getAllChairs();
    }

    @Override
    public List<Class> getAllClasses () {
        return mapper.getAllClasses();
    }

    @Override
    public List<Student> getAllStudents() {
        return mapper.getAllStudents();
    }

    @Override
    public List<Course> getAllCourses() {
        return mapper.getAllCourses();
    }

    @Override
    public List<Lecturer> getAllLecturers() {
        return mapper.getAllLecturer();
    }

    @Override
    public List<StudentCourse> getAllStdCrs() {
        return mapper.getAllStdCrs();
    }

    @Override
    public void insertDepartment(InsertForm form) {
        mapper.insertDepartment(form);
    }

    @Override
    public void insertChair(InsertForm form) {
        mapper.insertChair(form);
    }

    @Override
    public void insertLecturer(InsertForm form) {
        mapper.insertLecturer(form);
    }

    @Override
    public void insertClass(InsertForm form) {
        mapper.insertClass(form);
    }

    @Override
    public void insertStudent(InsertForm form) {
        mapper.insertStudent(form);
    }

    @Override
    public void insertCourse(InsertForm form) {
        mapper.insertCourse(form);
    }

    @Override
    public void insertStdCrs(InsertForm form) {
        mapper.insertStdCrs(form);
    }


    @Override
    public void deleteDepartment(DeleteForm form) {
        mapper.deleteDepartment(form);
    }

    @Override
    public void deleteChair(DeleteForm form) {
        mapper.deleteChair(form);
    }

    @Override
    public void deleteLecturer(DeleteForm form) {
        mapper.deleteLecturer(form);
    }

    @Override
    public void deleteClass(DeleteForm form) {
        mapper.deleteClass(form);
    }

    @Override
    public void deleteStudent(DeleteForm form) {
        mapper.deleteStudent(form);
    }

    @Override
    public void deleteCourse(DeleteForm form) {
        mapper.deleteCourse(form);
    }

    @Override
    public void deleteStdCrs(DeleteForm form) {
        mapper.deleteStdCrs(form);
    }

    @Override
    public void updateDepartment(UpdateForm form) {
        mapper.updateDepartment(form);
    }

    @Override
    public void updateChair(UpdateForm form) {
        mapper.updateChair(form);
    }

    @Override
    public void updateLecturer(UpdateForm form) {
        mapper.updateLecturer(form);
    }

    @Override
    public void updateClass(UpdateForm form) {
        mapper.updateClass(form);
    }

    @Override
    public void updateStudent(UpdateForm form) {
        mapper.updateStudent(form);
    }

    @Override
    public void updateCourse(UpdateForm form) {
        mapper.updateCourse(form);
    }

    @Override
    public void updateStdCrs(UpdateForm form) {
        mapper.updateStdCrs(form);
    }

    @Override
    public List<SpecialSeveralColumn> getSpecial1(String str) {
        return mapper.getSpecial1(str);
    }

    @Override
    public List<SpecialSeveralColumn> getSpecial2(String str) {
        return mapper.getSpecial2(str);
    }

    @Override
    public int getSpecial3(String str) {
        return mapper.getSpecial3(str);
    }

    @Override
    public List<SpecialSeveralColumn> getSpecial4(String str) {
        return mapper.getSpecial4(str);
    }
}
