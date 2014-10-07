package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Vedstatus {
	
	ArrayList<Integer> tallX, tallY;
	ArrayList<String> datoer;
	double sumX=0, sumY=0, sumXY=0, sumXX=0, a, b, estimat;
	int ar, maned, dag, ar2, maned2, dag2;
	String nyDato;
	DBConnection dbconnect = new DBConnection();
	
	public void lagVedEstimat(int dagerBakover, String koieID, String dato) throws SQLException{
		tallX = new ArrayList<Integer>();
		tallY = new ArrayList<Integer>();
		datoer = new ArrayList<String>();
		ar = Integer.parseInt(dato.substring(0,4));
		maned = Integer.parseInt(dato.substring(5,7));
		dag = Integer.parseInt(dato.substring(8,10));
		for(int k = dagerBakover; k >= 0; k--){
			ar2 = ar;
			if(dag-k <= 0){
				maned2 = maned - 1;
				if(maned2==0){
					ar2 = ar - 1;
					maned2 = 12;
				}
				int dagerManed = finnManed(maned2);
				dagerManed = dagerManed-(k-dag);
				dag2 = dagerManed;
			} else {
				dag2 = dag - k;
				maned2 = maned;
			}
			String nyDato = ar2+"-"+maned2+"-"+dag2;
			ResultSet t = dbconnect.getVedstatusRapport(koieID, nyDato);
			for(int s = 1; s < 15; s++){
				tallY.add(0, Integer.parseInt(t.getString(1)));
			}
			String p = t.getString(1);
			tallY.add(Integer.parseInt(p));
		}
		finnDagerBakover();
		
		for(int i = 0; i < dagerBakover; i++){
			tallX.add(i);
			sumXY += tallX.get(i)*tallY.get(i);
			sumXX += tallX.get(i)*tallX.get(i);
		}
		
		for(int j = 0; j < tallX.size(); j++){
			sumX += tallX.get(j);
			sumY += tallY.get(j);
		}
		
		a = ((sumY*sumXX)-(sumX*sumXY))/((tallX.size()*sumXX)-(sumX*sumX));
		b = ((tallX.size()*sumXY)-(sumX*sumY))/((tallX.size()*sumXX)-(sumX*sumX));
		estimat = Math.floor((a*(-1))/b);
		
		System.out.println("TallX: "+tallX);
		System.out.println("TallY: "+tallY);
		System.out.println("SumX: "+sumX);
		System.out.println("SumY: "+sumY);
		System.out.println("SumXY: "+sumXY);
		System.out.println("SumXX: "+sumXX);
		System.out.println("a: "+a);
		System.out.println("b: "+b);
		System.out.println("estimatet: "+estimat);
	}

	private void finnDagerBakover() {
		tallY.add(56);
		tallY.add(52);
		tallY.add(49);
		tallY.add(46);
		tallY.add(46);
		tallY.add(45);
		tallY.add(44);
		tallY.add(41);
		tallY.add(38);
		tallY.add(34);
		tallY.add(32);
		tallY.add(28);
		tallY.add(23);
		tallY.add(19);
	}

	private int finnManed(int m) {
		if(m==1 || m==3 || m==5 || m==7 || m==8 || m==10 || m==12){
			return 31;
		} else if (m==2){
			return 28;
		}else{
			return 30;
		}
	}

	public static void main(String[] args) throws SQLException {
		Vedstatus test = new Vedstatus();
		test.lagVedEstimat(14,"Flaakoia","2015-02-10");
	}
}
