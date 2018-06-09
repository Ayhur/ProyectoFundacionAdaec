<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Estadisticas, informacion</title>
	</head>
	<body>
		
		<div>
			Sobre que pregunta desea realizar la consulta:
			  <!-- 					SELECTOR DE PAISES			 -->
	            <br/>
	            <form action="ServletCrearDiagraBarrasHorizontal" method="post">
	            	<select name="selectPregunta" id="selectPregunta">
	            	<option class="option" value="-1">---</option>
	            	<c:forEach items="${preguntas}" var="pregunta">
	            		<option class="option" value="${pregunta.idpregunta}">${pregunta.descripcion}</option>
	            	</c:forEach>
	            	</select><br/>
	            	
	            </form>
	            
	         
		</div>	
		
		
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