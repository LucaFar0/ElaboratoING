package application.Model;

public class Gita {
	
	private int Vacanza;
	private String Destinazione = new String();
	private int Costo;
	private int ore;
	private String Descrizione = new String();
	
	
	public int getVacanza() {
		return this.Vacanza;
	}
	public void setVacanza(int vacanza) {
		//@TODO
		//CONTROLLO VALIDITA' DEL CODICE INSERITO
		this.Vacanza = vacanza;
	}
	public String getDestinazione() {
		return this.Destinazione;
	}
	public void setDestinazione(String destinazione) {
		this.Destinazione = destinazione;
	}
	public int getCosto() {
		return this.Costo;
	}
	public void setCosto(int costo) {
		this.Costo = costo;
	}
	public int getOre() {
		return this.ore;
	}
	public void setOre(int ore) {
		this.ore = ore;
	}
	public String getDescrizione() {
		return this.Descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.Descrizione = descrizione;
	}
	
	
	public Gita(int vacanza, String destinazione, int costo, int ore, String descrizione) {
		this.setVacanza(vacanza);
		this.setDestinazione(destinazione);
		this.setCosto(costo);
		this.setOre(ore);
		this.setDescrizione(descrizione);
		
	}
	
	

}
