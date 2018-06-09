package modelos;

public class Preguntas {

	private int idpregunta;
	private String descripcion;
	private int tipo;

	public Preguntas() {

	}

	public Preguntas(int idpregunta, String descripcion, int tipo) {
		super();
		this.idpregunta = idpregunta;
		this.descripcion = descripcion;
		this.tipo = tipo;
	}
	
	public Preguntas(String descripcion, int tipo) {
		super();
		this.descripcion = descripcion;
		this.tipo = tipo;
	}

	public int getIdpregunta() {
		return idpregunta;
	}

	public void setIdpregunta(int idpregunta) {
		this.idpregunta = idpregunta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Preguntas [idpregunta=" + idpregunta + ", descripcion="
				+ descripcion + ", tipo=" + tipo + "]";
	}

}
