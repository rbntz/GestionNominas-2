package laboral.model;

public class DatosNoCorrectosException extends Exception {

	public DatosNoCorrectosException() {
	}

	public DatosNoCorrectosException(String mensaje) {
		System.out.println(mensaje);
	}

}