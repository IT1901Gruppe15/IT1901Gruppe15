package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class GUITestController {

	@FXML private BorderPane root;
	@FXML private Pane loginScreen;
	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;
	@FXML private Pane koieListe;
	@FXML private HBox toolbar;
	@FXML private ImageView image;

	public void initialize() {
		root.setCenter(loginScreen);
		image.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				if (me.getButton() == MouseButton.PRIMARY) {
					System.out.println("Clicked on map");
				}
			}
		});
	}
	
	@FXML
	public void test(ActionEvent event) {
		System.out.println("test " + event);
	}

	@FXML
	public void newUser(ActionEvent event) {
		//root.getChildren().clear();
	}

	@FXML
	public void logIn(ActionEvent event) throws IOException {
		if (/*SQL query returns true*/ true) {
			root.getChildren().clear();
			root.setLeft(koieListe);
			root.setCenter(image);
			root.setTop(toolbar);
		}
	}

}
