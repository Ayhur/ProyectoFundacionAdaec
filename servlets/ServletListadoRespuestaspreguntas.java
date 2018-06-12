package servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daosImpl.RespuestaspreguntasDAOImpl;


@WebServlet("/ServletListadoRespuestaspreguntas")
public class ServletListadoRespuestaspreguntas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("obteniendo respuestas de preguntas");
		RespuestaspreguntasDAOImpl respuestaspreguntasDAO = new RespuestaspreguntasDAOImpl();
		request.setAttribute("respuestaspreguntas", respuestaspreguntasDAO.obtenerRespuestaspreguntas());
		request.getRequestDispatcher("gestionRespuestaspreguntas.jsp").forward(request,response);
		

	}
}//end class
