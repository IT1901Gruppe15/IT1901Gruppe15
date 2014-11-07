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
		final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("GUI.fxml"));
		Parent root = loader.load();
		final GUIController controller = loader.getController();
		controller.initStengDBListener(primaryStage);
		//primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("ntnuiIkon.png")));
		primaryStage.getIcons().add(new Image("gui/res/ntnuiIkon.png"));
		primaryStage.setTitle("NTNUI Koiesystem");
		primaryStage.setMinWidth(950);
		primaryStage.setMinHeight(750);
		primaryStage.setScene(new Scene(root, 1280, 720));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
