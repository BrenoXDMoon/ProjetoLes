function trocarCartao(){
	document.getElementById("a").classList.toggle("d-none");
	document.getElementById("b").classList.toggle("d-none");
}

function listaCartao(){
	console.log("entrou");
	document.getElementById("listaCartao").classList.toggle("d-none");
}

document.getElementById("botaoCartao").addEventListener("click", listaCartao);