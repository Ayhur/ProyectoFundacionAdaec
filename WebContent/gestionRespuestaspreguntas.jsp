<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:forEach items="${respuestaspreguntas}" var="respuestapregunta">
	<div style="margin: 8px">
		descripcion: ${respuestapregunta.descripcion} <br/>
		
	</div>

</c:forEach>


</body>
</html>