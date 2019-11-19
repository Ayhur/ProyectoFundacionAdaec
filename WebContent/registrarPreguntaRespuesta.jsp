<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Registrar pregunta y respuesta</title>
		<link rel="stylesheet" href="css/registroPreguntaStyle.css">
	</head>
<body>
	
	<div class="formularioRegistroPregunta">
	<h1>Registrar pregunta/respuesta para el formulario</h1>
			<form action="ServletRegistroAdminPregunta" method="post" name="formularioRegistroAdminPregunta" id="formularioRegistroAdminPregunta">
				
				<label>Introduzca la pregunta:</label>
				<input type="text" name="registroPregunta" placeholder="Pregunta" required>
				<br/><br/>
			
				<label>Señale el tipo de respuesta:</label>
				<div id="ContenedorTipoRespuesta" class="contenedorFilaElementos">
					<input type="radio" name="tipoRespuesta" value="Texto" required>Texto
					<input type="radio" name="tipoRespuesta" value="Radio">Seleccione solo <strong>una</strong> respuesta
					<input type="radio" name="tipoRespuesta" value="Check">Seleccione <strong>varias</strong> respuestas
					<input type="radio" name="tipoRespuesta" value="Slider">Barra de rangos numéricos
				</div>
				
				<!-- INICIO para la opcion caja texto respuesta final -->
				<div id="ContenedorTipoRespuestaOpc" class="contenedorFilaElementos">
					<label>(Opcional): </label>
					<input type="checkbox" name="tipoRespuestaOpcional">Agregar caja de texto al final de todas las respuestas
				</div>
				<!-- FIN para la opcion caja texto respuesta final -->
				
				<!-- INICIO CONTENEDOR AGREGACION DE LAS RESPUESTAS -->
				<div id="contenidoRespuestas" class="contenedorFilaElementos">
					<label>Introduzca las respuestas:</label><br/>
	
					<!-- Contenedor de cajas agregar respuesta a preguntas nuevas -->
					<div id="contenedorRespuestasAgregar" class="contenedorFilaElementos">
						<label>Respuesta 1:</label>
						<input type="text" id="resgistroRespuestaId1" name="resgistroRespuesta1" class="rgr" placeholder="Inserte Respuesta" /> 
					</div>
					<!-- FIN Contenedor de cajas agregar respuesta a preguntas nuevas -->
					<input type="button" id="agregar" value="+" />
				</div>
				<!-- FIN CONTENEDOR DE AGREGACIÓN DE LAS RESPUESTAS -->
				
				<!-- CONTENEDOR AGREGACION DE LOS RANGOS SLIDER -->
				<div id="contenidoRangosSlider" class="contenedorFilaElementos">
					<label>Introduzca los rangos:</label><br/>
					<!-- Contenedor interno de rangos numéricos para slider -->
					<div id="contenidoRangosSliderInterno" class="contenedorFilaElementos">
						<label>Valor mínimo:</label>
						<input type="text" id="valorMinimoSlider" name="VMin" class="rgr" placeholder="Inserte valor Mín." /> 
					</div>
					<div id="contenidoRangosSliderInterno" class="contenedorFilaElementos">
						<label>Valor máximo:</label>
						<input type="text" id="valorMaximoSlider" name="VMax" class="rgr" placeholder="Inserte valor Máx." /> 
					</div>
					<!-- FIN Contenedor interno de rangos numéricos para slider -->
				</div>
				<!-- FIN CONTENEDOR DE AGREGACIÓN DE LOS RANGOS SLIDER -->
				
				<div class="contenedorModelo1" id="contenedorDependencia">
					<label>¿Esta pregunta depende de la respuesta de otra pregunta anterior?:</label>
					<div id="contenedorFilaRespuestas">
						<input type="radio" name="tipoDependencia" value="Si" required>Si
						<input type="radio" name="tipoDependencia" value="No" checked>No
					</div>
					<!-- Div form dependencias de preguntas -->
					<div id="dependenciasPreguntas">
						<div class="contenedorFilaElementos">
							<label>Seleccione la pregunta de la que va a depender esta nueva:</label>
						</div>
						<div class="contenedorFilaElementos">
							<select name="selectPregunta" id="selectPregunta">
	                   		<option class="option" value="-1">---</option>
	                    	<c:forEach items="${preguntas}" var="pregunta">
	                        	<option class="option" value="${pregunta.idpregunta}">${pregunta.descripcion}</option>
	                   		</c:forEach>
	                   		</select>
						</div>
					</div>
					<!-- FIN Div form dependencias de preguntas -->
				</div>
				
				<!-- INICIO Div form orden de preguntas -->
				<div class="contenedorModelo1" id="contenedorOrdenPregunta">
					<label>¿Quiere ubicar la pregunta en una ubicación que no sea al final del formulario?:</label>
					<div id="contenedorFilaRespuestas">
						<input type="radio" name="ordenPregunta" value="Si" required>Si
						<input type="radio" name="ordenPregunta" value="No" checked>No
					</div>
					<!-- INICIO Div muestro/oculto -->
					<div id="ordenPreguntas">
						<div class="contenedorFilaElementos">
							<label>Seleccione la pregunta tras la cual ubicaremos esta nueva pregunta:</label>
						</div>
						<div class="contenedorFilaElementos">
							<select name="selectOrdenPregunta" id="selectOrdenPregunta">
	                   		<option class="option" value="-1">---</option>
	                    	<c:forEach items="${preguntas}" var="pregunta">
	                        	<option class="option" value="${pregunta.idpregunta}">${pregunta.descripcion}</option>
	                   		</c:forEach>
	                   		</select>
						</div>
					</div>
					<!-- FIN Div muestro/oculto -->
				</div>
				<!-- FIN Div form ubicacion de preguntas -->
				
				<!-- INICIO Div form ubicar bloque de preguntas -->
				<div class="contenedorModelo1" id="contenedorBloquePregunta">
					<label>¿Quiere agrupar la pregunta al bloque de maternidad?:</label>
					<div id="contenedorFilaRespuestas">
						<input type="radio" name="bloquePregunta" value="Si" required>Si
						<input type="radio" name="bloquePregunta" value="No" checked>No
					</div>
					<!-- INICIO Div muestro/oculto -->
					<div id="bloquePreguntas">
						<div class="contenedorFilaElementos">
							<label>Seleccione el bloque de preguntas:</label>
						</div>
						<div class="contenedorFilaElementos">
							<select name="selectBloquePregunta" id="selectBloquePregunta">
	                   		<option class="option" value="-1">---</option>
	                    	<c:forEach items="${bloquePreguntas}" var="bloque">
	                        	<option class="option" value="${bloque}">${bloque}</option>
	                   		</c:forEach>
	                   		</select>
						</div>
					</div>
					<!-- FIN Div muestro/oculto -->
				</div>
				<!-- FIN Div form ubicar bloque de preguntas -->
				
				<input type="submit" id="botonRegistrarPregunta" value="Registrar pregunta">
			</form>
			<button id="botonVolver" onclick="location.href='endometriosis.jsp'">Volver</button>
		</div>
		
		<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
		<script src='./jquery/formulariosjs.js'></script>
		
