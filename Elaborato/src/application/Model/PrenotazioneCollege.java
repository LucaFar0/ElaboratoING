package application.Model;

public class PrenotazioneCollege {
	private int Vacanza;
	private String Persona = new String();
	private String College = new String();
	private tipoStanza Stanza;
	private metodoDiPagamento MdP;
	
	public PrenotazioneCollege(int vacanza, String persona, String college, tipoStanza stanza, metodoDiPagamento mdp) {
		this.setVacanza(vacanza);
		this.setPersona(persona);
		this.setCollege(college);
		this.setStanza(stanza);
		this.setMdP(mdp);
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


	public String getCollege() {
		return this.College;
	}


	public void setCollege(String college) {
		this.College = college;
	}


	public tipoStanza getStanza() {
		return this.Stanza;
	}


	public void setStanza(tipoStanza stanza) {
		this.Stanza = stanza;
	}


	public metodoDiPagamento getMdP() {
		return this.MdP;
	}


	public void setMdP(metodoDiPagamento mdP) {
		this.MdP = mdP;
	}

}
