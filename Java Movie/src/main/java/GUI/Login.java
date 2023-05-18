package GUI;

import java.sql.*;
import java.util.Date;

public class Login {
   public static boolean LogAsUser(String username_, String password_){
       Connection connection =null;
       Statement statement = null;
       ResultSet resultSet =null;
       String sqlPass = "";
       try {
           Class.forName("com.mysql.jdbc.Driver");

           String url = "jdbc:mysql://localhost:3306/JMMS";
           String username = "root";
           String password = "admin123456";

           connection = DriverManager.getConnection(url, username, password);
           statement=connection.createStatement();
           resultSet = statement.executeQuery("select * from users WHERE username ='"+username_+"'");
           while (resultSet.next()){
               sqlPass = resultSet.getString(2);
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
       if (sqlPass.equals(password_)) return true;
       else return false;
   }

   public static boolean LogAsAdmin(String username_, String password_){
       Connection connection =null;
       Statement statement = null;
       ResultSet resultSet =null;
       String sqlPass = "";
       try {
           Class.forName("com.mysql.jdbc.Driver");

           String url = "jdbc:mysql://localhost:3306/JMMS";
           String username = "root";
           String password = "admin123456";

           connection = DriverManager.getConnection(url, username, password);
           statement=connection.createStatement();
           resultSet = statement.executeQuery("select * from admins WHERE admin ='"+username_+"'");
           while (resultSet.next()){
               sqlPass = resultSet.getString(2);
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
       if (sqlPass.equals(password_)) return true;
       else return false;

   }

}
