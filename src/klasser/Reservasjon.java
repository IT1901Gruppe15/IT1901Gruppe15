package klasser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Reservasjon {
	String koieID;
	private String epost,dato;
	
	public void lesReservasjon(){
		BufferedReader reader=null; 
		try{
		File file = new File("testinput.txt");
		reader=new BufferedReader(new FileReader(file));
		String line = null;
			while((line = reader.readLine())!=null){
				int pos = line.indexOf(";");
				if(pos>0){
					koieID=line.substring(0,pos);
				}
				int pos2 = line.indexOf(";",pos+1);
				epost=line.substring(pos,pos2);
				dato = line.substring(pos2);
				
			}
		}catch (Exception e){
		System.err.println(e.getStackTrace());
			
		}
			
		
	}

	public static void main(String[] args) {
		Reservasjon res = new Reservasjon();
		res.lesReservasjon();
		System.out.println(res.epost+res.dato+res.koieID);
		// TODO Auto-generated method stub

	}

}
