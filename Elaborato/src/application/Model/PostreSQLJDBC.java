package application.Model;


import java.sql.*;
import java.util.ArrayList;

public class PostreSQLJDBC {

	private static final String DB_URL = "jdbc:postgresql://localhost:5432/db";
	private static final String DB_User = "postgres";
	private static final String DB_Password = "Ciao1107";
	private static  String QUERY_LoginU = "SELECT * FROM (Persona INNER JOIN Ragazzo ON Ragazzo.passwd = ? AND Persona.EMail = ?);";
	private static  String QUERY_LoginR = "SELECT * FROM (Persona INNER JOIN Responsabile ON Responsabile.passwd = ? AND Persona.EMail = ?);";
	private static  String QUERY_GetMaxCollege = "SELECT MAX(codice) FROM College WHERE vacanzafk = ?";
	private static  String QUERY_GetMaxFamiglia = "SELECT MAX(codice) FROM Famiglia WHERE vacanzafk = ?";
	private static  String QUERY_GetVacanzeData = "SELECT * FROM Vacanza WHERE vacanza.datadipartenza >= ? ORDER BY codice";
	private static  String QUERY_GetVacanzeDurata = "SELECT * FROM Vacanza WHERE vacanza.durata = ? ORDER BY codice";
	private static  String QUERY_GetVacanzeCitta = "SELECT * FROM Vacanza WHERE vacanza.città = ? ORDER BY codice";
	private static  String QUERY_GetGita = "SELECT * FROM Gita WHERE Gita.vacanzafk = ? ";
	private static  String QUERY_GetCollege = "SELECT * FROM College WHERE College.vacanzafk = ? ";
	private static  String QUERY_GetAttivitaCollege = "SELECT * FROM Attivita WHERE Attivita.collegefk = ? ";
	private static  String QUERY_GetCapoFam = "SELECT * \r\n"
			+ "FROM ((Persona INNER JOIN CapoFamiglia ON CapoFamiglia.personafk = Persona.cf) \r\n"
			+ "	  	INNER JOIN Famiglia On CapoFamiglia.personafk = Famiglia.famfk)\r\n"
			+ "WHERE Famiglia.vacanzafk = '0002'\r\n"
			+ " ";
	private static  String QUERY_GeFam = "SELECT * FROM Attivita WHERE Attivita.collegefk = ? ";
	
	
	private static  String INSERT_Persona = "INSERT INTO Persona (Cf, Nome, Cognome, DataDiNascita, EMail) VALUES (?, ?, ?, ?, ?);";
	private static  String INSERT_Ragazzo = "INSERT INTO Ragazzo (Hobby, Indirizzo, passwd, nrtelefono, personafk) VALUES (?, ?, ?, ?, ?); ";
	private static  String INSERT_Allergia = "INSERT INTO Allergia (Nome, Ragazzofk, Precauzioni) VALUES (?, ?, ?); ";
	private static  String INSERT_Genitore = "INSERT INTO Genitore (nrtelefono, personafk, figliofk) VALUES (?, ?, ?); ";
	private static  String INSERT_Vacanza = "INSERT INTO Vacanza (codice, città, datadipartenza, durata, lingua) VALUES (?, ?, ?, ?, ?);";
	private static  String INSERT_College =	"INSERT INTO College (codice, nome, nrstanze, indirizzo, vacanzafk) VALUES (?, ?, ?, ?, ?);";
	private static  String INSERT_Gita = "INSERT INTO Gita (destinazione, costo, durata, descrizione, vacanzafk) VALUES (?, ?, ?, ?, ?);";
	private static  String INSERT_Attivita = "INSERT INTO Attivita (nome, descrizione, collegefk) VALUES (?, ?, ?);";
	private static  String INSERT_Famiglia = "INSERT INTO Famiglia (codice, nrcomponenti, animali, nrcamere, nrbagni, distanza, famfk, vacanzafk) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	private static  String INSERT_CapoFamiglia = "INSERT INTO CapoFamiglia (personafk) VALUES (?);";
	private static  String UPDATE_Persona = "UPDATE Persona SET Nome = ?, Cognome = ?, datadinascita = ?, Email = ? WHERE cf = ?;";
	private static  String UPDATE_Ragazzo = "UPDATE Ragazzo SET Indirizzo = ?, nrtelefono = ? WHERE personafk = ?;";
	
	
	// cerco il codice più alto della relativa tabella per poi poter gnerare il prossimo
	public static String getMaxCodice(String tabella, String vacanza) throws SQLException {
		String codice = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
			PreparedStatement getmax;
			
			switch(tabella) {
			case "College":
				getmax = c.prepareStatement(QUERY_GetMaxCollege);
			break;
			case "Famiglia":
				getmax = c.prepareStatement(QUERY_GetMaxFamiglia);
			break;
			default:
				getmax = c.prepareStatement(QUERY_GetMaxCollege);
			}
				
			
			getmax.clearParameters();
			
			//getmax.setString(1, tabella);
			getmax.setString(1, vacanza);
			System.out.println(getmax);

			ResultSet max = getmax.executeQuery();
			
			while(max.next()) {
				codice = max.getString("max");
				System.out.println(codice);
			}
			max.close();
			getmax.close();
			c.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
			
		}
		if(codice == null) codice = "0000";
		return codice;
	}
	
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
				
				
				
				//System.out.println(user.getString("Cf"));
				
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

	public static void addVacanza(Vacanza vacanza) throws SQLException{
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
			PreparedStatement vac = c.prepareStatement(INSERT_Vacanza);
			
			vac.clearParameters();
			
			vac.setString(1, vacanza.getCodice());
			vac.setString(2, vacanza.getCitta());
			vac.setString(3, vacanza.getDataPartenza());
			vac.setString(4, vacanza.getDurata());
			vac.setString(5, vacanza.getLingua());
			
			
			System.out.println(vac);
			
			if(vac.executeUpdate() != 1) System.out.println("ERRORE INSERIMENTO GENITORE" + vacanza.getCodice());
			vac.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}System.out.println("Opened database successfully");
	}

	public static void addCollege(College college) throws SQLException{
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
			PreparedStatement coll = c.prepareStatement(INSERT_College);
			
			coll.clearParameters();
			
			coll.setString(1, college.getCodice());
			coll.setString(2, college.getNome());
			coll.setString(3, college.getNrStanze());
			coll.setString(4, college.getIndirizzo());
			coll.setString(5, college.getVacanza());
			
			
			System.out.println(coll);
			
			if(coll.executeUpdate() != 1) System.out.println("ERRORE INSERIMENTO GENITORE" + college.getCodice());
			coll.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}System.out.println("Opened database successfully");
	}
	
	public static void addGita(Gita gita) throws SQLException{
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
			PreparedStatement g = c.prepareStatement(INSERT_Gita);
			
			g.clearParameters();
			
			g.setString(1, gita.getDestinazione());
			g.setString(2, gita.getCosto());
			g.setString(3, gita.getOre());
			g.setString(4, gita.getDescrizione());
			g.setString(5, gita.getVacanza());
			
			
			System.out.println(g);
			
			if(g.executeUpdate() != 1) System.out.println("ERRORE INSERIMENTO GITA" + gita.getDestinazione());
			g.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}System.out.println("Opened database successfully");
	}
	
	public static void addAttivita(Attivita attivita) throws SQLException{
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
			PreparedStatement att = c.prepareStatement(INSERT_Attivita);
			
			att.clearParameters();
			
			att.setString(1, attivita.getNome());
			att.setString(2, attivita.getDescrizione());
			att.setString(3, attivita.getCollege());

			
			System.out.println(att);
			
			if(att.executeUpdate() != 1) System.out.println("ERRORE INSERIMENTO ATTIVITA" + attivita.getNome());
			att.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}System.out.println("Opened database successfully");
	}
	
	public static void addFamiglia(CapoFamiglia capo, Famiglia famiglia) throws SQLException{
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
			PreparedStatement persona = c.prepareStatement(INSERT_Persona);
			PreparedStatement capofam = c.prepareStatement(INSERT_CapoFamiglia);
			PreparedStatement fam = c.prepareStatement(INSERT_Famiglia);
			
			persona.clearParameters();
			capofam.clearParameters();
			fam.clearParameters();
			
			//persona
			persona.setString(1, capo.getCF());
			persona.setString(2, capo.getNome());
			persona.setString(3, capo.getCognome());
			persona.setString(4, capo.getDdN());
			persona.setString(5, capo.getEmail());
			
			capofam.setString(1, capo.getCF());
			
			fam.setString(1, famiglia.getCodice());
			fam.setString(2, famiglia.getNrComponenti());
			fam.setBoolean(3, famiglia.isAnimali());
			fam.setString(4, famiglia.getNrCamere());
			fam.setString(5, famiglia.getNrBagni());
			fam.setString(6, famiglia.getDistanza());
			fam.setString(7, famiglia.getCapoFamiglia());
			fam.setString(8, famiglia.getVacanza());
			
			
			System.out.println(persona);
			System.out.println(capofam);
			System.out.println(fam);
			
			if(persona.executeUpdate() != 1) System.out.println("ERRORE INSERIMENTO PERSONA" + capo.getCF());
			if(capofam.executeUpdate() != 1) System.out.println("ERRORE INSERIMENTO CAPO FAMIGLIA" + capo.getCF());
			if(fam.executeUpdate() != 1) System.out.println("ERRORE INSERIMENTO FAMIGLIA" + famiglia.getCodice());
			persona.close();
			capofam.close();
			fam.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}System.out.println("Opened database successfully");
	}

	//Modifica dei dati dell'utente
	public static void UpdateUser(Ragazzo old, Ragazzo mod) throws SQLException{
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
			PreparedStatement persona = c.prepareStatement(UPDATE_Persona);
			PreparedStatement ragazzo = c.prepareStatement(UPDATE_Ragazzo);
			
			persona.clearParameters();
			ragazzo.clearParameters();
			
			//persona
			persona.setString(1, mod.getNome());
			persona.setString(2, mod.getCognome());
			persona.setString(3, mod.getDdN());
			persona.setString(4, mod.getEmail());
			persona.setString(5, old.getCF());
			
			//RAGAZZO
			ragazzo.setString(1, mod.getIndirizzo());
			ragazzo.setString(2, mod.getNrTelefono());
			ragazzo.setString(3, old.getCF());
			
			System.out.println(persona);
			System.out.println(ragazzo);
			
			if(persona.executeUpdate() != 1) System.out.println("ERRORE UPDATE PERSONA" + old.getCF());
			if(ragazzo.executeUpdate() != 1) System.out.println("ERRORE UPDATE CAPO FAMIGLIA" + old.getCF());
			persona.close();
			ragazzo.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}System.out.println("Opened database successfully");

	}
	
	
	//Lista vacanze future
	public static ArrayList<Vacanza> getVacanzaData(String data) throws SQLException{
		ArrayList<Vacanza> vac = new ArrayList<Vacanza>();
		Vacanza x;
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);

			PreparedStatement vacanza = c.prepareStatement(QUERY_GetVacanzeData);



			vacanza.setString(1, data);
			
			System.out.println(vacanza);


			ResultSet elenco = vacanza.executeQuery();



			//salvo I dati del Ragazzo se le credenziali inserite sono di un ragazzo
			while(elenco.next()) {
				x = new Vacanza(elenco.getString("codice"), elenco.getString("città"), elenco.getString("datadipartenza"), elenco.getString("durata"),  elenco.getString("lingua"));
				System.out.println(x);
				vac.add(x);
				x = null;
				
				
			}
			
			elenco.close();
			vacanza.close();
			c.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		
		return vac;
		
	}

	public static ArrayList<Vacanza> getVacanzaDurata(String durata) throws SQLException{
		ArrayList<Vacanza> vac = new ArrayList<Vacanza>();
		Vacanza x;
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);

			PreparedStatement vacanza = c.prepareStatement(QUERY_GetVacanzeDurata);



			vacanza.setString(1, durata);
			
			System.out.println(vacanza);


			ResultSet elenco = vacanza.executeQuery();



			//salvo I dati del Ragazzo se le credenziali inserite sono di un ragazzo
			while(elenco.next()) {
				x = new Vacanza(elenco.getString("codice"), elenco.getString("città"), elenco.getString("datadipartenza"), elenco.getString("durata"),  elenco.getString("lingua"));
				System.out.println(x);
				vac.add(x);
				x = null;
				
				
			}
			
			elenco.close();
			vacanza.close();
			c.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		
		return vac;
		
	}
	
	public static ArrayList<Vacanza> getVacanzaCitta(String citta) throws SQLException{
		ArrayList<Vacanza> vac = new ArrayList<Vacanza>();
		Vacanza x;
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);

			PreparedStatement vacanza = c.prepareStatement(QUERY_GetVacanzeCitta);



			vacanza.setString(1, citta);
			
			System.out.println(vacanza);


			ResultSet elenco = vacanza.executeQuery();



			//salvo I dati del Ragazzo se le credenziali inserite sono di un ragazzo
			while(elenco.next()) {
				x = new Vacanza(elenco.getString("codice"), elenco.getString("città"), elenco.getString("datadipartenza"), elenco.getString("durata"),  elenco.getString("lingua"));
				System.out.println(x);
				vac.add(x);
				x = null;
				
				
			}
			
			elenco.close();
			vacanza.close();
			c.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		
		return vac;
		
	}

	//liste Gite per vacanza
	public static ArrayList<Gita> getGitaVacanza(String codice) throws SQLException{
		ArrayList<Gita> gite = new ArrayList<Gita>();
		Gita g;
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);

			PreparedStatement vacanzagita = c.prepareStatement(QUERY_GetGita);



			vacanzagita.setString(1, codice);
			
			System.out.println(vacanzagita);


			ResultSet elenco = vacanzagita.executeQuery();



			//salvo I dati delle gite se presenti
			while(elenco.next()) {
				g = new Gita( elenco.getString("vacanzafk"), elenco.getString("destinazione"), elenco.getString("costo"), elenco.getString("durata"), elenco.getString("descrizione") );
				//System.out.println(g);
				gite.add(g);
				g = null;
				
				
			}
			
			elenco.close();
			vacanzagita.close();
			c.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		
		return gite;
		
	}
	
	//liste college per vacanza
	public static ArrayList<College> getCollegeVacanza(String codice) throws SQLException{
		ArrayList<College> college = new ArrayList<College>();
		College coll;
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);

			PreparedStatement vacanzacollege = c.prepareStatement(QUERY_GetCollege);



			vacanzacollege.setString(1, codice);
			
			System.out.println(vacanzacollege);


			ResultSet elenco = vacanzacollege.executeQuery();



			//salvo I dati del Ragazzo se le credenziali inserite sono di un ragazzo
			while(elenco.next()) {
				coll = new College( elenco.getString("nome"), elenco.getString("indirizzo"), elenco.getString("vacanzafk"), elenco.getString("nrstanze"), true, elenco.getString("codice"));
				//System.out.println(g);
				college.add(coll);
				coll = null;
				
				
			}
			
			elenco.close();
			vacanzacollege.close();
			c.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		
		return college;
	}

	public static ArrayList<Attivita> getAttivitaCollegeVacanza(String codice) throws SQLException{
		ArrayList<Attivita> attivita = new ArrayList<Attivita>();
		Attivita a;
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);

			PreparedStatement attivitaCollege = c.prepareStatement(QUERY_GetAttivitaCollege);



			attivitaCollege.setString(1, codice);
			
			System.out.println(attivitaCollege);


			ResultSet elenco = attivitaCollege.executeQuery();



			//salvo I dati dele attività se presenti
			while(elenco.next()) {
				a = new Attivita( elenco.getString("nome"), elenco.getString("descrizione"), elenco.getString("collegefk"));
				//System.out.println(g);
				attivita.add(a);
				a = null;
				
				
			}
			
			elenco.close();
			attivitaCollege.close();
			c.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		
		return attivita;
	}

	public static ArrayList<Attivita> getFamigliaVacanza(String codice) throws SQLException{
		ArrayList<Attivita> attivita = new ArrayList<Attivita>();
		Attivita a;
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);

			PreparedStatement attivitaCollege = c.prepareStatement(QUERY_GetAttivitaCollege);



			attivitaCollege.setString(1, codice);
			
			System.out.println(attivitaCollege);


			ResultSet elenco = attivitaCollege.executeQuery();



			//salvo I dati dele attività se presenti
			while(elenco.next()) {
				a = new Attivita( elenco.getString("nome"), elenco.getString("descrizione"), elenco.getString("collegefk"));
				//System.out.println(g);
				attivita.add(a);
				a = null;
				
				
			}
			
			elenco.close();
			attivitaCollege.close();
			c.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		
		return attivita;
	}

	
}