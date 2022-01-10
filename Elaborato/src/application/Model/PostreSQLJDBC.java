package application.Model;

import java.sql.*;

public class PostreSQLJDBC {
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/db";
	private static final String DB_User = "postgres";
	private static final String DB_Password = "Ciao1107";
	private static final String QUERY_LoginU = "SELECT * FROM join(Persona Ragazzo) WHERE email= ? and password = ?";

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
}
