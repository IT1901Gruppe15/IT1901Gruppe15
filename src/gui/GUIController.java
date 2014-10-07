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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import klasser.Bruker;
import klasser.DBConnection;
import klasser.Koie;
import klasser.Rapport;

public class GUIController {

	@FXML private BorderPane root; // �verste element i gui-hierarkiet
	@FXML private TextField usernameField; // tekstfeltene for login og registrering
	@FXML private PasswordField passwordField;
	@FXML private TextField regFullNameField;
	@FXML private TextField regTlfField;
	@FXML private TextField regEpostField;
	@FXML private TextField regUsernameField;
	@FXML private PasswordField regPasswordField;
	@FXML private PasswordField regPasswordFieldConfirmation;
	@FXML private TextField vedstatusField;
	@FXML private TextArea ødelagteTingField;
	@FXML private TextArea gjenglemteTingField;
	@FXML private TextArea rapportødelagteTingField;
	@FXML private TextArea rapportGjenglemteTingField;
	@FXML private Pane loginScreen; // root fylles av Panes
	@FXML private Pane registerScreen;
	@FXML private Pane koieListe;
	@FXML private Pane toolbar;
	@FXML private Pane brukerToolbar;
	@FXML private Pane mapPane;
	@FXML private Pane welcomePane;
	@FXML private Pane koiePane;
	@FXML private Pane reservasjonsPane;
	@FXML private Pane reportPane;
	@FXML private Text welcomeName; // overskriften p� welcome-panelet
	@FXML private Text koieReservasjonsName; // overskriften p� reservasjons-panelet
	@FXML private Text koieStatusName; // overskriften p� koie-panelet
	@FXML private Text antallSengeplasserText; // overskriften p� koie-panelet
	@FXML private Text ledigeSengeplasserText; // overskriften p� koie-panelet
	@FXML private Label feilLoginInfo; // skrift som dukker opp n�r man pr�ver � logge inn med feil info
	@FXML private Label registreringsFeil; // skrift som dukker opp n�r man f�r feil ved registrering
	private String activeKoie; // holder styr p� aktiv koie
	private Button mapBtn; //knappen som dukker opp n�r man trykker p� en koie p� kartet
	@FXML private Pane medlemPane;
	@FXML private VBox medlemListe;
	@FXML private HBox medlemListeOverskrift;
	private Bruker admin;
	private DBConnection connection;
	@FXML DatePicker kalender; // kalenderen i koie-panelet
	@FXML DatePicker reservasjonFra; // kalenderen i koie-panelet
	@FXML DatePicker reservasjonTil; // kalenderen i koie-panelet
	LocalDate ld; // aktiv dato i kalenderen
	@FXML ComboBox<String> rapportDropDown;

