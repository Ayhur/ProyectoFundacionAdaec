package daos;

import java.util.List;

import modelos.Provincia;

public interface PronvinciasDAO {
	
	/**
	 * M�todo que se encarga de listar las comunidades en funci�n del pais.<br>
	 * @param pais
	 * @return
	 */
	List <Provincia> listarProvincias(int pais);
}
