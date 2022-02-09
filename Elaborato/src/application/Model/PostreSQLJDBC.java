package application.Model;



import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;  

public class PostreSQLJDBC {

	private static final String DB_URL = "jdbc:postgresql://localhost:5432/db";
	private static final String DB_User = "postgres";
	private static final String DB_Password = "Ciao1107";
	private static  String QUERY_LoginU = "SELECT * FROM (Persona INNER JOIN Ragazzo ON Ragazzo.passwd = ? AND Persona.EMail = ?) WHERE Ragazzo.personafk = Persona.cf;;";
	private static  String QUERY_LoginR = "SELECT * FROM (Persona INNER JOIN Responsabile ON Responsabile.passwd = ? AND Persona.EMail = ?) WHERE Responsabile.personafk = Persona.cf;;";
	private static  String QUERY_GetMaxCollege = "SELECT MAX(codice) FROM College WHERE vacanzafk = ?";
	private static  String QUERY_GetMaxFamiglia = "SELECT MAX(codice) FROM Famiglia WHERE vacanzafk = ?";
	private static  String QUERY_GetMaxPrenotazioniCollege = "SELECT MAX(codice) FROM PrenotazioneCollege WHERE vacanzafk = ? AND collegefk = ?";
	private static  String QUERY_GetMaxPrenotazioniFamiglia = "SELECT MAX(codice) FROM PrenotazioneFamiglia WHERE vacanzafk = ? AND famfk = ?";
	private static  String QUERY_GetVacanzeData = "SELECT * FROM Vacanza ORDER BY datadipartenza";
	private static  String QUERY_GetVacanzeDurata = "SELECT * FROM Vacanza WHERE vacanza.durata = ? ORDER BY codice";
	private static  String QUERY_GetVacanzeCitta = "SELECT * FROM Vacanza WHERE vacanza.città = ? ORDER BY codice";
	private static  String QUERY_GetGita = "SELECT * FROM Gita WHERE Gita.vacanzafk = ? ";
	private static  String QUERY_GetCollege = "SELECT * FROM College WHERE College.vacanzafk = ? ";
	private static  String QUERY_GetAttivitaCollege = "SELECT * FROM Attivita WHERE Attivita.collegefk = ? ";
	private static  String QUERY_GetCapoFam = "SELECT * FROM (Persona INNER JOIN Famiglia On Persona.cf = Famiglia.famfk) WHERE Famiglia.vacanzafk = ? ";
	private static  String QUERY_GetPrenotazioneCollege = "SELECT * FROM (PrenotazioneCollege INNER JOIN Vacanza On PrenotazioneCollege.vacanzafk = Vacanza.codice AND PrenotazioneCollege.ragazzofk = ?);";
	private static  String QUERY_GetPrenotazioneFamiglia = "SELECT * FROM (PrenotazioneFamiglia INNER JOIN Vacanza On PrenotazioneFamiglia.vacanzafk = Vacanza.codice AND PrenotazioneFamiglia.ragazzofk = ?);";
	private static  String QUERY_GetVacanzePassate = "SELECT * FROM Vacanza ";
	private static  String QUERY_GetMediaVoti = "SELECT avg(voto) FROM Questionario WHERE vacanzafk = ?;";
	private static  String QUERY_GetCommenti = "SELECT commento FROM Questionario WHERE vacanzafk = ?;";
	private static  String QUERY_NQuestionari = "SELECT COUNT(prenotazionefk) FROM Questionario WHERE Questionario.prenotazionefk = ?;";
	private static  String IsPrenotata = "SELECT COUNT(*) FROM (PrenotazioneFamiglia INNER JOIN PrenotazioneCollege ON PrenotazioneFamiglia.ragazzofk = ? OR PrenotazioneCollege.ragazzofk  = ? ) WHERE PrenotazioneFamiglia.vacanzafk = ? OR PrenotazioneCollege.vacanzafk = ?;";
	
	
	
