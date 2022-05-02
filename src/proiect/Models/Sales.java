package proiect.Models;

import java.util.ArrayList;
import java.util.List;

public class Sales {
	List <Sistems> listaReduceri = new ArrayList <Sistems>();
	float pretNou;
	public float getPretNou() {
		return pretNou;
	}

	public void setPretNou(float pretNou) {
		this.pretNou = pretNou;
	}

	public List<Sistems> getListaReduceri() {
		return listaReduceri;
	}

	public void setListaReduceri(List<Sistems> listaReduceri) {
		this.listaReduceri = listaReduceri;
	}

	@Override
	public String toString() {
		return "Sales [listaReduceri=" + listaReduceri + ", pretNou=" + pretNou + "]";
	}

	public Sales(List<Sistems> listaReduceri, float pretNou) {
		super();
		this.listaReduceri = listaReduceri;
		this.pretNou = pretNou;
	}
}
