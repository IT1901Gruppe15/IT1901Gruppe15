package gui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import core.Bruker;
import core.DBConnection;
import core.Koie;
import core.Rapport;
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

public class GUIController {

	//root
	@FXML private BorderPane root; // �verste element i gui-hierarkiet

	//admin toolbar
	@FXML private Pane adminToolbar; // knapper for admin

	//bruker toolbar
	@FXML private Pane brukerToolbar; // knapper for bruker

	//koie liste
	@FXML private Pane koieListe; // liste over koiene

	//welcome pane
	@FXML private Pane welcomePane; // velkomst panelet (default n�r man logger inn)
	@FXML private Text welcomeName; // overskriften p� welcome-panelet

	//map pane
	@FXML private Pane mapPane; // kart over koiene
	private Button mapBtn; //knappen som dukker opp n�r man trykker p� en koie p� kartet

	//login pane
	@FXML private Pane loginScreen; // login skjerm
	@FXML private TextField usernameField; // tekstfelt for brukernavn i login
	@FXML private PasswordField passwordField; // tekstfelt for passord i login
	@FXML private Label invalidLoginInfo; // skrift som dukker opp n�r man f�r feil ved login

	//register pane
	@FXML private Pane registerScreen; // registrerings skjerm
	@FXML private TextField regFullNameField; // tekstfelt for navn i registrering
	@FXML private TextField regTlfField; // tekstfelt for telefonnummer i registrering
	@FXML private TextField regEpostField; // tekstfelt for epost i registrering
	@FXML private TextField regUsernameField; // tekstfelt for brukernavn i registrering
	@FXML private PasswordField regPasswordField; // tekstfelt for passord i registrering
	@FXML private PasswordField regPasswordFieldConfirmation; // tekstfelt for gjentagelse av passord i registrering
	@FXML private Label registreringsFeil; // skrift som dukker opp n�r man f�r feil ved registrering

	//report pane
	@FXML private Pane reportPane; // rapport panel
	@FXML private ComboBox<String> rapportDropDown; // drop-down meny med alle koiene i rapport-panelet
	@FXML private TextField vedstatusField; // tekstfelt for � angi vedstatus i rapport-panelet
	@FXML private TextArea rapportOdelagteTingField; // tekstfelt for � angi �delagte gjenstander i rapport-panelet
	@FXML private TextArea rapportGjenglemteTingField; // tekstfelt for � angi gjenglemte gjenstander i rapport-panelet

	//admin koie info pane
	@FXML private Pane adminKoiePane; // koie status panel for admin
	@FXML private Text adminKoieStatusName; // overskriften p� koie-panelet for admin
	@FXML private TextArea adminOdelagteTingField; // tekstfelt for �delagte gjenstander i koie-panelet for admin
	@FXML private TextArea adminGjenglemteTingField; // tekstfelt for gjenglemte gjenstander i koie-panelet for admin
	@FXML private DatePicker adminKalender; // kalenderen i koie-panelet for admin
	@FXML private Text adminAntallSengeplasserText; // overskriften p� koie-panelet for admin
	@FXML private Text adminLedigeSengeplasserText; // overskriften p� koie-panelet for admin

	//bruker koie info pane
	@FXML private Pane brukerKoiePane; // koie status panel for bruker
	@FXML private Text brukerKoieStatusName; // overskriften p� koie-panelet for bruker
	@FXML private TextArea brukerOdelagteTingField; // tekstfelt for �delagte gjenstander i koie-panelet for bruker
	@FXML private TextArea brukerGjenglemteTingField; // tekstfelt for gjenglemte gjenstander i koie-panelet for bruker
	@FXML private DatePicker brukerKalender; // kalenderen i koie-panelet for bruker
	@FXML private Text brukerAntallSengeplasserText; // overskriften p� koie-panelet for bruker
	@FXML private Text brukerLedigeSengeplasserText; // overskriften p� koie-panelet for bruker

	//medlem liste pane
	@FXML private Pane medlemPane; // panel med liste over registrerte brukere i systemet
	@FXML private VBox medlemListe; // liste over medlemmer
	@FXML private HBox medlemListeOverskrift; // overskriften i tabellen (Navn, Tlf, Epost osv...)

	private String activeKoie; // holder styr p� aktiv koie
	private Bruker bruker; // innlogget bruker i systemet
	private DBConnection connection; // h�ndterer alt av database ting

