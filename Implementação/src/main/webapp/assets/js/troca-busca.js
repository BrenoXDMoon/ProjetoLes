	let el = document.getElementById("j_idt4:filtro");
	let nome = document.getElementsByClassName('troca-nome');
	let raridade = document.getElementsByClassName('troca-raridade');


function teste(){

	
	console.log(el.options[el.selectedIndex].text)
	if(el.options[el.selectedIndex].text === "raridade"){
		raridade[0].classList.remove('oof');
		nome[0].classList.add("oof");
	}
	if(el.options[el.selectedIndex].text === "nome"){
		raridade[0].classList.add('oof');
		nome[0].classList.remove("oof");
	}
	
}
	
