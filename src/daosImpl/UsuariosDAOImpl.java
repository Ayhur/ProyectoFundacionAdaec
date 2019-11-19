package daosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import daos.ConstantesSQL;
import daos.GenericDAO;
import daos.UsuariosDAO;
import modelos.Usuario;

public class UsuariosDAOImpl extends GenericDAO implements UsuariosDAO {
	
	
	/**
	 * Método que identifica el usuario al logearse.<br>
	 * recoge el usuario de las tablas usuarios y administradores
	 * sino esta en uno busca en la otra.		 
	 * @param user
	 * @param pass
	 * @return
	 */
	@Override
	public Usuario identificarUsuario(Usuario usuarioDto) {
		conectar();
		
		
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.IDENFITICAR_USUARIO);
			System.out.println("user: " + usuarioDto.getUsuario() + " pass: " + usuarioDto.getPassword() + " " + ConstantesSQL.IDENFITICAR_USUARIO);

			ps.setString(1, usuarioDto.getUsuario());
			ps.setString(2, usuarioDto.getPassword());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				usuarioDto.setId(rs.getInt("id"));
				usuarioDto.setRealizado(rs.getInt("realizado"));
			} else {
				ps = con.prepareStatement(ConstantesSQL.IDENFITICAR_USUARIO_ADMIN);
				ps.setString(1, usuarioDto.getUsuario());
				ps.setString(2, usuarioDto.getPassword());
				rs = ps.executeQuery();

				if (rs.next()) {
					// transferimos id 0 para marcar administrador
					usuarioDto.setId(new Integer(0));
				}
			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("Error al identificar usuario del login");
			e.printStackTrace();
		}

		desconectar();
		return usuarioDto;
	}
	
	/**
	 * Método que registra un nuevo usuario en la BBDD.<br>
	 * @param usuario
	 */
	@Override
	public void registrarUsuario(Usuario usuario) {

		conectar();

		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.REGISTRAR_USUARIO);
			ps.setString(1, usuario.getUsuario());
			ps.setString(2, usuario.getPassword());
			ps.setString(3, usuario.getNombre());
			ps.setString(4, usuario.getApellidos());

			/** transformacion a util.sql.Date ****/
			java.sql.Date sqlDate = java.sql.Date.valueOf(String.valueOf(usuario.getFechaNacimiento()));
			ps.setDate(5, sqlDate);
			/*********** FIN transformacion ******/
			ps.setString(6, usuario.getEmail());
			ps.setString(7, usuario.getDni());
			ps.setInt(8, usuario.getPais());
			ps.setInt(9, usuario.getMunicipio());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Error al insertar usuario");
			e.printStackTrace();
		}

		desconectar();
	}
	
	/**
	 * Falta descripción.<br>
	 * @param user
	 * @return
	 */
	@Override
	public Usuario extraccionDatosPersonales(int user) {
		
		conectar();
		Usuario usuario = new Usuario();
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.DATOS_ESTADISTICOS_PEROSNALES);
			ps.setInt(1, user);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				usuario.setFechaNacimiento(rs.getString("fechaNacimiento"));
				usuario.setPais(rs.getInt("pais"));
				usuario.setMunicipio(rs.getInt("municipio"));
				
			}
			
		} catch (SQLException e) {
			System.out.println("Error al virtualizar la info del usuario para el registro del formulario");
			e.printStackTrace();
		}
		
		desconectar();
		return usuario;
	}
	
	/**
	 * Método que genera un usuari virtual en la SI06, con los datos de interes necesarios para
	 * crear estadisticas pero sin los datos personales que pueda vincularlo a ese registro de formulario
	 * (vease protección de datos y registro anonimos).<br>
	 * @param usuarioRVP
	 * @param erroresLog
	 * @return
	 */
	@Override
	public int creacionRegistroVirtualPersonal(Usuario usuarioRVP, ArrayList<Integer> erroresLog) {
		conectar();
		int id_SI06_Generada = -1;
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.CREAR_RVP);
			ps.setInt(1, usuarioRVP.getMunicipio());
			ps.setString(2, usuarioRVP.getFechaNacimiento());
			ps.setInt(3, usuarioRVP.getPais());
			id_SI06_Generada = ps.executeUpdate();
			ps = con.prepareStatement(ConstantesSQL.EXTRAER_ID_REGISTRADO);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				id_SI06_Generada = rs.getInt(1);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Error al insertar el usuarioRVP");
			e.printStackTrace();
			erroresLog.add(103);
		}
		
		desconectar();
		return id_SI06_Generada;
	}
	
	/**
	 * Método que actualiza el campo realizado de la tabla usuarios.<br>
	 * @param usuarioLogeado 
	 */
	@Override
	public void actualizarRealizado(Integer usuarioLogeado, ArrayList<Integer> erroresLog) {
		
		conectar();
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.ACTUALIZAR_REALIZADO_USUARIO);
			ps.setInt(1, 1);
			ps.setInt(2, usuarioLogeado);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Error al actualizar el campo Realizado");
			e.printStackTrace();
			erroresLog.add(104);
		}
		
		desconectar();
	}

}
