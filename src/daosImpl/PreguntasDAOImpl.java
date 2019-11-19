package daosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import daos.ConstantesSQL;
import daos.GenericDAO;
import daos.PreguntasDAO;
import modelos.Preguntas;

public class PreguntasDAOImpl extends GenericDAO implements PreguntasDAO {

	/**
	 * Método que obtiene el listado de todas las preguntas del formulario.<br>
	 */
	@Override
	public List<Preguntas> obtenerPreguntas() {
		conectar();
		List<Preguntas> preguntas = new ArrayList<Preguntas>();
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.LISTAR_PREGUNTAS);
			ResultSet resultado = ps.executeQuery();
			while (resultado.next()) {
				Preguntas pregunta = new Preguntas();
				pregunta.setIdpregunta(resultado.getInt("idpregunta"));
				pregunta.setDescripcion(resultado.getString("descripcion"));
				pregunta.setTipo(resultado.getInt("tipo"));
				pregunta.setNivel(resultado.getInt("nivel"));
				pregunta.setOrden(resultado.getInt("orden"));
				pregunta.setCajaAdicional(resultado.getBoolean("cajaAdicional"));
				pregunta.setBloque(resultado.getInt("SI18_ID"));
				preguntas.add(pregunta);

			}
		} catch (SQLException e) {
			System.out.println("Error en la SQL de listar preguntas");
			e.printStackTrace();
		}
		desconectar();
		return preguntas;
	}

	public void borrarPregunta(int idpregunta) {
		conectar();
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.BORRAR_PREGUNTA);
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
	public int buscarPreguntaPorId(int idpregunta) {
		conectar();
		int tipopregunta = -1;
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.OBTENER_PREGUNTA_POR_ID);
			ResultSet resultado = ps.executeQuery();
			while (resultado.next()) {
				tipopregunta = resultado.getInt("tipo");
			}
		} catch (SQLException e) {
			System.out.println("Error en la SQL de obtener pregunta por id");
			System.out.println(e.getMessage());
		}
		desconectar();
		return tipopregunta;
	}

	/**
	 * Método que registra una pregunta de formulario nueva.<br>
	 * 
	 * @param nuevaPregunta
	 * @return
	 */
	@Override
	public int registrarPregunta(Preguntas nuevaPregunta) {
		conectar();

		int idGenerado = -1;

		try {

			PreparedStatement ps = con.prepareStatement(ConstantesSQL.REGISTRAR_PREGUNTA_ADMIN,
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, nuevaPregunta.getDescripcion());
			ps.setInt(2, nuevaPregunta.getTipo());
			ps.setInt(3, nuevaPregunta.getNivel());
			ps.setInt(4, nuevaPregunta.getOrden());
			ps.setBoolean(5, nuevaPregunta.isCajaAdicional());
			ps.setInt(6, nuevaPregunta.getBloque());
			ps.execute();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				idGenerado = rs.getInt(1);
			}

			ps.close();

		} catch (SQLException e) {
			System.out.println("Error SQL registrar pregunta administrador");
			System.out.println(e.getMessage());
		}
		desconectar();

		return idGenerado;
	}

	@Override
	public void editarPregunta(String descripcionPregunta, int idPregunta) {
		conectar();

		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.EDITAR_PREGUNTA);

			ps.setString(1, descripcionPregunta);
			ps.setInt(2, idPregunta);

			ps.execute();
			ps.close();

		} catch (SQLException e) {
			System.out.println("Error SQL editar pregunta");
			System.out.println(e.getMessage());
		}

		desconectar();
	}

	/**
	 * Método que obtiene una pregunta del formulario a través de su ID.<br>
	 * 
	 * @param idPregunta
	 */
	@Override
	public Preguntas obtenerPreguntaPorId(int idPregunta) {
		conectar();

		Preguntas pregunta = new Preguntas();

		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.OBTENER_PREGUNTA_POR_ID);

			ps.setInt(1, idPregunta);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				pregunta.setIdpregunta(rs.getInt("idPregunta"));
				pregunta.setDescripcion(rs.getString("descripcion"));
				pregunta.setTipo(rs.getInt("tipo"));

			}

			ps.close();

		} catch (SQLException e) {
			System.out.println("Error en la SQL de editar anuncio");
			System.out.println(e.getMessage());
		}

		desconectar();
		return pregunta;
	}

	/**
	 * Método que extrae el orden de las preguntas del formulario.<br>
	 * 
	 * @param orden
	 * @return
	 */
	public List<Integer> extraerAntiguoOrdenRegistroPreguntas(int orden) {
		conectar();
		List<Integer> listaOrden = new ArrayList<Integer>();
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.EXTRAER_ORDEN_PREGUNTAS);

			ps.setInt(1, orden);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				listaOrden.add(rs.getInt("orden"));
			}

			ps.close();

		} catch (SQLException e) {
			System.out.println("Error al extraer el listado de orden del formulario");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		desconectar();
		return listaOrden;
	}

	/**
	 * Método que cambia el orden de las preguntas del formulario.<br>
	 * 
	 * @param orden
	 * @return
	 */
	public void configurarNuevoOrdenListado(Integer ordenPregunta) {
		conectar();

		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.MODIFICAR_ORDEN_PREGUNTAS);
			ps.setInt(1, ordenPregunta + 1);
			ps.setInt(2, ordenPregunta);
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al modificar el orden del formulario");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		desconectar();
	}

	/**
	 * Método que busca el orden de la pregunta seleccionada por el usuario.<br>
	 * 
	 * @return
	 */
	public Integer buscarOrdenPreguntaDependencia(Integer idpregunta) {
		conectar();
		Integer ordenPregunta = -1;
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.EXTRAER_ORDEN_PREGUNTA);
			ps.setInt(1, idpregunta);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				ordenPregunta = rs.getInt("orden");
			}

		} catch (SQLException e) {
			System.out.println("Error al extraer  el orden de la pregunta");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		desconectar();
		return ordenPregunta;
	}

	/**
	 * Método que busca si una pregunta posee otra dependiente de ella.<br>
	 * 
	 * @param idPregunta
	 * @return
	 */
	@Override
	public Preguntas buscarDependenciaPregunta(Integer idPregunta) {
		conectar();
		Preguntas pregunta = new Preguntas();

		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.BUSCAR_DEPENDENCIA_PREGUNTA);
			ps.setInt(1, idPregunta);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				pregunta.setDescripcion(rs.getString("descripcion"));
				pregunta.setIdpregunta(rs.getInt("idpregunta"));
				pregunta.setTipo(rs.getInt("tipo"));
				pregunta.setNivel(rs.getInt("nivel"));
				pregunta.setOrden(rs.getInt("orden"));
				pregunta.setCajaAdicional(rs.getBoolean("cajaAdicional"));
			}
		} catch (SQLException e) {
			System.out.println("Error al interrogar si la pregunta posee dependencia");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		desconectar();
		return pregunta;
	}

	/**
	 * Método que lista todas las preguntas, de la BBDD, sin tener encuenta
	 * dependencias.<br>
	 * 
	 * @return
	 */
	@Override
	public List<Preguntas> listarTodasPreguntas() {
		conectar();
		List<Preguntas> preguntas = new ArrayList<Preguntas>();
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.LISTAR_TODAS_PREGUNTAS);
			ResultSet resultado = ps.executeQuery();
			while (resultado.next()) {
				Preguntas pregunta = new Preguntas();
				pregunta.setIdpregunta(resultado.getInt("idpregunta"));
				pregunta.setDescripcion(resultado.getString("descripcion"));
				pregunta.setTipo(resultado.getInt("tipo"));
				pregunta.setNivel(resultado.getInt("nivel"));
				pregunta.setOrden(resultado.getInt("orden"));
				preguntas.add(pregunta);

			}
		} catch (SQLException e) {
			System.out.println("Error en la SQL de listar preguntas");
			e.printStackTrace();
		}
		desconectar();
		return preguntas;
	}

	/**
	 * Método que se encarga de buscar la última posicion de orden en el formulario
	 * de preguntas.<br>
	 * 
	 * @return
	 */
	public Integer buscanPosicionFinalFormulario() {
		conectar();

		Integer ultimaPos = new Integer(0);
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(ConstantesSQL.BUSCAR_ULTIMO_ORDEN_FORMULARIO);
			ResultSet resultado = ps.executeQuery();

			if (resultado.next()) {
				ultimaPos = resultado.getInt("orden");
				ultimaPos++;
			}

		} catch (SQLException e) {
			System.out.println("Error al buscar la ultima posicion de orden en el formulario");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		desconectar();

		// incremeto en uno para que coja la siguiente última posición
		return ultimaPos;
	}

	/**
	 * Método que obtiene el listado de todas las preguntas del formulario, para
	 * borrar mediante selección.<br>
	 * 
	 * @return
	 */
	@Override
	public List<Preguntas> obtenerPreguntasBorrar() {
		conectar();
		List<Preguntas> preguntas = new ArrayList<Preguntas>();
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.LISTAR_PREGUNTAS_BORRAR);
			ResultSet resultado = ps.executeQuery();
			while (resultado.next()) {
				Preguntas pregunta = new Preguntas();
				pregunta.setIdpregunta(resultado.getInt("idpregunta"));
				pregunta.setDescripcion(resultado.getString("descripcion"));
				pregunta.setTipo(resultado.getInt("tipo"));
				pregunta.setNivel(resultado.getInt("nivel"));
				pregunta.setOrden(resultado.getInt("orden"));
				pregunta.setCajaAdicional(resultado.getBoolean("cajaAdicional"));
				pregunta.setBloque(resultado.getInt("SI18_ID"));
				preguntas.add(pregunta);

			}
		} catch (SQLException e) {
			System.out.println("Error en la SQL de listar preguntas para borrar");
			e.printStackTrace();
		}
		desconectar();
		return preguntas;
	}
	
	/**
	 * Método que interroga si la pregunta dependiente es la pregunta clave de maternidad.<br>
	 * @param string
	 * @return
	 */
	@Override
	public boolean buscarPreguntaMadre(String string) {
		conectar();
		boolean enc = false;
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.LOCALIZAR_PREGUNTA_MADRE);
			ps.setInt(1, Integer.parseInt(string));
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				enc = true;
			}
			ps.close();
			desconectar();

		} catch (SQLException e) {
			System.out.println("Error al localizar la pregunta madre");
			e.printStackTrace();
		}
		return enc;

	}

}
