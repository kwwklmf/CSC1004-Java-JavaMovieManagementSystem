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

public class Chart extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 创建横轴和纵轴
        Chart2 chart2 = new Chart2();
        BarChart<String,Number> barChart = chart2.getChartValue("The Dark Knight");

        // 创建一个Scene，并将柱状图添加到其中
        Scene scene = new Scene(barChart, 400, 300);

        primaryStage.setTitle("Score Statistics");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
