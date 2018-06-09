package daos;

import java.util.List;

import modelos.RadioRespuesta;

public interface RespuestasRadioDAO {
	//Recibe una lista de respuestas de tipo radio
	public void registrarRespuestas(List<RadioRespuesta> radioRespuestas);
}
