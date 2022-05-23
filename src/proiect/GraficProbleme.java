package proiect;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import proiect.Models.Problems;

public class GraficProbleme extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		int i, nr = 0;

		String filepath;
		BufferedReader fisierProbleme = null;
		Gson obj = new Gson();

		filepath = "Database\\probleme.json";
		try {
			fisierProbleme = new BufferedReader(new FileReader(filepath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Problems[] probleme = obj.fromJson(fisierProbleme, Problems[].class);

		for (i = 0; i < probleme.length; i++)
			nr++;

		Pane root = new Pane();
		ObservableList<javafx.scene.chart.PieChart.Data> valueList = FXCollections.observableArrayList();

		for (i = 0; i < probleme.length; i++)
			valueList.add(
					new PieChart.Data(probleme[i].getSistem() + " (" + probleme[i].getComponenta() + ")", 100 / nr));

		// javafx.scene.chart.PieChart pieChart=new
		// javafx.scene.chart.PieChart(valueList);
		PieChart pieChart = new PieChart(valueList);
		pieChart.setTitle("Situatie probleme sisteme");
		pieChart.getData().forEach(data -> {
			String percentage = String.format("%.2f%%", (data.getPieValue()));
			Tooltip toolTip = new Tooltip(percentage);
			Tooltip.install(data.getNode(), toolTip);
		});

		root.getChildren().addAll(pieChart);
		Scene scene = new Scene(root, 450, 450);
		primaryStage.setTitle("Diagrama sisteme returnate");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
