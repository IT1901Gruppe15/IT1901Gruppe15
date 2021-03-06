package core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/** 
 * F�r input fra tekstfil i f�lgende format:
 * koieID�vedtatus�yyyy-mm-dd�odelagt1;odelagt2;odelagt3�glemt1;glemt2	
 * HVIS ET FELT ER TOMT SKAL DET INNEHOLDE ETT MELLOMROM
 * Leser rapporten(e), legger inn det som er �delagt og t�mmer fila.
 */

public class RapportHandler {

	private static final String INPUT_FILE = "textfiles/rapporter.txt";
	private static Scanner infile = null;
	private static DBConnection connection = new DBConnection();
	
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
				String[] type = infile.nextLine().split("�");
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
							try {
								RapportHandler.Odelegg(temp[i], koieID, odelagteTing, gjenglemteTing, vedstatus);
							} catch(SQLException e) {
								e.printStackTrace();
							}
						}
					}else{
						try{
							RapportHandler.Odelegg(odelagteTing, koieID, odelagteTing, gjenglemteTing, vedstatus); 
						}catch(SQLException e){
							e.printStackTrace();
						}
					}
				}
				
				if(gjenglemteTing.length() > 1){
					ResultSet rID = connection.getRapportID(odelagteTing, gjenglemteTing, vedstatus);
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
	
	/**
	 * Endrer verdier i databsen slik at spesifikt utstyr er �delagt
	 * 
	 * @param utstyrsnavn Navnet p� utstyret som har blitt �delagt
	 * @param koieID Identifikasjonen til koien
	 * @param odelagteTing Streng med alle ting som har blitt �delagt
	 * @param gjenglemteTing Streng med alle ting som har blitt gjenglemt
	 * @param vedstatus Status p� gjenv�rende mengde ved i koia
	 * @throws SQLException
	 */
	public static void Odelegg(String utstyrsnavn, String koieID, String odelagteTing, String gjenglemteTing, int vedstatus) throws SQLException {
		ResultSet rsU = connection.getUtstyrID(utstyrsnavn, koieID);
		rsU.next();
		int utstyrsID = rsU.getInt(1);
		ResultSet rsR = connection.getRapportID(odelagteTing, gjenglemteTing, vedstatus);
		rsR.next();
		int rapportID = rsR.getInt(1);
		connection.oppdaterUtstyr(utstyrsID, 0);
		connection.leggInnOdelagtUtstyr(utstyrsID, rapportID);
	}
	
	/**
	 * Legger til gjenglemte ting i koien inn i databasen
	 * 
	 * @param gjenglemteTing Streng med alle gjenglemte ting
	 * @param koieID Identifikasjonen til koien
	 * @param rapportID Vite hvilken rapport som meldte om det gjenglemte
	 * @throws SQLException
	 */
	public static void glemt(String gjenglemteTing, String koieID, int rapportID) throws SQLException{
		
		if(gjenglemteTing.contains(";")){
			String[] split = gjenglemteTing.split(";");
			for(int i = 0; i<split.length;i++){
				connection.leggInnGjenglemteTing(split[i], rapportID, koieID);
			}
			
		}else{
			connection.leggInnGjenglemteTing(gjenglemteTing, rapportID, koieID);
		}
	}
	
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
	
}
