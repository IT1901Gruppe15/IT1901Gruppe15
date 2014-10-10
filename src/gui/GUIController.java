package gui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import core.Bruker;
import core.DBConnection;
import core.Koie;
import core.RapportHandler;
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

/**
 * Kontrolleren til GUI.fxml.
 * Håndterer all logikk for GUI i NTNUI Koiesystemet.
 * 
 */
public class GUIController {

	//root
	@FXML private BorderPane root; // øverste element i gui-hierarkiet

	//admin toolbar
	@FXML private Pane adminToolbar; // knapper for admin

	//bruker toolbar
	@FXML private Pane brukerToolbar; // knapper for bruker

	//koie liste
	@FXML private Pane koieListe; // liste over koiene

	//welcome pane
	@FXML private Pane welcomePane; // velkomst panelet (default når man logger inn)
	@FXML private Text welcomeName; // overskriften på welcome-panelet

	//map pane
	@FXML private Pane mapPane; // kart over koiene
	private Button mapBtn; //knappen som dukker opp når man trykker på en koie på kartet

	//login pane
	@FXML private Pane loginScreen; // login skjerm
	@FXML private TextField usernameField; // tekstfelt for brukernavn i login
	@FXML private PasswordField passwordField; // tekstfelt for passord i login
	@FXML private Label invalidLoginInfo; // skrift som dukker opp når man får feil ved login

	//register pane
	@FXML private Pane registerScreen; // registrerings skjerm
	@FXML private TextField regFullNameField; // tekstfelt for navn i registrering
	@FXML private TextField regTlfField; // tekstfelt for telefonnummer i registrering
	@FXML private TextField regEpostField; // tekstfelt for epost i registrering
	@FXML private TextField regUsernameField; // tekstfelt for brukernavn i registrering
	@FXML private PasswordField regPasswordField; // tekstfelt for passord i registrering
	@FXML private PasswordField regPasswordFieldConfirmation; // tekstfelt for gjentagelse av passord i registrering
	@FXML private Label registreringsFeil; // skrift som dukker opp når man får feil ved registrering

	//report pane
	@FXML private Pane reportPane; // rapport panel
	@FXML private ComboBox<String> rapportDropDown; // drop-down meny med alle koiene i rapport-panelet
	@FXML private TextField vedstatusField; // tekstfelt for å angi vedstatus i rapport-panelet
	@FXML private TextArea rapportOdelagteTingField; // tekstfelt for å angi ødelagte gjenstander i rapport-panelet
	@FXML private TextArea rapportGjenglemteTingField; // tekstfelt for å angi gjenglemte gjenstander i rapport-panelet

	//admin koie info pane
	@FXML private Pane adminKoiePane; // koie status panel for admin
	@FXML private Text adminKoieStatusName; // overskriften på koie-panelet for admin
	@FXML private TextArea adminOdelagteTingField; // tekstfelt for ødelagte gjenstander i koie-panelet for admin
	@FXML private TextArea adminGjenglemteTingField; // tekstfelt for gjenglemte gjenstander i koie-panelet for admin
	@FXML private DatePicker adminKalender; // kalenderen i koie-panelet for admin
	@FXML private Text adminAntallSengeplasserText; // overskriften på koie-panelet for admin
	@FXML private Text adminLedigeSengeplasserText; // overskriften på koie-panelet for admin

	//bruker koie info pane
	@FXML private Pane brukerKoiePane; // koie status panel for bruker
	@FXML private Text brukerKoieStatusName; // overskriften på koie-panelet for bruker
	@FXML private TextArea brukerOdelagteTingField; // tekstfelt for ødelagte gjenstander i koie-panelet for bruker
	@FXML private TextArea brukerGjenglemteTingField; // tekstfelt for gjenglemte gjenstander i koie-panelet for bruker
	@FXML private DatePicker brukerKalender; // kalenderen i koie-panelet for bruker
	@FXML private Text brukerAntallSengeplasserText; // overskriften på koie-panelet for bruker
	@FXML private Text brukerLedigeSengeplasserText; // overskriften på koie-panelet for bruker

	//medlem liste pane
	@FXML private Pane medlemPane; // panel med liste over registrerte brukere i systemet
	@FXML private VBox medlemListe; // liste over medlemmer
	@FXML private HBox medlemListeOverskrift; // overskriften i tabellen (Navn, Tlf, Epost osv...)

	private String activeKoie; // holder styr på aktiv koie
	private Bruker bruker; // innlogget bruker i systemet
	private DBConnection connection; // håndterer alt av database ting

