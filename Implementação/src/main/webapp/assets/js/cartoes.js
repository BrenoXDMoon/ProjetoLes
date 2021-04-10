function trocarCartao(){
	document.getElementById("a").classList.toggle("d-none");
	document.getElementById("b").classList.toggle("d-none");
}

function listaCartao(){
	console.log("entrou");
	document.getElementById("listaCartao").classList.toggle("d-none");
}


document.getElementById("botaoCartao").addEventListener("click", listaCartao);

function doisCartoes() {
	var cartao = document.getElementById("j_idt8:cartao").getElementsByTagName("input");
	var limit = 2;
	for (var i = 0; i < cartao.length; i++) {
		cartao[i].onclick = function() {
			var checkedcount = 0;
				for (var i = 0; i < cartao.length; i++) {
				checkedcount += (cartao[i].checked) ? 1 : 0;
			}
			if (checkedcount > limit) {
				this.checked = false;
			}
		}
	}
}

function teste(){
	var total = document.getElementById("totalFrete").innerHTML;
	var cartao_1 = document.getElementById("j_idt8:valorCartaoUm").value;
	var cartao_2 = document.getElementById("j_idt8:valorCartaoDois").value;
	
	if(total - (cartao_1 + cartao_2) < 10)
		console.log("dru");
}

function getCheckedBoxes() {
  var total = document.getElementById("totalFrete").innerHTML;
  var checkboxes = document.getElementById("j_idt8:cupomTroca").getElementsByTagName("input");
  var soma = 0;
  // loop over them all
  for (var i=0; i<checkboxes.length; i++) {
     // And stick the checked ones onto an array...
     if (checkboxes[i].checked) {
     		console.log(checkboxes[i].id);
 			soma =+ document.getElementById(checkboxes[i].id).nextSibling.innerHTML.split(":")[1];
         if(soma > (total*1.2)){
		  	alert("Os Valores dos cupons ultrapassam muito o valor da compra ");
		  	document.getElementById(checkboxes[i].id).checked = false;
		  }
     }
  }
  
  
  return soma;
}


