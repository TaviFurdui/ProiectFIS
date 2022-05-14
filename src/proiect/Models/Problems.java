package proiect.Models;

public class Problems {
	String sistem, componenta, descriere;

	@Override
	public String toString() {
		return "Problems [sistem=" + sistem + ", componenta=" + componenta + ", descriere=" + descriere + "]";
	}

	public Problems(String sistem, String componenta, String descriere) {
		super();
		this.sistem = sistem;
		this.componenta = componenta;
		this.descriere = descriere;
	}

	public String getSistem() {
		return sistem;
	}

	public void setSistem(String sistem) {
		this.sistem = sistem;
	}

	public String getComponenta() {
		return componenta;
	}

	public void setComponenta(String componenta) {
		this.componenta = componenta;
	}

	public String getDescriere() {
		return descriere;
	}

	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
}
