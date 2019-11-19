package daos;

import java.util.ArrayList;

import modelos.Usuario;

public interface UsuariosDAO {
	
	/**
	 * M�todo que identifica el usuario al logearse.<br>
	 * @param user
	 * @param pass
	 * @return
	 */
	Usuario identificarUsuario(Usuario usuarioDto);
	
	/**
	 * M�todo que registra un nuevo usuario en la BBDD.<br>
	 * @param usuario
	 */
	void registrarUsuario(Usuario usuario);
	
	/**
	 * Falta descripci�n.<br>
	 * @param user
	 * @return
	 */
	Usuario extraccionDatosPersonales(int user);
	
	/**
	 * M�todo que genera un usuari virtual en la SI06, con los datos de interes necesarios para
	 * crear estadisticas pero sin los datos personales que pueda vincularlo a ese registro de formulario
	 * (vease protecci�n de datos y registro anonimos).<br>
	 * @param usuarioRVP
	 * @param erroresLog
	 * @return
	 */
	int creacionRegistroVirtualPersonal(Usuario usuarioRVP, ArrayList<Integer> erroresLog);
	
	/**
	 * M�todo que actualiza el campo realizado de la tabla usuarios.<br>
	 * @param usuarioLogeado 
	 * @param erroresLog 
	 */
	void actualizarRealizado(Integer usuarioLogeado, ArrayList<Integer> erroresLog);
	
}
