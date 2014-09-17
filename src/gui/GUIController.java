package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class GUIController {

	@FXML private BorderPane root;
	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;
	@FXML private TextField regUsernameField;
	@FXML private PasswordField regPasswordField;
	@FXML private PasswordField regPasswordFieldConfirmation;
	@FXML private Pane loginScreen;
	@FXML private Pane registerScreen;
	@FXML private Pane koieListe;
	@FXML private Pane toolbar;
	@FXML private Pane mapPane;
	@FXML private Pane welcomePane;
	@FXML private Pane koiePane;
	@FXML private Text activeKoieName;
	private String activeKoie;
	private Button mapBtn;

	public void initialize() {
		mapBtn = new Button();
		mapBtn.setFocusTraversable(false);
		mapBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				activeKoieName.setText(activeKoie.substring(0, activeKoie.length() - 3));
				root.setCenter(koiePane);
			}
		});
		root.setCenter(loginScreen);
		mapPane.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				if (me.getButton() == MouseButton.PRIMARY) {
					activeKoie = ((Node) me.getTarget()).getId();
					if (activeKoie == null || activeKoie == mapPane.getId()) {
						mapPane.getChildren().remove(mapBtn);
						return;
					}
					double koieX = ((ImageView) me.getTarget()).getLayoutX();
					double koieY = ((ImageView) me.getTarget()).getLayoutY();
					mapBtn.setText("Åpne " + activeKoie.substring(0, activeKoie.length() - 3));
					mapBtn.setLayoutX(koieX + 25);
					mapBtn.setLayoutY(koieY - 25);
					mapPane.getChildren().remove(mapBtn);
					mapPane.getChildren().add(mapBtn);
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
		((Hyperlink) event.getSource()).setVisited(false);
		activeKoie = ((Hyperlink) event.getSource()).getText();
		activeKoieName.setText(activeKoie);
		root.setCenter(koiePane);
	}

	@FXML
	public void openWelcome(ActionEvent event) {
		root.setCenter(welcomePane);
	}

	@FXML
	public void openMap(ActionEvent event) {
		mapPane.getChildren().remove(mapBtn);
		root.setCenter(mapPane);
	}

	@FXML
	public void newUser(ActionEvent event) {
		root.setCenter(registerScreen);
	}

	@FXML
	public void register(ActionEvent event) {
		if (/*username does not exist*/ true && regPasswordField.getText().equals(regPasswordFieldConfirmation.getText())) {
			System.out.println("success!!");
			logOut(event);
		}
	}

	@FXML
	public void logOut(ActionEvent event) {
		usernameField.clear();
		passwordField.clear();
		regUsernameField.clear();
		regPasswordField.clear();
		regPasswordFieldConfirmation.clear();
		root.getChildren().clear();
		root.setCenter(loginScreen);
	}

	@FXML
	public void logIn(ActionEvent event) {
		if (/*SQL query returns true*/ true) {
			root.setLeft(koieListe);
			root.setCenter(welcomePane);
			root.setTop(toolbar);
		}
	}

}
