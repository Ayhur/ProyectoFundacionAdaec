package servletsAdmin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletCargaDeDatosMedicamentos
 */
@WebServlet("/ServletCargaDeDatosMedicamentos")
public class ServletCargaDeDatosMedicamentos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCargaDeDatosMedicamentos() {
		super();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Envio de datos y redirección web
		// request.setAttribute("preguntas", preguntaAcotadas);
		request.getRequestDispatcher("registrarMedicamentos.jsp").forward(request, response);

	}

}
