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
import daosImpl.PreguntasDAOImpl;
import modelos.Preguntas;

@WebServlet("/ServletCargaDeDatosPreviaParaEditarPregunta")
public class ServletCargaDeDatosPreviaParaEditarPregunta extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PreguntasDAO preguntasDAO = new PreguntasDAOImpl();
		List<Preguntas> preguntaAcotadas = new ArrayList<Preguntas>();
        
        // Recorremos todas las preguntas del listado y las acortamos a max 48 caracteres
     		for (Preguntas pregunta : preguntasDAO.listarTodasPreguntas()) {
     			String cadenaTemporal = String.valueOf(pregunta.getOrden()).concat(". ").concat(pregunta.getDescripcion());
     			if (pregunta.getDescripcion().toCharArray().length > 48) {
     				cadenaTemporal = cadenaTemporal.substring(0, 48).concat("...");
     			}
     			pregunta.setDescripcion(cadenaTemporal);
     			preguntaAcotadas.add(pregunta);
     		}

 		request.setAttribute("preguntas", preguntaAcotadas);
 		request.getRequestDispatcher("editarPreguntas.jsp").forward(request, response);
    }

}
