package sample;

public class adminUserClass {

    private int user_id;
    private String u_username;
    private String u_password;

    public adminUserClass(int user_id, String u_username, String u_password) {
        this.user_id = user_id;
        this.u_username = u_username;
        this.u_password = u_password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getU_username() {
        return u_username;
    }

    public void setU_username(String u_username) {
        this.u_username = u_username;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }
}
