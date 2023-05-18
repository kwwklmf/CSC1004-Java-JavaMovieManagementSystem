package dbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersChange {
    public int insert(Users user){
        String username_= user.getUsername();
        String password_=user.getPassword();
        String gender_ =user.getGender();
        Integer age_ = user.getAge();
        String email_ =user.getEmail();
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

            //String sql = "INSERT INTO customers (first_name, last_name, birth_date, phone, address, city, state, points) VALUES ('Dabian','Kong',null,null,'2001 Longxiang Blv','Shenzhen','MA','0602')";

            String sql = "INSERT INTO users (username, password, gender, age, email) VALUES ('"+username_+"', '"+password_+"', '"+gender_+"', "+age_+", '"+email_+"')";
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
        return result;


    }
}
