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
    private JFXButton btnPengajuan;

    @FXML
    private JFXButton btnKerjakan;

    @FXML
    private JFXButton btnRegistrasi;

    @FXML
    private JFXButton btnBradcast;

    @FXML
    void broadcastPage(ActionEvent event) {
        changeView("broadcast_view");
        opacitySetting((float)0.6, (float)0.6, (float)0.6, (float)0.6,(float)1.0);
    }

    @FXML
    void dashboardClicked(ActionEvent event) {
        changeView("dashboard_view");
        opacitySetting(1.0f,0.6f,0.6f,0.6f,0.6f);
    }

    @FXML
    void kerjakanPage(ActionEvent event) {

    }

    @FXML
    void pengajuanPage(ActionEvent event) {
        changeView("arrangement_view");
        opacitySetting(0.6f, 0.6f,1.0f, 0.6f, 0.6f);
    }

    @FXML
    void registrasiPage(ActionEvent event) {
        changeView("registration_view");
        opacitySetting(0.6f, 0.6f,0.6f, 1.0f, 0.6f);
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        changeView("dashboard_view");
        dashboardButton.setOpacity(1);
        btnBradcast.setOpacity(0.6);
        btnKerjakan.setOpacity(0.6);
        btnPengajuan.setOpacity(0.6);
        btnRegistrasi.setOpacity(0.6);
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
        btnKerjakan.setOpacity(kerjakan);
        btnPengajuan.setOpacity(pengajuan);
        btnRegistrasi.setOpacity(regis);
    }

}
