package application.Model;

public class Questionario {
	private int Vacanza;
	private String Persona = new String();
	private int Voto;
	private String Commento = new String();
	
	
	public Questionario(int vacanza, String persona, int voto, String commento) {
		this.setVacanza(vacanza);
		this.setPersona(persona);
		this.setVoto(voto);
		this.setCommento(commento);
		
	}


	public int getVacanza() {
		return this.Vacanza;
	}


	public void setVacanza(int vacanza) {
		this.Vacanza = vacanza;
	}


	public String getPersona() {
		return this.Persona;
	}


	public void setPersona(String persona) {
		this.Persona = persona;
	}


	public int getVoto() {
		return this.Voto;
	}


	public void setVoto(int voto) {
		this.Voto = voto;
	}


	public String getCommento() {
		return this.Commento;
	}


	public void setCommento(String commento) {
		this.Commento = commento;
	}

}
