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