package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class GUIController {

	@FXML private BorderPane root;
	@FXML private Pane loginScreen;
	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;
	@FXML private Pane koieListe;
	@FXML private Pane toolbar;
	@FXML private Pane mapPane;
	@FXML private Pane welcomePane;
	@FXML private Pane koiePane;
	@FXML private String activeKoie;
	@FXML private Text activeKoieName;

	public void initialize() {
		root.setCenter(loginScreen);
		mapPane.setOnMousePressed(new EventHandler<MouseEvent>() {
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
	public void koieClicked(ActionEvent event) {
		activeKoie = ((Hyperlink) event.getSource()).getText();
		activeKoieName.setText(activeKoie);
		if (activeKoie.equals("Flåkoia")) {
			System.out.println("woho");
		}
		root.setCenter(koiePane);
	}

	@FXML
	public void newUser(ActionEvent event) {
		//root.getChildren().clear();
	}
	
	@FXML
	public void koie1(ActionEvent event) {
		System.out.println("koie1");
	}
	
	 @FXML
	 public void openWelcome(ActionEvent event) {
		 root.setCenter(welcomePane);
	 }
	 
	 @FXML
	 public void openMap(ActionEvent event) {
		 root.setCenter(mapPane);
	 }
	 
	 @FXML
	 public void logOut(ActionEvent event) {
		 root.getChildren().clear();
		 root.setCenter(loginScreen);
	 }

	@FXML
	public void logIn(ActionEvent event) {
		if (/*SQL query returns true*/ true) {
			usernameField.clear();
			passwordField.clear();
			root.getChildren().clear();
			root.setLeft(koieListe);
			root.setCenter(welcomePane);
			root.setTop(toolbar);
		}
	}

}
