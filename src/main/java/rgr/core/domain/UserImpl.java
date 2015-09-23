package rgr.core.domain;

/**
 * Created by vik on 17.09.15.
 */
public class UserImpl {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserImpl(){}

    public UserImpl(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
