package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.UsuariosDAO;
import daosImpl.UsuariosDAOImpl;
import modelos.Usuario;

@WebServlet("/ServletRegistroUsuario")
public class ServletRegistroUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombre = request.getParameter("campoNombre");
		String apellidos = request.getParameter("campoApellidos");
		String fechaNacimiento = request.getParameter("campoFechaNacimiento");
		String email = request.getParameter("campoEmail");
		String dni = request.getParameter("campoDNI");
		String usuario = request.getParameter("campoUsuario");
		String password = request.getParameter("campoPassword");
		String repPassword = request.getParameter("campoRepetirpassword");
		String municipio = request.getParameter("selectMunicipios");

		/** TODO validaciones */
		/*
		 * //validacion por carlos falta DNI// //valido nombre// String
		 * expresionRegularnombre = "[a-zA-Záéíóú�?É�?ÓÚñÑ\\s]{2,10}"; Pattern pattern =
		 * Pattern.compile(expresionRegularnombre); Matcher matcher =
		 * pattern.matcher(nombre); if ( matcher.matches()){
		 * System.out.println("nombre ok"); }else{
		 * System.out.println("nombre no valido"); request.setAttribute("mensajenombre",
		 * "Nombre no valido");
		 * request.getRequestDispatcher("registroUsuario.jsp").forward(request,
		 * response); return; } //valido apellidos// String expresionRegularapellidos =
		 * "[a-zA-Záéíóú�?É�?ÓÚñÑ\\s]{3,25}"; Pattern pattern1 =
		 * Pattern.compile(expresionRegularapellidos); Matcher matcher1 =
		 * pattern1.matcher(apellidos); if ( matcher1.matches()){
		 * System.out.println("apellidos ok"); }else{
		 * System.out.println("apellidos no valido ");
		 * request.setAttribute("mensajeapellido", "apellidos no valido");
		 * request.getRequestDispatcher("registroUsuario.jsp").forward(request,
		 * response); return; } //validacion fecha nacimiento// String
		 * expresionRegulfechanacimiento ="\\d{2}-\\d{2}-\\d{4}"; Pattern pattern2 =
		 * Pattern.compile(expresionRegulfechanacimiento); Matcher matcher2 =
		 * pattern2.matcher(fechaNacimiento); if ( matcher2.matches()){
		 * System.out.println("fecha nacimiento ok"); }else{
		 * System.out.println("fecha de nacimiento no es valida dd-mm-yyyy ");
		 * request.setAttribute("mensajefechanacimiento",
		 * "fecha de nacimiento no es valida");
		 * request.getRequestDispatcher("registroUsuario.jsp").forward(request,
		 * response); return; } //validacion email// String expresionRegulemail
		 * ="\\A[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\z";
		 * Pattern pattern3 = Pattern.compile(expresionRegulemail); Matcher matcher3 =
		 * pattern3.matcher(email); if ( matcher3.matches()){
		 * System.out.println("email ok"); }else{
		 * System.out.println("el email no es valido ");
		 * request.setAttribute("mensajeemail", "el email no es valida");
		 * request.getRequestDispatcher("registroUsuario.jsp").forward(request,
		 * response); return; } //validacion DNI//
		 * 
		 * //validacion usuario// String expresionRegulusuario ="[a-zA-ZñÑ0-9]{3,15}";
		 * Pattern pattern5 = Pattern.compile(expresionRegulusuario); Matcher matcher5 =
		 * pattern5.matcher(usuario); if ( matcher5.matches()){
		 * System.out.println("usuario ok"); }else{
		 * System.out.println("el usuario no es valido ");
		 * request.setAttribute("mensajeusuario", "el usuario no es valida");
		 * request.getRequestDispatcher("registroUsuario.jsp").forward(request,
		 * response); return; } //longitud de password// String expresionRegulpassword
		 * ="[a-zA-ZnÑ0-9]{8,20}"; Pattern pattern6 =
		 * Pattern.compile(expresionRegulpassword); Matcher matcher6 =
		 * pattern6.matcher(password); if ( matcher6.matches()){
		 * System.out.println("password con los digitos ok"); }else{
		 * System.out.println("la password no tiene lo caracteres necesarios ");
		 * request.setAttribute("mensajepassword",
		 * "La password tiene que tener entre 8 y 20 caracteres");
		 * request.getRequestDispatcher("registroUsuario.jsp").forward(request,
		 * response); return; }
		 * 
		 * 
		 * //validacion concordancia password// if (password.equals(repPassword)){
		 * System.out.println("la password concuerda"); }else{
		 * System.out.println("la password no coincide");
		 * request.setAttribute("mensajeconconrdancia","la password no concuerda");
		 * request.getRequestDispatcher("registroUsuario.jsp").forward(request,
		 * response); return; }
		 */

		/* Fin validaciones */

		/** Transformacion de fecha nacimiento */
		/*
		 * SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd"); Date
		 * fn = null; try { fn = formatoDelTexto.parse(fechaNacimiento); } catch
		 * (ParseException ex) {
		 * System.out.println("Error al transformar la fecha de nacimiento");
		 * ex.printStackTrace(); }
		 */
		/** Fin transformacion fecha */

		Usuario usuarios = new Usuario(nombre, apellidos, dni, email, usuario, password, fechaNacimiento,
				Integer.parseInt(municipio));
		UsuariosDAO daoUsuario = new UsuariosDAOImpl();
		daoUsuario.registrarUsuario(usuarios);
		System.out.println("usuario registrado con exito");

		request.getRequestDispatcher("registroUsuarioOK.jsp").forward(request, response);

	}

}