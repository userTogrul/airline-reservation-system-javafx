package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class UserCancelReservationController {

    @FXML
    private TextField psName;

    @FXML
    private TextField psSurname;


    @FXML
    private TextField psId;


    public void cancelReservation(ActionEvent actionEvent) {

        try {
            if (!psName.getText().isEmpty() && !psSurname.getText().isEmpty() &&  !psId.getText().isEmpty()) {

                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "root", "root");




                    Statement stmt = con.createStatement();
                    // +
                //                            psName.getText()
                //                            + "' AND ps_surname='"
                //                            + psSurname.getText() +
                //                            "') AND
                    //System.out.println(stmt.executeQuery("SELECT name FROM registertable WHERE name = 'A')")); "DELETE FROM passengers WHERE (ps_name =  and ps_surname= ?) and ps_id = ?"
                    int deleted = stmt.executeUpdate("DELETE FROM passengers WHERE ps_id = " + Integer.parseInt(psId.getText()));

               if(deleted != 0){
                   Alert a = new Alert(Alert.AlertType.INFORMATION);
                   a.setTitle("Cancellation");
                   a.setHeaderText("Cancelled");
                   a.setContentText("Ticket cancelled successfully.");
                   a.showAndWait();
                   con.close();
                   toMainMenu(actionEvent);

               } else {
                   Alert a = new Alert(Alert.AlertType.ERROR);
                   a.setTitle("Cancellation");
                   a.setHeaderText("Not cancelled");
                   a.setContentText("Can not delete.");
                   a.showAndWait();
               }

            /*} else {
                st.executeUpdate("delete from passengers where ps_id = " + Integer.valueOf(ps_id.getText()));


            }*/

            con.close();
            } else {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Incorrect information");
                a.setHeaderText("Cancellation information");
                a.setContentText("Please fill all fields.");
                a.showAndWait();
            }
        } catch(Exception e) {
            System.out.println(e);
        }


    }

    public void toMainMenu(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("UserPanel.fxml"));
        Scene loginScene = new Scene(root2);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

}
