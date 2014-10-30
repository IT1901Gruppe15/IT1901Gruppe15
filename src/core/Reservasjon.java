package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
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
	 *
	 */
	public Reservasjon() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}


	
	// string til dato for validering av dato før, dato etter.
//	public Date asDate(String date, String dateFromat) throws ParseException {
//		SimpleDateFormat s = new SimpleDateFormat(dateFromat);
//		s.setLenient(false);
//		Date dat = s.parse(date);
//		return dat;
//	}
	/**
	 * @author morten
	 * Valdierer en gitt dato på et gitt format
	 * 
	 * @param dateToValidate, dato som skal valideres
	 * @param dateFormat, angir formatet som datoen skal være på
	 * @return true eller false avhening om dato ble valideret som riktig eller ikke
	 * @throws ParseException
	 *
	 */
	// valider gyldig dato
	
	public boolean isThisDateValid(String dateToValidate, String dateFormat) {

		if (dateToValidate == null) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

		sdf.setLenient(false);

		try {

			Date date = sdf.parse(dateToValidate);
			System.out.println(date);

		} catch (ParseException e) {

			e.printStackTrace();
			return false;
		}

		return true;
	}

	// valider epost
	/**
	 * @author morten
	 * Validerer om epost er gyldig eller ikke
	 * @param hex, epost string som skal valideres
	 * @throws IllegalArgumentException som må håndteres av metoden som kaller på metoden
	 * 
	 * 
	 *
	 */
	public void validate(final String hex) throws IllegalArgumentException {

		matcher = pattern.matcher(hex);
		System.out.println(hex);
		if (!matcher.matches()) {
			throw new IllegalArgumentException("feil epost format");
		}

	}
	
	/**
	 * @author morten
	 * Metode for å lese reservasjon fra en tekstfil på formatet koie; epostadresse; dato.
	 *
	 */

	public void lesReservasjon() {
		BufferedReader reader = null;

		try {
			
			

			reader = new BufferedReader(new FileReader("textfiles/inputtext.txt"));
			String line = null;
			line = reader.readLine();
			String[] list = line.split(";");
			for (int i = 0; i < list.length; i++) {
				switch (i) {
				case 0:
					koieID = list[0].trim();// sjekke om koieid er gyldig?
					break;
				case 1:
					validate(list[1].trim());
					epost = list[1].trim();

					break;
				case 2:
					isThisDateValid(list[2], "yyyy-MM-dd");
					start_dato = list[2].trim();

					//break;
//				case 3:
//					isThisDateValid(list[3], "yyyy-MM-dd");
//					slutt_dato = list[3];
//					if (asDate(slutt_dato, "yyyy-MM-dd").compareTo(
//							asDate(start_dato, "yyyy-MM-dd")) <= 0) {
//						throw new IllegalArgumentException(
//								"Start dato kan ikke være etter slutt dato!");
//
//					}

				}
			}
			updateDB();

		} catch (Exception e) {
			System.err.println(e.getMessage());

		}

	}
	/**
	 * @author morten
	 *	Metode for å oppdatere database med feltene epost
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

	public static void main(String[] args) {
		Reservasjon res = new Reservasjon();
		res.lesReservasjon();

		System.out.println(res.koieID + res.epost + res.start_dato
				);
		System.out.println(res.getEpost());
		// TODO Auto-generated method stub

	}

}
