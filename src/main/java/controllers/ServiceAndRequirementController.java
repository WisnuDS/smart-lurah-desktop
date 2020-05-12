package controllers;

import com.jfoenix.controls.*;
import com.jfoenix.skins.JFXComboBoxListViewSkin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Callback;
import models.RequirementModel;
import models.ServiceModel;
import models.ServiceRequirementModel;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class ServiceAndRequirementController implements Initializable {

    private Callback<ListView<ServiceModel>, ListCell<ServiceModel>> settingListService = new Callback<ListView<ServiceModel>, ListCell<ServiceModel>>() {
        @Override
        public ListCell<ServiceModel> call(ListView<ServiceModel> serviceModelListView) {
            final ListCell<ServiceModel> cell = new ListCell<ServiceModel>(){
                @Override
                protected void updateItem(ServiceModel serviceModel, boolean b) {
                    super.updateItem(serviceModel, b);
                    if (b){
                        setGraphic(null);
                        setText(null);
                    }else {
                        setText(serviceModel.getTypeService());
                    }
                }
            };
            return cell;
        }
    };

    private Callback<ListView<RequirementModel>, ListCell<RequirementModel>> settingListRequirement = new Callback<ListView<RequirementModel>, ListCell<RequirementModel>>() {
        @Override
        public ListCell<RequirementModel> call(ListView<RequirementModel> requirementModelListView) {
            final ListCell<RequirementModel> cell = new ListCell<RequirementModel>(){
                @Override
                protected void updateItem(RequirementModel requirementModel, boolean b) {
                    super.updateItem(requirementModel, b);
                    if (!b) {
                        setText(requirementModel.getNameRequirement());
                    }else {
                        setText(null);
                    }
                    setGraphic(null);
                }
            };
            return cell;
        }
    };

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXListView<ServiceModel> listServices;

    @FXML
    private JFXListView<RequirementModel> listRequirements;

    @FXML
    private JFXButton btnAddService;

    @FXML
    private JFXButton btnEditService;

    @FXML
    private JFXButton btnAddRequirement;

    @FXML
    private JFXButton btnEditRequirement;

    @FXML
    void addRequirements(ActionEvent event) {
        createDialog("Persyaratan");
    }

    @FXML
    void addServices(ActionEvent event) {
        createDialog("Pelayanan");
    }

    @FXML
    void editRequirements(ActionEvent event) {

    }

    @FXML
    void editServices(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnEditService.setDisable(true);
        btnEditRequirement.setDisable(true);
        btnAddRequirement.setDisable(true);
        listServices.getItems().setAll(ServiceModel.getServices("active"));
        listServices.setCellFactory(settingListService);
        listRequirements.setCellFactory(settingListRequirement);
        listServices.getSelectionModel().selectedItemProperty().addListener((observableValue, serviceModel, t1) -> {
            btnEditService.setDisable(false);
            btnAddRequirement.setDisable(false);
            ServiceModel selected = listServices.getSelectionModel().getSelectedItem();
            List<ServiceRequirementModel> details = ServiceRequirementModel.getDetailRequirement(selected.getId());
            listRequirements.getItems().removeAll(listRequirements.getItems());
            for (ServiceRequirementModel detail : details){
                listRequirements.getItems().add(new RequirementModel(detail.getRequirementId(),detail.getNameRequirement()));
            }
            listRequirements.refresh();
        });
        listRequirements.getSelectionModel().selectedItemProperty().addListener((observableValue, requirementModel, t1) -> {
            btnEditRequirement.setDisable(false);
        });
    }

    private void createDialog(String type){
        JFXDialogLayout content = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(stackPane,content, JFXDialog.DialogTransition.CENTER);
        content.setHeading(new Text("Tambahkan "+type));
        JFXTextField textField = new JFXTextField();
        textField.setPromptText("Masukan Nama "+type);
        textField.setDisable(true);
        if (type.equals("Pelayanan")){
            JFXComboBox<ServiceModel> box = new JFXComboBox<ServiceModel>();
            box.setCellFactory(settingListService);
            box.setButtonCell(settingListService.call(null));
            box.getItems().setAll(ServiceModel.getServices("inactive"));
//            box.setPromptText("Pilih");
            box.getItems().add(new ServiceModel("0","Masukan Pelayanan Baru"));
            box.valueProperty().addListener((observableValue, serviceModel, t1) -> {
                textField.setDisable(!t1.getTypeService().equals("Masukan Pelayanan Baru"));
            });
            VBox vBox = new VBox(25,box,textField);
            content.setBody(vBox);
        }else {
            JFXComboBox<RequirementModel> box = new JFXComboBox<RequirementModel>();
            box.setCellFactory(settingListRequirement);
            box.setButtonCell(settingListRequirement.call(null));
            box.getItems().setAll(RequirementModel.getAllRequirement(String.format("WHERE id NOT IN (SELECT id FROM api_detailrequirements WHERE service_id = %s)",listServices.getSelectionModel().getSelectedItem().getId())));
            box.getItems().add(new RequirementModel("0","Masukan Persyaratan Baru"));
//            box.setPromptText("Pilih");
            box.valueProperty().addListener((observableValue, serviceModel, t1) -> {
                textField.setDisable(!t1.getNameRequirement().equals("Masukan Persyaratan Baru"));
            });
            content.setBody(new VBox(25,box,textField));
        }
        JFXButton btnYes = new JFXButton("Tambah");
        JFXButton btnNo = new JFXButton("Batal");
        btnYes.setTextFill(Paint.valueOf("#ffffff"));
        btnYes.setButtonType(JFXButton.ButtonType.FLAT);
        btnYes.setBackground(new Background(new BackgroundFill(Paint.valueOf("#4e73df"), CornerRadii.EMPTY,null)));
        btnYes.setCursor(Cursor.HAND);
        btnNo.setCursor(Cursor.HAND);
        btnNo.setOnAction(event -> dialog.close());
        btnYes.setOnAction(event -> {
            if (type.equals("Pelayanan")){
                if (textField.isDisable()){
                    JFXComboBox<ServiceModel> box = (JFXComboBox<ServiceModel>) content.getBody().get(0);
                    ServiceModel serviceModel = box.getValue();
                    HashMap<String,String> hashMap = new HashMap<>();
                    hashMap.put("status","'active'");
                    serviceModel.update(hashMap,"id = "+serviceModel.getId());
                }else {
                    ServiceModel serviceModel = new ServiceModel();
                    HashMap<String,String> hashMap = new HashMap<>();
                    hashMap.put("type_service","'"+textField.getText()+"'");
                    hashMap.put("status","'active'");
                    serviceModel.insert(hashMap);
                }
                listServices.getItems().removeAll(listServices.getItems());
                listServices.getItems().addAll(ServiceModel.getServices("active"));
                listServices.refresh();
            }else {
                if (textField.isDisable()){
                    VBox boxx = (VBox) content.getBody().get(0);
                    JFXComboBox<RequirementModel> box = (JFXComboBox<RequirementModel>) boxx.getChildren().get(0);
                    RequirementModel requirementModel = box.getValue();
                    ServiceRequirementModel serviceRequirementModel = new ServiceRequirementModel();
                    HashMap<String,String> hashMap = new HashMap<>();
                    hashMap.put("requirement_id",requirementModel.getId());
                    insertServiceRequirement(serviceRequirementModel, hashMap);
                }else {
                    RequirementModel requirementModel = new RequirementModel();
                    HashMap<String,String> hash = new HashMap<>();
                    hash.put("name_requirement",textField.getText());
                    requirementModel.insert(hash);
                    String id = RequirementModel.getAllRequirement("ORDER BY id DESC LIMIT 1").get(0).getId();
                    ServiceRequirementModel serviceRequirementModel = new ServiceRequirementModel();
                    HashMap<String,String> hashMap = new HashMap<>();
                    hashMap.put("requirement_id",id);
                    insertServiceRequirement(serviceRequirementModel, hashMap);
                }
            }
            dialog.close();
        });
        content.setActions(btnNo,btnYes);
        dialog.show();
    }

    private void insertServiceRequirement(ServiceRequirementModel serviceRequirementModel, HashMap<String, String> hashMap) {
        hashMap.put("service_id",listServices.getSelectionModel().getSelectedItem().getId());
        serviceRequirementModel.insert(hashMap);
        List<ServiceRequirementModel> details = ServiceRequirementModel.getDetailRequirement(listServices.getSelectionModel().getSelectedItem().getId());
        listRequirements.getItems().removeAll(listRequirements.getItems());
        for (ServiceRequirementModel detail : details){
            listRequirements.getItems().add(new RequirementModel(detail.getRequirementId(),detail.getNameRequirement()));
        }
        listRequirements.refresh();
    }
}

