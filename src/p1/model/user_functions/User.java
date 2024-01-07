package p1.model.user_functions;

import java.io.File;
import java.io.Serializable;

public class User implements Comparable<User>, Serializable {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public int compareTo(User o) {
        return username.compareTo(o.username);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
