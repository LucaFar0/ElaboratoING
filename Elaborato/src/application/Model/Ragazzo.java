package application.Model;

import java.time.LocalDate;
import java.util.Date;

public class Ragazzo extends Persona {
	
	private String Hobby;	
	private String password = new String();
	private String  NrTelefono;
	private String Indirizzo = new String();
	
	
	public Ragazzo(String nome, String cognome, String CF, String Email, String DdN, String nrTelefono, String hobby, String password, String Indirizzo) {
		super(nome, cognome, CF, Email, DdN);

		this.setHobby(hobby);
		this.setIndirizzo(Indirizzo);
		this.setPassword(password);
		this.setNrTelefono(nrTelefono);
	
		
	}

 
	public String getIndirizzo() {
		return this.Indirizzo;
	}


	public void setIndirizzo(String indirizzo) {
		this.Indirizzo = indirizzo;
	}


	public String getNrTelefono() {
		return this.NrTelefono;
	}


	public void setNrTelefono(String nrTelefono) {
		this.NrTelefono = nrTelefono;
	}


	public String getPassword() {
		return this.password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getHobby() {
		return this.Hobby;
	}


	public void setHobby(String hobby) {
		this.Hobby = hobby;
	}


	
	public String toString() {
		String p = super.toString() + " | " + this.getNrTelefono() + " | " + this.getIndirizzo() + " | " + this.getHobby() ;
		return p;
	}

	

}
