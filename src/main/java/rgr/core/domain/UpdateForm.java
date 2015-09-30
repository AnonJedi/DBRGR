package rgr.core.domain;

/**
 * Created by vik on 23.09.15.
 */
public class UpdateForm {
    private Long id;
    private String table;
    private String name;
    private String firstname;
    private String lastname;
    private boolean isHead;
    private String department;
    private String chair;
    private String clas;

    private String oldName;
    private String oldFirstname;
    private String oldLastname;
    private boolean oldIsHead;
    private String oldDepartment;
    private String oldChair;
    private String oldClas;

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

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getOldFirstname() {
        return oldFirstname;
    }

    public void setOldFirstname(String oldFirstname) {
        this.oldFirstname = oldFirstname;
    }

    public String getOldLastname() {
        return oldLastname;
    }

    public void setOldLastname(String oldLastname) {
        this.oldLastname = oldLastname;
    }

    public boolean isOldIsHead() {
        return oldIsHead;
    }

    public void setOldIsHead(boolean oldIsHead) {
        this.oldIsHead = oldIsHead;
    }

    public String getOldDepartment() {
        return oldDepartment;
    }

    public void setOldDepartment(String oldDepartment) {
        this.oldDepartment = oldDepartment;
    }

    public String getOldChair() {
        return oldChair;
    }

    public void setOldChair(String oldChair) {
        this.oldChair = oldChair;
    }

    public String getOldClas() {
        return oldClas;
    }

    public void setOldClas(String oldClass) {
        this.oldClas = oldClass;
    }
}
