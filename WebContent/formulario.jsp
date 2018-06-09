<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List" %>
<%@page import="modelos.Preguntas" %>
<%@page import="modelos.Respuestaspreguntas" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Rellene el formulario</title>
		<link rel="stylesheet" href="css/formulario.css">
	</head>
	<body>
	
		
		<form action="ServletRecogidaDatos" method="post">
			<%
			if(request.getAttribute("preguntas") instanceof List){
				
				//Recojo las preguntas y respuestas del servlet
				List<?>listaPreguntas=(List<?>)request.getAttribute("preguntas");
				List<?>listaRespuestas=(List<?>)request.getAttribute("respuestas");
				int k = 0;
				
				//Bucle para ir publicando las preguntas
				for(int i=0;i<listaPreguntas.size();i++) {
					//Contador para ir recorriendo las respuestas porque en
					//las preguntas de texto, la respuesta no esta registrada en bbdd
					//Entonces no se puede recorrer a la vez las dos listas con for
					//Ya que llegará un momento que intentarás leer una pregunta X
					//Pero no habrá respuesta X. Al no haber respuestas de preguntas de texto
					//numero de preguntas > numero de respuestas
					
					out.println("<div class='campoFormulario'>");
							
					//Voy sacando pregunta y su respectiva respuesta
					Preguntas pregunta = (Preguntas)listaPreguntas.get(i);
					//Muestro pregunta
					out.println(pregunta.getDescripcion() + "<br/>");
					
					
					//Si el tipo de pregunta es texto (1)
					if(pregunta.getTipo() == 1){
						//Saco el input text de texto
						out.println("<input type='text' name='" + pregunta.getTipo() + "_" + pregunta.getIdpregunta() + "'><br/>");
				    	out.println("</div>");
					}else if(pregunta.getTipo() == 2){//Si es pregunta de tipo 2
						//Siendo pregunta 2 se que hay respuesta. La saco
						Respuestaspreguntas respuesta = (Respuestaspreguntas)listaRespuestas.get(k);
						
						//Guardo en un array las posibles respuestas
				    	String[] respuestasRadio = respuesta.getDescripcion().split("-");
				    	
				    	//Imprimo cada respuesta
				    	for(int j=0;j<respuestasRadio.length;j++){
				    		out.println("<input type='radio' name='" + pregunta.getTipo() + "_" + pregunta.getIdpregunta() + "' value='" + respuestasRadio[j] + "'> " + respuestasRadio[j] + "&nbsp;&nbsp;&nbsp;");
				    	}
				    	out.println("</div>");
				    	//Al haber sacado respuesta incremento contador de respuestas
				    	k++;
					}else if(pregunta.getTipo() == 3){//Si es pregunta de tipo 3
						
						//Siendo pregunta 3 se que hay respuesta. La saco
						Respuestaspreguntas respuesta = (Respuestaspreguntas)listaRespuestas.get(k);
						
						String[] respuestasCheck = respuesta.getDescripcion().split("-");
				    	for(int j=0;j<respuestasCheck.length;j++){
				    		out.println("<input type='checkbox' name='" + pregunta.getTipo() + "_" + pregunta.getIdpregunta() + "_" + (j) + "' value='" + respuestasCheck[j] + "'> " + respuestasCheck[j] + "&nbsp;&nbsp;&nbsp;");
				    	}
				    	out.println("</div>");
				    	//Al haber sacado respuesta incremento contador de respuestas
				    	k++;
					}
					
				}
			}
			%>
			<input type="hidden" name="userLog" value="${sessionScope.idUser}">
			<input type="submit" value="Enviar formulario" id="botonEnviar">
		
		</form>
	</body>
</html>
