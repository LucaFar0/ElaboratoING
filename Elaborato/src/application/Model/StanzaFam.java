package application.Model;

public class StanzaFam {
	private String Famiglia = new String();
	private int Vacanza;
	private String Persona1 = new String();
	private String Persona2 = new String();
	
	
	public StanzaFam(String famiglia, int vacanza, String persona1, String persona2) {
		this.setFamiglia(famiglia);
		this.setVacanza(vacanza);
		this.setPersona1(persona1);
		this.setPersona2(persona2);
	}


	public String getFamiglia() {
		return this.Famiglia;
	}


	public void setFamiglia(String famiglia) {
		this.Famiglia = famiglia;
	}


	public int getVacanza() {
		return this.Vacanza;
	}


	public void setVacanza(int vacanza) {
		this.Vacanza = vacanza;
	}


	public String getPersona1() {
		return Persona1;
	}


	public void setPersona1(String persona1) {
		this.Persona1 = persona1;
	}


	public String getPersona2() {
		return this.Persona2;
	}


	public void setPersona2(String persona2) {
		this.Persona2 = persona2;
	}

}
