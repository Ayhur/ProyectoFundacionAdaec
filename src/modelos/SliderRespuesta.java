package modelos;

public class SliderRespuesta {
	
	int idpregunta;
	int respuesta;
	
	public SliderRespuesta() {
	}
	
	public SliderRespuesta(int respuesta, int idpregunta) {
		super();
		this.respuesta = respuesta;
		this.idpregunta = idpregunta;
	}

	public int getIdpregunta() {
		return idpregunta;
	}

	public void setIdpregunta(int idpregunta) {
		this.idpregunta = idpregunta;
	}

	public int getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(int respuesta) {
		this.respuesta = respuesta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idpregunta;
		result = prime * result + respuesta;
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
		SliderRespuesta other = (SliderRespuesta) obj;
		if (idpregunta != other.idpregunta)
			return false;
		if (respuesta != other.respuesta)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SliderRespuesta [idpregunta=" + idpregunta + ", respuesta=" + respuesta + "]";
	}
	
}
