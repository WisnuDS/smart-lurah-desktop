package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import helper.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.TextFlow;

public class LoginController {

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private TextFlow dialog;

    @FXML
    private JFXButton btnLogin1;

    @FXML
    void authentication(ActionEvent event) {

    }

    @FXML
    void byPass(ActionEvent event) {
        Helper.changePage(event,"main_view");
    }
}
