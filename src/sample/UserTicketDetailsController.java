package sample;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Observable;
import java.util.ResourceBundle;

public class UserTicketDetailsController implements Initializable {

    @FXML
    public TextField ps_name;

    @FXML
    public TextField ps_surname;

    @FXML
    public TextField ps_dob;

    @FXML
    public ComboBox<String> ps_gender;

    @FXML
    public TextField ps_phone;

    @FXML
    public TextField ps_country;

    @FXML
    public TextField ps_city;

    @FXML
    public TextField ps_pcode;

    @FXML
    public TextField ps_cardno;

    @FXML
    public TextField ps_cardco;

    @FXML
    public TextField ps_expire;


    @FXML
    public Label ticketPrice;

    @FXML
    public ComboBox<String> comboBox;

    ObservableList<String> list = FXCollections.observableArrayList("economy","business");
    ObservableList<String> gender = FXCollections.observableArrayList("Male","Female");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            comboBox.setItems(list);
            ps_gender.setItems(gender);

//            while(rs.next){
//            list.add(rs.getInt(1));
//        }


    }

    public void changeComboBox(javafx.event.ActionEvent actionEvent) {
            Connection con = JDBCConnection.ConnectDB();

            try{
            Statement st = con.createStatement();
            ResultSet rs;

            if(comboBox.getValue().equals("economy")){


                rs = st.executeQuery("SELECT lf_price_eco FROM flights where fl_id = " + User.getFlId());
                if(rs.next()) {
                    ticketPrice.setText(String.valueOf(rs.getInt("lf_price_eco")));
                }

            } else  if(comboBox.getValue().equals("business")) {

                rs = st.executeQuery("SELECT lf_price_bsn FROM flights where fl_id = " + 10010);
                if(rs.next()) {
                    ticketPrice.setText(String.valueOf(rs.getInt("lf_price_bsn")));
                }
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }


    public void Buying(javafx.event.ActionEvent actionEvent) throws IOException {
        try {

            if(
                    !ps_name.getText().isEmpty()&&
                            !ps_surname.getText().isEmpty()&&
                            !ps_dob.getText().isEmpty()&&
                            !ps_phone.getText().isEmpty()&&
                            !ps_cardco.getText().isEmpty()&&
                            !ps_cardno.getText().isEmpty()&&
                            !ps_expire.getText().isEmpty()
                    ) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "root", "root");
                Statement st = con.createStatement();

                st.executeUpdate("INSERT INTO passengers VALUES(ps_id_inc.nextval,'" + ps_name.getText() +
                        "','" + ps_surname.getText() + "','" + ps_dob.getText() + "','"
                        + ps_gender.getValue() +
                        "',"
                        + Integer.valueOf(ps_phone.getText()) +
                        ",'"
                        + ps_country.getText() +
                        "','"
                        + ps_city.getText() +
                        "','"
                        + ps_pcode.getText() +
                        "',"
                        + User.getId() +
                        ")");

                st.executeUpdate("INSERT INTO payment_details VALUES(pym_id_inc.nextval,ps_id_inc.currval,"
                        + ps_cardno.getText() +
                        ","
                        + ps_cardco.getText() +
                        ",'"
                        + ps_expire.getText() +
                        "')");

                st.executeUpdate("INSERT INTO tickets VALUES(tk_id_inc.nextval," + User.getFlId() + ","
                        + Integer.valueOf(ticketPrice.getText()) +
                        ",PS_ID_INC.currval)");

                if (comboBox.getValue().equals("business")) {
                    st.executeUpdate("INSERT INTO business_class VALUES(bs_id_inc.nextval,ps_id_inc.currval)");
                } else if (comboBox.getValue().equals("economy")) {
                    st.executeUpdate("INSERT INTO economy_class VALUES(ec_id_inc.nextval,ps_id_inc.currval)");
                }

                con.close();

                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Tickets");
                a.setHeaderText("Buying information");
                a.setContentText("You have bought a ticket");

                a.show();

                toMainMenu(actionEvent);

            } else {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Incorrect information");
                a.setHeaderText("Logging information");
                a.setContentText("Please fill all fields.");

                a.showAndWait();
            }

        } catch(Exception e){
            System.out.println(e);
        }
    }

    public void backToSearch(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("UserBookFlights.fxml"));
        Scene loginScene = new Scene(root2);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

    public void toMainMenu(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("UserPanel.fxml"));
        Scene loginScene = new Scene(root2);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

}
