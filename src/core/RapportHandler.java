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
	/**
	 * Leser rapporter som linjer fra egen tekstfil
	 * 
	 * @throws FileNotFoundException
	 */
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
	/**
	 * Endrer verdier i databsen slik at spesifikt utstyr er ødelagt
	 * 
	 * @param utstyrsnavn Navnet på utstyret som har blitt ødelagt
	 * @param koieID Identifikasjonen til koien
	 * @param odelagteTing Streng med alle ting som har blitt ødelagt
	 * @param gjenglemteTing Streng med alle ting som har blitt gjenglemt
	 * @param vedstatus Status på gjenværende mengde ved i koia
	 * @throws SQLException
	 */
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
	/**
	 * Legger til gjenglemte ting i koien inn i databasen
	 * 
	 * @param gjenglemteTing Streng med alle gjenglemte ting
	 * @param koieID Identifikasjonen til koien
	 * @param rapportID Vite hvilken rapport som meldte om det gjenglemte
	 * @throws SQLException
	 */
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
	/**
	 * Sletter innholdet i input filen
	 * 
	 * @throws FileNotFoundException
	 */
	private static void wipeFile() throws FileNotFoundException{
		PrintWriter writer = new PrintWriter(INPUT_FILE);
		writer.print("");
		writer.close();
	}
	
	/**
	 * gjør om tekst til formatet: ting1;ting2;ting3
	 * 
	 * @param tekst Teksten som endres
	 * @param separator Hvilket symbol(er) som skiller tekstbitene
	 * @return returnerer den ferdige teksten
	 */
	public static String formaterTekst(String tekst, String separator) {
		String[] liste = tekst.split(separator);
		String ferdigTekst = "";
		for (int i = 0; i < liste.length; i++) {
			ferdigTekst += liste[i] + ";";
		}
		ferdigTekst = ferdigTekst.substring(0, ferdigTekst.length() - 1);
		ferdigTekst = ferdigTekst.trim();
		if (ferdigTekst.length() == 0) {
			return " ";
		}
		return ferdigTekst;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		RapportHandler.lesRapport();
	}
}
