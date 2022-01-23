package application.Model;

import java.sql.SQLException;

import application.Model.PostreSQLJDBC;


public class College {
	private String Nome = new String();
	private String Indirizzo = new String();
	private String Vacanza;
	private String Codice;
	private String nrStanze;
	

	public void setCodice() {
		try {
			Codice = PostreSQLJDBC.getMaxCodice("College", Vacanza);
			System.out.println(Codice);
			Integer c = Integer.valueOf(Codice) + 1;
			String zeros = "";
			for(int i = c.toString().length(); i < 4; i++) {
				zeros += "0";
			}
			zeros += c.toString();
			Codice = Vacanza+zeros;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getCodice() {
		return Codice;
	}
	
	public String getNome() {
		return this.Nome;
	}
	public void setNome(String nome) {
		this.Nome = nome;
	}
	public String getIndirizzo() {
		return this.Indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.Indirizzo = indirizzo;
	}
	public String getVacanza() {
		return this.Vacanza;
	}
	public void setVacanza(String vacanza) {
		//@TODO
		//CONTROLLO VALIDITA' DEL CODICE INSERITO
		this.Vacanza = vacanza;
	}
	public String getNrStanze() {
		return nrStanze;
	}
	public void setNrStanze(String nrStanze) {
		this.nrStanze = nrStanze;
	}
	
	
	public College(String nome, String indirizzo, String vacanza, String nrStanze) {
		this.setNome(nome);
		this.setIndirizzo(indirizzo);
		this.setVacanza(vacanza);
		this.setNrStanze(nrStanze);
		this.setCodice();
	}
	
	public String toString() {
		String p = "\n" + this.getCodice() + " | " + this.getNome()  + " | " + this.getNrStanze() + " | " + this.getIndirizzo() + " | " + this.getVacanza();
		return p;
	}
	
	
}