package proiect.Models;

public class SpecialCommand {
	String ram, ssd, procesor, memorie, situatie, livrat;
	int pret;

	public SpecialCommand(String ram, String ssd, String procesor, String memorie, String situatie, String livrat,
			int pret) {
		super();
		this.ram = ram;
		this.ssd = ssd;
		this.procesor = procesor;
		this.memorie = memorie;
		this.situatie = situatie;
		this.livrat = livrat;
		this.pret = pret;
	}

	public String getSituatie() {
		return situatie;
	}

	public void setSituatie(String situatie) {
		this.situatie = situatie;
	}

	public String getLivrat() {
		return livrat;
	}

	public void setLivrat(String livrat) {
		this.livrat = livrat;
	}

	public int getPret() {
		return pret;
	}

	public void setPret(int pret) {
		this.pret = pret;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getSsd() {
		return ssd;
	}

	public void setSsd(String ssd) {
		this.ssd = ssd;
	}

	public String getProcesor() {
		return procesor;
	}

	public void setProcesor(String procesor) {
		this.procesor = procesor;
	}

	public String getMemorie() {
		return memorie;
	}

	public void setMemorie(String memorie) {
		this.memorie = memorie;
	}
}