	public void initialize() { //basically konstrukt�r
		rapportDropDown.getItems().addAll("Fl�koia", "Fosenkoia", "Heinfjordstua", "Hognabu", "Holms�koia", "Holvassgamma", "Iglbu", "Kamtj�nna", "Kr�kilk�ten", "Kvernmovollen", "K�sen", "Lynh�gen", "Mortensk�ten", "Nicokoia", "Rindasl�a", "Selbuk�ten", "Sonvasskoia", "Stabburet", "Stakkslettbua", "Telin", "Taagaabu", "Vekvess�tra", "�vensenget"	);
		connection = new DBConnection();
		adminKalender.setValue(LocalDate.now()); // setter default dato til idag
		adminKalender.setOnAction(new EventHandler<ActionEvent>() { // n�r man endrer dato
			public void handle(ActionEvent event) {
				oppdaterSengeplasser(true, adminKalender.getValue()); // oppdaterer info om sengeplasser i koie-panelet for admin
			}
		});
		brukerKalender.setValue(LocalDate.now());  // setter default dato til idag
		brukerKalender.setOnAction(new EventHandler<ActionEvent>() { // n�r man endrer dato
			public void handle(ActionEvent event) {
				oppdaterSengeplasser(false, brukerKalender.getValue()); // oppdaterer info om sengeplasser i koie-panelet for bruker
			}
		});
		invalidLoginInfo.setVisible(false); // setter default synlighet for teksten som dukker opp ved feil ved login til false
		registreringsFeil.setVisible(false); // setter default synlighet for teksten som dukker opp ved feil ved registrering til false
		root.setCenter(loginScreen); // setter default panel til login skjermen
		mapBtn = new Button();
		mapBtn.setFocusTraversable(false); // gj�r at man ikke kan "hoppe" til knappen ved � trykke p� tab
		mapBtn.setOnAction(new EventHandler<ActionEvent>() { // n�r man trykker p� knappen
			public void handle(ActionEvent event) {
				fyllKoiePane(); // finner all informasjon som skal vises i koie-panelet
				if (bruker.isAdmin()) { // hvis bruker er admin
					root.setCenter(adminKoiePane); // viser koie-informasjon for admin
				} else {
					root.setCenter(brukerKoiePane); // viser koie-informasjon for bruker
				}
			}
		});
		mapPane.setOnMousePressed(new EventHandler<MouseEvent>() {  // logikk for � h�ndtere trykking p� knapper p� kartet
			public void handle(MouseEvent me) {
				if (me.getButton() == MouseButton.PRIMARY) { // hvis venstre museklikk
					activeKoie = ((Node) me.getTarget()).getId(); // finner hvilken koie man trykket p�
					if (activeKoie == null || activeKoie == mapPane.getId()) {  // hvis man ikke trykket p� en koie
						mapPane.getChildren().remove(mapBtn); // skjuler knappen
						return;
					}
					activeKoie = activeKoie.substring(0, activeKoie.length() - 3); // activeKoie er her f.eks. FosenkoiaMap, fjerner "Map"
					double koieX = ((ImageView) me.getTarget()).getLayoutX(); // finner x-posisjonen til koia pan trykket p�
					double koieY = ((ImageView) me.getTarget()).getLayoutY(); // finner x-posisjonen til koia pan trykket p�
					mapBtn.setText("�pne " + activeKoie); // setter teksten p� knappen til f.eks "�pne FosenKoia"
					mapBtn.setLayoutX(koieX + 25); // plasserer knappen p� kartet
					mapBtn.setLayoutY(koieY - 25);
					mapPane.getChildren().remove(mapBtn); // hvis knappen allerede er p� skjermen, s� fjernes den (gj�res for � forhindre error)
					mapPane.getChildren().add(mapBtn); // viser knappen
				}
			}
		});
	}

	/**
	 * Finner ut hvilken koie man trykket p� i lista og �pner koie-panelet for denne koia 
	 * 
	 * @param event Eventen som kalte denne metoden
	 */
	@FXML
	public void koieClicked(ActionEvent event) { // n�r man trykker p� koie i lista
		((Hyperlink) event.getSource()).setVisited(false); // gj�r at linkene i koia-lista ikke for strek under seg n�r man trykker p� de
		activeKoie = ((Hyperlink) event.getSource()).getText(); // setter aktivKoie til koia man trykket p�
		fyllKoiePane(); // finner all informasjon som skal vises i koie-panelet
		if (bruker.isAdmin()) { // hvis bruker er admin
			root.setCenter(adminKoiePane); // viser koie-informasjon for admin
		} else {
			root.setCenter(brukerKoiePane); // viser koie-informasjon for bruker
		}
	}

