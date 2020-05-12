package controllers;

import com.jfoenix.controls.*;
import helper.Context;
import helper.Globe;
import helper.Helper;
import helper.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Callback;
import models.ArrangementModel;
import models.FileModel;
import models.UserModel;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ArrangementFileController implements Initializable {
    private String from;
    private final ArrayList<FileModel> dataFiles = new ArrayList<>();
    private String id;
    private String status;

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXListView<FileModel> listFile;

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
        }else if (from.equals(ArrangementController.PAGE)){
            changeScene(event,"arrangement_view");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        State state = Globe.getGlobe().getContext("VERIFICATION").getState("VIEW_DETAIL_USER_REGISTRATION");
        id = state.getItem("ID_SELECTED").toString();
        from = String.valueOf(state.getItem("FROM"));
        status = (String) state.getItem("STATUS_SELECTED");
        System.out.println("Id Selected : "+id);
        System.out.println("from : "+from);
        Callback<ListView<FileModel>, ListCell<FileModel>> settingList = new Callback<ListView<FileModel>, ListCell<FileModel>>() {
            @Override
            public ListCell<FileModel> call(ListView<FileModel> fileModelListView) {
                final ListCell<FileModel> cell = new ListCell<FileModel>(){
                    @Override
                    protected void updateItem(FileModel item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty){
                            setGraphic(null);
                        }else {
                            setText(item.getNameRequirement());
                        }
                    }
                };
                return cell;
            }
        };
        listFile.setCellFactory(settingList);
        if (from.equals(RegistrationController.PAGE)){
            for (UserModel model : UserModel.getUnregisteredUserModels(id)){
                FileModel file1 = new FileModel();
                file1.setUrlFile(model.getUrlKtpPhoto());
                file1.setNameRequirement("Foto KTP");
                FileModel file2 = new FileModel();
                file2.setUrlFile(model.getUrlSelfPhoto());
                file2.setNameRequirement("Foto Diri");
                dataFiles.add(file1);
                dataFiles.add(file2);
            }
        }else {
            dataFiles.addAll(FileModel.getAllFile(id));
        }
        listFile.getItems().setAll(dataFiles);
        listFile.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            FileModel file = listFile.getSelectionModel().getSelectedItem();
            String path = file.getUrlFile();
            File temp = new File(path);
            Image image = null;
            try {
                image = new Image(temp.toURI().toURL().toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            imgPreview.setImage(image);
        });
        if (from.equals(ArrangementController.PAGE)){
            if (status.equals("rejected")){
                btnAccept.setDisable(true);
                btnDecline.setDisable(true);
                btnDoing.setDisable(true);
            }else if (!status.equals("not verified")){
                btnAccept.setDisable(true);
                btnDecline.setDisable(true);
                btnDoing.setDisable(false);
            }
        }else {
            btnAccept.setDisable(false);
            btnDecline.setDisable(false);
            btnDoing.setVisible(false);
        }

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
            if (from.equals(ArrangementController.PAGE)){
                btnAccept.setDisable(true);
                btnDecline.setDisable(true);
                btnDoing.setDisable(false);
                btnDoing.setOnAction(event2 -> changeScene(event2,"form_view"));
                ArrangementModel model = new ArrangementModel();
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("status", "'verified'");
                model.update(hashMap,"id = "+id);
                dialog.close();
            }else {
                btnAccept.setDisable(true);
                btnDecline.setDisable(true);
                btnDoing.setOnAction(event2 -> changeScene(event2,"form_view"));
                UserModel model = new UserModel();
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("status", "'verified'");
                model.update(hashMap,"id = "+id);
                dialog.close();
            }
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
            if (from.equals(ArrangementController.PAGE)){

                ArrangementModel model = new ArrangementModel();
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("status","'rejected'");
                model.update(hashMap,"id="+id);
                dialog.close();
            }else {
                btnAccept.setDisable(true);
                btnDecline.setDisable(true);
                UserModel model = new UserModel();
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("status","'rejected'");
                model.update(hashMap,"id="+id);
                dialog.close();
            }
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

    @FXML
    public void doing(ActionEvent event) {
        State state = new State();
        state.putItem("ID_SELECTED",id);
        Context context = new Context();
        context.putState("ON_PROCESS",state);
        Globe.getGlobe().putContext("DOING",context);
        changeScene(event,"form_view");
    }
}
