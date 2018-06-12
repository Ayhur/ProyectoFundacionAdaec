package servletsAdmin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daosImpl.PreguntasDAOImpl;
import daosImpl.RespuestaspreguntasDAOImpl;
import modelos.Preguntas;
import modelos.Respuestaspreguntas;

@WebServlet("/ServletRegistroAdminPregunta")
public class ServletRegistroAdminPregunta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Recojo la pregunta, el tipo y las posibles respuestas
		String preguntaRecibida = request.getParameter("registroPregunta");
		int tipoRespuestaRecibida=-1;		
		String respuestaRecibida = request.getParameter("resgistroRespuesta");
		
		//Dependiendo de lo que reciba en el tipo, el tipoRespuesta será 1, 2, 3
		if(request.getParameter("tipoRespuesta").equals("Texto")){
			tipoRespuestaRecibida = 1;
		}else if(request.getParameter("tipoRespuesta").equals("Radio")){
			tipoRespuestaRecibida = 2;
		}else if(request.getParameter("tipoRespuesta").equals("Check")){
			tipoRespuestaRecibida = 3;
		}
		
		//Creamos el objeto pregunta
		Preguntas preguntaNueva  = new Preguntas(preguntaRecibida, tipoRespuestaRecibida);
		
		//Registro la pregunta
		PreguntasDAOImpl preguntasDAOImpl = new PreguntasDAOImpl();
		//Me tiene que devolver el identificador autogenerado por BBDD de la pregunta
		int idPreguntaGenerada = preguntasDAOImpl.registrarPregunta(preguntaNueva);
		
		if(tipoRespuestaRecibida == 2 || tipoRespuestaRecibida == 3){
			Respuestaspreguntas respuestaNueva = new Respuestaspreguntas(respuestaRecibida);
			
			RespuestaspreguntasDAOImpl respuestaspreguntasDAOImpl = new RespuestaspreguntasDAOImpl();
			respuestaspreguntasDAOImpl.registrarRespuesta(respuestaNueva, idPreguntaGenerada);
		}
		
		request.getRequestDispatcher("registroPreguntaOK.jsp").forward(request, response);
		
		
	}

}
