package gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import core.Bruker;
import core.DBConnection;
import core.Epost;
import core.TheFormator;
import core.RapportHandler;
import core.Vedstatus;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * Kontrolleren til GUI.fxml.
 * Håndterer all logikk for GUI i NTNUI Koiesystemet.
 * 
 */
public class GUIController {

	//root
	@FXML private BorderPane root; // ï¿½verste element i gui-hierarkiet

	//admin toolbar
	@FXML private Pane adminToolbar; // knapper for admin

	//bruker toolbar
	@FXML private Pane brukerToolbar; // knapper for bruker

	//koie liste
	@FXML private Pane koieListe; // liste over koiene

	//welcome pane
<<<<<<< Updated upstream
	@FXML private Pane welcomePane; // velkomst panelet (default når man logger inn)
	@FXML private Text welcomeName; // overskriften på welcome-panelet
	@FXML private VBox koieVedstatusListe;
	@FXML private HBox koieVedstatusListeOverskrift;

=======
	@FXML private Pane welcomePane; // velkomst panelet (default nï¿½r man logger inn)
	@FXML private Text welcomeName; // overskriften pï¿½ welcome-panelet
>>>>>>> Stashed changes

	//map pane
	@FXML private Pane mapPane; // kart over koiene
	private Button mapBtn; //knappen som dukker opp nï¿½r man trykker pï¿½ en koie pï¿½ kartet

	//login pane
	@FXML private Pane loginScreen; // login skjerm
	@FXML private TextField usernameField; // tekstfelt for brukernavn i login
	@FXML private PasswordField passwordField; // tekstfelt for passord i login
	@FXML private Label invalidLoginInfo; // skrift som dukker opp nï¿½r man fï¿½r feil ved login

	//register pane
	@FXML private Pane registerScreen; // registrerings skjerm
	@FXML private TextField regFullNameField; // tekstfelt for navn i registrering
	@FXML private TextField regTlfField; // tekstfelt for telefonnummer i registrering
	@FXML private TextField regEpostField; // tekstfelt for epost i registrering
	@FXML private TextField regUsernameField; // tekstfelt for brukernavn i registrering
	@FXML private PasswordField regPasswordField; // tekstfelt for passord i registrering
	@FXML private PasswordField regPasswordFieldConfirmation; // tekstfelt for gjentagelse av passord i registrering
	@FXML private Label registreringsFeil; // skrift som dukker opp nï¿½r man fï¿½r feil ved registrering

	//report pane
	@FXML private Pane reportPane; // rapport panel
	@FXML private ComboBox<String> rapportDropDown; // drop-down meny med alle koiene i rapport-panelet
<<<<<<< Updated upstream
	@FXML private TextField rapportVedstatusField; // tekstfelt for å angi vedstatus i rapport-panelet
=======
<<<<<<< Updated upstream
	@FXML private TextField vedstatusField; // tekstfelt for å angi vedstatus i rapport-panelet
>>>>>>> Stashed changes
	@FXML private VBox rapportOdelagteTingCheckList; // checkliste for å angi ødelagte gjenstander i rapport-panelet
	@FXML private TextArea rapportGjenglemteTingField; // tekstfelt for å angi gjenglemte gjenstander i rapport-panelet
	final ObservableList<CheckListObject> checkListObjectList = FXCollections.observableArrayList(); // liste over ødelagte ting som brukes av rapportOdelagteTingCheckList
=======
	@FXML private TextField vedstatusField; // tekstfelt for ï¿½ angi vedstatus i rapport-panelet
	@FXML private TextArea rapportOdelagteTingField; // tekstfelt for ï¿½ angi ï¿½delagte gjenstander i rapport-panelet
	@FXML private TextArea rapportGjenglemteTingField; // tekstfelt for ï¿½ angi gjenglemte gjenstander i rapport-panelet
>>>>>>> Stashed changes

