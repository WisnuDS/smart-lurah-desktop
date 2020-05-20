package controllers;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import helper.Context;
import helper.Globe;
import helper.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.TextFlow;
import models.AdminModel;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static helper.Helper.changePage;

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
        dialog.setVisible(false);
        String user = username.getText();
        String pass = password.getText();
        AdminModel currentUser;
        try {
            URL url = new URL("http://127.0.0.1:8000/api/login/?format=json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            String param = "{\"username\":\"" + user + "\",\"password\":\"" + pass + "\"}";
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
            connection.disconnect();
            Gson gson = new Gson();
            currentUser = gson.fromJson(response, AdminModel.class);
            if (currentUser.getUsername()!=null){
                State state = new State();
                state.putItem(MainController.KEY_STATE,currentUser);
                Context context = new Context();
                context.putState(MainController.KEY_CONTEXT,state);
                Globe.getGlobe().putContext(MainController.KEY_GLOBE,context);
                changePage(event,"main_view");
            }else {
                dialog.setVisible(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void byPass(ActionEvent event) {
        changePage(event,"main_view");
    }


}
