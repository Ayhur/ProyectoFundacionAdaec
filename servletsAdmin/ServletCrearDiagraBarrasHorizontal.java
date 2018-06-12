package servletsAdmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			System.out.println("Ocurrio una excepcion");
			break;
		}

	}

}
