package GUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Chart2 {
    public BarChart getChartValue(String movieName) {
        // 用于测试的示例数据

        Connection connection =null;
        Statement statement = null;
        ResultSet resultSet =null;
        Map<Integer, Double> data = new HashMap<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/JMMS";
            String username = "root";
            String password = "admin123456";

            connection = DriverManager.getConnection(url, username, password);
            statement=connection.createStatement();
            resultSet = statement.executeQuery("select * from comments WHERE name='"+movieName+"'");
            while (resultSet.next()){

                int rateInt = resultSet.getInt(3);
                Double rate = (double) rateInt;
                int age = resultSet.getInt(6);
                data.put(age,rate);
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

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // 创建柱状图
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Score Statistics");

        // 创建数据系列
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Average Score");

        // 创建年龄区间和平均分数据



        // 定义年龄区段范围
        int startAge = 5;
        int endAge = 70;
        int ageRange = 15;

        // 计算年龄区段的平均分
        Map<String, Double> result = calculateAverageRating(data, startAge, endAge, ageRange);
        ObservableList<XYChart.Data<String, Number>> data2 = FXCollections.observableArrayList(
                new XYChart.Data<>("5-19", result.get("5-19")),
                new XYChart.Data<>("20-34", result.get("20-34")),
                new XYChart.Data<>("35-49", result.get("35-49")),
                new XYChart.Data<>("50-64", result.get("50-64")),
                new XYChart.Data<>("65-79", result.get("65-79"))
        );

        // 将数据添加到数据系列中
        series.getData().addAll(data2);

        // 将数据系列添加到柱状图中
        barChart.getData().add(series);
        return barChart;
    }

    public static Map<String, Double> calculateAverageRating(Map<Integer, Double> data, int startAge, int endAge, int ageRange) {
        Map<String, Double> result = new HashMap<>();
        int ageGroupCount = (endAge - startAge) / ageRange;

        for (int i = 0; i <= ageGroupCount; i++) {
            int currentStartAge = startAge + i * ageRange;
            int currentEndAge = currentStartAge + ageRange - 1;

            double sum = 0;
            int count = 0;

            for (Map.Entry<Integer, Double> entry : data.entrySet()) {
                int age = entry.getKey();
                double rating = entry.getValue();

                if (age >= currentStartAge && age <= currentEndAge) {
                    sum += rating;
                    count++;
                }
            }

            double average = count > 0 ? sum / count : 0;
            String ageRangeString = currentStartAge + "-" + currentEndAge;
            result.put(ageRangeString, average);
        }

        return result;
    }
}
