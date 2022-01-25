package application.Model;

public class Gita {
	
	private String Vacanza;
	private String Destinazione = new String();
	private String Costo;
	private String ore;
	private String Descrizione = new String();
	
	
	public String getVacanza() {
		return this.Vacanza;
	}
	public void setVacanza(String vacanza) {
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
	public String getCosto() {
		return this.Costo;
	}
	public void setCosto(String costo) {
		this.Costo = costo;
	}
	public String getOre() {
		return this.ore;
	}
	public void setOre(String ore) {
		this.ore = ore;
	}
	public String getDescrizione() {
		return this.Descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.Descrizione = descrizione;
	}
	
	
	public Gita(String vacanza, String destinazione, String costo, String ore, String descrizione) {
		this.setVacanza(vacanza);
		this.setDestinazione(destinazione);
		this.setCosto(costo);
		this.setOre(ore);
		this.setDescrizione(descrizione);
		
	}
	
	public String toString() {
		String p = "\n" + this.getDestinazione() + " | " + this.getCosto() + " | " + this.getOre() + " | " + this.getDescrizione();// + " | " + this.getVacanza();
		return p;
	}
	public String toString2() {
		String p = "\n\n Destinazione" + this.getDestinazione() + " \n Costo: " + this.getCosto() + " \n Durata(ore= " + this.getOre() + " \n Descrizione " + this.getDescrizione();// + " | " + this.getVacanza();
		return p;
	}
	

}
