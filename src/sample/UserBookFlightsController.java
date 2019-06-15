package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import  javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserBookFlightsController {

    @FXML
    private TextField flFrom;

    @FXML
    private TextField flTo;

    @FXML
    private TextField flDept;

    @FXML
    private TextField flRet;

    @FXML
    private Label flEcoPrice;


    public void searchFlight(ActionEvent actionEvent){

        try{
            if(
                    !flFrom.getText().isEmpty()&&
                            !flTo.getText().isEmpty()&&
                            !flDept.getText().isEmpty()&&
                            !flRet.getText().isEmpty()
                    ) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "root", "root");

                Statement st = con.createStatement();

                ResultSet rs;

                rs = st.executeQuery("select * from flights where (begining = '"+ flFrom.getText() +"' and destination = '" + flTo.getText() + "')and(dept_time = '" + flDept.getText() + "' and ret_time = '"+ flRet.getText() +"')");
                if(rs.next()){
                    User.setFlId(rs.getInt("fl_id"));
                    rs = st.executeQuery("SELECT lf_price_eco FROM flights where fl_id = " + User.getFlId());
                    if(rs.next()) {
                        flEcoPrice.setText(String.valueOf(rs.getInt("lf_price_eco")));
                    }
                } else {

                    flEcoPrice.setText("Not Found");

                }

                con.close();


            } else {

                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Incorrect information");
                a.setHeaderText("Booking information false");
                a.setContentText("Please fill all fields.");

                a.showAndWait();
            }

        }catch(Exception e){
            System.out.println(e);
        }

    }

    public void toTicketDetails(ActionEvent actionEvent) throws IOException {
        if(
                !flFrom.getText().isEmpty()&&
                        !flTo.getText().isEmpty()&&
                        !flDept.getText().isEmpty()&&
                        !flRet.getText().isEmpty()
                ) {
            Parent root2 = FXMLLoader.load(getClass().getResource("UserTicketDetails.fxml"));
            Scene loginScene = new Scene(root2);

            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.show();
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Incorrect information");
            a.setHeaderText("Booking information false");
            a.setContentText("Please fill all fields.");

            a.showAndWait();
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
