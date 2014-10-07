package core;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reservasjon {
	String koieID;
	private String epost, start_dato;
	DBConnection db = new DBConnection();
	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public Reservasjon() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	// string til dato for validering av dato før, dato etter.
	public Date asDate(String date, String dateFromat) throws ParseException {
		SimpleDateFormat s = new SimpleDateFormat(dateFromat);
		s.setLenient(false);
		Date dat = s.parse(date);
		return dat;
	}

	// valider gyldig dato
	public boolean isThisDateValid(String dateToValidate, String dateFromat) {

		if (dateToValidate == null) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);

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
	public void validate(final String hex) throws IllegalArgumentException {

		matcher = pattern.matcher(hex);
		System.out.println(hex);
		if (!matcher.matches()) {
			throw new IllegalArgumentException("feil epost format");
		}

	}

	public void lesReservasjon() {
		BufferedReader reader = null;

		try {
			InputStream inputStream = Reservasjon.class
					.getResourceAsStream("testinput");

			reader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			line = reader.readLine();
			String[] list = line.split(";");
			for (int i = 0; i < list.length; i++) {
				switch (i) {
				case 0:
					koieID = list[0];// sjekke om koieid er gyldig?
					break;
				case 1:
					validate(list[1].trim());
					epost = list[1];

					break;
				case 2:
					isThisDateValid(list[2], "yyyy-MM-dd");
					start_dato = list[2];

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

		} catch (Exception e) {
			System.err.println(e.getMessage());

		}

	}

	public void updateDB() {
		db.settinnReservasjon(getEpost(), getStart_dato(),
				getKoieID());

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
		res.updateDB();

		System.out.println(res.koieID + res.epost + res.start_dato
				);
		// TODO Auto-generated method stub

	}

}
