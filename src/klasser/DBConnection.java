package klasser;

import java.sql.ResultSet;
import database.DB;

public class DBConnection {

	private DB db;
	
	public DBConnection(){
		db = new DB();
	}
	
	// Returnerer ResultSet med kolonne1 = brukernavn og kolonne2 = passord
	public ResultSet login(String brukernavn) {
		
		String q = ("select Brukernavn, Passord from Admin where Brukernavn = '"
				+ brukernavn + "';");

		return db.sporDB(q);
	}
	
	// Registrere ny bruker
	public void registrerBruker(String brukernavn, String passord) {
		
		String q = ("insert into Admin (Brukernavn, Passord) values ('"
				+ brukernavn
				+ "','"
				+ passord
				+ "');");
		
		db.oppdaterDB(q);
	}

	//Sett inn rapport i DB
	public void settinnRapport(String tekst, String gjenglemt, int vedstatus,
			String koienavn) {
		
		String q = ("insert into Rapport (tekst, gjenglemt, vedstatus, koierapportID) values ('"
				+ tekst
				+ "','"
				+ gjenglemt
				+ "','"
				+ (new String("" + vedstatus)) + "','" + koienavn + "');");

		db.oppdaterDB(q);
	}
	
	//Sett inn reservasjon i DB
	public void settinnReservasjon(String epost, String datoFra, String datoTil, String koienavn){
		
		String q = ("insert into Reservasjon (epost,datoFra,datoTil,reservertkoieid) values ('"
				+ epost + "','" + datoFra + "','" + datoTil + "','" + koienavn + "');");

		db.oppdaterDB(q);
	}
	
	// Oppdater en koies vedstatus
	public void oppdaterVedstatus(String koienavn, int vedstatus) {

		String q = ("update Koie set Vedstatus = '"
				+ (new String("" + vedstatus)) + "' where KoieID = '"
				+ koienavn + "';");

		db.oppdaterDB(q);
	}

	// Oppdater Utstyrsstatus 1= i live, 0= ødelagt
	public void oppdaterUtstyr(int utstyrsID, int status) {

		String q = ("update Utstyr set stat = '" + (new String("" + status))
				+ "' where UtstyrsID = '" + (new String("" + utstyrsID)) + "';");

		db.oppdaterDB(q);
	}

	// Legg inn ødelagt Utstyr
	public void leggInnOdelagtUtstyr(int utstyrsID, int rapportID) {

		String q = ("insert into ErOdelagt values ('"
				+ (new String("" + utstyrsID)) + "','"
				+ (new String("" + rapportID)) + "');");

		db.oppdaterDB(q);
	}

	// Returner alt ødelagt utstyr
	public ResultSet getOdelagtUtstyr() {

		String q = ("select * from ErOdelagt;");

		return db.sporDB(q);
	}

	// Legge inn nytt utstyr (STATUS 1 eller 0!)
	public void registrerUtstyr(String navn, String dato, int status, String adminID, String koie){
		
		String q = ("insert into Utstyr (navn, innkjopsdato, stat, adminID,fraktesTilID) VALUES ('" + navn + "','" + dato + "','" + (new String("" + status)) + "','" + adminID + "','" + koie + "');");
		
		db.oppdaterDB(q);
	}
	
}
