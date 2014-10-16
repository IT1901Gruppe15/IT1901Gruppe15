package core;

import static org.junit.Assert.*;

import org.junit.Test;

public class BrukerTest {
	
	Bruker test = new Bruker("knut","Ola Nordmann","99887766","ola.nordmann@gmail.com","0");

	@Test
	public void testGetBrukernavn() {
		System.out.println(test.getBrukernavn());
	}

	@Test
	public void testGetFulltNavn() {
		System.out.println(test.getFulltNavn());
	}

	@Test
	public void testGetTlf() {
		System.out.println(test.getTlf());
	}

	@Test
	public void testGetEpost() {
		System.out.println(test.getEpost());
	}

	@Test
	public void testIsAdmin() {
		System.out.println(test.isAdmin());
	}

}
