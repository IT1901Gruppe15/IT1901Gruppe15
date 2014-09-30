package klasser;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DB;

public class DBConnection {

	private DB db;

	public DBConnection(){
		db = new DB();
	}

	// Returnerer ResultSet med kolonne1 = brukernavn og kolonne2 = passord
	public ResultSet login(String brukernavn) {

		String q = ("select Brukernavn, Passord, Navn, Tlf, Epost, isAdmin from Admin where Brukernavn = '"
				+ brukernavn + "';");

		return db.sporDB(q);
	}

	// Registrere ny bruker
	public void registrerBruker(String brukernavn, String passord, String navn, String tlf, String epost) {

		String q = ("insert into Admin (Brukernavn, Passord, Navn, Tlf, Epost, isAdmin) values ('"
				+ brukernavn
				+ "','"
				+ passord
				+ "','"
				+ navn
				+ "','"
				+ tlf
				+ "','"
				+ epost
				+ "','"
				+ "0" // vanlig bruker (ikke admin)
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

	// Få utstyrsID
	public String getUtstyrID(String navn, String koie) throws SQLException {
		
		String q = ("select UtstyrsID from Utstyr where Navn = '" + navn + "' and FraktesTilID = '" + koie + "';");
		
		ResultSet k=db.sporDB(q);
		if (k.next()){
			return k.getString(1);
		}
		return null;
	}


	// Returnerer liste over alle medlemmer
	public ResultSet getMembers() {
		
		String q = ("select Navn, Tlf, Epost, isAdmin from Admin");
		
		return db.sporDB(q);
	}

	// Få rapportID
	public String getrapportID(String tekst, String gjenglemt, int vedstatus) throws SQLException {
			
			String q = ("select RapportID from Rapport where Tekst = '" + tekst + "' and Gjenglemt = '" + gjenglemt + "' and Vedstatus = '" + vedstatus + "';");
			
			ResultSet k=db.sporDB(q);
			if (k.next()){
				return k.getString(1);
			}
			return null;
		}

	// Returner alt ødelagt utstyr
	public ResultSet getOdelagtUtstyr(String koieID) {

		String q = ("select * from ErOdelagt where koieID = '" + koieID + "';");

		return db.sporDB(q);
	}

	// Legge inn nytt utstyr (STATUS 1 eller 0!)
	public void registrerUtstyr(String navn, String dato, int status, String adminID, String koie){

		String q = ("insert into Utstyr (navn, innkjopsdato, stat, adminID,fraktesTilID) VALUES ('" + navn + "','" + dato + "','" + (new String("" + status)) + "','" + adminID + "','" + koie + "');");

		db.oppdaterDB(q);
	}

}
