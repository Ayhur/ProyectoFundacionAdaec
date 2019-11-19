package daosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelos.TextRespuesta;
import daos.ConstantesSQL;
import daos.GenericDAO;
import daos.RespuestasTextoDAO;

public class RespuestasTextoDAOImpl extends GenericDAO implements RespuestasTextoDAO {

	@Override
	public void registrarRespuestas(List<TextRespuesta> textRespuestas, int idRegistroFormulario, ArrayList<Integer> erroresLog) {

		conectar();
		try {

			// Preparo consulta para ir metiendo las respuestas
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.REGISTRAR_RESPUESTA_TEXTO);
			System.out.println("Empezando a insertar las textRespuestas");
			// Bucle para ir metiendo todas las respuestas de texto recogidas
			for (int i = 0; i < textRespuestas.size(); i++) {
				// Variable para sacar una respuesta de la lista de respuestas
				TextRespuesta textRespuesta = new TextRespuesta();

				// Saco de la lista un elemento
				textRespuesta = textRespuestas.get(i);

				// Relleno la consulta con los valores del elemento sacado
				ps.setString(1, textRespuesta.getRespuesta());
				ps.setInt(2, textRespuesta.getIdPregunta());
				// Ejecuto consulta
				ps.execute();
				// Ejecuto consulta para conocer el ID generado al agregar la respuesta
				PreparedStatement ps2 = con.prepareStatement(ConstantesSQL.EXTRAER_ID_REGISTRADO);
				ResultSet rs = ps2.executeQuery();
				int idGeneradoEnTextRespuestas = -1;

				if (rs.next()) {
					idGeneradoEnTextRespuestas = rs.getInt(1);
				}
				
				insertar_NxN_Estadist_Text(idRegistroFormulario, idGeneradoEnTextRespuestas);
				rs.close();
			} // FIN BUCLE FOR
			System.out.println("Todas las respuestas de texto han sido insertadas");
			// Cierro el prepared para la consulta
			ps.close();
		} catch (SQLException e) {
			System.out.println("Error al insertar respuesta de texto");
			System.out.println(e.getMessage());
			erroresLog.add(100);
		}
		desconectar();
	}

	private void insertar_NxN_Estadist_Text(int idRegistroFormulario, int idGeneradoEnTextRespuestas) {

		/*System.out.println(
				"registro virtual: " + idRegistroFormulario + "  REGISTRO respuesta: " + idGeneradoEnTextRespuestas);*/

		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.NxN_ESTAD_TEXT);
			ps.setInt(1, idRegistroFormulario);
			ps.setInt(2, idGeneradoEnTextRespuestas);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Error al insertar en la NxN de estadist-textrespuesta");
			e.printStackTrace();
		}
	}
}
