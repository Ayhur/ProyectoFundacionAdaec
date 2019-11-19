package daosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.ComunidadesAutonomasDAO;
import daos.ConstantesSQL;
import daos.GenericDAO;
import daos.PronvinciasDAO;
import modelos.ComunidadesAutonomas;
import modelos.Provincia;

public class ProvinciasDAOImpl extends GenericDAO implements PronvinciasDAO {
	
	/**
	 * Método que se encarga de listar las comunidades en función del pais.<br>
	 * @param pais
	 * @return
	 */
	@Override
	public List<Provincia> listarProvincias(int pais) {

		conectar();
		List<Provincia> comunidades = new ArrayList<Provincia>();

		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.LISTAR_COMUNIDADES);
			ps.setInt(1, pais);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Provincia comunidad = new Provincia(rs.getInt("id"), rs.getString("provincia"));
				comunidades.add(comunidad);
			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("Error al localizar las comunidades en la sql");
			e.printStackTrace();
		}

		desconectar();

		return comunidades;
	}
	
	
}
