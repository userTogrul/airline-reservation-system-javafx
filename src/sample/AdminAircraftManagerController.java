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

public class AdminAircraftManagerController implements Initializable {


    @FXML
    private TableView<aircraftClass> aircraftTableView;

    @FXML
    private TableColumn aircraftId;

    @FXML
    private TableColumn aircraftName;

    @FXML
    private TableColumn aircraftSeats;

    @FXML
    private TableColumn manufacturerCompany;

    @FXML
    private TableColumn manufacturingDate;

    @FXML
    private TextField aircraftIdBox;

    @FXML
    private TextField aircraftNameBox;

    @FXML
    private TextField seatsBox;

    @FXML
    private TextField manufacutererCompBox;

    @FXML
    private TextField manufacturingDateBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getAircraftTableView();
    }


    public void getAircraftTableView(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "root", "root");

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from aircrafts");

            aircraftTableView.getItems().clear();
            aircraftId.setCellValueFactory(new PropertyValueFactory<>("aircraftId"));
            aircraftName.setCellValueFactory(new PropertyValueFactory<>("aircraftName"));
            aircraftSeats.setCellValueFactory(new PropertyValueFactory<>("seats"));
            manufacturerCompany.setCellValueFactory(new PropertyValueFactory<>("ManuCompany"));
            manufacturingDate.setCellValueFactory(new PropertyValueFactory<>("manuDate"));

            while (rs.next()) {
                aircraftTableView.getItems().add(new aircraftClass(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5)
                ));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onEdit() {
        // check the table's selected item and get selected item
        if (aircraftTableView.getSelectionModel().getSelectedItem() != null) {
            aircraftClass fl = aircraftTableView.getSelectionModel().getSelectedItem();
            aircraftId.setText(String.valueOf(fl.getAircraftId()));
        }
    }

    public void inserting(ActionEvent actionEvent) {

        if (!aircraftIdBox.getText().isEmpty() &&
                !aircraftNameBox.getText().isEmpty() &&
                !seatsBox.getText().isEmpty() &&
                !manufacutererCompBox.getText().isEmpty() &&
                !manufacturingDateBox.getText().isEmpty()
                ) {

            Connection con = JDBCConnection.ConnectDB();

            try {


                Statement st = con.createStatement();

                st.executeUpdate("INSERT INTO aircrafts VALUES(" +
                        Integer.parseInt(aircraftIdBox.getText()) +
                        ",'" + aircraftNameBox.getText() +
                        "'," + Integer.parseInt(seatsBox.getText()) +
                        ",'" + manufacutererCompBox.getText() +
                        "','" + manufacturingDateBox.getText() +
                        "')");

                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            getAircraftTableView();

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
            if (!aircraftNameBox.getText().isEmpty()) {
                st.executeUpdate("UPDATE flights SET plane_name = '" + aircraftNameBox.getText() + "' WHERE aircr_id = " + Integer.parseInt(aircraftIdBox.getText()));
                k = 1;
            }
            if (!seatsBox.getText().isEmpty()) {
                st.executeUpdate("UPDATE flights SET seats = " + Integer.parseInt(seatsBox.getText()) + " WHERE aircr_id = " + Integer.parseInt(aircraftIdBox.getText()));
                k = 1;
            }
            if (!manufacutererCompBox.getText().isEmpty()) {
                st.executeUpdate("UPDATE flights SET mf_company = '" + manufacutererCompBox.getText() + "' WHERE aircr_id = " + Integer.parseInt(aircraftIdBox.getText()));
                k = 1;
            }
            if (!manufacturingDateBox.getText().isEmpty()) {
                st.executeUpdate("UPDATE flights SET mf_dt = '" + manufacturingDateBox.getText() + "' WHERE aircr_id = " + Integer.parseInt(aircraftIdBox.getText()));
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
                getAircraftTableView();
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
            st.executeUpdate("DELETE FROM aircrafts WHERE aircr_id = " + Integer.parseInt(aircraftIdBox.getText()));

        } catch (Exception e){e.printStackTrace();}
        getAircraftTableView();
    }

    public void toAdminPanel(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
        Scene toAdminPanel = new Scene(root2);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(toAdminPanel);
        window.show();
    }


}
