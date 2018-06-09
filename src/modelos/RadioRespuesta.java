package modelos;

public class RadioRespuesta {

	String respuesta;
	int idPregunta;
	
	public RadioRespuesta() {
		// TODO Auto-generated constructor stub
	}

	public RadioRespuesta(String respuesta, int idPregunta) {
		super();
		this.respuesta = respuesta;
		this.idPregunta = idPregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public int getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(int idPregunta) {
		this.idPregunta = idPregunta;
	}

	@Override
	public String toString() {
		return "RadioRespuesta [respuesta=" + respuesta + ", idPregunta="
				+ idPregunta + "]";
	}
	
}
