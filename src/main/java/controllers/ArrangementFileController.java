package controllers;

import com.jfoenix.controls.*;
import helper.Globe;
import helper.Helper;
import helper.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public class ArrangementFileController implements Initializable {
    private String from;

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXListView<String> listFile;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnAccept;

    @FXML
    private JFXButton btnDecline;

    @FXML
    private JFXButton btnDoing;

    @FXML
    private ImageView imgPreview;

    @FXML
    void goBack(ActionEvent event) {
        if (from.equals(RegistrationController.PAGE)){
            changeScene(event,"registration_view");
        }else {
            changeScene(event,"arrangement_view");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listFile.getItems().add("Data");
        listFile.getItems().add("Dada");
        listFile.getItems().add("Deee");
        listFile.getItems().add("yoi");
        State state = Globe.getGlobe().getContext("VERIFICATION").getState("VIEW_DETAIL_USER_REGISTRATION");
        String id = String.valueOf(state.getItem("ID_SELECTED"));
        from = String.valueOf(state.getItem("FROM"));
        System.out.println("Id Selected : "+id);
        listFile.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String selectData = listFile.getSelectionModel().getSelectedItem();
            String path = "https://lh3.googleusercontent.com/proxy/jizElBpArw9-BgsxwTANBWgaf66XPPckUY4pVHGeF-3N-VqSMTU0BQKTiOD2TebucGVnPftkxSq_gswvGkOvzBROs084tGWUalXyRjJYLPovJHk6b9K7ZZu6to-EfytNogawceLw";
            Image image = new Image(path);
            imgPreview.setImage(image);
        });
    }

    @FXML
    void accept(ActionEvent event) {
        JFXDialogLayout content = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(stackPane,content, JFXDialog.DialogTransition.CENTER);
        content.setHeading(new Text("Konfirmasi Penerimaan"));
        content.setBody(new Text("Apakah anda yakin mengkonfirmasi berkas ini?"));
        JFXButton buttonYa = new JFXButton("Ya");
        JFXButton buttonTidak = new JFXButton("Tidak");
        buttonYa.setTextFill(Paint.valueOf("#ffffff"));
        buttonYa.setButtonType(JFXButton.ButtonType.FLAT);
        buttonYa.setBackground(new Background(new BackgroundFill(Paint.valueOf("#4e73df"), CornerRadii.EMPTY,null)));
        buttonYa.setCursor(Cursor.HAND);
        buttonYa.setOnAction(event1 -> {
            btnAccept.setDisable(true);
            btnDecline.setDisable(true);
            btnDoing.setDisable(false);
            btnDoing.setOnAction(event2 -> changeScene(event2,"form_view"));
            dialog.close();
        });
        buttonTidak.setCursor(Cursor.HAND);
        buttonTidak.setOnAction(event1 -> dialog.close());
        content.setActions(buttonTidak,buttonYa);
        dialog.show();
    }

    @FXML
    void decline(ActionEvent event) {
        JFXDialogLayout content = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(stackPane,content, JFXDialog.DialogTransition.CENTER);
        content.setHeading(new Text("Isikan Alasan Penolakan"));
        JFXTextArea area = new JFXTextArea();
        area.setPromptText("Alasan Penolakan");
        area.setLabelFloat(true);
        content.setBody(area);
        JFXButton buttonYa = new JFXButton("Kirim");
        JFXButton buttonTidak = new JFXButton("Batal");
        buttonYa.setTextFill(Paint.valueOf("#ffffff"));
        buttonYa.setButtonType(JFXButton.ButtonType.FLAT);
        buttonYa.setBackground(new Background(new BackgroundFill(Paint.valueOf("#4e73df"), CornerRadii.EMPTY,null)));
        buttonYa.setCursor(Cursor.HAND);
        buttonYa.setOnAction(event1 -> {
            dialog.close();
        });
        buttonTidak.setCursor(Cursor.HAND);
        buttonTidak.setOnAction(event1 -> dialog.close());
        content.setActions(buttonTidak,buttonYa);
        dialog.show();
    }

    public void changeScene(ActionEvent event, String page){
        Node node = (Node) event.getSource();
        System.out.println(node.getParent().getParent().getParent().getParent().getChildrenUnmodifiable());
        AnchorPane pane = (AnchorPane) node.getParent().getParent().getParent().getParent().getParent();
        try {
            pane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("../views/"+page+".fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
