

var documento = document.getElementById("add_field");



documento.onclick = function() {
    var clone = document.getElementById('docs').cloneNode(true);
    var destino = document.getElementById('destino');
    destino.appendChild (clone);
    
    var camposClonados = clone.getElementsByTagName('input');
    var botao = document.createAttribute("button").id = "Remove";

    

    for(i=0; i<camposClonados.length;i++){
        camposClonados[i].value = '';
    }

    var rem = document.getElementById("Remove");

}


rem.onclick = function(id) {
	var node1 = document.getElementById('destino');
	node1.removeChild(node1.childNodes[0]);
}