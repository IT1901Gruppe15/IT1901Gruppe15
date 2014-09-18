package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {  // f�r programmet til � dukke opp p� skjermen
										// ikke mye � se her...
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(this.getClass().getResource("GUI.fxml"));
		primaryStage.setScene(new Scene(root, 1280, 720));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
