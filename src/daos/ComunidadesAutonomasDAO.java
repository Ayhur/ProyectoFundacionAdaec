package daos;

import java.util.List;

import modelos.ComunidadesAutonomas;

public interface ComunidadesAutonomasDAO {
	
	List <ComunidadesAutonomas> listarComunidades(int pais);
}
