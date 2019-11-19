package daos;

import java.util.HashMap;
import java.util.List;

import modelos.PreguntaRespuestasMaternidad;
import modelos.Preguntas;
import modelos.Respuestaspreguntas;

public interface RespuestaspreguntasDAO {


	/**
	 * M�todo que trae las respuestas de las preguntas en el formulario.<br>
	 * @return
	 */
	List <Respuestaspreguntas> obtenerRespuestaspreguntas();

	void borrarRespuesta(int idpregunta);
	
	void registrarRespuesta(Respuestaspreguntas respuestaNueva, int idPreguntaAsociada);
	
	/**
	 * M�todo que recoge las respuestas de una pregunta a trav�s de un id de pregunta.<br>
	 * @param parameter
	 * @return
	 */
	Respuestaspreguntas obtenerRespuestaPorId(int idPreguntaAsociada);
	
	/**
	 * M�todo que edita las respuestas de una pregunta.<br>
	 * @param descripcionRespuesta
	 * @param idPreguntaAsociada
	 */
	void editarRespuesta(String descripcionRespuesta, int idPreguntaAsociada);
	
	/**
	 * M�todo que extra el bloque de preguntas de maternidad.<br>
	 * @return
	 */
	public List<PreguntaRespuestasMaternidad> extraerBloqueMaternidad();
}
