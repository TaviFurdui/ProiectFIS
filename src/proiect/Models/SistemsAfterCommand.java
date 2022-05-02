package proiect.Models;

public class SistemsAfterCommand {
	String nume,familie,situatie;

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
		return "SistemsAfterCommand [nume=" + nume + ", familie=" + familie + ", situatie=" + situatie + "]";
	}

	public SistemsAfterCommand(String nume, String familie, String situatie) {
		super();
		this.nume = nume;
		this.familie = familie;
		this.situatie = situatie;
	}
}
