package klasser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Rapport {
	private static String koieID;
	private static List<String> odelagtUtstyr;
	private static List<String> gjenglemteTing;
	private static int vedstatus;
	DBConnection connection = new DBConnection();
	
	//får input fra tekstfil i følgende format:
	//koieID¤35¤odelagt1;odelagt2;odelagt3¤glemt1;glemt2
	
	public static void lesRapport(Reader input) throws IOException {
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
		int k = odelagtUtstyr.size();
		int id = 0; 
		for(int i=0;i<k;i++){
			id = ((Number) connection.getUtstyrID(odelagtUtstyr.get(i), koieID)).intValue();
			connection.oppdaterUtstyr(id,0);
		}
		//TODO: finne utstyr fra database utifra odelagtUtstyr liste
		//TODO: endre utstyrstatus til ødelagt 
		
	}
	public void oppdaterVedStatus(){
		//TODO: skaff tidligere vedstatus fra database
		//TODO: skaff antall dager (fra database? fra reservasjonsklasse?)
		//TODO: lag utregning med (gammel_vedstatus - ny_vedstatus)/antall_dager 
	}
	public void oppdaterGjenglemt(){
		//TODO: finne Koie fra koieID
		//TODO: legge gjenglemteTing-lista inn i gjenglemt-lista som befinner seg i det objektet
	}

	public static void main(String[] args) throws IOException {
		//kjører testfil
//		Rapport test = new Rapport();
//		String filename = "test.txt";
//		FileReader file = new FileReader(filename);
//		test.lesRapport(file);
	}
}