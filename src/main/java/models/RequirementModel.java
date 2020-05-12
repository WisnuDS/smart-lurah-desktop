package models;

import helper.Model;

import java.util.ArrayList;
import java.util.List;

public class RequirementModel extends Model {
    private String id;
    private String nameRequirement;
    private static final RequirementModel model = new RequirementModel();

    public RequirementModel() {
    }

    public RequirementModel(String id, String nameRequirement) {
        this.id = id;
        this.nameRequirement = nameRequirement;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameRequirement() {
        return nameRequirement;
    }

    public void setNameRequirement(String nameRequirement) {
        this.nameRequirement = nameRequirement;
    }

    public static List<RequirementModel> getAllRequirement(String clause){
        List<RequirementModel> models = new ArrayList<>();
        for (Object o : model.getAllData(clause)){
            models.add((RequirementModel) o);
        }
        return models;
    }


    @Override
    public String table() {
        return "api_servicerequirements";
    }

    @Override
    public String[] columns() {
        return new String[]{"id","name_requirement"};
    }
}
