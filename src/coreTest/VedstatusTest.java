package coreTest;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import core.DBConnection;
import core.Vedstatus;

public class VedstatusTest {
	
	DBConnection test = new DBConnection();

	@Test
	public void testLagVedEstimat() throws SQLException {
		test.datoVeddugnad("Flaakoia", "2015-01-01");
		test.datoVeddugnad("Fosenkoia", "2015-03-15");
		test.datoVeddugnad("Heinfjordstua", "2015-01-01");
		test.datoVeddugnad("Hognabu", "2015-01-01");
		test.datoVeddugnad("Holmsaakoia", "2015-01-01");
		test.datoVeddugnad("Holvassgamma", "2015-01-01");
		test.datoVeddugnad("Iglbu", "2015-01-01");
		test.datoVeddugnad("Kamtjonnkoia", "2015-01-01");
		test.datoVeddugnad("Kraaklikaaten", "2015-01-01");
		test.datoVeddugnad("Kvermovollen", "2015-01-01");
		test.datoVeddugnad("Lynhogen", "2015-01-01");
		test.datoVeddugnad("Mortenskaaten", "2015-01-01");
		test.datoVeddugnad("Nicokoia", "2015-01-01");
		test.datoVeddugnad("Rindalsloa", "2015-01-01");
		test.datoVeddugnad("Selbukaaten", "2015-01-01");
		test.datoVeddugnad("Sonvasskoia", "2015-01-01");
		test.datoVeddugnad("Stabburet", "2015-01-01");
		test.datoVeddugnad("Stakkslettbua", "2015-01-01");
		test.datoVeddugnad("Telin", "2015-01-01");
		test.datoVeddugnad("Taagaabu", "2015-01-01");
		test.datoVeddugnad("Vekvessaetra", "2015-01-01");
		test.datoVeddugnad("Ovensenget", "2015-01-01");
		System.out.println("Antall dager til neste veddugnad i Flåkoia: "+Vedstatus.lagVedEstimat("Flaakoia"));
		System.out.println("Antall dager til neste veddugnad i Fosenkoia: "+Vedstatus.lagVedEstimat("Fosenkoia"));
		System.out.println("Antall dager til neste veddugnad i Heinfjordstua: "+Vedstatus.lagVedEstimat("Heinfjordstua"));
		System.out.println("Antall dager til neste veddugnad i Hognabu: "+Vedstatus.lagVedEstimat("Hognabu"));
		System.out.println("Antall dager til neste veddugnad i Holmsåkoia: "+Vedstatus.lagVedEstimat("Holmsaakoia"));
		System.out.println("Antall dager til neste veddugnad i Holvassgamma: "+Vedstatus.lagVedEstimat("Holvassgamma"));
		System.out.println("Antall dager til neste veddugnad i Iglbu: "+Vedstatus.lagVedEstimat("Iglbu"));
		System.out.println("Antall dager til neste veddugnad i Kamtjønnkoia: "+Vedstatus.lagVedEstimat("Kamtjonnkoia"));
		System.out.println("Antall dager til neste veddugnad i Kråklikåten: "+Vedstatus.lagVedEstimat("Kraaklikaaten"));
		System.out.println("Antall dager til neste veddugnad i Kvernmovollen: "+Vedstatus.lagVedEstimat("Kvernmovollen"));
		System.out.println("Antall dager til neste veddugnad i Kåsen: "+Vedstatus.lagVedEstimat("Kaasen"));
		System.out.println("Antall dager til neste veddugnad i Lynhøgen: "+Vedstatus.lagVedEstimat("Lynhogen"));
		System.out.println("Antall dager til neste veddugnad i Mortenskåten: "+Vedstatus.lagVedEstimat("Mortenskaaten"));
		System.out.println("Antall dager til neste veddugnad i Nicokoia: "+Vedstatus.lagVedEstimat("Nicokoia"));
		System.out.println("Antall dager til neste veddugnad i Rindalsløa: "+Vedstatus.lagVedEstimat("Rindalsloa"));
		System.out.println("Antall dager til neste veddugnad i Selbukåten: "+Vedstatus.lagVedEstimat("Selbukaaten"));
		System.out.println("Antall dager til neste veddugnad i Sonvasskoia: "+Vedstatus.lagVedEstimat("Sonvasskoia"));
		System.out.println("Antall dager til neste veddugnad i Stabburet: "+Vedstatus.lagVedEstimat("Stabburet"));
		System.out.println("Antall dager til neste veddugnad i Stakkslettbua: "+Vedstatus.lagVedEstimat("Stakkslettbua"));
		System.out.println("Antall dager til neste veddugnad i Telin: "+Vedstatus.lagVedEstimat("Telin"));
		System.out.println("Antall dager til neste veddugnad i Taagaabu: "+Vedstatus.lagVedEstimat("Taagaabu"));
		System.out.println("Antall dager til neste veddugnad i Vekvessætra: "+Vedstatus.lagVedEstimat("Vekvessaetra"));
		System.out.println("Antall dager til neste veddugnad i Øvensenget: "+Vedstatus.lagVedEstimat("Ovensenget"));
	}

}
