<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Guardar cambios</title>
<link rel="stylesheet" href="css/registroPreguntaStyle.css">
</head>
<body>

	<div class="formularioRegistroPregunta2">	
		<h1>Secccion para editar la pregunta</h1>
		<form action="ServletGuardarCambios" method="post">
			<label>Pregunta a editar:</label><br/>
			<input type="text" size="36" name="guardarPregunta" value="${preguntaAeditar.descripcion}">
			<input type="hidden" name="idPreguntaEditada" value="${preguntaAeditar.idpregunta}"><br/>	
			
			<c:if test="${respuestaAeditar != null}">
				<br/><br/><label>Respuesta a editar:</label><br/>
				<label>Recuerde separar las respuestas con guiones ("-")</label><br/>
				<input type="text" name="guardarRespuesta" value="${respuestaAeditar.descripcion}"><br/>
			</c:if>
			
			<input type="submit" value="Guardar cambios">
			
		</form>
		<button id="botonVolver" onclick="location.href='endometriosis.jsp'">Volver</button>
	
	
	</div>
	

</body>
</html>