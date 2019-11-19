package servletsAdmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ConstantesEndometriosis;
import daosImpl.InformacionAdicionalDAOImpl;
import daosImpl.PreguntasDAOImpl;
import daosImpl.RespuestaspreguntasDAOImpl;
import modelos.Preguntas;
import modelos.Respuestaspreguntas;

@WebServlet("/ServletRegistroAdminPregunta")
public class ServletRegistroAdminPregunta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// errores corregir
	/*
	 * no registra una pregunta dependiente de otra si le pones que es texto
	 */

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("esto es orden: " + request.getParameter("selectOrdenPregunta"));
		System.out.println("esto es nivel dependencia: " + request.getParameter("selectPregunta"));

		// Recojo la pregunta, el tipo y las posibles respuestas
		String preguntaRecibida = request.getParameter("registroPregunta");
		int tipoRespuestaRecibida = -1;
		boolean cajaAdicional = false;
		// String respuestaRecibida = request.getParameter("resgistroRespuesta");
		System.out.println("entro a registrar pregunta");
		System.out.println("esto es pregunta recibida: " + preguntaRecibida);
		// System.out.println("esto es respuesta recibida: "+respuestaRecibida);

		/**
		 * Dependiendo de lo que reciba en el tipo, el tipoRespuesta será: 1 - Caja de
		 * texto 2 - Radio button 3 - Check button 4 - Slider bar
		 */
		if (request.getParameter("tipoRespuesta").equals("Texto")) {
			tipoRespuestaRecibida = 1;
		} else if (request.getParameter("tipoRespuesta").equals("Radio")) {
			tipoRespuestaRecibida = 2;
		} else if (request.getParameter("tipoRespuesta").equals("Check")) {
			tipoRespuestaRecibida = 3;
		} else if (request.getParameter("tipoRespuesta").equals("Slider")) {
			tipoRespuestaRecibida = 4;
		}

		// Recogo la opcion adicional de caja de texto
		if (null != request.getParameter("tipoRespuestaOpcional")) {
			cajaAdicional = true;
		}
		// fin codigo recogida de opción adicional

		// Creamos el objeto pregunta
		generacionRegistroPregunta(request, preguntaRecibida, tipoRespuestaRecibida, cajaAdicional);

		request.getRequestDispatcher("registroPreguntaOK.jsp").forward(request, response);

	}

	/**
	 * Método previo al registro de pregunta. Según sea el tipo de pregunta deriva
	 * hacia un registro distinto.<br>
	 * 
	 * @param request
	 * @param preguntaRecibida
	 * @param tipoRespuestaRecibida
	 * @param cajaAdicional
	 */
	private void generacionRegistroPregunta(HttpServletRequest request, String preguntaRecibida,
			int tipoRespuestaRecibida, boolean cajaAdicional) {

		// Si seleccionamos depende de la respuesta de otra pregunta
		if (null != request.getParameter("tipoDependencia")
				&& ConstantesEndometriosis.BOTON_RADIO_SI.equals(request.getParameter("tipoDependencia"))) {
			// Recogemos el ID de la pregunta de la que depende
			Integer nivel = Integer.parseInt(request.getParameter("selectPregunta"));

			// la buscamos en la BBDD y le extraemos su orden y le agregamos +1
			PreguntasDAOImpl preguntasDAOImpl = new PreguntasDAOImpl();
			int orden = preguntasDAOImpl.buscarOrdenPreguntaDependencia(nivel) + 1;
			Preguntas preguntaNueva = new Preguntas(preguntaRecibida, tipoRespuestaRecibida, nivel, orden,
					cajaAdicional);
			registrarPregunta(request, tipoRespuestaRecibida, preguntaNueva);
		}
		// Si seleccionamos que queremos ubicarla detrás de otra pregunta
		else if (null != request.getParameter("ordenPregunta")
				&& ConstantesEndometriosis.BOTON_RADIO_SI.equals(request.getParameter("ordenPregunta"))) {

			int idPreguntaSelecCombOrden = Integer.parseInt(request.getParameter("selectOrdenPregunta"));
			// El orden solo lo puedo conocer si hago una consulta a través del id traido en
			// selectOrdenPregunta
			PreguntasDAOImpl preguntasDAOImpl = new PreguntasDAOImpl();
			int orden = preguntasDAOImpl.buscarOrdenPreguntaDependencia(idPreguntaSelecCombOrden) + 1;
//			int orden = Integer.parseInt(request.getParameter("ordenPregunta") + 1);
			Preguntas preguntaNueva = new Preguntas(preguntaRecibida, tipoRespuestaRecibida, 0, orden, cajaAdicional);
			registrarPregunta(request, tipoRespuestaRecibida, preguntaNueva);

		}
		// Sin dependencias
		else {
			Preguntas preguntaNueva = new Preguntas(preguntaRecibida, tipoRespuestaRecibida, cajaAdicional);
			registrarPregunta(request, tipoRespuestaRecibida, preguntaNueva);
		}
	}

	/**
	 * Método que se encarga de registrar la pregunta agregada por el
	 * administrador.<br>
	 * Estableceremos un orden, primero si posee una dependencia se registra por
	 * detrás de esa dependencia, y si no posee dependencia se registra por orden si
	 * ha sido seleccionado. Por ultimo si no hay ninguno de los dos, se registra al
	 * final del formulario.<br>
	 * 
	 * @param request
	 * @param tipoRespuestaRecibida
	 */
	private void registrarPregunta(HttpServletRequest request, int tipoRespuestaRecibida, Preguntas preguntaNueva) {

		PreguntasDAOImpl preguntasDAOImpl = new PreguntasDAOImpl();

		// Interrogamos si debemos de cambiar el orden de las preguntas (esto se ejecuta
		// en dependencia y orden de pregunta
		// no para las preguntas por defecto normales)
		if (null != preguntaNueva && 0 != preguntaNueva.getOrden()) {
			// Extraemos el orden desde el numero de pregunta que irá ubicado
			List<Integer> ordenListado = preguntasDAOImpl
					.extraerAntiguoOrdenRegistroPreguntas(preguntaNueva.getOrden());
			// Modificamos el orden para que se pueda insertar la pregunta en ese puesto
			for (Integer ordenPregunta : ordenListado) {
				preguntasDAOImpl.configurarNuevoOrdenListado(ordenPregunta);
			}

		}
		// Se ejecuta else cuando una pregunta no trae orden (pregunta por defecto)
		else {
			preguntaNueva.setOrden(preguntasDAOImpl.buscanPosicionFinalFormulario());
		}
		
		// Informamos el campo de bloque pregunta
		preguntaNueva.setBloque(buscarBloqueDependencia(request));
		

		// Me tiene que devolver el identificador autogenerado por BBDD de la pregunta
		int idPreguntaGenerada = preguntasDAOImpl.registrarPregunta(preguntaNueva);

		// Si el tipo de respuesta es radio o check o slider
		if (tipoRespuestaRecibida == 2 || tipoRespuestaRecibida == 3 || tipoRespuestaRecibida == 4) {
			// Recogo las respuestas introducidas por el usuario en un arraylist
			registrarRespuestas(request, idPreguntaGenerada, preguntaNueva);
		}
	}
	
	/**
	 * Método que se encarga de buscar si la pregunta esta categorizada dentro de un bloque.<br>
	 * @param request
	 * @return
	 */

	private int buscarBloqueDependencia(HttpServletRequest request) {
		
		String bloque = "";
		
		
		if(null != request.getParameter("selectBloquePregunta")) {
			bloque = request.getParameter("selectBloquePregunta");
			return new InformacionAdicionalDAOImpl().buscarBloquePreguntas(bloque);
		}
		
		else {
			return 0;
		}
	}

	/**
	 * Método que registra las respuesta introducidas por el administrador.<br>
	 * 
	 * @param request
	 * @param idPreguntaGenerada
	 * @param preguntaNueva
	 */
	private void registrarRespuestas(HttpServletRequest request, int idPreguntaGenerada, Preguntas preguntaNueva) {

		int contador = 0;
		List<String> listaRespuestas = new ArrayList<String>();
		try {
			while (true) {
				contador++;
				if ((null != request.getParameter("VMin") && !request.getParameter("VMin").isEmpty())
						&& (null != request.getParameter("VMax") && !request.getParameter("VMax").isEmpty())) {
					listaRespuestas.add(request.getParameter("VMin"));
					listaRespuestas.add(request.getParameter("VMax"));
					break;
				} else if (null != request.getParameter("resgistroRespuesta".concat(String.valueOf(contador)))) {
					listaRespuestas.add(request.getParameter("resgistroRespuesta".concat(String.valueOf(contador))));
				} else {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Error al registrar Respuestas");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			System.out
					.println("Finalizada la recabación de respuestas para la creación de pregunta nueva de formulario");
		}

		// Transformamos el arrayList en una cadena la cual cada elemento sera separado
		// por "-"
		String respuestaRecibida = "";
		for (String respuesta : listaRespuestas) {
			if (respuestaRecibida.equals("")) {
				respuestaRecibida = respuestaRecibida.concat(respuesta);
			} else {
				respuestaRecibida = respuestaRecibida.concat("-").concat(respuesta);
			}
		}

		System.out.println("*Esto seria las respuestas recibidas:* " + respuestaRecibida);
		Respuestaspreguntas respuestaNueva = new Respuestaspreguntas(respuestaRecibida);

		RespuestaspreguntasDAOImpl respuestaspreguntasDAOImpl = new RespuestaspreguntasDAOImpl();
		respuestaspreguntasDAOImpl.registrarRespuesta(respuestaNueva, idPreguntaGenerada);
	}
}
