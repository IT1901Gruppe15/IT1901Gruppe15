package klasser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Rapport {
	static String koieID;
	static List<String> odelagtUtstyr;
	static int vedstatus;
	static DBConnection connection = new DBConnection();
	
	//får input fra tekstfil i følgende format:
	//koieID¤35¤odelagt1;odelagt2;odelagt3¤glemt1;glemt2
	
	public static void lesRapport(Reader input) throws IOException {
		try{
			BufferedReader reader = new BufferedReader(input);
			String line = null;
			String[] ord;
			String[] odelagt;
			String utstyrOdelagt = null;
			
			while((line = reader.readLine())!=null){
				odelagtUtstyr = new ArrayList<String>();
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
						utstyrOdelagt = ord[2];
						odelagt = ord[2].split(";");
						for(int j=0;j<odelagt.length;j++){
							odelagtUtstyr.add(odelagt[j]);
						}
						break;
					}
				}
				connection.settinnRapport(utstyrOdelagt,ord[3],vedstatus,koieID);
				endreUtstyrStatus();
				oppdaterVedStatus();
			}			
		}catch (Exception e){
			System.err.println(e.getStackTrace());
			
		}finally {
			PrintWriter writer = new PrintWriter("rapportTest.txt");
			writer.print("koie1¤35¤odelagt1.1;odelagt1.2;odelagt1.3¤glemt1.1;glemt1.2"+"\r\n"
						+"koie2¤27¤odelagt2.1;odelagt2.2;odelagt2.3¤glemt2.1;glemt2.2"+"\r\n"
						+"koie3¤16¤odelagt3.1;odelagt3.2;odelagt3.3¤glemt3.1;glemt3.2"+"\r\n"
						+"koie4¤57¤odelagt4.1;odelagt4.2;odelagt4.3¤glemt4.1;glemt4.2");
			writer.close();
		}
	}
	public static void endreUtstyrStatus(){
		for(int i = 0; i<odelagtUtstyr.size(); i++){
			connection.oppdaterUtstyr(((Number) connection.getUtstyrID(odelagtUtstyr.get(i), koieID)).intValue(),0);
		}
	}
	
	public static void oppdaterVedStatus(){
		connection.oppdaterVedstatus(koieID,vedstatus);
	}
	
	public static void main(String[] args) throws IOException {
		//kjører testfil
		String filename = "rapportTest.txt";
		FileReader file = new FileReader(filename);
		lesRapport(file);
	}
}