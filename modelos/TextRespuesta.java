package modelos;

public class TextRespuesta {
	String respuesta;
	int idPregunta;
	
	//Constructor vacio
	public TextRespuesta() {
	}

	//Constructor con campos
	public TextRespuesta(String respuesta, int idPregunta) {
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
		return "TextRespuesta [respuesta=" + respuesta + ", idPregunta="
				+ idPregunta + "]";
	}
	
}
