<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html;"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;">
		<title>Registrate para entrar en la pagina web</title>
		<!-- CSS -->
		<link rel="stylesheet" href="css/style.css">
		<link rel="stylesheet" href="css/registrostyle.css">
	</head>
	
	<body>
		 <div class="registro">
	      	<div id="RegistroUsuario">
	
		
		      <!-- VALIDACIONES POR CARLOS -->
				<div style="color:red">&nbsp;${mensajenombre}</div>
				<div style="color:red">&nbsp;${mensajeapellido}</div>
				<div style="color:red">&nbsp;${mensajefechanacimiento}</div>
				<div style="color:red">&nbsp;${mensajeemail}</div>
				<div style="color:red">&nbsp;${mensajeusuario}</div>
				<div style="color:red">&nbsp;${mensajepassword}</div>
				<div style="color:red">&nbsp;${mensajeconconrdancia}</div>
			  <!--  FIN VALIDACIONES CARLOS -->
<!-- 			  <div> -->
<!-- 			  		<p>hola</p> -->
<!-- 					 <img src="D:\ENDOMETRIOSIS - proyecto - web  todo\workspace\zProyectoEverisFinV0003\WebContent\img\fleha.png"> -->
<!-- 			 </div> -->
			  
<!-- 			 <a href="login.jsp"><img src="img/login.png" width='1200' alt=''></a> -->
		      
	        <form action="ServletRegistroUsuario"	 method="post">
	
	          <fieldset class="clearfix">
				
	            <label id="label">Nombre:</label>
	            <input type="text" name="campoNombre" required>
	            
	            <label id="labelSegundoCampo">Apellidos:</label>
	            <input type="text" name="campoApellidos" required>
	            <br/>
	            
	            <label id="label">Fecha de nacimiento:</label>
	            <input id="campofecha" type="date" name="campoFechaNacimiento" required> 
	            
	            <label id="labelSegundoCampo">DNI:</label>
	            <input type="text" name="campoDNI" required>
	            <br/>
	            
	            <!-- 					SELECTOR DE PAISES			 -->
	            <label id="label">Pais:</label>
	            <select name="selectPais" id="selectPais" onChange="mostrarComunidad();">
	            	<option class="option" value="-1">---</option>
	            	<c:forEach items="${paises}" var="pais">
	            		<option class="option" value="${pais.id}">${pais.nombre}</option>
	            	</c:forEach>
	            </select>
	            <!-- 						FIN PAISES			 -->
	            
	            <!-- 					SELECTOR DE COMUNIDADES			 -->
	            <div id='comunidades'>            	
	            	<label id="label">Comunidad Autónoma:</label>
		            <select name="selectcomunidades" id="selectcomunidades" onChange="mostrarMunicipios();" >
		            	<option class="option" value="-1">------</option>
		            </select>
	            </div>
	            <!-- 						FIN COMUNIDADES			 -->
	            
	            <!-- 					SELECTOR DE MUNICIPIOS			 -->
	            <div id='municipios'>	
		            <label id="label">Municipio:</label>
		            <select name="selectMunicipios" id="selectMunicipios">
		          	  <option class="option" value="-1">------</option>
		            </select>
	            </div>
	             <!-- 						FIN MUNICIPIOS			 -->
	            
	            <label id="label">Email:</label>
	            <input type="email" name="campoEmail" required>
	           	<br>
	           	
	            <label id="label">Usuario:</label>
	            <input type="text" name="campoUsuario" required>
	            <br/>
	            
	            <label id="label">Contraseña:</label>
	            <input type="password" name="campoPassword" required>
	             <br/>
	           
			    <label id="label">Repita la contraseña:</label>
	            <input type="password" name="campoRepetirPassword" required>
	           
				<input type="submit" value="Registrarse">
	
	          </fieldset>
	
	        </form>
	
	      </div> <!-- end login -->
	    </div>
		
		<!--  JQUERY -->
		<script type="text/javascript" src="./jquery/jquery.js"></script>
		<script type="text/javascript" src="./jquery/registroJQ.js"></script>
		
		<!--  JS -->
		<script src="./js/registro.js"></script>
		
	</body>
</html>