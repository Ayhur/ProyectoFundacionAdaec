<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Registrar medicamentos</title>
		<link rel="stylesheet" href="css/registroPreguntaStyle.css">
	</head>
<body>

	<!-- INICIO DIV FORMULARIO -->
	<div class="formularioRegistroPregunta">
		<h1>Registrar pregunta/respuesta para el formulario</h1>
		<form action="ServletRegistroMedicamentos" method="post" name="formularioRegistroMedicamentos" id="formularioRegistroMedicamentos">
			
			<div class="contenedorModelo1" id="contenedorBloquePregunta">
				<label>Introduzca los medicamentos:</label><br/>

				<!-- Contenedor de cajas agregar respuesta a preguntas nuevas -->
				<div id="contenedorRespuestasAgregar" class="contenedorFilaElementos">
					<label>Medicamento 1:</label>
					<input type="text" id="resgistroRespuestaId1" name="resgistroRespuesta1" class="rgr" placeholder="Inserte Respuesta" /> 
				</div>
				<!-- FIN Contenedor de cajas agregar respuesta a preguntas nuevas -->
				<input type="button" id="agregar" value="+" />
			</div>
			
			<input type="submit" id="botonRegistrarPregunta" value="Registrar pregunta">
			
			
		</form>
		<button id="botonVolver" onclick="location.href='endometriosis.jsp'">Volver</button>
	
	
	</div>
	<!-- FIN DIV FORMULARIO -->
	
	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script src='./jquery/formulariosjs.js'></script>
	
</body>
</html>