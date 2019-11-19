package servletsAdmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.PreguntasDAO;
import daosImpl.InformacionAdicionalDAOImpl;
import daosImpl.PreguntasDAOImpl;
import modelos.Preguntas;

/**
 * Servlet implementation class ServletCargaDatosPreviaRegistrarPreguntaRespuesta
 */
@WebServlet("/ServletCargaDatosPreviaRegistrarPreguntaRespuesta")
public class ServletCargaDatosPreviaRegistrarPreguntaRespuesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCargaDatosPreviaRegistrarPreguntaRespuesta() {
        super();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Llamamos a PreguntasDAO para traer de BBDD todas las preguntas del formulario 
		// y cargarlos para la dependencia de creación de nuevas preguntas
		PreguntasDAO preguntasDAO = new PreguntasDAOImpl();
		List<Preguntas> preguntaAcotadas = new ArrayList<Preguntas>();
		// Recorremos todas las preguntas del listado y las acortamos a max 48 caracteres
		for (Preguntas pregunta : preguntasDAO.obtenerPreguntas()) {
			String cadenaTemporal = String.valueOf(pregunta.getOrden()).concat(". ").concat(pregunta.getDescripcion());
			if (pregunta.getDescripcion().toCharArray().length > 48) {
				cadenaTemporal = cadenaTemporal.substring(0, 48).concat("...");
			}
			pregunta.setDescripcion(cadenaTemporal);
			preguntaAcotadas.add(pregunta);
		}
		
		List<String> bloquePreguntas = new ArrayList<String>();
		bloquePreguntas = new InformacionAdicionalDAOImpl().listarBloquePreguntas();
		
        request.setAttribute("preguntas", preguntaAcotadas);
        request.setAttribute("bloquePreguntas", bloquePreguntas);
        request.getRequestDispatcher("registrarPreguntaRespuesta.jsp").forward(request, response);
	}

}
