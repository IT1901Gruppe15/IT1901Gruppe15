package coreTest;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Bruker;

public class BrukerTest {
	String brukernavn="knut", fulltnavn="Ola Nordmann", tlf="99887766", epost="ola.nordmann@gmail.com", admin="0";
	Bruker test = new Bruker(brukernavn,fulltnavn,tlf,epost,admin);

	@Test
	public void testGetBrukernavn() {
		if(test.getBrukernavn()!=brukernavn){
			fail("Not yet implemented");
		}
		System.out.println(test.getBrukernavn());
	}

	@Test
	public void testGetFulltNavn() {
		if(test.getFulltNavn()!=fulltnavn){
			fail("Not yet implemented");
		}
		System.out.println(test.getFulltNavn());
	}

	@Test
	public void testGetTlf() {
		if(test.getTlf()!=tlf){
			fail("Not yet implemented");
		}
		System.out.println(test.getTlf());
	}

	@Test
	public void testGetEpost() {
		if(test.getEpost()!=epost){
			fail("Not yet implemented");
		}
		System.out.println(test.getEpost());
	}

	@Test
	public void testIsAdmin() {
		if(test.isAdmin()!=false){
			fail("Not yet implemented");
		}
		System.out.println(test.isAdmin());
	}

}
