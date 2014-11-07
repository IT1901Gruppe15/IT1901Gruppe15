package core;

import java.sql.SQLException;

/**
 * Holder informasjon om utstyr og metode for å kjøpe nytt utstyr som legges til database
 *
 */
public class Utstyr {
	private String koieID;
	private String navn,admin_id;
	private String innkjopsdato;
	private int status;
	
	
	/**
	 * Konstruktør for klassen Utstyr som setter forskjellige felt
	 * @param koieID navnet på aktuell koide
	 * @param navn navn på utstyr
	 * @param innkjøpsdato dato for kjøp av utstyr
	 * @param status status om utstyrer er ødelagt eller ikke,der 0 er ødelagt og 1 er ikke ødelagt
	 * @param admin_id navnet til adminen som legger til utstyr
	 */
	private Utstyr( String koieID, String navn, String innkjopsdato, int status, String admin_id){

		this.koieID = koieID;
		this.navn = navn;
		this.innkjopsdato = innkjopsdato;
		this.status = status;
		this.admin_id=admin_id;
	}
	
	/**
	 * Registrerer nytt utstyrskjøp i databasen
	 */
	public void registrerUtstyrKjop(){
		DBConnection db = new DBConnection();
		try {
			db.registrerUtstyr(getNavn(), getInnkjøpsdato(), getStatus(), getAdmin_id(), getKoieID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Finner og returnerer koieID (navn på koie) der å=aa, ø=o osv
	 * 
	 * @return Formatert koienavn
	 */
	public String getKoieID() {
		return TheFormator.formaterKoieNavn(koieID);
		
		}
	

	public void setKoieID(String koieID) {
		this.koieID = koieID;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getInnkjøpsdato() {
		return innkjopsdato;
	}

	public void setInnkjøpsdato(String innkjøpsdato) {
		this.innkjopsdato = innkjøpsdato;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
