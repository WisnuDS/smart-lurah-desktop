package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import helper.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;

import javax.swing.*;

public class AddAdminController {

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXPasswordField txtPassword2;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnCencel;

    @FXML
    void cancelAction(ActionEvent event) {
        Helper.changeAnchorPaneScene(event,"dashboard_view");
    }

    @FXML
    void onPasswordTyped(KeyEvent event) {
        String password1 = txtPassword.getText();
        String password2 = txtPassword2.getText();
        if (!password1.equals(password2)){
            txtPassword2.setFocusColor(Paint.valueOf("#e74a3b"));
            txtPassword2.setUnFocusColor(Paint.valueOf("#e74a3b"));
        }else {
            txtPassword2.setFocusColor(Paint.valueOf("#4e73df"));
            txtPassword2.setUnFocusColor(Paint.valueOf("#000000"));
        }
    }

    @FXML
    void saveAction(ActionEvent event) {
        Helper.changeAnchorPaneScene(event,"dashboard_view");
    }

}
