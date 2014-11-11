package core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reservasjon klassen leser fra en fil og lagrer feltene i databasen hvis de har gyldige verdier
 */
public class Reservasjon {
	private String koieID;
	private String epost, start_dato;
	private DBConnection db = new DBConnection();
	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Setter email pattern til EMAIL_PATTERN 
	 * som brukes til å verifisere e-post
	 * 
	 */
	public Reservasjon() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	/**
	 * Valdierer en gitt dato på et gitt format
	 * 
	 * @author morten
	 * @param dateToValidate dato som skal valideres
	 * @param dateFormat angir formatet som datoen skal være på
	 * @return true eller false avhening om dato ble valideret som riktig eller ikke
	 * @throws ParseException
	 *
	 */
	public boolean isThisDateValid(String dateToValidate, String dateFormat) {
		if (dateToValidate == null) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

		sdf.setLenient(false);

		try {

			@SuppressWarnings("unused")
			Date date = sdf.parse(dateToValidate);

		} catch (ParseException e) {

			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Validerer om epost er gyldig eller ikke
	 * 
	 * @author morten
	 * @param hex epost string som skal valideres
	 * @throws IllegalArgumentException som må håndteres av metoden som kaller på metoden
	 * 
	 */
	public void validate(final String hex) throws IllegalArgumentException {
		matcher = pattern.matcher(hex);
		if (!matcher.matches()) {
			throw new IllegalArgumentException("feil epost format");
		}
	}
	
	/**
	 * Metode for å lese reservasjon fra en tekstfil på formatet koie; epostadresse; dato.
	 * 
	 * @author morten
	 *
	 */
	public void lesReservasjon() {
		BufferedReader reader = null;

		try {	
			reader = new BufferedReader(new FileReader("textfiles/inputtext.txt"));
			String line = null;
			while(( line=reader.readLine() )!=null){
				
				String[] list = line.split(";");
				for (int i = 0; i < list.length; i++) {
					switch (i) {
					case 0:
						koieID = list[0].trim();// sjekke om koieid er gyldig?
						koieID=TheFormator.formaterKoieNavn(koieID);
						break;
					case 1:
						validate(list[1].trim());
						epost = list[1].trim();
						
						break;
					case 2:
						isThisDateValid(list[2], "yyyy-MM-dd");
						start_dato = list[2].trim();
					}
				}
				updateDB();
			}
			wipeFile();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	/**
	 *	Metode for å oppdatere database med feltene epost
	 *
	 * @author morten
	 */
	private void wipeFile() throws FileNotFoundException{
		PrintWriter writer = new PrintWriter("textfiles/inputtext.txt");
		writer.print("");
		writer.close();
	}
	
	/**
	 * Setter inn reservasjon i database
	 */
	public void updateDB() {
		try {
			db.settinnReservasjon(getEpost(), getStart_dato(),
					getKoieID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getKoieID() {
		return koieID;
	}

	public String getEpost() {
		return epost;
	}

	public String getStart_dato() {
		return start_dato;
	}
}
