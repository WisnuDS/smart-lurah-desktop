package models;

import helper.Model;

import java.util.ArrayList;
import java.util.List;

public class AdminModel extends Model {
    private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private static final AdminModel model = new AdminModel();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String table() {
        return "auth_user";
    }

    @Override
    public String[] columns() {
        return new String[]{"id","username","email","first_name","last_name"};
    }

    public static List<AdminModel> getUserModels(){
        List<AdminModel> models = new ArrayList<>();
        for (Object o : model.getAllData("")){
            models.add((AdminModel) o);
        }
        return models;
    }
}
