package Controller;

import DBConnection.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard  implements Initializable {

    public ImageView imgDeleteTimeTable;
    @FXML
    private ImageView imgManageStudent;

    @FXML
    private ImageView imgSearchStudent;

    @FXML
    private ImageView imgUpdateStudent;

    @FXML
    private ImageView imgDeleteStudent;

    @FXML
    private ImageView imgStudentTimeTable;

    @FXML
    private Label lblWelcome;



    @FXML
    void imgMouseExit(MouseEvent event) {

    }

    @FXML
    void imgMouseOn(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
