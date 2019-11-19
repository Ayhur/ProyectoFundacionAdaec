package servletsAdmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import daos.EstadisticasDAO;
import daosImpl.EstadisticasDAOImpl;
import modelos.BarrasDiagrama;

/**
 * Servlet implementation class ServletCrearDiagraBarrasHorizontal
 */
@WebServlet("/ServletCrearDiagraBarrasHorizontal")
public class ServletCrearDiagraBarrasHorizontal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idpregunta = request.getParameter("selectPregunta");
		System.out.println(idpregunta + "  <-----");

		EstadisticasDAO daoEstadisticas = new EstadisticasDAOImpl();
		int tipo = daoEstadisticas.clasificarPregunta(Integer.parseInt(idpregunta));
		
		// Creamos la consulta SQL avanzada
		String consultaAvanzada = crearConsultaBusqueda(request,idpregunta);
		
		
		switch (tipo) {
		case 1: /* para las preguntas de tipo text */
			
			break;

		case 2: /* para las preguntas de tipo radioButton */

			List<BarrasDiagrama> datosDiagramaBarras = new ArrayList<BarrasDiagrama>();
			datosDiagramaBarras = daoEstadisticas.obtenerValoresDiagramaBarras(Integer.parseInt(idpregunta));

			/* transformar para javascript */

			/*
			 * Gson gson = new Gson(); gson.toJson(datosDiagramaBarras);
			 */

			Gson gson = new Gson();
			String json = gson.toJson(datosDiagramaBarras);

			/* FIN TRANSFORMACION JS */
			
			response.getWriter().print(json);
			
			/*request.setAttribute("valoresXrespuestas", json);
			request.getRequestDispatcher("estadisticasInfo.jsp").forward(request, response);*/

			break;

		case 3:
			/* para las preguntas de checkbox */
			
			List<BarrasDiagrama> datosDiagramaBarras2 = new ArrayList<BarrasDiagrama>();
			datosDiagramaBarras2 = daoEstadisticas.obtenerValoresDiagramaBarras(Integer.parseInt(idpregunta));

			/* transformar para javascript */

			/*
			 * Gson gson = new Gson(); gson.toJson(datosDiagramaBarras);
			 */

			Gson gson2 = new Gson();
			String json2 = gson2.toJson(datosDiagramaBarras2);

			/* FIN TRANSFORMACION JS */
			
			response.getWriter().print(json2);
			

			break;
		default:
			System.out.println("Ocurrio una excepcion en el ServletCrearDiagrama");
			break;
		}

	}
	
	public String crearConsultaBusqueda(HttpServletRequest request, String idpregunta) {
		
		/*
		 * Select Count(r.respuesta) 
			from radiorespuestas r
				inner join preguntas p on p.idpregunta = r.idpregunta
			    inner join si12_form_est_radiorespuesta si12 on si12.SI12_ID_RADIORESPUESTA = r.id
			    inner join si06_form_datos_estadist si06 on si06.SI06_ID_FORM_DATOS_ESTADIST = si12.SI12_ID_FORM_DATOS_ESTADIST
			where 1 = 1 
			and r.idpregunta = 3
			and r.respuesta like 'SI'
			and si06.SI06_MUNICIPIO = 99
			and si06.SI06_PAIS like 'España'
		 */
		String consulta = "Select Count(r.respuesta) \r\n" + 
				"			from radiorespuestas r\r\n" + 
				"				inner join preguntas p on p.idpregunta = r.idpregunta\r\n" + 
				"			    inner join si12_form_est_radiorespuesta si12 on si12.SI12_ID_RADIORESPUESTA = r.id\r\n" + 
				"			    inner join si06_form_datos_estadist si06 on si06.SI06_ID_FORM_DATOS_ESTADIST = si12.SI12_ID_FORM_DATOS_ESTADIST\r\n" + 
				"			where 1 = 1 ";
		consulta.concat("and r.idpregunta = ").concat(idpregunta);
		
		if(null != request.getParameter("selectMunicipios")) {
			consulta.concat("and si06.SI06_MUNICIPIO = ").concat(request.getParameter("localidad"));
		}

		if(null != request.getParameter("selectComunidades")) {
			//tengo que diseñar subconsulta que extraiga la comunidad
			
		}

		if(null != request.getParameter("selectPais")) {
			consulta.concat("and si06.SI06_PAIS like ").concat(request.getParameter("pais"));
		}

		if(null != request.getParameter("edadInicial")) {
			Calendar calendar = Calendar.getInstance();
			calendar.getTime();
			calendar.add(Calendar.YEAR, Integer.parseInt(request.getParameter("edadInicial"))*(-1));
			
		}

		if(null != request.getParameter("edadFinal")) {
			
		}

		if(null != request.getParameter("fechaDesde")) {
			
		}

		if(null != request.getParameter("fechaHASta")) {
			
		}
		
		return null;
		
		
		
		
	}

}
