package core;

import java.sql.SQLException;

/**
 * Holder informasjon om utstyr og metode for Â kj¯pe nytt utstyr som legges til database
 *
 */
public class Utstyr {
	String koieID;
	String navn,admin_id,admin;
	String innkj√∏psdato;
	int status;
<<<<<<< Updated upstream
	
	
	/**
	 * Konstrukt¯r for klassen Utstyr som setter forskjellige felt
	 * @param koieID navnet pÂ aktuell koide
	 * @param navn navn pÂ utstyr
	 * @param innkj¯psdato dato for kj¯p av utstyr
	 * @param status status om utstyrer er ¯delagt eller ikke,der 0 er ¯delagt og 1 er ikke ¯delagt
	 * @param admin_id navnet til adminen som legger til utstyr
	 */
	private Utstyr( String koieID, String navn, String innkj¯psdato, int status, String admin_id){
=======

	private Utstyr( String koieID, String navn, String innkj√∏psdato, int status, String admin_id){
>>>>>>> Stashed changes

		this.koieID = koieID;
		this.navn = navn;
		this.innkj√∏psdato = innkj√∏psdato;
		this.status = status;
		this.admin_id=admin_id;
	}
	
	/**
	 * Registrerer nytt utstyrskj¯p i databasen
	 */
	public void registrerUtstyrKjop(){
		DBConnection db = new DBConnection();
		try {
			db.registrerUtstyr(getNavn(), getInnkj√∏psdato(), getStatus(), getAdmin_id(), getKoieID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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

	public String getInnkj√∏psdato() {
		return innkj√∏psdato;
	}

	public void setInnkj√∏psdato(String innkj√∏psdato) {
		this.innkj√∏psdato = innkj√∏psdato;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
