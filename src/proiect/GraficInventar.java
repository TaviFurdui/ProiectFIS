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
import proiect.Models.Sistems;

public class GraficInventar extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		int i, nr = 0;

		String filepath;
		BufferedReader fisierSisteme = null;
		Gson obj = new Gson();

		filepath = "Database\\sisteme.json";
		try {
			fisierSisteme = new BufferedReader(new FileReader(filepath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Sistems[] sistem = obj.fromJson(fisierSisteme, Sistems[].class);

		for (i = 0; i < sistem.length; i++)
			nr += sistem[i].getCantitate_inventar();

		Pane root = new Pane();
		ObservableList<javafx.scene.chart.PieChart.Data> valueList = FXCollections.observableArrayList();

		for (i = 0; i < sistem.length; i++)
			valueList.add(new PieChart.Data(sistem[i].getNume(), 100 * sistem[i].getCantitate_inventar() / nr));

		// javafx.scene.chart.PieChart pieChart=new
		// javafx.scene.chart.PieChart(valueList);
		PieChart pieChart = new PieChart(valueList);
		pieChart.setTitle("Situatie inventar sisteme");
		pieChart.getData().forEach(data -> {
			String percentage = String.format("%,2f%%", (data.getPieValue()));
			Tooltip toolTip = new Tooltip(percentage);
			Tooltip.install(data.getNode(), toolTip);
		});

		root.getChildren().addAll(pieChart);
		Scene scene = new Scene(root, 450, 450);
		primaryStage.setTitle("Diagrama sisteme");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
