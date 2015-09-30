package rgr.core.domain;

/**
 * Created by vik on 20.09.15.
 */
public class InsertForm {

    private Long id;
    private String table;
    private String name;
    private String firstname;
    private String lastname;
    private boolean isHead;
    private String department;
    private String chair;
    private String clas;
    private String lecturer;
    private String student;
    private String course;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname.toLowerCase();
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname.toLowerCase();
    }

    public boolean isHead() {
        return isHead;
    }

    public void setIsHead(boolean isHead) {
        this.isHead = isHead;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department.toLowerCase();
    }

    public String getChair() {
        return chair;
    }

    public void setChair(String chair) {
        this.chair = chair.toLowerCase();
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas.toLowerCase();
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer.toLowerCase();
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student.toLowerCase();
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course.toLowerCase();
    }
}
