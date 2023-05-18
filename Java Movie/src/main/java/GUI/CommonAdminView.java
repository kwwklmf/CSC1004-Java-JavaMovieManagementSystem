package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class CommonAdminView {
    public void show()
    {
        Stage stage = new Stage();
        stage.setMaxWidth(800);
        stage.setMaxHeight(586);
        AnchorPane anchorPane = new AnchorPane();

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

        anchorPane.getChildren().add(movies);
        movies.setLayoutX(45);
        movies.setLayoutY(20);
        movies.setPrefWidth(200);

        Button addButton = new Button("Add a movie");
        anchorPane.getChildren().add(addButton);
        addButton.setLayoutX(50);
        addButton.setLayoutY(350);
        addButton.setOnAction(actionEvent -> {addMovie();});

        Button deleteButton = new Button("Delete a movie");
        anchorPane.getChildren().add(deleteButton);
        deleteButton.setLayoutX(50);
        deleteButton.setLayoutY(400);
        deleteButton.setOnAction(actionEvent -> {deleteMovie(movies.getValue());});

        Button clear = new Button("Clear");
        anchorPane.getChildren().add(clear);
        clear.setLayoutX(50);
        clear.setLayoutY(450);

        Button commentLook = new Button("Look comments");
        anchorPane.getChildren().add(commentLook);
        commentLook.setLayoutX(200);
        commentLook.setLayoutY(400);

        Image imageGif = new Image("file:src/main/java/GUI/Picture/Tom.GIF",0,220,true,true);
        ImageView imageViewGif = new ImageView(imageGif);
        anchorPane.getChildren().add(imageViewGif);
        imageViewGif.setLayoutX(30);
        imageViewGif.setLayoutY(110);

        commentLook.setOnAction(actionEvent -> {

            ArrayList<String> commentMovie = new ArrayList<>();
            Connection connection2 =null;
            Statement statement2 = null;
            ResultSet resultSet2 =null;
            String commentMovieName = movies.getValue();

            try {
                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://localhost:3306/JMMS";
                String username = "root";
                String password = "admin123456";

                connection2 = DriverManager.getConnection(url, username, password);
                statement2=connection2.createStatement();
                resultSet2 = statement2.executeQuery("select * from comments where name ='"+commentMovieName+"'");
                while (resultSet2.next()){

                    String aComment = resultSet2.getString(3)+"  "+resultSet2.getString(4)+"  "+ resultSet2.getString(6);

                    commentMovie.add(aComment);
                }
                connection2.close();
            } catch (Exception e){
                e.printStackTrace();
            }finally {
                if (connection2 != null){
                    try {
                        connection2.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (statement2 != null){
                    try {
                        statement2.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (resultSet2 != null){
                    try {
                        resultSet2.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            ObservableList<String> allComments = FXCollections.observableArrayList(commentMovie);
            ComboBox<String> commentsMovie = new ComboBox<>(allComments);
            anchorPane.getChildren().add(commentsMovie);
            commentsMovie.setLayoutX(45);
            commentsMovie.setLayoutY(70);

            Chart2 chart2 = new Chart2();
            BarChart<String,Number> barChart = chart2.getChartValue(commentMovieName);
            barChart.setPrefSize(330,330);
            anchorPane.getChildren().add(barChart);
            barChart.setLayoutX(430);
            barChart.setLayoutY(110);


            clear.setOnAction(actionEvent2 ->
            {if (anchorPane.getChildren().contains(commentsMovie)){
                anchorPane.getChildren().remove(commentsMovie);
                commentMovie.clear();}
                if (anchorPane.getChildren().contains(barChart)){anchorPane.getChildren().remove(barChart);}
            });
        });





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
        anchorPane.setBackground(background);
        stage.setScene(scene);
        stage.setTitle("Admins Window");
        stage.show();
    }


    public void addMovie()
    {
        Stage stage = new Stage();
        stage.setMaxWidth(400);
        stage.setMaxHeight(450);
        AnchorPane anchorPane = new AnchorPane();

        TextField name = new TextField();
        anchorPane.getChildren().add(name);
        name.setLayoutX(200);
        name.setLayoutY(20);
        Label nameLabel = new Label("Movie name");
        anchorPane.getChildren().add(nameLabel);
        nameLabel.setLayoutX(100);
        nameLabel.setLayoutY(20);

        TextField length = new TextField();
        anchorPane.getChildren().add(length);
        length.setLayoutX(200);
        length.setLayoutY(70);
        Label movieLength = new Label("Length");
        anchorPane.getChildren().add(movieLength);
        movieLength.setLayoutX(100);
        movieLength.setLayoutY(70);


        TextField director = new TextField();
        anchorPane.getChildren().add(director);
        director.setLayoutX(200);
        director.setLayoutY(120);
        Label movieDirector = new Label("Director");
        anchorPane.getChildren().add(movieDirector);
        movieDirector.setLayoutX(100);
        movieDirector.setLayoutY(120);

        TextField actor = new TextField();
        anchorPane.getChildren().add(actor);
        actor.setLayoutX(200);
        actor.setLayoutY(170);
        Label movieActor = new Label("Actors");
        anchorPane.getChildren().add(movieActor);
        movieActor.setLayoutX(100);
        movieActor.setLayoutY(170);

        TextField date = new TextField();
        anchorPane.getChildren().add(date);
        date.setLayoutX(200);
        date.setLayoutY(220);
        Label movieDate = new Label("Date");
        anchorPane.getChildren().add(movieDate);
        movieDate.setLayoutX(100);
        movieDate.setLayoutY(220);

        TextField intro = new TextField();
        anchorPane.getChildren().add(intro);
        intro.setLayoutX(200);
        intro.setLayoutY(270);
        Label movieIntro = new Label("Introduction");
        anchorPane.getChildren().add(movieIntro);
        movieIntro.setLayoutX(100);
        movieIntro.setLayoutY(270);

        TextField poster = new TextField();
        anchorPane.getChildren().add(poster);
        poster.setLayoutX(200);
        poster.setLayoutY(320);
        Label moviePoster = new Label("Poster file");
        anchorPane.getChildren().add(moviePoster);
        moviePoster.setLayoutX(100);
        moviePoster.setLayoutY(320);

        TextField video = new TextField();
        anchorPane.getChildren().add(video);
        video.setLayoutX(200);
        video.setLayoutY(370);
        Label movieVideo = new Label("Video file");
        anchorPane.getChildren().add(movieVideo);
        movieVideo.setLayoutX(100);
        movieVideo.setLayoutY(370);

        Button addButton = new Button("Add");
        anchorPane.getChildren().add(addButton);
        addButton.setLayoutX(200);
        addButton.setLayoutY(400);



        addButton.setOnAction(actionEvent -> {
            String newName = name.getText();
            String newLength = length.getText();
            String newDirector = director.getText();
            String newDate = date.getText();
            String newActor = actor.getText();
            String newIntro = intro.getText();
            String imagePath = poster.getText();
            String videoPath = video.getText();
            // 定义目标文件夹
            String targetFolderPath1 = "src/main/java/GUI/Picture/Movies/";

            try {
                // 读取源文件
                Path source = Paths.get(imagePath);
                // 构造目标文件路径
                String targetPath = targetFolderPath1 + newName + ".jpg";
                // 创建目标文件夹（如果不存在）
                Files.createDirectories(Paths.get(targetFolderPath1));
                // 复制文件到目标文件夹
                Path target = Paths.get(targetPath);
                Files.copy(source, target);
                System.out.println("Image uploaded successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            String targetFolderPath2 = "src/main/java/GUI/Videos/";

            try {
                // 读取源文件
                Path source = Paths.get(videoPath);
                // 构造目标文件路径
                String targetPath = targetFolderPath2 + newName + ".MP4";
                // 创建目标文件夹（如果不存在）
                Files.createDirectories(Paths.get(targetFolderPath2));
                // 复制文件到目标文件夹
                Path target = Paths.get(targetPath);
                Files.copy(source, target);
                System.out.println("Video uploaded successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }


            Connection connection = null;
            Statement statement = null;
            int result = 0;
            try {
                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://localhost:3306/JMMS";
                String username = "root";
                String password = "admin123456";

                connection = DriverManager.getConnection(url, username, password);
                statement=connection.createStatement();

                String sql = "INSERT INTO movies (name, length, director, actor, date, introduction, poster,videos) VALUES ('"+newName+"','"+newLength+"','"+newDirector+"','"+newActor+"','"+newDate+"','"+newIntro+"','"+targetFolderPath1+newName+".jpg','"+targetFolderPath2+newName+".MP4')";
                result = statement.executeUpdate(sql);


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

            }
            if (result==0) System.out.println("failed");
            else System.out.println("success");
        });
        Scene scene = new Scene(anchorPane,960,586);
        stage.setScene(scene);
        stage.setTitle("Add Movies");
        stage.show();


    }

    public void deleteMovie(String movie)
    {
        Connection connection =null;
        Statement statement = null;
        ResultSet resultSet =null;
        int result = 0;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/JMMS";
            String username = "root";
            String password = "admin123456";

            connection = DriverManager.getConnection(url, username, password);
            statement=connection.createStatement();
            resultSet = statement.executeQuery("select * from movies WHERE name='"+movie+"'");
            while (resultSet.next()){
                String deletePath1 = resultSet.getString(7);
                String deletePath2 = resultSet.getString(9);
                File file1 = new File(deletePath1);
                File file2 = new File(deletePath2);
                file1.delete();
                file2.delete();
            }

            String sql = "DELETE from movies where name = '"+movie+"'";
            result = statement.executeUpdate(sql);

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
    }
}
