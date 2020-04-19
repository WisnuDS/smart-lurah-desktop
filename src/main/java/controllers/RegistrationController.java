package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

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
                            btn.setOnAction(event -> {
                                Node node = (Node) event.getSource();
                                AnchorPane anchorPane = (AnchorPane) node.getParent().getParent().getParent().getParent().getParent().getParent().getParent();
                                try {
                                    anchorPane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("../views/arrangement_file_view.fxml")));
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

        ObservableList<RegistrationColumn> registrationColumns = FXCollections.observableArrayList();
        registrationColumns.add(new RegistrationColumn("1","12345","Wisnu","http://haha.com","http://haha.com",true));
        registrationColumns.add(new RegistrationColumn("1","12345","Wisnu","http://haha.com","http://haha.com",true));
        registrationColumns.add(new RegistrationColumn("1","12345","Wisnu","http://haha.com","http://haha.com",true));
        registrationColumns.add(new RegistrationColumn("1","12345","Wisnu","http://haha.com","http://haha.com",true));

        TreeItem<RegistrationColumn> item = new RecursiveTreeItem<>(registrationColumns, RecursiveTreeObject::getChildren);
        tableRegistration.getColumns().setAll(id,telegramId,name,urlKtp,urlSelf,verified,more);
        tableRegistration.setRoot(item);
        tableRegistration.setShowRoot(false);
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
