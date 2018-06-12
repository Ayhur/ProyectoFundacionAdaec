package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import daos.ComunidadesAutonomasDAO;
import daos.MunicipiosDAO;
import daos.PaisesDAO;
import daosImpl.ComunidadesAutonomasDAOImpl;
import daosImpl.MunicipiosDAOImpl;
import daosImpl.PaisesDAOImpl;

@WebServlet("/ServletCargaDeElementosRegistro")
public class ServletCargaDeElementosRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * Para la recarga de comunidades Autonomas una ve seleccionamos el pais
		 */
		if (request.getParameter("paisSeleccionado") != null) {
			
			String paisSeleccionado = request.getParameter("paisSeleccionado");
			
			// llamo a ComunidadesAutonomasDAOImpl para obtener mediante el pais los datos
			// requeridos para el seclect COmunidades

			ComunidadesAutonomasDAO comunidades = new ComunidadesAutonomasDAOImpl();

			Gson gson = new Gson();
			String json = gson.toJson(comunidades.listarComunidades(Integer.parseInt(paisSeleccionado)));
			// transformacion de las letras acentuadas
			json = transformacionDeLetrasAcentuadas(json);
			response.getWriter().print(json);

		} else if (request.getParameter("comunidadSeleccionada") != null) {//
			
			String comunidadSeleccionada = request.getParameter("comunidadSeleccionada");

			MunicipiosDAO municipios = new MunicipiosDAOImpl();

			Gson gson = new Gson();
			String json = gson.toJson(municipios.listarMunicipios(Integer.parseInt(comunidadSeleccionada)));
			// transformacion de las letras acentuadas
			json = transformacionDeLetrasAcentuadas(json);
			response.getWriter().print(json);

		} else { // la primera carga de datos en el jsp para el campo PAIS:

			/*
			 * Instanciamos la implementacion de paises para extraer un listado de los
			 * mismos y lo enviamos al jsp de registro para rellenar el campo selector Pais
			 */
			PaisesDAO daoPaises = new PaisesDAOImpl();
			request.setAttribute("paises", daoPaises.listarPaises());
			request.getRequestDispatcher("registroUsuario.jsp").forward(request, response);
		}
	}

	private String transformacionDeLetrasAcentuadas(String json) {
		json = json.replaceAll("í", "&iacute;");
		json = json.replaceAll("á", "&aacute;");
		json = json.replaceAll("é", "&eacute;");
		json = json.replaceAll("ó", "&oacute;");
		json = json.replaceAll("ú", "&uacute;");
		json = json.replaceAll("ñ", "&ntilde;");
		// fin transformacion de las letras acentuadas
		return json;
	}

}
