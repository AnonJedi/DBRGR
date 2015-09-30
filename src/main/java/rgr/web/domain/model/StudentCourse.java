package rgr.web.domain.model;

/**
 * Created by vik on 23.09.15.
 */
public class StudentCourse {
    private String lastname;
    private String name;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname.toLowerCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }
}
