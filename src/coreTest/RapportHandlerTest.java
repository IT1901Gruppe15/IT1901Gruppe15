package coreTest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import core.RapportHandler;

public class RapportHandlerTest {
	
	ArrayList<String> koier = new ArrayList<String>();

	@Test
	public void testLesRapport() throws IOException {
		FileWriter f1 = new FileWriter("textfiles/rapporter.txt");
		koier.add("Fosenkoia");
		koier.add("Heinfjordstua");
		koier.add("Hognabu");
		koier.add("Holmsaakoia");
		koier.add("Holvassgamma");
		koier.add("Iglbu");
		koier.add("Kamtjonnkoia");
		koier.add("Kraaklikaaten");
		koier.add("Kvernmovollen");
		koier.add("Kaasen");
		koier.add("Lynhogen");
		koier.add("Mortenskaaten");
		koier.add("Nicokoia");
		koier.add("Rindalsloa");
		koier.add("Selbukaaten");
		koier.add("Sonvasskoia");
		koier.add("Stabburet");
		koier.add("Stakkslettbua");
		koier.add("Telin");
		koier.add("Taagaabu");
		koier.add("Vekvessaetra");
		koier.add("Ovensenget");
		for(int i = 0; i < koier.size(); i++){
			f1.write(koier.get(i)+"¤"+(int) Math.floor((70+((((i+1)^2)*0.1)+(10/(i+1))))-i)+"¤"+"2014-09-08"+"¤"+" "+"¤"+" "+"\r\n");
			f1.write(koier.get(i)+"¤"+(int) Math.floor((68+((((i+1)^2)*0.1)+(10/(i+1))))-i)+"¤"+"2014-09-13"+"¤"+" "+"¤"+" "+"\r\n");
			f1.write(koier.get(i)+"¤"+(int) Math.floor((66+((((i+1)^2)*0.1)+(10/(i+1))))-i)+"¤"+"2014-09-17"+"¤"+" "+"¤"+" "+"\r\n");
			f1.write(koier.get(i)+"¤"+(int) Math.floor((64+((((i+1)^2)*0.1)+(10/(i+1))))-i)+"¤"+"2014-09-20"+"¤"+" "+"¤"+" "+"\r\n");
			f1.write(koier.get(i)+"¤"+(int) Math.floor((62+((((i+1)^2)*0.1)+(10/(i+1))))-i)+"¤"+"2014-09-25"+"¤"+" "+"¤"+" "+"\r\n");
			f1.write(koier.get(i)+"¤"+(int) Math.floor((60+((((i+1)^2)*0.1)+(10/(i+1))))-i)+"¤"+"2014-09-28"+"¤"+" "+"¤"+" "+"\r\n");
			f1.write(koier.get(i)+"¤"+(int) Math.floor((58+((((i+1)^2)*0.1)+(10/(i+1))))-i)+"¤"+"2014-10-01"+"¤"+" "+"¤"+" "+"\r\n");
			f1.write(koier.get(i)+"¤"+(int) Math.floor((56+((((i+1)^2)*0.1)+(10/(i+1))))-i)+"¤"+"2014-10-04"+"¤"+" "+"¤"+" "+"\r\n");
			f1.write(koier.get(i)+"¤"+(int) Math.floor((54+((((i+1)^2)*0.1)+(10/(i+1))))-i)+"¤"+"2014-10-07"+"¤"+" "+"¤"+" "+"\r\n");
			f1.write(koier.get(i)+"¤"+(int) Math.floor((52+((((i+1)^2)*0.1)+(10/(i+1))))-i)+"¤"+"2014-10-10"+"¤"+" "+"¤"+" "+"\r\n");
			f1.write(koier.get(i)+"¤"+(int) Math.floor((50+((((i+1)^2)*0.1)+(10/(i+1))))-i)+"¤"+"2014-10-15"+"¤"+" "+"¤"+" "+"\r\n");
			f1.write(koier.get(i)+"¤"+(int) Math.floor((48+((((i+1)^2)*0.1)+(10/(i+1))))-i)+"¤"+"2014-10-16"+"¤"+" "+"¤"+" "+"\r\n");
			f1.write(koier.get(i)+"¤"+(int) Math.floor((46+((((i+1)^2)*0.1)+(10/(i+1))))-i)+"¤"+"2014-10-17"+"¤"+" "+"¤"+" "+"\r\n");
			f1.write(koier.get(i)+"¤"+(int) Math.floor((44+((((i+1)^2)*0.1)+(10/(i+1))))-i)+"¤"+"2014-10-18"+"¤"+" "+"¤"+" "+"\r\n");
			f1.write(koier.get(i)+"¤"+(int) Math.floor((42+((((i+1)^2)*0.1)+(10/(i+1))))-i)+"¤"+"2014-10-21"+"¤"+" "+"¤"+" "+"\r\n");
			f1.write(koier.get(i)+"¤"+(int) Math.floor((40+((((i+1)^2)*0.1)+(10/(i+1))))-i)+"¤"+"2014-10-22"+"¤"+" "+"¤"+" "+"\r\n");
			f1.write(koier.get(i)+"¤"+(int) Math.floor((38+((((i+1)^2)*0.1)+(10/(i+1))))-i)+"¤"+"2014-10-23"+"¤"+" "+"¤"+" "+"\r\n");
		}
		f1.close(); 
		
		RapportHandler.lesRapport();
	}

}
