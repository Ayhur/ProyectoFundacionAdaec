package daos;

import java.util.List;

import modelos.Provincia;

public interface PronvinciasDAO {
	
	/**
	 * Método que se encarga de listar las comunidades en función del pais.<br>
	 * @param pais
	 * @return
	 */
	List <Provincia> listarProvincias(int pais);
}
