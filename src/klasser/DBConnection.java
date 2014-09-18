package klasser;

import java.sql.ResultSet;
import database.DB;

public class DBConnection {

	private DB db;
	
	public DBConnection(){
		db = new DB();
	}
	
	public ResultSet login(String brukernavn){
		
		String q = ("select Brukernavn, Passord from Admin where Brukernavn = '" + brukernavn + "';");
		
		return db.sporDB(q);
	}
	
}