</body>

<!-- <script>
			$(document).ready(function(){
				var cuentaInputs = $('#contenedorRespuestasAgregar').children().length - 1;
				
				// Método que se encarga de añadir cajas de respuestas
				$('#agregar').click(function() {
					cuentaInputs++;
					$('<br class="fila' +cuentaInputs+ '" /><label class="fila' +cuentaInputs+'" for="dato' +cuentaInputs+'"> Respuesta ' +cuentaInputs+ ':</label><input type="text" name="resgistroRespuesta' +cuentaInputs+ '" class="fila' +cuentaInputs+ '" id="resgistroRespuestaId' + cuentaInputs +'" placeholder="Inserte Respuesta" required/>').appendTo('#contenedorRespuestasAgregar');
					if (2 == cuentaInputs) {
						$('<input type="button" id="eliminame" value="-"/>').insertAfter('#agregar')
					}
				});
				
				// Método que se encarga de eliminar cajas de respuestas
				$(document).on('click','#eliminame',function(){
					$('.fila'+cuentaInputs).remove();
					cuentaInputs--;
					if(1 == cuentaInputs){
						$(this).remove();
					}
				});
				
				// Método se encarga de mostrar/ocultar div caja respuestas
				$('#ContenedorTipoRespuesta').click(function(){

					let tipoRespuesta = $('input[name="tipoRespuesta"]:checked').val();
					if (null != tipoRespuesta && ('Radio' === tipoRespuesta || 'Check' === tipoRespuesta)) {
						// Muestro el div con cajas respuestas y oculto el rango numerico Slider
						$('#contenidoRespuestas').show();
						$('#contenidoRangosSlider').hide();
					}
					else if (null != tipoRespuesta && 'Slider' === tipoRespuesta) {
						// muestro el div para rangos numericos Slider y oculto cajas respuestas
						$('#contenidoRangosSlider').show();
						$('#contenidoRespuestas').hide();
					}
					else if (null != tipoRespuesta && 'Texto' === tipoRespuesta) {
						// Oculto los div de cajas respuesta y valores slider
						$('#contenidoRespuestas').hide();
						$('#contenidoRangosSlider').hide();
					}
				});
				
				//Método de test para reparacióin envio de registro pregunta
				$('#botonRegistrarPregunta').click(function(){
					console.log("Estoy presionando boton de enviar registro");
				});
				
				// Método se encarga de mostrar/ocultar div pregunta de la dependencia
				$('#contenedorDependencia').click(function(){
					console.log("entro en contenedorDependencia");
					
					let ordenRespuesta = $('input[name="tipoDependencia"]:checked').val();
					if (null != ordenRespuesta && 'Si' === ordenRespuesta ) {
						console.log("entro en SI");
						// Muestro el div con cajas respuestas
						$('#dependenciasPreguntas').show();
					}
					else {
						console.log("entro en NO");
						// Oculto el div con cajas respuesta
						$('#dependenciasPreguntas').hide();
					}
				});
				
				// Método se encarga de mostrar/ocultar div orden de pregunta
				$('#contenedorOrdenPregunta').click(function(){
					console.log("entro en contendorORden");
					let tipoRespuesta = $('input[name="ordenPregunta"]:checked').val();
					if (null != tipoRespuesta && 'Si' === tipoRespuesta ) {
						// Muestro el div con cajas respuestas
						$('#ordenPreguntas').show();
					}
					else {
						// Oculto el div con cajas respuesta
						$('#ordenPreguntas').hide();
					}
				});
				
				// Método se encarga de mostrar/ocultar div BLOQUE de pregunta
				$('#contenedorBloquePregunta').click(function(){
					console.log("entro en contendorBloquePreguntas");
					let tipoRespuesta = $('input[name="bloquePregunta"]:checked').val();
					if (null != tipoRespuesta && 'Si' === tipoRespuesta ) {
						// Muestro el div con cajas respuestas
						$('#bloquePreguntas').show();
					}
					else {
						// Oculto el div con cajas respuesta
						$('#bloquePreguntas').hide();
					}
				});
				
			});		
		</script> -->
</html>


