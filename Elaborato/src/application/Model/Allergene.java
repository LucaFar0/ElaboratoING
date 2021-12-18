package application.Model;

public class Allergene {
	private String nome = new String();
	private String descrizione = new String();
	
	
	public Allergene(String nome, String descrizione) {
		this.setNome(nome);
		this.setDescrizione(descrizione);
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
