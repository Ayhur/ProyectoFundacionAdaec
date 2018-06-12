package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.UsuariosDAO;
import daosImpl.UsuariosDAOImpl;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		System.out.println(user);

		UsuariosDAO daoUsuario = new UsuariosDAOImpl();
		int idUsuario = daoUsuario.identificarUsuario(user, pass);
		System.out.println("idUsuario:" + idUsuario);
		
		/**
		 * Metodo que recoge el usuario de las tablas usuarios y administradores
		 * sino esta en uno busca en la otra
		 * en funcion de quien logea envia un attribute distinto para cargar
		 * distintas paginas de endometriosis
		 */
		
		if (idUsuario > 0) {			// usuario normal

			request.getSession().setAttribute("idUser", idUsuario);
			request.getRequestDispatcher("endometriosis.jsp").forward(request, response);

		} else if (idUsuario == 0) {	// administrador

			request.getSession().setAttribute("idUserAdmin", idUsuario);
			request.getRequestDispatcher("endometriosis.jsp").forward(request, response);

		} else if (idUsuario == -1) {	// fallo en el login
			//validacion login carlos
			request.setAttribute("mensajelogin", "email y/o pass incorrectos");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			// signficaria que el usuario no existe en la base de datos y deberia
			// registrarse
		} else {						// intento de hackeo
			// significaria que nos estan asaltando la seguridad de la pagina web
		}
	}
}