	//admin koie info pane
	@FXML private Pane adminKoiePane; // koie status panel for admin
<<<<<<< Updated upstream
	@FXML private Text adminKoieStatusName; // overskriften på koie-panelet for admin
	@FXML private TextArea adminKoieAltUtstyrField; // tekstfelt for alt utstyr i koie-panelet for admin
	@FXML private DatePicker adminKalender; // kalenderen i koie-panelet for admin
	@FXML private Label adminKoieVedstatusText;
	@FXML private Text adminAntallSengeplasserText; // tekst med totalt antall sengeplasser i koie-panelet for admin
	@FXML private Text adminLedigeSengeplasserText; // tekst med ledige sengeplasser i koie-panelet for admin
	@FXML private TextField adminKoieLeggTilUtstyrField; // tekstfelt for å legge til nytt utstyr i koie-panelet for admin
	@FXML private ComboBox<String> adminKoieOdelagteTingDropDown; // drop-down meny med ødelagte ting i koie-panelet for admin
	@FXML private ComboBox<String> adminKoieGjenglemteTingDropDown; // drop-down meny med gjenglemte ting i koie-panelet for admin

	//bruker koie info pane
	@FXML private Pane brukerKoiePane; // koie status panel for bruker
	@FXML private Text brukerKoieStatusName; // overskriften på koie-panelet for bruker
	@FXML private TextArea brukerAlleTingField; // tekstfelt for alt utstyr i koie-panelet for bruker
	@FXML private TextArea brukerOdelagteTingField; // tekstfelt for ødelagte gjenstander i koie-panelet for bruker
=======
	@FXML private Text adminKoieStatusName; // overskriften pï¿½ koie-panelet for admin
	@FXML private TextArea adminOdelagteTingField; // tekstfelt for ï¿½delagte gjenstander i koie-panelet for admin
	@FXML private TextArea adminGjenglemteTingField; // tekstfelt for gjenglemte gjenstander i koie-panelet for admin
	@FXML private DatePicker adminKalender; // kalenderen i koie-panelet for admin
	@FXML private Text adminAntallSengeplasserText; // overskriften pï¿½ koie-panelet for admin
	@FXML private Text adminLedigeSengeplasserText; // overskriften pï¿½ koie-panelet for admin

	//bruker koie info pane
	@FXML private Pane brukerKoiePane; // koie status panel for bruker
	@FXML private Text brukerKoieStatusName; // overskriften pï¿½ koie-panelet for bruker
	@FXML private TextArea brukerOdelagteTingField; // tekstfelt for ï¿½delagte gjenstander i koie-panelet for bruker
>>>>>>> Stashed changes
	@FXML private TextArea brukerGjenglemteTingField; // tekstfelt for gjenglemte gjenstander i koie-panelet for bruker
	@FXML private DatePicker brukerKalender; // kalenderen i koie-panelet for bruker
<<<<<<< Updated upstream
	@FXML private Label brukerKoieVedstatusText;
=======
<<<<<<< Updated upstream
>>>>>>> Stashed changes
	@FXML private Text brukerAntallSengeplasserText; // tekst med totalt antall sengeplasser i koie-panelet for bruker
	@FXML private Text brukerLedigeSengeplasserText; // tekst med ledige sengeplasser i koie-panelet for bruker
=======
	@FXML private Text brukerAntallSengeplasserText; // overskriften pï¿½ koie-panelet for bruker
	@FXML private Text brukerLedigeSengeplasserText; // overskriften pï¿½ koie-panelet for bruker
>>>>>>> Stashed changes

	//medlem liste pane
	@FXML private Pane medlemPane; // panel med liste over registrerte brukere i systemet
	@FXML private VBox medlemListe; // liste over medlemmer
	@FXML private HBox medlemListeOverskrift; // overskriften i tabellen (Navn, Tlf, Epost osv...)

