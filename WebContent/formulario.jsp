<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List" %>
<%@page import="modelos.Preguntas" %>
<%@page import="modelos.Respuestaspreguntas" %>
<%@page import="modelos.Medicamento" %>
<!DOCTYPE html>
<html>
	<head>
<!-- 		<meta http-equiv="Content-type" content="text/html; charset=utf-8" /> -->
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Rellene el formulario</title>
		<link rel="stylesheet" href="css/formulario.css">
	</head>
	<body>
	
		
		<form action="ServletRecogidaDatos" method="post">
			<%
			if(request.getAttribute("preguntas") instanceof List){
				
				//Recojo las preguntas y respuestas del servlet
				List<?>listaPreguntas=(List<?>)request.getAttribute("preguntas");
// 				List<?>listaRespuestas=(List<?>)request.getAttribute("respuestas");
				List<Respuestaspreguntas>listaRespuestas=(List<Respuestaspreguntas>)request.getAttribute("respuestas");
				List<Medicamento>listaMedicamentos=(List<Medicamento>)request.getAttribute("medicamentos");
				int k = 0;
				
				//Bucle para ir publicando las preguntas
				for(int i=0;i<listaPreguntas.size();i++) {
					//Contador para ir recorriendo las respuestas porque en
					//las preguntas de texto, la respuesta no esta registrada en bbdd
					//Entonces no se puede recorrer a la vez las dos listas con for
					//Ya que llegará un momento que intentarás leer una pregunta X
					//Pero no habrá respuesta X. Al no haber respuestas de preguntas de texto
					//numero de preguntas > numero de respuestas
					
					out.println("<div class='campoFormulario' id='cajaPregunta"+i+"'>");
							
					//Voy sacando pregunta y su respectiva respuesta
					Preguntas pregunta = (Preguntas)listaPreguntas.get(i);
					//Muestro pregunta
					out.println("<div class='cajaEnunciadoPregunta'>");
					out.println(pregunta.getDescripcion());
// 					out.println(pregunta.getDescripcion() + "<br/>");
					out.println("</div>");
					
					//Si el tipo de pregunta es 1 (texto)
					if(pregunta.getTipo() == 1) {
						//Saco el input text de texto
						out.println("<input type='text' name='" + pregunta.getTipo() + "_" + pregunta.getIdpregunta() + "'><br/>");
						//cierro caja de pregunta forumario
						out.println("</div>");
				    	
			    	
					} 
					//Si es pregunta de tipo 2 (radio)
					else if(pregunta.getTipo() == 2) {   
						//Siendo pregunta 2 se que hay respuesta. Tegno que buscarlas y que el idPregunta coincida con el idPregunta dentro de las respuestasArray
						
						for(Respuestaspreguntas respuesta : listaRespuestas) {
							if (null != respuesta && (respuesta.getIdPregunta() == pregunta.getIdpregunta())) {
								
								//Guardo en un array las posibles respuestas
						    	String[] respuestasRadio = respuesta.getDescripcion().split("-");
								
						    	//Imprimo cada respuesta
						    	for(int j=0;j<respuestasRadio.length;j++){
						    		//Genero una caja para la respuesta
						    		out.println("<div class='cajaRespuesta'>");
// 						    		out.println("<input type='radio' name='" + pregunta.getTipo() + "_" + pregunta.getIdpregunta() + "' id=rad"+i+"_"+j+" value='" + respuestasRadio[j] + "'> " + respuestasRadio[j] + "&nbsp;&nbsp;&nbsp;");
						    		out.println("<input type='radio' name='" + pregunta.getTipo() + "_" + pregunta.getIdpregunta() + "' id=rad"+i+"_"+j+" value='" + respuestasRadio[j] + "'> " + respuestasRadio[j]);
						    		
						    		//cierro caja de respuesta
							    	out.println("</div>");
						    	}
						    	
						    	// Si la pregunta tenía la opción adicional activada le agregamos al final una caja de texto
								if (true == pregunta.isCajaAdicional()) {
									out.println("<div class='cajaRespuesta'>");
									out.println("<input type='text' name='" + pregunta.getTipo() + "_" + pregunta.getIdpregunta() + "_at" + "'><br/>");
									out.println("</div>");
								}
						    	
						    	//cierro caja de pregunta forumario
						    	out.println("</div>");
						    	
						    	//poner un break para una vez localizado salga de bucle
							} //fin if
						} // fin for
							
					} 
			    	//Si es pregunta de tipo 3 (check)					
					else if (pregunta.getTipo() == 3) {	
						out.println("<div class='cajaGlobalRespuestas'>");
						//Siendo pregunta 3 se que hay respuesta. La saco
						for(Respuestaspreguntas respuesta : listaRespuestas) {
							if (null != respuesta && (respuesta.getIdPregunta() == pregunta.getIdpregunta())) {
								
								//Guardo en un array las posibles respuestas
						    	String[] respuestasCheck = respuesta.getDescripcion().split("-");
								
						    	//Imprimo cada respuesta
						    	for(int j=0;j<respuestasCheck.length;j++){
						    		out.println("<div class='cajaRespuesta'>");
						    		out.println("<input type='checkbox' name='" + pregunta.getTipo() + "_" + pregunta.getIdpregunta() + "_" + (j) + "'  id=chk"+i+"_"+j+" value='" + respuestasCheck[j] + "'> " + respuestasCheck[j] + "&nbsp;&nbsp;&nbsp;");
						    		out.println("</div>");
						    	}
						    	
						    	// Si la pregunta tenía la opción adicional activada le agregamos al final una caja de texto
								if (true == pregunta.isCajaAdicional()) {
									out.println("<div class='cajaRespuesta'>");
									out.println("<input type='text' name='" + pregunta.getTipo() + "_" + pregunta.getIdpregunta() + "'><br/>");
									out.println("</div>");
								}
								
						    	//cierro caja de pregunta forumario
						    	out.println("</div>");
						    	out.println("</div>");
						    	
						    	//poner un break para una vez localizado salga de bucle
							}
						}
						
					} 
					//Si es pregunta tipo 4 (slider progress bar)
					else if (4 == pregunta.getTipo()){
						
						for(Respuestaspreguntas respuesta : listaRespuestas) {
							if (null != respuesta && (respuesta.getIdPregunta() == pregunta.getIdpregunta())) {
								
								out.println("<div class='cajaRespuesta'>");
								
								//Guardo en un array las respuestas
						    	String[] respuestasSlider = respuesta.getDescripcion().split("-");
								// Generamos el slider con los valores
								out.println("<input type='range' name='" + pregunta.getTipo() + "_" + pregunta.getIdpregunta() + "' min='"+respuestasSlider[0]+"' max='"+respuestasSlider[1]+"' value='"+respuestasSlider[0]+"' oninput='this.form.amount_" + pregunta.getTipo() + "_" + pregunta.getIdpregunta() + ".value=this.value'/>");
								out.println("</div>");
								/** Debido a que el name empieza por numero, provoca que el boton del number no localize el name porque no se puede inicializar en los metodos html por numeros, tiene que ser letras,
								si le pones una 'a' delante tanto en el name de arriba como en la referencia vinculante de abajo despues de oninput funcionaria, entonces abria que cambiarlo todo
								para futuros parches hacerlo*/
								out.println("<div class='cajaRespuesta'>");
								out.println("<input type='number' name='amount_" + pregunta.getTipo() + "_" + pregunta.getIdpregunta() + "' min='"+respuestasSlider[0]+"' max='"+respuestasSlider[1]+"' oninput='this.form." + pregunta.getTipo() + "_" + pregunta.getIdpregunta() + ".value=this.value'/>");
								out.println("</div>");
								//cierro caja de pregunta forumario
					    		out.println("</div>");
							}
							
						}
					} //fin else if 4
				} //fin for principal de preguntas
				
				// INICIO bloque tabla medicamentos al final de formulario
				
				out.println("<div class='campoFormulario' id='cajaPreguntaMedicamentos'>");
				out.println("Señala que tratamientos has tomado a lo largo de tu vida y si te fueron bien o mal" + "<br/>");
				
				out.println("<table id='tablaMedicamentos' border='1'>");
					out.println("<tr>");
						out.println("<th>Medicamento</th>");
						out.println("<th>Marca si lo has tomado</th>");
						out.println("<th>Te fue bien</th>");
						out.println("<th>Te fue mal</th>");
					out.println("</tr>");
				//BUCLE GENERACIÓN MATRIZ TABLA
				for(Medicamento medicamento : listaMedicamentos){
					out.println("<tr>");
						out.println("<td>"+medicamento.getNombreMed()+"</td>");
						out.println("<td align='center'><input type='checkbox' id='check"+medicamento.getNombreMed()+"' name='check"+medicamento.getNombreMed()+"'></td>");
						out.println("<td align='center'><input type='radio' name='radio"+medicamento.getNombreMed()+"' value='bien' disabled></td>");
						out.println("<td align='center'><input type='radio' name='radio"+medicamento.getNombreMed()+"' value='mal' disabled></td>");
					out.println("</tr>");
				}
				out.println("</table>");
				// FIN bloque tabla medicamentos al final de formulario
				out.println("</div>");
			}
			%>
			<input type="hidden" name="userLog" value="${sessionScope.user.id}">
			<input type="submit" value="Enviar formulario" id="botonEnviar">
		
		</form>
		
		<!--  JQUERY IMPORT LIBRARY -->
		<script type="text/javascript" src="./jquery/jquery.js"></script>
		<!-- IMPORT JS -->
