package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Vedstatus {
	
	ArrayList<Integer> tallX, tallY;
	ArrayList<String> datoer;
	double sumX=0, sumY=0, sumXY=0, sumXX=0, a, b;
	int ar1, maned1, dag1, ar2, maned2, dag2, estimat, antallDager;
	DBConnection dbconnect = new DBConnection();
	String dato;
	boolean skuddar = false;
	
	/**
	 * @param koieID Identifikasjonen til koien hvor vi skal hente vedstatus
	 * @return Integer som viser antall dager til neste veddugnad
	 * @throws SQLException
	 */
	public int lagVedEstimat(String koieID) throws SQLException{
		ResultSet p = dbconnect.getForrigeVeddugnad(koieID);
		dato = p.getString(1);
		tallX = new ArrayList<Integer>();
		tallY = new ArrayList<Integer>();
		datoer = new ArrayList<String>();
		ResultSet t = dbconnect.getDatoListe(koieID, dato);
		int s = 1;
		while(t.next() && s < 15){
			datoer.add(0, t.getString(1));
			tallY.add(0, t.getInt(2));
			s+=1;
		}
		tallX.add(0);
		System.out.println(datoer);
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
				antallDager -= finnManed(l);
			}
			for(int m = 1; m <= maned2; m++){
				antallDager += finnManed(m);
			}
			antallDager += (finnManed(maned1)-dag1);
			antallDager -= (finnManed(maned2)-dag2);
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
		estimat = (int) Math.floor((a*(-1))/b);
		
		System.out.println("TallX: "+tallX);
		System.out.println("TallY: "+tallY);
		System.out.println("SumX: "+sumX);
		System.out.println("SumY: "+sumY);
		System.out.println("SumXY: "+sumXY);
		System.out.println("SumXX: "+sumXX);
		System.out.println("a: "+a);
		System.out.println("b: "+b);
		System.out.println("estimatet: "+estimat);
		return estimat;
	}

	/**
	 * @param m Hvilken måned det er ut ifra tallet i datoen
	 * @return Integer med antall dager i m måneder
	 */
	private int finnManed(int m) {
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

	public static void main(String[] args) throws SQLException {
		Vedstatus test = new Vedstatus();
		test.lagVedEstimat("Flaakoia");
	}
}
