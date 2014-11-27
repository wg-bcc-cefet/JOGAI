function limparCampoData(data) {
//	alert(data.value);
	if(data.value.length != 0 && data.value.length != 10) {
		data.value = '';
	}
}

function validaNumeroInteiro(w, ref) {
	var which ;
	//alert(w);
	if (w == undefined) {
		which = event.keyCode;
	} else {
		which = w;
	}

	if ((which != 0 && which != 8) && (which < 48 || which > 57)) {
		return false;
	}

	else {
		return true;
	}

}

var countVirgula = 0;
var AceitarTecla = true;
var existeVirgula;
var which;

function validaNumeroVirgula(w, ref) {
	if (w == undefined) {
		which = event.keyCode;
	} else {
		which = w;
	}

	var str = String(ref.value);
	var n = str.search(',');
	if (n >= 0 && w == 44) {
		return false;
	}

	digitoInicio = /\d+/.test(ref.value);

	if ((which != 0 && which != 8 && which != 44) && (which < 48 || which > 57)) {
		AceitarTecla = false;
	} else {
		AceitarTecla = true;
		if (which == 44 && !digitoInicio) {
			AceitarTecla = false;
		} else if (which == 44) {
			existeVirgula = /^\d+\,\d+$/.test(ref.value);
			virgulaPosdigito = /^\d+\,/.test(ref.value);
			if (existeVirgula || virgulaPosdigito) {
				AceitarTecla = false;
			}
		}
	}
	return AceitarTecla;

}

function formatarHora(src, mask, e) {
	var tecla = (window.event) ? e.keyCode : e.which;

	if (tecla == 8 || tecla == 0)
		return true;
	var i = src.value.length;
	var saida = mask.substring(0, 1);
	var texto = mask.substring(i);

	if (texto.substring(0, 1) != saida) {
		src.value += texto.substring(0, 1);
	}

}

function formatarData(data, mask, e) {
	var tecla = (window.event) ? e.keyCode : e.which;

	if (tecla == 8 || tecla == 0)
		return true;
	var i = data.value.length;
	var saida = mask.substring(0, 1);
	var texto = mask.substring(i);

	if (texto.substring(0, 1) != saida) {
		data.value += texto.substring(0, 1);
	}

}

// if ((which != 0 && which != 8 && which != 44) && (which < 48 || which > 57)) {
function validaTelefone(src, e){
	var m = /^\(\d{2}$/.test(src.value);	
	var m2 = /^\(\d{2}\)$/.test(src.value);
	var m3 = /^\(\d{2}\)\d{4}$/.test(src.value);
	var m4 =  /^\(\)$/.test(src.value);
	var which = (window.event) ? e.keyCode : e.which;
 	
	//if(which){alert(which);}
    
	if ((which != 0 && which != 8 && which != 40 &&  which != 41 && which == 44 && which != 45) && (which < 48 || which > 57)) {
	     
		return false;
	}
		
	else{	
		 
		
		 if(which && src.value == "" && which != 40){			 
			
			  src.value = "(";
			  
			}else if(src.value == "(" && which == 40){
				return false ;
				
	     }
		 
		 if(m && which != 41 && which != 8){
			 src.value += ")";
			 
			 
		 }
		 else  if( m2  && which == 41  || m4 && which == 41 && which != 8)
			 {
			  
			    return false ;
			 }
		 
		 if(which && m3 && which != 45 && which != 8){
			src.value += "-";
			
			 
		 }
		 
		
		 
		 if(/^\(\d{2}\)\d{4}-$/.test(src.value) && which == 45){
			 
			 return false ;
		 }
  
		 
		 }
	
}


function validaCep(src, e){
	
	var which = (window.event) ? e.keyCode : e.which;
	//alert(which);
 
	if ((which != 0 && which != 8 && which != 45 ) && (which < 48 || which > 57)){
		
		
		   return false ;
	}
	
	if(/\d{5}$/.test(src.value) &&  which != 45  &&  which !=8){
		             
		src.value += "-";

	}
	
	if(/\d{5}-$/.test(src.value) &&  which == 45){
		
		return false ;
	}

}



function validaNumeroBarra(w, ref){
	
	var which ;
	
	if (w == undefined) {
		
		which = event.keyCode;
		
	} else {
		which = w;
		
	}
	// /\d\s\d\/\d/.test(ref.value)
	//alert(/\d\/\d/.test(ref.value));
	if(/\d\,/.test(ref.value) && which == 44)
	{
	   return false;
	}
	
	if(/\d\/\d/.test(ref.value) && which == 44)
	{
	   return false ;
	}
	
	if(/\d/.test(ref.value) && which == 44)
	{
	   return true ;
	}
	
	
	if(/\d\//.test(ref.value) &&  which == 47)
	{
		return false ;
	}
	if(/\d\s/.test(ref.value) && which == 32)
	{
		return false ;
	}	
	if(/\d\s\d\//.test(ref.value) && which == 32)
	{
		return false ;
	}
	
	if(/\d/.test(ref.value) && which == 32)
	{
		return true ;
		
	}
	

	if(/\d/.test(ref.value) && which == 47)
	{
		return true ;
	}
	   
	if ((which != 0 && which != 8) && (which < 48 || which > 57)) 
	{
		return false;
	}

	else
	{		
		return true ;
	}	
	
	
}


function validaCgc(src, mask, e){
	
	
	 //33.018.748/0001-70 v 46 b 47
	// /\d{2}.\d{3}
	var tecla = (window.event) ? e.keyCode : e.which;
	
   // alert(/\d{2}.$/.test(src.value));
//	if ((tecla != 0 && tecla != 8  && tecla) && (tecla < 48 || tecla > 57)) {
//		return false;
//	}
//
//	else {
//		return true;
//	}
//	
//	if(/\d{2}$/.test(src.value)  || /\d{2}.\d{3}$/.test(src.value) && which == 46){
//		
//		return true ;
//		
//	}
//	else{
//		return false ;
//	}
//		
//   
	
	


	if (tecla == 8 || tecla == 0)
		return true;
	var i = src.value.length;
	var saida = mask.substring(0, 1);
	var texto = mask.substring(i);

	if (texto.substring(0, 1) != saida) {
		src.value += texto.substring(0, 1);
	}
	
	
}

function abrirPopUp()
{
	window.open('texto-ajuda-plc.xhtml','page','toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=1100,height=500, top=100, left=100');
}