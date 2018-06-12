package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.PreguntasDAO;
import daos.RespuestaspreguntasDAO;
import daosImpl.PreguntasDAOImpl;
import daosImpl.RespuestaspreguntasDAOImpl;

@WebServlet("/ServletCreaFormulario")
public class ServletCreaFormulario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PreguntasDAO preguntasDAO = new PreguntasDAOImpl();
		RespuestaspreguntasDAO respuestasPreguntasDAO = new RespuestaspreguntasDAOImpl();
		
		//Obtengo las preguntas
		request.setAttribute("preguntas", preguntasDAO.obtenerPreguntas());
		request.setAttribute("respuestas", respuestasPreguntasDAO.obtenerRespuestaspreguntas());
		
		request.getRequestDispatcher("formulario.jsp").forward(request, response);
	}

}
