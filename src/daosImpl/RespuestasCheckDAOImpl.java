package daosImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import daos.ConstantesSQL;
import modelos.CheckRespuesta;
import daos.GenericDAO;
import daos.RespuestasCheckDAO;

public class RespuestasCheckDAOImpl extends GenericDAO implements RespuestasCheckDAO{

	@Override
	public void registrarRespuestas(List<CheckRespuesta> checkRespuestas) {
		conectar();
		
		try {
			
			//Preparo consulta para ir metiendo las respuestas
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.REGISTRAR_RESPUESTA_CHECK);
			
			//Bucle para ir metiendo todas las respuestas de texto recogidas
			for(int i=0;i<checkRespuestas.size();i++){
				//Variable para sacar una respuesta de la lista de respuestas
				CheckRespuesta checkRespuesta = new CheckRespuesta();
				
				//Saco de la lista 1 elemento
				checkRespuesta = checkRespuestas.get(i);
				
				//Relleno la consulta con los valores del elemento sacado
				ps.setString(1, checkRespuesta.getRespuesta());
				ps.setInt(2, checkRespuesta.getIdPregunta());
				
				//Ejecuto consulta
				ps.execute();
				
			}
			System.out.println("Todas las respuestas de check han sido insertadas");
			//Cierro el prepared para la consulta
			ps.close();
		} catch (SQLException e) {
			System.out.println("Error al insertar respuestas de check");
			System.out.println(e.getMessage());
		}
		//Desconecto
		desconectar();
	}

}
