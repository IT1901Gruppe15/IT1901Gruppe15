package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	private static String bruker = "mariulun_infpro";
	private static String passord = "qwertyuiop";
	private static String url = "jdbc:mysql://mysql.stud.ntnu.no/mariulun_infprodb";
	private static Connection connection = null;
	private static Statement statement;
	
	
	public Connection getConnection() {
		return connection;
	}
	
	//kobler til databasen
	public DB() {
		try {
			connection = DriverManager.getConnection(url, bruker, passord);
			statement = connection.createStatement();
		} catch (Exception e) {
			System.out.println("Tilkoblingsfeil: "
					+ e.getMessage());
		}
	}
	
	//stenger av databasen
	public void stengDB() {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//spm til databasen
	public ResultSet sporDB(String q) {
		Statement s;
		try {
			s = connection.createStatement();
			ResultSet resultat;

			resultat = s.executeQuery(q);
			return resultat;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//forandringer av databasen
	public void oppdaterDB(String q){
		Statement s;
		try {
			s = connection.createStatement();
			s.executeUpdate(q);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
