package daos;

import java.util.List;

public interface InformacionAdicionalDAO {
	
	/**
	 * Metodo que retorna un listado con los bloques de pregunta existentes.<br>
	 * @return
	 */
	public List<String> listarBloquePreguntas();
	
	/**
	 * Método que busca el bloque de preguntas traspasado como parámetro.<br>
	 * @param bloque
	 * @return
	 */
	public int buscarBloquePreguntas(String bloque);

}
