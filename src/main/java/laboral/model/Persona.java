package laboral.model;

public class Persona {
	
	public String nombre, DNI;
	public char sexo;
	
	public Persona() {}

	public Persona(String nombre, String dni, char sexo) {
		this.nombre = nombre;
		this.DNI = dni;
		this.sexo = sexo;
	}
	
	public Persona(String nombre, char sexo) {
		this.nombre = nombre;
		this.sexo = sexo;
	}

	public void setDNI(String dniNuevo) {
		DNI = dniNuevo;
	}
	
	public String getDNI() {
		return DNI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String imprime() {
		return "Persona [nombre=" + nombre + ", DNI=" + DNI + "]";
	}
	
}
