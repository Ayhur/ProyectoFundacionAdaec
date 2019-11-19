package daos;

import java.util.HashMap;
import java.util.List;

import modelos.PreguntaRespuestasMaternidad;
import modelos.Preguntas;
import modelos.Respuestaspreguntas;

public interface RespuestaspreguntasDAO {


	/**
	 * Método que trae las respuestas de las preguntas en el formulario.<br>
	 * @return
	 */
	List <Respuestaspreguntas> obtenerRespuestaspreguntas();

	void borrarRespuesta(int idpregunta);
	
	void registrarRespuesta(Respuestaspreguntas respuestaNueva, int idPreguntaAsociada);
	
	/**
	 * Método que recoge las respuestas de una pregunta a través de un id de pregunta.<br>
	 * @param parameter
	 * @return
	 */
	Respuestaspreguntas obtenerRespuestaPorId(int idPreguntaAsociada);
	
	/**
	 * Método que edita las respuestas de una pregunta.<br>
	 * @param descripcionRespuesta
	 * @param idPreguntaAsociada
	 */
	void editarRespuesta(String descripcionRespuesta, int idPreguntaAsociada);
	
	/**
	 * Método que extra el bloque de preguntas de maternidad.<br>
	 * @return
	 */
	public List<PreguntaRespuestasMaternidad> extraerBloqueMaternidad();
}
