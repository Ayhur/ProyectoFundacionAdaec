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
<!-- 		  @Ayhur: Voy a comentar ciertos campos por temas de estilo y obsolescencia (required evita que ciertas validaciones sean necesarias) -->
<%-- 				<div style="color:red">&nbsp;${mensajenombre}</div> --%>
<%-- 				<div style="color:red">&nbsp;${mensajeapellido}</div> --%>
<%-- 				<div style="color:red">&nbsp;${mensajefechanacimiento}</div> --%>
				<div style="color:red">&nbsp;${mensajeemail}</div>
				<div style="color:red">&nbsp;${mensajeusuario}</div>
				<div style="color:red">&nbsp;${mensajepassword}</div>
				<div style="color:red">&nbsp;${mensajeconconrdancia}</div>
			  <!--  FIN VALIDACIONES CARLOS -->
			  
		      
	        <form action="ServletRegistroUsuario"	 method="post">
	
	          <fieldset class="clearfix">
				
	            <label class="label">Nombre:</label>
	            <input type="text" name="campoNombre" required>
	            
	            <label id="labelSegundoCampo">Apellidos:</label>
	            <input type="text" name="campoApellidos" required>
	            <br/>
	            
	            <label class="label">Fecha de nacimiento:</label>
	            <input id="campofecha" type="date" name="campoFechaNacimiento" required> 
	            
	            <label id="labelSegundoCampo">DNI:</label>
	            <input type="text" name="campoDNI" required>
	            <br/>
	            
	            <!-- 					SELECTOR DE PAISES			 -->
	            <label class="label">Pais:</label>
	            <select name="selectPais" id="selectPais" onChange="mostrarComunidad();">
	            	<option class="option" value="-1">---</option>
	            	<c:forEach items="${paises}" var="pais">
	            		<option class="option" value="${pais.id}">${pais.nombre}</option>
	            	</c:forEach>
	            </select>
	            <!-- 						FIN PAISES			 -->
	            
	            <!-- 					SELECTOR DE PROVINCIAS			 -->
	            <div id='comunidades'>            	
	            	<label class="label">Provincia:</label>
		            <select name="selectcomunidades" id="selectcomunidades" onChange="mostrarMunicipios();" >
		            	<option class="option" value="-1">------</option>
		            </select>
	            </div>
	            <!-- 						FIN PROVINCIAS			 -->
	            
	            <!-- 					SELECTOR DE MUNICIPIOS			 -->
	            <div id='municipios'>	
		            <label class="label">Municipio:</label>
		            <select name="selectMunicipios" id="selectMunicipios">
		          	  <option class="option" value="-1">------</option>
		            </select>
	            </div>
	             <!-- 						FIN MUNICIPIOS			 -->
	            
	            <label class="label">Email:</label>
	            <input type="email" name="campoEmail" required>
	           	<br>
	           	
	            <label class="label">Usuario:</label>
	            <input type="text" name="campoUsuario" required>
	            <br/>
	            
	            <label class="label">Contraseña:</label>
	            <input type="password" name="campoPassword" required>
	             <br/>
	           
			    <label class="label">Repita la contraseña:</label>
	            <input type="password" name="campoRepetirPassword" required>
	             <br/>
	           	
	           	<input type="checkbox" name="aceptar_terminos" id="aceptar_terminos" value="aceptar_terminos" required /> 
	           	<label id="terminoslbl">He leído y acepto la <a href="#" target="_blank">Política de Privacidad</a></label>
	           	 <br/>
				<input type="submit" id="btnEnviar" value="Registrarse" disabled>
	
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