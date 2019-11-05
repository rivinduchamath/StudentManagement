package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import  DBConnection.DBConnection;
import utill.studentTM;


public class UpdateFormController {

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    void btnUpdate_onAction(studentTM student) throws SQLException {

    }

    public void btnUpdate_onAction(ActionEvent event) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE student SET name=?, address=?, telephone=? WHERE student.id=?");

        pstm.setString(1, txtName.getText());
        pstm.setString(2, txtAddress.getText());
        pstm.setString(3, txtContact.getText().toString());
        pstm.setString(4, txtId.getText());
        if (pstm.executeUpdate() == 0) {
            throw new RuntimeException("Something went wrong");
        }
    }
}
