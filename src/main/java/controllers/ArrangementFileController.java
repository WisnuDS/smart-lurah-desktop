package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import helper.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public class ArrangementFileController implements Initializable {

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
        Node node = (Node) event.getSource();
        System.out.println(node.getParent().getParent().getParent().getParent().getChildrenUnmodifiable());
        AnchorPane pane = (AnchorPane) node.getParent().getParent().getParent().getParent();
        try {
            pane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("../views/arrangement_view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listFile.getItems().add("Data");
        listFile.getItems().add("Dada");
        listFile.getItems().add("Deee");
        listFile.getItems().add("yoi");

        listFile.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String selectData = listFile.getSelectionModel().getSelectedItem();
            String path = "https://lh3.googleusercontent.com/proxy/jizElBpArw9-BgsxwTANBWgaf66XPPckUY4pVHGeF-3N-VqSMTU0BQKTiOD2TebucGVnPftkxSq_gswvGkOvzBROs084tGWUalXyRjJYLPovJHk6b9K7ZZu6to-EfytNogawceLw";
            Image image = new Image(path);
            imgPreview.setImage(image);
        });
    }
}
