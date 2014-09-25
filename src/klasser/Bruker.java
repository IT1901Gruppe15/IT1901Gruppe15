package klasser;

public class Bruker {

	private String brukernavn;
	private String navn;
	private String tlf;
	private String epost;
	private boolean admin;

	public Bruker(String brukernavn, String navn, String tlf, String epost, String admin) {
		this.brukernavn = brukernavn;
		this.navn = navn;
		this.tlf = tlf;
		this.epost = epost;
		this.admin = (admin.equals("1"));
	}

	public boolean getAdminStatus() {
		return admin;
	}

}
