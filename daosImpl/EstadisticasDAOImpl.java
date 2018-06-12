package daosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import daos.ConstantesSQL;
import daos.EstadisticasDAO;
import daos.GenericDAO;
import modelos.BarrasDiagrama;

public class EstadisticasDAOImpl extends GenericDAO implements EstadisticasDAO {

	@Override
	public int clasificarPregunta(int id) {

		conectar();
		int tipo = 0;

		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.IDENTIFICAR_TIPO_PREGUNTA);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				tipo = rs.getInt("tipo");
			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("Error al localizar el tipo de pregunta en la base de datos");
			e.printStackTrace();
		}

		desconectar();
		return tipo;
	}

	@Override
	public int cantidadDeRepuestasEspecificasEnUnaPregunta(int id, String valorDistinto) {

		conectar();
		// List<Integer> cantidadPorTipoRespuesta = new ArrayList<Integer>();
		int recuentoDeRespuestas = 0;

		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.CONTAR_RESPUESTAS_IGUALES);
			ps.setInt(1, id);
			ps.setString(2, valorDistinto);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				recuentoDeRespuestas = rs.getInt(1); /* utilizo posicion de columna porque solo devuelve una */
			}

		} catch (SQLException e) {
			System.out.println("Error al buscar el numero de respuestas dada por agrupacion de respuestas [506]");
			e.printStackTrace();
		}

		desconectar();
		return recuentoDeRespuestas;
	}

	@Override
	public List<String> valoresDeRespuesta(int id) {

		conectar();
		List<String> cantidadPorTipoRespuesta = new ArrayList<String>();

		try {
			PreparedStatement ps = con.prepareStatement(ConstantesSQL.EXTRAER_VALORES_DISTINTOS_RESPUESTA);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				cantidadPorTipoRespuesta.add(rs.getString("respuesta"));
			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("Error al obtener los distintos valores de respuesta");
			e.printStackTrace();
		}

		desconectar();
		return cantidadPorTipoRespuesta;
	}

	@Override
	public List<BarrasDiagrama> obtenerValoresDiagramaBarras(int idpregunta) {

		List<BarrasDiagrama> datosDiagramaBarras = new ArrayList<BarrasDiagrama>();
		List<String> valoresDistintosDeRespuesta = valoresDeRespuesta(idpregunta);
		
		int numeroDeBarras = valoresDistintosDeRespuesta.size();// numero de barras que tenemos en el diagrama
		int cont = 0;
		String [] valoresColorBarras = valoresAleatoriosColor(numeroDeBarras);
		

		for (String valorDistinto : valoresDistintosDeRespuesta) {
			BarrasDiagrama barraDiagrama = new BarrasDiagrama();
			barraDiagrama.setEtiquetasBarra(valorDistinto);
			barraDiagrama.setValorEetiquetasBarra(cantidadDeRepuestasEspecificasEnUnaPregunta(idpregunta,
					valorDistinto));
			barraDiagrama.setValorColorFondoBarraYContorno(valoresColorBarras[cont]);
			barraDiagrama.setSolidedColorFondoBarra(0.2);
			barraDiagrama.setSolidedColorBorderBarra(1.0);
			datosDiagramaBarras.add(barraDiagrama);
			cont++; //incremento para recoger en el siguiente ciclo el siguiente color
		}

		return datosDiagramaBarras;
	}

	@Override
	public  String [] valoresAleatoriosColor(int numeroDeBarras) {

		//List<String> valoresColorBarras = new ArrayList<String>();
		String [] valoresColorBarras = new String[numeroDeBarras];
		Random r = new Random();
		//inicializo posiciones para enviar el null y nos de error en la comparativa de EQUALS
		for(int i = 0; i<valoresColorBarras.length; i++) {
			valoresColorBarras[i] = "";
		}

		/*
		 * genero un for doble, en el que recorro tantas veces como encabezados de
		 * columna en el diagrama tenga y para cada uno de ellos recorro el segundo
		 * bucle generando 3 rangos aleatorios de valores numericos que son usados para
		 * los colores RGB y son concatenados en una cadena ambos con sus respectivas
		 * comas para introducir en el javascript (dado que la parte donde se introducen
		 * va entre comillas simples lo cual indica que se introduce como "cadena" de
		 * texto los colores en javascript
		 */

		for (int i = 0; i < numeroDeBarras; i++) {
			String colorCompleto = "";
			for (int j = 0; j < 3; j++) {
				int numero = r.nextInt(255);
				colorCompleto += numero + ",";
			}

			boolean enc = compararColorNoRepetible(colorCompleto, valoresColorBarras);

			if (!enc) {
				valoresColorBarras[i] = colorCompleto;
			} else {
				i--;
			}
		}
		return valoresColorBarras;
	}

	private boolean compararColorNoRepetible(String colorCompletoGenerado, String[] valoresColorBarras) {

		boolean enc = false;

		if (valoresColorBarras[0].equals("")) { /* si es la primera vez no tiene nada la primera posicion */
			enc = false;
		} else { /* sino es la primera vez ya comparamos el list entero */
			
			for(int i = 0; i<valoresColorBarras.length&&!enc; i++) {
				if (valoresColorBarras[i].equals(colorCompletoGenerado)) {
					enc = true;
				}
			}
			
			/*for (String color : valoresColorBarras) {
				if (color.equals(colorCompletoGenerado)) {
					enc = true;
					break;
				}
			}*/
		}
		return enc;
	}
}
