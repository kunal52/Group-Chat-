package a52.puri.fbkunal.com.firebase;

/**
 * Created by kunal on 09-02-2017.
 */

public class Chat {
    String message;
    String name;
    //String time;

    public Chat(String message,String user)
    {
        this.message=message;
        this.name=user;
    }

    Chat()
    {
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
   /* public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }*/

}
