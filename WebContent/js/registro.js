
document.getElementById("aceptar_terminos").addEventListener('change', checkAccepted);

function checkAccepted(event) {
	console.log("estoy en el javascript checkaccepted");
	var btnEnviar = document.getElementById("btnEnviar");
	console.log(this.checked);
	var isNotChecked = !this.checked;
	btnEnviar.disabled = isNotChecked;

}

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