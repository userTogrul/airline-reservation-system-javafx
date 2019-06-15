package sample;

public class User {

    public static int user_id;
    public static int fl_id;

    public User(){

    }

    public User(int id){

        user_id = id;

    }

    public static void setId(int id) {
        user_id = id;
    }



    public static int getId()
    {
        return user_id;
    }


    public static void setFlId(int id) {
        fl_id = id;
    }



    public static int getFlId()
    {
        return fl_id;
    }

    public static void setOnLogOut(int val){
        fl_id = val;
        user_id = val;
    }


}
