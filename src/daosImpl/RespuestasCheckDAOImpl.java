package daosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.ConstantesSQL;
import modelos.CheckRespuesta;
import daos.GenericDAO;
import daos.RespuestasCheckDAO;

public class RespuestasCheckDAOImpl extends GenericDAO implements RespuestasCheckDAO{

	@Override
	public void registrarRespuestas(List<CheckRespuesta> checkRespuestas, int idRegistroFormulario, ArrayList<Integer> erroresLog) {
		
		conectar();
		try {
			
			//Preparo consulta para ir metiendo las respuestas
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.REGISTRAR_RESPUESTA_CHECK);
			
			System.out.println("Empezando a insertar las checkRespuestas");
			
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
				
				// Ejecuto consulta para conocer el ID generado al agregar la respuesta
				PreparedStatement ps2 = con.prepareStatement(ConstantesSQL.EXTRAER_ID_REGISTRADO);
				ResultSet rs = ps2.executeQuery();
				int idGeneradoEnCheckRespuestas = -1;

				if (rs.next()) {
					idGeneradoEnCheckRespuestas = rs.getInt(1);
				}
				
				insertar_NxN_Estadist_Check(idRegistroFormulario, idGeneradoEnCheckRespuestas);
				System.out.println("Finalizada insertacion de las checkRespuestas");
				rs.close();
				
			}
			System.out.println("Todas las respuestas de check han sido insertadas");
			//Cierro el prepared para la consulta
			ps.close();
		} catch (SQLException e) {
			System.out.println("Error al insertar respuestas de check");
			System.out.println(e.getMessage());
			erroresLog.add(102);
		}
		desconectar();
	}

	private void insertar_NxN_Estadist_Check(int idRegistroFormulario, int idGeneradoEnCheckRespuestas) {
		
		System.out.println(
				"registro virtual: " + idRegistroFormulario + "  REGISTRO respuesta: " + idGeneradoEnCheckRespuestas);

		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.NxN_ESTAD_CHECK);
			ps.setInt(1, idRegistroFormulario);
			ps.setInt(2, idGeneradoEnCheckRespuestas);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Error al insertar en la NxN de estadist-checkrespuesta");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
