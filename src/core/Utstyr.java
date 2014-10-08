package core;

import java.sql.SQLException;

public class Utstyr {
	String koieID;
	String navn,admin_id,admin;
	String innkjøpsdato;
	int status;

	private Utstyr( String koieID, String navn, String innkjøpsdato, int status, String admin_id){

		this.koieID = koieID;
		this.navn = navn;
		this.innkjøpsdato = innkjøpsdato;
		this.status = status;
		this.admin_id=admin_id;
	}

	public void registrerUtstyrKjop(){
		DBConnection db = new DBConnection();
		try {
			db.registrerUtstyr(getNavn(), getInnkjøpsdato(), getStatus(), getAdmin_id(), getKoieID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getKoieID() {
		return koieID;
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
		return innkjøpsdato;
	}

	public void setInnkjøpsdato(String innkjøpsdato) {
		this.innkjøpsdato = innkjøpsdato;
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
