function calculaValorOutroCartao(){
	var cartao1 = document.getElementById("j_idt8:valorCartaoUm").value

	document.getElementById("j_idt8:valorCartaoDois").value = document.getElementById("j_idt8:valorTotal").innerHTML - cartao1;
}
