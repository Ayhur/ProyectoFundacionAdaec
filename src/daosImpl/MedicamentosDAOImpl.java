package daosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.ConstantesSQL;
import daos.GenericDAO;
import daos.MedicamentosDAO;
import modelos.Medicamento;

/**
 * @author Ayhur-Port
 */
public class MedicamentosDAOImpl extends GenericDAO implements MedicamentosDAO {

	/**
	 * Método que registra los nuevos medicamentos en la BBDD.<br>
	 * 
	 * @param listaMedicamentos
	 */
	@Override
	public void registrarMedicamentos(List<String> listaMedicamentos) {
		conectar();

		try {

			for (String medicamento : listaMedicamentos) {
				PreparedStatement ps = con.prepareStatement(ConstantesSQL.REGISTRAR_MEDICAMENTOS);
				ps.setString(1, medicamento);
				ps.execute();
			}

		} catch (SQLException e) {
			System.out.println("Error SQL registrar medicamentos");
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Los medicamentos fueron agregados a la BBDD");
		}
		desconectar();

	}

	/**
	 * Método que trae una lista con todos los medicamentos.<br>
	 * 
	 * @return
	 */
	@Override
	public List<Medicamento> listarMedicamentos() {
		conectar();

		List<Medicamento> medicamentos = new ArrayList<Medicamento>();

		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.LISTAR_MEDICAMENTOS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Medicamento medicamento = new Medicamento(rs.getInt("SI19_ID"), rs.getString("SI19_NOMBRE"));
				medicamentos.add(medicamento);

			}

		} catch (SQLException e) {
			System.out.println("Error al listas los medicamentos");
			e.printStackTrace();
		}

		desconectar();
		return medicamentos;
	}

	/**
	 * Método que registra las respuestas asociadas a la tabla medicamentos en el formulario.<br>
	 * @param listaMedRespuesta
	 */
	@Override
	public void agregarMedicamentosRespuesta(List<Medicamento> listaMedRespuesta) {
		
	}

}
