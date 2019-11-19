<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
   		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    	<link rel="stylesheet" href="css/registroPreguntaStyle.css">
        <title>Edición preguntas</title>
    </head>
    <body>
        
        <!-- INICIO DIV principal -->
        <div class="formularioRegistroPregunta">
            Sobre que pregunta desea realizar la consulta:
              <!--                     SELECTOR DE PAISES             -->
                <br/>
                <form action="ServletEditarPreguntas" method="post">
                    <select name="selectPregunta" id="selectPregunta">
                    <option class="option" value="-1">---</option>
                    <c:forEach items="${preguntas}" var="pregunta">
                        <option class="option" value="${pregunta.idpregunta}">${pregunta.descripcion}</option>
                    </c:forEach>
                    </select><br/>
                    
                    <input type="submit" id="botonRegistrarPregunta" value="Editar pregunta">
<%--                     <a href ="ServletEditarPreguntas?idpregunta=${pregunta.idpregunta}"  --%>
<!--                     onclick="return editarPregunta()">EDITAR</a> -->
                </form>
        	<button id="botonVolver" onclick="location.href='endometriosis.jsp'">Volver</button>
        </div>
        <!-- FIN DIV principal -->
    
    <script type="text/javascript">
    function editarPregunta(){
    	var select = document.getElementById("selectPregunta");
    	var value = select.options[select.selectedIndex].value;
    	var ruta = "ServletEditarPreguntas?idpregunta="+value;
    	window.location.href = ruta;
    	return false;
    }
    </script>    
        
    </body>
</html>