package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Text name;

    @FXML
    private AnchorPane containerView;

    @FXML
    private JFXButton dashboardButton;

    @FXML
    void dashboardClicked(ActionEvent event) {
        changeView("dashboard_view");
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        changeView("dashboard_view");
    }

    public void changeView(String view){
        try {
            containerView.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("../views/"+view+".fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
