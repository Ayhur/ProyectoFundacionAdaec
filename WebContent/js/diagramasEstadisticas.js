function init(){
	
	for (var i = 0; i<valoresXrespuestas.lenght; i++){
		console.log(valoresXrespuestas[i]);
	}
	
}



function crearDiagramaDeBarras(){
	var ctx = document.getElementById("myChart").getContext('2d');
	var myChart = new Chart(ctx, {
	    type: 'bar',
	    data: {
	        labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],   /* agregar los distintos valores de respuesta aqui para nombre de barras*/
	        datasets: [{
	            label: '# of Votes',
	            data: [12, 19, 3, 5, 2, 3], /* agregar aqui los datos (numericos) para los anteriores mercionados */
	            backgroundColor: [
	                'rgba(255, 99, 132, 0.2)',  /* generar aleatoriamente */
	                'rgba(54, 162, 235, 0.2)',
	                'rgba(255, 206, 86, 0.2)',
	                'rgba(75, 192, 192, 0.2)',
	                'rgba(153, 102, 255, 0.2)',
	                'rgba(255, 159, 64, 0.2)'
	            ],
	            borderColor: [   				/* es el mismo que el de arriba pero mas solido (4º valor) */
	                'rgba(255,99,132,1)',
	                'rgba(54, 162, 235, 1)',
	                'rgba(255, 206, 86, 1)',
	                'rgba(75, 192, 192, 1)',
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


