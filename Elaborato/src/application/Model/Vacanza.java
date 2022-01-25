package application.Model;

import java.util.Date;

public class Vacanza {
	private String Codice;
	private String Citta = new String();
	private String DataPartenza = new String();
	private String Durata;
	private String Lingua = new String();
	
	
	public String getCodice() {
		return this.Codice;
	}
	public void setCodice(String codice) {
		this.Codice = codice;
	}
	public String getCitta() {
		return this.Citta;
	}
	public void setCitta(String citta) {
		this.Citta = citta;
	}
	public String getDataPartenza() {
		return this.DataPartenza;
	}
	public void setDataPartenza(String dataPartenza) {
		this.DataPartenza = dataPartenza;
	}
	public String getDurata() {
		return this.Durata;
	}
	public void setDurata(String durata) {
		this.Durata = durata;
	}
	public String getLingua() {
		return this.Lingua;
	}
	public void setLingua(String lingua) {
		this.Lingua = lingua;
	}
	
	public Vacanza(String codice, String citta, String dataPartenza, String durata, String lingua) {
		this.setCodice(codice);
		this.setCitta(citta);
		this.setDataPartenza(dataPartenza);
		this.setDurata(durata);
		this.setLingua(lingua);
		
	}
	
	
	public String toString() {
		String p = "\n" + this.getCodice() + " | " + this.getCitta() + " | " + this.getDataPartenza() + " | " + this.getDurata() + " | " + this.getLingua();
		return p;
	}
	
	
}
