package daosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.ComunidadesAutonomasDAO;
import daos.ConstantesSQL;
import daos.GenericDAO;
import modelos.ComunidadesAutonomas;

public class ComunidadesAutonomasDAOImpl extends GenericDAO implements ComunidadesAutonomasDAO {

	@Override
	public List<ComunidadesAutonomas> listarComunidades(int pais) {

		conectar();
		List<ComunidadesAutonomas> comunidades = new ArrayList<ComunidadesAutonomas>();

		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.LISTAR_COMUNIDADES);
			ps.setInt(1, pais);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ComunidadesAutonomas comunidad = new ComunidadesAutonomas(rs.getInt("id"), rs.getString("comunidad"));
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
