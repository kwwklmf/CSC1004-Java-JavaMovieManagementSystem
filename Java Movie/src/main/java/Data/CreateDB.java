package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreateDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sql_store";
        //URL：连接的路径。语法：jdbc:mysql://+ip地址(域名):端口号/数据库名称
        String username = "root";
        //mysql用户名
        String password = "admin123456";
        //mysql密码
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//可省略

            Connection connection = DriverManager.getConnection(url, username, password);
            //链接数据库

            Statement statement=connection.createStatement();
            //DML语句：int executeUpdate(String sql);
            //返回int：影响的行数
            //sql：执行的语句 insert update delete 等
            //DQL语句：Resultset executeQuery(String sql)
            //Resultset:
            //sql: select语句

            ResultSet resultSet = statement.executeQuery("select *from customers ");
            //resultset.next() 下一行是否有数据
            //resultset.getxxx(数据类型)（列名）

            while (resultSet.next()){
                System.out.println(resultSet.getString(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getString(4)+" "+resultSet.getString(5)+" "+resultSet.getString(6)+" "+resultSet.getString(7)+" "+resultSet.getString(8)+" "+resultSet.getString(9));
            }


            connection.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
}


