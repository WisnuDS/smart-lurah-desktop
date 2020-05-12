package models;

import helper.Model;

import java.util.ArrayList;
import java.util.List;

public class ServiceModel extends Model {
    private String id;
    private String typeService;
    private String status;
    private static final ServiceModel model = new ServiceModel();

    public ServiceModel() {
    }

    public ServiceModel(String id, String typeService) {
        this.id = id;
        this.typeService = typeService;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeService() {
        return typeService;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static List<ServiceModel> getServices(String status){
        List<ServiceModel> models = new ArrayList<>();
        for (Object o : model.getAllData(String.format("WHERE status = '%s'",status))){
            models.add((ServiceModel) o);
        }
        return models;
    }

    @Override
    public String table() {
        return "api_services";
    }

    @Override
    public String[] columns() {
        return new String[]{"id","type_service","status"};
    }
}
