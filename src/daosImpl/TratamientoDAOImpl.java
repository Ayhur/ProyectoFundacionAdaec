package daosImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.ConstantesSQL;
import daos.GenericDAO;
import daos.TratamientoDAO;
import modelos.Tratamiento;

public class TratamientoDAOImpl extends GenericDAO implements TratamientoDAO {

	/**
	 * Método que registra los tratamientos que ha tomado un usuario.<br>
	 * 
	 * @param listaTratamientos
	 * @param idRegistroFormulario
	 * @param erroresLog
	 */
	@Override
	public void registrarRespuestaTratamientos(List<Tratamiento> listaTratamientos, int idRegistroFormulario,
			ArrayList<Integer> erroresLog) {
		conectar();

		try {

			PreparedStatement ps = con.prepareStatement(ConstantesSQL.REGISTRAR_TRATAMIENTOS);
			System.out.println("Empezando a insertar los tratamientos");
			for (Tratamiento tratamiento : listaTratamientos) {
				ps.setInt(1, idRegistroFormulario);
				ps.setInt(2, tratamiento.getIdMedicamento());
				ps.setBoolean(3, tratamiento.isFueBien());
				ps.execute();
			}

			ps.close();
			System.out.println("Registrados todos los tratamientos");

		} catch (SQLException e) {
			System.out.println("Error al registrar la lista de tratamientos");
			erroresLog.add(106);
			e.printStackTrace();
		}

		desconectar();
	}

}