	private String activeKoie; // holder styr pï¿½ aktiv koie
	private Bruker bruker; // innlogget bruker i systemet
<<<<<<< Updated upstream
	private DBConnection connection; // håndterer alt av database
=======
	private DBConnection connection; // hï¿½ndterer alt av database ting
>>>>>>> Stashed changes

<<<<<<< Updated upstream
	/**
	 * Initialiserer GUI
	 */
	public void initialize() { //basically konstruktør
<<<<<<< Updated upstream
		rapportDropDown.getItems().addAll("Flåkoia", "Fosenkoia", "Heinfjordstua", "Hognabu", "Holmsåkoia", "Holvassgamma", "Iglbu", "Kamtjønnkoia", "Kråklikåten", "Kvernmovollen", "Kåsen", "Lynhøgen", "Mortenskåten", "Nicokoia", "Rindalsløa", "Selbukåten", "Sonvasskoia", "Stabburet", "Stakkslettbua", "Telin", "Taagaabu", "Vekvessætra", "Øvensenget");
=======
		rapportDropDown.getItems().addAll("Flåkoia", "Fosenkoia", "Heinfjordstua", "Hognabu", "Holmsåkoia", "Holvassgamma", "Iglbu", "Kamtjønna", "Kråkilkåten", "Kvernmovollen", "Kåsen", "Lynhøgen", "Mortenskåten", "Nicokoia", "Rindasløa", "Selbukåten", "Sonvasskoia", "Stabburet", "Stakkslettbua", "Telin", "Taagaabu", "Vekvessætra", "Øvensenget");
<<<<<<< Updated upstream
>>>>>>> Stashed changes
		rapportDropDown.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				activeKoie = rapportDropDown.getValue();
				openReport(null);
			}
		});
