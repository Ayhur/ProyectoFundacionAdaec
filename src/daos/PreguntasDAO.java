package daos;

import java.util.List;

import modelos.Preguntas;

public interface PreguntasDAO {
	
	/**
	 * M�todo que obtiene el listado de todas las preguntas del formulario.<br>
	 */
	List<Preguntas> obtenerPreguntas();
	
	/**
	 * M�todo que borrar una pregunta del formulario.<br>
	 * @param idpregunta
	 */
	void borrarPregunta(int idpregunta);
	
	/**
	 * M�todo que registra una pregunta de formulario nueva.<br>
	 * @param nuevaPregunta
	 * @return
	 */
	int registrarPregunta(Preguntas nuevaPregunta);
	
	int buscarPreguntaPorId(int idpregunta);
	
	//Para editar pregunta por administrador
	void editarPregunta(String descripcionPregunta, int idPregunta);
	
	/**
	 * M�todo que obtiene una pregunta del formulario a trav�s de su ID.<br>
	 * @param idPregunta
	 */
	Preguntas obtenerPreguntaPorId(int idPregunta);
	
	/**
	 * M�todo que extrae el orden de las preguntas del formulario.<br>
	 * @param orden
	 * @return 
	 */
	public List<Integer> extraerAntiguoOrdenRegistroPreguntas(int orden);
	
	/**
	 * M�todo que cambia el orden de las preguntas del formulario.<br>
	 * @param orden
	 * @return 
	 */
	void configurarNuevoOrdenListado(Integer ordenPregunta);
	

	/**
	 * M�todo que busca el orden de la pregunta seleccionada por el usuario.<br>
	 * @return
	 */
	Integer buscarOrdenPreguntaDependencia(Integer orden);
	
	/**
	 * M�todo que busca si una pregunta posee otra dependiente de ella.<br>
	 * @param idPregunta
	 * @return
	 */
	Preguntas buscarDependenciaPregunta(Integer idPregunta);
	
	/**
	 * M�todo que lista todas las preguntas, de la BBDD, sin tener encuenta dependencias.<br>
	 * @return
	 */
	List<Preguntas> listarTodasPreguntas();
	
	
	/**
	 * M�todo que se encarga de buscar la �ltima posicion de orden en el formulario de preguntas.<br>
	 * @return
	 */
	public Integer buscanPosicionFinalFormulario();
	
	//[29/08/2018] - INICIO ISMAEL
	/**
	 * M�todo que obtiene el listado de todas las preguntas del formulario, para borrar mediante selecci�n.<br>
	 * @return
	 */
	List<Preguntas> obtenerPreguntasBorrar();
	//[29/08/2018] - FIN ISMAEL
	
	/**
	 * M�todo que interroga si la pregunta dependiente es la pregunta clave de maternidad.<br>
	 * @param string
	 * @return
	 */
	boolean buscarPreguntaMadre(String string);
}
