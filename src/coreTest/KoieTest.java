package coreTest;

import static org.junit.Assert.*;

import org.junit.Test;

import core.TheFormator;

public class KoieTest {

	@Test
	public void testFormaterKoieNavn() {
		assertEquals("Flaakoia",TheFormator.formaterKoieNavn("Fl�koia"));
		assertEquals("Holmsaakoia",TheFormator.formaterKoieNavn("Holms�koia"));
		assertEquals("Kamtjonnkoia",TheFormator.formaterKoieNavn("Kamtj�nnkoia"));
		assertEquals("Kraaklikaaten",TheFormator.formaterKoieNavn("Kr�klik�ten"));
		assertEquals("Kaasen",TheFormator.formaterKoieNavn("K�sen"));
		assertEquals("Lynhogen",TheFormator.formaterKoieNavn("Lynh�gen"));
		assertEquals("Mortenskaaten",TheFormator.formaterKoieNavn("Mortensk�ten"));
		assertEquals("Rindalsloa",TheFormator.formaterKoieNavn("Rindalsl�a"));
		assertEquals("Selbukaaten",TheFormator.formaterKoieNavn("Selbuk�ten"));
		assertEquals("Vekvessaetra",TheFormator.formaterKoieNavn("Vekvess�tra"));
		assertEquals("Ovensenget",TheFormator.formaterKoieNavn("�vensenget"));
	}

}
