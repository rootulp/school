function calculate()
{
		var a = Number(document.forms["carcalc"].inputa.value); //principal
		var b = Number(document.forms["carcalc"].inputb.value); //years
		var c = Number(document.forms["carcalc"].inputc.value); //rate
		var i = c*.01;
		var m = b*12*-1;
		var r = i/12;
		var d = (a*r) / (1-Math.pow(1+r,m));
		var result = Math.round(d*100)/100;
		var resultloc = document.getElementById("result");

		resultloc.innerHTML = "To borrow $" + a + " at " + c + "%" + " APR, paid over " + b + " year(s)... your payment is $" + result + "!";

		return false;  /* I have to return false or the result disappears */

}