package daos;

import java.util.ArrayList;
import java.util.List;

import modelos.Tratamiento;

public interface TratamientoDAO {
	
	/**
	 * Método que registra los tratamientos que ha tomado un usuario.<br>
	 * @param listaTratamientos
	 * @param idRegistroFormulario 
	 * @param erroresLog 
	 */
	public void registrarRespuestaTratamientos(List<Tratamiento> listaTratamientos, int idRegistroFormulario, ArrayList<Integer> erroresLog);

}