	/**
	 * Initialiserer GUI
	 */
	public void initialize() { //basically konstruktør
		rapportDropDown.getItems().addAll("Flåkoia", "Fosenkoia", "Heinfjordstua", "Hognabu", "Holmsåkoia", "Holvassgamma", "Iglbu", "Kamtjønna", "Kråkilkåten", "Kvernmovollen", "Kåsen", "Lynhøgen", "Mortenskåten", "Nicokoia", "Rindasløa", "Selbukåten", "Sonvasskoia", "Stabburet", "Stakkslettbua", "Telin", "Taagaabu", "Vekvessætra", "Øvensenget"	);
		connection = new DBConnection();
		adminKalender.setValue(LocalDate.now()); // setter default dato til idag
		adminKalender.setOnAction(new EventHandler<ActionEvent>() { // når man endrer dato
			public void handle(ActionEvent event) {
				oppdaterSengeplasser(true, adminKalender.getValue()); // oppdaterer info om sengeplasser i koie-panelet for admin
			}
		});
		brukerKalender.setValue(LocalDate.now());  // setter default dato til idag
		brukerKalender.setOnAction(new EventHandler<ActionEvent>() { // når man endrer dato
			public void handle(ActionEvent event) {
				oppdaterSengeplasser(false, brukerKalender.getValue()); // oppdaterer info om sengeplasser i koie-panelet for bruker
			}
		});
		invalidLoginInfo.setVisible(false); // setter default synlighet for teksten som dukker opp ved feil ved login til false
		registreringsFeil.setVisible(false); // setter default synlighet for teksten som dukker opp ved feil ved registrering til false
		root.setCenter(loginScreen); // setter default panel til login skjermen
		mapBtn = new Button();
		mapBtn.setFocusTraversable(false); // gjør at man ikke kan "hoppe" til knappen ved å trykke på tab
		mapBtn.setOnAction(new EventHandler<ActionEvent>() { // når man trykker på knappen
			public void handle(ActionEvent event) {
				fyllKoiePane(); // finner all informasjon som skal vises i koie-panelet
				if (bruker.isAdmin()) { // hvis bruker er admin
					root.setCenter(adminKoiePane); // viser koie-informasjon for admin
				} else {
					root.setCenter(brukerKoiePane); // viser koie-informasjon for bruker
				}
			}
		});
		mapPane.setOnMousePressed(new EventHandler<MouseEvent>() {  // logikk for å håndtere trykking på knapper på kartet
			public void handle(MouseEvent me) {
				if (me.getButton() == MouseButton.PRIMARY) { // hvis venstre museklikk
					activeKoie = ((Node) me.getTarget()).getId(); // finner hvilken koie man trykket på
					if (activeKoie == null || activeKoie == mapPane.getId()) {  // hvis man ikke trykket på en koie
						mapPane.getChildren().remove(mapBtn); // skjuler knappen
						return;
					}
					activeKoie = activeKoie.substring(0, activeKoie.length() - 3); // activeKoie er her f.eks. FosenkoiaMap, fjerner "Map"
					double koieX = ((ImageView) me.getTarget()).getLayoutX(); // finner x-posisjonen til koia pan trykket på
					double koieY = ((ImageView) me.getTarget()).getLayoutY(); // finner x-posisjonen til koia pan trykket på
					mapBtn.setText("Åpne " + activeKoie); // setter teksten på knappen til f.eks "Åpne FosenKoia"
					mapBtn.setLayoutX(koieX + 25); // plasserer knappen på kartet
					mapBtn.setLayoutY(koieY - 25);
					mapPane.getChildren().remove(mapBtn); // hvis knappen allerede er på skjermen, så fjernes den (gjøres for å forhindre error)
					mapPane.getChildren().add(mapBtn); // viser knappen
				}
			}
		});
	}

	@FXML
	private void koieClicked(ActionEvent event) { // når man trykker på koie i lista
		((Hyperlink) event.getSource()).setVisited(false); // gjør at linkene i koia-lista ikke for strek under seg når man trykker på de
		activeKoie = ((Hyperlink) event.getSource()).getText(); // setter aktivKoie til koia man trykket på
		fyllKoiePane(); // finner all informasjon som skal vises i koie-panelet
		if (bruker.isAdmin()) {
			root.setCenter(adminKoiePane); // viser koie-informasjon for admin
		} else {
			root.setCenter(brukerKoiePane); // viser koie-informasjon for bruker
		}
	}
	
