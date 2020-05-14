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
import javafx.beans.value.ObservableValue;
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
import javafx.scene.text.Text;
import javafx.util.Callback;
import models.ArrangementModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class ArrangementController implements Initializable {
    public static final String PAGE = "ARRANGEMENT_CONTROLLER";
//    private Timer timer = new Timer();

    @FXML
    private JFXTreeTableView<ArrangementController.ArrangementColumn> tableArrangement;

    @FXML
    private JFXComboBox<String> cbboxStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbboxStatus.getItems().setAll("Semua","Dalam Proses","Belum Diverifikasi","Ditolak","Selesai");
        JFXTreeTableColumn<ArrangementController.ArrangementColumn,String> id = new JFXTreeTableColumn<>("Id");
        id.setPrefWidth(80);
        id.setCellValueFactory(userColumnStringCellDataFeatures -> userColumnStringCellDataFeatures.getValue().getValue().id);
        JFXTreeTableColumn<ArrangementController.ArrangementColumn,String> date = new JFXTreeTableColumn<>("Tanggal");
        date.setPrefWidth(200);
        date.setCellValueFactory(userColumnStringCellDataFeatures -> userColumnStringCellDataFeatures.getValue().getValue().date);
        JFXTreeTableColumn<ArrangementController.ArrangementColumn,String> user = new JFXTreeTableColumn<>("Pemohon");
        user.setPrefWidth(200);
        user.setCellValueFactory(userColumnStringCellDataFeatures -> userColumnStringCellDataFeatures.getValue().getValue().user);
        JFXTreeTableColumn<ArrangementController.ArrangementColumn,String> service = new JFXTreeTableColumn<>("Pelayanan");
        service.setPrefWidth(250);
        service.setCellValueFactory(userColumnStringCellDataFeatures -> userColumnStringCellDataFeatures.getValue().getValue().service);
        JFXTreeTableColumn<ArrangementController.ArrangementColumn,String> verified = new JFXTreeTableColumn<>("Status");
        verified.setPrefWidth(186);
        verified.setCellValueFactory(arrangementColumnStringCellDataFeatures -> arrangementColumnStringCellDataFeatures.getValue().getValue().status);
        JFXTreeTableColumn<ArrangementController.ArrangementColumn,String> more = new JFXTreeTableColumn<>("Lihat");
        more.setPrefWidth(100);
        more.setCellValueFactory(arrangementColumnStringCellDataFeatures -> arrangementColumnStringCellDataFeatures.getValue().getValue().status);
        Callback<TreeTableColumn<ArrangementColumn,String>, TreeTableCell<ArrangementColumn, String>> settingColumn = new Callback<TreeTableColumn<ArrangementColumn, String>, TreeTableCell<ArrangementColumn, String>>() {

            @Override
            public TreeTableCell<ArrangementColumn, String> call(TreeTableColumn<ArrangementColumn, String> arrangementColumnStringTreeTableColumn) {
                final TreeTableCell<ArrangementColumn,String> cell = new TreeTableCell<ArrangementColumn,String>(){
                    final JFXButton btn = new JFXButton("More");

                    @Override
                    protected void updateItem(String aString, boolean b) {
                        super.updateItem(aString, b);
                        if (b) {
                            setGraphic(null);
                        } else {
                            btn.setButtonType(JFXButton.ButtonType.FLAT);
                            btn.setBackground(new Background(new BackgroundFill(Color.valueOf("#4e73df"), CornerRadii.EMPTY,null)));
                            btn.setTextFill(Paint.valueOf("#ffffff"));
                            btn.setCursor(Cursor.HAND);
                            btn.setOnAction(event -> {
                                Node node = (Node) event.getSource();
                                AnchorPane anchorPane = (AnchorPane) node.getParent().getParent().getParent().getParent().getParent().getParent().getParent();
                                JFXTreeTableRow<ArrangementColumn> row = (JFXTreeTableRow<ArrangementColumn>) node.getParent().getParent();
                                String id = String.valueOf(row.getTreeItem().getValue().id.getValue());
                                String status = row.getTreeItem().getValue().status.getValue();
                                State state = new State();
                                state.putItem("ID_SELECTED",id);
                                state.putItem("STATUS_SELECTED",status);
                                state.putItem("FROM",ArrangementController.PAGE);
                                Context context = new Context();
                                context.putState("VIEW_DETAIL_USER_REGISTRATION",state);
                                Globe.getGlobe().putContext("VERIFICATION",context);
//                                timer.cancel();
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

        Callback<TreeTableColumn<ArrangementColumn,String>, TreeTableCell<ArrangementColumn, String>> settingStatusColumn = new Callback<TreeTableColumn<ArrangementColumn, String>, TreeTableCell<ArrangementColumn, String>>() {

            @Override
            public TreeTableCell<ArrangementColumn, String> call(TreeTableColumn<ArrangementColumn, String> arrangementColumnStringTreeTableColumn) {
                final TreeTableCell<ArrangementColumn,String> cell = new TreeTableCell<ArrangementColumn,String>(){
                    final Text text = new Text();

                    @Override
                    protected void updateItem(String s, boolean b) {
                        super.updateItem(s, b);
                        if (b){
                            setGraphic(null);
                        }else {
                            switch (s) {
                                case "finished":
                                    text.setText("Selesai");
                                    text.setFill(Paint.valueOf("#1cc88a"));
                                    break;
                                case "verified":
                                    text.setText("Sedang Diproses");
                                    text.setFill(Paint.valueOf("#36b9cc"));
                                    break;
                                case "rejected":
                                    text.setText("Ditolak");
                                    text.setFill(Paint.valueOf("#e74a3b"));
                                    break;
                                default:
                                    text.setText("Belum Diverifikasi");
                                    text.setFill(Paint.valueOf("#f6c23e"));
                                    break;
                            }
                            setGraphic(text);
                        }
                        setText(null);
                    }
                };
                return cell;
            }
        };
        more.setCellFactory(settingColumn);
        verified.setCellFactory(settingStatusColumn);

        ObservableList<ArrangementColumn> arrangementColumns = FXCollections.observableArrayList();
        for (ArrangementModel model : ArrangementModel.getArrangements()){
            arrangementColumns.add(new ArrangementColumn(model.getId(),model.getDate(),model.getName(),model.getTypeService(),model.getStatus()));
        }

        TreeItem<ArrangementColumn> item = new RecursiveTreeItem<>(arrangementColumns, RecursiveTreeObject::getChildren);
        tableArrangement.getColumns().setAll(id,date,user,service,verified,more);
        tableArrangement.setRoot(item);
        tableArrangement.setShowRoot(false);
        cbboxStatus.valueProperty().addListener((observableValue, s, t1) -> {
            refreshTable(t1,arrangementColumns);
        });
        cbboxStatus.setValue("Semua");
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                refreshTable(cbboxStatus.getValue(),arrangementColumns);
//            }
//        },0,5000);
    }

    private void refreshTable(String t1, ObservableList<ArrangementColumn> arrangementColumns){
        switch (t1) {
            case "Semua":
                arrangementColumns.removeAll(arrangementColumns);
                for (ArrangementModel model : ArrangementModel.getArrangements()) {
                    arrangementColumns.add(new ArrangementColumn(model.getId(), model.getDate(), model.getName(), model.getTypeService(), model.getStatus()));
                }
                tableArrangement.refresh();
                break;
            case "Dalam Proses":
                arrangementColumns.removeAll(arrangementColumns);
                for (ArrangementModel model : ArrangementModel.getArrangements("api_arrangement.status='verified'")) {
                    arrangementColumns.add(new ArrangementColumn(model.getId(), model.getDate(), model.getName(), model.getTypeService(), model.getStatus()));
                }
                tableArrangement.refresh();
                break;
            case "Ditolak":
                arrangementColumns.removeAll(arrangementColumns);
                for (ArrangementModel model : ArrangementModel.getArrangements("api_arrangement.status='rejected'")) {
                    arrangementColumns.add(new ArrangementColumn(model.getId(), model.getDate(), model.getName(), model.getTypeService(), model.getStatus()));
                }
                tableArrangement.refresh();
                break;
            case "Selesai":
                arrangementColumns.removeAll(arrangementColumns);
                for (ArrangementModel model : ArrangementModel.getArrangements("api_arrangement.status='finished'")) {
                    arrangementColumns.add(new ArrangementColumn(model.getId(), model.getDate(), model.getName(), model.getTypeService(), model.getStatus()));
                }
                tableArrangement.refresh();
                break;
            default:
                arrangementColumns.removeAll(arrangementColumns);
                for (ArrangementModel model : ArrangementModel.getArrangements("api_arrangement.status='not verified'")) {
                    arrangementColumns.add(new ArrangementColumn(model.getId(), model.getDate(), model.getName(), model.getTypeService(), model.getStatus()));
                }
                tableArrangement.refresh();
                break;
        }
    }

    static class ArrangementColumn extends RecursiveTreeObject<ArrangementColumn> {
        StringProperty id;
        StringProperty date;
        StringProperty user;
        StringProperty service;
        StringProperty status;

        public ArrangementColumn(String id, String date, String user, String service, String status) {
            this.id = new SimpleStringProperty(id);
            this.date = new SimpleStringProperty(date);
            this.user = new SimpleStringProperty(user);
            this.service = new SimpleStringProperty(service);
            this.status = new SimpleStringProperty(status);
        }
    }
}
