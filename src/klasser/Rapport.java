package klasser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Rapport {
	static String koieID;
	static List<String> odelagtUtstyr;
	static int vedstatus;
	static DBConnection connection = new DBConnection();
	
	//f�r input fra tekstfil i f�lgende format:
	//koieID�35�odelagt1;odelagt2;odelagt3�glemt1;glemt2
	
	public static void lesRapport(Reader input) throws IOException {
		try{
			BufferedReader reader = new BufferedReader(input);
			String line = null;
			String[] ord;
			String[] odelagt;
			String utstyrOdelagt = null;
			
			while((line = reader.readLine())!=null){
				odelagtUtstyr = new ArrayList<String>();
				ord = line.split("�");
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
				endreUtstyrStatus(utstyrOdelagt, ord[3]);
				oppdaterVedStatus();
			}			
		}catch (Exception e){
			System.err.println(e.getStackTrace());
			
		}finally {
			PrintWriter writer = new PrintWriter("rapportTest.txt");
			writer.print("Flaakoia�35�baat�glemt1.1;glemt1.2");
			writer.close();
		}
	}
	public static void endreUtstyrStatus(String utstyrOdelagt, String gjenglemt){
		for(int i = 0; i<odelagtUtstyr.size(); i++){
			try {
				int utstyrID = Integer.parseInt(connection.getUtstyrID(odelagtUtstyr.get(i), koieID));
				int rapportID = Integer.parseInt(connection.getrapportID(utstyrOdelagt, gjenglemt, vedstatus));
				connection.oppdaterUtstyr(utstyrID,0);
				connection.leggInnOdelagtUtstyr(utstyrID, rapportID);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void oppdaterVedStatus(){
		connection.oppdaterVedstatus(koieID,vedstatus);
	}
	
	public static void main(String[] args) throws IOException {
		//kj�rer testfil
		String filename = "rapportTest.txt";
		FileReader file = new FileReader(filename);
		lesRapport(file);
	}
}