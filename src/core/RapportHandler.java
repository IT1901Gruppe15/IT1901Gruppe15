package core;

import java.io.FileReader;
import java.sql.ResultSet;
import java.util.Scanner;

public class RapportHandler {

	/** 
	 * Får input fra tekstfil i følgende format:
	 * koieID¤vedtatus¤yyyy-mm-dd¤odelagt1;odelagt2;odelagt3¤glemt1;glemt2	
	*/
	
	static final String INPUT_FILE = "rapporter.txt";
	static Scanner infile = null;
	static DBConnection connection = new DBConnection();
	
	//les alle rapporter fra tekstfil
	public static void lesRapport() {
		
		try {
			infile = new Scanner(new FileReader(INPUT_FILE));
			while (infile.hasNextLine()) {
				
				String koieID, dato, gjenglemteTing, odelagteTing;
				int vedstatus;
				String[] type = infile.nextLine().split("¤");
				koieID = type[0];
				vedstatus = Integer.parseInt(type[1]);
				dato = type[2];
				odelagteTing = type[3];
				gjenglemteTing = type[4];

				//sett inn rapport i DB
				connection.settinnRapport(odelagteTing, gjenglemteTing, vedstatus, koieID, dato);
				
				try {
				    Thread.sleep(1000);                 //1000 milliseconds is one second.
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
				
				//oppdater ødelagt utstyr
				//legg inn utlagt utstyr i odelagt-tabellen
				if(odelagteTing.contains(";")){
					String[] temp = odelagteTing.split(";");
					for(int i = 0; i<temp.length; i++){
						try{
							ResultSet rsU = connection.getUtstyrID(temp[i], koieID);
							rsU.next();
							int utstyrsID = rsU.getInt(1);
							ResultSet rsR = connection.getrapportID(odelagteTing, gjenglemteTing, vedstatus);
							rsR.next();
							int rapportID = rsR.getInt(1);
							connection.oppdaterUtstyr(utstyrsID, 0);
							connection.leggInnOdelagtUtstyr(utstyrsID, rapportID);
						}catch(Exception e){
							System.out.println(e);
						}
					}
				}else{
					try{
						ResultSet rsU = connection.getUtstyrID(odelagteTing, koieID);
						rsU.next();
						int utstyrsID = rsU.getInt(1);
						ResultSet rsR = connection.getrapportID(odelagteTing, gjenglemteTing, vedstatus);
						rsR.next();
						int rapportID = rsR.getInt(1);
						connection.oppdaterUtstyr(utstyrsID, 0);
						connection.leggInnOdelagtUtstyr(utstyrsID, rapportID);
					}catch(Exception e){
						System.out.println(e);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			infile.close();
		}
	}
	
	//endre et utstyrs status
	public void endreUtstyrsstatus(){
		
	}
	
	
	//test
	public static void main(String[] args) {
		RapportHandler.lesRapport();
	}
	
	//slett innhold i fila... IKKE SELVE FILA
	private static void wipeFile(){
		
	}
	
	
}
