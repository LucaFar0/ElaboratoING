package application.Model;

public class Famiglia {
	private String CapoFamiglia = new String();
	private int Vacanza;
	private int nrComponenti;
	private int nrCamere;
	private int nrBagni;
	private boolean Animali;
	private int Distanza;
	
	public Famiglia(String capoFam, int vacanza, int nrComponenti, int nrCamere, int nrBagni, boolean animali, int distanza) {
		this.setCapoFamiglia(capoFam);
		this.setVacanza(vacanza);
		this.setNrComponenti(nrComponenti);
		this.setNrCamere(nrCamere);
		this.setNrBagni(nrBagni);
		this.setDistanza(distanza);
		this.setAnimali(animali);
		
	}

	
	public String getCapoFamiglia() {
		return this.CapoFamiglia;
	}

	public void setCapoFamiglia(String capoFamiglia) {
		//@TODO
		//CONTROLLO VALIDITA' DEL CODICE INSERITO
		this.CapoFamiglia = capoFamiglia;
	}


	public int getVacanza() {
		return this.Vacanza;
	}


	public void setVacanza(int vacanza) {
		//@TODO
		//CONTROLLO VALIDITA' DEL CODICE INSERITO
		this.Vacanza = vacanza;
	}


	public int getNrComponenti() {
		return this.nrComponenti;
	}


	public void setNrComponenti(int nrComponenti) {
		this.nrComponenti = nrComponenti;
	}


	public int getNrCamere() {
		return this.nrCamere;
	}


	public void setNrCamere(int nrCamere) {
		this.nrCamere = nrCamere;
	}


	public int getNrBagni() {
		return nrBagni;
	}


	public void setNrBagni(int nrBagni) {
		this.nrBagni = nrBagni;
	}


	public boolean isAnimali() {
		return this.Animali;
	}


	public void setAnimali(boolean animali) {
		this.Animali = animali;
	}


	public int getDistanza() {
		return this.Distanza;
	}


	public void setDistanza(int distanza) {
		this.Distanza = distanza;
	}

}
