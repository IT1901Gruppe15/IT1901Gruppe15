package coreTest;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Koie;

public class KoieTest {

	@Test
	public void testFormaterKoieNavn() {
		assertEquals("Flaakoia",Koie.formaterKoieNavn("Flåkoia"));
		assertEquals("Holmsaakoia",Koie.formaterKoieNavn("Holmsåkoia"));
		assertEquals("Kamtjonnkoia",Koie.formaterKoieNavn("Kamtjønnkoia"));
		assertEquals("Kraaklikaaten",Koie.formaterKoieNavn("Kråklikåten"));
		assertEquals("Kaasen",Koie.formaterKoieNavn("Kåsen"));
		assertEquals("Lynhogen",Koie.formaterKoieNavn("Lynhøgen"));
		assertEquals("Mortenskaaten",Koie.formaterKoieNavn("Mortenskåten"));
		assertEquals("Rindalsloa",Koie.formaterKoieNavn("Rindalsløa"));
		assertEquals("Selbukaaten",Koie.formaterKoieNavn("Selbukåten"));
		assertEquals("Vekvessaetra",Koie.formaterKoieNavn("Vekvessætra"));
		assertEquals("Ovensenget",Koie.formaterKoieNavn("Øvensenget"));
	}

}
