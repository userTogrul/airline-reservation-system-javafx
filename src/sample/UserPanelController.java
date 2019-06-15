package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class UserPanelController implements Initializable {

    @FXML
    private Label username_label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "root", "root");
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT u_username FROM users WHERE user_id = " + User.getId());

            if(rs.next()){
                username_label.setText("Welcome " + rs.getString("u_username"));
            } else {
                username_label.setText("Woops");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void toBookFlights(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("UserBookFlights.fxml"));
        Scene toBookFlightsScene = new Scene(root2);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(toBookFlightsScene);
        window.show();
    }

    public void toViewTickets(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("UserViewTickets.fxml"));
        Scene viewTicketScene = new Scene(root2);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(viewTicketScene);
        window.show();
    }

    public void toDeleteResevations(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("UserCancelReservation.fxml"));
        Scene deleteScene = new Scene(root2);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(deleteScene);
        window.show();
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
        Scene logOutScene = new Scene(root2);

        User.setOnLogOut(0);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(logOutScene);
        window.show();
    }

}
