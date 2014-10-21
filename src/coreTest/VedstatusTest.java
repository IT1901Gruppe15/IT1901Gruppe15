package coreTest;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import core.Vedstatus;

public class VedstatusTest {
	
	Vedstatus test = new Vedstatus();

	@Test
	public void testLagVedEstimat() throws SQLException {
		System.out.println("Antall dager til neste veddugnad i Fl�koia: "+test.lagVedEstimat("Flaakoia"));
		System.out.println("Antall dager til neste veddugnad i Fosenkoia: "+test.lagVedEstimat("Fosenkoia"));
		System.out.println("Antall dager til neste veddugnad i Heinfjordstua: "+test.lagVedEstimat("Feinfjordstua"));
		System.out.println("Antall dager til neste veddugnad i Hognabu: "+test.lagVedEstimat("Hognabu"));
		System.out.println("Antall dager til neste veddugnad i Holms�koia: "+test.lagVedEstimat("Holmsaakoia"));
		System.out.println("Antall dager til neste veddugnad i Holvassgamma: "+test.lagVedEstimat("Holvassgamma"));
		System.out.println("Antall dager til neste veddugnad i Iglbu: "+test.lagVedEstimat("Iglbu"));
		System.out.println("Antall dager til neste veddugnad i Kamtj�nnkoia: "+test.lagVedEstimat("Kamtjonnkoia"));
		System.out.println("Antall dager til neste veddugnad i Kr�klik�ten: "+test.lagVedEstimat("Kraaklikaaten"));
		System.out.println("Antall dager til neste veddugnad i Kvermovollen: "+test.lagVedEstimat("Kvermovollen"));
		System.out.println("Antall dager til neste veddugnad i K�sen: "+test.lagVedEstimat("Kaasen"));
		System.out.println("Antall dager til neste veddugnad i Lynh�gen: "+test.lagVedEstimat("Lynhogen"));
		System.out.println("Antall dager til neste veddugnad i Mortensk�ten: "+test.lagVedEstimat("Mortenskaaten"));
		System.out.println("Antall dager til neste veddugnad i Nicokoia: "+test.lagVedEstimat("Nicokoia"));
		System.out.println("Antall dager til neste veddugnad i Rindalsl�a: "+test.lagVedEstimat("Rindalsloa"));
		System.out.println("Antall dager til neste veddugnad i Selbuk�ten: "+test.lagVedEstimat("Selbukaaten"));
		System.out.println("Antall dager til neste veddugnad i Sonvasskoia: "+test.lagVedEstimat("Sonvasskoia"));
		System.out.println("Antall dager til neste veddugnad i Stabburet: "+test.lagVedEstimat("Stabburet"));
		System.out.println("Antall dager til neste veddugnad i Stakkslettbua: "+test.lagVedEstimat("Stakkslettbua"));
		System.out.println("Antall dager til neste veddugnad i Telin: "+test.lagVedEstimat("Telin"));
		System.out.println("Antall dager til neste veddugnad i Taagaabu: "+test.lagVedEstimat("Taagaabu"));
		System.out.println("Antall dager til neste veddugnad i Vekvess�tra: "+test.lagVedEstimat("Vekvessaetra"));
		System.out.println("Antall dager til neste veddugnad i �vensenget: "+test.lagVedEstimat("Ovensenget"));
	}

}
