package daos;

import java.util.ArrayList;
import java.util.List;

import modelos.SliderRespuesta;

public interface RespuestasSliderDAO {
	
	/**
	 * Método que se encarga de registrar todas las respuestas de tipo Slider en la BBDD.<br>
	 */
	public void registrarRespuestas(List<SliderRespuesta> sliderRespuestas, int idRegistroFormulario, ArrayList<Integer> erroresLog);
}
