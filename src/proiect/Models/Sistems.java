package proiect.Models;

public class Sistems {
	String familie, nume;
	float pret;
	int cantitate_inventar, cantitate_livrata, cantitate_comandata;
	public String getFamilie() {
		return familie;
	}
	public void setFamilie(String familie) {
		this.familie = familie;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public float getPret() {
		return pret;
	}
	public void setPret(float pret) {
		this.pret = pret;
	}
	public int getCantitate_inventar() {
		return cantitate_inventar;
	}
	public void setCantitate_inventar(int cantitate_inventar) {
		this.cantitate_inventar = cantitate_inventar;
	}
	public int getCantitate_livrata() {
		return cantitate_livrata;
	}
	public void setCantitate_livrata(int cantitate_livrata) {
		this.cantitate_livrata = cantitate_livrata;
	}
	public int getCantitate_comandata() {
		return cantitate_comandata;
	}
	public void setCantitate_comandata(int cantitate_comandata) {
		this.cantitate_comandata = cantitate_comandata;
	}
	@Override
	public String toString() {
		return "Sistems [familie=" + familie + ", nume=" + nume + ", pret=" + pret + ", cantitate_inventar="
				+ cantitate_inventar + ", cantitate_livrata=" + cantitate_livrata + ", cantitate_comandata="
				+ cantitate_comandata + "]";
	}
	public Sistems(String familie, String nume, float pret, int cantitate_inventar, int cantitate_livrata,
			int cantitate_comandata) {
		super();
		this.familie = familie;
		this.nume = nume;
		this.pret = pret;
		this.cantitate_inventar = cantitate_inventar;
		this.cantitate_livrata = cantitate_livrata;
		this.cantitate_comandata = cantitate_comandata;
	}
}
