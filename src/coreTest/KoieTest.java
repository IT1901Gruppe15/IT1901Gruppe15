package coreTest;

import static org.junit.Assert.*;

import org.junit.Test;

import core.TheFormator;

public class KoieTest {

	@Test
	public void testFormaterKoieNavn() {
		assertEquals("Flaakoia",TheFormator.formaterKoieNavn("Flåkoia"));
		assertEquals("Holmsaakoia",TheFormator.formaterKoieNavn("Holmsåkoia"));
		assertEquals("Kamtjonnkoia",TheFormator.formaterKoieNavn("Kamtjønnkoia"));
		assertEquals("Kraaklikaaten",TheFormator.formaterKoieNavn("Kråklikåten"));
		assertEquals("Kaasen",TheFormator.formaterKoieNavn("Kåsen"));
		assertEquals("Lynhogen",TheFormator.formaterKoieNavn("Lynhøgen"));
		assertEquals("Mortenskaaten",TheFormator.formaterKoieNavn("Mortenskåten"));
		assertEquals("Rindalsloa",TheFormator.formaterKoieNavn("Rindalsløa"));
		assertEquals("Selbukaaten",TheFormator.formaterKoieNavn("Selbukåten"));
		assertEquals("Vekvessaetra",TheFormator.formaterKoieNavn("Vekvessætra"));
		assertEquals("Ovensenget",TheFormator.formaterKoieNavn("Øvensenget"));
	}

}
