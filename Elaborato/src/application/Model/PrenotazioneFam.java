package application.Model;

public class PrenotazioneFam {
	private int Vacanza;
	private String Persona = new String();
	private int Famiglia;
	private String NomeAmico = new String();
	private String EmailAmico = new String();
	private metodoDiPagamento MdP;
	
	
	
	
	
	public PrenotazioneFam(int vacanza, String persona, int famiglia, String nomeAmico, String emailAmico, metodoDiPagamento mdp) {
		this.setVacanza(vacanza);
		this.setPersona(persona);
		this.setFamiglia(famiglia);
		this.setNomeAmico(nomeAmico);
		this.setEmailAmico(emailAmico);
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




	public int getFamiglia() {
		return this.Famiglia;
	}




	public void setFamiglia(int famiglia) {
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

	public metodoDiPagamento getMdP() {
		return MdP;
	}

	public void setMdP(metodoDiPagamento mdP) {
		MdP = mdP;
	}

}
