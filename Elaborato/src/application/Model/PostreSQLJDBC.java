package application.Model;

import java.sql.*;
import java.util.ArrayList;

public class PostreSQLJDBC {
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/db";
	private static final String DB_User = "postgres";
	private static final String DB_Password = "Ciao1107";
	private static final String QUERY_LoginU = "SELECT * FROM join(Persona Ragazzo) WHERE email= ? and password = ?";
	private static final String INSERT_Ragazzo = "INSERT INTO Persona (Cf, Nome, Cognome, DataDiNascita, EMail ) VALUES (?, ?, ?, ?, ?); \r\n"
									+ "INSERT INTO Ragazzo (Hobby, Indirizzo, passwd, nrtelefono, personafk) VALUES ( ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_Allergia = "INSERT INTO Allergia (Nome, Ragazzofk, Precauzioni) VALUES (?, ?, ?)";
	private static final String INSERT_Genitore = "INSERT INTO Persona (Cf, Nome, Cognome, DataDiNascita, EMail ) VALUES (?, ?, ?, ?, ?); \r\n"
									+ "INSERT INTO Genitore (nrtelefono, personafk, figliofk) VALUES ( ?, ?, ?)";
	
	
	
	public static void Validate(String emailId, String password) throws SQLException {

		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");

	}

	public static void Registrazione(ArrayList<Allergene> Allergie, ArrayList<Genitore> Genitori, Ragazzo Utente ) throws SQLException{
		
		String sqlAllergie = new String();
		String sqlGenitori = new String();
			
		if(Genitori.size() == 2) {
			sqlGenitori = INSERT_Genitore + "\n " + INSERT_Genitore;
		}else sqlGenitori = INSERT_Genitore;
		
		for(int i = 0; i < Allergie.size(); i++) {
			sqlAllergie = INSERT_Allergia + " \n";
		}
		
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
			PreparedStatement ragazzo = c.prepareStatement(INSERT_Ragazzo);
			PreparedStatement gen = c.prepareStatement(sqlGenitori);
			PreparedStatement all = c.prepareStatement(sqlAllergie);
			
			ragazzo.clearParameters();
			gen.clearParameters();
			all.clearParameters();
		
			ragazzo.setString(1, Utente.getCF());
			ragazzo.setString(2, Utente.getNome());
			ragazzo.setString(3, Utente.getCognome());
			ragazzo.setString(4, Utente.getDdN().toString());
			ragazzo.setString(5, Utente.getEmail());
			ragazzo.setString(6, Utente.getHobby());		
			ragazzo.setString(7, Utente.getIndirizzo());
			ragazzo.setString(8, Utente.getPassword());
			ragazzo.setString(9, Utente.getNrTelefono());
			ragazzo.setString(10, Utente.getCF());
			
			int x = 0;
			for(Genitore i: Genitori) {
				gen.setString(x+1, i.getCF());
				gen.setString(x+2, i.getNome());
				gen.setString(x+3, i.getCognome());
				gen.setString(x+4, i.getDdN().toString());
				gen.setString(x+5, i.getEmail());
				gen.setString(x+6, i.getNrTelefono());
				gen.setString(x+7, i.getCF());
				gen.setString(x+8, Utente.getCF());
				x = 8;
			}
			
			int y = 0;
			for(Allergene a: Allergie) {
				all.setString(y+1, a.getNome());
				all.setString(y+2, Utente.getCF());
				all.setString(y+3, a.getDescrizione());
				
				y = 3;
			}


		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");

	}
}
