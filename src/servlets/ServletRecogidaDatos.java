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

import daos.MedicamentosDAO;
import daos.RespuestasSliderDAO;
import daos.RespuestasTextoDAO;
import daos.TratamientoDAO;
import daos.UsuariosDAO;
import daosImpl.MedicamentosDAOImpl;
import daosImpl.RespuestasCheckDAOImpl;
import daosImpl.RespuestasRadioDAOImpl;
import daosImpl.RespuestasSliderDAOImpl;
import daosImpl.RespuestasTextoDAOImpl;
import daosImpl.TratamientoDAOImpl;
import daosImpl.UsuariosDAOImpl;
import modelos.CheckRespuesta;
import modelos.Medicamento;
import modelos.RadioRespuesta;
import modelos.SliderRespuesta;
import modelos.TextRespuesta;
import modelos.Tratamiento;
import modelos.Usuario;

@WebServlet("/ServletRecogidaDatos")
public class ServletRecogidaDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Se ejecuta el post del servletRecogidaDatos");
		System.out.println("El ID de usuario es: "+request.getParameter("userLog"));
		Integer usuarioLogeado = Integer.parseInt(request.getParameter("userLog"));

		// Recojo todos los name de todo el formulario
		Enumeration<String> camposRecibidos = request.getParameterNames();

		List<TextRespuesta> textRespuestas = new ArrayList<TextRespuesta>();
		List<RadioRespuesta> radioRespuestas = new ArrayList<RadioRespuesta>();
		List<CheckRespuesta> checkRespuestas = new ArrayList<CheckRespuesta>();
		List<SliderRespuesta> sliderRespuestas = new ArrayList<SliderRespuesta>();
		List<Medicamento> listaMedicamentos = new ArrayList<Medicamento>();
		List<Tratamiento> tratamientoRespuestas = new ArrayList<Tratamiento>();
		
		// Interrogo todos los medicamentos de la BBDD para poder recoger las respuestas mas adelante
		listaMedicamentos = crearListaMedicamentos(listaMedicamentos);
		
		//ArrayList para agrupar los campos check
		String camposCheck = "";

		// Variable para saber la pregunta actual por la que voy (para el check)
		String idPreguntaActual = "";
		
		// Variable para contener los check de medicamentos y compraralos con los radio
		String nombreCheckMedicamento = "";
		
		/**
		 * 100  -  Error en la inserccion de datos de textRespuesta.<br>
		 * 101  -  Error en la inserccion de datos de radioRespuesta.<br>
		 * 102  -  Error en la inserccion de datos de checkRespuesta.<br>
		 * 103  -  Error en la creacion de la virtualizacion del usuario RVP.<br>
		 * 104  -  Error en la actualizacion del campo SI06 realizado.<br>
		 * 105  -  Error en la inserccion de datos de SI16_slider_respuestas.<br>
		 * 106	-  Error en la inserccion de los tratamientos.<br>
		 */
		ArrayList<Integer> erroresLog = new ArrayList<Integer>();
		
		// Recorro el enumeration
		while (camposRecibidos.hasMoreElements()) {

			// Guardo en una variable el contenido de su name
			String nombreCampo = camposRecibidos.nextElement();
			System.out.println(nombreCampo);
			boolean encMedicamento = false;
			Tratamiento tratamiento = new Tratamiento();
			// bucle que busca si el campo contiene el nombre de un medicamento
			for (Medicamento medicamento : listaMedicamentos) {
				if (nombreCampo.contains(medicamento.getNombreMed())) {
					encMedicamento = true;
					tratamiento.setIdMedicamento(medicamento.getIdMedicamento());
					break;
				}
			}
			
			// Si encontramos un medicamento procedemos a su agregado a la lista de Tratamientos
			if(encMedicamento) {
				
				// Aqui lo que hago es recoger el nombre del campo check que siempre irá primero para posteriormente compararlo en la recogida del radio (else if)
				if(nombreCampo.startsWith("check")) {
					nombreCheckMedicamento = nombreCampo.substring(5);
				}
				
				// cuando coincide el medicamento de radio con el de check lo agregamos al listado de tratamientos.
				else if (nombreCampo.startsWith("radio") && nombreCampo.substring(5).equalsIgnoreCase(nombreCheckMedicamento)) {
					
					String[] val = request.getParameterValues(nombreCampo);
							for (String string : val) {
								System.out.println(string);
							}
					
					tratamiento.setNombreMed(nombreCheckMedicamento);
					System.out.println(request.getParameter(nombreCampo));
					if(request.getParameter(nombreCampo).equals("bien")) 
						tratamiento.setFueBien(true);
					else
						tratamiento.setFueBien(false); 
					
					tratamientoRespuestas.add(tratamiento);
				}
				
			}
			
			else if(!nombreCampo.equalsIgnoreCase("userLog")) {//para evitar error en logica de Alberto al introducir el hidden de usuario
				String contenidoRespuesta = request.getParameter(nombreCampo);
				System.out.println(contenidoRespuesta);
				// Muestro name y contenido para ver que lo cojo bien
//				System.out.println("Atributo name de la respuesta: " + nombreCampo);
//				System.out.println("Contenido de la respuesta: " + contenidoRespuesta);

				// Obtengo el nombre del atributo name que tiene que se
				// id del tipo de pregunta + _ + id de la pregunta
				
				String[] idsSeparados = nombreCampo.split("_");
				if (idPreguntaActual == "") { // si no hay un id se le agrega el
												// primero que encuentre
					idPreguntaActual = idsSeparados[1];
				}
				else if (!idPreguntaActual.equals(idsSeparados[1])) {
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
				
				/**
				 * Si mal no recuerdo en el primer condicional recogemos las respuestas que son de tipo unica es decir caja texto y radio
				 * porque solo traen un elemento respuesta, mientras que en el segundo condicional se recoge una cadena compuesta de elementos
				 * y separadores.<br>
				 */
				if (nombreCampo.startsWith("1") || nombreCampo.startsWith("2") || (nombreCampo.startsWith("4"))) {
					extraccionDeDatosUnitaria(textRespuestas, radioRespuestas, sliderRespuestas,
							nombreCampo, contenidoRespuesta, idsSeparados);
				} 
				else if (nombreCampo.startsWith("3")) {
					// las preguntas check, (que no con radio que van por otra rama de codigo) con caja adicional
					// contienen en los check 3 ids pero la caja adicional solo contiene 2 ids
					if(idsSeparados.length == 3) {
						camposCheck = extraccionDeDatosMultiples(checkRespuestas, nombreCampo,
								contenidoRespuesta,idsSeparados[1],idPreguntaActual, camposCheck, idsSeparados[2]);
					}
					else if (!contenidoRespuesta.equals("")){
						TextRespuesta respuestaTexto = new TextRespuesta(contenidoRespuesta, Integer.parseInt(idsSeparados[1]));
						textRespuestas.add(respuestaTexto);
					}
				}
			}//fin de la condicion que sea distinto de userLog
//			System.out.println("*****************************");
		}//fin while
		
		// 1.- Creamos un registro con los datos necesarios para virtualizar una persona
		// con el fin de poder "recrearla" para la creacion de estadísticas
		
		System.out.println("el usuario antes de agregar los datos es: "+request.getParameter("userLog"));
		UsuariosDAO crearRVP = new UsuariosDAOImpl();
		Usuario usuarioRVP = crearRVP.extraccionDatosPersonales(Integer.parseInt(request.getParameter("userLog")));
		int idRegistroFormulario = crearRVP.creacionRegistroVirtualPersonal(usuarioRVP, erroresLog);
		System.out.println("El idRegistroFormulario es: "+idRegistroFormulario);
		
		
		//Cargo respuestas texto
		RespuestasTextoDAO respuestasTextoDAO = new RespuestasTextoDAOImpl();
		respuestasTextoDAO.registrarRespuestas(textRespuestas,idRegistroFormulario,erroresLog);
		
		//Cargo respuestas radio
		RespuestasRadioDAOImpl respuestasRadioDAO = new RespuestasRadioDAOImpl();
		respuestasRadioDAO.registrarRespuestas(radioRespuestas, idRegistroFormulario,erroresLog);
		
		//Cargo respuestas check
		RespuestasCheckDAOImpl respuestasCheckDAO = new RespuestasCheckDAOImpl();
		respuestasCheckDAO.registrarRespuestas(checkRespuestas, idRegistroFormulario,erroresLog);
		
		//Cargo respuestas slider
		RespuestasSliderDAO respuestaSliderDao = new RespuestasSliderDAOImpl();
		respuestaSliderDao.registrarRespuestas(sliderRespuestas, idRegistroFormulario, erroresLog);
		
		//Cargo respuestas tabla medicamentos
		TratamientoDAO respuestaTratamientoDAO = new TratamientoDAOImpl();
		respuestaTratamientoDAO.registrarRespuestaTratamientos(tratamientoRespuestas,idRegistroFormulario, erroresLog);
		
		
		
		request.setAttribute("mensajeFormSubido", "Formulario realizado con correctamente");
		
		// Interrogamos si ha ocurrido algun error de inserccion en el transcurso de agregar las respuestas
		// del usuario, si no ha ocurrido nada cambiamos el valor de realizado a 1 y redirigimos a formularioOK.jsp
		if (null != erroresLog && erroresLog.size() == 0) {
			// Editamos el campo de usuario "realizado" de 0 (false) a 1 (true) para posteriormente no entre mas veces al formulario
			System.out.println("Entramos a actualizar el campo 'Realizado' de usuarios");
			crearRVP.actualizarRealizado(usuarioLogeado,erroresLog);
			System.out.println("Finalizamos la actualización del campo 'Realizado' de usuarios");
			// fin edicion del campo realizado
			
			// agregamos a la variable de sesion el valor realizado para que no pueda volver
			// a realizado una vez finalizado y redirigida las paginas correspondientes
			Usuario usuario = (Usuario) request.getSession().getAttribute("user");
			usuario.setRealizado(new Integer(1));
			request.getSession().setAttribute("user", usuario);
			
			// redirigimos a formularioOK.jsp
			request.getRequestDispatcher("formularioOK.jsp").forward(request, response);
		}
		else {
			System.out.println("Redirigimos a pagina de Error");
			// Colocar aqui redireccion a una nueva jsp de error
			request.getRequestDispatcher("errorFormulario.jsp").forward(request, response);
		}
		
		
		
		
		
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

	}

	/**
	 * Método que trae una lista con todos los medicamentos.<br>
	 * @param listaMedicamentos 
	 * @return 
	 * 
	 */
	private List<Medicamento> crearListaMedicamentos(List<Medicamento> listaMedicamentos) {
		
		MedicamentosDAO listarMedicamentosDAO = new MedicamentosDAOImpl();
		return listaMedicamentos = listarMedicamentosDAO.listarMedicamentos();
	}
	
	/**
	 * Método que se encarga de las extracciones donde los valores de respuesta son varios, como los check.<br>
	 * @param checkRespuestas
	 * @param nombreCampo
	 * @param contenidoRespuesta
	 * @param idsSeparados1
	 * @param idPreguntaActual
	 * @param camposCheck
	 * @param idsSeparados2
	 * @return
	 */
	private String extraccionDeDatosMultiples(List<CheckRespuesta> checkRespuestas, String nombreCampo,
			String contenidoRespuesta, String idsSeparados1, String idPreguntaActual, String camposCheck, String idsSeparados2) {
		
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

	/**
	 * Falta descripcion.<br>
	 * @param textRespuestas
	 * @param radioRespuestas
	 * @param sliderRespuestas 
	 * @param nombreCampo
	 * @param contenidoRespuesta
	 * @param idsSeparados
	 */
	private void extraccionDeDatosUnitaria(List<TextRespuesta> textRespuestas,
			List<RadioRespuesta> radioRespuestas, List<SliderRespuesta> sliderRespuestas, String nombreCampo,
			String contenidoRespuesta, String[] idsSeparados) {
		
		//Para respuestas tipo texto
		if (nombreCampo.startsWith("1")) {
			TextRespuesta respuestaTexto = new TextRespuesta(contenidoRespuesta, Integer.parseInt(idsSeparados[1]));
			textRespuestas.add(respuestaTexto);
			
		} 
		//Para respuestas tipo radio
		else if (nombreCampo.startsWith("2")) {
			// Si el campo termina en "at" (adicional text) recogemos la caja de texto como si fuera una respuesta text solo que la vinculacion id-pregunta evidentemente
			// referenciará a una pregunta de tipo 2 radiobuttom
			if(nombreCampo.endsWith("at")) {//revisar que no se registren cajas adicionales con texto nulo o vacio
				TextRespuesta respuestaTexto = new TextRespuesta(contenidoRespuesta, Integer.parseInt(idsSeparados[1]));
				textRespuestas.add(respuestaTexto);
			}
			else {
				RadioRespuesta respuestaRadio = new RadioRespuesta(contenidoRespuesta, Integer.parseInt(idsSeparados[1]));
				radioRespuestas.add(respuestaRadio);
			}
		
		} 
		//Para respuestas tipo slider
		else if (nombreCampo.startsWith("4")) {
			SliderRespuesta respuestaSlider = new SliderRespuesta(Integer.parseInt(contenidoRespuesta), Integer.parseInt(idsSeparados[1]));
			sliderRespuestas.add(respuestaSlider);
			
		} 
		else {
			System.out.println("valor no contemplado ¿probablemente inteto de hackeo?");
		}
	}//fin metodo

}
