package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Vedstatus {
	
	ArrayList<Integer> tallX, tallY;
	double sumX=0, sumY=0, sumXY=0, sumXX=0, a, b, estimat;
	int ar, maned, dag;
	String nyDato;
	DBConnection dbconnect = new DBConnection();
	
	public void lagVedEstimat(int dagerBakover, String koieID, String dato) throws SQLException{
		tallX = new ArrayList<Integer>();
		tallY = new ArrayList<Integer>();
		ar = Integer.parseInt(dato.substring(0,4));
		maned = Integer.parseInt(dato.substring(5,7));
		dag = Integer.parseInt(dato.substring(8,10));
		for(int k = dagerBakover; k >= 0; k--){
			if(dag-k < 0){
				maned -= 1;
				if(maned==0){
					ar -= 1;
					maned = 12;
				}
				int dagerManed = finnManed(maned);
				dagerManed = dagerManed-(k-dag);
				dag = dagerManed;
			}
			String nyDato = ar+"-"+maned+"-"+dag;
			ResultSet t = dbconnect.getVedstatusRapport(koieID, nyDato);
			String p = t.getString(1);
			tallY.add(Integer.parseInt(p));
		}
//		tallY.add(36);
//		tallY.add(32);
//		tallY.add(31);
//		tallY.add(31);
//		tallY.add(31);
//		tallY.add(27);
//		tallY.add(22);
		
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
		System.out.println(estimat);
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
