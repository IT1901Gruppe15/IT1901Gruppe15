package gui;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import klasser.Admin;
import klasser.DBConnection;

public class GUIController {

	@FXML private BorderPane root; // �verste element i gui-hierarkiet
	@FXML private TextField usernameField; // tekstfeltene for login og registrering
	@FXML private PasswordField passwordField;
	@FXML private TextField regUsernameField;
	@FXML private PasswordField regPasswordField;
	@FXML private PasswordField regPasswordFieldConfirmation;
	@FXML private Pane loginScreen; // root fylles av Panes
	@FXML private Pane registerScreen;
	@FXML private Pane koieListe;
	@FXML private Pane toolbar;
	@FXML private Pane mapPane;
	@FXML private Pane welcomePane;
	@FXML private Pane koiePane;
	@FXML private Pane reportPane;
	@FXML private Text welcomeName; // overskriften p� welcome-panelet
	@FXML private Text activeKoieName; // overskriften p� koie-panelet
	@FXML private Label feilLoginInfo; // skrift som dukker opp n�r man pr�ver � logge inn med feil info
	private String activeKoie; // holder styr p� aktiv koie (skal alltid v�re lik activeKoieName.getText())
	private Button mapBtn; //knappen som dukker opp n�r man trykker p� en koie p� kartet
	private Admin admin;
	private DBConnection connection = new DBConnection();

	public void initialize() { //basically konstrukt�r
		feilLoginInfo.setVisible(false);
		root.setCenter(loginScreen);
		mapBtn = new Button();
		mapBtn.setFocusTraversable(false); //gj�r at man ikke kan "hoppe" til knappen ved � trykke p� tab
		mapBtn.setOnAction(new EventHandler<ActionEvent>() { //n�r man trykker p� knappen
			public void handle(ActionEvent event) {
				activeKoieName.setText(activeKoie.substring(0, activeKoie.length() - 3));
				root.setCenter(koiePane);
			}
		});
		mapPane.setOnMousePressed(new EventHandler<MouseEvent>() {  // logikk for � h�ndtere trykking
			public void handle(MouseEvent me) {						// p� knapper p� kartet
				if (me.getButton() == MouseButton.PRIMARY) { // hvis venstre museklikk
					activeKoie = ((Node) me.getTarget()).getId();
					if (activeKoie == null || activeKoie == mapPane.getId()) {  // hvis man ikke trykka p�
						mapPane.getChildren().remove(mapBtn);					// en koie
						return;
					}
					double koieX = ((ImageView) me.getTarget()).getLayoutX(); // plasserer knappen p� kartet
					double koieY = ((ImageView) me.getTarget()).getLayoutY();
					mapBtn.setText("�pne " + activeKoie.substring(0, activeKoie.length() - 3));
					mapBtn.setLayoutX(koieX + 25);
					mapBtn.setLayoutY(koieY - 25);
					mapPane.getChildren().remove(mapBtn);
					mapPane.getChildren().add(mapBtn);
				}
			}
		});
	}

	@FXML
	public void test(ActionEvent event) { // test-metode please ignore
		System.out.println("test " + event);
	}

	@FXML
	public void koieClicked(ActionEvent event) { // n�r man trykker p� koie i lista
		((Hyperlink) event.getSource()).setVisited(false);
		activeKoie = ((Hyperlink) event.getSource()).getText();
		activeKoieName.setText(activeKoie);
		root.setCenter(koiePane);
	}

	@FXML
	public void openWelcome(ActionEvent event) { // n�r man trykker p� hjem-knappen
		root.setCenter(welcomePane);
	}

	@FXML
	public void openMap(ActionEvent event) { // n�r man trykker p� kart-knappen
		mapPane.getChildren().remove(mapBtn);
		root.setCenter(mapPane);
	}

	@FXML
	public void openReport(ActionEvent event) { // n�r man trykker p� rapport-knappen
		root.setCenter(reportPane);
	}

	@FXML
	public void newUser(ActionEvent event) { // n�r man trykker p� "ny bruker" knappen
		root.setCenter(registerScreen);
	}

	@FXML
	public void register(ActionEvent event) { // n�r man trykker p� "registrer" knappen
		if (/*username does not exist*/ true && regPasswordField.getText().equals(regPasswordFieldConfirmation.getText())) {
			System.out.println("success!!");
			logOut(event);
		}
	}

	@FXML
	public void logOut(ActionEvent event) { // n�r man trykker p� "logg ut"  eller "tilbake" knappen
		admin = null;
		usernameField.clear();
		passwordField.clear();
		regUsernameField.clear();
		regPasswordField.clear();
		regPasswordFieldConfirmation.clear();
		root.getChildren().clear();
		root.setCenter(loginScreen);
	}

	@FXML
	public void logIn(ActionEvent event) { // n�r man trykker p� "logg inn" knappen
		ResultSet dbUserInfo = connection.login(usernameField.getText());
		try {
			if (dbUserInfo.next()) {
				if (dbUserInfo.getString(1).equals(usernameField.getText()) && dbUserInfo.getString(2).equals(passwordField.getText())) {
					feilLoginInfo.setVisible(false);
					admin = new Admin(usernameField.getText());
					welcomeName.setText("Velkommen, " + usernameField.getText());
					root.setLeft(koieListe);
					root.setCenter(welcomePane);
					root.setTop(toolbar);
				} else {
					feilLoginInfo.setVisible(true);
				}
			} else {
				feilLoginInfo.setVisible(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
