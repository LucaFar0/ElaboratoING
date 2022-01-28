package application.Model;

public class Questionario {
	private String Prenotazione;
	private String Voto;
	private String Commento;
	private String Vacanza;
	
	
	public Questionario(String prenotazione, String voto, String commento) {
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


	public String getVoto() {
		return this.Voto;
	}


	public void setVoto(String voto) {
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
