function calculaValorOutroCartao() {
	var cartao1 = Number(document.getElementById("j_idt8:valorCartaoUm").value)
	var total = Number(document.getElementById("totalFrete").innerHTML)
	total = Math.abs(total - somaCupom()).toFixed(2);

	

	if (cartao1 > total && total <= 10) {
		console.log("false")
		document.getElementById("j_idt8:valorCartaoUm").value = total;
		document.getElementById("j_idt8:valorCartaoDois").value = 0;
	}
	else {
		console.log("true")
		document.getElementById("j_idt8:valorCartaoDois").value = Math.abs((total - cartao1)).toFixed(2);
	}
	if (cartao1 > total) {
		document.getElementById("j_idt8:valorCartaoUm").value = total;
		document.getElementById("j_idt8:valorCartaoDois").value = 0;
	}

	if (total > 10 && cartao1 < 10) {
		document.getElementById("j_idt8:valorCartaoUm").value = 0;
		document.getElementById("j_idt8:valorCartaoDois").value = 0;
		alert("O valor inválido");
	}

	return total
}
