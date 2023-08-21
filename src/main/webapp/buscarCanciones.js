/**
 * 
 */
 
document.getElementById("txtBusqueda").addEventListener("keyup", buscarCanciones);
document.getElementById("divLetraCancion").addEventListener("click", ocultarLetra);
 
 function buscarCanciones() {
	let textoBusqueda = document.getElementById("txtBusqueda").value;
	if (textoBusqueda != "") {
		axios.post(
			"BuscarCancionesPorTitulo",
			null,
			{params: {busqueda: textoBusqueda}}
		).then(function(respuesta) {
			imprimirCanciones(respuesta.data);
		}).catch(function (error) {
	    	console.error(error);
	  	});
	}
}

function imprimirCanciones(lista) {
	//console.log(lista);
	let divListaCanciones = document.getElementById("divListaCanciones");
	let contenidoHTML = "";
	if (lista.length > 0) {
		for (let cancion of lista) {
			contenidoHTML = contenidoHTML +
				`<div class="cancion">
					<span class="tituloCancion">${cancion.titulo}</span>
					<audio controls="controls" controlsList="nodownload">
						<source src="${"Canciones/"+cancion.urlFichero}" type="audio/mpeg"/>
					</audio>
					<input type="button" value="Letra" onclick="buscarLetra('${cancion.titulo}');"/>`;
			if (!cancion.publica) {
				contenidoHTML = contenidoHTML + 
					`<input type="button" value="Borrar" onclick="borrarCancion(${cancion.idCancion}, '${cancion.urlFichero}');"/>`;
			}
			contenidoHTML = contenidoHTML + '</div>';
					
		}
	} else {
		contenidoHTML = "<h2>No se ha encontrado ninguna canción</h2>";
	}
	divListaCanciones.innerHTML = contenidoHTML;
}

function buscarLetra(titulo) {
	let url = "http://localhost:8080/CancionesREST/api/titulo/" + titulo;
	axios.get(url)
		.then(respuesta => {
  		  	imprimirLetra(respuesta.data);
		})
		.catch(error => {
   		 	console.error(error);
		});
}

function imprimirLetra(cancion) {
	let divLetra = document.getElementById("divLetraCancion");
	let divTitulo = document.getElementById("divTituloCancion");
	//let letraHTML = cancion.letra.replaceAll("\n", "<br/>");
	divTitulo.innerHTML = cancion.titulo;
	divLetra.innerHTML = 
		`<pre>${cancion.letra}</pre>`;
	divLetra.style.visibility = "visible";
	let contenedorModal = document.getElementById("contenedorModal");
	contenedorModal.modal('toggle'); 
}

function ocultarLetra() {
	let contenedorModal = document.getElementById("contenedorModal");
	contenedorModal.modal('toggle'); 
}

function borrarCancion(idCancion, urlFichero) {
	if (confirm("Desea eliminar esta canción?")) {
		axios.post(
				"BorrarCancion",
				null,
				{params: {cancion: idCancion, fichero: urlFichero}}
			).then(function(respuesta) {
				if (respuesta.data) {
					buscarCanciones();
				} else {
					alert("No se ha podido eliminar la canción");
				}
			}).catch(function (error) {
		    	console.error(error);
		  	});
	}
}




