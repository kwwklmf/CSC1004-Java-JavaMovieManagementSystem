package dbs;

import java.sql.*;
import java.util.Date;
import java.util.Iterator;

public class Movies {
    public static Movie chooseMovie(String chosenMovie) {
        Movie movie = new Movie();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/JMMS";
            String username = "root";
            String password = "admin123456";

            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from movies WHERE name ='" + chosenMovie + "'");
            while (resultSet.next()) {

                movie.setMovieName(resultSet.getString(1));
                movie.setLength(resultSet.getString(2));
                movie.setDirector(resultSet.getString(3));
                movie.setActor(resultSet.getString(4));
                movie.setDate(resultSet.getString(5));
                movie.setInformation(resultSet.getString(6));
                movie.setPoster(resultSet.getString(7));
                movie.setVideos(resultSet.getString(9));

            }


            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


        }
    return movie;
    }



    public static int commentMovies(String name, Integer rate, String comment,Integer age,String path)
    {
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

            String sql = "INSERT INTO comments (name, rate, comment,age,path) VALUES ('"+name+"','"+rate+"','"+comment+"','"+age+"','"+path+"')";
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

    return result;}
}