	public void initialize() { //basically konstrukt�r
		rapportDropDown.getItems().addAll("Fl�koia", "Fosenkoia", "Heinfjordstua", "Hognabu", "Holms�koia", "Holvassgamma", "Iglbu", "Kamtj�nna", "Kr�kilk�ten", "Kvernmovollen", "K�sen", "Lynh�gen", "Mortensk�ten", "Nicokoia", "Rindasl�a", "Selbuk�ten", "Sonvasskoia", "Stabburet", "Stakkslettbua", "Telin", "Taagaabu", "Vekvess�tra", "�vensenget"	);
		connection = new DBConnection();
		ld = LocalDate.now();
		kalender.setValue(ld);
		kalender.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evet) {
				ld = kalender.getValue();
				oppdaterSengeplasser();
			}
		});
		feilLoginInfo.setVisible(false);
		registreringsFeil.setVisible(false);
		root.setCenter(loginScreen);
		mapBtn = new Button();
		mapBtn.setFocusTraversable(false); //gj�r at man ikke kan "hoppe" til knappen ved � trykke p� tab
		mapBtn.setOnAction(new EventHandler<ActionEvent>() { //n�r man trykker p� knappen
			public void handle(ActionEvent event) {
				if (admin.getAdminStatus()) {
					koieStatusName.setText(activeKoie);
					System.out.println(activeKoie);
					fyllKoiePane();
					root.setCenter(koiePane);
				} else {
					koieReservasjonsName.setText(activeKoie);
					root.setCenter(reservasjonsPane);
				}
			}
		});
		mapPane.setOnMousePressed(new EventHandler<MouseEvent>() {  // logikk for � h�ndtere trykking
			public void handle(MouseEvent me) {						// p� knapper p� kartet
				if (me.getButton() == MouseButton.PRIMARY) { // hvis venstre museklikk
					activeKoie = ((Node) me.getTarget()).getId();
					activeKoie = activeKoie.substring(0, activeKoie.length() - 3);
					if (activeKoie == null || activeKoie == mapPane.getId()) {  // hvis man ikke trykka p�
						mapPane.getChildren().remove(mapBtn);					// en koie
						return;
					}
					double koieX = ((ImageView) me.getTarget()).getLayoutX(); // plasserer knappen p� kartet
					double koieY = ((ImageView) me.getTarget()).getLayoutY();
					mapBtn.setText("�pne " + activeKoie);
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
		if (admin.getAdminStatus()) {
			koieStatusName.setText(activeKoie);
			fyllKoiePane();
			root.setCenter(koiePane);
		} else {
			koieReservasjonsName.setText(activeKoie);
			root.setCenter(reservasjonsPane);
		}
	}

	private void fyllKoiePane() {
		ødelagteTingField.clear();
		gjenglemteTingField.clear();
		ResultSet rs = connection.getOdelagtGjenglemtKoie(Koie.formaterKoieNavn(activeKoie));
		try {
			String ødelagt = "";
			String gjenglemt = "";
			while (rs.next()) {
				ødelagt += ";" + rs.getString(1);
				gjenglemt += ";" + rs.getString(2);
			}
			if (ødelagt.length() > 0) {				
				ødelagt = ødelagt.substring(1);
				String[] ødelagtListe = ødelagt.split(";");
				for (int i = 0; i < ødelagtListe.length; i++) {
					ødelagteTingField.setText(ødelagteTingField.getText() + ødelagtListe[i] + "\n");
				}
			}
			if (gjenglemt.length() > 0) {
				gjenglemt = gjenglemt.substring(1);				
				String[] gjenglemtListe = gjenglemt.split(";");
				for (int i = 0; i < gjenglemtListe.length; i++) {
					gjenglemteTingField.setText(gjenglemteTingField.getText() + gjenglemtListe[i] + "\n");
				}
			}
			oppdaterSengeplasser();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void oppdaterSengeplasser() {
		try {
			ResultSet antallSengeplasser = connection.getSengeplasser(Koie.formaterKoieNavn(activeKoie));
			if (antallSengeplasser.next()) {
				antallSengeplasserText.setText("Antall sengeplasser: " + antallSengeplasser.getString(1));
			}
			ResultSet reserverteSengeplasser = connection.getReservertePlasser(Koie.formaterKoieNavn(activeKoie), kalender.getValue().toString());
			if (reserverteSengeplasser.next()) {
				int ledigeSengeplasser = Integer.parseInt(antallSengeplasser.getString(1).toString()) - Integer.parseInt(reserverteSengeplasser.getString(1).toString());
				ledigeSengeplasserText.setText("Ledige sengeplasser: " + ledigeSengeplasser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	public void openReservasjon(ActionEvent event) {
		root.setCenter(reservasjonsPane);
	}

	@FXML
	public void newUser(ActionEvent event) { // n�r man trykker p� "ny bruker" knappen
		root.setCenter(registerScreen);
	}

	@FXML
	public void sendRapport(ActionEvent event) { 
		if (rapportDropDown.getValue().equals("Velg en koie")) {
			return;
		}
		connection.settinnRapport(rapportødelagteTingField.getText(), rapportGjenglemteTingField.getText(), Integer.parseInt(vedstatusField.getText()), rapportDropDown.getValue(), LocalDate.now().toString());
		String ødelagt = Rapport.formaterTekst(rapportødelagteTingField.getText(), "\n");
		String gjenglemt = Rapport.formaterTekst(rapportGjenglemteTingField.getText(), "\n");
		int vedstatus = Integer.parseInt(vedstatusField.getText());
		connection.settinnRapport(ødelagt, gjenglemt, vedstatus, rapportDropDown.getValue(), LocalDate.now().toString());
	}

	@FXML
	public void fyllMedlemListe() {
		medlemListe.getChildren().clear();
		medlemListe.getChildren().add(medlemListeOverskrift);
		ResultSet rs = connection.getMembers();  // Brukernavn, Navn, Tlf, Epost, isAdmin
		try {
			while (rs.next()) {
				HBox hbox = new HBox();
				for (int i = 1; i < 5; i++) {
					Label text = new Label(rs.getString(i));
					if (i == 3) {
						text.setPrefWidth(200);
					} else {
						text.setPrefWidth(100);
					}
					hbox.getChildren().add(text);
				}
				medlemListe.getChildren().add(hbox);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		root.setCenter(medlemPane);
	}

	@FXML
	public void register(ActionEvent event) { // n�r man trykker p� "registrer" knappen
		ResultSet rs = connection.login(regUsernameField.getText());
		try {
			if (regUsernameField.getText().equals("") || regPasswordField.getText().equals("") || regFullNameField.getText().equals("") || regTlfField.getText().equals("") || regEpostField.getText().equals("")) {
				registreringsFeil.setText("Ingen felter kan v�re tomme");
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
	public void logOut(ActionEvent event) { // n�r man trykker p� "logg ut"  eller "tilbake" knappen
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
	public void logIn(ActionEvent event) { // n�r man trykker p� "logg inn" knappen
		ResultSet dbUserInfo = connection.login(usernameField.getText());
		try {
			if (dbUserInfo.next()) {
				if (dbUserInfo.getString(1).equals(usernameField.getText()) && dbUserInfo.getString(2).equals(passwordField.getText())) {
					admin = new Bruker(usernameField.getText(), dbUserInfo.getString(3), dbUserInfo.getString(4), dbUserInfo.getString(5), dbUserInfo.getString(6));
					welcomeName.setText("Velkommen, " + usernameField.getText());
					if (admin.getAdminStatus()) {
						root.setLeft(koieListe);
						root.setCenter(welcomePane);
						root.setTop(toolbar);
					} else {
						root.setLeft(koieListe);
						root.setCenter(welcomePane);
						root.setTop(brukerToolbar);
					}
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
