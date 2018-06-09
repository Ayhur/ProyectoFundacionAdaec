package daosImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import daos.ConstantesSQL;
import modelos.RadioRespuesta;
import daos.GenericDAO;
import daos.RespuestasRadioDAO;

public class RespuestasRadioDAOImpl extends GenericDAO implements RespuestasRadioDAO{

	@Override
	public void registrarRespuestas(List<RadioRespuesta> radioRespuestas) {
		conectar();
		
		
		try {
			//Preparo consulta para ir metiendo las respuestas
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.REGISTRAR_RESPUESTA_RADIO);
			
			//Bucle para ir metiendo todas las respuestas de texto recogidas
			for(int i=0;i<radioRespuestas.size();i++){
				//Variable para sacar una respuesta de la lista de respuestas
				RadioRespuesta radioRespuesta = new RadioRespuesta();
				
				//Saco de la lista 1 elemento
				radioRespuesta = radioRespuestas.get(i);
				
				//Relleno la consulta con los valores del elemento sacado
				ps.setString(1, radioRespuesta.getRespuesta());
				ps.setInt(2, radioRespuesta.getIdPregunta());
				
				//Ejecuto consulta
				ps.execute();
			}
			System.out.println("Preguntas radio insertadas");
			ps.close();
			
		} catch (SQLException e) {
			System.out.println("Error al insertar las preguntas radio");
			System.out.println(e.getMessage());
		}
		desconectar();
	}

}
