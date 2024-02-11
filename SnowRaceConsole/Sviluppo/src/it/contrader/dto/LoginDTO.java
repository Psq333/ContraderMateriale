package it.contrader.dto;

import java.util.HashMap;
import java.util.Map;

public class LoginDTO {
    private String username;
    private String password;

    private String usertype;

    public LoginDTO(String username, String password, String usertype) {
        this.username = username;
        this.password = password;
        this.usertype = usertype;
    }

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
        this.usertype = null;
    }

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

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}
