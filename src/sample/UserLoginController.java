package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;

import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.sql.*;

public class UserLoginController {

    public int user_id;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    public void login(ActionEvent actionEvent){

        if(!username.getText().isEmpty()&&!password.getText().isEmpty()) {
            try {

                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "root", "root");

                PreparedStatement stmt = con.prepareStatement("SELECT * FROM users WHERE u_username = ? AND u_password = ?");

                stmt.setString(1, username.getText());
                stmt.setString(2, password.getText());

                ResultSet rs = stmt.executeQuery();



                if (rs.next()) {

                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Profile");
                    a.setHeaderText("Logged in");
                    a.setContentText("Logged in successfully.");
                    a.show();

                    Statement st = con.createStatement();
                    ResultSet rs1 = st.executeQuery("SELECT user_id FROM users WHERE u_username = '" + username.getText() + "'");

                    if(rs1.next()) {
                        User.setId(rs1.getInt("user_id"));

                        Alert c = new Alert(Alert.AlertType.INFORMATION);
                        c.setTitle("Profile");
                        c.setHeaderText("Logged in"+ User.getId());
                        c.setContentText("Logged in successfully.");
                        c.show();
                    } else {
                        Alert b = new Alert(Alert.AlertType.ERROR);
                        b.setTitle("Profile");
                        b.setHeaderText("Woops");
                        b.setContentText("Woops");
                        b.show();
                    }

                    con.close();
                    toUserPanel(actionEvent);


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


    public void toUserRegister(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("UserRegister.fxml"));
        Scene loginScene = new Scene(root2);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

    public void toUserPanel(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("UserPanel.fxml"));
        Scene loginScene = new Scene(root2);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

    public void toAdminLogin(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene toAdminLogin = new Scene(root2);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(toAdminLogin);
        window.show();
    }

    public void toRecover(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene toRecover = new Scene(root2);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(toRecover);
        window.show();
    }



}