package daos;

import java.util.List;

import modelos.Municipios;

public interface MunicipiosDAO {
	
	/**
	 * M�todo que se encarga de listar los municipios pertenecientes a una comunidad.<br>
	 * @param comunidad
	 * @return
	 */
	List<Municipios> listarMunicipios (int comunidad);
}
