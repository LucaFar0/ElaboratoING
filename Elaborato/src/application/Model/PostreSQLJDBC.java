package application.Model;


import java.sql.*;
import java.util.ArrayList;

public class PostreSQLJDBC {
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/db";
	private static final String DB_User = "postgres";
	private static final String DB_Password = "Ciao1107";
	private static  String QUERY_LoginU = "SELECT * FROM join(Persona Ragazzo) WHERE email= ? and password = ?";
	private static  String INSERT_Persona = "INSERT INTO Persona (Cf, Nome, Cognome, DataDiNascita, EMail) VALUES (?, ?, ?, ?, ?);";
	private static  String INSERT_Ragazzo = "INSERT INTO Ragazzo (Hobby, Indirizzo, passwd, nrtelefono, personafk) VALUES (?, ?, ?, ?, ?); ";
	private static  String INSERT_Allergia = "INSERT INTO Allergia (Nome, Ragazzofk, Precauzioni) VALUES (?, ?, ?); ";
	private static  String INSERT_Genitore = "INSERT INTO Genitore (nrtelefono, personafk, figliofk) VALUES (?, ?, ?); ";
	
	
	
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
		//String sqlGenitori = new String();
		
		
		System.out.println("genitori : " + Genitori.size());
		System.out.println("allergie : " + Allergie.size());
		
		
	/*	if(Genitori.size() == 2) {
			sqlGenitori = INSERT_Genitore + "\n " + INSERT_Genitore;
		}else sqlGenitori = INSERT_Genitore;*/
		
		for(int i = 0; i < Allergie.size(); i++) {
			sqlAllergie += INSERT_Allergia + " \n";
		}
		
		System.out.println(sqlAllergie);		
		
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
			PreparedStatement persona = c.prepareStatement(INSERT_Persona);
			PreparedStatement ragazzo = c.prepareStatement(INSERT_Ragazzo);
			PreparedStatement gen = c.prepareStatement(INSERT_Genitore);
			PreparedStatement all = c.prepareStatement(sqlAllergie);
			
			
			ragazzo.clearParameters();
			//gen.clearParameters();
			//all.clearParameters();
	
			//persona
			persona.setString(1, Utente.getCF());
			persona.setString(2, Utente.getNome());
			persona.setString(3, Utente.getCognome());
			persona.setString(4, Utente.getDdN());
			persona.setString(5, Utente.getEmail());
			//ragazzo
			ragazzo.setString(1, Utente.getHobby());		
			ragazzo.setString(2, Utente.getIndirizzo());
			ragazzo.setString(3, Utente.getPassword());
			ragazzo.setString(4, Utente.getNrTelefono());
			ragazzo.setString(5, Utente.getCF());
			
			System.out.println(persona);
			System.out.println(ragazzo);
			
			int x = 0;
			for(Genitore g: Genitori) {
				persona.clearParameters();
				//persona
				persona.setString(1, g.getCF());
				persona.setString(2, g.getNome());
				persona.setString(3, g.getCognome());
				persona.setString(4, g.getDdN());
				persona.setString(5, g.getEmail());
				//genitore
				gen.setString(1, g.getNrTelefono());
				gen.setString(2, g.getCF());
				gen.setString(3, Utente.getCF());
				
				System.out.println(persona);
				System.out.println(gen);
			}
			
			
			int y = 0;
			for(Allergene a: Allergie) {
				all.setString(y+1, a.getNome());
				all.setString(y+2, Utente.getCF());
				all.setString(y+3, a.getDescrizione());
				
				y = 3;
			}
			System.out.println(all);
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		
	}
}
