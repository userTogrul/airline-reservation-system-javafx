package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AdminAirportsManagerController implements Initializable {

    @FXML
    private TableView<airportClass> airportTableView;

    @FXML
    private TableColumn airportId;

    @FXML
    private TableColumn airportName;

    @FXML
    private TableColumn airportCountry;

    @FXML
    private TableColumn airportCity;

    @FXML
    private TableColumn airportFlightId;

    @FXML
    private TextField flightIdBox;

    @FXML
    private TextField airportIdBox;

    @FXML
    private TextField airportNameBox;

    @FXML
    private TextField AirportCountryBox;

    @FXML
    private TextField AirportCityBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getAirportTable();
    }

    public void getAirportTable() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "root", "root");

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from airports");

            airportTableView.getItems().clear();
            airportId.setCellValueFactory(new PropertyValueFactory<>("airportId"));
            airportName.setCellValueFactory(new PropertyValueFactory<>("airportName"));
            airportCountry.setCellValueFactory(new PropertyValueFactory<>("airportCountry"));
            airportCity.setCellValueFactory(new PropertyValueFactory<>("airportCity"));
            airportFlightId.setCellValueFactory(new PropertyValueFactory<>("flightId"));

            while (rs.next()) {
                airportTableView.getItems().add(new airportClass(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5)
                ));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onEdit() {
        // check the table's selected item and get selected item
        if (airportTableView.getSelectionModel().getSelectedItem() != null) {
            airportClass fl = airportTableView.getSelectionModel().getSelectedItem();
            airportIdBox.setText(String.valueOf(fl.getFlightId()));
        }
    }

    public void inserting(ActionEvent actionEvent) {

        if (!airportIdBox.getText().isEmpty() &&
                !AirportCountryBox.getText().isEmpty() &&
                !airportNameBox.getText().isEmpty() &&
                !AirportCityBox.getText().isEmpty() &&
                !flightIdBox.getText().isEmpty()
                ) {

            Connection con = JDBCConnection.ConnectDB();

            try {


                Statement st = con.createStatement();

                st.executeUpdate("INSERT INTO airports VALUES(" +
                        Integer.parseInt(airportIdBox.getText()) +
                        ",'" + airportNameBox.getText() +
                        "','" + AirportCountryBox.getText() +
                        "','" + AirportCityBox.getText() +
                        "'," + Integer.parseInt(flightIdBox.getText()) +
                        ")");

                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            getAirportTable();

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
            if (!airportNameBox.getText().isEmpty()) {
                st.executeUpdate("UPDATE flights SET airpr_name = '" + airportNameBox.getText() + "' WHERE airpr_id = " + Integer.parseInt(airportId.getText()));
                k = 1;
            }
            if (!AirportCountryBox.getText().isEmpty()) {
                st.executeUpdate("UPDATE flights SET country = '" + AirportCountryBox.getText() + "' WHERE airpr_id = " + Integer.parseInt(airportId.getText()));
                k = 1;
            }
            if (!AirportCityBox.getText().isEmpty()) {
                st.executeUpdate("UPDATE flights SET city = '" + AirportCityBox.getText() + "' WHERE airpr_id = " + Integer.parseInt(airportId.getText()));
                k = 1;
            }
            if (!flightIdBox.getText().isEmpty()) {
                st.executeUpdate("UPDATE flights SET fl_id = " + Integer.parseInt(flightIdBox.getText()) + " WHERE airpr_id = " + Integer.parseInt(airportId.getText()));
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
                getAirportTable();
            }

        } catch (Exception e)

        {
            e.printStackTrace();
        }

    }


    public void deleting() {
        Connection con = JDBCConnection.ConnectDB();

        try {

            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM airports WHERE airpr_id = " + Integer.parseInt(airportIdBox.getText()));

        } catch (Exception e){e.printStackTrace();}
        getAirportTable();
    }

    public void toAdminPanel(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
        Scene toAdminPanel = new Scene(root2);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(toAdminPanel);
        window.show();
    }

}
