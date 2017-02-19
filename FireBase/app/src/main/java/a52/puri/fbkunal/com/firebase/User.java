package a52.puri.fbkunal.com.firebase;

/**
 * Created by kunal on 09-02-2017.
 */

public class User {
    String email;
    String password;
    String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public User(String email,String password,String name)
    {
        this.email=email;
        this.password=password;
        this.name=name;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
