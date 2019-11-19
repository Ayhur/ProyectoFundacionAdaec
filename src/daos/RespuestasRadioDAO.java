package daos;

import java.util.ArrayList;
import java.util.List;

import modelos.RadioRespuesta;

public interface RespuestasRadioDAO {
	/**
	 * Recibe una lista de respuestas de tipo radio.<br>
	 * @param radioRespuestas
	 * @param idRegistroFormulario
	 * @param erroresLog
	 */
	public void registrarRespuestas(List<RadioRespuesta> radioRespuestas, int idRegistroFormulario, ArrayList<Integer> erroresLog);
}
