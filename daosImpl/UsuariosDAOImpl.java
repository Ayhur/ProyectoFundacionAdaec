package daosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import daos.ConstantesSQL;
import daos.GenericDAO;
import daos.UsuariosDAO;
import modelos.Usuario;

public class UsuariosDAOImpl extends GenericDAO implements UsuariosDAO {

	@Override
	public int identificarUsuario(String user, String pass) {
		conectar();

		int id = -1;

		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.IDENFITICAR_USUARIO);
			System.out.println("user: " + user + " pass: " + pass + " " + ConstantesSQL.IDENFITICAR_USUARIO);

			ps.setString(1, user);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				id = rs.getInt("id");
			} else {
				ps = con.prepareStatement(ConstantesSQL.IDENFITICAR_USUARIO_ADMIN);
				ps.setString(1, user);
				ps.setString(2, pass);
				rs = ps.executeQuery();

				if (rs.next()) {
					id = 0;
				}
			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("Error al identificar usuario del login");
			e.printStackTrace();
		}

		desconectar();
		return id;
	}

	@Override
	public void registrarUsuario(Usuario usuario) {

		conectar();

		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.REGISTRAR_USUARIO);
			ps.setString(1, usuario.getUsuario());
			ps.setString(2, usuario.getpassword());
			ps.setString(3, usuario.getNombre());
			ps.setString(4, usuario.getApellidos());

			/** transformacion a util.sql.Date ****/
			java.sql.Date sqlDate = java.sql.Date.valueOf(String.valueOf(usuario.getFechaNacimiento()));
			ps.setDate(5, sqlDate);
			/*********** FIN transformacion ******/
			ps.setString(6, usuario.getEmail());
			ps.setString(7, usuario.getDni());
			ps.setInt(8, usuario.getMunicipio());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Error al insertar usuario");
			e.printStackTrace();
		}

		desconectar();
	}

	@Override
	public Usuario extraccionDatosPersonales(int user) {
		conectar();
		Usuario usuario = new Usuario();
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.DATOS_ESTADISTICOS_PEROSNALES);
			ps.setInt(1, user);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				usuario.setFechaNacimiento(rs.getString("fechaNacimiento"));
				usuario.setMunicipio(rs.getInt("municipio"));
			}
			
		} catch (SQLException e) {
			System.out.println("Error al virtualizar la info del usuario para el registro del formulario");
			e.printStackTrace();
		}
		
		desconectar();
		
		
		return usuario;
	}

	@Override
	public int creacionRegistroVirtualPersonal(Usuario usuarioRVP) {
		conectar();
		int id_SI06_Generada = -1;
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.CREAR_RVP);
			ps.setInt(1, usuarioRVP.getMunicipio());
			ps.setString(2, usuarioRVP.getFechaNacimiento());
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
		}
		
		desconectar();
		return id_SI06_Generada;
	}

}
