package klasser;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Reservasjon {
	String koieID;
	private String epost,start_dato,slutt_dato;
	
	public void lesReservasjon(){
		BufferedReader reader=null; 
		
		try{		
		InputStream inputStream = Reservasjon.class.getResourceAsStream("testinput.txt");

		reader=new BufferedReader(new InputStreamReader(inputStream));
		String line = null;	
		line = reader.readLine();
		String[] list = line.split(";");
		for (int i=0;i<list.length;i++){
			switch(i){
			case 0:
				koieID = list[0];
				break;
			case 1:
				epost=list[1];
				break;
			case 2:
				start_dato=list[2];
				break;
			case 3:
				slutt_dato=list[3];
			
			}
		}
		

		}catch (Exception e){
		System.err.println(e.getStackTrace());
			
		}
			
		
	}

	public static void main(String[] args) {
		Reservasjon res = new Reservasjon();
		res.lesReservasjon();
		System.out.println(res.koieID + res.epost+res.start_dato + res.slutt_dato);
		// TODO Auto-generated method stub

	}

}
