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

@WebServlet("/ServletBorrarPreguntas")
public class ServletBorrarPreguntas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int tipoPregunta = -1;
		String idpregunta = request.getParameter("idpregunta");
		System.out.println("borrar id pregunta: " + idpregunta);
	
		System.out.println("borrar preguntas por idpregunta: " + idpregunta);
		PreguntasDAO preguntasDAOImpl = new PreguntasDAOImpl();
	
		tipoPregunta = preguntasDAOImpl.buscarPreguntaPorId(Integer.parseInt(idpregunta));
		
		
		preguntasDAOImpl.borrarPregunta(Integer.parseInt(idpregunta));
		//Distinguir el tipo de pregunta
		if(tipoPregunta != 1){//Si tipoPregunta es texto (1)
			//codigo para borrar respuesta
			RespuestaspreguntasDAO respuestaspreguntasDAO = new RespuestaspreguntasDAOImpl();
			respuestaspreguntasDAO.borrarRespuesta(Integer.parseInt(idpregunta));
		}
		
		
		request.getRequestDispatcher("borradoPreguntaRespuestaOK.jsp").forward(request, response);
		
		
		
	}
	

}


