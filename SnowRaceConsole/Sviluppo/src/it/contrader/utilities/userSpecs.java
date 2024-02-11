package it.contrader.utilities;

import java.util.HashMap;
import java.util.Map;

public class userSpecs {

    private String usertype, username;
    private int id;
    private static userSpecs instance;

    private userSpecs() {
        usertype = new String();
    }

    public static userSpecs getInstance() {
        if (instance == null) {
            instance = new userSpecs();
        }
        return instance;
    }

    public void esci(){
        instance = null;
    }


    public String getUsertype() {
        return usertype;
    }
    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
