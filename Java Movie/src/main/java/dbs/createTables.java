package dbs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class createTables {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/JMMS"; // 数据库连接地址
        String user = "root"; // 数据库用户名
        String password = "admin123456"; // 数据库密码

        // SQL语句，用于创建数据表


        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String createTableSql = "CREATE TABLE movies (name VARCHAR(255) NOT NULL UNIQUE,length VARCHAR(255),director VARCHAR(255),actor VARCHAR(255), date VARCHAR(255), introduction VARCHAR(255), poster VARCHAR(255));";

            // 执行SQL语句，创建数据表
            statement.executeUpdate(createTableSql);
            System.out.println("Table " + " created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String createTableSql = "INSERT INTO movies (name, length, director, actor, date, introduction, poster) VALUES ('The Shawshank Redemption', '142 min ', 'Frank Darabont', 'Tim Robbins, Morgan Freeman', '1994', 'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.', 'src/main/java/GUI/Picture/Movies/Shawshank.jpg'),('The Godfather', '175 min', 'Francis Ford Coppola', 'Marlon Brando, Al Pacino', '1972', 'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.', 'src/main/java/GUI/Picture/Movies/Godfather.jpg'),('The Dark Knight', '152 min', 'Christopher Nolan', 'Christian Bale, Heath Ledger', '2008', 'When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.', 'src/main/java/GUI/Picture/Movies/The Dark Knight.jpeg'),('Interstellar','169 min','Christopher Nolan','Matthew, Anne Hathaway','2014','A team of astronauts, led by Cooper, who travel through a wormhole in search of a new home for humanity. Along the way, they face numerous obstacles while making difficult choices to save humanity.','src/main/java/GUI/Picture/Movies/Interstellar.jpeg'),('Forrest Gump','142 min','Robert Zemeckis','Tom Hanks, Robin Wright','1994','The presidencies of Kennedy, the events of Vietnam, Watergate and other historical events unfold through the perspective of an Alabama man with an IQ of 75, whose only desire is to be reunited with his childhood sweetheart.','src/main/java/GUI/Picture/Movies/Forrest.jpg');";
            // 执行SQL语句，创建数据表
            statement.executeUpdate(createTableSql);
            System.out.println("Table " + " created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }




        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String createTableSql = "CREATE TABLE admins (admin VARCHAR(255),password VARCHAR(255));";

            // 执行SQL语句，创建数据表
            statement.executeUpdate(createTableSql);
            System.out.println("Table " + " created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String createTableSql = "INSERT INTO admins (admin, password) values ('admin1','password1'),('admin2','password2')";
            // 执行SQL语句，创建数据表
            statement.executeUpdate(createTableSql);
            System.out.println("Table " + " created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String createTableSql = "CREATE TABLE comments (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(255),rate VARCHAR(255),comment VARCHAR(255),time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,age INT,path VARCHAR(255),PRIMARY KEY (id));";

            // 执行SQL语句，创建数据表
            statement.executeUpdate(createTableSql);
            System.out.println("Table " + " created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String createTableSql = "INSERT INTO comments (name, rate, comment, age, path) values ('The Dark Knight','5','The best batman movie ever','18','null'),('The Dark Knight','4','Great','27','null'),('Interstellar','5','Amazing','19','null'),('Interstellar','5','A wonderful movie','28','null'),('The Godfather','5','Classic movie','54','null'),('The Godfather','5','Good old days!','76','null')";
            // 执行SQL语句，创建数据表
            statement.executeUpdate(createTableSql);
            System.out.println("Table " + " created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }




        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String createTableSql = " CREATE TABLE users (username VARCHAR(255),password VARCHAR(255),gender VARCHAR(255),age VARCHAR(255),email VARCHAR(255));";

            // 执行SQL语句，创建数据表
            statement.executeUpdate(createTableSql);
            System.out.println("Table " + " created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String createTableSql = "INSERT INTO users (username, password, gender, age, email) values ('user1','password1','male','20','122090243@link.cuhk.edu.cn')";
            // 执行SQL语句，创建数据表
            statement.executeUpdate(createTableSql);
            System.out.println("Table " + " created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }





    }
}


