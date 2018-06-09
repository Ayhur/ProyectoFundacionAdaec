<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Bienvenido  a "Sociedad endometriosis"</title>
		<!-- CSS -->
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<div id="clouds">
			<div class="cloud x1"></div>
			<!-- Time for multiple clouds to dance around -->
			<div class="cloud x2"></div>
			<div class="cloud x3"></div>
			<div class="cloud x4"></div>
			<div class="cloud x5"></div>
		</div>
	
	   <div class="container">
	      <div id="login">
	
		<!-- Validacion por carlos -->
		<div style="color:red">&nbsp;${mensajelogin}</div>      
		      
	        <form action="ServletLogin"	 method="post">
	
	          <fieldset class="clearfix">
	
	            <p><span class="fontawesome-user"></span><input type="text" name="user" value="Username" onBlur="if(this.value == '') this.value = 'Username'" onFocus="if(this.value == 'Username') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Username" -->
	            <p><span class="fontawesome-lock"></span><input type="password" name="pass"  value="Password" onBlur="if(this.value == '') this.value = 'Password'" onFocus="if(this.value == 'Password') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Password" -->
	            <p><input type="submit" value="Entrar"></p>
	
	          </fieldset>
	
	        </form>
	
	         <p>¿No eres miembro? <a href="ServletCargaDeElementosRegistro" class="blue">Registrate ahora</a><span class="fontawesome-arrow-right"></span></p>
	
	      </div> <!-- end login -->
	
	    </div>
		<div><img src="img/login.png" width='1200' alt=''></div>
	</body>
</html>
