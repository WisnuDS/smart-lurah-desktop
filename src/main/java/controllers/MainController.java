package controllers;

import com.jfoenix.controls.JFXButton;
import helper.Globe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import models.AdminModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public static final String KEY_STATE = "CURRENT_USER";
    public static final String KEY_CONTEXT = "CURRENT_USER";
    public static final String KEY_GLOBE = "LOGIN";
    public static AdminModel currentAdmin;

    @FXML
    private Text name;

    @FXML
    private AnchorPane containerView;

    @FXML
    private JFXButton dashboardButton;

    @FXML
    private JFXButton btnPengajuan;

    @FXML
    private JFXButton btnRegistrasi;

    @FXML
    private JFXButton btnUsers;

    @FXML
    private JFXButton btnBradcast;

    @FXML
    void broadcastPage(ActionEvent event) {
        changeView("broadcast_view");
//        opacitySetting((float)0.6, (float)0.6, (float)0.6, (float)0.6,(float)1.0);
    }

    @FXML
    void dashboardClicked(ActionEvent event) {
        changeView("dashboard_view");
//        opacitySetting(1.0f,0.6f,0.6f,0.6f,0.6f);
    }

    @FXML
    void pengajuanPage(ActionEvent event) {
        changeView("arrangement_view");
//        opacitySetting(0.6f, 0.6f,1.0f, 0.6f, 0.6f);
    }

    @FXML
    void registrasiPage(ActionEvent event) {
        changeView("registration_view");
//        opacitySetting(0.6f, 0.6f,0.6f, 1.0f, 0.6f);
    }

    @FXML
    void userManagementPage(ActionEvent event) {
        changeView("management_user_view");
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
//        currentAdmin = (AdminModel) Globe.getGlobe().getContext(MainController.KEY_GLOBE).getState(MainController.KEY_CONTEXT).getItem(MainController.KEY_STATE);
        changeView("dashboard_view");
//        name.setText(currentAdmin.getUsername());
//        dashboardButton.setOpacity(1);
//        btnBradcast.setOpacity(0.6);
////        btnKerjakan.setOpacity(0.6);
//        btnPengajuan.setOpacity(0.6);
//        btnRegistrasi.setOpacity(0.6);
    }

    public void changeView(String view){
        try {
            containerView.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("../views/"+view+".fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void opacitySetting(float dashboard, float kerjakan, float pengajuan, float regis, float broadcast){
        dashboardButton.setOpacity(dashboard);
        btnBradcast.setOpacity(broadcast);
        btnPengajuan.setOpacity(pengajuan);
        btnRegistrasi.setOpacity(regis);
    }

}
