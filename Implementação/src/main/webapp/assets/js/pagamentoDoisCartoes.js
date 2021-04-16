function calculaValorOutroCartao(){
	var cartao1 = document.getElementById("j_idt8:valorCartaoUm").value
	var total = document.getElementById("totalFrete").innerHTML
	total = Math.abs(total - somaCupom()).toFixed(2);
	if(cartao1 > total & total <= 10){
		document.getElementById("j_idt8:valorCartaoUm").value = total;
		document.getElementById("j_idt8:valorCartaoDois").value =  0;
	}
	else{
	document.getElementById("j_idt8:valorCartaoDois").value =  (total - cartao1).toFixed(2);
	}
	if(total > 10 & cartao1<10){
			
			document.getElementById("j_idt8:valorCartaoUm").value = 0;
			document.getElementById("j_idt8:valorCartaoDois").value =  0;
			alert("O valor inválido");
	}
	
	return total
}
