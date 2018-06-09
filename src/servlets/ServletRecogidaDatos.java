package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.RespuestasTextoDAO;
import daos.UsuariosDAO;
import daosImpl.RespuestasCheckDAOImpl;
import daosImpl.RespuestasRadioDAOImpl;
import daosImpl.RespuestasTextoDAOImpl;
import daosImpl.UsuariosDAOImpl;
import modelos.CheckRespuesta;
import modelos.RadioRespuesta;
import modelos.TextRespuesta;
import modelos.Usuario;

@WebServlet("/ServletRecogidaDatos")
public class ServletRecogidaDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Se ejecuta el post del servletRecogidaDatos");
		System.out.println("El ID de usuario es: "+request.getParameter("userLog"));

		// Recojo todos los name de todo el formulario
		Enumeration<String> camposRecibidos = request.getParameterNames();

		List<TextRespuesta> textRespuestas = new ArrayList<TextRespuesta>();
		List<RadioRespuesta> radioRespuestas = new ArrayList<RadioRespuesta>();
		List<CheckRespuesta> checkRespuestas = new ArrayList<CheckRespuesta>();
		
		//ArrayList para agrupar los campos check
		String camposCheck = "";

		// Variable para saber la pregunta actual por la que voy (para el check)
		String idPreguntaActual = "";

		// Recorro el enumeration
		while (camposRecibidos.hasMoreElements()) {

			// Guardo en una variable el contenido de su name
			String nombreCampo = camposRecibidos.nextElement();
			if(!nombreCampo.equalsIgnoreCase("userLog")) {//para evitar error en logica de Alberto al introducir el hidden de usuario
				String contenidoRespuesta = request.getParameter(nombreCampo);

				// Muestro name y contenido para ver que lo cojo bien
//				System.out.println("Atributo name de la respuesta: " + nombreCampo);
//				System.out.println("Contenido de la respuesta: " + contenidoRespuesta);

				// Obtengo el nombre del atributo name que tiene que se
				// id del tipo de pregunta + _ + id de la pregunta
				
				String[] idsSeparados = nombreCampo.split("_");
				if (idPreguntaActual == "") { // si no hay un id se le agrega el
												// primero que encuentre
					idPreguntaActual = idsSeparados[1];
				} else if (!idPreguntaActual.equals(idsSeparados[1])) {
					//Se compara si la pregunta almaccenada es igual a el de la nueva interaccion
					//del bucle, sino lo es hemos cambiado de pregunta
					//si lo es seguimos almacenando los datos de la pregunta check
					
					//Si camposcheck tiene algo es que hemos estado preparando una
					//agrupacion de checks. Si estamos aquí es que hemos cambiado
					//de pregunta y tenemos que preparar el check final antes de cambiar
					//de pregunta.
					if(camposCheck != ""){
						//Preparar check aqui
						CheckRespuesta respuestaCheck = new CheckRespuesta(camposCheck, Integer.parseInt(idPreguntaActual));
						checkRespuestas.add(respuestaCheck);
						
						//Limpiamos la variable donde almacenabamos los check agrupados
						camposCheck="";
					}
					//Cambiamos de pregunta
					idPreguntaActual = idsSeparados[1];
				}

				if (nombreCampo.startsWith("1") || nombreCampo.startsWith("2")) {
					extraccionDeDatosUnitaria(textRespuestas, radioRespuestas,
							nombreCampo, contenidoRespuesta, idsSeparados);
				} else if (nombreCampo.startsWith("3")) {
					camposCheck = extraccionDeDatosMultiples(checkRespuestas, nombreCampo,
							contenidoRespuesta,idsSeparados[1],idPreguntaActual, camposCheck, idsSeparados[2]);
				}
			}//fin de la condicion que sea distinto de userLog
//			System.out.println("*****************************");
		}//fin while
		
		// 1.- Creamos un registro con los datos necesarios para virtualizar una persona
		// con el fin de poder "recrearla" para la creacion de estadísticas
		
		System.out.println("el usuario antes de agregar los datos es: "+request.getParameter("userLog"));
		UsuariosDAO crearRVP = new UsuariosDAOImpl();
		Usuario usuarioRVP = crearRVP.extraccionDatosPersonales(Integer.parseInt(request.getParameter("userLog")));
		int idRegistroFormulario = crearRVP.creacionRegistroVirtualPersonal(usuarioRVP);
		System.out.println("El idRegistroFormulario es: "+idRegistroFormulario);
		
		//Cargo respuestas texto
		RespuestasTextoDAO respuestasTextoDAO = new RespuestasTextoDAOImpl();
		respuestasTextoDAO.registrarRespuestas(textRespuestas,idRegistroFormulario);
		
		//Cargo respuestas texto
		RespuestasRadioDAOImpl respuestasRadioDAO = new RespuestasRadioDAOImpl();
		respuestasRadioDAO.registrarRespuestas(radioRespuestas);
		
		//Cargo respuestas texto
		RespuestasCheckDAOImpl respuestasCheckDAO = new RespuestasCheckDAOImpl();
		respuestasCheckDAO.registrarRespuestas(checkRespuestas);
		
		request.setAttribute("mensajeFormSubido", "Formulario realizado con correctamente");
		request.getRequestDispatcher("formularioOK.jsp").forward(request, response);
		
