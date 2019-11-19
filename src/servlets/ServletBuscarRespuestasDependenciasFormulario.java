package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import daos.RespuestaspreguntasDAO;
import daosImpl.RespuestaspreguntasDAOImpl;
import modelos.Respuestaspreguntas;

/**
 * Servlet implementation class ServletBuscarRespuestasDependenciasFormulario
 */
@WebServlet("/ServletBuscarRespuestasDependenciasFormulario")
public class ServletBuscarRespuestasDependenciasFormulario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBuscarRespuestasDependenciasFormulario() {
        super();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RespuestaspreguntasDAO respuestasPregunta = new RespuestaspreguntasDAOImpl();
		Respuestaspreguntas listaRespuestas = respuestasPregunta.obtenerRespuestaPorId(Integer.parseInt(request.getParameter("pregunta")));
		
		Gson gson = new Gson();
		String json = gson.toJson(listaRespuestas);
		// envio devuelta la respuesta de AJAX
		response.getWriter().print(json);
		
	}

}
