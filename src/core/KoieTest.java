package core;

import static org.junit.Assert.*;

import org.junit.Test;

public class KoieTest {

	@Test
	public void testFormaterKoieNavn() {
		System.out.println(Koie.formaterKoieNavn("Fl�koia"));
		System.out.println(Koie.formaterKoieNavn("Holms�koia"));
		System.out.println(Koie.formaterKoieNavn("Kamtj�nnkoia"));
		System.out.println(Koie.formaterKoieNavn("Kr�klik�ten"));
		System.out.println(Koie.formaterKoieNavn("K�sen"));
		System.out.println(Koie.formaterKoieNavn("Lynh�gen"));
		System.out.println(Koie.formaterKoieNavn("Mortensk�ten"));
		System.out.println(Koie.formaterKoieNavn("Rindalsl�a"));
		System.out.println(Koie.formaterKoieNavn("Selbuk�ten"));
		System.out.println(Koie.formaterKoieNavn("Vekvess�tra"));
		System.out.println(Koie.formaterKoieNavn("�vensenget"));
	}

}
