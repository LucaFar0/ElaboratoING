package application.Model;

import java.time.LocalDate;
import java.util.Date;

public class Genitore extends Persona {
	
	private String  NrTelefono;

	
	public Genitore(String nome, String cognome, String CF, String Email, String DdN,  String  NrTelefono) {
		super(nome, cognome, CF, Email, DdN);
		this.setNrTelefono(NrTelefono);
	}


	public String getNrTelefono() {
		return NrTelefono;
	}


	public void setNrTelefono(String nrTelefono) {
		NrTelefono = nrTelefono;
	}
	
	public String toString() {
		String p = super.toString() + " | " + this.getNrTelefono();
		return p;
	}

}