//		// Comprobar que estan todos los datos
//		System.out.println("Listado de respuestas de texto:");
//		for (TextRespuesta respuesta : textRespuestas) {
//			System.out.println(respuesta.toString());
//		}
//
//		System.out.println("Listado de respuestas de radio:");
//		for (RadioRespuesta respuesta : radioRespuestas) {
//			System.out.println(respuesta.toString());
//		}
//
//		System.out.println("Listado de respuestas de check:");
//		for (CheckRespuesta respuesta : checkRespuestas) {
//			System.out.println(respuesta.toString());
//		}
		System.out.println("hola");
		request.getRequestDispatcher("formularioOK.jsp").forward(request, response);

	}

	private void creacionRegistroVirtualPersonal(Usuario usuarioRVP) {
		// TODO Auto-generated method stub
		
	}

	private String extraccionDeDatosMultiples(List<CheckRespuesta> checkRespuestas, String nombreCampo,
			String contenidoRespuesta, String idsSeparados1, String idPreguntaActual, String camposCheck, String idsSeparados2) {
		
		//comienzo de metodo
		if(idsSeparados1.equals(idPreguntaActual)){
			//sigo en la misma pregunta de tipo check
			//pues sigo almacenando o concateno los datos porque sigo en la misma pregunta de check
			
			//Si es el primer check lo meto como tal
			if(camposCheck==""){
				camposCheck+= contenidoRespuesta;
			}else{//Sino el check debe estar precedido por guion para separar los check 
				camposCheck = camposCheck.concat("-").concat(contenidoRespuesta);
			}
		}else{//Creo que esto me sobraria
			// aqui estoy en una nueva pregunta check
			// aqui tengo dos casos, que ya tenga un check y haya pasado a otro check
			//o que haya pasado de un radio o un text hasta un check
			camposCheck = contenidoRespuesta;
		}// fin del if
		return camposCheck;
			
	}//final de metodo

	private void extraccionDeDatosUnitaria(List<TextRespuesta> textRespuestas,
			List<RadioRespuesta> radioRespuestas, String nombreCampo,
			String contenidoRespuesta, String[] idsSeparados) {
		//comienzo metodo
		if (nombreCampo.startsWith("1")) {
			//System.out.println("Esta respuesta es de texto");
			TextRespuesta respuestaTexto = new TextRespuesta(contenidoRespuesta, Integer.parseInt(idsSeparados[1]));
			textRespuestas.add(respuestaTexto);
		} else if (nombreCampo.startsWith("2")) {
			//System.out.println("Esta respuesta es de tipo radio");
//			System.out.println("Valores del registro");
//			System.out.println("Valor contenido: " + contenidoRespuesta);
//			System.out.println("Valor pregunta: " + idsSeparados[1]);
			RadioRespuesta respuestaRadio = new RadioRespuesta(contenidoRespuesta, Integer.parseInt(idsSeparados[1]));
			radioRespuestas.add(respuestaRadio);
		}
	}//fin metodo

}
