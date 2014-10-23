package core;

public class TheFormator {
	
	/**
	 * Formaterer koie navn til gyldig format
	 * @param koie tar inn koie navn
	 * @return formatertTekst koie blir returnert med riktig format
	 */
	public static String formaterKoieNavn(String koie) {
		String formatertTekst = "";
		for (int i = 0; i < koie.length(); i++) {
			if (koie.charAt(i) == 'æ' || koie.charAt(i) == 'Æ') {
				formatertTekst += "ae";
			} else if (koie.charAt(i) == 'ø' || koie.charAt(i) == 'Ø') {
				formatertTekst += 'o';
			} else if (koie.charAt(i) == 'å' || koie.charAt(i) == 'Å') {
				formatertTekst += "aa";
			} else {
				formatertTekst += koie.charAt(i);
			}
			String forsteBokstav = formatertTekst.substring(0, 1).toUpperCase();
			formatertTekst = forsteBokstav + formatertTekst.substring(1);
		}
		return formatertTekst;
	}
	
	/**
	 * gjør om tekst til formatet: ting1;ting2;ting3
	 * 
	 * @param tekst Teksten som endres
	 * @param separator Hvilket symbol(er) som skiller tekstbitene
	 * @return returnerer den ferdige teksten
	 */
	public static String formaterTekst(String tekst, String separator) {
		String[] liste = tekst.split(separator);
		String ferdigTekst = "";
		for (int i = 0; i < liste.length; i++) {
			ferdigTekst += liste[i] + ";";
		}
		ferdigTekst = ferdigTekst.substring(0, ferdigTekst.length() - 1);
		ferdigTekst = ferdigTekst.trim();
		if (ferdigTekst.length() == 0) {
			return " ";
		}
		return ferdigTekst;
	}
}
