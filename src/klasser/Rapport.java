package klasser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Rapport {
	private String koieID;
	private List<String> gjenglemte_ting;
	private List<Utstyr> odelagtUtstyr;
	private int vedstatus;


	public void lesRapport( ) {
		try{
			BufferedReader reader = new BufferedReader(new FileReader("testinput"));
			String line = null;
			while((line = reader.readLine())!=null){
				int pos = line.indexOf(";");
				if(pos>0){
					koieID=line.substring(0,pos);
				}
				pos = line.indexOf(";",pos+1);
			}
			reader.close();
		}
		catch(IOException e) {
			e.printStackTrace();
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
