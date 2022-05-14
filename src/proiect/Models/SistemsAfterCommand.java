package proiect.Models;

public class SistemsAfterCommand {
	String nume,familie,situatie,livrat;
	

	public SistemsAfterCommand(String nume, String familie, String situatie, String livrat) {
		super();
		this.nume = nume;
		this.familie = familie;
		this.situatie = situatie;
		this.livrat = livrat;
	}

	public String getLivrat() {
		return livrat;
	}

	public void setLivrat(String livrat) {
		this.livrat = livrat;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getFamilie() {
		return familie;
	}

	public void setFamilie(String familie) {
		this.familie = familie;
	}

	public String getSituatie() {
		return situatie;
	}

	public void setSituatie(String situatie) {
		this.situatie = situatie;
	}

	@Override
	public String toString() {
		return "SistemsAfterCommand [nume=" + nume + ", familie=" + familie + ", situatie=" + situatie + ", livrat="
				+ livrat + "]";
	}

}
