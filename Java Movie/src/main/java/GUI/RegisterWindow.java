package GUI;

import dbs.Users;
import dbs.UsersChange;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RegisterWindow {
    Label usernameLabel = new Label("Username:");
    Label passwordLabel = new Label("Password:");
    Label genderLabel = new Label("Gender:");
    Label ageLabel = new Label("Age:");
    Label emailLabel = new Label("Email:");
    Label state = new Label();

    TextField TextUser = new TextField();
    TextField TextPassword = new TextField();
    TextField TextGender = new TextField();
    TextField TextAge = new TextField();
    TextField TextEmail = new TextField();
    String username;
    String password;
    String gender;
    Integer age;
    String email;
    Button registerButton = new Button("Register!");

    public void show(){
        Stage stage = new Stage();
        stage.setMaxHeight(586);
        stage.setMaxWidth(960);

        GridPane gridPaneUp = new GridPane();
        gridPaneUp.setHgap(50);
        gridPaneUp.setVgap(50);
        gridPaneUp.setPadding(new Insets(10,10,10,10));
        GridPane gridPane = new GridPane();
        gridPaneUp.add(gridPane,3,2);
        gridPane.setHgap(50);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.add(usernameLabel,0,0);
        gridPane.add(TextUser,1,0);
        gridPane.add(passwordLabel,0,1);
        gridPane.add(TextPassword,1,1);
        gridPane.add(genderLabel,0,2);
        gridPane.add(TextGender,1,2);
        gridPane.add(ageLabel,0,3);
        gridPane.add(TextAge,1,3);
        gridPane.add(emailLabel,0,4);
        gridPane.add(TextEmail,1,4);
        gridPane.add(registerButton,1,5);
        gridPane.add(state,1,6);
        Scene scene = new Scene(gridPaneUp,600,400);

        Image imageGif = new Image("file:src/main/java/GUI/Picture/IMG_4559.GIF",0,100,true,true);
        ImageView imageViewGif = new ImageView(imageGif);
        gridPane.add(imageViewGif,0,5);

        registerButton.setOnAction(actionEvent -> {
            username = TextUser.getText();
            password = TextPassword.getText();
            gender = TextGender.getText();
            age = Integer.parseInt(TextAge.getText());
            email = TextEmail.getText();
            Users users = new Users(username,password,gender,age,email);
            UsersChange insertUser = new UsersChange();//Add new User
            int i = insertUser.insert(users);
            if (i != 0) state.setText("Register Successfully");
            else state.setText("Register Failed");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                stage.close();
            }));
            timeline.play();
        });


        Image image = new Image("file:src/main/java/GUI/Picture/LoginPic2.jpeg");
        // 创建一个BackgroundImage对象，使用图片作为背景
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        stage.setTitle("Register Window");

        Background background = new Background(backgroundImage);
        gridPaneUp.setBackground(background);

        // 显示窗口
        stage.setScene(scene);
        Platform.runLater(stage::show);

    }
}
