package Controller;

import DBConnection.DBConnection;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import utill.LecturerTM;

import java.io.IOException;
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
    public TableView<LecturerTM> tblLecturer;
    public AnchorPane root;
    public Button btnAdd;
    public Button btnUpdate;
    public ImageView imgHome;
    PreparedStatement preparedStatement1;
    PreparedStatement pstmInsert;
    PreparedStatement pstmUpdate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2500), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
        //Mapping table
        tblLecturer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("subjectId"));
        tblLecturer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("subject"));
        tblLecturer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("lecturerName"));

        try {
            Class.forName("com.mysql.jdbc.Driver");

        //Connection singleton pattern
        Connection connection = (Connection) DBConnection.getInstance().getConnection();
        //All Data

            preparedStatement1 = connection.prepareStatement("SELECT * FROM timetable");
            pstmInsert = connection.prepareStatement("INSERT INTO timetable VALUES (?,?,?)");
            pstmUpdate = connection.prepareStatement("UPDATE timetable SET  subject=? , lecturer = ? WHERE  id = ?");


            loadAllCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        tblLecturer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LecturerTM>() {
            @Override
            public void changed(ObservableValue<? extends LecturerTM> observable, LecturerTM oldValue, LecturerTM newValue) {
                LecturerTM selectedItem = tblLecturer.getSelectionModel().getSelectedItem();
                if (selectedItem == null) {
                    btnAdd.setDisable(false);
                    return;
                }
                btnAdd.setDisable(true);
                btnUpdate.setDisable(false);
                txtSubId.setText(selectedItem.getSubjectId());
                txtSubject.setText(selectedItem.getSubject());
                txtLecturerName.setText(selectedItem.getLecturerName());
            }
        });

    }

    //Load All Method for load all items to the table
    private void loadAllCustomers() throws SQLException {
        tblLecturer.getItems().clear();

        //Getting all data from Database
        ResultSet rst = preparedStatement1.executeQuery();

        while (rst.next()) {
            String code = rst.getString("id");
            String description = rst.getString("subject");
            String qtyOnHand = rst.getString("lecturer");

            ObservableList<LecturerTM> items = tblLecturer.getItems();
            LecturerTM i = new LecturerTM(code, description, qtyOnHand);
            items.add(i);
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws Exception {
        pstmInsert.setString(1, txtSubId.getText());

        boolean find = findAvilable(txtSubId.getText());
        if(find){
            new Alert(Alert.AlertType.ERROR,"Duplicate Entry").show();
            return;
        }
        pstmInsert.setString(2, txtLecturerName.getText());
        pstmInsert.setString(3, txtSubject.getText());
        pstmInsert.executeUpdate();
        loadAllCustomers();
    }

    private boolean findAvilable(String lec) throws Exception{
       ObservableList<LecturerTM> lecturerTMS = tblLecturer.getItems();
       for (LecturerTM l:lecturerTMS) {
         if( lec.equals(l.getSubjectId())){
             return true;
         }

        }
        return false;
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException {
        pstmUpdate.setString(3, txtSubId.getText());
        pstmUpdate.setString(1, txtLecturerName.getText());
        pstmUpdate.setString(2, txtSubject.getText());
        pstmUpdate.executeUpdate();
        loadAllCustomers();
        tblLecturer.refresh();
        btnUpdate.setDisable(true);
    }

    public void imgHome(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/View/Dashboard.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }
}
