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
              <!--                     SELECTOR DE PAISES             -->
                <br/>
                <form action="ServletCrearDiagraBarrasHorizontal" method="post">
                    <select name="selectPregunta" id="selectPregunta" onChange="mostrarComunidad();">
                    <option class="option" value="-1">---</option>
                    <c:forEach items="${preguntas}" var="pregunta">
                        <option class="option" value="${pregunta.idpregunta}">${pregunta.descripcion}</option>
                    </c:forEach>
                    </select><br/>
<!--                     <input type="submit" value="Ver Datos"> -->
                    <a href ="ServletBorrarPreguntas?idpregunta=${pregunta.idpregunta}" 
                    onclick="return borrarPregunta()">BORRAR</a>
                </form>
                
        </div>    
    
    <script type="text/javascript">
    function borrarPregunta(){
    	var select = document.getElementById("selectPregunta");
    	var value = select.options[select.selectedIndex].value;
    	var ruta = "ServletBorrarPreguntas?idpregunta="+value;
    	window.location.href = ruta;
    	return false;
    }
    </script>    
        
    </body>
</html>