package coreTest;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Bruker;

public class BrukerTest {
	String brukernavn="knut", fulltnavn="Ola Nordmann", tlf="99887766", epost="ola.nordmann@gmail.com", admin="0";
	Bruker test = new Bruker(brukernavn,fulltnavn,tlf,epost,admin);

	@Test
	public void testGetBrukernavn() {
		assertEquals(brukernavn,test.getBrukernavn());
	}

	@Test
	public void testGetFulltNavn() {
		assertEquals(fulltnavn,test.getFulltNavn());
	}

	@Test
	public void testGetTlf() {
		assertEquals(tlf,test.getTlf());
	}

	@Test
	public void testGetEpost() {
		assertEquals(epost,test.getEpost());
	}

	@Test
	public void testIsAdmin() {
		assertEquals(false,test.isAdmin());
	}

}
