package controllers;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import helper.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

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
        try {
            URL url = new URL("http://127.0.0.1:8000/api/create-user/?format=json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("username",txtUsername.getText());
            hashMap.put("password",txtPassword2.getText());
            hashMap.put("email",txtEmail.getText());
            hashMap.put("first_name",txtFirstName.getText());
            hashMap.put("last_name",txtLastName.getText());
            Gson gson = new Gson();
            String param = gson.toJson(hashMap);
            byte[] paramBytes = param.getBytes();
            int length = paramBytes.length;
            connection.setFixedLengthStreamingMode(length);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.write(paramBytes);
            out.close();
            if (connection.getResponseCode() != 200) {
                String failed = "Failed : HTTP Error code : " + connection.getResponseCode();
                throw new RuntimeException(failed);
            }
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(inputStreamReader);
            String output;
            String response = "";
            while ((output = br.readLine()) != null) {
                response = output;
            }
            Helper.changeAnchorPaneScene(event,"dashboard_view");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