	//private static  String QUERY_GeFam = "SELECT * FROM Attivita WHERE Attivita.collegefk = ? ";


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
	private static  String INSERT_PrenotazioneCollege = "INSERT INTO PrenotazioneCollege (codice , tipostanza, pagamento, ragazzofk, vacanzafk, collegefk) VALUES (?, ?, ?, ?, ?, ?)";
	private static  String INSERT_PrenotazioneFamiglia = "INSERT INTO PrenotazioneFamiglia (nomeamico, emailamico, pagamento, ragazzofk, codice , vacanzafk, famfk) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static  String INSERT_Questionario = "INSERT INTO Questionario (prenotazionefk, voto, commento, vacanzafk) VALUES (?, ?, ?, ?)";
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


	// cerco il codice più alto della relativa tabella per poi poter gnerare il prossimo
	public static String getMaxCodicePrenotazione(String tabella, String vacanza, String x) throws SQLException {
		String codice = null;

		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
			PreparedStatement getmax;

			switch(tabella) {
			case "College":
				getmax = c.prepareStatement(QUERY_GetMaxPrenotazioniCollege);
				break;
			case "Famiglia":
				getmax = c.prepareStatement(QUERY_GetMaxPrenotazioniFamiglia);
				break;
			default:
				getmax = c.prepareStatement(QUERY_GetMaxPrenotazioniCollege);
			}


			getmax.clearParameters();

			//getmax.setString(1, tabella);
			getmax.setString(1, vacanza);
			getmax.setString(2, x);
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

			if(coll.executeUpdate() != 1) System.out.println("ERRORE INSERIMENTO COLLEGE" + college.getCodice());
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



			//vacanza.setString(1, data);
			Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(data);  
			System.out.println(vacanza);


			ResultSet elenco = vacanza.executeQuery();



			//salvo I dati del Ragazzo se le credenziali inserite sono di un ragazzo
			while(elenco.next()) {
				Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(elenco.getString("datadipartenza")); 
				
				if(date1.after(date2) || date1.equals(date2)) {

					x = new Vacanza(elenco.getString("codice"), elenco.getString("città"), elenco.getString("datadipartenza"), elenco.getString("durata"),  elenco.getString("lingua"));
					System.out.println(x);
					vac.add(x);
					x = null;
				}

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

	//liste attivita dei college per vacanza
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

	//liste famiglie e capofam per vacanza
	public static void getFamigliaVacanza(String codice, ArrayList<CapoFamiglia> capofam, ArrayList<Famiglia> fam) throws SQLException{

		CapoFamiglia capo;
		Famiglia f;

		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);

			PreparedStatement datifam = c.prepareStatement(QUERY_GetCapoFam);



			datifam.setString(1, codice);

			System.out.println(datifam);


			ResultSet elenco = datifam.executeQuery();



			//salvo I dati dele attività se presenti
			while(elenco.next()) {
				capo = new CapoFamiglia( elenco.getString("nome"), elenco.getString("cognome"), elenco.getString("cf"),  elenco.getString("email"), elenco.getString("datadinascita"));
				f = new Famiglia( elenco.getString("cf"),  elenco.getString("vacanzafk"),  elenco.getString("nrcomponenti"),  elenco.getString("nrcamere"),  elenco.getString("nrbagni"),  elenco.getBoolean("animali"),  elenco.getString("distanza"), true, elenco.getString("codice"));

				//System.out.println(g);
				capofam.add(capo);
				fam.add(f);


				capo = null;
				f = null;

			}

			elenco.close();
			datifam.close();
			c.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");

	}

	//inserimento prenotazione college
	public static void addPrenotazioneCollege(PrenotazioneCollege prenotazione) throws SQLException{
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
			PreparedStatement pren = c.prepareStatement(INSERT_PrenotazioneCollege);

			pren.clearParameters();

			pren.setString(1, prenotazione.getCodice());
			pren.setString(2, prenotazione.getStanza());
			pren.setString(3, prenotazione.getMdP());
			pren.setString(4, prenotazione.getPersona());
			pren.setString(5, prenotazione.getVacanza());
			pren.setString(6, prenotazione.getCollege());


			System.out.println(pren);

			if(pren.executeUpdate() != 1) System.out.println("ERRORE INSERIMENTO PRENOTAZIONE COLLEGE" + prenotazione.getCodice());
			pren.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}System.out.println("Opened database successfully");
	}

