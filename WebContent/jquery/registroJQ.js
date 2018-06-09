/* se le transfiere el ID del componente, en este caso el selector*/
$("#selectPais").change(
		function() {
			var valueSeleccionado = $("#selectPais").val();
			// se lo mandamos al servlet para que nos de una respuesta:
			$.ajax({
				dataType : "json",
				url : 'ServletCargaDeElementosRegistro',
				data : {
					'paisSeleccionado' : valueSeleccionado
				},
				success : function(json) { // genero las opciones de
											// comunidades autonomas
					// ahroa mismo json es directamente un array, no hay que
					// transformar nada
					//console.log(json);
					for (x in json) {
						$("#selectcomunidades").append(
								"<option class='option' value='"
										+ json[x].id + "'>"
										+ json[x].nombre + "</option>");
						//console.log(json[x].nombre);

					}
				}// end success
			});// end ajax
		}// end function
);// end change


$("#selectcomunidades").change(
		function() {
			var valueSeleccionado = $("#selectcomunidades").val();
			alert("valueSeleccionado: " + valueSeleccionado);
			// se lo mandamos al servlet para que nos de una respuesta:
			$.ajax({
				dataType : "json",
				url : 'ServletCargaDeElementosRegistro',
				data : {
					'comunidadSeleccionada' : valueSeleccionado
				},
				success : function(json) { // genero las opciones de
											// comunidades autonomas
					// ahroa mismo json es directamente un array, no hay que
					// transformar nada
					console.log(json);
					for (x in json) {
						$("#selectMunicipios").append(
								"<option class='option' value='"
										+ json[x].id + "'>"
										+ json[x].nombre + "</option>");
						console.log(json[x].nombre);

					}
				}// end success
			});// end ajax
		}// end function
);// end change

