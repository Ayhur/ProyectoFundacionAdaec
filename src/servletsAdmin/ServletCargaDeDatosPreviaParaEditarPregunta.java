package servletsAdmin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.PreguntasDAO;
import daosImpl.PreguntasDAOImpl;

@WebServlet("/ServletCargaDeDatosPreviaParaEditarPregunta")
public class ServletCargaDeDatosPreviaParaEditarPregunta extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PreguntasDAO preguntasDAO = new PreguntasDAOImpl();
        request.setAttribute("preguntas", preguntasDAO.obtenerPreguntas());
        request.getRequestDispatcher("editarPreguntas.jsp").forward(request, response);

    }

}
