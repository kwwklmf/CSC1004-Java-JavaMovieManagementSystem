package GUI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    public void start(Stage stage) {
        // 创建柱状图的X轴和Y轴
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // 创建柱状图并设置X轴和Y轴
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        // 设置柱状图的标题和X轴、Y轴标签
        barChart.setTitle("Sales Report");
        xAxis.setLabel("Month");
        yAxis.setLabel("Sales");

        // 创建柱状图的数据
        ArrayList<XYChart.Series<String, Number>> data = new ArrayList<>();
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Product 1");
        series1.getData().add(new XYChart.Data<>("Jan", 100));
        series1.getData().add(new XYChart.Data<>("Feb", 150));
        series1.getData().add(new XYChart.Data<>("Mar", 200));
        series1.getData().add(new XYChart.Data<>("Apr", 250));
        series1.getData().add(new XYChart.Data<>("May", 300));
        data.add(series1);

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Product 2");
        series2.getData().add(new XYChart.Data<>("Jan", 50));
        series2.getData().add(new XYChart.Data<>("Feb", 75));
        series2.getData().add(new XYChart.Data<>("Mar", 100));
        series2.getData().add(new XYChart.Data<>("Apr", 125));
        series2.getData().add(new XYChart.Data<>("May", 150));
        data.add(series2);

        // 将ArrayList转换为ObservableList并将数据设置到柱状图上
        ObservableList<XYChart.Series<String, Number>> observableData = FXCollections.observableArrayList(data);
        barChart.setData(observableData);

        // 创建场景并将柱状图添加到场景上
        Scene scene = new Scene(barChart, 800, 600);

        // 显示场景
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
