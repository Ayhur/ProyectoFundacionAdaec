package daosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modelos.PreguntaRespuestasMaternidad;
import modelos.Preguntas;
import modelos.Respuestaspreguntas;
import daos.ConstantesSQL;
import daos.GenericDAO;
import daos.RespuestaspreguntasDAO;

public class RespuestaspreguntasDAOImpl extends GenericDAO implements RespuestaspreguntasDAO {

	/**
	 * Método que trae las respuestas de las preguntas en el formulario.<br>
	 * 
	 * @return
	 */
	@Override
	public List<Respuestaspreguntas> obtenerRespuestaspreguntas() {

		conectar();
		// Listado donde almacenaremos todas las respuestas
		List<Respuestaspreguntas> respuestaspreguntas = new ArrayList<Respuestaspreguntas>();
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.SELECCION_RESPUESTASPREGUNTAS);
			ResultSet resultado = ps.executeQuery();
			while (resultado.next()) {
				Respuestaspreguntas respuestapregunta = new Respuestaspreguntas();
				respuestapregunta.setDescripcion(resultado.getString("descripcion"));
				respuestapregunta.setIdPregunta(resultado.getInt("idpregunta"));
				respuestapregunta.setIdRespuesta(resultado.getInt("id"));
				respuestaspreguntas.add(respuestapregunta);

			} // end while
		} catch (SQLException e) {
			System.out.println("SQL select respuestaspreguntas esta mal");
			System.out.println(e.getMessage());

		} // end catch
		desconectar();

		return respuestaspreguntas;
	}

	@Override
	public void borrarRespuesta(int idpregunta) {
		conectar();
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.BORRAR_RESPUESTA);
			ps.setInt(1, idpregunta);
			ps.execute();
			ps.close();
			System.out.println("borrado con exito");
		} catch (SQLException e) {
			System.out.println("SQL borrar pregunta esta mal");

		}
		desconectar();
	}

	@Override
	public void registrarRespuesta(Respuestaspreguntas respuestaNueva, int idRespuestaAsociada) {
		conectar();

		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.REGISTRAR_RESPUESTA_ADMIN);
			ps.setString(1, respuestaNueva.getDescripcion());
			ps.setInt(2, idRespuestaAsociada);

			ps.execute();
			ps.close();

		} catch (SQLException e) {
			System.out.println("Error SQL registrar respuesta administrador");
			System.out.println(e.getMessage());
		}
		desconectar();
	}

	/**
	 * Método que recoge las respuestas de una pregunta a través de un id de
	 * pregunta.<br>
	 * 
	 * @param parameter
	 * @return
	 */
	@Override
	public Respuestaspreguntas obtenerRespuestaPorId(int idPreguntaAsociada) {
		conectar();

		// Donde va a ir al respuesta correspondiente
		Respuestaspreguntas respuesta = new Respuestaspreguntas();

		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.OBTENER_RESPUESTA_POR_ID);

			// Recojo la respuesta
			ps.setInt(1, idPreguntaAsociada);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				respuesta.setDescripcion(rs.getString("descripcion"));
			}

			ps.close();

		} catch (SQLException e) {
			System.out.println("Error en la SQL de buscar respuesta por id");
			System.out.println(e.getMessage());
		}

		desconectar();
		return respuesta;
	}

	@Override
	public void editarRespuesta(String descripcionRespuesta, int idPreguntaAsociada) {
		conectar();

		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.EDITAR_RESPUESTA);

			ps.setString(1, descripcionRespuesta);
			ps.setInt(2, idPreguntaAsociada);

			ps.execute();
			ps.close();

		} catch (SQLException e) {
			System.out.println("Error SQL editar respuesta");
			System.out.println(e.getMessage());
		}

		desconectar();

	}

	/**
	 * Método que extra el bloque de preguntas de maternidad.<br>
	 * 
	 * @return
	 */
	@Override
	public List<PreguntaRespuestasMaternidad> extraerBloqueMaternidad() {
		conectar();
		List<PreguntaRespuestasMaternidad> listaMaternidad = new ArrayList<>();

		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.EXTRACCION_BLOQUE_MATERNIDAD);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PreguntaRespuestasMaternidad prm = new PreguntaRespuestasMaternidad();
//				prm.setDescripcion(rs.getString("descripcion"));
				prm.setDescripcion(rs.getString(4));
				prm.setCajaAdicional(rs.getBoolean("cajaAdicional"));
				prm.setIdpregunta(rs.getInt("idpregunta"));
				prm.setNivel(rs.getInt("nivel"));
				prm.setOrden(rs.getInt("orden"));
				prm.setTipo(rs.getInt("tipo"));
				
				prm.setIdRespuesta(rs.getInt("id"));
//				prm.setDescripcion(rs.getString("descripcionResp"));
				prm.setDescripcionResp(rs.getString(2));
				
				listaMaternidad.add(prm);

			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("Error al extraer el bloque de maternidad");
			e.printStackTrace();
		}

		desconectar();
		return listaMaternidad;
	}

}
