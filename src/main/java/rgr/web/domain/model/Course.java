package rgr.web.domain.model;

/**
 * Created by vik on 22.09.15.
 */
public class Course {

    private Long id;
    private String name;
    private String lecturerName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName.toLowerCase();
    }
}
