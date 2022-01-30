package application.Model;

public class Questionario {
	private String Prenotazione;
	private Integer Voto;
	private String Commento;
	private String Vacanza = "";
	
	
	public Questionario(String prenotazione, Integer voto, String commento) {
		this.setPrenotazione(prenotazione);
		this.setVoto(voto);
		this.setCommento(commento);
		this.setVacanza();
	}


	public String getPrenotazione() {
		return this.Prenotazione;
	}


	public void setPrenotazione(String vacanza) {
		this.Prenotazione = vacanza;
	}


	public Integer getVoto() {
		return this.Voto;
	}


	public void setVoto(Integer voto) {
		this.Voto = voto;
	}


	public String getCommento() {
		return this.Commento;
	}


	public void setCommento(String commento) {
		this.Commento = commento;
	}


	public String getVacanza() {
		return Vacanza;
	}


	public void setVacanza() {
		String x = this.Prenotazione;
		for(int i = 0; i < 4; i++)
			this.Vacanza += x.charAt(i);
		
		
	}

}