	private void fyllKoiePane() { // finner all informasjon som skal vises i koie-panelet
		ResultSet rs = connection.getOdelagtGjenglemtKoie(Koie.formaterKoieNavn(activeKoie)); // f�r alle �delagte og gjenglemte gjenstander fra databasen
		try {
			String �delagt = ""; // string som fylles med alle �delagte gjenstander
			String gjenglemt = ""; // string som fylles med alle gjenglemte gjenstander
			while (rs.next()) { // mens det finnes flere elementer i databasen
				�delagt += ";" + rs.getString(1); // ;�delagt1;�delagt2;�delagt3
				gjenglemt += ";" + rs.getString(2); // ;gjenglemt1;gjenglemt2;gjenglemt3
			}
			String ferdig�delagt = ""; // string som skal settes inn i tekstfeltet for �delagte gjenstander
			if (�delagt.length() > 0) { // hvis det finnes minst en �delagt gjenstand
				�delagt = �delagt.substring(1); // fjerner den f�rste semikoloen
				String[] �delagtListe = �delagt.split(";"); // splitter stringen
				for (int i = 0; i < �delagtListe.length; i++) { // for alle elementer i lista
					ferdig�delagt += �delagtListe[i] + "\n"; // setter alle elementene i en string med "\n" mellom de
				}
			}
			String ferdigGjenglemt = ""; // string som skal settes inn i tekstfeltet for gjenglemte gjenstander
			if (gjenglemt.length() > 0) { // hvis det finnes minst en gjenglemt gjenstand
				gjenglemt = gjenglemt.substring(1); // fjerner den f�rste semikoloen
				String[] gjenglemtListe = gjenglemt.split(";"); // splitter stringen
				for (int i = 0; i < gjenglemtListe.length; i++) { // for alle elementer i lista
					ferdigGjenglemt += gjenglemtListe[i] + "\n"; // setter alle elementene i en string med "\n" mellom de
				}
			}
			if (bruker.isAdmin()) {
				adminKoieStatusName.setText(activeKoie); // setter inn all informasjonen i koie-panelet
				adminOdelagteTingField.setText(ferdig�delagt);
				adminGjenglemteTingField.setText(ferdigGjenglemt);
				oppdaterSengeplasser(true, adminKalender.getValue());
			} else {
				brukerKoieStatusName.setText(activeKoie);
				brukerOdelagteTingField.setText(ferdig�delagt);
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
	
	/**
	 * �pner velkomst-panelet
	 * 
	 * @param event Eventen som kalte denne metoden
	 */
	@FXML
	public void openWelcome(ActionEvent event) { // n�r man trykker p� hjem-knappen
		root.setCenter(welcomePane);
	}

	/**
	 * �pner kartet
	 * 
	 * @param event Eventen som kalte denne metoden
	 */
	@FXML
	public void openMap(ActionEvent event) { // n�r man trykker p� kart-knappen
		mapPane.getChildren().remove(mapBtn);
		root.setCenter(mapPane);
	}

	/**
	 * �pner rapport-panelet
	 * 
	 * @param event Eventen som kalte denne metoden
	 */
	@FXML
	public void openReport(ActionEvent event) { // n�r man trykker p� rapport-knappen
		root.setCenter(reportPane);
	}

	/**
	 * �pner registrerings-skjermen
	 * 
	 * @param event Eventen som kalte denne metoden
	 */
	@FXML
	public void newUser(ActionEvent event) { // n�r man trykker p� "ny bruker" knappen
		root.setCenter(registerScreen);
	}

	/**
	 * Sender en rapport inn i databasen
	 * 
	 * @param event Eventen som kalte denne metoden
	 */
	@FXML
	public void sendRapport(ActionEvent event) { 
		if (rapportDropDown.getValue().equals("Velg en koie")) {
			return;
		}
		connection.settinnRapport(rapportOdelagteTingField.getText(), rapportGjenglemteTingField.getText(), Integer.parseInt(vedstatusField.getText()), rapportDropDown.getValue(), LocalDate.now().toString());
		String �delagt = Rapport.formaterTekst(rapportOdelagteTingField.getText(), "\n");
		String gjenglemt = Rapport.formaterTekst(rapportGjenglemteTingField.getText(), "\n");
		int vedstatus = Integer.parseInt(vedstatusField.getText());
		connection.settinnRapport(�delagt, gjenglemt, vedstatus, rapportDropDown.getValue(), LocalDate.now().toString());
	}

	/**
	 * Fyller og �pner listen over alle registrerte brukere
	 */
	@FXML
	public void openMedlemListe() {
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

	/**
	 * Fors�ker � registrere en ny bruker i databasen
	 * 
	 * @param event Eventen som kalte denne metoden
	 */
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

	/**
	 * Logger ut av systemet
	 * 
	 * @param event Eventen som kalte denne metoden
	 */
	@FXML
	public void logOut(ActionEvent event) { // n�r man trykker p� "logg ut"  eller "tilbake" knappen
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

	/**
	 * Fors�ker � logge inn i systemet
	 * 
	 * @param event Eventen som kalte denne metoden
	 */
	@FXML
	public void logIn(ActionEvent event) { // n�r man trykker p� "logg inn" knappen
		ResultSet dbUserInfo = connection.login(usernameField.getText());
		try {
			if (dbUserInfo.next()) {
				if (dbUserInfo.getString(1).equals(usernameField.getText()) && dbUserInfo.getString(2).equals(passwordField.getText())) {
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
				} else {
					invalidLoginInfo.setVisible(true);
				}
			} else {
				invalidLoginInfo.setVisible(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