=======
=======
	public void initialize() { //basically konstruktï¿½r
		rapportDropDown.getItems().addAll("Flï¿½koia", "Fosenkoia", "Heinfjordstua", "Hognabu", "Holmsï¿½koia", "Holvassgamma", "Iglbu", "Kamtjï¿½nna", "Krï¿½kilkï¿½ten", "Kvernmovollen", "Kï¿½sen", "Lynhï¿½gen", "Mortenskï¿½ten", "Nicokoia", "Rindaslï¿½a", "Selbukï¿½ten", "Sonvasskoia", "Stabburet", "Stakkslettbua", "Telin", "Taagaabu", "Vekvessï¿½tra", "ï¿½vensenget"	);
>>>>>>> Stashed changes
>>>>>>> Stashed changes
		connection = new DBConnection();
		adminKalender.setValue(LocalDate.now()); // setter default dato til idag
		adminKalender.setOnAction(new EventHandler<ActionEvent>() { // nï¿½r man endrer dato
			public void handle(ActionEvent event) {
				oppdaterSengeplasser(true, adminKalender.getValue()); // oppdaterer info om sengeplasser i koie-panelet for admin
			}
		});
		brukerKalender.setValue(LocalDate.now());  // setter default dato til idag
		brukerKalender.setOnAction(new EventHandler<ActionEvent>() { // nï¿½r man endrer dato
			public void handle(ActionEvent event) {
				oppdaterSengeplasser(false, brukerKalender.getValue()); // oppdaterer info om sengeplasser i koie-panelet for bruker
			}
		});
		invalidLoginInfo.setVisible(false); // setter default synlighet for teksten som dukker opp ved feil ved login til false
		registreringsFeil.setVisible(false); // setter default synlighet for teksten som dukker opp ved feil ved registrering til false
		root.setCenter(loginScreen); // setter default panel til login skjermen
		mapBtn = new Button();
		mapBtn.setFocusTraversable(false); // gjï¿½r at man ikke kan "hoppe" til knappen ved ï¿½ trykke pï¿½ tab
		mapBtn.setOnAction(new EventHandler<ActionEvent>() { // nï¿½r man trykker pï¿½ knappen
			public void handle(ActionEvent event) {
				fyllKoiePane(); // finner all informasjon som skal vises i koie-panelet
				if (bruker.isAdmin()) { // hvis bruker er admin
					root.setCenter(adminKoiePane); // viser koie-informasjon for admin
				} else {
					root.setCenter(brukerKoiePane); // viser koie-informasjon for bruker
				}
			}
		});
		mapPane.setOnMousePressed(new EventHandler<MouseEvent>() {  // logikk for ï¿½ hï¿½ndtere trykking pï¿½ knapper pï¿½ kartet
			public void handle(MouseEvent me) {
				if (me.getButton() == MouseButton.PRIMARY) { // hvis venstre museklikk
					activeKoie = ((Node) me.getTarget()).getId(); // finner hvilken koie man trykket pï¿½
					if (activeKoie == null || activeKoie == mapPane.getId()) {  // hvis man ikke trykket pï¿½ en koie
						mapPane.getChildren().remove(mapBtn); // skjuler knappen
						return;
					}
					activeKoie = activeKoie.substring(0, activeKoie.length() - 3); // activeKoie er her f.eks. FosenkoiaMap, fjerner "Map"
					double koieX = ((ImageView) me.getTarget()).getLayoutX(); // finner x-posisjonen til koia pan trykket pï¿½
					double koieY = ((ImageView) me.getTarget()).getLayoutY(); // finner x-posisjonen til koia pan trykket pï¿½
					mapBtn.setText("ï¿½pne " + activeKoie); // setter teksten pï¿½ knappen til f.eks "ï¿½pne FosenKoia"
					mapBtn.setLayoutX(koieX + 25); // plasserer knappen pï¿½ kartet
					mapBtn.setLayoutY(koieY - 25);
					mapPane.getChildren().remove(mapBtn); // hvis knappen allerede er pï¿½ skjermen, sï¿½ fjernes den (gjï¿½res for ï¿½ forhindre error)
					mapPane.getChildren().add(mapBtn); // viser knappen
				}
			}
		});
	}

	@FXML
	private void koieClicked(ActionEvent event) { // nï¿½r man trykker pï¿½ koie i lista
		((Hyperlink) event.getSource()).setVisited(false); // gjï¿½r at linkene i koia-lista ikke for strek under seg nï¿½r man trykker pï¿½ de
		activeKoie = ((Hyperlink) event.getSource()).getText(); // setter aktivKoie til koia man trykket pï¿½
		fyllKoiePane(); // finner all informasjon som skal vises i koie-panelet
		if (bruker.isAdmin()) {
			root.setCenter(adminKoiePane); // viser koie-informasjon for admin
		} else {
			root.setCenter(brukerKoiePane); // viser koie-informasjon for bruker
		}
	}

	private void openKoie(String koie) {
		activeKoie = koie;
		fyllKoiePane();
		if (bruker.isAdmin()) {
			root.setCenter(adminKoiePane);
		} else {
			root.setCenter(brukerKoiePane);
		}
	}

	private void fyllKoiePane() { // finner all informasjon som skal vises i koie-panelet
		try {
<<<<<<< Updated upstream
			ResultSet altDB = connection.getAltUtstyr(TheFormator.formaterKoieNavn(activeKoie));
			ResultSet odelagtDB = connection.getOdelagt(TheFormator.formaterKoieNavn(activeKoie));
			ResultSet gjenglemtDB = connection.getGjenglemt(TheFormator.formaterKoieNavn(activeKoie));
=======
<<<<<<< Updated upstream
			ResultSet altDB = connection.getAltUtstyr(Koie.formaterKoieNavn(activeKoie));
			ResultSet odelagtDB = connection.getOdelagt(Koie.formaterKoieNavn(activeKoie));
			ResultSet gjenglemtDB = connection.getGjenglemt(Koie.formaterKoieNavn(activeKoie));
>>>>>>> Stashed changes
			String alt = "";
			String ødelagt = ""; // string som fylles med alle ødelagte gjenstander
			String gjenglemt = ""; // string som fylles med alle gjenglemte gjenstander
			while (altDB.next()) {
				alt += ";" + altDB.getString(1);
			}
			while (odelagtDB.next()) { // mens det finnes flere elementer i databasen
				ødelagt += ";" + odelagtDB.getString(1); // ;ødelagt1;ødelagt2;ødelagt3
			}
			while (gjenglemtDB.next()) {
				gjenglemt += ";" + gjenglemtDB.getString(1); // ;gjenglemt1;gjenglemt2;gjenglemt3
			}
			String ferdigAlt = "";
			String[] altListe = alt.split(";");
			for (int i = 1; i < altListe.length; i++) {
				ferdigAlt += altListe[i] + "\n";
			}
			adminKoieOdelagteTingDropDown.getItems().clear();
			String ferdigØdelagt = ""; // string som skal settes inn i tekstfeltet for ødelagte gjenstander
			String[] ødelagtListe = ødelagt.split(";");
			for (int i = 1; i < ødelagtListe.length; i++) {
				adminKoieOdelagteTingDropDown.getItems().add(ødelagtListe[i]);
				ferdigØdelagt += ødelagtListe[i] + "\n";
=======
			ResultSet rs = connection.getOdelagtGjenglemtKoie(Koie.formaterKoieNavn(activeKoie)); // fï¿½r alle ï¿½delagte og gjenglemte gjenstander fra databasen
			String Ã¸delagt = ""; // string som fylles med alle ï¿½delagte gjenstander
			String gjenglemt = ""; // string som fylles med alle gjenglemte gjenstander
			while (rs.next()) { // mens det finnes flere elementer i databasen
				Ã¸delagt += ";" + rs.getString(1); // ;ï¿½delagt1;ï¿½delagt2;ï¿½delagt3
				gjenglemt += ";" + rs.getString(2); // ;gjenglemt1;gjenglemt2;gjenglemt3
			}
			String ferdigÃ¸delagt = ""; // string som skal settes inn i tekstfeltet for ï¿½delagte gjenstander
			if (Ã¸delagt.length() > 0) { // hvis det finnes minst en ï¿½delagt gjenstand
				Ã¸delagt = Ã¸delagt.substring(1); // fjerner den fï¿½rste semikolonen
				String[] Ã¸delagtListe = Ã¸delagt.split(";");
				for (int i = 0; i < Ã¸delagtListe.length; i++) {
					ferdigÃ¸delagt += Ã¸delagtListe[i] + "\n";
				}
>>>>>>> Stashed changes
			}
			adminKoieGjenglemteTingDropDown.getItems().clear();
			String ferdigGjenglemt = ""; // string som skal settes inn i tekstfeltet for gjenglemte gjenstander
			if (gjenglemt.length() > 0) { // hvis det finnes minst en gjenglemt gjenstand
				gjenglemt = gjenglemt.substring(1); // fjerner den fï¿½rste semikolonen
				String[] gjenglemtListe = gjenglemt.split(";");
				for (int i = 0; i < gjenglemtListe.length; i++) {
					adminKoieGjenglemteTingDropDown.getItems().add(gjenglemtListe[i]);
					ferdigGjenglemt += gjenglemtListe[i] + "\n";
				}
			}
			int vedEstimat = Vedstatus.lagVedEstimat(TheFormator.formaterKoieNavn(activeKoie));
			if (bruker.isAdmin()) {
				if (adminKoieOdelagteTingDropDown.getItems().size() > 0) {					
					adminKoieOdelagteTingDropDown.setValue(adminKoieOdelagteTingDropDown.getItems().get(0));
				} else {
					adminKoieOdelagteTingDropDown.setValue("");
				}
				if (adminKoieGjenglemteTingDropDown.getItems().size() > 0) {					
					adminKoieGjenglemteTingDropDown.setValue(adminKoieGjenglemteTingDropDown.getItems().get(0));
				} else {
					adminKoieGjenglemteTingDropDown.setValue("");
				}
				adminKoieStatusName.setText(activeKoie); // setter inn all informasjonen i koie-panelet
<<<<<<< Updated upstream
				adminKoieAltUtstyrField.setText(ferdigAlt.trim());
				if (vedEstimat == -1) {
					adminKoieVedstatusText.setText("Utilstrekkelig data");
				} else {
					adminKoieVedstatusText.setText(vedEstimat + " dager");
				}
				oppdaterSengeplasser(true, adminKalender.getValue());
			} else {
				brukerKoieVedstatusText.setText(Vedstatus.lagVedEstimat(TheFormator.formaterKoieNavn(activeKoie)) + " dager");
				brukerKoieStatusName.setText(activeKoie);
				brukerAlleTingField.setText(ferdigAlt);
				brukerOdelagteTingField.setText(ferdigØdelagt);
=======
				adminOdelagteTingField.setText(ferdigÃ¸delagt);
				adminGjenglemteTingField.setText(ferdigGjenglemt);
				oppdaterSengeplasser(true, adminKalender.getValue());
			} else {
				brukerKoieStatusName.setText(activeKoie);
				brukerOdelagteTingField.setText(ferdigÃ¸delagt);
>>>>>>> Stashed changes
				brukerGjenglemteTingField.setText(ferdigGjenglemt);
				if (vedEstimat == -1) {
					brukerKoieVedstatusText.setText("Utilstrekkelig data");
				} else {
					brukerKoieVedstatusText.setText(vedEstimat + " dager");
				}
				oppdaterSengeplasser(false, brukerKalender.getValue());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void oppdaterSengeplasser(boolean isAdmin, LocalDate date) { // setter inn riktig antall totalt/tilgjengelige sengeplasser
		try {
			ResultSet antallSengeplasser = connection.getSengeplasser(TheFormator.formaterKoieNavn(activeKoie));
			ResultSet reserverteSengeplasser = connection.getReservertePlasser(TheFormator.formaterKoieNavn(activeKoie), date.toString());
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
<<<<<<< Updated upstream
	private void openURL(ActionEvent event) {
		Desktop desktop = Desktop.getDesktop();
		try {
			URI url = new URI("http://org.ntnu.no/koiene/koiene/koiene.php?k=" + (TheFormator.formaterKoieNavn(activeKoie)).toLowerCase());
			desktop.browse(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void openWelcome(ActionEvent event) { // når man trykker på hjem-knappen
		koieVedstatusListe.getChildren().clear(); // fjerner listen hvis den har blitt fylt før
		koieVedstatusListe.getChildren().add(koieVedstatusListeOverskrift); // setter inn overskriftene
		for (int i = 0; i < rapportDropDown.getItems().size(); i++) {
			int vedEstimat;
			try {
				vedEstimat = Vedstatus.lagVedEstimat(TheFormator.formaterKoieNavn(rapportDropDown.getItems().get(i)));
				HBox hbox = new HBox();
				Label koieNavn = new Label(rapportDropDown.getItems().get(i));
				koieNavn.setPrefWidth(100);
				Label vedstatus = new Label("" + vedEstimat);
				if (vedEstimat < 20) {
					vedstatus.setStyle("-fx-text-fill: RED");
					if (vedEstimat == -1) {
						vedstatus.setText("Utilstrekkelig data");
					}
				}
				hbox.getChildren().addAll(koieNavn, vedstatus);
				koieVedstatusListe.getChildren().add(hbox);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
=======
	private void openWelcome(ActionEvent event) { // nï¿½r man trykker pï¿½ hjem-knappen
>>>>>>> Stashed changes
		root.setCenter(welcomePane);
	}

	@FXML
	private void openMap(ActionEvent event) { // nï¿½r man trykker pï¿½ kart-knappen
		mapPane.getChildren().remove(mapBtn);
		root.setCenter(mapPane);
	}

	@FXML
<<<<<<< Updated upstream
	private void openReport(ActionEvent event) { // når man trykker på rapport-knappe
<<<<<<< Updated upstream
		if (rapportDropDown.getValue() == "Velg en koie") {
			root.setCenter(reportPane);
			return;
		}
		checkListObjectList.clear();
		try {
			ResultSet altUtstyr = connection.getAltUtstyr(TheFormator.formaterKoieNavn(rapportDropDown.getValue()));
			while (altUtstyr.next()) {
				checkListObjectList.add(new CheckListObject(altUtstyr.getString(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		final ListView<CheckListObject> listView = new ListView<CheckListObject>();
		listView.setPrefSize(200, 250);
		listView.setEditable(true);
		listView.setItems(checkListObjectList);
		Callback<CheckListObject, ObservableValue<Boolean>> getProperty = new Callback<CheckListObject, ObservableValue<Boolean>>() {
			@Override
			public BooleanProperty call(CheckListObject layer) {

				return layer.selectedProperty();

			}
		};
		Callback<ListView<CheckListObject>, ListCell<CheckListObject>> forListView = CheckBoxListCell.forListView(getProperty);
		listView.setCellFactory(forListView);
		rapportOdelagteTingCheckList.getChildren().clear();
		rapportOdelagteTingCheckList.getChildren().addAll(listView);
=======
=======
	private void openReport(ActionEvent event) { // nï¿½r man trykker pï¿½ rapport-knappen
>>>>>>> Stashed changes
>>>>>>> Stashed changes
		root.setCenter(reportPane);
	}

	@FXML
	private void newUser(ActionEvent event) { // nï¿½r man trykker pï¿½ "ny bruker" knappen
		root.setCenter(registerScreen);
	}

	@FXML
	private void sendRapport(ActionEvent event) {  // nï¿½r man trykker pï¿½ "send rapport" knappen i rapport-panelet
		if (rapportDropDown.getValue().equals("Velg en koie")) { // hvis man ikke har valgt en koie
			return;
		}
<<<<<<< Updated upstream
		String ødelagt = "";
		for (CheckListObject odelagt : checkListObjectList) {
			if (odelagt.getSelected()) {				
				ødelagt += ";" + odelagt.getName();
			}
		}
		if (ødelagt.length() > 0) {			
			ødelagt = ødelagt.substring(1);
		}
<<<<<<< Updated upstream
		String gjenglemt = TheFormator.formaterTekst(rapportGjenglemteTingField.getText(), "\n");
=======
=======
<<<<<<< Updated upstream
		String ødelagt = RapportHandler.formaterTekst(rapportOdelagteTingField.getText(), "\n");
>>>>>>> Stashed changes
		String gjenglemt = RapportHandler.formaterTekst(rapportGjenglemteTingField.getText(), "\n");
>>>>>>> Stashed changes
		int vedstatus = 0;
		if (rapportVedstatusField.getText().length() != 0) {
			vedstatus = Integer.parseInt(rapportVedstatusField.getText());
		}
		try {
<<<<<<< Updated upstream
			connection.settinnRapport(ødelagt, gjenglemt, vedstatus, TheFormator.formaterKoieNavn(rapportDropDown.getValue()), LocalDate.now().toString());
			if (ødelagt.length() > 0) {
				String[] ødelagtListe = ødelagt.split(";");
				for (int i = 0; i < ødelagtListe.length; i++) {
					RapportHandler.Odelegg(ødelagtListe[i], TheFormator.formaterKoieNavn(activeKoie), ødelagt, gjenglemt, vedstatus);
				}
			}
			ResultSet rapportID = connection.getRapportID(ødelagt, gjenglemt, vedstatus);
			if (rapportID.next()) {
				RapportHandler.glemt(gjenglemt, TheFormator.formaterKoieNavn(activeKoie), Integer.parseInt(rapportID.getString(1)));
			}
=======
			connection.settinnRapport(ødelagt, gjenglemt, vedstatus, Koie.formaterKoieNavn(rapportDropDown.getValue()), LocalDate.now().toString());
=======
		String Ã¸delagt = Rapport.formaterTekst(rapportOdelagteTingField.getText(), "\n");
		String gjenglemt = Rapport.formaterTekst(rapportGjenglemteTingField.getText(), "\n");
		int vedstatus = Integer.parseInt(vedstatusField.getText());
		try {
			connection.settinnRapport(Ã¸delagt, gjenglemt, vedstatus, rapportDropDown.getValue(), LocalDate.now().toString());
>>>>>>> Stashed changes
>>>>>>> Stashed changes
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void openMedlemListe() { // nï¿½r man trykker pï¿½ medlemsliste-knappen
		medlemListe.getChildren().clear(); // fjerner listen hvis den har blitt fylt fï¿½r
		medlemListe.getChildren().add(medlemListeOverskrift); // setter inn overskriftene
		ResultSet rs = connection.getMembers();  // Brukernavn, Navn, Tlf, Epost, isAdmin
		try {
			while (rs.next()) { // mens det er flere medlemmer
				HBox hbox = new HBox(); // gjï¿½r masse rart...
				for (int i = 1; i < 5; i++) {
					Label text = new Label(rs.getString(i));
					if (i == 3) { // eposten trenger litt mer plass enn de andre feltene
						text.setPrefWidth(200);
					} else {
						text.setPrefWidth(100);
					}
					if (i == 4) {
						if(rs.getString(i).equals("0")) {
							text.setText("Nei");
						} else {
							text.setText("Ja");
						}
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
<<<<<<< Updated upstream
	private void fiksUtstyr(ActionEvent event) {
		try {
			connection.fixUtstyr(TheFormator.formaterKoieNavn(activeKoie), adminKoieOdelagteTingDropDown.getValue());
			openKoie(activeKoie);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void finnUtstyr(ActionEvent event) {
		try {
			connection.funnetTing(adminKoieGjenglemteTingDropDown.getValue());
			openKoie(activeKoie);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void veddugnadUtfort(ActionEvent event) {
		try {
			connection.datoVeddugnad(TheFormator.formaterKoieNavn(activeKoie), LocalDate.now().toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		openKoie(activeKoie);
	}

	@FXML
	private void leggTilUtstyr(ActionEvent event) {
		try {
			ResultSet rs = connection.getUtstyrID(adminKoieLeggTilUtstyrField.getText(), TheFormator.formaterKoieNavn(activeKoie));
			if (rs.next()) { // hvis utstyr allerede finnes
				System.out.println("utstyr finnes allerede");
				adminKoieLeggTilUtstyrField.setText("");
				return;
			}
			connection.registrerUtstyr(adminKoieLeggTilUtstyrField.getText(), LocalDate.now().toString(), 1, bruker.getBrukernavn(), TheFormator.formaterKoieNavn(activeKoie));
			String epostAdresse = "";
			ResultSet epostDB = connection.getReservasjonsEpost(TheFormator.formaterKoieNavn(activeKoie), LocalDate.now().toString());
			if (epostDB.next()) {
				epostAdresse = epostDB.getString(1);
			}
			if (epostAdresse.length() != 0) {
				Epost epost = new Epost();
				epost.setSub("Frakting av utstyr til " + activeKoie);
				epost.setMes("Hei, du/dere må ta med " + adminKoieLeggTilUtstyrField.getText() + " til " + activeKoie);
				epost.sendMessage(epostAdresse);
			} else {
				System.out.println("ingen å sende epost til");
			}
			adminKoieLeggTilUtstyrField.setText("");
			openKoie(activeKoie);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void register(ActionEvent event) { // når man trykker på "registrer" knappen
=======
	private void register(ActionEvent event) { // nï¿½r man trykker pï¿½ "registrer" knappen
>>>>>>> Stashed changes
		try {
			ResultSet rs = connection.login(regUsernameField.getText());
			if (regUsernameField.getText().equals("") || regPasswordField.getText().equals("") || regFullNameField.getText().equals("") || regTlfField.getText().equals("") || regEpostField.getText().equals("")) {
				registreringsFeil.setText("Ingen felter kan vï¿½re tomme");
				registreringsFeil.setVisible(true);
			}
			else if (rs.next()) { // hvis det finnes et element i ResultSetet sï¿½ finnes det allerede en bruker med det brukernavnet
				registreringsFeil.setText("Brukernavn er allerede i bruk");
				registreringsFeil.setVisible(true);
			} else if (regPasswordField.getText().equals(regPasswordFieldConfirmation.getText())) { // passordfeltene mï¿½ vï¿½re like
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
	private void logOut(ActionEvent event) { // nï¿½r man trykker pï¿½ "logg ut"  eller "tilbake" knappen
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
	private void logIn(ActionEvent event) { // nï¿½r man trykker pï¿½ "logg inn" knappen
		try {
			ResultSet dbUserInfo = connection.login(usernameField.getText());
			if (dbUserInfo.next()) { // true hvis det angitte brukernavnet finnes i databasen
				if (dbUserInfo.getString(1).equals(usernameField.getText()) && dbUserInfo.getString(2).equals(passwordField.getText())) { // hvis brukernavn og passord stemmer overens
					bruker = new Bruker(usernameField.getText(), dbUserInfo.getString(3), dbUserInfo.getString(4), dbUserInfo.getString(5), dbUserInfo.getString(6));
					welcomeName.setText("Velkommen, " + dbUserInfo.getString(3));
					if (bruker.isAdmin()) {
						root.setLeft(koieListe);
						openWelcome(null);
						root.setTop(adminToolbar);
					} else {
						root.setLeft(koieListe);
						openWelcome(null);
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
