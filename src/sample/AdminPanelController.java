package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPanelController {

    public void toAdminLogin(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
        Scene toAdminLogin = new Scene(root2);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(toAdminLogin);
        window.show();
    }

    public void toMain(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene toMain = new Scene(root2);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(toMain);
        window.show();
    }

    public void toManageAirports(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("AdminAirportManager.fxml"));
        Scene toManageAirports = new Scene(root2);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(toManageAirports);
        window.show();
    }

    public void toManageAircrafts(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("AdminAircraftManager.fxml"));
        Scene toManageAircrafts = new Scene(root2);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(toManageAircrafts);
        window.show();
    }

    public void toUsersView(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("AdminViewUsers.fxml"));
        Scene toUsersView = new Scene(root2);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(toUsersView);
        window.show();
    }

}
