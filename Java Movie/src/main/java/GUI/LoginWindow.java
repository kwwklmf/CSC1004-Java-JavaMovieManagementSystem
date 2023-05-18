package GUI;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class LoginWindow {
    Label userNameLabel = new Label("User Name");
    Label passwordLabel = new Label("Password");
    TextField userText = new TextField();
    PasswordField passwordField = new PasswordField();
    Button UserLogin = new Button("Login as a User");
    Button AdminLogin = new Button("Login as an Admin");
    Button Registry = new Button("Registry");
    public String userName;
    public String password;


    public void show() {
        // 创建一个新的 Stage 对象
        Stage stage = new Stage();
        stage.setMaxHeight(586);
        stage.setMaxWidth(960);

        // 创建一个场景
        AnchorPane anchorPane = new AnchorPane();
        GridPane gridPaneUp = new GridPane();
        anchorPane.getChildren().add(gridPaneUp);
        gridPaneUp.setHgap(50);
        gridPaneUp.setVgap(50);
        gridPaneUp.setPadding(new Insets(10,10,10,10));
        GridPane gridPane = new GridPane();
        gridPaneUp.add(gridPane,3,2);
        gridPane.setHgap(50);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.add(userNameLabel,0,0);
        gridPane.add(userText,1,0);
        gridPane.add(passwordLabel,0,1);
        gridPane.add(passwordField,1,1);
        gridPane.add(UserLogin,0,2);
        gridPane.add(AdminLogin,1,2);
        gridPane.add(Registry,0,4);
        Image imageGif = new Image("file:src/main/java/GUI/Picture/IMG_4559.GIF",0,100,true,true);
        ImageView imageViewGif = new ImageView(imageGif);
        gridPane.add(imageViewGif,0,5);
        Scene scene = new Scene(anchorPane,600,400);
        Image image = new Image("file:src/main/java/GUI/Picture/LoginPic2.jpeg");
        // 创建一个BackgroundImage对象，使用图片作为背景
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        UserLogin.setOnAction(actionEvent -> {
            userName = userText.getText();
            password = passwordField.getText();
            boolean state = Login.LogAsUser(userName,password);
            if (state) {CommonUserView commonUserView = new CommonUserView();
                try {
                    commonUserView.show();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            };

        });

        AdminLogin.setOnAction(actionEvent -> {
            userName = userText.getText();
            password = passwordField.getText();
            boolean state = Login.LogAsAdmin(userName,password);
            if (state) {CommonAdminView commonAdminView = new CommonAdminView();commonAdminView.show();};
        });

        Registry.setOnAction(actionEvent -> {
            RegisterWindow registerWindow = new RegisterWindow();
            registerWindow.show();
        });
        // 创建一个背景对象，将背景图像设置为窗口的背景
        Background background = new Background(backgroundImage);
        anchorPane.setBackground(background);

        // 设置场景和标题
        stage.setScene(scene);
        stage.setTitle("Login Window");

        // 显示窗口
        Platform.runLater(stage::show);
    }

}
