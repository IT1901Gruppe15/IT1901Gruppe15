package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GUI extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(this.getClass().getResource("GUI.fxml"));
		primaryStage.getIcons().add(new Image("file:res/ntnuiIkon.png"));
		primaryStage.setTitle("NTNUI Koiesystem");
		//primaryStage.setMaximized(true);
		primaryStage.setMinWidth(950);
		primaryStage.setMinHeight(750);
		primaryStage.setScene(new Scene(root, 1280, 720));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
