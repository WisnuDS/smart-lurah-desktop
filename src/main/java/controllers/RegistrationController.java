package controllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import helper.Context;
import helper.Globe;
import helper.State;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import models.UserModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {
    public static final String PAGE = "REGISTRATION_CONTROLLER";

    @FXML
    private JFXTreeTableView<RegistrationColumn> tableRegistration;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JFXTreeTableColumn<RegistrationColumn,String> id = new JFXTreeTableColumn<>("Id");
        id.setPrefWidth(50);
        id.setCellValueFactory(userColumnStringCellDataFeatures -> userColumnStringCellDataFeatures.getValue().getValue().id);
        JFXTreeTableColumn<RegistrationColumn,String> telegramId = new JFXTreeTableColumn<>("Id Telegram");
        telegramId.setPrefWidth(166);
        telegramId.setCellValueFactory(userColumnStringCellDataFeatures -> userColumnStringCellDataFeatures.getValue().getValue().telegramId);
        JFXTreeTableColumn<RegistrationColumn,String> name = new JFXTreeTableColumn<>("Nama");
        name.setPrefWidth(166);
        name.setCellValueFactory(userColumnStringCellDataFeatures -> userColumnStringCellDataFeatures.getValue().getValue().name);
        JFXTreeTableColumn<RegistrationColumn,String> urlKtp = new JFXTreeTableColumn<>("Url Foto Ktp");
        urlKtp.setPrefWidth(166);
        urlKtp.setCellValueFactory(userColumnStringCellDataFeatures -> userColumnStringCellDataFeatures.getValue().getValue().urlKtpPhoto);
        JFXTreeTableColumn<RegistrationColumn,String> urlSelf = new JFXTreeTableColumn<>("Url Foto Diri");
        urlSelf.setPrefWidth(166);
        urlSelf.setCellValueFactory(userColumnStringCellDataFeatures -> userColumnStringCellDataFeatures.getValue().getValue().urlSelfPhoto);
        JFXTreeTableColumn<RegistrationColumn,Boolean> verified = new JFXTreeTableColumn<>("Terverifikasi");
        verified.setPrefWidth(166);
        verified.setCellValueFactory(registrationColumnBooleanCellDataFeatures -> registrationColumnBooleanCellDataFeatures.getValue().getValue().verified);
        JFXTreeTableColumn<RegistrationColumn,Boolean> more = new JFXTreeTableColumn<>("Detail");
        more.setPrefWidth(116);
        more.setCellValueFactory(registrationColumnBooleanCellDataFeatures -> registrationColumnBooleanCellDataFeatures.getValue().getValue().verified);
        Callback<TreeTableColumn<RegistrationColumn,Boolean>, TreeTableCell<RegistrationColumn, Boolean>> settingColumn = new Callback<TreeTableColumn<RegistrationColumn, Boolean>, TreeTableCell<RegistrationColumn, Boolean>>() {

            @Override
            public TreeTableCell<RegistrationColumn, Boolean> call(TreeTableColumn<RegistrationColumn, Boolean> arrangementColumnBooleanTreeTableColumn) {
                final TreeTableCell<RegistrationColumn,Boolean> cell = new TreeTableCell<RegistrationColumn,Boolean>(){
                    final JFXButton btn = new JFXButton("More");

                    @Override
                    protected void updateItem(Boolean aBoolean, boolean b) {
                        super.updateItem(aBoolean, b);
                        if (b) {
                            setGraphic(null);
                        } else {
                            btn.setButtonType(JFXButton.ButtonType.FLAT);
                            btn.setBackground(new Background(new BackgroundFill(Color.valueOf("#4e73df"), CornerRadii.EMPTY,null)));
                            btn.setTextFill(Paint.valueOf("#ffffff"));
                            btn.setCursor(Cursor.HAND);
                            btn.setOnAction(event -> {
                                Node node = (Node) event.getSource();
                                JFXTreeTableRow<RegistrationColumn> row = (JFXTreeTableRow<RegistrationColumn>) node.getParent().getParent();
                                String id = String.valueOf(row.getTreeItem().getValue().id);
                                State state = new State();
                                state.putItem("ID_SELECTED",id);
                                state.putItem("FROM",RegistrationController.PAGE);
                                Context context = new Context();
                                context.putState("VIEW_DETAIL_USER_REGISTRATION",state);
                                Globe.getGlobe().putContext("VERIFICATION",context);
                                changeScene(event);
                            });
                            setGraphic(btn);
                        }
                        setText(null);
                    }
                };
                return cell;
            }
        };

        more.setCellFactory(settingColumn);
        ObservableList<RegistrationColumn> registrationColumns = FXCollections.observableArrayList();
        for (UserModel model : UserModel.getUnregisteredUserModels()){
            registrationColumns.add(new RegistrationColumn(model.getId(),model.getTelegramId(),model.getName(),model.getUrlKtpPhoto(),model.getUrlSelfPhoto(),model.isVerification()));
        }

        TreeItem<RegistrationColumn> item = new RecursiveTreeItem<>(registrationColumns, RecursiveTreeObject::getChildren);
        tableRegistration.getColumns().setAll(id,telegramId,name,urlKtp,urlSelf,verified,more);
        tableRegistration.setRoot(item);
        tableRegistration.setShowRoot(false);
    }

    private void changeScene(ActionEvent event) {
        Node node1 = (Node) event.getSource();
        Scene scene = node1.getScene();
        Node node = scene.lookup("#anchorpane");
        AnchorPane anchorPane = (AnchorPane) node;
        try {
            anchorPane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("../views/arrangement_file_view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class RegistrationColumn extends RecursiveTreeObject<RegistrationColumn> {
        StringProperty id;
        StringProperty telegramId;
        StringProperty name;
        StringProperty urlKtpPhoto;
        StringProperty urlSelfPhoto;
        BooleanProperty verified;

        public RegistrationColumn(String id, String telegramId, String name, String urlKtpPhoto, String urlSelfPhoto, Boolean verified) {
            this.id = new SimpleStringProperty(id);
            this.telegramId = new SimpleStringProperty(telegramId);
            this.name = new SimpleStringProperty(name);
            this.urlKtpPhoto = new SimpleStringProperty(urlKtpPhoto);
            this.urlSelfPhoto = new SimpleStringProperty(urlSelfPhoto);
            this.verified = new SimpleBooleanProperty(verified);
        }
    }
}
