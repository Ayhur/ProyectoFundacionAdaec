package daosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import daos.ConstantesSQL;
import modelos.RadioRespuesta;
import daos.GenericDAO;
import daos.RespuestasRadioDAO;

public class RespuestasRadioDAOImpl extends GenericDAO implements RespuestasRadioDAO{

	@Override
	public void registrarRespuestas(List<RadioRespuesta> radioRespuestas, int idRegistroFormulario) {
		
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
				
				// Ejecuto consulta para conocer el ID generado al agregar la respuesta
				PreparedStatement ps2 = con.prepareStatement(ConstantesSQL.EXTRAER_ID_REGISTRADO);
				ResultSet rs = ps2.executeQuery();
				int idGeneradoEnRadioRespuestas = -1;

				if (rs.next()) {
					idGeneradoEnRadioRespuestas = rs.getInt(1);
				}
				// Inserto en la tabla NxN correspondiente
				insertar_NxN_Estadist_Radio(idRegistroFormulario, idGeneradoEnRadioRespuestas);
				rs.close();
			} // FIN BUCLE FOR
			System.out.println("Preguntas radio insertadas");
			ps.close();
			
		} catch (SQLException e) {
			System.out.println("Error al insertar las preguntas radio");
			System.out.println(e.getMessage());
		}
		desconectar();
	}

	private void insertar_NxN_Estadist_Radio(int idRegistroFormulario, int idGeneradoEnRadioRespuestas) {
		
		System.out.println(
				"registro virtual: " + idRegistroFormulario + "  REGISTRO respuesta: " + idGeneradoEnRadioRespuestas);

		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.NxN_ESTAD_RADIO);
			ps.setInt(1, idRegistroFormulario);
			ps.setInt(2, idGeneradoEnRadioRespuestas);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Error al insertar en la NxN de estadist-radiorespuesta");
			e.printStackTrace();
		}
	}

}
