package proiect.Models;

public class Users {
	String nume, email, parola, rol;

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Users [nume=" + nume + ", email=" + email + ", parola=" + parola + ", rol=" + rol + "]";
	}

	public Users(String nume, String email, String parola, String rol) {
		super();
		this.nume = nume;
		this.email = email;
		this.parola = parola;
		this.rol = rol;
	}
}
