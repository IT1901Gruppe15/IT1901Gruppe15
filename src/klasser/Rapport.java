package klasser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Rapport {
	private String koieID, gjenglemte ting;
	private List<Utstyr> odelagtUtstyr;
	private int vedstatus;
	
	
	public void lesRapport(Reader input) {
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
			
		}
		
	}
	public void endreUtstyrStatus(){
		
	}
	public void oppdaterVedStatus(){
		
	}
	public void oppdaterGjenglemt(){
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
