package daos;

import java.util.List;

public interface InformacionAdicionalDAO {
	
	/**
	 * Metodo que retorna un listado con los bloques de pregunta existentes.<br>
	 * @return
	 */
	public List<String> listarBloquePreguntas();
	
	/**
	 * M�todo que busca el bloque de preguntas traspasado como par�metro.<br>
	 * @param bloque
	 * @return
	 */
	public int buscarBloquePreguntas(String bloque);

}
