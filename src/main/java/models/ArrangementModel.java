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
    private boolean verification;
    private boolean finished;
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

    public boolean isVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = !verification.equals("0");
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = !finished.equals("0");
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

    public static List<ArrangementModel> getArrangements(){
        List<ArrangementModel> models = new ArrayList<>();
        String clause = "JOIN api_services on api_arrangement.service_id = api_services.id JOIN api_user on api_arrangement.user_id = api_user.id";
        for (Object o : model.getAllData(clause)){
            models.add((ArrangementModel) o);
        }
        return models;
    }

    @Override
    public String table() {
        return "api_arrangement";
    }

    @Override
    public String[] columns() {
        return new String[]{"id","date","verification","service_id","user_id","finished","name","type_service"};
    }
}
