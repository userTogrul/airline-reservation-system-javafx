package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private User user;

    @FXML
    private Label showusername;

    @FXML
    private TableView<flightClass> flightTableView;

    @FXML
    private TableColumn flightId;

    @FXML
    private TableColumn fromId;

    @FXML
    private TableColumn toId;

    @FXML
    private TableColumn departureId;

    @FXML
    private TableColumn returnId;

    @FXML
    private TableColumn ecoPriceId;

    @FXML
    private TableColumn businessPriceId;

    @FXML
    private TableColumn aircraftId;

    @FXML
    private TextField flightIdBox;

    @FXML
    private TextField fromIdBox;

    @FXML
    private TextField toIdBox;

    @FXML
    private TextField depatureBox;

    @FXML
    private TextField returnBox;

    @FXML
    private TextField ecoPriceBox;

    @FXML
    private TextField BusinessPriceBox;

    @FXML
    private TextField aircraftIdBox;


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        try {

            //showusername.setText(String.valueOf(user.getId()));
            //Statement st = con.createStatement();
            //ResultSet rs = st.executeQuery("SELECT u_username FROM users WHERE user_id = " + 1001);


//            if (rs.next()) {
//
//                //showusername.setText(rs.getString("u_username"));
//
//            } else {
//                Alert b = new Alert(Alert.AlertType.ERROR);
//                b.setTitle("Profile");
//                b.setHeaderText("Woops");
//                b.setContentText("Woops");
//                b.show();
//            }

            //con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        getFlightTable();


    }

    public void getFlightTable() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "root", "root");

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from flights");

            flightTableView.getItems().clear();
            flightId.setCellValueFactory(new PropertyValueFactory<>("flightId"));
            fromId.setCellValueFactory(new PropertyValueFactory<>("From"));
            toId.setCellValueFactory(new PropertyValueFactory<>("To"));
            departureId.setCellValueFactory(new PropertyValueFactory<>("Departure"));
            returnId.setCellValueFactory(new PropertyValueFactory<>("Return"));
            ecoPriceId.setCellValueFactory(new PropertyValueFactory<>("EcoPriceId"));
            businessPriceId.setCellValueFactory(new PropertyValueFactory<>("BusinessPriceId"));
            aircraftId.setCellValueFactory(new PropertyValueFactory<>("AircraftId"));

            while (rs.next()) {
                flightTableView.getItems().add(new flightClass(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8)
                ));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onEdit() {
        // check the table's selected item and get selected item
        if (flightTableView.getSelectionModel().getSelectedItem() != null) {
            flightClass fl = flightTableView.getSelectionModel().getSelectedItem();
            flightIdBox.setText(String.valueOf(fl.getFlightId()));
        }
    }

    public void inserting(ActionEvent actionEvent) {

        if (!flightIdBox.getText().isEmpty() &&
                !fromIdBox.getText().isEmpty() &&
                !toIdBox.getText().isEmpty() &&
                !depatureBox.getText().isEmpty() &&
                !returnBox.getText().isEmpty() &&
                !ecoPriceBox.getText().isEmpty() &&
                !BusinessPriceBox.getText().isEmpty() &&
                !aircraftIdBox.getText().isEmpty()
                ) {

            Connection con = JDBCConnection.ConnectDB();

            try {


                Statement st = con.createStatement();
                st.executeUpdate("INSERT INTO flights VALUES(" +
                        Integer.parseInt(flightIdBox.getText()) +
                        ",'" + fromIdBox.getText() +
                        "','" + toIdBox.getText() +
                        "','" + depatureBox.getText() +
                        "','" + returnBox.getText() +
                        "'," + Integer.parseInt(ecoPriceBox.getText()) +
                        "," + Integer.parseInt(BusinessPriceBox.getText()) +
                        "," + Integer.parseInt(aircraftIdBox.getText()) +
                        ")");

                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            getFlightTable();

        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Incorrect information");
            a.setHeaderText("Empty blocks");
            a.setContentText("Please fill all fields.");

            a.showAndWait();
        }

    }


    public void updating() {

        Connection con = JDBCConnection.ConnectDB();
        int k = 0;

        try {
            Statement st = con.createStatement();
            if (!fromIdBox.getText().isEmpty()) {
                st.executeUpdate("UPDATE flights SET begining = '" + fromIdBox.getText() + "' WHERE fl_id = " + Integer.parseInt(flightIdBox.getText()));
                k = 1;
            }
            if (!toIdBox.getText().isEmpty()) {
                st.executeUpdate("UPDATE flights SET destination = '" + toIdBox.getText() + "' WHERE fl_id = " + Integer.parseInt(flightIdBox.getText()));
                k = 1;
            }
            if (!depatureBox.getText().isEmpty()) {
                st.executeUpdate("UPDATE flights SET dept_time = '" + depatureBox.getText() + "' WHERE fl_id = " + Integer.parseInt(flightIdBox.getText()));
                k = 1;
            }
            if (!returnBox.getText().isEmpty()) {
                st.executeUpdate("UPDATE flights SET ret_time = '" + returnBox.getText() + "' WHERE fl_id = " + Integer.parseInt(flightIdBox.getText()));
                k = 1;
            }
            if (!ecoPriceBox.getText().isEmpty()) {
                st.executeUpdate("UPDATE flights SET lf_price_eco = " + Integer.parseInt(ecoPriceBox.getText()) + " WHERE fl_id = " + Integer.parseInt(flightIdBox.getText()));
                k = 1;
            }
            if (!BusinessPriceBox.getText().isEmpty()) {
                st.executeUpdate("UPDATE flights SET lf_price_bsn = " + Integer.parseInt(BusinessPriceBox.getText()) + " WHERE fl_id = " + Integer.parseInt(flightIdBox.getText()));
                k = 1;
            }
            if (!aircraftIdBox.getText().isEmpty()) {
                st.executeUpdate("UPDATE flights SET aircr_id = " + Integer.parseInt(aircraftIdBox.getText()) + " WHERE fl_id = " + Integer.parseInt(flightIdBox.getText()));
                k = 1;
            }

            con.close();

            if (k == 0) {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Incorrect information");
                a.setHeaderText("Empty blocks");
                a.setContentText("Please fill all fields.");

                a.showAndWait();
            } else {
            getFlightTable();
        }

        } catch (Exception e)

        {
            e.printStackTrace();
        }

    }


    public void deleteFlight() {
            Connection con = JDBCConnection.ConnectDB();

            try {

                Statement st = con.createStatement();
                st.executeUpdate("DELETE FROM flights WHERE fl_id = " + Integer.parseInt(flightIdBox.getText()));

            } catch (Exception e){e.printStackTrace();}
        getFlightTable();
    }

    public void toAdminPanel(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
        Scene toAdminPanel = new Scene(root2);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(toAdminPanel);
        window.show();
    }
}
