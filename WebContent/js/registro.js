

function mostrarComunidad (){
	
	
	var pais = document.getElementById("selectPais"); //id del selectorPaises
	var optionPais = pais.options[pais.selectedIndex].value; 
	
	
	if(optionPais != -1){
		document.getElementById('comunidades').style.display = 'block';
	}
	
}

function mostrarMunicipios (){
	
	var comunidad = document.getElementById("selectcomunidades");
	var optionComunidad = comunidad.options[comunidad.selectedIndex].value;
	
	
	if(optionComunidad != -1){
		document.getElementById('municipios').style.display = 'block';
	}
	
}