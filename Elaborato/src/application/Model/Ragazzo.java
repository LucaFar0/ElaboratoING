package application.Model;

import java.util.Date;

public class Ragazzo extends Persona {
	
	private String[] Hobby;	
	private String password = new String();
	private long  NrTelefono;
	private String Indirizzo = new String();
	private Allergene[] allergie;
	
	public Ragazzo(String nome, String cognome, String CF, String Email, Date DdN, long nrTelefono, String[] hobby, String password, String Indirizzo, Allergene[] allergie) {
		super(nome, cognome, CF, Email, DdN);

		this.setHobby(hobby);
		this.setIndirizzo(Indirizzo);
		this.setPassword(password);
		this.setNrTelefono(nrTelefono);
		this.setAllergie(allergie);
		
	}

 
	public String getIndirizzo() {
		return this.Indirizzo;
	}


	public void setIndirizzo(String indirizzo) {
		this.Indirizzo = indirizzo;
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


	public String[] getHobby() {
		return this.Hobby;
	}


	public void setHobby(String[] hobby) {
		this.Hobby = hobby;
	}


	public Allergene[] getAllergie() {
		return allergie;
	}


	public void setAllergie(Allergene[] allergie) {
		this.allergie = allergie;
	}


	

}
