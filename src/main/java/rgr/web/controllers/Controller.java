package rgr.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import rgr.core.domain.InsertForm;
import rgr.core.domain.UpdateForm;
import rgr.core.domain.UserImpl;
import rgr.web.domain.form.Form;
import rgr.web.domain.model.*;
import rgr.web.domain.model.Class;
import rgr.web.service.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by vik on 09.09.15.
 */

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private Service service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getWelcome() {
        return new ModelAndView("welcome");
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String redirectFromsignin() {
        return "redirect:/";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String redirectFromsignup() {
        return "redirect:/";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ModelAndView signin(@ModelAttribute("form") Form form) {
        UserImpl user = new UserImpl(form.getUsername().toLowerCase(), form.getPassword().toLowerCase());

        if (user.getUsername().equals("") || user.getPassword().equals("")) {
            ModelAndView model = new ModelAndView("welcome", "signinError", new Object());
            model.addObject("form", form);
            return model;
        }

        if (!service.checkUser(user)) {
            ModelAndView model = new ModelAndView("welcome", "signinError", new Object());
            model.addObject("form", form);
            return model;
        }

        return new ModelAndView("redirect:/main");
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView signup(@ModelAttribute("form") Form form) {
        UserImpl user = new UserImpl(form.getUsername().toLowerCase(), form.getPassword().toLowerCase());

        if (user.getUsername().equals("") || user.getPassword().equals("")) {
            ModelAndView model = new ModelAndView("welcome", "signupError", new Object());
            model.addObject("form", form);
            return model;
        }

        if (!service.createUser(user)) {
            ModelAndView model = new ModelAndView("welcome", "signupError", new Object());
            model.addObject("form", form);
            return model;
        }

        return new ModelAndView("welcome");
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView getMain() {

        ModelAndView model = new ModelAndView("main");
        List<Department> departments = service.getAllDepartments();
        Map<String, List<String>> map = service.getStringsForDropdown();
        model.addObject("departments", departments);
        model.addObject("department", map.get("department"));
        model.addObject("chair", map.get("chair"));
        model.addObject("class", map.get("class"));
        model.addObject("lecturer", map.get("lecturer"));
        model.addObject("student", map.get("student"));
        model.addObject("course", map.get("course"));

        return model;
    }

    @RequestMapping(value = "/department", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Department> getDepartment() {
        return service.getAllDepartments();
    }

    @RequestMapping(value = "/chair", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Chair> getChair() {
        return service.getAllChairs();
    }

    @RequestMapping(value = "/class", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Class> getClas() {
        return service.getAllClasses();
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Student> getStudent() {
        return service.getAllStudents();
    }

    @RequestMapping(value = "/course", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Course> getCourse() {
        return service.getAllCourses();
    }

    @RequestMapping(value = "/lecturer", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Lecturer> getLecturer() {
        return service.getAllLecturers();
    }

    @RequestMapping(value = "/student_course", method = RequestMethod.POST)
    public
    @ResponseBody
    List<StudentCourse> getStdCrs() {
        return service.getAllStdCrs();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity insert(HttpServletRequest request) {
        InsertForm form = new InsertForm();
        try {
            form.setTable(request.getParameter("table"));
            form.setName(request.getParameter("name"));
            form.setFirstname(request.getParameter("firstname"));
            form.setLastname(request.getParameter("lastname"));
            form.setDepartment(request.getParameter("department"));
            form.setChair(request.getParameter("chair"));
            form.setLecturer(request.getParameter("lecturer"));
            form.setClas(request.getParameter("clas"));
            form.setIsHead(Boolean.parseBoolean(request.getParameter("isHead")));
            form.setStudent(request.getParameter("student"));
            form.setCourse(request.getParameter("course"));

            service.insert(form);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

/*    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity delete(HttpServletRequest request) {
        String table = request.getParameter("table");
        InsertForm form = new InsertForm();
        try {
            if (table.equals("department")) {
                form.setName(request.getParameter("name").toLowerCase());
                service.deleteDepartment(form);
            } else if (table.equals("chair")) {
                form.setName(request.getParameter("name").toLowerCase());
                form.setDepartmentId(Long.parseLong(request.getParameter("departmentId")));
                service.deleteChair(form);
            } else if (table.equals("lecturer")) {
                form.setFirstname(request.getParameter("firstname").toLowerCase());
                form.setLastname(request.getParameter("lastname").toLowerCase());
                service.deleteLecturer(form);
            } else if (table.equals("class")) {
                form.setName(request.getParameter("name").toLowerCase());
                form.setDepartmentId(Long.parseLong(request.getParameter("departmentId")));
                service.deleteClass(form);
            } else if (table.equals("student")) {
                form.setFirstname(request.getParameter("firstname").toLowerCase());
                form.setLastname(request.getParameter("lastname").toLowerCase());
                try {
                    form.setClassId(Long.parseLong(request.getParameter("classId")));
                } catch (NumberFormatException e) {
                    form.setClassId(null);
                }

                service.deleteStudent(form);
            } else if (table.equals("course")) {
                form.setName(request.getParameter("name").toLowerCase());
                form.setLecturerId(Long.parseLong(request.getParameter("lecturerId")));
                service.deleteCourse(form);
            } else if (table.equals("student_course")) {
                form.setName(request.getParameter("name").toLowerCase());
                form.setLastname(request.getParameter("lastname").toLowerCase());
                service.deleteStdCrs(form);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
*/
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity update(HttpServletRequest request) {
        UpdateForm form = new UpdateForm();
        String table = request.getParameter("table");

        try {
            if (table.equals("department")) {
                form.setName(request.getParameter("name").toLowerCase());
                form.setOldName(request.getParameter("oldName"));
                service.updateDepartment(form);
            } else if (table.equals("chair")) {
                form.setName(request.getParameter("name").toLowerCase());
                form.setDepartmentId(Long.parseLong(request.getParameter("departmentId")));
                form.setOldName(request.getParameter("oldName"));
                form.setOldDepartmentId(Long.parseLong(request.getParameter("oldDepartmentId")));
                service.updateChair(form);
            } else if (table.equals("lecturer")) {
                form.setFirstname(request.getParameter("firstname").toLowerCase());
                form.setLastname(request.getParameter("lastname").toLowerCase());
                form.setChairId(Long.parseLong(request.getParameter("chairId")));
                form.setOldFirstname(request.getParameter("oldFirstname"));
                form.setOldLastname(request.getParameter("oldLastname"));
                form.setOldChairId(Long.parseLong(request.getParameter("oldChairId")));
                service.updateLecturer(form);
            } else if (table.equals("class")) {
                form.setName(request.getParameter("name").toLowerCase());
                form.setDepartmentId(Long.parseLong(request.getParameter("departmentId")));
                form.setOldName(request.getParameter("oldName"));
                form.setOldDepartmentId(Long.parseLong(request.getParameter("oldDepartmentId")));
                service.updateClass(form);
            } else if (table.equals("student")) {
                form.setFirstname(request.getParameter("firstname").toLowerCase());
                form.setLastname(request.getParameter("lastname").toLowerCase());
                form.setIsHead(Boolean.parseBoolean(request.getParameter("isHead")));
                form.setOldFirstname(request.getParameter("oldFirstname"));
                form.setOldLastname(request.getParameter("oldLastname"));
                form.setOldIsHead(Boolean.parseBoolean(request.getParameter("oldIsHead")));
                try {
                    form.setClassId(Long.parseLong(request.getParameter("classId")));
                } catch (NumberFormatException e) {
                    form.setClassId(null);
                }
                try {
                    form.setChairId(Long.parseLong(request.getParameter("chairId")));
                } catch (NumberFormatException e) {
                    form.setChairId(null);
                }
                try {
                    form.setOldClassId(Long.parseLong(request.getParameter("oldClassId")));
                } catch (NumberFormatException e) {
                    form.setOldClassId(null);
                }
                try {
                    form.setOldChairId(Long.parseLong(request.getParameter("oldChairId")));
                } catch (NumberFormatException e) {
                    form.setOldChairId(null);
                }

                service.updateStudent(form);
            } else if (table.equals("course")) {
                form.setName(request.getParameter("name").toLowerCase());
                form.setLecturerId(Long.parseLong(request.getParameter("lecturerId")));
                form.setOldName(request.getParameter("oldName"));
                form.setOldLecturerId(Long.parseLong(request.getParameter("oldLecturerId")));
                service.updateCourse(form);
            } else if (table.equals("student_course")) {
                form.setName(request.getParameter("name").toLowerCase());
                form.setLastname(request.getParameter("lastname").toLowerCase());
                form.setOldName(request.getParameter("oldName"));
                form.setOldLastname(request.getParameter("oldLastname"));
                service.updateStdCrs(form);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}