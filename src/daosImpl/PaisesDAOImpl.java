package daosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.ConstantesSQL;
import daos.GenericDAO;
import daos.PaisesDAO;
import modelos.Pais;

public class PaisesDAOImpl extends GenericDAO implements PaisesDAO {

	@Override
	public List<Pais> listarPaises() {

		conectar();
		List<Pais> paises = new ArrayList<Pais>();
		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.LISTAR_PAISES);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Pais p = new Pais();
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				paises.add(p);
				

			}
		} catch (SQLException e) {
			System.out.println("Error la buscar paises");
			e.printStackTrace();
		}

		desconectar();
		return paises;
	}

}
