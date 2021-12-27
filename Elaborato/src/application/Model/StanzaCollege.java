package application.Model;

public class StanzaCollege {
	private String College = new String();
	private int Vacanza;
	private String Persona1 = new String();
	private String Persona2 = new String();
	private tipoStanza Tipo;
	
	public StanzaCollege(String college, int vacanza, String Persona1, String Persona2, tipoStanza tipo) {
		this.setCollege(college);
		this.setVacanza(vacanza);
		this.setPersona1(Persona1);
		this.setTipo(tipo);
		if(this.Tipo.equals(tipoStanza.DOPPIA)){
			this.setPersona2(Persona2);
		}
	}

	public String getCollege() {
		return College;
	}

	public void setCollege(String college) {
		College = college;
	}

	public int getVacanza() {
		return Vacanza;
	}

	public void setVacanza(int vacanza) {
		Vacanza = vacanza;
	}

	public String getPersona1() {
		return Persona1;
	}

	public void setPersona1(String persona1) {
		Persona1 = persona1;
	}

	public String getPersona2() {
		return Persona2;
	}

	public void setPersona2(String persona2) {
		Persona2 = persona2;
	}

	public tipoStanza getTipo() {
		return Tipo;
	}

	public void setTipo(tipoStanza tipo) {
		Tipo = tipo;
	}

}
