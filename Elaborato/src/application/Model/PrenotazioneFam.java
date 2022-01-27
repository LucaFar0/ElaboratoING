package application.Model;

import java.sql.SQLException;

public class PrenotazioneFam {
	private String Vacanza;
	private String Persona = new String();
	private String Famiglia;
	private String NomeAmico = new String();
	private String EmailAmico = new String();
	private String MdP;
	private String Codice;
	
	
	
	
	
	public PrenotazioneFam(String vacanza, String persona, String famiglia, String nomeAmico, String emailAmico, String mdp) {
		this.setVacanza(vacanza);
		this.setPersona(persona);
		this.setFamiglia(famiglia);
		this.setNomeAmico(nomeAmico);
		this.setEmailAmico(emailAmico);
		this.setMdP(mdp);
		this.setCodice();
	}
	
	public void setCodice() {
		try {
			Codice = PostreSQLJDBC.getMaxCodicePrenotazione("Famiglia", Vacanza, this.Famiglia);
			System.out.println(Codice);
			Integer c = Integer.valueOf(Codice) + 1;
			String zeros = "";
			for(int i = c.toString().length(); i < 4; i++) {
				zeros += "0";
			}
			zeros += c.toString();
			Codice = Famiglia+zeros;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String getCodice() {
		return Codice;
	}
	
	
	
	
	public String getVacanza() {
		return this.Vacanza;
	}


	public void setVacanza(String vacanza) {
		this.Vacanza = vacanza;
	}

	public String getPersona() {
		return this.Persona;
	}

	public void setPersona(String persona) {
		this.Persona = persona;
	}




	public String getFamiglia() {
		return this.Famiglia;
	}




	public void setFamiglia(String famiglia) {
		this.Famiglia = famiglia;
	}


	public String getNomeAmico() {
		return this.NomeAmico;
	}




	public void setNomeAmico(String nomeAmico) {
		this.NomeAmico = nomeAmico;
	}


	public String getEmailAmico() {
		return this.EmailAmico;
	}




	public void setEmailAmico(String emailAmico) {
		this.EmailAmico = emailAmico;
	}

	public String getMdP() {
		return MdP;
	}

	public void setMdP(String mdP) {
		MdP = mdP;
	}

}
