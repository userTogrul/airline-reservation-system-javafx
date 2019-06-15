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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;

public class UserRegisterController implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private PasswordField empPassword;

    @FXML
    private PasswordField conpassword;

    @FXML
    private TextField SecretAnswer;

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

    public void registering(ActionEvent actionEvent) {
        if(!username.getText().isEmpty()&&
                !SecretAnswer.getText().isEmpty()&&
                !comboBox.getValue().isEmpty()
                &&!empPassword.getText().isEmpty()
                &&empPassword.getText().equals(conpassword.getText())) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "root", "root");

                Statement stmt = con.createStatement();
                stmt.executeUpdate("INSERT INTO users VALUES(user_id_inc.nextval, '" + String.valueOf(username.getText()) + "', '"+ String.valueOf(empPassword.getText()) +"','"+ SecretAnswer.getText() +"','"+ comboBox.getValue() +"')");

                con.close();

                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Registering state");
                a.setTitle("Completed");
                a.setContentText("Registered. Please log in.");
                a.show();

                toLoginLink(actionEvent);

            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Registering state");
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
