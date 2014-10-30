package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Estimerer antall dager til neste veddugnad basert på tidligere vedstatus
 */
public class Vedstatus {
	
	private static ArrayList<Integer> tallX, tallY;
	private static ArrayList<String> datoer;
	private static String dato;
	private static double sumX=0, sumY=0, sumXY=0, sumXX=0, a, b;
	private static int ar1, maned1, dag1, ar2, maned2, dag2, estimat, antallDager, totalRows;
	private static DBConnection dbconnect = new DBConnection();
	private static boolean skuddar = false, negativMengde = false;
	
	/**
	 * Estimerer antall dager til neste veddugnad basert på tidligere vedstatus
	 * 
	 * @param koieID Identifikasjonen til koien hvor vi skal hente vedstatus
	 * @return Integer som viser antall dager til neste veddugnad
	 * @throws SQLException
	 */
	public static int lagVedEstimat(String koieID) throws SQLException{
		tallX = new ArrayList<Integer>();
		tallY = new ArrayList<Integer>();
		datoer = new ArrayList<String>();
		ResultSet p = dbconnect.getForrigeVeddugnad(koieID);
		p.beforeFirst();
		if(p.next()){
			dato=p.getString(1);
		}
		ResultSet t = dbconnect.getDatoListe(koieID,dato);
		t.last();
	    totalRows = t.getRow();
	    t.beforeFirst();
	    System.out.println(totalRows);
		while(totalRows<=3){
			int bak1dag = Integer.parseInt((dato.substring(8,10)));
			bak1dag-=14;
			if(bak1dag<=0){
				int bak1maned = Integer.parseInt(dato.substring(5,7));
				bak1maned-=1;
				bak1dag = finnManed(bak1maned,false)+bak1dag;
				if(bak1maned<10){
					dato = dato.substring(0,5)+"0"+bak1maned+dato.substring(7);
				} else {
					dato = dato.substring(0,5)+bak1maned+dato.substring(7);
				}
			}
			if(bak1dag<10){
				dato = dato.substring(0,8)+"0"+bak1dag;
			} else {
				dato = dato.substring(0,8)+""+bak1dag;
			}
			t = dbconnect.getDatoListe(koieID, dato);
			t.last();
		    totalRows = t.getRow();
		    t.beforeFirst();
		    negativMengde = true;
		}
		
		while(t.next() && t.getRow() < 15){
			int l = t.getInt(2);
			if(tallY.isEmpty() || l<=tallY.get(0)){
				datoer.add(0, t.getString(1));
				tallY.add(0, l);
			}
		}
		tallX.add(0);
		for(int k = 1; k < tallY.size(); k++){
			dag1 = Integer.parseInt((datoer.get(k-1)).substring(8,10));
			maned1 = Integer.parseInt((datoer.get(k-1)).substring(5,7));
			ar1 = Integer.parseInt((datoer.get(k-1)).substring(0,4));
			dag2 = Integer.parseInt((datoer.get(k)).substring(8,10));
			maned2 = Integer.parseInt((datoer.get(k)).substring(5,7));
			ar2 = Integer.parseInt((datoer.get(k)).substring(0,4));
			if((ar1&4) == 0 || (ar2&4) == 0){
				skuddar = true;
				antallDager = ((ar2-ar1)*366);
			} else {
				antallDager = ((ar2-ar1)*365);
			}
			for(int l = 1; l <= maned1; l++){
				antallDager -= finnManed(l, skuddar);
			}
			for(int m = 1; m <= maned2; m++){
				antallDager += finnManed(m, skuddar);
			}
			antallDager += (finnManed(maned1, skuddar)-dag1);
			antallDager -= (finnManed(maned2, skuddar)-dag2);
			tallX.add(antallDager+tallX.get(k-1));
		}
		
		for(int i = 0; i < tallY.size(); i++){
			sumXY += tallX.get(i)*tallY.get(i);
			sumXX += tallX.get(i)*tallX.get(i);
		}
		
		for(int j = 0; j < tallX.size(); j++){
			sumX += tallX.get(j);
			sumY += tallY.get(j);
		}
		
		a = ((sumY*sumXX)-(sumX*sumXY))/((tallX.size()*sumXX)-(sumX*sumX));
		b = ((tallX.size()*sumXY)-(sumX*sumY))/((tallX.size()*sumXX)-(sumX*sumX));
		System.out.println((int) Math.floor((a*(-1))/b));
		if(negativMengde){
			ResultSet q = dbconnect.getDatoListe(koieID, dato);
			while(q.next()){
				a=q.getInt(2);
			}
		}
		estimat = (int) Math.floor((a*(-1))/b);
		System.out.println((int) Math.floor((a*(-1))/b));
		return estimat;
	}
	/**
	 * Finner hvor mange dager det er i hver måned
	 * 
	 * @param m Hvilken måned det er ut ifra tallet i datoen
	 * @return Integer med antall dager i m måneder
	 */
	private static int finnManed(int m, Boolean skuddar) {
		if(m==1 || m==3 || m==5 || m==7 || m==8 || m==10 || m==12){
			return 31;
		} else if (m==2){
			if(skuddar){
				return 29;
			} else {
				return 28;
			}
		}else{
			return 30;
		}
	}
}