	//inserimento prenotazione famiglia
	public static void addPrenotazioneFamiglia(PrenotazioneFam prenotazione) throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
			PreparedStatement pren = c.prepareStatement(INSERT_PrenotazioneFamiglia);

			pren.clearParameters();

			pren.setString(1, prenotazione.getNomeAmico());
			pren.setString(2, prenotazione.getEmailAmico());
			pren.setString(3, prenotazione.getMdP());
			pren.setString(4, prenotazione.getPersona());
			pren.setString(5, prenotazione.getCodice());
			pren.setString(6, prenotazione.getVacanza());
			pren.setString(7, prenotazione.getFamiglia());


			System.out.println(pren);

			if(pren.executeUpdate() != 1) System.out.println("ERRORE INSERIMENTO PRENOTAZIONE FAMIGLIA" + prenotazione.getCodice());
			pren.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}System.out.println("Opened database successfully");

	}

	//metodo che trova le proprie prenotazioni passate 
	public static void getPrenotazioniPassate(String user, ArrayList<Vacanza> vacanze,  ArrayList<PrenotazioneCollege> prenotazioniCollege,  ArrayList<PrenotazioneFam> prenotazioniFam )  throws SQLException{

		PrenotazioneCollege coll;
		PrenotazioneFam fam;
		Vacanza vac;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
		LocalDateTime now = LocalDateTime.now();  
		String data = dtf.format(now);
		

		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);

			PreparedStatement prencollege = c.prepareStatement(QUERY_GetPrenotazioneCollege);
			PreparedStatement prenFam = c.prepareStatement(QUERY_GetPrenotazioneFamiglia);



			//prenotazioni college
			prencollege.setString(1, user);
			//prencollege.setString(2, data);

			//prenotazioni Famiglie
			prenFam.setString(1, user);
			//prenFam.setString(2, data);

			System.out.println(prencollege);


			ResultSet elencoCollege = prencollege.executeQuery();
			ResultSet elencoFamiglia = prenFam.executeQuery();

			Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(data);  

			//salvo I dati di collegge e vacanze relative alle prenotazioni
			while(elencoCollege.next()) {
				Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(elencoCollege.getString("datadipartenza")); 
				
				if(date1.before(date2)) {
					coll = new PrenotazioneCollege( elencoCollege.getString("vacanzafk"), elencoCollege.getString("ragazzofk"), elencoCollege.getString("collegefk"),  elencoCollege.getString("tipostanza"), elencoCollege.getString("pagamento"), true, elencoCollege.getString("codice"));
					vac = new Vacanza(elencoCollege.getString("vacanzafk"), elencoCollege.getString("città"), elencoCollege.getString("datadipartenza"), elencoCollege.getString("durata"),  elencoCollege.getString("lingua"));
				
	
					prenotazioniCollege.add(coll);
					vacanze.add(vac);
	
					coll = null;
					vac = null;
				}
			}
			//salvo I dati di famiglia e vacanze relative alle prenotazioni
			while(elencoFamiglia.next()) {
				Date date3 = new SimpleDateFormat("dd-MM-yyyy").parse(elencoFamiglia.getString("datadipartenza")); 
				
				if(date3.before(date2)) {
					fam = new PrenotazioneFam( elencoFamiglia.getString("vacanzafk"), elencoFamiglia.getString("ragazzofk"), elencoFamiglia.getString("famfk"),  elencoFamiglia.getString("nomeamico"), elencoFamiglia.getString("emailamico"), elencoFamiglia.getString("pagamento"), true, elencoFamiglia.getString("codice"));
					vac = new Vacanza(elencoFamiglia.getString("vacanzafk"), elencoFamiglia.getString("città"), elencoFamiglia.getString("datadipartenza"), elencoFamiglia.getString("durata"),  elencoFamiglia.getString("lingua"));


					prenotazioniFam.add(fam);
					vacanze.add(vac);

					fam = null;
					vac = null;
				}
			}


			elencoCollege.close();
			elencoFamiglia.close();
			prencollege.close();
			prenFam.close();
			c.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");



	}

	public static void addQuestionario(Questionario questionario) throws SQLException{
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
			PreparedStatement q = c.prepareStatement(INSERT_Questionario);

			q.clearParameters();

			q.setString(1, questionario.getPrenotazione());
			q.setInt(2, questionario.getVoto());
			q.setString(3, questionario.getCommento());
			q.setString(4, questionario.getVacanza());


			System.out.println(q);

			if(q.executeUpdate() != 1) System.out.println("ERRORE INSERIMENTO QUESTIONARIO" + questionario.getPrenotazione());
			q.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}System.out.println("Opened database successfully");
	}


	//metodo che restituisce le vacanze passate/in corso
	public static void getVacanzePassate( ArrayList<Vacanza> vacanze)  throws SQLException{

		
		Vacanza v;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
		LocalDateTime now = LocalDateTime.now();  
		String data = dtf.format(now);

		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);

			PreparedStatement vac = c.prepareStatement(QUERY_GetVacanzePassate);
			




			System.out.println(vac);


			ResultSet elenco = vac.executeQuery();



			//salvo I dati di collegge e vacanze relative alle prenotazioni
			while(elenco.next()) {
				Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(elenco.getString("datadipartenza")); 
				Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(data);  
				if(date1.before(date2)) {
					v = new Vacanza(elenco.getString("codice"), elenco.getString("città"), elenco.getString("datadipartenza"), elenco.getString("durata"),  elenco.getString("lingua"));
					vacanze.add(v);
				}
				

				v = null;
			}
		
			vac.close();
			c.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");

	}

	//metodo che restituisce le vacanze passate/in corso
	public static float getVotoMedio(String vacanza)  throws SQLException{

		float media = 0;
		

		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);

			PreparedStatement voto = c.prepareStatement(QUERY_GetMediaVoti);



			voto.setString(1, vacanza);

			System.out.println(voto);


			ResultSet elenco = voto.executeQuery();



			//salvo I dati di collegge e vacanze relative alle prenotazioni
			while(elenco.next()) {
				media = elenco.getFloat("avg");

			}

			voto.close();
			c.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		
		
		return media;
	}


	public static String getCommenti(String codice) {
		
		String s = "";
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);

			PreparedStatement commenti = c.prepareStatement(QUERY_GetCommenti);



			commenti.setString(1, codice);

			System.out.println(commenti);


			ResultSet elenco = commenti.executeQuery();



			//salvo i commenti
			while(elenco.next()) {
				s += "\n 		- " + elenco.getString("commento");

			}

			commenti.close();
			c.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		
		
	
		return s;
	}
	
	
	//metodo che interroga il db e dice se è già stato compilato un questionario per la vacanza in questione
	public static boolean isCompilato(String codice) {

		boolean flag = false;

		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);

			PreparedStatement q = c.prepareStatement(QUERY_NQuestionari);



			q.setString(1, codice);

			System.out.println(q);


			ResultSet elenco = q.executeQuery();



		
			while(elenco.next()) {
				if(elenco.getInt("count") > 0) flag = true;

			}

			q.close();
			c.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");



		return flag;
	}
	

	//metodo che interroga il db e dice se è già stata fatta una prenotazione per la vacanza in questione
	public static boolean isPrenotata(String user, String vacanza) {

		boolean flag = false;

		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DB_URL, DB_User, DB_Password);

			PreparedStatement q = c.prepareStatement(IsPrenotata);



			q.setString(1, user);
			q.setString(2, user);
			q.setString(3, vacanza);
			q.setString(4, vacanza);

			System.out.println(q);


			ResultSet elenco = q.executeQuery();



		
			while(elenco.next()) {
				if(elenco.getInt("count") > 0) flag = true;

			}

			q.close();
			c.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");



		return flag;
	}



}