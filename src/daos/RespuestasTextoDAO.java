package daos;

import java.util.ArrayList;
import java.util.List;

import modelos.TextRespuesta;

public interface RespuestasTextoDAO {
	//Recibe una lista de respuestas de tipo texto
	public void registrarRespuestas(List<TextRespuesta> textRespuestas, int idRegistroFormulario, ArrayList<Integer> erroresLog);
}
