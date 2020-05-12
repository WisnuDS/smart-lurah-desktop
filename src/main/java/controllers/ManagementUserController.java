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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
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

public class ManagementUserController implements Initializable {

    @FXML
    private JFXTreeTableView<ManagementUserController.ManagementUserColumn> tableArrangement;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JFXTreeTableColumn<ManagementUserController.ManagementUserColumn,String> id = new JFXTreeTableColumn<>("Id");
        id.setPrefWidth(50);
        id.setCellValueFactory(userColumnStringCellDataFeatures -> userColumnStringCellDataFeatures.getValue().getValue().id);
        JFXTreeTableColumn<ManagementUserController.ManagementUserColumn,String> username = new JFXTreeTableColumn<>("Id Telegram");
        username.setPrefWidth(166);
        username.setCellValueFactory(userColumnStringCellDataFeatures -> userColumnStringCellDataFeatures.getValue().getValue().telegramId);
        JFXTreeTableColumn<ManagementUserController.ManagementUserColumn,String> email = new JFXTreeTableColumn<>("Nama");
        email.setPrefWidth(166);
        email.setCellValueFactory(userColumnStringCellDataFeatures -> userColumnStringCellDataFeatures.getValue().getValue().name);
        JFXTreeTableColumn<ManagementUserController.ManagementUserColumn,String> firstName = new JFXTreeTableColumn<>("Url Foto Ktp");
        firstName.setPrefWidth(166);
        firstName.setCellValueFactory(userColumnStringCellDataFeatures -> userColumnStringCellDataFeatures.getValue().getValue().urlKtpPhoto);
        JFXTreeTableColumn<ManagementUserController.ManagementUserColumn,String> lastName = new JFXTreeTableColumn<>("Url Foto Diri");
        lastName.setPrefWidth(166);
        lastName.setCellValueFactory(userColumnStringCellDataFeatures -> userColumnStringCellDataFeatures.getValue().getValue().urlSelfPhoto);
        JFXTreeTableColumn<ManagementUserController.ManagementUserColumn,String> status = new JFXTreeTableColumn<>("Status");
        status.setPrefWidth(166);
        status.setCellValueFactory(managementUserColumnStringCellDataFeatures -> managementUserColumnStringCellDataFeatures.getValue().getValue().status);
        JFXTreeTableColumn<ManagementUserController.ManagementUserColumn,String> more = new JFXTreeTableColumn<>("Edit");
        more.setPrefWidth(100);
        more.setCellValueFactory(managementUserColumnStringCellDataFeatures -> managementUserColumnStringCellDataFeatures.getValue().getValue().status);
        Callback<TreeTableColumn<ManagementUserColumn,String>, TreeTableCell<ManagementUserColumn, String>> settingColumn = new Callback<TreeTableColumn<ManagementUserColumn, String>, TreeTableCell<ManagementUserColumn, String>>() {
            @Override
            public TreeTableCell<ManagementUserColumn, String> call(TreeTableColumn<ManagementUserColumn, String> managementUserColumnBooleanTreeTableColumn) {
                final TreeTableCell<ManagementUserColumn,String> cell = new TreeTableCell<ManagementUserColumn,String>(){
                  final JFXButton btn = new JFXButton("Edit");
                    @Override
                    protected void updateItem(String aString, boolean b) {
                        super.updateItem(aString, b);
                        if (b){
                            setGraphic(null);
                        }else {
                            btn.setButtonType(JFXButton.ButtonType.FLAT);
                            btn.setBackground(new Background(new BackgroundFill(Color.valueOf("#4e73df"), CornerRadii.EMPTY,null)));
                            btn.setTextFill(Paint.valueOf("#ffffff"));
                            btn.setCursor(Cursor.HAND);
                            btn.setOnAction(event -> {
                                Node node = (Node) event.getSource();
                                AnchorPane anchorPane = (AnchorPane) node.getParent().getParent().getParent().getParent().getParent().getParent().getParent();
                                JFXTreeTableRow<ManagementUserColumn> row = (JFXTreeTableRow<ManagementUserColumn>) node.getParent().getParent();
                                String id = row.getTreeItem().getValue().id.getValue();
                                State state = new State();
                                state.putItem("ID_SELECTED",id);
                                Context context = new Context();
                                context.putState("ID",state);
                                Globe.getGlobe().putContext("CONTEXT_ID",context);
                                try {
                                    anchorPane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("../views/edit_user_view.fxml")));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
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
        ObservableList<ManagementUserController.ManagementUserColumn> managementUserColumns = FXCollections.observableArrayList();
        for (UserModel model : UserModel.getUserModels()){
            managementUserColumns.add(new ManagementUserColumn(model.getId(),model.getTelegramId(),model.getName(),model.getUrlKtpPhoto(),model.getUrlSelfPhoto(),model.getStatus()));
        }

        TreeItem<ManagementUserColumn> item = new RecursiveTreeItem<>(managementUserColumns,RecursiveTreeObject::getChildren);
        tableArrangement.getColumns().setAll(id,username,email,firstName,lastName,status,more);
        tableArrangement.setRoot(item);
        tableArrangement.setShowRoot(false);
    }

    static class ManagementUserColumn extends RecursiveTreeObject<ManagementUserController.ManagementUserColumn> {
        StringProperty id;
        StringProperty telegramId;
        StringProperty name;
        StringProperty urlKtpPhoto;
        StringProperty urlSelfPhoto;
        StringProperty status;

        public ManagementUserColumn(String id, String telegramId, String name, String urlKtpPhoto, String urlSelfPhoto, String status) {
            this.id = new SimpleStringProperty(id);
            this.telegramId = new SimpleStringProperty(telegramId);
            this.name = new SimpleStringProperty(name);
            this.urlKtpPhoto = new SimpleStringProperty(urlKtpPhoto);
            this.urlSelfPhoto = new SimpleStringProperty(urlSelfPhoto);
            this.status = new SimpleStringProperty(status);
        }
    }
}
