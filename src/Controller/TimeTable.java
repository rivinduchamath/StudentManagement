package Controller;

import DBConnection.DBConnection;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import utill.LecturerTM;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TimeTable implements Initializable {
    public JFXTextField txtSubId;
    public Label lblWelcome;
    public JFXTextField txtSubject;
    public JFXTextField txtLecturerName;
    public TableView <LecturerTM> tblLecturer;
    public AnchorPane root;
    public Button btnAdd;
    public Button btnUpdate;
    PreparedStatement preparedStatement1;
    PreparedStatement pstmInsert;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2500), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
        //Mapping table
        tblLecturer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("subjectId"));
        tblLecturer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("lecturerName"));
        tblLecturer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("subject"));

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Connection singleton pattern
        Connection connection = (Connection) DBConnection.getInstance().getConnection();
        //All Data
        try {
            preparedStatement1 = connection.prepareStatement("SELECT * FROM timetable");
            pstmInsert = connection.prepareStatement("INSERT INTO timetable VALUES (?,?,?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            loadAllCustomers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private void loadAllCustomers() throws SQLException {
        tblLecturer.getItems().clear();

        ResultSet rst = preparedStatement1.executeQuery();

        ObservableList<LecturerTM> customers = tblLecturer.getItems();

        while (rst.next()) {
            String id = rst.getString("id");
            String name = rst.getString("subject");
            String address = rst.getString("lecturer");

            LecturerTM customer = new LecturerTM(id, name, address);
            customers.add(customer);


        }

    }


    public void btnAddOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }
}
