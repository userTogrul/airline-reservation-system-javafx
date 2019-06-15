package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.io.*;
import java.util.*;
//registers admins
public class Controller implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private TextField SecretAnswer;

    @FXML
    private PasswordField empPassword;

    @FXML
    private PasswordField conpassword;
    @FXML
    public ComboBox<String> comboBox;

    @FXML
    private Button Register;

    @FXML
    private Hyperlink toLogin;

    ObservableList<String> list = FXCollections.observableArrayList("What is your Favourite movie?","What is your Favourite book?", "What high school did you attend?","What is the name of your first school?","What street did you grow up on?","What was the make of your first car?");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        comboBox.setItems(list);
    }


    public void recovering(ActionEvent actionEvent) {
        if(!username.getText().isEmpty()
        &&!SecretAnswer.getText().isEmpty()
                &&!comboBox.getValue().isEmpty()
                &&!empPassword.getText().isEmpty()
                &&empPassword.getText().equals(conpassword.getText())) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "root", "root");

                PreparedStatement stmt = con.prepareStatement("SELECT * FROM users WHERE u_username = ? AND question = ? AND keywords = ?");

                stmt.setString(1, username.getText());
                stmt.setString(2, comboBox.getValue());
                stmt.setString(3, SecretAnswer.getText());

                ResultSet rs = stmt.executeQuery();

                if(rs.next()){
                    Statement st  = con.createStatement();
                    int updated = st.executeUpdate("UPDATE users SET u_password = '"+empPassword.getText()+"' WHERE u_username = '"+username.getText()+"'");

                    if(updated != 0){
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setHeaderText("State");
                        a.setTitle("Completed");
                        a.setContentText("Updated password.");
                        a.show();
                    } else {
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setHeaderText("State");
                        a.setTitle("Not found");
                        a.setContentText("Updated password.");
                        a.show();
                    }
                } else {

                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setHeaderText("State");
                    a.setTitle("Not Found");
                    a.setContentText("Please try again.");
                    a.show();

                }



                toLoginLink(actionEvent);

            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Updating state");
            a.setTitle("Incorrect information");
            a.setContentText("Please fill all fields.");

            a.showAndWait();
        }
    }

    public void toLoginLink(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
        Scene loginScene = new Scene(root2);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }




}
