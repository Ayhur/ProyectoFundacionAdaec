package daosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.ConstantesSQL;
import daos.GenericDAO;
import daos.MunicipiosDAO;
import modelos.Municipios;

public class MunicipiosDAOImpl extends GenericDAO implements MunicipiosDAO {

	@Override
	public List<Municipios> listarMunicipios(int comunidad) {

		conectar();

		List<Municipios> municipios = new ArrayList<Municipios>();

		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.LISTAR_MUNICIPIOS);
			ps.setInt(1, comunidad);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Municipios municipio = new Municipios(rs.getInt("id"), rs.getString("municipio"));
				municipios.add(municipio);
			}

		} catch (SQLException e) {
			System.out.println("Error al obtener los municipios en la SQL");
			e.printStackTrace();
		}

		desconectar();
		return municipios;
	}

}
