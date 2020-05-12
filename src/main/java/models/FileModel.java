package models;

import helper.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileModel extends Model {
    private String id;
    private String urlFile;
    private String arrangementId;
    private String requirementId;
    private String nameRequirement;
    private static final FileModel model = new FileModel();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlFile() {
        return urlFile;
    }

    public void setUrlFile(String urlFile) {
        this.urlFile = urlFile;
    }

    public String getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(String arrangementId) {
        this.arrangementId = arrangementId;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }

    public String getNameRequirement() {
        return nameRequirement;
    }

    public void setNameRequirement(String nameRequirement) {
        this.nameRequirement = nameRequirement;
    }

    public static List<FileModel> getAllFile(String id){
        List<FileModel> models = new ArrayList<>();
        for (Object o : model.getAllData("JOIN api_servicerequirements on api_filerequirement.requirement_id = api_servicerequirements.id WHERE arrangement_id = "+id)){
            models.add((FileModel) o);
        }
        return models;
    }

    @Override
    public String table() {
        return "api_filerequirement";
    }

    @Override
    public String[] columns() {
        return new String[]{"id","url_file","arrangement_id","requirement_id","name_requirement"};
    }
}
