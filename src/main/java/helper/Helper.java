package helper;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Helper {
    public static void changePage(ActionEvent event, String page){
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(Helper.class.getResource("../views/"+page+".fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeAnchorPaneScene(ActionEvent event, String scene){
        Node node = (Node) event.getSource();
        AnchorPane pane = (AnchorPane) node.getParent();
        try {
            pane.getChildren().setAll((Node) FXMLLoader.load(Helper.class.getResource("../views/"+scene+".fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
