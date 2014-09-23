package klasser;

import java.io.BufferedReader;
<<<<<<< HEAD
=======



>>>>>>> origin/master
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import textfiles.*;

public class Reservasjon {
	String koieID;
	private String epost,start_dato,slutt_dato;
	DBConnection db = new DBConnection();
	public void dateIsValid(){
		
	}
	public void lesReservasjon(){
		BufferedReader reader=null; 
		
		try{		
		InputStream inputStream = Reservasjon.class.getResourceAsStream("testinput");

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
	public void update(){
		db.settinnReservasjon(getEpost(), getStart_dato(), getSlutt_dato(), getKoieID());
		
	}

	public String getKoieID() {
		return koieID;
	}
	public String getEpost() {
		return epost;
	}
	public String getStart_dato() {
		return start_dato;
	}
	public String getSlutt_dato() {
		return slutt_dato;
	}
	public static void main(String[] args) {
		Reservasjon res = new Reservasjon();
		res.lesReservasjon();
		res.update();
		System.out.println(res.koieID + res.epost+res.start_dato + res.slutt_dato);
		// TODO Auto-generated method stub
		
	}

}
