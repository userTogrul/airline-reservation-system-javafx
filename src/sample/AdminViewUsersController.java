package sample;

import javafx.event.ActionEvent;
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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AdminViewUsersController implements Initializable {


    @FXML
    private TableView<adminUserClass> usersTableView;

    @FXML
    private TableColumn userId;

    @FXML
    private TableColumn userName;

    @FXML
    private TableColumn password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getUserTableView();

    }

    public void getUserTableView(){

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "root", "root");

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select user_id, u_username,u_password from users");

            usersTableView.getItems().clear();
            userId.setCellValueFactory(new PropertyValueFactory<>("user_id"));
            userName.setCellValueFactory(new PropertyValueFactory<>("u_username"));
            password.setCellValueFactory(new PropertyValueFactory<>("u_password"));


            while (rs.next()) {
                usersTableView.getItems().add(new adminUserClass(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                ));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void onEdit() {
        // check the table's selected item and get selected item
        if (usersTableView.getSelectionModel().getSelectedItem() != null) {
            adminUserClass fl = usersTableView.getSelectionModel().getSelectedItem();
            User.setId(Integer.valueOf(fl.getUser_id()));
        }
    }

    public void deleting() {
        Connection con = JDBCConnection.ConnectDB();

        try {

            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM users WHERE user_id = " + User.getId());

        } catch (Exception e){e.printStackTrace();}
        getUserTableView();
    }

    public void toAdminPanel(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
        Scene toAdminPanel = new Scene(root2);
        User.setId(0);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(toAdminPanel);
        window.show();
    }


}
