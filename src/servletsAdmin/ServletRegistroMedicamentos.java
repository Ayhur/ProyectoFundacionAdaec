package servletsAdmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daosImpl.MedicamentosDAOImpl;

/**
 * Servlet implementation class ServletRegistroMedicamentos
 */
@WebServlet("/ServletRegistroMedicamentos")
public class ServletRegistroMedicamentos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletRegistroMedicamentos() {
		super();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recogo los datos de medicamentos para registrarlos en la base de datos
		int cont = 0;
		List<String> listaMedicamentos = new ArrayList<String>();

		// Recogo toda la lista de medicamentos
		while (true) {
			cont++;
			if (null != request.getParameter("resgistroRespuesta".concat(String.valueOf(cont)))) {
				listaMedicamentos.add(request.getParameter("resgistroRespuesta".concat(String.valueOf(cont))));
			}
			
			else {
				break;
			}
		}
		// Agrego la lista de medicamentos a la BBDD
		MedicamentosDAOImpl medicamentosDAOImpl = new MedicamentosDAOImpl();
		medicamentosDAOImpl.registrarMedicamentos(listaMedicamentos);
		
		// Redirijo a confirmación
		request.getRequestDispatcher("registrarMedicamentosOK.jsp").forward(request, response);

	}

}
