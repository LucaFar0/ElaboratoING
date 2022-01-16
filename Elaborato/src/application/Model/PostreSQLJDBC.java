package application.Model;


import java.sql.*;
import java.util.ArrayList;

public class PostreSQLJDBC {

	private static final String DB_URL = "jdbc:postgresql://localhost:5432/db";
	private static final String DB_User = "postgres";
	private static final String DB_Password = "Ciao1107";
	private static  String QUERY_LoginU = "SELECT * FROM (Persona INNER JOIN Ragazzo ON Ragazzo.passwd = ? AND Persona.EMail = ?);";
	private static  String QUERY_LoginR = "SELECT * FROM (Persona INNER JOIN Responsabile ON Responsabile.passwd = ? AND Persona.EMail = ?);";
	private static  String INSERT_Persona = "INSERT INTO Persona (Cf, Nome, Cognome, DataDiNascita, EMail) VALUES (?, ?, ?, ?, ?);";
	private static  String INSERT_Ragazzo = "INSERT INTO Ragazzo (Hobby, Indirizzo, passwd, nrtelefono, personafk) VALUES (?, ?, ?, ?, ?); ";
	private static  String INSERT_Allergia = "INSERT INTO Allergia (Nome, Ragazzofk, Precauzioni) VALUES (?, ?, ?); ";
	private static  String INSERT_Genitore = "INSERT INTO Genitore (nrtelefono, personafk, figliofk) VALUES (?, ?, ?); ";



	public static boolean ValidateUser(String emailId, String password, Ragazzo rag) throws SQLException {

		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);

			PreparedStatement loginRagazzo = c.prepareStatement(QUERY_LoginU);



			loginRagazzo.setString(1, password);
			loginRagazzo.setString(2, emailId);
			
			System.out.println(loginRagazzo);


			ResultSet user = loginRagazzo.executeQuery();



			//salvo I dati del Ragazzo se le credenziali inserite sono di un ragazzo
			while(user.next()) {
				rag.setCF(user.getString("Cf"));
				rag.setNome(user.getString("Nome"));
				rag.setCognome(user.getString("Cognome"));
				rag.setDdN(user.getString("datadinascita"));
				rag.setEmail(user.getString("EMail"));
				rag.setIndirizzo(user.getString("Indirizzo"));
				rag.setNrTelefono(user.getString("nrtelefono"));
				rag.setPassword(password);
				rag.setHobby(user.getString("Hobby"));

			}
			user.close();
			loginRagazzo.close();
			c.close();
			if(rag.getCF() == null) {
				//credenziali sbagliate
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");

		return true;

	}

	public static boolean ValidateResp(String emailId, String password, Responsabile resp) throws SQLException {

		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);


			PreparedStatement loginResponsabile = c.prepareStatement(QUERY_LoginR);



			loginResponsabile.setString(1, password);
			loginResponsabile.setString(2, emailId);

			System.out.println(loginResponsabile);

			
			// altrimenti controllo se le creedenziali  sono di un responsabile
			ResultSet responsablie = loginResponsabile.executeQuery();

			//salvo I dati del Responsabile se le credenziali inserite sono di un responsabile
			while(responsablie.next()) {
				resp.setCF(responsablie.getString("Cf"));
				resp.setNome(responsablie.getString("Nome"));
				resp.setCognome(responsablie.getString("Cognome"));
				resp.setDdN(responsablie.getString("datadinascita"));
				resp.setEmail(responsablie.getString("EMail"));
				resp.setNrTelefono(responsablie.getString("nrtelefono"));
				resp.setPassword(password);
			}
			responsablie.close();
			loginResponsabile.close();
			c.close();
			if(resp.getCF() == null) {
				//credenziali sbagliate
				return false;
			}


		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");

		return true;

	}






	public static void Registrazione(ArrayList<Allergene> Allergie, ArrayList<Genitore> Genitori, Ragazzo Utente ) throws SQLException{	

		System.out.println("genitori : " + Genitori.size());
		System.out.println("allergie : " + Allergie.size());



		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
			PreparedStatement persona = c.prepareStatement(INSERT_Persona);
			PreparedStatement ragazzo = c.prepareStatement(INSERT_Ragazzo);
			PreparedStatement gen = c.prepareStatement(INSERT_Genitore);
			PreparedStatement all = c.prepareStatement(INSERT_Allergia);


			//------------------------------------Ragazzo----------------------------
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

			//esecuzione query
			if(persona.executeUpdate() != 1) System.out.println("ERRORE INSERIMENTO PERSONA" + Utente.getCF());
			if(ragazzo.executeUpdate() != 1) System.out.println("ERRORE INSERIMENTO RAGAZZO" + Utente.getCF());


			//------------------------------------Genitori----------------------------
			for(Genitore g: Genitori) {
				persona.clearParameters();
				gen.clearParameters();
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

				//esecuzione query
				if(persona.executeUpdate() != 1) System.out.println("ERRORE INSERIMENTO PERSONA" + g.getCF());
				if(gen.executeUpdate() != 1) System.out.println("ERRORE INSERIMENTO GENITORE" + g.getCF());
			}


			//------------------------------------Allergie----------------------------
			for(Allergene a: Allergie) {
				all.clearParameters();

				all.setString(1, a.getNome());
				all.setString(2, Utente.getCF());
				all.setString(3, a.getDescrizione());

				System.out.println(all);

				//esecuzione query
				if(all.executeUpdate() != 1) System.out.println("ERRORE INSERIMENTO ALEERGIA" + a.getNome());
			}
			persona.close();
			ragazzo.close();
			gen.close();
			all.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");

	}
}