<!-- 		<script src="./js/jsFormularioJsp.js"></script> -->
		
		<script>
			
			
			$(document).ready(function(){
				
				//Método que se encarga de recoger todas las escuchas de los radio button
			    $('input[type=radio]').click(function(){
			    	
			        var elementoHTML = this.tagName+'#'+this.id;
			        
			        // Si la respuesta del radiobutton es 'Si' enviamos por ajax su name que contiene el ID
			        if ('SI'.toUpperCase() === this.value.toUpperCase()) {
			        	$.ajax({
							type: "post",
							dataType : "json",
							url : 'ServletBuscarDependenciasFormulario',
							data : {
								'selectPregunta' : this.name
							},
							success : function(json) {
								// incorporo la nueva pregunta
								console.log("exito! me devuelve lo siguiente: "+json);
								console.log(json);
								
								var elementoIdentificador = "#cajaDependeciaPregunta" + json.idpregunta;
								//Si el elemento no existe creamos los elemento HTML de agregación
								if ( !$(elementoIdentificador).length > 0 ) { 
									// variable que contendrá el ID del elemento padre
									var padre;
									
									//con ID 
									var parentElsis = $(elementoHTML).parent()
									  .map(function() {
										  padre = this.id;
										console.log(this.id);
									    return this.id;
									  })
									
									// transformo variable JS a Jquery
									var padrejq = '#'+padre;
									
									
									// PRUEBA LOCALIZAR PREGUNTA MADRE
									txt = document.getElementById(padre);
									if(txt.innerHTML.search("¿has podido ser mamá?") !== -1){
										console.log("He localizado la pregunta eres madre");
										// INICIO insercción bloque de preguntas de maternidad
										
										inserccionBloqueMaternidad(json, padrejq);
										
										// FIN insercción bloque
									}
									//FIN PRUEBA LOCALIZAR PREGUNTA MADRE
									
									
									// inserto despues del elemento HTML padre
									else if(1 === json.tipo){ // tipo text
										$(padrejq).after("<div class='campoFormulario' id='cajaDependeciaPregunta"+json.idpregunta+"'>"+json.descripcion+"<br/><input type='text' name='" + json.tipo + "_" + json.idpregunta + "'><br/></div>");
									}
									else if(2 === json.tipo){ // tipo radio
										
										var respuestas = json.descripcionRespuestas.split("-");
										
										// Agrega la nueva pregunta dentro de su caja correspondiente
										$(padrejq).after("<div class='campoFormulario' id='cajaDependeciaPregunta"+json.idpregunta+"'>"+json.descripcion+"<br/></div>");
										
										//encuentro la nueva pregunta para trabajar con el elementoHTML
										var preguntaNueva= '#cajaDependeciaPregunta'+json.idpregunta;
										
										// Agrego las respuestas
										for (var respuesta in respuestas) {
										   $(preguntaNueva).append("<input type='radio' name='" + json.tipo + "_" + json.idpregunta + " value='"+respuesta+"'>"+respuestas[respuesta]);
										}
										
										// Si la pregunta tenía la opción adicional activada le agregamos al final una caja de texto
										if (true == json.cajaAdicional) {
											$(preguntaNueva).append("<label>Otro (Rellene):</label>");
											$(preguntaNueva).append("<input type='text' name='" + json.tipo + "_" + json.idpregunta + "'><br/>");
										}
										
									}
									else if(3 === json.tipo){ // tipo check
										console.log("inserto check");
										var respuestas = json.descripcionRespuestas.split("-");
										
										// Agrega la nueva pregunta dentro de su caja correspondiente
										$(padrejq).after("<div class='campoFormulario' id='cajaDependeciaPregunta"+json.idpregunta+"'>"+json.descripcion+"<br/></div>");
										
										//encuentro la nueva pregunta para trabajar con el elementoHTML
										var preguntaNueva= '#cajaDependeciaPregunta'+json.idpregunta;
										
										// Agrego las respuestas
										for (var respuesta in respuestas) {
										   $(preguntaNueva).append("<input type='checkbox' name='" + json.tipo + "_" + json.idpregunta + " value='"+respuesta+"'>"+respuestas[respuesta]);
										}
										
										// Si la pregunta tenía la opción adicional activada le agregamos al final una caja de texto
										if (true == json.cajaAdicional) {
											$(preguntaNueva).append("<label>Otro (Rellene):</label>");
											$(preguntaNueva).append("<input type='text' name='" + json.tipo + "_" + json.idpregunta + "'><br/>");
										}
										
										
									}
									else{
										console.log("Es imposible llegar aqui sin hackear la pagina o no se ha traido informado json correctamente porque el json2 del codigo venia vacio");
									}
								} // end IF elemento no existe 
								
							} //end success
						}); //end ajax
			        
			        } //end IF respuesta SI
			        else if ('NO' === this.value.toUpperCase()){
			        	$.ajax({
							type: "post",
							dataType : "json",
							url : 'ServletBuscarDependenciasFormulario',
							data : {
								'selectPregunta' : this.name
							},
							success : function(json) {
								
								console.log("esto es josnlenght: "+json.length);
								
								if(undefined === json.length){
									console.log("json trae 1 elemento");
									// elimino el elemento
									var elementoHTML = "#cajaDependeciaPregunta"+json.idpregunta;
									$(elementoHTML).remove();
								}
								else{
									console.log("json trae mas de 1 elemento");
									// elimino los elementos
									json.forEach(elemento => {
										var elementoHTML = "#cajaDependeciaPregunta"+elemento.idpregunta;
										$(elementoHTML).remove();
									}); // fin bucle forEach
								} // fin else
								
							} // end success
						}); // end ajax
			        } // end IF respuesta NO
			    }) ;// end function radio
			    
			    // Método que se encarga de recoger escuchas de check buttons
			    $('input[type=checkbox]').click(function(){
			    	
			    	var elementoHTML = this.tagName+'#'+this.id;
			    	var radioButton = "radio" + this.id.substring(5);
			    	
			    	// Si el check de la tabla de medicamentos se activa, se habilita los radio de la misma fila
			    	if(this.checked){
			    		console.log("el checkbox esta seleccionado");
			    		$("input[name="+radioButton+"]").prop( "disabled", false );
			    	}
			    	else{
			    		console.log("el checkbox NO esta seleccionado");
			    		 $("input[name="+radioButton+"]").prop("disabled", true);
			    		 $("input[name="+radioButton+"]").prop("checked", false);
			    	}
			    }); // end function checkboxtype
			    
			    
			    // Funcion que inserta el bloque de preguntas de maternidad en el formulario
			    function inserccionBloqueMaternidad(json, padrejq){
			    	
			    	json.forEach(element => {
			    		console.log(element);
			    		
			    		var elementoIdentificador = "#cajaDependeciaPregunta" + element.idpregunta;
						//Si el elemento no existe creamos los elemento HTML de agregación
						if ( !$(elementoIdentificador).length > 0 ) { 
			    		
				    		// inserto despues del elemento HTML padre
							if(1 === element.tipo){ // tipo text
								$(padrejq).after("<div class='campoFormulario' id='cajaDependeciaPregunta"+element.idpregunta+"'>"+element.descripcion+"<br/><input type='text' name='" + element.tipo + "_" + element.idpregunta + "'><br/></div>");
							}
							else if(2 === element.tipo){ // tipo radio
								
								var respuestas = element.descripcionResp.split("-");
								
								// Agrega la nueva pregunta dentro de su caja correspondiente
								$(padrejq).after("<div class='campoFormulario' id='cajaDependeciaPregunta"+element.idpregunta+"'>"+element.descripcion+"<br/></div>");
								
								//encuentro la nueva pregunta para trabajar con el elementoHTML
								var preguntaNueva= '#cajaDependeciaPregunta'+element.idpregunta;
								
								// Agrego las respuestas
								for (var respuesta in respuestas) {
								   $(preguntaNueva).append("<input type='radio' name='" + element.tipo + "_" + element.idpregunta + " value='"+respuesta+"'>"+respuestas[respuesta]);
								}
								
							}
							else if(3 === element.tipo){ // tipo check
								console.log("inserto check");
								var respuestas = element.descripcionResp.split("-");
								
								// Agrega la nueva pregunta dentro de su caja correspondiente
								$(padrejq).after("<div class='campoFormulario' id='cajaDependeciaPregunta"+element.idpregunta+"'>"+element.descripcion+"<br/></div>");
								
								//encuentro la nueva pregunta para trabajar con el elementoHTML
								var preguntaNueva= '#cajaDependeciaPregunta'+element.idpregunta;
								
								// Agrego las respuestas
								for (var respuesta in respuestas) {
								   $(preguntaNueva).append("<input type='checkbox' name='" + element.tipo + "_" + element.idpregunta + " value='"+respuesta+"'>"+respuestas[respuesta]);
								}
							}
							else{
								console.log("Es imposible llegar aqui sin hackear la pagina o no se ha traido informado element correctamente porque el element del codigo venia vacio");
							}
						}
			    	}); // fin bucle forEach
			    	
			    } // fin metodo inserccion bloque maternidad
			    
// 			    $('#clearRadios').click(function() {
// 			        // use attr only if you're using an older version of jQuery
// 			        $('input[type=radio]').prop('checked', false);
// 			    });
			    
			    
			    
			}); //end method
		
		</script>
				
	</body>
</html>
