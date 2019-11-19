package daosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.ConstantesSQL;
import daos.GenericDAO;
import daos.RespuestasSliderDAO;
import modelos.SliderRespuesta;

public class RespuestasSliderDAOImpl extends GenericDAO implements RespuestasSliderDAO{

	@Override
	public void registrarRespuestas(List<SliderRespuesta> sliderRespuestas, int idRegistroFormulario,
			ArrayList<Integer> erroresLog) {
		
		conectar();
		
		//Preparo consulta para ir metiendo las respuestas
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.REGISTRAR_RESPUESTA_SLIDER);
			System.out.println("Empezando a insertar las SliderRespuestas");
			//Bucle para ir metiendo todas las respuestas de texto recogidas
			for(int i=0;i<sliderRespuestas.size();i++){
				//Variable para sacar una respuesta de la lista de respuestas
				SliderRespuesta sliderRespuesta = new SliderRespuesta();
				
				//Saco de la lista 1 elemento
				sliderRespuesta = sliderRespuestas.get(i);
				
				//Relleno la consulta con los valores del elemento sacado
				ps.setInt(1, sliderRespuesta.getRespuesta());
				ps.setInt(2, sliderRespuesta.getIdpregunta());
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
			ps.close();
			System.out.println("Registradas todas las sliderRespuestas");
			
			
			
		} catch (SQLException e) {
			System.out.println("Error al insertar las preguntas radio");
			System.out.println(e.getMessage());
			erroresLog.add(105);
		}
		desconectar();
	}
	
	/**
	 * Método que registra en la base de datos la tabla de conexion NxN correspondiente, entre la SI06 y la correspondiente (en este caso SI16).<br>
	 * @param idRegistroFormulario
	 * @param idGeneradoEnRadioRespuestas
	 */
	private void insertar_NxN_Estadist_Radio(int idRegistroFormulario, int idGeneradoEnRadioRespuestas) {
		
		System.out.println(
				"registro virtual: " + idRegistroFormulario + "  REGISTRO respuesta: " + idGeneradoEnRadioRespuestas);

		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.NxN_ESTAD_SLIDER);
			ps.setInt(1, idRegistroFormulario);
			ps.setInt(2, idGeneradoEnRadioRespuestas);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Error al insertar en la SI17 (NxN) de estadist-sliderrespuestas");
			e.printStackTrace();
		}
	}

}
