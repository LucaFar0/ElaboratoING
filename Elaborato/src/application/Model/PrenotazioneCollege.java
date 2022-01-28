package application.Model;

import java.sql.SQLException;

public class PrenotazioneCollege {
	private String Vacanza;
	private String Persona = new String();
	private String College = new String();
	private String Stanza;
	private String MdP;
	private String Codice;
	
	public PrenotazioneCollege(String vacanza, String persona, String college, String stanza, String mdp, boolean flag, String codice) {
		this.setVacanza(vacanza);
		this.setPersona(persona);
		this.setCollege(college);
		this.setStanza(stanza);
		this.setMdP(mdp);
		if(flag == false)	this.setCodice();
		else this.setCodice2(codice);
	}

	
	public void setCodice() {
		try {
			Codice = PostreSQLJDBC.getMaxCodicePrenotazione("College", Vacanza, this.College);
			System.out.println(Codice);
			Integer c = Integer.valueOf(Codice) + 1;
			String zeros = "";
			for(int i = c.toString().length(); i < 4; i++) {
				zeros += "0";
			}
			zeros += c.toString();
			Codice = College+zeros+"C";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String getCodice() {
		return Codice;
	}
	
	public void setCodice2(String codice) {
		this.Codice = codice;
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


	public String getCollege() {
		return this.College;
	}


	public void setCollege(String college) {
		this.College = college;
	}


	public String getStanza() {
		return this.Stanza;
	}


	public void setStanza(String stanza) {
		this.Stanza = stanza;
	}


	public String getMdP() {
		return this.MdP;
	}


	public void setMdP(String mdP) {
		this.MdP = mdP;
	}

}
