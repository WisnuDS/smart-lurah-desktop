package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import helper.BotHendler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import models.UserModel;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class BroadcastController {

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXTextField txtTitle;

    @FXML
    private TextArea txtMessage;

    @FXML
    private JFXButton btnSend;

    @FXML
    void sendMessage(ActionEvent event) {
        JFXDialogLayout content = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(stackPane,content, JFXDialog.DialogTransition.CENTER);
        content.setHeading(new Text("Konfirmasi Pegiriman"));
        content.setBody(new Text("Apakah anda yakin akan mengirim pesan ke seluruh pengguna bot?"));
        JFXButton btnYes = new JFXButton("Kirim");
        JFXButton btnNo = new JFXButton("Batal");
        btnYes.setTextFill(Paint.valueOf("#ffffff"));
        btnYes.setButtonType(JFXButton.ButtonType.FLAT);
        btnYes.setBackground(new Background(new BackgroundFill(Paint.valueOf("#4e73df"), CornerRadii.EMPTY,null)));
        btnYes.setCursor(Cursor.HAND);
        btnNo.setCursor(Cursor.HAND);
        btnNo.setOnAction(event1 -> dialog.close());
        btnYes.setOnAction(event1 -> {
            String message = "*[["+ StringUtils.upperCase(txtTitle.getText())+"]]*\n\n"+txtMessage.getText()+"\n\n TTD\nLurah";
            List<String> chatId = new ArrayList<>();
            List<UserModel> users = UserModel.getUserModels();
            for (UserModel user : users){
                chatId.add(user.getTelegramId());
            }
            BotHendler.sendMultipleChat(chatId,message);
            txtTitle.setText("");
            txtMessage.setText("");
            dialog.close();
        });
        content.setActions(btnNo,btnYes);
        dialog.show();
    }

}
