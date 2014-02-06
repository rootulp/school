function calculate(){
		var feet = Number(document.forms["bmicalc"].feet.value);
		var inches = Number(document.forms["bmicalc"].inches.value);
		var pounds = Number(document.forms["bmicalc"].pounds.value);
		var height = (feet * 12) + inches;
		var bmi = (pounds / Math.pow(height, 2)) * 703
		var resultloc = document.getElementById("result");
		var message;
		var image;
		
		if (bmi < 18.5) {
			message = "Your BMI is " + bmi.toFixed(2) + ". You are underweight.<br>";
			image = "<img src=\"img/underweight.png\" alt=\"Obese\" />";
		} else if (bmi < 25) {
			message = "Your BMI is " + bmi.toFixed(2) + ". You are normal weight.<br>";
			image = "<img src=\"img/normalweight.png\" alt=\"Obese\" />";
		} else if (bmi < 30) {
			message = "Your BMI is " + bmi.toFixed(2) + ". You are overweight.<br>";
			image = "<img src=\"img/overweight.png\" alt=\"Obese\" />";
		} else if (bmi < 40) {
			message = "Your BMI is " + bmi.toFixed(2) + ". You are obese.<br>";
			image = "<img src=\"img/obese.png\" alt=\"Obese\" />";
		} else {
			message = "Your BMI is " + bmi.toFixed(2) + ". You are morbidly obese.<br>";
			image = "<img src=\"img/morbidlyobese.png\" alt=\"Obese\" />";
		}
		resultloc.innerHTML= message + "<br>" + image;
		
		return false;
	}