package modelos;

public class Respuestaspreguntas {

	private Integer idPregunta;
	private Integer idRespuesta;
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

	public Integer getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}

	public Integer getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(Integer idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((idPregunta == null) ? 0 : idPregunta.hashCode());
		result = prime * result + ((idRespuesta == null) ? 0 : idRespuesta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Respuestaspreguntas other = (Respuestaspreguntas) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (idPregunta == null) {
			if (other.idPregunta != null)
				return false;
		} else if (!idPregunta.equals(other.idPregunta))
			return false;
		if (idRespuesta == null) {
			if (other.idRespuesta != null)
				return false;
		} else if (!idRespuesta.equals(other.idRespuesta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Respuestaspreguntas [idPregunta=" + String.valueOf(idPregunta) + ", idRespuesta=" + String.valueOf(idRespuesta) + ", descripcion="
				+ descripcion + "]";
	}

} // end class
