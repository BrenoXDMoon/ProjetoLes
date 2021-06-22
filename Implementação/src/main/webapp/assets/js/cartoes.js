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

function doisCartoesSelcionados(){
	var cartao = document.getElementById("j_idt8:cartao").getElementsByTagName("input");
	var min = 2;
	var soma =0;
	for (var i = 0; i < cartao.length; i++) {
		if(cartao[i].checked){
			soma += 1;
		}
	}
	if(soma != min){
		alert("Selecione 2 cartões de crédito");
	}
}

function somaCupom() {
  var total = document.getElementById("totalFrete").innerHTML;
  var cupomTroca = document.getElementById("j_idt8:cupomTroca").getElementsByTagName("input");
  var cupomDesconto = document.getElementById("j_idt8:cupomDesconto").getElementsByTagName("input");
  var soma_troca = 0;
  var soma_desc = 0;
  // loop over them all
  for (var i=0; i<cupomTroca.length; i++) {
     // And stick the checked ones onto an array...
     if (cupomTroca[i].checked) {
 			soma_troca =+ document.getElementById(cupomTroca[i].id).nextSibling.innerHTML.split(":")[1];
        
     }
     if((soma_desc + soma_troca) > (total * 1.2)){
	  	alert("Os Valores dos cupons ultrapassam  o valor máximo de "+(total * 1.2).toFixed(2)+" remova algum cupom");
	  	document.getElementById(cupomTroca[i].id).checked = false;
	  	return
	  }
     
  }
  
  for (var i=0; i<cupomDesconto.length; i++) {
     // And stick the checked ones onto an array...
     if (cupomDesconto[i].checked) {
 			soma_desc =+ document.getElementById(cupomDesconto[i].id).nextSibling.innerHTML.split(":")[1];
        
     }
     if((soma_desc + soma_troca) > (total * 1.2)){
	  	alert("Os Valores dos cupons ultrapassam  o valor máximo de "+(total * 1.2).toFixed(2)+" remova algum cupom");
	  	document.getElementById(cupomDesconto[i].id).checked = false;
	  }
  }
  
	document.getElementById("j_idt8:somaCupom").value = soma_desc + soma_troca;
   
  
  if((soma_desc + soma_troca) > (total * 0.8)){
  	
  }else{
  
  }
  return soma_desc + soma_troca
}


