package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.UsuariosDAO;
import daosImpl.UsuariosDAOImpl;
import modelos.Usuario;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Recabamos los campos usuario y password
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		System.out.println(user);
		
		
		Usuario usuarioDto = new Usuario();
		usuarioDto.setUsuario(user);
		usuarioDto.setPassword(pass);
		
		UsuariosDAO daoUsuario = new UsuariosDAOImpl();
		usuarioDto = daoUsuario.identificarUsuario(usuarioDto);
		System.out.println("idUsuario:" + usuarioDto.getId());
		
		// En funcion de quien logea envia un attribute distinto para cargar distintas paginas de endometriosis
		
		if (usuarioDto.getId() > 0) {			// usuario normal

			//request.getSession().setAttribute("idUser", usuarioDto.getId());
			request.getSession().setAttribute("user", usuarioDto);
			request.getRequestDispatcher("endometriosis.jsp").forward(request, response);

		} else if (usuarioDto.getId() == 0) {	// administrador

			request.getSession().setAttribute("idUserAdmin", usuarioDto.getId());
			request.getRequestDispatcher("endometriosis.jsp").forward(request, response);

		} else if (usuarioDto.getId() == -1) {	// fallo en el login
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
