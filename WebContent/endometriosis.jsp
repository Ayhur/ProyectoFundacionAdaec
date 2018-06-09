<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Endometriosis</title>
		<link rel="stylesheet" href="css/menu.css">
	</head>
	<body>
	
	
	
	
	
	
	
	
	
	<c:if test="${sessionScope.idUser != null}">
<%-- 		Bienvenido: ${sessionScope.idUser} <br/> --%>
		
		<div id="divUserInfo">
			<h3>INFORMACION:</h3>
			El siguiente formulario que usted va a rellenar ayudará a <br/>futuras investigaciones.<br/><br/>
			
			Todos los datos que rellene del formulario serán almacenados <br/>en una base de datos. <br/><br/>
			
			El formulario es anónimo. En ningun momento del proceso <br/>se vincula los datos registrados 
			con el usuario que se encuentra<br/> conectado en ese momento. <br/><br/>
			
			Gracias por su ayuda.<br/>
		
		</div>
		
		<div>
			
			<nav id="navUser">
				<ul class="dropdown">
				        	<li><a href="ServletCreaFormulario">FORMULARIO</a>
				        	</li>
				        	<li><a href="ServletLogOut">SALIR</a>
				        	</li>
				        </ul>
				</nav> 
			
			
		
		</div>
<!-- 		<!--Para ir al formulario --> 
<!-- 		<a href="ServletCreaFormulario">IR AL FORMULARIO</a> -->
<!-- 		<a href="ServletLogOut">SALIR</a>&nbsp; -->
		
		
		
	</c:if>
	
	
	
	
	
	
	
	
	
	
	
	<c:if test="${sessionScope.idUserAdmin != null}">
<%--  		Bienvenido ADMINISTRADOR: ${sessionScope.idUser} <br/>  --%>
<!-- 		<!-- Registrar editar borrar nuevas preguntas redirecciones -->
<!-- 		<a href="registrarPreguntaRespuesta.jsp">REGISTRAR PREGUNTA/RESPUESTA</a><br/> -->
<!-- 		<a href="ServletCargaDeDatosPreviaParaEditarPregunta">EDITAR PREGUNTA/RESPUESTA</a><br/> -->
<!-- 		<a href="ServletCargaDeDatosPreviaParaBorrarPregunta">Borrar Preguntas</a>&nbsp; -->
<!-- 		<a href="ServletCargaDeDatosPreviaParaBusquedaPregunta">Buscar Datos por Preguntas</a>&nbsp;<br/> -->
<!-- 		<a href="ServletLogOut">salir</a>&nbsp; -->


<!-- 	RAFA CODIGO -->

<!--   <nav role="navigation" class="nav"> -->
<!--     <ul class="nav-items"> -->
<!--         <li class="nav-item dropdown"> -->
<!--             <a href="#" class="nav-link"><span>OPCIONES DE FORMULARIO</span></a> -->
<!--             <nav class="submenu"> -->
<!--                 <ul class="submenu-items"> -->
<!--                     <li class="submenu-item"><a href="registrarPreguntaRespuesta.jsp" class="submenu-link">CREAR PREGUNTA</a></li> -->
<!--                 </ul> -->
<!--                  <ul class="submenu-items"> -->
<!--                     <li class="submenu-item"><a href="ServletCargaDeDatosPreviaParaEditarPregunta" class="submenu-link">EDITAR PREGUNTA	</a></li> -->
<!--                 </ul> -->
<!--                   <ul class="submenu-items"> -->
<!--                     <li class="submenu-item"><a href="ServletCargaDeDatosPreviaParaBorrarPregunta" class="submenu-link">BORRAR PREGUNTAS</a></li> -->
<!--                 </ul> -->
<!--             </nav> -->
<!--         </li>  -->
    
<!--         <li class="nav-item"> -->
<!--             <a href="ServletCargaDeDatosPreviaParaBusquedaPregunta" class="nav-link"><span>ESTADISTICAS</span></a> -->
<!--         </li> -->
<!--         <li class="nav-item"> -->
<!--             <a href="ServletLogOut" class="nav-link"><span>DESCONECTAR</span></a> -->
<!--         </li> -->
       
<!--     </ul> -->
<!-- </nav> -->
<!-- FIN RAFA CODIGO -->






<nav>
<ul class="dropdown">
        	<li class="drop"><a href="#">FORMULARIO</a>
        		<ul class="sub_menu">
							<li><a href="registrarPreguntaRespuesta.jsp">Crear Pregunta</a></li>
							<li><a href="ServletCargaDeDatosPreviaParaEditarPregunta">Editar Pregunta</a></li>
							<li><a href="ServletCargaDeDatosPreviaParaBorrarPregunta">Borrar Pregunta</a></li>
        		</ul>
        	</li>
        	<li class="drop"><a href="#">ESTADISTICAS</a>
        		<ul class="sub_menu">
        			<li><a href="ServletCargaDeDatosPreviaParaBusquedaPregunta">Mostrar Diagrama</a></li>
        		</ul>
        	</li>
        	<li><a href="ServletLogOut">SALIR</a>
        	</li>
        </ul>
</nav> 





	</c:if>
	
	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script src="./js/menu.js"></script>
		<!-- Queda pendiente mostrar por aquÃ­ mensaje de formulario realizado-->
		<!-- Variable setAttribute mensajeFormSubido en servletRecogidaDatos-->
	</body>
</html>
