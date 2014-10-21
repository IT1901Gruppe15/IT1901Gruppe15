package coreTest;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Koie;

public class KoieTest {

	@Test
	public void testFormaterKoieNavn() {
		assertEquals("Flaakoia",Koie.formaterKoieNavn("Fl�koia"));
		assertEquals("Holmsaakoia",Koie.formaterKoieNavn("Holms�koia"));
		assertEquals("Kamtjonnkoia",Koie.formaterKoieNavn("Kamtj�nnkoia"));
		assertEquals("Kraaklikaaten",Koie.formaterKoieNavn("Kr�klik�ten"));
		assertEquals("Kaasen",Koie.formaterKoieNavn("K�sen"));
		assertEquals("Lynhogen",Koie.formaterKoieNavn("Lynh�gen"));
		assertEquals("Mortenskaaten",Koie.formaterKoieNavn("Mortensk�ten"));
		assertEquals("Rindalsloa",Koie.formaterKoieNavn("Rindalsl�a"));
		assertEquals("Selbukaaten",Koie.formaterKoieNavn("Selbuk�ten"));
		assertEquals("Vekvessaetra",Koie.formaterKoieNavn("Vekvess�tra"));
		assertEquals("Ovensenget",Koie.formaterKoieNavn("�vensenget"));
	}

}
