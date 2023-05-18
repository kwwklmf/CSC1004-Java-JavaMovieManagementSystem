
import GUI.CommonAdminView;
import GUI.CommonUserView;
import GUI.LoginWindow;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.show();
        //CommonUserView commonUserView = new CommonUserView();
        //commonUserView.show();
        //CommonAdminView commonAdminView = new CommonAdminView();
        //commonAdminView.show();

    }

}

