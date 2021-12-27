package application.Model;

import java.util.Date;

public class Vacanza {
	private int Codice;
	private String Citta = new String();
	private Date DataPartenza = new Date();
	private int Durata;
	private String Lingua = new String();
	
	
	public int getCodice() {
		return this.Codice;
	}
	public void setCodice(int codice) {
		this.Codice = codice;
	}
	public String getCitta() {
		return this.Citta;
	}
	public void setCitta(String citta) {
		this.Citta = citta;
	}
	public Date getDataPartenza() {
		return this.DataPartenza;
	}
	public void setDataPartenza(Date dataPartenza) {
		this.DataPartenza = dataPartenza;
	}
	public int getDurata() {
		return this.Durata;
	}
	public void setDurata(int durata) {
		this.Durata = durata;
	}
	public String getLingua() {
		return this.Lingua;
	}
	public void setLingua(String lingua) {
		this.Lingua = lingua;
	}
	
	public Vacanza(int codice, String citta, Date dataPartenza, int durata, String lingua) {
		this.setCodice(codice);
		this.setCitta(citta);
		this.setDataPartenza(dataPartenza);
		this.setDurata(durata);
		this.setLingua(lingua);
		
	}
	
	
}
