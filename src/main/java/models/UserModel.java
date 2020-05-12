package models;

import helper.Model;

import java.util.ArrayList;
import java.util.List;

public class UserModel extends Model {
    private String id;
    private String telegramId;
    private String name;
    private String urlKtpPhoto;
    private String urlSelfPhoto;
    private String status;
    private static final UserModel model = new UserModel();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(String telegramId) {
        this.telegramId = telegramId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlKtpPhoto() {
        return urlKtpPhoto;
    }

    public void setUrlKtpPhoto(String urlKtpPhoto) {
        this.urlKtpPhoto = urlKtpPhoto;
    }

    public String getUrlSelfPhoto() {
        return urlSelfPhoto;
    }

    public void setUrlSelfPhoto(String urlSelfPhoto) {
        this.urlSelfPhoto = urlSelfPhoto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static List<UserModel> getUserModels(){
        List<UserModel> models = new ArrayList<>();
        for (Object o : model.getAllData("WHERE status = 'verified'")){
            models.add((UserModel) o);
        }
        return models;
    }

    public static List<UserModel> getUserModels(String id){
        List<UserModel> models = new ArrayList<>();
        for (Object o : model.getAllData("WHERE status = 'verified' && id = "+id)){
            models.add((UserModel) o);
        }
        return models;
    }

    public static List<UserModel> getUnregisteredUserModels(){
        List<UserModel> models = new ArrayList<>();
        for (Object o : model.getAllData("WHERE status = 'unverified'")){
            models.add((UserModel) o);
        }
        return models;
    }

    public static List<UserModel> getRejectedUserModels(){
        List<UserModel> models = new ArrayList<>();
        for (Object o : model.getAllData("WHERE status = 'rejected'")){
            models.add((UserModel) o);
        }
        return models;
    }

    public static List<UserModel> getUnregisteredUserModels(String id){
        List<UserModel> models = new ArrayList<>();
        for (Object o : model.getAllData("WHERE status = 'unverified' && id = "+id)){
            models.add((UserModel) o);
        }
        return models;
    }

    @Override
    public String table() {
        return "api_user";
    }

    @Override
    public String[] columns() {
        return new String[]{"id","telegram_id","name","url_ktp_photo","url_self_photo","status"};
    }
}
