package application.Model;

import java.util.Date;

public class Persona {
	private String Nome = new String();
	private String Cognome = new String();
	private String CF = new String();
	private Date DdN = new Date();
	private String Email = new String();

	
	public String getNome() {
		return this.Nome;
	}
	public void setNome(String nome) {
		this.Nome = nome;
	}
	public String getCognome() {
		return this.Cognome;
	}
	public void setCognome(String cognome) {
		this.Cognome = cognome;
	}
	public String getCF() {
		return this.CF;
	}
	public void setCF(String cF) {
		this.CF = cF;
	}
	public Date getDdN() {
		return this.DdN;
	}
	public void setDdN(Date ddN) {
		this.DdN = ddN;
	}
	public String getEmail() {
		return this.Email;
	}
	public void setEmail(String email) {
		this.Email = email;
	}
	
	public Persona(String nome, String cognome, String CF, String Email, Date DdN) {
		setNome(nome);
		setCognome(cognome);
		setCF(CF);
		setEmail(Email);
		setDdN(DdN);
	}
	
}
