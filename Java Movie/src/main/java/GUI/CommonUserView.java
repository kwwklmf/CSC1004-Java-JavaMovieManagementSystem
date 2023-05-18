package GUI;


import dbs.Movie;
import dbs.Movies;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
public class CommonUserView {

    ImageView poster = new ImageView();

    TextField rate = new TextField("Rate in Integer from 1 to 5");
    TextField comment = new TextField("Write your comment here");
    TextField age = new TextField("Your age");
    TextField picture = new TextField("Picture Path");
    Button submit = new Button("Submit");


    Label infoName = new Label("Information: ");
    Label length = new Label("Choose a movie");
    Label director = new Label("Choose a movie");
    Label actors = new Label("Choose a movie");
    Label date = new Label("Choose a movie");
    Label intro = new Label("Choose a movie");

    public void show() throws FileNotFoundException {

        String videoPath = "src/main/java/GUI/Videos/Video1.MP4"; // 视频文件的路径

        // 创建Media对象，指定视频文件的路径
        Media media = new Media(new File(videoPath).toURI().toString());

        // 创建MediaPlayer对象，加载并准备视频
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true); // 设置自动播放

        // 创建MediaView对象，用于显示视频
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setVolume(0.0);




        ArrayList<String> allMovies = new ArrayList<>();
        Connection connection =null;
        Statement statement = null;
        ResultSet resultSet =null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/JMMS";
            String username = "root";
            String password = "admin123456";

            connection = DriverManager.getConnection(url, username, password);
            statement=connection.createStatement();
            resultSet = statement.executeQuery("select name from movies");
            while (resultSet.next()){

                String aMovie = resultSet.getString(1);

                allMovies.add(aMovie);
            }
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        ObservableList<String> allMovieName = FXCollections.observableArrayList(allMovies);
        ComboBox<String> movies = new ComboBox<>(allMovieName);


        Stage stage = new Stage();
        stage.setMaxWidth(960);
        stage.setMaxHeight(586);
        AnchorPane anchorPane = new AnchorPane();

        anchorPane.getChildren().add(poster);
        Image image1 = new Image("file:src/main/java/GUI/Picture/Welcome.jpg",0,500,true,true);
        poster.setImage(image1);
        poster.setLayoutX(300);
        poster.setLayoutY(20);

        anchorPane.getChildren().add(movies);
        movies.setLayoutX(45);
        movies.setLayoutY(20);
        movies.setPrefWidth(200);

        anchorPane.getChildren().add(infoName);
        infoName.setFont(new Font("Arial", 25));
        infoName.setLayoutX(20);
        infoName.setLayoutY(80);

        anchorPane.getChildren().add(length);
        length.setFont(new Font("Arial", 20));
        length.setLayoutX(25);
        length.setLayoutY(120);

        anchorPane.getChildren().add(director);
        director.setFont(new Font("Arial", 20));
        director.setLayoutX(25);
        director.setLayoutY(160);

        Image imageGif = new Image("file:src/main/java/GUI/Picture/Tom.GIF",0,70,true,true);
        ImageView imageViewGif = new ImageView(imageGif);
        anchorPane.getChildren().add(imageViewGif);
        imageViewGif.setLayoutX(180);
        imageViewGif.setLayoutY(70);


        anchorPane.getChildren().add(actors);
        actors.setFont(new Font("Arial", 20));
        actors.setLayoutX(25);
        actors.setLayoutY(200);
        actors.setPrefWidth(250);
        actors.setWrapText(true);


        anchorPane.getChildren().add(date);
        date.setFont(new Font("Arial", 20));
        date.setLayoutX(25);
        date.setLayoutY(260);

        anchorPane.getChildren().add(intro);
        intro.setFont(new Font("Arial", 20));
        intro.setLayoutX(25);
        intro.setLayoutY(300);
        intro.setPrefWidth(250);
        intro.setWrapText(true);

        anchorPane.getChildren().add(rate);
        rate.setLayoutX(750);
        rate.setLayoutY(350);

        anchorPane.getChildren().add(comment);
        comment.setLayoutX(750);
        comment.setLayoutY(400);

        anchorPane.getChildren().add(age);
        age.setLayoutX(750);
        age.setLayoutY(450);

        anchorPane.getChildren().add(picture);
        picture.setLayoutX(750);
        picture.setLayoutY(300);

        anchorPane.getChildren().add(submit);
        submit.setLayoutX(750);
        submit.setLayoutY(500);

        anchorPane.getChildren().add(mediaView);
        mediaView.setFitHeight(270);
        mediaView.setLayoutX(750);
        mediaView.setLayoutY(20);



        Scene scene = new Scene(anchorPane,960,586);
        Image image = new Image("file:src/main/java/GUI/Picture/UserBack.jpeg");
        // 创建一个BackgroundImage对象，使用图片作为背景
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        anchorPane.setBackground(background);
        anchorPane.setBackground(background);//Background

        //Choose and comment movies
        movies.getSelectionModel().selectedIndexProperty().addListener((observable,oldValue,newValue)  -> {
            String selectedMovie = movies.getValue();
            Movie movie = Movies.chooseMovie(selectedMovie);
            length.setText("Time: " +movie.getLength()+ " min");
            director.setText("Director: "+movie.getDirector());
            actors.setText("Actors: "+movie.getActor());
            date.setText("Date: "+movie.getDate());
            intro.setText("Introduction: "+movie.getInformation());
            Image image2 = new Image("file:"+movie.getPoster(),0,500,true,true);
            poster.setImage(image2);
            Media media2 = new Media(new File(movie.getVideos()).toURI().toString());

            // 创建MediaPlayer对象，加载并准备视频
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            mediaPlayer2.setVolume(0.0);
            mediaPlayer2.setAutoPlay(true); // 设置自动播放
            // 创建MediaView对象，用于显示视频
            mediaView.setMediaPlayer(mediaPlayer2);



            submit.setOnAction(actionEvent -> {

                String movieName = movie.getMovieName();
                Integer movieRate = Integer.parseInt(rate.getText());
                Integer userAge = Integer.parseInt(age.getText());
                String movieComment = comment.getText();
                String imagePath = picture.getText();
                // 定义目标文件夹
                String targetFolderPath = "src/main/java/GUI/Picture/Comments/";

                try {
                    // 读取源文件
                    Path source = Paths.get(imagePath);

                    // 构造目标文件路径
                    String targetPath = targetFolderPath + source.getFileName().toString();

                    // 创建目标文件夹（如果不存在）
                    Files.createDirectories(Paths.get(targetFolderPath));

                    // 复制文件到目标文件夹
                    Path target = Paths.get(targetPath);
                    Files.copy(source, target);

                    System.out.println("Image uploaded successfully.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int i = Movies.commentMovies(movieName,movieRate,movieComment,userAge,targetFolderPath);
                if (i==0) System.out.println("Comment Unsuccessfully");
                else System.out.println("Comment Successfully");
            });
        });


        stage.setScene(scene);
        stage.setTitle("Users Window");
        stage.show();


    }
}
