package application.Model;

import java.util.Date;

public class Genitore extends Persona {
	
	private long  NrTelefono;

	
	public Genitore(String nome, String cognome, String CF, String Email, Date DdN,  long  NrTelefono) {
		super(nome, cognome, CF, Email, DdN);
		this.setNrTelefono(NrTelefono);
	}


	public long getNrTelefono() {
		return NrTelefono;
	}


	public void setNrTelefono(long nrTelefono) {
		NrTelefono = nrTelefono;
	}

}
