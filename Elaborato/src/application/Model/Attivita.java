package application.Model;

public class Attivita {
	private String Nome = new String();
	private String Descrizione = new String();
	private String College = new String();
	private String Vacanza;
	
	
	public String getNome() {
		return this.Nome;
	}
	public void setNome(String nome) {
		this.Nome = nome;
	}
	public String getDescrizione() {
		return this.Descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.Descrizione = descrizione;
	}
	public String getCollege() {
		return this.College;
	}
	public void setCollege(String college) {
		//@TODO
		//CONTROLLO VALIDITA' DEL NOME INSERITO
		this.College = college;
	}
	public String getVacanza() {
		return this.Vacanza;
	}
	public void setVacanza(String vacanza) {
		//@TODO
		//CONTROLLO VALIDITA' DEL CODICE INSERITO		
		this.Vacanza = vacanza;
	}
	
	public Attivita(String nome, String descrizione, String college, String vacanza) {
		this.setNome(nome);
		this.setDescrizione(descrizione);
		this.setCollege(college);
		this.setVacanza(vacanza);
	}
	
}
