package klasser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class Reservasjon {
	private int reservasjonsID;
	String koieID;
	private String epost,dato;
	
	public void lesReservasjon(Reader input){
		BufferedReader reader = new BufferedReader(input);
		String line = null;
		try{
			while((line = reader.readLine())!=null){
				int pos = line.indexOf(";");
				if(pos>0){
					koieID=line.substring(0,pos);
				}
				pos = line.indexOf(";",pos+1);
				
				
			}
		}catch (Exception e);
			
		
	}
	
	

	
		
		
	
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
