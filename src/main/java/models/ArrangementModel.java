package models;

import helper.Model;

import java.util.ArrayList;
import java.util.List;

public class ArrangementModel extends Model {
    private String id;
    private String date;
    private String serviceId;
    private String typeService;
    private String userId;
    private String name;
    private String status;
    private static final ArrangementModel model = new ArrangementModel();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTypeService() {
        return typeService;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static List<ArrangementModel> getArrangements(){
        List<ArrangementModel> models = new ArrayList<>();
        String clause = "JOIN api_services on api_arrangement.service_id = api_services.id JOIN api_user on api_arrangement.user_id = api_user.id";
        for (Object o : model.getAllData(clause)){
            models.add((ArrangementModel) o);
        }
        return models;
    }

    public static List<ArrangementModel> getArrangements(String condition){
        List<ArrangementModel> models = new ArrayList<>();
        String clause = "JOIN api_services on api_arrangement.service_id = api_services.id JOIN api_user on api_arrangement.user_id = api_user.id WHERE "+condition;
        for (Object o : model.getAllData(clause)){
            models.add((ArrangementModel) o);
        }
        return models;
    }

    @Override
    public Object findData(String id) {
        try {
            return this.getAllData("JOIN api_services on api_arrangement.service_id = api_services.id JOIN api_user on api_arrangement.user_id = api_user.id WHERE api_arrangement.id = " + id).get(0);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public String table() {
        return "api_arrangement";
    }

    @Override
    public String[] columns() {
        return new String[]{"id","date","service_id","user_id","status","name","type_service"};
    }
}