	private void fyllKoiePane() { // finner all informasjon som skal vises i koie-panelet
		try {
			ResultSet rs = connection.getOdelagtGjenglemtKoie(Koie.formaterKoieNavn(activeKoie)); // får alle ødelagte og gjenglemte gjenstander fra databasen
			String ødelagt = ""; // string som fylles med alle ødelagte gjenstander
			String gjenglemt = ""; // string som fylles med alle gjenglemte gjenstander
			while (rs.next()) { // mens det finnes flere elementer i databasen
				ødelagt += ";" + rs.getString(1); // ;ødelagt1;ødelagt2;ødelagt3
				gjenglemt += ";" + rs.getString(2); // ;gjenglemt1;gjenglemt2;gjenglemt3
			}
			String ferdigØdelagt = ""; // string som skal settes inn i tekstfeltet for ødelagte gjenstander
			if (ødelagt.length() > 0) { // hvis det finnes minst en ødelagt gjenstand
				ødelagt = ødelagt.substring(1); // fjerner den første semikolonen
				String[] ødelagtListe = ødelagt.split(";");
				for (int i = 0; i < ødelagtListe.length; i++) {
					ferdigØdelagt += ødelagtListe[i] + "\n";
				}
			}
			///
			/*
			ødelagt = "";
			ferdigØdelagt = "";
			rs = connection.getOdelagtUtstyr(Koie.formaterKoieNavn(activeKoie));
			while (rs.next()) {
				ødelagt += ";" + rs.getString(1);
			}
			if (ødelagt.length() > 0) {
				ødelagt = ødelagt.substring(1);
				String[] ødelagtListe2 = ødelagt.split(";");
				for (int i = 0; i < ødelagtListe2.length; i++) {
					ferdigØdelagt += ødelagtListe2[i] + "\n";
				}
			}
			*/
			///
			String ferdigGjenglemt = ""; // string som skal settes inn i tekstfeltet for gjenglemte gjenstander
			if (gjenglemt.length() > 0) { // hvis det finnes minst en gjenglemt gjenstand
				gjenglemt = gjenglemt.substring(1); // fjerner den første semikolonen
				String[] gjenglemtListe = gjenglemt.split(";");
				for (int i = 0; i < gjenglemtListe.length; i++) {
					ferdigGjenglemt += gjenglemtListe[i] + "\n";
				}
			}
			if (bruker.isAdmin()) {
				adminKoieStatusName.setText(activeKoie); // setter inn all informasjonen i koie-panelet
				adminOdelagteTingField.setText(ferdigØdelagt);
				adminGjenglemteTingField.setText(ferdigGjenglemt);
				oppdaterSengeplasser(true, adminKalender.getValue());
			} else {
				brukerKoieStatusName.setText(activeKoie);
				brukerOdelagteTingField.setText(ferdigØdelagt);
				brukerGjenglemteTingField.setText(ferdigGjenglemt);
				oppdaterSengeplasser(false, brukerKalender.getValue());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void oppdaterSengeplasser(boolean isAdmin, LocalDate date) { // setter inn riktig antall totalt/tilgjengelige sengeplasser
		try {
			ResultSet antallSengeplasser = connection.getSengeplasser(Koie.formaterKoieNavn(activeKoie));
			ResultSet reserverteSengeplasser = connection.getReservertePlasser(Koie.formaterKoieNavn(activeKoie), date.toString());
			if (antallSengeplasser.next()) {
				if (isAdmin) {					
					adminAntallSengeplasserText.setText("Antall sengeplasser: " + antallSengeplasser.getString(1));
				} else 
					brukerAntallSengeplasserText.setText("Antall sengeplasser: " + antallSengeplasser.getString(1));					
			}
			if (reserverteSengeplasser.next()) {
				int ledigeSengeplasser = Integer.parseInt(antallSengeplasser.getString(1).toString()) - Integer.parseInt(reserverteSengeplasser.getString(1).toString());
				if (isAdmin) {
					adminLedigeSengeplasserText.setText("Ledige sengeplasser: " + ledigeSengeplasser);					
				} else {
					brukerLedigeSengeplasserText.setText("Ledige sengeplasser: " + ledigeSengeplasser);										
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void openWelcome(ActionEvent event) { // når man trykker på hjem-knappen
		root.setCenter(welcomePane);
	}

	@FXML
	private void openMap(ActionEvent event) { // når man trykker på kart-knappen
		mapPane.getChildren().remove(mapBtn);
		root.setCenter(mapPane);
	}

	@FXML
	private void openReport(ActionEvent event) { // når man trykker på rapport-knappe
		System.out.println("'" + rapportOdelagteTingField.getText() + "'");
		root.setCenter(reportPane);
	}

	@FXML
	private void newUser(ActionEvent event) { // når man trykker på "ny bruker" knappen
		root.setCenter(registerScreen);
	}

	@FXML
	private void sendRapport(ActionEvent event) {  // når man trykker på "send rapport" knappen i rapport-panelet
		if (rapportDropDown.getValue().equals("Velg en koie")) { // hvis man ikke har valgt en koie
			return;
		}
		String ødelagt = RapportHandler.formaterTekst(rapportOdelagteTingField.getText(), "\n");
		String gjenglemt = RapportHandler.formaterTekst(rapportGjenglemteTingField.getText(), "\n");
		int vedstatus = 0;
		if (vedstatusField.getText().length() != 0) {
			vedstatus = Integer.parseInt(vedstatusField.getText());
		}
		try {
			connection.settinnRapport(ødelagt, gjenglemt, vedstatus, rapportDropDown.getValue(), LocalDate.now().toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void openMedlemListe() { // når man trykker på medlemsliste-knappen
		medlemListe.getChildren().clear(); // fjerner listen hvis den har blitt fylt før
		medlemListe.getChildren().add(medlemListeOverskrift); // setter inn overskriftene
		ResultSet rs = connection.getMembers();  // Brukernavn, Navn, Tlf, Epost, isAdmin
		try {
			while (rs.next()) { // mens det er flere medlemmer
				HBox hbox = new HBox(); // gjør masse rart...
				for (int i = 1; i < 5; i++) {
					Label text = new Label(rs.getString(i));
					if (i == 3) { // eposten trenger litt mer plass enn de andre feltene
						text.setPrefWidth(200);
					} else {
						text.setPrefWidth(100);
					}
					hbox.getChildren().add(text);
				}
				medlemListe.getChildren().add(hbox); // ...og legg til informasjonen i lista
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		root.setCenter(medlemPane);
	}
	
	@FXML
	private void oppdaterUtstyr(ActionEvent event) {
		try {
			ResultSet rs = connection.getUtstyrID("badstue", "Flaakoia");
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
			connection.oppdaterUtstyr(1, 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void register(ActionEvent event) { // når man trykker på "registrer" knappen
		try {
			ResultSet rs = connection.login(regUsernameField.getText());
			if (regUsernameField.getText().equals("") || regPasswordField.getText().equals("") || regFullNameField.getText().equals("") || regTlfField.getText().equals("") || regEpostField.getText().equals("")) {
				registreringsFeil.setText("Ingen felter kan våre tomme");
				registreringsFeil.setVisible(true);
			}
			else if (rs.next()) { // hvis det finnes et element i ResultSetet så finnes det allerede en bruker med det brukernavnet
				registreringsFeil.setText("Brukernavn er allerede i bruk");
				registreringsFeil.setVisible(true);
			} else if (regPasswordField.getText().equals(regPasswordFieldConfirmation.getText())) { // passordfeltene må være like
				connection.registrerBruker(regUsernameField.getText(), regPasswordField.getText(), regFullNameField.getText(), regTlfField.getText(), regEpostField.getText());
				logOut(event); // sender tilbake til login-skjermen
			} else {
				registreringsFeil.setText("Passordene er ikke like");
				registreringsFeil.setVisible(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 

	@FXML
	private void logOut(ActionEvent event) { // når man trykker på "logg ut"  eller "tilbake" knappen
		bruker = null;
		invalidLoginInfo.setVisible(false);
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
	private void logIn(ActionEvent event) { // når man trykker på "logg inn" knappen
		try {
			ResultSet dbUserInfo = connection.login(usernameField.getText());
			if (dbUserInfo.next()) { // denne if-setningen er true hvis det angitte brukernavnet finnes i databasen
				if (dbUserInfo.getString(1).equals(usernameField.getText()) && dbUserInfo.getString(2).equals(passwordField.getText())) { // hvis brukernavn og passord stemmer overens
					bruker = new Bruker(usernameField.getText(), dbUserInfo.getString(3), dbUserInfo.getString(4), dbUserInfo.getString(5), dbUserInfo.getString(6));
					welcomeName.setText("Velkommen, " + usernameField.getText());
					if (bruker.isAdmin()) {
						root.setLeft(koieListe);
						root.setCenter(welcomePane);
						root.setTop(adminToolbar);
					} else {
						root.setLeft(koieListe);
						root.setCenter(welcomePane);
						root.setTop(brukerToolbar);
					}
				} else { // hvis brukernavnet fantes, men passordet var feil
					invalidLoginInfo.setVisible(true);
				}
			} else { // hvis brukernavnet ikke fantes
				invalidLoginInfo.setVisible(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
