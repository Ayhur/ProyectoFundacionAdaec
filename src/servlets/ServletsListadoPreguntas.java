package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.PreguntasDAO;
import daosImpl.PreguntasDAOImpl;

@WebServlet("/ServletsListadoPreguntas")
public class ServletsListadoPreguntas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("obteniendo preguntas");

		PreguntasDAO preguntasDAO = new PreguntasDAOImpl();
		request.setAttribute("preguntas", preguntasDAO.obtenerPreguntas());
		request.getRequestDispatcher("borrarPreguntas.jsp").forward(request,
				response);

	}
}
