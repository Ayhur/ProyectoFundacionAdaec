package daos;

import java.util.List;

import modelos.Respuestaspreguntas;

public interface RespuestaspreguntasDAO {


	
	List <Respuestaspreguntas> obtenerRespuestaspreguntas();

	void borrarRespuesta(int idpregunta);
	
	void registrarRespuesta(Respuestaspreguntas respuestaNueva, int idPreguntaAsociada);
	
	//Para editar respuestas por administrador
	Respuestaspreguntas obtenerRespuestaPorId(int idPreguntaAsociada);
	void editarRespuesta(String descripcionRespuesta, int idPreguntaAsociada);
}
