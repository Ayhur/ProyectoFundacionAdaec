<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!-- SI NO HAY UNA SESION LOCALIZADA SE REDIRIGIRÁ AL USUARIO UNA Y OTRA VEZ A LOGIN -->
<c:if test="${ sessionScope.idUser == null }">
	<span style="color: red;">Debes identificarte para acceder al sitio web</span><br/>
	<meta http-equiv="refresh" content="1; url=login.jsp">
	
</c:if>
<!-- SI  HAY UNA SESION LOCALIZADA SE PODRÁ MOVER AL SIGUIENTE PUNTO -->
<c:if test="${ sessionScope.idUser != null }">
	<span style="color: red;">Usuario identificado, redirigiendo a la web endometriosis</span><br/>
	<meta http-equiv="refresh" content="10; url=endometriosis.jsp">
</c:if>	
