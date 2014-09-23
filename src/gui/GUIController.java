package gui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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

	@FXML private BorderPane root; // øverste element i gui-hierarkiet
	@FXML private TextField usernameField; // tekstfeltene for login og registrering
	@FXML private PasswordField passwordField;
	@FXML private TextField regFullNameField;
	@FXML private TextField regTlfField;
	@FXML private TextField regEpostField;
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
	@FXML private Text welcomeName; // overskriften på welcome-panelet
	@FXML private Text activeKoieName; // overskriften på koie-panelet
	@FXML private Label feilLoginInfo; // skrift som dukker opp når man prøver å logge inn med feil info
	@FXML private Label registreringsFeil; // skrift som dukker opp når man får feil ved registrering
	private String activeKoie; // holder styr på aktiv koie (skal alltid være lik activeKoieName.getText())
	private Button mapBtn; //knappen som dukker opp når man trykker på en koie på kartet
	private Admin admin;
	private DBConnection connection;
	@FXML DatePicker kalender; // kalenderen i koie-panelet
	LocalDate ld; // aktiv dato i kalenderen
	@FXML ComboBox rapportDropDown;

	public void initialize() { //basically konstruktør
		rapportDropDown.getItems().addAll("test1234", "test12345" ,"test123");
		connection = new DBConnection();
		ld = LocalDate.now();
		kalender.setValue(ld);
		kalender.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evet) {
				ld = kalender.getValue();
				System.out.println("dd-mm-yyyy " + ld.getDayOfMonth() + "-" + ld.getMonth().getValue() + "-" + ld.getYear());
			}
		});
		feilLoginInfo.setVisible(false);
		registreringsFeil.setVisible(false);
		root.setCenter(loginScreen);
		mapBtn = new Button();
		mapBtn.setFocusTraversable(false); //gjør at man ikke kan "hoppe" til knappen ved å trykke på tab
		mapBtn.setOnAction(new EventHandler<ActionEvent>() { //når man trykker på knappen
			public void handle(ActionEvent event) {
				activeKoieName.setText(activeKoie.substring(0, activeKoie.length() - 3));
				root.setCenter(koiePane);
			}
		});
		mapPane.setOnMousePressed(new EventHandler<MouseEvent>() {  // logikk for å håndtere trykking
			public void handle(MouseEvent me) {						// på knapper på kartet
				if (me.getButton() == MouseButton.PRIMARY) { // hvis venstre museklikk
					activeKoie = ((Node) me.getTarget()).getId();
					if (activeKoie == null || activeKoie == mapPane.getId()) {  // hvis man ikke trykka på
						mapPane.getChildren().remove(mapBtn);					// en koie
						return;
					}
					double koieX = ((ImageView) me.getTarget()).getLayoutX(); // plasserer knappen på kartet
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
	public void test(ActionEvent event) { // test-metode please ignore
		System.out.println("test " + event);
	}

	@FXML
	public void koieClicked(ActionEvent event) { // når man trykker på koie i lista
		((Hyperlink) event.getSource()).setVisited(false);
		activeKoie = ((Hyperlink) event.getSource()).getText();
		activeKoieName.setText(activeKoie);
		root.setCenter(koiePane);
	}

	@FXML
	public void openWelcome(ActionEvent event) { // når man trykker på hjem-knappen
		root.setCenter(welcomePane);
	}

	@FXML
	public void openMap(ActionEvent event) { // når man trykker på kart-knappen
		mapPane.getChildren().remove(mapBtn);
		root.setCenter(mapPane);
	}

	@FXML
	public void openReport(ActionEvent event) { // når man trykker på rapport-knappen
		root.setCenter(reportPane);
	}

	@FXML
	public void newUser(ActionEvent event) { // når man trykker på "ny bruker" knappen
		root.setCenter(registerScreen);
	}

	@FXML
	public void register(ActionEvent event) { // når man trykker på "registrer" knappen
		ResultSet rs = connection.login(regUsernameField.getText());
		try {
			if (regUsernameField.getText().equals("") || regPasswordField.getText().equals("") || regFullNameField.getText().equals("") || regTlfField.getText().equals("") || regEpostField.getText().equals("")) {
				registreringsFeil.setText("Ingen felter kan være tomme");
				registreringsFeil.setVisible(true);
			}
			else if (rs.next()) {
				registreringsFeil.setText("Brukernavn er allerede i bruk");
				registreringsFeil.setVisible(true);
			} else if (regPasswordField.getText().equals(regPasswordFieldConfirmation.getText())) {
				connection.registrerBruker(regUsernameField.getText(), regPasswordField.getText(), regFullNameField.getText(), regTlfField.getText(), regEpostField.getText());
				System.out.println("Bruker er registrert");
				logOut(event);
			} else {
				registreringsFeil.setText("Passordene er ikke like");
				registreringsFeil.setVisible(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 

	@FXML
	public void logOut(ActionEvent event) { // når man trykker på "logg ut"  eller "tilbake" knappen
		admin = null;
		feilLoginInfo.setVisible(false);
		registreringsFeil.setVisible(false);
		usernameField.clear();
		passwordField.clear();
		regFullNameField.clear();
		regTlfField.clear();
		regEpostField.clear();
		regUsernameField.clear();
		regPasswordField.clear();
		regPasswordFieldConfirmation.clear();
		root.getChildren().clear();
		root.setCenter(loginScreen);
	}

	@FXML
	public void logIn(ActionEvent event) { // når man trykker på "logg inn" knappen
		ResultSet dbUserInfo = connection.login(usernameField.getText());
		try {
			if (dbUserInfo.next()) {
				if (dbUserInfo.getString(1).equals(usernameField.getText()) && dbUserInfo.getString(2).equals(passwordField.getText())) {
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
