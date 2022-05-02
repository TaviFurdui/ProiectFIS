package proiect.Models;

import java.util.Date;

public class ComponentsAfterCommand {
	String nume, inventar, furnizor, status, situatie, observatii;
	Date data;

	public ComponentsAfterCommand(String nume, String inventar, String furnizor, String status, String situatie,
			String observatii, Date data) {
		super();
		this.nume = nume;
		this.inventar = inventar;
		this.furnizor = furnizor;
		this.status = status;
		this.situatie = situatie;
		this.observatii = observatii;
		this.data = data;
	}

	@Override
	public String toString() {
		return "ComponentsAfterCommand [nume=" + nume + ", inventar=" + inventar + ", furnizor=" + furnizor
				+ ", status=" + status + ", situatie=" + situatie + ", observatii=" + observatii + ", data=" + data
				+ "]";
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getInventar() {
		return inventar;
	}

	public void setInventar(String inventar) {
		this.inventar = inventar;
	}

	public String getFurnizor() {
		return furnizor;
	}

	public void setFurnizor(String furnizor) {
		this.furnizor = furnizor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSituatie() {
		return situatie;
	}

	public void setSituatie(String situatie) {
		this.situatie = situatie;
	}

	public String getObservatii() {
		return observatii;
	}

	public void setObservatii(String observatii) {
		this.observatii = observatii;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
