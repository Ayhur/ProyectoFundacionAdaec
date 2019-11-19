package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import daos.PreguntasDAO;
import daos.RespuestaspreguntasDAO;
import daosImpl.PreguntasDAOImpl;
import daosImpl.RespuestaspreguntasDAOImpl;
import modelos.PreguntaRespuestasMaternidad;
import modelos.Preguntas;
import modelos.Respuestaspreguntas;

/**
 * Servlet implementation class ServletBuscarDependenciasFormulario
 */
@WebServlet("/ServletBuscarDependenciasFormulario")
public class ServletBuscarDependenciasFormulario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBuscarDependenciasFormulario() {
        super();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String preguntaformulario = request.getParameter("selectPregunta");
		String [] fraccionarPregunta = preguntaformulario.split("_");
		PreguntasDAO pregunta = new PreguntasDAOImpl();
		
		if(pregunta.buscarPreguntaMadre(fraccionarPregunta[1])) {
			retornarBloqueMaternidad(response);
		}
		
		else {
			retornarPreguntaDependiente(response, fraccionarPregunta, pregunta);
		}
	}

	/**
	 * Método que retorna las preguntas del bloque de maternidad.<br>
	 * @param response
	 * @throws IOException
	 */
	private void retornarBloqueMaternidad(HttpServletResponse response) throws IOException {
		List<PreguntaRespuestasMaternidad> listaMaternidad = new ArrayList<>();
		listaMaternidad = new RespuestaspreguntasDAOImpl().extraerBloqueMaternidad();
		Gson gson = new Gson();
		String json = gson.toJson(listaMaternidad);
		System.out.println("json de maternidad");
		System.out.println(json);
		
		json = transformacionDeLetrasAcentuadas(json);
		// lo envio de vuelta a la jsp
		response.getWriter().print(json);
	}

	/**
	 * Método que retorna al AJAX una pregunta dependiente.<br>
	 * @param response
	 * @param fraccionarPregunta
	 * @param pregunta
	 * @throws IOException
	 */
	private void retornarPreguntaDependiente(HttpServletResponse response, String[] fraccionarPregunta,
			PreguntasDAO pregunta) throws IOException {
		RespuestaspreguntasDAO respuestasPregunta = new RespuestaspreguntasDAOImpl();
		Respuestaspreguntas respuestas = respuestasPregunta.obtenerRespuestaPorId(Integer.parseInt(fraccionarPregunta[1]));
		
		Gson gson = new Gson();
		String json = gson.toJson(pregunta.buscarDependenciaPregunta(Integer.parseInt(fraccionarPregunta[1])));
		String json2 = gson.toJson(respuestas);
		System.out.println("json2");
		System.out.println(json2);
		
		// Esto es un parche de mierda para evitar que salga un fallo cuando se busca si la respuesta SI en una radiobutton
		// poseeia dependencia o tenia alguna dependencia de pregunta. Si no la tenia el json2 estaba vacio y claro ejecutaba
		// un codigo de concat que petaba porque no existia posiciones de charAt al no haber una cadena de texto
		// se debería hacer de otra manera mejor, como por ejemplo que si no existe dependencia se retorne algun marcador
		// que luego se use el marcador para saltarse esta parte, en vez de validar si viene vacio json2
		if(!"{}".equals(json2)) {
			json = agregacionDeRespuestasJson(json, json2);
		}
		
		System.out.println("esto es lo que tra el objeto json:");
		System.out.println(json);
		json = transformacionDeLetrasAcentuadas(json);
		// envio devuelta la respuesta de AJAX
		response.getWriter().print(json);
	}

	/**
	 * Método que agrega a la cadena JSON las respuestas tambien de la pregunta.<br>
	 * @param json
	 * @param respuestas 
	 * @return
	 */
	private String agregacionDeRespuestasJson(String json, String json2) {
		
		// ejemplo de lo que contiene stringCortadoJson: {"idpregunta":67,"descripcion":"dependo de la numero 2","tipo":2,"nivel":66,"orden":1
		String stringCortadoJson = json.substring(0, json.length()-1);
		
		// // ejemplo de lo que contiene stringCortadoJson2: "descripcion":"Yes-No"
		String stringCortadoJson2 = json2.substring(1, json2.length()-1);
		
		StringBuffer arregloCortadoJson2 = new StringBuffer(stringCortadoJson2);
		arregloCortadoJson2.insert(12, "Respuestas");
		
		// ejemplo de lo que devuelve: {"idpregunta":37,"descripcion":"Prueba 3","tipo":2,"nivel":4,"orden":6,"descripcionRespuestas":"si-no"}
		return stringCortadoJson.concat(",").concat(String.valueOf(arregloCortadoJson2)).concat("}");
	}
	
	/**
	 * Método que reemplaza las letras acentuadas.<br>
	 * @param json
	 * @return
	 */
	private String transformacionDeLetrasAcentuadas(String json) {
		json = json.replaceAll("í", "&iacute;");
		json = json.replaceAll("á", "&aacute;");
		json = json.replaceAll("é", "&eacute;");
		json = json.replaceAll("ó", "&oacute;");
		json = json.replaceAll("ú", "&uacute;");
		json = json.replaceAll("ñ", "&ntilde;");
		json = json.replaceAll("¿", "&iquest;");
		// fin transformacion de las letras acentuadas
		return json;
	}

}
