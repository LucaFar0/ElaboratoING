package application.Model;

public class Questionario {
	private String Prenotazione;
	private String Voto;
	private String Commento;
	
	
	public Questionario(String prenotazione, String voto, String commento) {
		this.setVacanza(prenotazione);
		this.setVoto(voto);
		this.setCommento(commento);
		
	}


	public String getVacanza() {
		return this.Prenotazione;
	}


	public void setVacanza(String vacanza) {
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

}
