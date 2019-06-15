package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class UserViewTicketsController implements Initializable {

    @FXML
    private TableView<ticketsClass> ticketTableView;

    @FXML
    private TableColumn ticketId;

    @FXML
    private TableColumn passengerId;

    @FXML
    private TableColumn ticketPrice;

    @FXML
    private TableColumn fromId;

    @FXML
    private TableColumn toId;

    @FXML
    private TableColumn departureId;

    @FXML
    private TableColumn returnId;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getTicketTable();
    }

    public void getTicketTable() {

        Connection con = JDBCConnection.ConnectDB();

        try {

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select tk_id,ps_id,tk_price,begining,destination,dept_time,ret_time from tickets t inner join flights f on t.fl_id = f.fl_id where t.ps_id in (select ps_id from passengers where user_id =  " + User.getId() + ")");

            ticketTableView.getItems().clear();
            ticketId.setCellValueFactory(new PropertyValueFactory<>("ticketId"));
            passengerId.setCellValueFactory(new PropertyValueFactory<>("passengerId"));
            ticketPrice.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));
            fromId.setCellValueFactory(new PropertyValueFactory<>("From"));
            toId.setCellValueFactory(new PropertyValueFactory<>("To"));
            departureId.setCellValueFactory(new PropertyValueFactory<>("Departure"));
            returnId.setCellValueFactory(new PropertyValueFactory<>("Return"));

            while (rs.next()) {
                ticketTableView.getItems().add(new ticketsClass(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
                ));

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    public void toMainMenu(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("UserPanel.fxml"));
        Scene toMainMenu = new Scene(root2);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(toMainMenu);
        window.show();

    }
}
