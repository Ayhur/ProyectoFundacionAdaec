<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cuestionario</title>
</head>
<body>
	<div></div>
	<c:forEach items="${preguntas}" var="preguntas">
		<div style="margen: 8px">
			idpregunta:${preguntas.idpregunta}<br />
			descripcion:${preguntas.descripcion}<br /> 
			tipo:${preguntas.tipo}<br />

		</div>

	</c:forEach>


</body>
</html>