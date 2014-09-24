package klasser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Rapport {
	private String koieID;
	private List<String> odelagtUtstyr;
	private List<String> gjenglemteTing;
	private int vedstatus;
	DBConnection connection = new DBConnection();
	
	//får input fra tekstfil i følgende format:
	//koieID¤35¤odelagt1;odelagt2;odelagt3¤glemt1;glemt2
	
	public void lesRapport(Reader input) throws IOException {
		try{
			BufferedReader reader = new BufferedReader(input);
			int index = 0;
			String line = null;
			String[] ord;
			String[] odelagt;
			String[] gjenglemt;
			odelagtUtstyr = new ArrayList<String>();
			gjenglemteTing = new ArrayList<String>();
			
			while((line = reader.readLine())!=null){
				ord = line.split("¤");
				for(int i=0;i<ord.length;i++){
					switch(i){
					case 0:
						koieID = ord[0];
						break;
					case 1:
						vedstatus = Integer.parseInt(ord[1]);
						break;
					case 2:
						odelagt = ord[2].split(";");
						for(int j=0;j<odelagt.length;j++){
							odelagtUtstyr.add(odelagt[j]);
						}
						break;
					case 3:
						gjenglemt = ord[3].split(";");
						for(int k=0;k<gjenglemt.length;k++){
							gjenglemteTing.add(gjenglemt[k]);
						}
						break;
					}
				}
				connection.settinnRapport("tekst?","gjenglemt er ikke en streng, det er en liste",vedstatus,koieID);
				endreUtstyrStatus();
			}			
		}catch (Exception e){
			System.err.println(e.getStackTrace());
			
		}finally {
			System.out.println(koieID);
			System.out.println(vedstatus);
			System.out.println(odelagtUtstyr);
			System.out.println(gjenglemteTing);
		}
	}
	public void endreUtstyrStatus(){
		for(int i = 0; i<odelagtUtstyr.size(); i++){
			connection.oppdaterUtstyr(((Number) connection.getUtstyrID(odelagtUtstyr.get(i), koieID)).intValue(),0);
		}
	}
	
	public void oppdaterVedStatus(){
//		int r = (connection.hentVedStatus(koieID)-vedstatus); Hvis jeg skal regne ut hvor lenge det er til neste gang det trengs veddugnad
		connection.oppdaterVedstatus(koieID,vedstatus);
	}
	
	public void oppdaterGjenglemt(){
		for(int j = 0; j<gjenglemteTing.size(); j++){
			connection.leggInnGjenglemteUtstyr(koieID,gjenglemteTing.get(j));
		}
	}

	public static void main(String[] args) throws IOException {
		//kjører testfil
//		Rapport test = new Rapport();
//		String filename = "test.txt";
//		FileReader file = new FileReader(filename);
//		test.lesRapport(file);
	}
}