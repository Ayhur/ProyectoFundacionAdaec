package daos;

import java.util.List;

import modelos.Medicamento;

public interface MedicamentosDAO {
	
	/**
	 * Método que registra los nuevos medicamentos en la BBDD.<br>
	 * @param listaMedicamentos
	 */
	public void registrarMedicamentos(List <String> listaMedicamentos);
	
	/**
	 * Método que trae una lista con todos los medicamentos.<br>
	 * @return
	 */
	public List<Medicamento> listarMedicamentos();
	
	/**
	 * Método que registra las respuestas asociadas a la tabla medicamentos en el formulario.<br>
	 * @param listaMedRespuesta
	 */
	public void agregarMedicamentosRespuesta(List<Medicamento>listaMedRespuesta);
	
}
