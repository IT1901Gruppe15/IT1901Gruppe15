package core;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DB;

public class DBConnection { // noen andre får oppdatere de siste javadocene i denne klassen siden jeg vet ikke helt hva de gjør :S

	private DB db;

	public DBConnection() {
		db = new DB();
	}

	/**
	 * Returnerer et ResultSet med brukerinfo som hører til brukernavnet til den som prøver å logge inn
	 * 
	 * @param brukernavn Brukernavnet til den som prøver å logge inn
	 * @return ResultSet med en rad der col1 = brukernavn, col2 = passord, col3 = fullt navn, col4 = telefonnummer, col5 = epost, col6 = isAdmin
	 * @throws SQLException
	 */
	public ResultSet login(String brukernavn) throws SQLException {

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
	 * @throws SQLException
	 */
	public void registrerBruker(String brukernavn, String passord, String navn, String tlf, String epost) throws SQLException {
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
	 * @throws SQLException
	 */
	public void settinnRapport(String odelagt, String gjenglemt, int vedstatus,
			String koienavn, String dato) throws SQLException {
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
	 * @throws SQLException
	 */
	public void settinnReservasjon(String epost, String dato, String koienavn) throws SQLException {
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
	 * @throws SQLException
	 */
	public void oppdaterVedstatus(String koienavn, int vedstatus) throws SQLException {
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
	 * @throws SQLException
	 */
	public void oppdaterUtstyr(int utstyrsID, int status) throws SQLException {
		String q = ("update Utstyr set stat = '" + (new String("" + status))
				+ "' where UtstyrsID = '" + (new String("" + utstyrsID)) + "';");

		db.oppdaterDB(q);
	}

	// Legg inn ødelagt Utstyr
	public void leggInnOdelagtUtstyr(int utstyrsID, int rapportID) throws SQLException {
		String q = ("insert into ErOdelagt values ('"
				+ (new String("" + utstyrsID)) + "','"
				+ (new String("" + rapportID)) + "');");

		db.oppdaterDB(q);
	}
	
	// Legg inn gjenglemte ting
	public void leggInnGjenglemteTing(String Navn, int rapportID, String koieID) throws SQLException {
		String q = ("insert into Gjenglemt (Navn,RapportID,KoieID) values('" + Navn + "','" + rapportID + "','" + koieID + "');");
		
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
	 * Legger inn nytt utstyr i en koie
	 * 
	 * @param navn Navnet til det nye utstyret
	 * @param dato Datoen utstyret blir lagt til
	 * @param status 0 = ødelagt, 1 = fungerer
	 * @param adminID ID til den innloggede brukeren
	 * @param koie Koien utstyret skal legges til
	 * @throws SQLException
	 */
	public void registrerUtstyr(String navn, String dato, int status, String adminID, String koie) throws SQLException {
		String q = ("insert into Utstyr (navn, innkjopsdato, stat, adminID,fraktesTilID) VALUES ('" + navn + "','" + dato + "','" + (new String("" + status)) + "','" + adminID + "','" + koie + "');");

		db.oppdaterDB(q);
	}

	// Spør om laveste vedsatus fra en bestemt Koie en bestemt Dato
	public ResultSet getVedstatusRapport(String koieID, String dato) throws SQLException {
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
	 * @throws SQLException
	 */
	public ResultSet getOdelagtGjenglemtKoie(String koieID) throws SQLException {
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
	 * @throws SQLException
	 */
	public ResultSet getReservertePlasser(String koieID, String dato) throws SQLException {
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
	 * @throws SQLException
	 */
	public ResultSet getSengeplasser(String koieID) throws SQLException {
		String q = ("select Storrelse from Koie where KoieID = '" 
				+ koieID 
				+ "';");

		return db.sporDB(q);
	}
	
	//returnerer en liste med datoer med laveste vedstatus for hver dag i synkende rekkefølge etter dato, starter på innsendt dato
	public ResultSet getDatoListe(String koieID, String dato) throws SQLException {
		String q = ("select Dato, min(Vedstatus) from Rapport where Dato > '" + dato + "' and KoieRapportID = '" + koieID + "' group by Dato order by Dato desc;");
		
		return db.sporDB(q);
	}
	
	//returnerer liste med alt ødelagt utstyr på en koie
	public ResultSet getOdelagt(String koieID) throws SQLException {
		String q = ("select Navn from Rapport as R, ErOdelagt as E, Utstyr as U where KoieRapportID = '" + koieID + "' and R.RapportID = E.RapportID and E.UtstyrsID = U.UtstyrsID;");
		
		return db.sporDB(q);
	}
	
	//returnerer en liste med gjenglemte ting på en koie
	public ResultSet getGjenglemt(String koieID) throws SQLException {
		
		String q = ("select Navn from Gjenglemt where KoieID = '" + koieID + "';");
		
		return db.sporDB(q);
	}
	
	//fix utstyr
	public void fixUtstyr(String koie, String tingnavn) throws SQLException {
		String q = ("delete from ErOdelagt where UtstyrsID = (select UtstyrsID from Utstyr where Navn = '" + tingnavn + "' and FraktesTilID = '" + koie + "');");
		db.oppdaterDB(q);
		
		q = ("update Utstyr set status = '1' where Navn = '" + tingnavn + "' and FraktesTilID = '" + koie + "';");
		db.oppdaterDB(q);
	}
	
	//ting er hentet
	public void funnetTing(String ting) throws SQLException {
		String q = ("delete from Gjenglemt where navn = '" + ting + "';");
		
		db.oppdaterDB(q);
	}

}
