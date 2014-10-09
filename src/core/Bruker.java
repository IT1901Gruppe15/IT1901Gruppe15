package core;

/**
 * Inneholder informasjon om en bruker i et datasystem.
 *
 */
public class Bruker {

	private String brukernavn;
	private String navn;
	private String tlf;
	private String epost;
	private boolean admin;

	/**
	 * Lager en bruker der all informasjon tas in gjennom parametere
	 * 
	 * @param brukernavn Brukernavnet til brukeren
	 * @param navn Fullt navn til brukeren
	 * @param tlf Telefonnummer til brukeren
	 * @param epost Epostadresse til brukeren
	 * @param admin Om brukeren har adminstratorrettigheter
	 */
	public Bruker(String brukernavn, String navn, String tlf, String epost, String admin) {
		this.brukernavn = brukernavn;
		this.navn = navn;
		this.tlf = tlf;
		this.epost = epost;
		this.admin = (admin.equals("1"));
	}

	public String getBrukernavn() {
		return brukernavn;
	}
	
	public String getFulltNavn() {
		return navn;
	}
	
	public String getTlf() {
		return tlf;
	}
	
	public String getEpost() {
		return epost;
	}
	
	public boolean isAdmin() {
		return admin;
	}
	
}
