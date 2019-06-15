package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection {

    public static Connection ConnectDB()
    {
        try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "root", "root");

            return con;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
