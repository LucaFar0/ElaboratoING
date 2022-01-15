package application.Model;

import java.util.Date;

public class Responsabile extends Persona {
	
	private long  NrTelefono;
	private String password = new String();
	
	public Responsabile(String nome, String cognome, String CF, String Email, String DdN, long  NrTelefono, String password ) {
		super(nome, cognome, CF, Email, DdN);
		this.setPassword(password);
		this.setNrTelefono(NrTelefono);
	}
	

	public long getNrTelefono() {
		return this.NrTelefono;
	}


	public void setNrTelefono(long nrTelefono) {
		this.NrTelefono = nrTelefono;
	}


	public String getPassword() {
		return this.password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


}
