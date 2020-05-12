package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import helper.Globe;
import helper.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import models.UserModel;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class EditUserController implements Initializable {

    private String id;
    UserModel currentUser = new UserModel();

    @FXML
    private JFXTextField txtIdTelegram;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtUrlKtp;

    @FXML
    private JFXTextField txtUrlSelf;

    @FXML
    private JFXComboBox<String> cbxStatus;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnCencel;

    @FXML
    void cancelAction(ActionEvent event) {
        Helper.changeAnchorPaneScene(event,"management_user_view");
    }

    @FXML
    void saveAction(ActionEvent event) {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("telegram_id",String.format("'%s'",txtIdTelegram.getText()));
        hashMap.put("name",String.format("'%s'",txtName.getText()));
        hashMap.put("status",String.format("'%s'",cbxStatus.getValue()));
        UserModel model = new UserModel();
        model.update(hashMap,"id="+id);
        Helper.changeAnchorPaneScene(event,"management_user_view");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbxStatus.getItems().setAll("verified","unverified");
        id = (String) Globe.getGlobe().getContext("CONTEXT_ID").getState("ID").getItem("ID_SELECTED");
        System.out.println("id :"+id);
        currentUser = UserModel.getUserModels(id).get(0);
        txtIdTelegram.setText(currentUser.getTelegramId());
        txtName.setText(currentUser.getName());
        txtUrlKtp.setText(currentUser.getUrlKtpPhoto());
        txtUrlSelf.setText(currentUser.getUrlSelfPhoto());
        txtUrlKtp.setDisable(true);
        txtUrlSelf.setDisable(true);
        cbxStatus.setValue(currentUser.getStatus());
        txtIdTelegram.setOnKeyTyped(keyEvent -> btnSave.setDisable(txtIdTelegram.getText().isEmpty() || txtName.getText().isEmpty()));
        txtName.setOnKeyTyped(keyEvent -> btnSave.setDisable(txtIdTelegram.getText().isEmpty() || txtName.getText().isEmpty()));
    }
}
