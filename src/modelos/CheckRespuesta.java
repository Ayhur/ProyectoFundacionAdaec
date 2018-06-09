package modelos;

public class CheckRespuesta {

	String respuesta;
	int idPregunta;
	
	//Constructor base
	public CheckRespuesta() {
		
	}

	//Constructor con campos
	public CheckRespuesta(String respuesta, int idPregunta) {
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
		return "CheckRespuesta [respuesta=" + respuesta + ", idPregunta="
				+ idPregunta + "]";
	}
	
}
