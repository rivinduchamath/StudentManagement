package Controller;

import DBConnection.DBConnection;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utill.searchTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchStudentController {
    public JFXTextField searchStudent;
    public TableView<searchTM> tbl_student;
    public PreparedStatement search;

    public void initialize() {
        Connection connection = DBConnection.getInstance().getConnection();

        tbl_student.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tbl_student.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tbl_student.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tbl_student.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("telephone"));

        try {
            PreparedStatement search = connection.prepareStatement("SELECT * FROM student WHERE name LIKE ?");
            search.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        searchStudent.textProperty().addListener(observable ->  {
            tbl_student.getItems().clear();

            try {
                search.setString(1,"%"+searchStudent.getText()+"%");
                ResultSet resultSet = search.executeQuery();
                ObservableList<searchTM> searchItem =  tbl_student.getItems();
                while(resultSet.next()) {
                    searchItem.add(new searchTM(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4)
                            ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        });

    }
}
