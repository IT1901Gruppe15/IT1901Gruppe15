package core;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DB;

public class DBConnection { // noen andre får oppdatere de siste javadocene i denne klassen siden jeg vet ikke helt hva de gjør :S

	private DB db;

	public DBConnection(){
		db = new DB();
	}

	/**
	 * Returnerer et ResultSet med brukerinfo som hører til brukernavnet til den som prøver å logge inn
	 * 
	 * @param brukernavn Brukernavnet til den som prøver å logge inn
	 * @return ResultSet med en rad der col1 = brukernavn, col2 = passord, col3 = fullt navn, col4 = telefonnummer, col5 = epost, col6 = isAdmin
	 */
	public ResultSet login(String brukernavn) {

		String q = ("select Brukernavn, Passord, Navn, Tlf, Epost, isAdmin from Admin where Brukernavn = '"
				+ brukernavn 
				+ "';");

		return db.sporDB(q);
	}

	/**
	 * Registrerer en ny bruker i databasesystemet
	 * 
	 * @param brukernavn Brukernavnet til brukeren
	 * @param passord Passordet til brukeren
	 * @param navn Fullt navn til brukeren
	 * @param tlf Telefonnummer til brukeren
	 * @param epost Epostadressen til brukeren
	 */
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

	/**
	 * Setter inn en rapport i databasesystemet
	 * 
	 * @param odelagt Liste over ting som er ødelagt, separert med semikolon
	 * @param gjenglemt Liste over ting som er gjenglemt, separert med semikolon
	 * @param vedstatus Vedstatus for koie (gitt som prosent?)
	 * @param koienavn Navnet på koie
	 * @param dato Datoen rapporten blir satt inn
	 */
	public void settinnRapport(String odelagt, String gjenglemt, int vedstatus,
			String koienavn, String dato) {

		String q = ("insert into Rapport (odelagt, gjenglemt, vedstatus, koierapportID, dato) values ('"
				+ odelagt
				+ "','"
				+ gjenglemt
				+ "','"
				+ (new String("" + vedstatus))
				+ "','"
				+ koienavn 
				+ "','" 
				+ dato 
				+ "');");

		db.oppdaterDB(q);
	}

	/**
	 * Setter inn en reservasjon i databasesystemet
	 * 
	 * @param epost Epostadresse til den som reserverer
	 * @param dato Dato for reservajon
	 * @param koienavn Hvilken koie reservasjonen går til
	 */
	public void settinnReservasjon(String epost, String dato, String koienavn){

		String q = ("insert into Reservasjon (epost,dato,reservertkoieid) values ('"
				+ epost 
				+ "','" 
				+ dato 
				+ "','" 
				+ koienavn 
				+ "');");

		db.oppdaterDB(q);
	}

	/**
	 * Oppdaterer en koies vedstatus
	 * 
	 * @param koienavn Navnet på koien som skal oppdateres
	 * @param vedstatus Ny vedstatus
	 */
	public void oppdaterVedstatus(String koienavn, int vedstatus) {

		String q = ("update Koie set Vedstatus = '"
				+ (new String("" + vedstatus)) 
				+ "' where KoieID = '"
				+ koienavn + "';");

		db.oppdaterDB(q);
	}

	/**
	 * Oppdaterer status for et bestemt utstyr
	 * 
	 * @param utstyrsID Hvilket utstyr som skal oppdateres
	 * @param status 0 = ødelagt, 1 = fungerer
	 */
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
	public ResultSet getUtstyrID(String navn, String koie) throws SQLException {

		String q = ("select UtstyrsID from Utstyr where Navn = '" + navn + "' and FraktesTilID = '" + koie + "';");

		return db.sporDB(q);
	}

	/**
	 * Returnerer et ResultSet med personlig informasjon for alle registrerte medlemmer
	 * 
	 * @return ResultSet der col1 = fullt navn, col2 = telefonnummber, col3 = epostadresse, col4 = isAdmin
	 */
	public ResultSet getMembers() {

		String q = ("select Navn, Tlf, Epost, isAdmin from Admin");

		return db.sporDB(q);
	}

	// Få rapportID
	public ResultSet getrapportID(String odelagt, String gjenglemt,
			int vedstatus) throws SQLException {

		String q = ("select RapportID from Rapport where Odelagt = '" 
				+ odelagt
				+ "' and Gjenglemt = '" 
				+ gjenglemt 
				+ "' and Vedstatus = '"
				+ vedstatus 
				+ "';");

		return db.sporDB(q);
	}

	/**
	 * Returnerer alt ødelagt utstyr for en koie
	 * 
	 * @param koieID Koie der man skal finne alt ødelagt utstyr
	 * @return ResultSet med alt ødelagt utsyr i en koie
	 */
	public ResultSet getOdelagtUtstyr(String koieID) {

		String q = ("select * from ErOdelagt where koieID = '" 
				+ koieID 
				+ "';");

		return db.sporDB(q);
	}

	/**
	 * Legger inn nytt utstyr i en koie
	 * 
	 * @param navn Navnet til det nye utstyret
	 * @param dato Datoen utstyret blir lagt til
	 * @param status 0 = ødelagt, 1 = fungerer
	 * @param adminID ID til den innloggede brukeren
	 * @param koie Koien utstyret skal legges til
	 */
	public void registrerUtstyr(String navn, String dato, int status, String adminID, String koie){

		String q = ("insert into Utstyr (navn, innkjopsdato, stat, adminID,fraktesTilID) VALUES ('" + navn + "','" + dato + "','" + (new String("" + status)) + "','" + adminID + "','" + koie + "');");

		db.oppdaterDB(q);
	}

	// Spør om laveste vedsatus fra en bestemt Koie en bestemt Dato
	public ResultSet getVedstatusRapport(String koieID, String dato) {

		String q = ("select min(Vedstatus) from Rapport where Dato = '" 
				+ dato 
				+ "' and KoieRapportID = '" 
				+ koieID 
				+ "';");

		return db.sporDB(q);
	}

	/**
	 * Returnerer et ResultSet med alt ødelagt og gjenglemt utsyr fra en koie
	 * 
	 * @param koieID Koien informasjonen skal hentes fra
	 * @return ResultSet der col1 = ødelagt utstyr, col2 = gjenglemt utstyr
	 */
	public ResultSet getOdelagtGjenglemtKoie(String koieID) {

		String q = ("select Odelagt, Gjenglemt from Rapport where KoieRapportID = '" 
				+ koieID 
				+ "';");

		return db.sporDB(q);
	}

	/**
	 * Returnerer et ResultSet som sier hvor mange reservasjoner det er på en koie på en bestemt dato
	 * 
	 * @param koieID Koien informasjonen skal hentes fra
	 * @param dato Den aktuelle datoen
	 * @return ResultSet med antall reservasjoner (kun et tall, ikke informasjon om reservasjonene)
	 */
	public ResultSet getReservertePlasser(String koieID, String dato){

		String q = ("select count(*) from Reservasjon where ReservertKoieID = '" 
				+ koieID 
				+ "' and Dato = '" 
				+ dato 
				+ "';");

		return db.sporDB(q);
	}

	/**
	 * Returnerer et ResultSet med antall totale sengeplasser på en koie
	 * 
	 * @param koieID Koien informasjonen skal hentes fra
	 * @return ResultSet med antall sengeplasser
	 */
	public ResultSet getSengeplasser(String koieID) {

		String q = ("select Storrelse from Koie where KoieID = '" 
				+ koieID 
				+ "';");

		return db.sporDB(q);
	}

}
