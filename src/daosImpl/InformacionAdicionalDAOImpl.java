package daosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.ConstantesSQL;
import daos.GenericDAO;
import daos.InformacionAdicionalDAO;

public class InformacionAdicionalDAOImpl extends GenericDAO implements InformacionAdicionalDAO {
	
	/**
	 * Metodo que retorna un listado con los bloques de pregunta existentes.<br>
	 * @return
	 */
	@Override
	public List<String> listarBloquePreguntas() {
		conectar();
		List<String> listaBloque = new ArrayList<String>();
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.LISTAR_BLOQUE_PREGUNTAS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				listaBloque.add(rs.getString("SI18_NOMBRE_BLOQUE"));
			}
			
			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("Error al listar Bloque de preguntas");
			e.printStackTrace();
		}

		desconectar();
		return listaBloque;
	}
	

	/**
	 * Método que busca el bloque de preguntas traspasado como parámetro.<br>
	 * @param bloque
	 * @return
	 */
	@Override
	public int buscarBloquePreguntas(String bloque) {
		conectar();
		int idBloque = 0;
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.BUSCAR_BLOQUE_PREGUNTA);
			ps.setString(1, bloque);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				idBloque = rs.getInt("SI18_ID");
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			System.out.println("Error al buscar el bloque al que pertenece la pregunta");
			e.printStackTrace();
		}
		
		desconectar();
		return idBloque;
	}

}
