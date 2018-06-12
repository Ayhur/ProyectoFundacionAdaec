package daos;

import java.util.List;

import modelos.Municipios;

public interface MunicipiosDAO {
	
	List<Municipios> listarMunicipios (int comunidad);
}
