package daos;

import java.util.List;

import modelos.CheckRespuesta;

public interface RespuestasCheckDAO {
	//Recibe una lista de respuestas de tipo radio
		public void registrarRespuestas(List<CheckRespuesta> checkRespuestas);
}
