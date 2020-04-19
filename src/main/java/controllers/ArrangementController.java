package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import helper.Helper;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ArrangementController implements Initializable {

    @FXML
    private JFXTreeTableView<ArrangementController.ArrangementColumn> tableArrangement;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JFXTreeTableColumn<ArrangementController.ArrangementColumn,String> id = new JFXTreeTableColumn<>("Id");
        id.setPrefWidth(50);
        id.setCellValueFactory(userColumnStringCellDataFeatures -> userColumnStringCellDataFeatures.getValue().getValue().id);
        JFXTreeTableColumn<ArrangementController.ArrangementColumn,String> date = new JFXTreeTableColumn<>("Tanggal");
        date.setPrefWidth(166);
        date.setCellValueFactory(userColumnStringCellDataFeatures -> userColumnStringCellDataFeatures.getValue().getValue().date);
        JFXTreeTableColumn<ArrangementController.ArrangementColumn,String> user = new JFXTreeTableColumn<>("Pemohon");
        user.setPrefWidth(166);
        user.setCellValueFactory(userColumnStringCellDataFeatures -> userColumnStringCellDataFeatures.getValue().getValue().user);
        JFXTreeTableColumn<ArrangementController.ArrangementColumn,String> service = new JFXTreeTableColumn<>("Pelayanan");
        service.setPrefWidth(166);
        service.setCellValueFactory(userColumnStringCellDataFeatures -> userColumnStringCellDataFeatures.getValue().getValue().service);
        JFXTreeTableColumn<ArrangementController.ArrangementColumn,Boolean> verified = new JFXTreeTableColumn<>("Terverifikasi");
        verified.setPrefWidth(166);
        verified.setCellValueFactory(arrangementColumnBooleanCellDataFeatures -> arrangementColumnBooleanCellDataFeatures.getValue().getValue().verified);
        JFXTreeTableColumn<ArrangementController.ArrangementColumn,Boolean> finished = new JFXTreeTableColumn<>("Selesai");
        finished.setPrefWidth(166);
        finished.setCellValueFactory(arrangementColumnBooleanCellDataFeatures -> arrangementColumnBooleanCellDataFeatures.getValue().getValue().finished);
        JFXTreeTableColumn<ArrangementController.ArrangementColumn,Boolean> more = new JFXTreeTableColumn<>("Lihat");
        more.setPrefWidth(100);
        more.setCellValueFactory(arrangementColumnBooleanCellDataFeatures -> arrangementColumnBooleanCellDataFeatures.getValue().getValue().finished);
        Callback<TreeTableColumn<ArrangementColumn,Boolean>, TreeTableCell<ArrangementColumn, Boolean>> settingColumn = new Callback<TreeTableColumn<ArrangementColumn, Boolean>, TreeTableCell<ArrangementColumn, Boolean>>() {

            @Override
            public TreeTableCell<ArrangementColumn, Boolean> call(TreeTableColumn<ArrangementColumn, Boolean> arrangementColumnBooleanTreeTableColumn) {
                final TreeTableCell<ArrangementColumn,Boolean> cell = new TreeTableCell<ArrangementColumn,Boolean>(){
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

        ObservableList<ArrangementColumn> arrangementColumns = FXCollections.observableArrayList();
        arrangementColumns.add(new ArrangementColumn("1","20-12-2020","Wisnu","KTP",true,true));
        arrangementColumns.add(new ArrangementColumn("1","20-12-2020","isnu","SIM",true,true));
        arrangementColumns.add(new ArrangementColumn("1","20-12-2020","Dewa","STNK",true,true));
        arrangementColumns.add(new ArrangementColumn("1","20-12-2020","hahah","KTP",true,true));
        arrangementColumns.add(new ArrangementColumn("1","20-12-2020","Wisnu","KTP",true,true));

        TreeItem<ArrangementColumn> item = new RecursiveTreeItem<>(arrangementColumns, RecursiveTreeObject::getChildren);
        tableArrangement.getColumns().setAll(id,date,user,service,verified,finished,more);
        tableArrangement.setRoot(item);
        tableArrangement.setShowRoot(false);
    }

    static class ArrangementColumn extends RecursiveTreeObject<ArrangementColumn> {
        StringProperty id;
        StringProperty date;
        StringProperty user;
        StringProperty service;
        BooleanProperty verified;
        BooleanProperty finished;

        public ArrangementColumn(String id, String date, String user, String service, Boolean verified, Boolean finished) {
            this.id = new SimpleStringProperty(id);
            this.date = new SimpleStringProperty(date);
            this.user = new SimpleStringProperty(user);
            this.service = new SimpleStringProperty(service);
            this.verified = new SimpleBooleanProperty(verified);
            this.finished = new SimpleBooleanProperty(finished);
        }
    }
}
