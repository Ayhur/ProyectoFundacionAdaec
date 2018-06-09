package daos;

import java.util.List;

import modelos.BarrasDiagrama;

public interface EstadisticasDAO {
	
	
	int clasificarPregunta(int id);
	List<String> valoresDeRespuesta (int id);
	int cantidadDeRepuestasEspecificasEnUnaPregunta( int id, String valoresDistintosDeRespuesta );
	List<BarrasDiagrama> obtenerValoresDiagramaBarras(int parseInt);
	String [] valoresAleatoriosColor (int NColumnas);

}
