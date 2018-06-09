package servletsAdmin;

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

@WebServlet("/ServletGuardarCambios")
public class ServletGuardarCambios extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String preguntaEditada = request.getParameter("guardarPregunta");
		String respuestaEditada = request.getParameter("guardarRespuesta");
		String idPreguntaEditada = request.getParameter("idPreguntaEditada");
		
		//Guardar la edicion de la nueva pregunta
		PreguntasDAO preguntasDAOImpl = new PreguntasDAOImpl();
		preguntasDAOImpl.editarPregunta(preguntaEditada, Integer.parseInt(idPreguntaEditada));
		
		//Si es pregunta radio o check, tiene que tener una respuesta asociada
		//Si es pregunta texto, respuestaEditada recoge null
		if(respuestaEditada != null){
			//Guardar la edicion de la nueva respuesta
			RespuestaspreguntasDAO respuestasPreguntasDAOImpl = new RespuestaspreguntasDAOImpl();
			respuestasPreguntasDAOImpl.editarRespuesta(respuestaEditada, Integer.parseInt(idPreguntaEditada));
		}
		
		request.getRequestDispatcher("editarPreguntaOK.jsp").forward(request, response);
	}

}
