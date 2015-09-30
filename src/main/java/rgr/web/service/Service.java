package rgr.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import rgr.core.domain.DeleteForm;
import rgr.core.domain.InsertForm;
import rgr.core.domain.UpdateForm;
import rgr.core.domain.UserImpl;
import rgr.core.repository.IRepository;
import rgr.web.domain.model.*;
import rgr.web.domain.model.Class;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by vik on 09.09.15.
 */

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    @Qualifier(value = "repository")
    private IRepository repository;

    public boolean createUser(UserImpl user) {

        Optional<UserImpl> newUser = repository.checkUser(user);
        if (newUser.isPresent()) {
            if (newUser.get().getUsername().equals(user.getUsername())) return false;
        }

        repository.createUser(user);
        return true;
    }

    public boolean checkUser(UserImpl user) {

        Optional<UserImpl> currentUser = repository.checkUser(user);

        if (currentUser.get().getUsername().equals(user.getUsername()) &&
                currentUser.get().getPassword().equals(user.getPassword())) {
            return true;
        }

        return false;
    }

    public Map<String, List<String>> getStringsForDropdown() {
        Map<String, List<String>> map = new HashMap<>();

        map.put("department", repository.getDep());
        map.put("chair", repository.getCh());
        map.put("lecturer", repository.getLec());
        map.put("class", repository.getCl());
        map.put("student", repository.getSt());
        map.put("course", repository.getCrs());

        return map;
    }

    public List<Department> getAllDepartments() {
        return repository.getAllDepartments();
    }

    public List<Chair> getAllChairs() {
        return repository.getAllChairs();
    }

    public List<Class> getAllClasses() {
        return repository.getAllClasses();
    }

    public List<Student> getAllStudents() {
        return repository.getAllStudents();
    }

    public List<Course> getAllCourses() {
        return repository.getAllCourses();
    }

    public List<Lecturer> getAllLecturers() {
        return repository.getAllLecturers();
    }

    public List<StudentCourse> getAllStdCrs() {
        return repository.getAllStdCrs();
    }

    public boolean insert(InsertForm form) {
        if (form.getTable().equals("department")) {
            if (!form.getName().isEmpty()) {
                repository.insertDepartment(form);
                return true;
            }
        }
        if (form.getTable().equals("chair")){
            if (!form.getName().isEmpty()) {
                repository.insertChair(form);
                return true;
            }
        }
        if (form.getTable().equals("lecturer")) {
            if (!form.getFirstname().isEmpty() &&
                    !form.getLastname().isEmpty()) {
                repository.insertLecturer(form);
                return true;
            }
        }
        if (form.getTable().equals("class")) {
            if (!form.getName().isEmpty()) {
                repository.insertClass(form);
                return true;
            }
        }
        if (form.getTable().equals("student")) {
            if (!form.getFirstname().isEmpty() &&
                    !form.getLastname().isEmpty()) {
                repository.insertStudent(form);
                return true;
            }
        }
        if (form.getTable().equals("course")) {
            if (!form.getName().isEmpty()) {
                repository.insertCourse(form);
                return true;
            }
        }

        if (form.getTable().equals("student_course")) {
            repository.insertStdCrs(form);
            return true;
        }

        return false;
    }

    public boolean update(UpdateForm form) {
        if (form.getTable().equals("department")) {
            if (!form.getName().isEmpty()) {
                repository.updateDepartment(form);
                return true;
            }
        }
        if (form.getTable().equals("chair")){
            if (!form.getName().isEmpty()) {
                repository.updateChair(form);
                return true;
            }
        }
        if (form.getTable().equals("lecturer")) {
            if (!form.getFirstname().isEmpty() &&
                    !form.getLastname().isEmpty()) {
                repository.updateLecturer(form);
                return true;
            }
        }
        if (form.getTable().equals("class")) {
            if (!form.getName().isEmpty()) {
                repository.updateClass(form);
                return true;
            }
        }
        if (form.getTable().equals("student")) {
            if (!form.getFirstname().isEmpty() &&
                    !form.getLastname().isEmpty()) {
                repository.updateStudent(form);
                return true;
            }
        }
        if (form.getTable().equals("course")) {
            if (!form.getName().isEmpty()) {
                repository.updateCourse(form);
                return true;
            }
        }

        if (form.getTable().equals("student_course")) {
            if (!form.getLastname().isEmpty() && !form.getName().isEmpty()) {
                repository.updateStdCrs(form);
                return true;
            }
        }

        return false;
    }

    public boolean delete(DeleteForm form) {
        if (form.getTable().equals("department")) {
            repository.deleteDepartment(form);
            return true;
        }
        if (form.getTable().equals("chair")){
            repository.deleteChair(form);
            return true;
        }
        if (form.getTable().equals("lecturer")) {
            repository.deleteLecturer(form);
            return true;
        }
        if (form.getTable().equals("class")) {
            repository.deleteClass(form);
            return true;
        }
        if (form.getTable().equals("student")) {
            repository.deleteStudent(form);
            return true;
        }
        if (form.getTable().equals("course")) {
            repository.deleteCourse(form);
            return true;
        }

        if (form.getTable().equals("student_course")) {
            repository.deleteStdCrs(form);
            return true;
        }

        return false;
    }
}
