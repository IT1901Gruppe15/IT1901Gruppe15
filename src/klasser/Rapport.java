package klasser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Rapport {
	private String koieID;
	private List<String> odelagtUtstyr, gjenglemteTing;
	private int vedstatus;
	
	//får input fra tekstfil i følgende format:
	//"KoieID"
	//koieID
	//Vedstatus
	//35
	//"Odelagt"
	//odelagt1
	//odelagt2
	//odelagt3
	//"Gjenglemt"
	//glemt1
	//glemt2
	
	public void lesRapport(Reader input) throws IOException {
		BufferedReader reader = new BufferedReader(input);
		String line = null;
		//instansierer begge listene som ArrayList
		odelagtUtstyr = new ArrayList<String>();
		gjenglemteTing = new ArrayList<String>();
		try{
			while((line = reader.readLine())!=null){//kjører denne while-løkka så lenge det finnes en neste linje i input-filen
				if(line.equals("KoieID")){
					line = reader.readLine();//hopper over nåværende linje, siden den bare er en overskrift
					koieID=line;//setter koieID til linje 2 i input
				}else if(line.equals("Vedstatus")){
					line = reader.readLine();//hopper over overskrift
					vedstatus = Integer.parseInt(line);//setter ny vedstatus for koien 
				}else if(line.equals("Odelagt")){
					while(!(line = reader.readLine()).equals("Gjenglemt")){
					//sjekker at den neste linja ikke er strengen samtidig som den setter den linja til variabelen
						odelagtUtstyr.add(line);
					}
					while((line = reader.readLine()) != null){
					//kjører til det ikke finnes flere linjer siden det er siste overskrift
					//kjøres her inne slik at ikke de første variablene lagres her også
						gjenglemteTing.add(line);
					}
				}
			}			
		}finally {
			System.out.println(koieID);
			System.out.println(vedstatus);
			System.out.println(odelagtUtstyr);
			System.out.println(gjenglemteTing);
		}
	}
	public void endreUtstyrStatus(){
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
		Rapport test = new Rapport();
		String filename = "test.txt";
		FileReader file = new FileReader(filename);
		test.lesRapport(file);
	}
}