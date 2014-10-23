package core;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ReservasjonTest {
	Reservasjon res = new Reservasjon();
	public ExpectedException thrown= ExpectedException.none();



	@Test 
	public void testIsThisDateValid() {
		assertTrue(res.isThisDateValid("2012-10-06", "yyyy-MM-dd"));
		

	}
	@Test 
	public void testWat(){
		thrown.expect(ParseException.class);
		res.isThisDateValid("2018-13-06", "yyyy-MM-dd");
		
		
	}

	@Test(expected=IllegalArgumentException.class)  
	public void testValidate() {
		res.validate("hhhhhhhh");
		
		
	}
	@Test
	public void testValidate2(){
		res.validate("hhhh@hotmail.com");
	}

	@Test
	public void testLesReservasjon() {
		
		res.lesReservasjon();
		
		assertEquals("feil","Telin", res.getKoieID());
		

		
		assertEquals("feil","epostadresse@hotmail.com", res.getEpost());
		

	
		assertEquals("feil","2014-10-03", res.getStart_dato());	}

	

}
