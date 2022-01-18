package application.Model;

import java.sql.SQLException;

public class Famiglia {
	private String CapoFamiglia = new String();
	private String Vacanza = new String();
	private String nrComponenti = new String();
	private String nrCamere = new String();
	private String nrBagni = new String();
	private boolean Animali;
	private String Distanza = new String();
	private String Codice;
	
	public Famiglia(String capoFam, String vacanza, String nrComponenti, String nrCamere, String nrBagni, boolean animali, String distanza) {
		this.setCapoFamiglia(capoFam);
		this.setVacanza(vacanza);
		this.setNrComponenti(nrComponenti);
		this.setNrCamere(nrCamere);
		this.setNrBagni(nrBagni);
		this.setDistanza(distanza);
		this.setAnimali(animali);
		this.setCodice();
	}
	
	public void setCodice() {
		try {
			Codice = PostreSQLJDBC.getMaxCodice("Famiglia", Vacanza);
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
	
	public String getCapoFamiglia() {
		return this.CapoFamiglia;
	}

	public void setCapoFamiglia(String capoFamiglia) {
		//@TODO
		//CONTROLLO VALIDITA' DEL CODICE INSERITO
		this.CapoFamiglia = capoFamiglia;
	}


	public String getVacanza() {
		return this.Vacanza;
	}


	public void setVacanza(String vacanza) {
		//@TODO
		//CONTROLLO VALIDITA' DEL CODICE INSERITO
		this.Vacanza = vacanza;
	}


	public String getNrComponenti() {
		return this.nrComponenti;
	}


	public void setNrComponenti(String nrComponenti) {
		this.nrComponenti = nrComponenti;
	}


	public String getNrCamere() {
		return this.nrCamere;
	}


	public void setNrCamere(String nrCamere) {
		this.nrCamere = nrCamere;
	}


	public String getNrBagni() {
		return nrBagni;
	}


	public void setNrBagni(String nrBagni) {
		this.nrBagni = nrBagni;
	}


	public boolean isAnimali() {
		return this.Animali;
	}


	public void setAnimali(boolean animali) {
		this.Animali = animali;
	}


	public String getDistanza() {
		return this.Distanza;
	}


	public void setDistanza(String distanza) {
		this.Distanza = distanza;
	}
	
	
	public String toString() {
		String p = "\n" + this.getCodice() + " | " + this.getCapoFamiglia() + " | " + this.getVacanza() + " | " + this.getNrComponenti() + " | " + this.getNrCamere() + " | " + this.getNrBagni() + " | " + this.isAnimali() + " | " + this.getDistanza();
		return p;
	}

}
