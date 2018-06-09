package daos;

import java.util.List;

import modelos.Preguntas;

public interface PreguntasDAO {

	List<Preguntas> obtenerPreguntas();
	
	void borrarPregunta(int idpregunta);
	
	int registrarPregunta(Preguntas nuevaPregunta);
	
	int buscarPreguntaPorId(int idpregunta);
	
	//Para editar pregunta por administrador
	void editarPregunta(String descripcionPregunta, int idPregunta);
	Preguntas obtenerPreguntaPorId(int idPregunta);

}
