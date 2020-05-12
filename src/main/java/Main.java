import helper.BotHendler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
//        Parent parent = FXMLLoader.load(getClass().getResource("views/login_view.fxml"));
//        stage.setTitle("Smart Lurah");
//        stage.setScene(new Scene(parent));
//        stage.show();
        BotHendler.sendMessage("Test");
    }
}

