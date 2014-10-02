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
import klasser.Rapport;

public class GUIController {

	@FXML private BorderPane root; // ï¿½verste element i gui-hierarkiet
	@FXML private TextField usernameField; // tekstfeltene for login og registrering
	@FXML private PasswordField passwordField;
	@FXML private TextField regFullNameField;
	@FXML private TextField regTlfField;
	@FXML private TextField regEpostField;
	@FXML private TextField regUsernameField;
	@FXML private PasswordField regPasswordField;
	@FXML private PasswordField regPasswordFieldConfirmation;
	@FXML private TextField vedstatusField;
	@FXML private TextArea Ã¸delagteTingField;
	@FXML private TextArea gjenglemteTingField;
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
<<<<<<< HEAD
	@FXML private Text welcomeName; // overskriften pï¿½ welcome-panelet
	@FXML private Text koieReservasjonsName; // overskriften pï¿½ reservasjons-panelet
	@FXML private Text koieStatusName; // overskriften pï¿½ koie-panelet
	@FXML private Label feilLoginInfo; // skrift som dukker opp nï¿½r man prï¿½ver ï¿½ logge inn med feil info
	@FXML private Label registreringsFeil; // skrift som dukker opp nï¿½r man fï¿½r feil ved registrering
	private String activeKoie; // holder styr pï¿½ aktiv koie
	private Button mapBtn; //knappen som dukker opp nï¿½r man trykker pï¿½ en koie pï¿½ kartet
=======
	@FXML private Pane medlemPane;
	@FXML private VBox medlemListe;
	@FXML private HBox medlemListeOverskrift;
	@FXML private Text welcomeName; // overskriften på welcome-panelet
	@FXML private Text koieReservasjonsName; // overskriften på reservasjons-panelet
	@FXML private Text koieStatusName; // overskriften på koie-panelet
	@FXML private Label feilLoginInfo; // skrift som dukker opp når man prøver å logge inn med feil info
	@FXML private Label registreringsFeil; // skrift som dukker opp når man får feil ved registrering
	private String activeKoie; // holder styr på aktiv koie
	private Button mapBtn; //knappen som dukker opp når man trykker på en koie på kartet
>>>>>>> FETCH_HEAD
	private Bruker admin;
	private DBConnection connection;
	@FXML DatePicker kalender; // kalenderen i koie-panelet
	@FXML DatePicker reservasjonFra; // kalenderen i koie-panelet
	@FXML DatePicker reservasjonTil; // kalenderen i koie-panelet
	LocalDate ld; // aktiv dato i kalenderen
	@FXML ComboBox<String> rapportDropDown;

	public void initialize() { //basically konstruktï¿½r
		rapportDropDown.getItems().addAll("Flï¿½koia", "Fosenkoia", "Heinfjordstua", "Hognabu", "Holmsï¿½koia", "Holvassgamma", "Iglbu", "Kamtjï¿½nna", "Krï¿½kilkï¿½ten", "Kvernmovollen", "Kï¿½sen", "Lynhï¿½gen", "Mortenskï¿½ten", "Nicokoia", "Rindaslï¿½a", "Selbukï¿½ten", "Sonvasskoia", "Stabburet", "Stakkslettbua", "Telin", "Taagaabu", "Vekvessï¿½tra", "ï¿½vensenget"	);
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
		mapBtn.setFocusTraversable(false); //gjï¿½r at man ikke kan "hoppe" til knappen ved ï¿½ trykke pï¿½ tab
		mapBtn.setOnAction(new EventHandler<ActionEvent>() { //nï¿½r man trykker pï¿½ knappen
			public void handle(ActionEvent event) {
				if (admin.getAdminStatus()) {
					koieStatusName.setText(activeKoie.substring(0, activeKoie.length() - 3));
					root.setCenter(koiePane);
				} else {
					koieReservasjonsName.setText(activeKoie.substring(0, activeKoie.length() - 3));
					root.setCenter(reservasjonsPane);
				}
			}
		});
		mapPane.setOnMousePressed(new EventHandler<MouseEvent>() {  // logikk for ï¿½ hï¿½ndtere trykking
			public void handle(MouseEvent me) {						// pï¿½ knapper pï¿½ kartet
				if (me.getButton() == MouseButton.PRIMARY) { // hvis venstre museklikk
					activeKoie = ((Node) me.getTarget()).getId();
					if (activeKoie == null || activeKoie == mapPane.getId()) {  // hvis man ikke trykka pï¿½
						mapPane.getChildren().remove(mapBtn);					// en koie
						return;
					}
					double koieX = ((ImageView) me.getTarget()).getLayoutX(); // plasserer knappen pï¿½ kartet
					double koieY = ((ImageView) me.getTarget()).getLayoutY();
					mapBtn.setText("ï¿½pne " + activeKoie.substring(0, activeKoie.length() - 3));
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
	public void koieClicked(ActionEvent event) { // nï¿½r man trykker pï¿½ koie i lista
		((Hyperlink) event.getSource()).setVisited(false);
		activeKoie = ((Hyperlink) event.getSource()).getText();
		if (admin.getAdminStatus()) {
			koieStatusName.setText(activeKoie);
			root.setCenter(koiePane);			
		} else {
			koieReservasjonsName.setText(activeKoie);
			root.setCenter(reservasjonsPane);
		}
	}

	@FXML
	public void openWelcome(ActionEvent event) { // nï¿½r man trykker pï¿½ hjem-knappen
		root.setCenter(welcomePane);
	}

	@FXML
	public void openMap(ActionEvent event) { // nï¿½r man trykker pï¿½ kart-knappen
		mapPane.getChildren().remove(mapBtn);
		root.setCenter(mapPane);
	}

	@FXML
	public void openReport(ActionEvent event) { // nï¿½r man trykker pï¿½ rapport-knappen
		root.setCenter(reportPane);
	}
	
	@FXML
	public void openReservasjon(ActionEvent event) {
		root.setCenter(reservasjonsPane);
	}

	@FXML
	public void newUser(ActionEvent event) { // nï¿½r man trykker pï¿½ "ny bruker" knappen
		root.setCenter(registerScreen);
	}
	
	@FXML
	public void sendRapport(ActionEvent event) { 
		if (rapportDropDown.getValue().equals("Velg en koie")) {
			return;
		}
<<<<<<< HEAD
		connection.settinnRapport(ï¿½delagteTingField.getText(), gjenglemteTingField.getText(), Integer.parseInt(vedstatusField.getText()), rapportDropDown.getValue());
=======
		String ødelagt = Rapport.formaterTekst(ødelagteTingField.getText(), "\n");
		String gjenglemt = Rapport.formaterTekst(gjenglemteTingField.getText(), "\n");
		int vedstatus = Integer.parseInt(vedstatusField.getText());
		connection.settinnRapport(ødelagt, gjenglemt, vedstatus, rapportDropDown.getValue());
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
>>>>>>> FETCH_HEAD
	}

	@FXML
	public void register(ActionEvent event) { // nï¿½r man trykker pï¿½ "registrer" knappen
		ResultSet rs = connection.login(regUsernameField.getText());
		try {
			if (regUsernameField.getText().equals("") || regPasswordField.getText().equals("") || regFullNameField.getText().equals("") || regTlfField.getText().equals("") || regEpostField.getText().equals("")) {
				registreringsFeil.setText("Ingen felter kan vï¿½re tomme");
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
	public void logOut(ActionEvent event) { // nï¿½r man trykker pï¿½ "logg ut"  eller "tilbake" knappen
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
	public void logIn(ActionEvent event) { // nï¿½r man trykker pï¿½ "logg inn" knappen
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
