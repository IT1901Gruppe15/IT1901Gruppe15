package klasser;

import java.util.ArrayList;
import java.util.List;

public class Koie {
	String KoieID,navn,adresse;
	int sengeplasser,vedstatus;
	List<Utstyr> utstyr = new ArrayList<Utstyr>();
	List<String> gjenglemteTing = new ArrayList<String>();
	
	public void bergenVedStatus(){
		
	}
	
	public static String formaterKoieNavn(String koie) {
		String formatertTekst = "";
		for (int i = 0; i < koie.length(); i++) {
			if (koie.charAt(i) == 'æ' || koie.charAt(i) == 'Æ') {
				formatertTekst += "ae";
			} else if (koie.charAt(i) == 'ø' || koie.charAt(i) == 'Ø') {
				formatertTekst += 'o';
			} else if (koie.charAt(i) == 'å' || koie.charAt(i) == 'Å') {
				formatertTekst += 'a';
			} else {
				formatertTekst += koie.charAt(i);
			}
			if (i == 0) {
				formatertTekst = formatertTekst.toUpperCase();
			}
		}
		return formatertTekst;
	}
	

	public static void main(String[] args) {
		System.out.println(		formaterKoieNavn("test"));
		System.out.println(		formaterKoieNavn("høyåtttæ"));
		System.out.println(		formaterKoieNavn("Åle"));

	}

}
