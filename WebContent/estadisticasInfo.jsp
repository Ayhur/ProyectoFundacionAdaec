<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Estadisticas, informacion</title>
		<link rel="stylesheet" href="css/registroPreguntaStyle.css">
	</head>
	<body>
		
		<!-- INICIO contenedor de la pagina entera -->
		<div class="formularioRegistroPregunta">
		
			<!-- INICIO contenedor buscador -->
			<div id="contenedorBuscador">
			  
	            <form action="ServletCrearDiagraBarrasHorizontal" method="post">
	            	<div class="contenedorFilaEnunciados">
	            		<label>Sobre que pregunta desea realizar la consulta:</label>
	            	</div>
	            	<div class="contenedorFilaDatos">
	            		<!-- 				SELECTOR DE PREGUNTAS			 -->
		            	<select name="selectPregunta" id="selectPregunta">
		            	<option class="option" value="-1">---</option>
		            	<c:forEach items="${preguntas}" var="pregunta">
		            		<option class="option" value="${pregunta.idpregunta}">${pregunta.descripcion}</option>
	            		</c:forEach>
	            		</select>
	            		<!-- 			FIN	SELECTOR DE PREGUNTAS			 -->
	            	</div>
	            	
	            	<div class="contenedorFilaEnunciados">
	            		<label>Localidad:</label>
            		</div>
	            	<!-- 				SELECTOR DE LOCALIDAD			 -->
	            	<!-- 			FIN	SELECTOR DE LOCALIDAD			 -->
	            	<div class="contenedorFilaEnunciados">
	            		<label>Comunidad:</label>
            		</div>
	            	<!-- 				SELECTOR DE COMUNIDAD			 -->
	            	<!-- 			FIN	SELECTOR DE COMUNIDAD			 -->
	            	<div class="contenedorFilaEnunciados">
	            		<label>Pais:</label>
            		</div>
	            	<!-- 				SELECTOR DE PAIS			 -->
	            	<!-- 			FIN	SELECTOR DE PAIS			 -->
	            	
	            	
	            	
	            </form>
			</div>
            <!-- FIN contenedor buscador -->
	         
		</div>
		<!-- FIN contenedor de la pagina entera -->
		
		
		<canvas id="myChart" width="400" height="250"></canvas>
		<br/>
		<button id="botonVolver" onclick="location.href='endometriosis.jsp'">Volver al menu</button>
		
		
		<!--  JQUERY IMPORT LIBRARY -->
		<script type="text/javascript" src="./jquery/jquery.js"></script>
		<!--  		JS 	IMPORT LIBRARY		 -->
		<script src="./js/Chart.js"></script>
		<!--    	JQUERY    -->
		<script src="./jquery/crearDiagramaBarras.js"></script>
		
		
	</body>
</html>