package klasser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Rapport {
	static String koieID, dato;
	static List<String> odelagtUtstyr;
	static int vedstatus;
	static DBConnection connection = new DBConnection();

	//får input fra tekstfil i følgende format:
	//koieID¤35¤yyyy-mm-dd¤odelagt1;odelagt2;odelagt3¤glemt1;glemt2

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
						dato = ord[2];
					case 3:
						utstyrOdelagt = ord[3];
						odelagt = ord[3].split(";");
						for(int j=0;j<odelagt.length;j++){
							odelagtUtstyr.add(odelagt[j]);
						}
						break;
					}
				}
				connection.settinnRapport(utstyrOdelagt,ord[4],vedstatus,koieID,dato);
				endreUtstyrStatus(utstyrOdelagt, ord[4]);
				oppdaterVedStatus();
			}			
		}catch (Exception e){
			System.err.println(e.getStackTrace());

		}finally {
			PrintWriter writer = new PrintWriter("rapportTest.txt");
			writer.print("Flaakoia¤35¤baat¤glemt1.1;glemt1.2");
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

	public static String formaterTekst(String tekst, String separator) { // gjør om tekst til formatet
		String[] liste = tekst.split(separator);						 // ting1;ting2;ting3
		String ferdigTekst = "";
		for (int i = 0; i < liste.length; i++) {
			ferdigTekst += liste[i] + ";";
		}
		ferdigTekst = ferdigTekst.substring(0, ferdigTekst.length() - 1);
		return ferdigTekst.trim();
	}

	public static void oppdaterVedStatus(){
		connection.oppdaterVedstatus(koieID,vedstatus);
	}
	
	public static String getUtstyr(String koieID) throws SQLException{
		ResultSet ou;
		String odelagt = null;
		
		ou = connection.getOdelagtUtstyr(koieID);
		while(ou.next()){
			odelagt += ou.getString(1) + ";";
		}
		
		return odelagt;
	}
	
	public static void main(String[] args) throws IOException {
		//kjører testfil
		String filename = "rapportTest.txt";
		FileReader file = new FileReader(filename);
		lesRapport(file);
	}
}