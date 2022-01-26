package application.Model;

public class Attivita {
	private String Nome = new String();
	private String Descrizione = new String();
	private String College = new String();

	
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
	
	
	public Attivita(String nome, String descrizione, String college) {
		this.setNome(nome);
		this.setDescrizione(descrizione);
		this.setCollege(college);
	}
	
	public String toString() {
		String p = "\n" + this.getNome() + " | " + this.getDescrizione()  + " | " + this.getCollege();
		return p;
	}
	
	public String toString2() {
		String p = "\n\n Nome Attività: " + this.getNome() + " \n Descrizione:  " + this.getDescrizione(); //  + " | " + this.getCollege();
		return p;
	}
}
