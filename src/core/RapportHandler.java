package core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RapportHandler {

	/** 
	 * Får input fra tekstfil i følgende format:
	 * koieID¤vedtatus¤yyyy-mm-dd¤odelagt1;odelagt2;odelagt3¤glemt1;glemt2	
	 * HVIS ET FELT ER TOMT SKAL DET INNEHOLDE ETT MELLOMROM
	 * Leser rapporten(e), legger inn det som er ødelagt og tømmer fila.
	*/
	
	static final String INPUT_FILE = "textfiles/rapporter.txt";
	static Scanner infile = null;
	static DBConnection connection = new DBConnection();
	
	//les alle rapporter fra tekstfil
	public static void lesRapport() throws FileNotFoundException {
		
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

				connection.settinnRapport(odelagteTing, gjenglemteTing, vedstatus, koieID, dato);
				
				if(odelagteTing.length() > 1){
					if(odelagteTing.contains(";")){
						String[] temp = odelagteTing.split(";");
						for(int i = 0; i<temp.length; i++){
							try{
								RapportHandler.Odelegg(temp[i], koieID, odelagteTing, gjenglemteTing, vedstatus);
							}catch(Exception e){
								System.out.println(e);
							}
						}
					}else{
						try{
							RapportHandler.Odelegg(odelagteTing, koieID, odelagteTing, gjenglemteTing, vedstatus); 
						}catch(Exception e){
							System.out.println(e);
						}
					}
				}
				
				if(gjenglemteTing.length() > 1){
					ResultSet rID = connection.getrapportID(odelagteTing, gjenglemteTing, vedstatus);
					rID.next();
					int rapportID = rID.getInt(1);
					RapportHandler.glemt(gjenglemteTing, koieID, rapportID);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			infile.close();
			RapportHandler.wipeFile();
		}
	}
	
	//endre et utstyrs status
	private static void Odelegg(String utstyrsnavn, String koieID, String odelagteTing, String gjenglemteTing, int vedstatus) throws SQLException {
		ResultSet rsU = connection.getUtstyrID(utstyrsnavn, koieID);
		rsU.next();
		int utstyrsID = rsU.getInt(1);
		ResultSet rsR = connection.getrapportID(odelagteTing, gjenglemteTing, vedstatus);
		rsR.next();
		int rapportID = rsR.getInt(1);
		connection.oppdaterUtstyr(utstyrsID, 0);
		connection.leggInnOdelagtUtstyr(utstyrsID, rapportID);
	}
	
	//legg inn gjenglemt ting
	private static void glemt(String gjenglemteTing, String koieID, int rapportID) throws SQLException{
		
		if(gjenglemteTing.contains(";")){
			String[] split = gjenglemteTing.split(";");
			for(int i = 0; i<split.length;i++){
				connection.leggInnGjenglemteTing(split[i], rapportID, koieID);
			}
			
		}else{
			connection.leggInnGjenglemteTing(gjenglemteTing, rapportID, koieID);
		}
		
	}
	
	//slett innhold i fila... IKKE SELVE FILA
	private static void wipeFile() throws FileNotFoundException{
		PrintWriter writer = new PrintWriter(INPUT_FILE);
		writer.print("");
		writer.close();
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
	
	public static void main(String[] args) throws FileNotFoundException {
		RapportHandler.lesRapport();
	}
}
