package core;

import static org.junit.Assert.*;

import org.junit.Test;

public class KoieTest {

	@Test
	public void testFormaterKoieNavn() {
		System.out.println(Koie.formaterKoieNavn("Flåkoia"));
		System.out.println(Koie.formaterKoieNavn("Holmsåkoia"));
		System.out.println(Koie.formaterKoieNavn("Kamtjønnkoia"));
		System.out.println(Koie.formaterKoieNavn("Kråklikåten"));
		System.out.println(Koie.formaterKoieNavn("Kåsen"));
		System.out.println(Koie.formaterKoieNavn("Lynhøgen"));
		System.out.println(Koie.formaterKoieNavn("Mortenskåten"));
		System.out.println(Koie.formaterKoieNavn("Rindalsløa"));
		System.out.println(Koie.formaterKoieNavn("Selbukåten"));
		System.out.println(Koie.formaterKoieNavn("Vekvessætra"));
		System.out.println(Koie.formaterKoieNavn("Øvensenget"));
	}

}
