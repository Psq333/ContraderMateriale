package it.contrader.model;
import java.util.Objects;

public class User {

    private int id;
   private String username, password, usertype;

   public User(){}

    public User(int id){
       this.id = id;
    }

    public User(String username, String password, String usertype){
       this.username = username;
       this.password = password;
       this.usertype = usertype;
    }

    public User(int id, String username, String password, String usertype){
        this.id = id;
        this.username = username;
        this.password = password;
        this.usertype = usertype;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Credenziali{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", usertype='" + usertype + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (usertype == null) {
            if (other.usertype != null)
                return false;
        } else if (!usertype.equals(other.usertype))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, usertype);
    }
}
