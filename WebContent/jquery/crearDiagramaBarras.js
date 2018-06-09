$("#selectPregunta").change(
		function() {
			//alert("mostrar grafica de lso datos seleccionados");
			var valueSeleccionado = $("#selectPregunta").val();
			// se lo mandamos al servlet para que nos de una respuesta:
			$.ajax({
				type: "post",
				dataType : "json",
				url : 'ServletCrearDiagraBarrasHorizontal',
				data : {
					'selectPregunta' : valueSeleccionado
				},
				success : function(json) { // genero las opciones de
											// comunidades autonomas
					// ahroa mismo json es directamente un array, no hay que
					// transformar nada
					console.log(json);
					crearDiagramaDeBarras(json);
					
					
					/*for (x in json) {
						$("#selectcomunidades").append(
								"<option class='option' value='"
										+ json[x].id + "'>"
										+ json[x].nombre + "</option>");
						//console.log(json[x].nombre);

					}*/
				}// end success
			});// end ajax
		}// end function
);// end change

function devolverEtiquetasS(json){
	let cadena = [];
	
	/*ojo si la coma añadida al final del ultimo bucle me afecta a el codigo de diagrama y provoca error*/
	json.forEach(function (valor, indice, json){
		
		cadena += "'"+valor.etiquetasBarra+"',";
		
	});
	
	console.log(cadena);
	return cadena;
}


function devolverEtiquetas(json){
	let cadena = "";
	
	/*ojo si la coma añadida al final del ultimo bucle me afecta a el codigo de diagrama y provoca error*/
	json.forEach(function (valor, indice, json){
		
		cadena += "'"+valor.etiquetasBarra+"',";
		
	});
	
	console.log(cadena);
	return cadena;
}

function devolverEtiquetasEnArray(json){
	let cadena = [];
	
	/*ojo si la coma añadida al final del ultimo bucle me afecta a el codigo de diagrama y provoca error*/
	json.forEach(function (valor, indice, json){
		
		cadena.push(valor.etiquetasBarra);
		
	});
	
	console.log(cadena);
	return cadena;
}

function devolverValoresBarra(json){
	let cadena = [];
	
	json.forEach(function (valor, indice, json){
		
		cadena.push(valor.valorEetiquetasBarra);
	});
	
	console.log(cadena);
	return cadena;
}

function devolverColorFondoBarra(json){
	
	let cadena = [];
	
	json.forEach(function (valor, indice, json){
		
		//console.log("En el índice " + indice + " hay este valor: " + valor.valorColorFondoBarraYContorno);
		cadena.push("rgba("+valor.valorColorFondoBarraYContorno + valor.solidedColorFondoBarra+")");
	});
	
	console.log(cadena);
	return cadena;
}

function devolverColorFondoContornoBarra(json){
	
	let cadena = [];
	
	json.forEach(function (valor, indice, json){
		
		//console.log("En el índice " + indice + " hay este valor: " + valor.valorColorFondoBarraYContorno);
		cadena.push("rgba("+valor.valorColorFondoBarraYContorno+""+valor.solidedColorBorderBarra+")");
	});
	
	console.log(cadena);
	return cadena;
}

/*funcion para crear el diagrama*/
function crearDiagramaDeBarras(json){
	//alert("json a mostrar en charts: " + json);
	var etiquetas = devolverEtiquetas(json);
	//alert("tiene: "+etiquetas.lenght);
	console.log("json para el chart:");
	console.log(json);
	var etiquetasEnArray = devolverEtiquetasEnArray(json);
	var valoresBarras = devolverValoresBarra(json);
	var colorFondoBarras = devolverColorFondoBarra(json);
	var colorBordeBarras = devolverColorFondoContornoBarra(json);  //REVIENTA
	console.log("devolverEtiquetas");
	console.log(etiquetasEnArray);
	
	
	var ctx = document.getElementById("myChart").getContext('2d');
//	var myChart = new Chart(ctx, {
//	    type: 'bar',
//	    data: {
//	        labels: [etiquetasEnArray],//devolverEtiquetas(json)],   /* agregar los distintos valores de respuesta aqui para nombre de barras*/
//	        datasets: [{
//	            label: '# of Votes',
//	            data: [devolverValoresBarra(json)], /* agregar aqui los datos (numericos) para los anteriores mercionados */
//	            backgroundColor: [devolverColorFondoBarra(json)
//	            ],
//	            borderColor: [devolverColorFondoContornoBarra(json)
//	            ],
//	            borderWidth: 1
//	        }]
//	    },
//	    options: {
//	        scales: {
//	            yAxes: [{
//	                ticks: {
//	                    beginAtZero:true
//	                }
//	            }]
//	        }
//	    }
//	});	
//	
	var myChart = new Chart(ctx, {
	    type: 'bar',
	    data: {
	        labels: etiquetasEnArray,   /* agregar los distintos valores de respuesta aqui para nombre de barras*/
	        datasets: [{
	            label: '# of Votes',
	            data: valoresBarras, /* agregar aqui los datos (numericos) para los anteriores mercionados */
	            backgroundColor: colorFondoBarras,
	            borderColor: [   				/* es el mismo que el de arriba pero mas solido (4º valor) */
	                'rgba(255,99,132,1)',
	                'rgba(54, 162, 235, 1)',
	                'rgba(153, 102, 255, 1)',
	                'rgba(255, 159, 64, 1)'
	            ],
	            borderWidth: 1
	        }]
	    },
	    options: {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero:true
	                }
	            }]
	        }
	    }
	});	

}


