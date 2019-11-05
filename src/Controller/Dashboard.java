package Controller;

import DBConnection.DBConnection;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard  implements Initializable {

    public ImageView imgDeleteTimeTable;
    public AnchorPane root;
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
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblWelcome.setText("Welcome");
        }
    }

    @FXML
    void imgMouseOn(MouseEvent event)
    {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            switch (icon.getId()) {
                case "imgManageStudent":
                    lblWelcome.setText("Manage Student");

                    break;
                case "imgSearchStudent":
                    lblWelcome.setText("Search Student");

                    break;
                case "imgUpdateStudent":
                    lblWelcome.setText("Update Student");

                    break;
                case "imgDeleteStudent":
                    lblWelcome.setText("Delete Student");

                    break;
                case "imgStudentTimeTable":
                    lblWelcome.setText("Time Table");

                    break;
                case "imgDeleteTimeTable":
                    lblWelcome.setText("Manage Time Table");

                    break;


            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.GREEN);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2500), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    public void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent root = null;

            switch (icon.getId()) {
                case "imgManageStudent":
                    root = FXMLLoader.load(this.getClass().getResource("#"));
                    break;
                case "imgSearchStudent":
                    root = FXMLLoader.load(this.getClass().getResource("/View/SearchStudent.fxml"));
                    break;
                case "imgUpdateStudent":
                    root = FXMLLoader.load(this.getClass().getResource("#"));
                    break;
                case "imgDeleteStudent":
                    root = FXMLLoader.load(this.getClass().getResource("/View/DeleteStudent.fxml"));
                    break;
                case "imgStudentTimeTable":
                    root = FXMLLoader.load(this.getClass().getResource("/View/TimeTable.fxml"));
                    break;
                case "imgDeleteTimeTable":
                    root = FXMLLoader.load(this.getClass().getResource("#"));
                    break;
            }

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }
}
