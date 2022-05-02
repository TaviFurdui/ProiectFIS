package proiect.Models;

public class Components {
	String nume, furnizor;
	float pret;

	public Components(String nume, String furnizor, float pret) {
		super();
		this.nume = nume;
		this.furnizor = furnizor;
		this.pret = pret;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getFurnizor() {
		return furnizor;
	}

	public void setFurnizor(String furnizor) {
		this.furnizor = furnizor;
	}

	public float getPret() {
		return pret;
	}

	public void setPret(float pret) {
		this.pret = pret;
	}

	@Override
	public String toString() {
		return "Components [nume=" + nume + ", furnizor=" + furnizor + ", pret=" + pret + "]";
	}
}
