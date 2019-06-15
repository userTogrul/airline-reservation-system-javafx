package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.sql.*;
import java.io.*;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sun.security.util.Password;

import java.awt.*;
import java.io.IOException;

public class LoginController {


    @FXML
    private TextField username;

    @FXML
    private PasswordField password;


    public void login(ActionEvent actionEvent){

        if(!username.getText().isEmpty()&&!password.getText().isEmpty()) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "root", "root");

                PreparedStatement stmt = con.prepareStatement("SELECT * FROM registertable WHERE username = ? AND password = ?");
                //System.out.println(stmt.executeQuery("SELECT name FROM registertable WHERE name = 'A')"));

                stmt.setString(1, username.getText());
                stmt.setString(2, password.getText());

                ResultSet rs = stmt.executeQuery();



                if (rs.next()) {

                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Profile");
                    a.setHeaderText("Logged in");
                    a.setContentText("Logged in successfully.");
                    a.show();
                    con.close();//delete in problem
                    toMain(actionEvent);


                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Incorrect information");
                    a.setHeaderText("Invalid attempt");
                    a.setContentText("Invalid username or password.");
                    a.showAndWait();
                }

                con.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Incorrect information");
            a.setHeaderText("Logging information");
            a.setContentText("Please fill all fields.");

            a.showAndWait();
        }


    }



    public void toRegisterLink(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene loginScene = new Scene(root2);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }





    public void toMain(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
        Scene loginScene = new Scene(root2);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.centerOnScreen();
        window.show();
    }

    public void toUserLogin(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
        Scene loginScene = new Scene(root2);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

}
