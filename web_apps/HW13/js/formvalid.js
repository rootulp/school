// function validateBoxes(){
// 	var languages= <? echo json_encode($currentfavs); ?>;
// 	writetoelement("languagereport",
// 						"Please select at least one language ",
// 						"green");
// 	if (typeof image_array !== 'undefined' && image_array.length > 0) {
// 		writetoelement("languagereport", "","green");	
// 		return true;
// 	}else {
// 		writetoelement("languagereport",
// 						"Please select at least one language ",
// 						"green");
// 		return false;
// 	}
// }

// 	function validatelanguages(){
// 	writetoelement("languagereport",
// 						"Please select at least one language ",
// 						"green");
// 		return false;
// 		var languages=["languages[0]", "languages[1]", "languages[2]"];
// 	
// 		for(var i=0;i<languages.length;i++){
// 			if(document.getElementById(languages[i]).checked){ 
// 				writetoelement("languagereport",
// 						"",
// 						"green");	
// 				return true;
// 			}
// 		}
// 		writetoelement("languagereport",
// 						"Please select at least one language ",
// 						"green");
// 		return false;
// 	}
	
function hello(){
	alert("Hello!");
	var resultloc=document.getElementById("languagereport")
	var favorites= <? echo json_encode($currentfavs); ?>;
	var fav1 = document.getElementById('favorites[0]').value;  
	resultloc.innerHTML = "Hello";
	return false;
	}