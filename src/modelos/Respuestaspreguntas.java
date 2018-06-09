package modelos;

public class Respuestaspreguntas {
	private String descripcion;
	 
	public Respuestaspreguntas() {
	}

	public Respuestaspreguntas(String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Respuestaspreguntas [descripcion=" + descripcion + "]";
	}

	
	
	
	

} // end class
