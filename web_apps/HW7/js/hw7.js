function simplevalidate(){
     if (checkOrder() == true)
          newpage();
     else
          errorstyle();
}
	  
function checkOrder(){
          var namevalid    = checkname();
          var addressvalid = checkaddress();
          var emailvalid   = checkemail();
          var phonevalid   = checkphone();
          var paymentvalid = checkpayment();
          return namevalid && addressvalid && emailvalid && phonevalid && paymentvalid;
}

function checkname(){ 
	var name = document.getElementById('name').value;  
	if (name.length != 0)
		return true;
	else
		return false;
}

function checkphone(){ 
	var phone = document.getElementById('phone').value;  
	if (phone.length != 0)
		return true;
	else
		return false;
}

function checkemail(){ 
	var email = document.getElementById('email').value;  
	if (email.length != 0)
		return true;
	else
		return false;
}

function checkaddress(){ 
	var street = document.getElementById('street').value; 
	var city = document.getElementById('city').value; 
	var state = document.getElementById('state').value; 
	var zip = document.getElementById('zip').value;  
	if (street.length === 0)
		return false;
	else if (city.length === 0)
		return false;
	else if (state.length === 0)
		return false;
	else if (zip.length === 0)
		return false;
	else
		return true;
}

function checkpayment(){
	if(document.getElementById('check').checked)
		return true;
	else if (document.getElementById('cash').checked)
		return true;
	else if (document.getElementById('credit').checked)
		return true;
	else
		return false;
}

function newpage() {
	var name = document.getElementById('name').value;  
	var phone = document.getElementById('phone').value;
	var email = document.getElementById('email').value; 
	var street = document.getElementById('street').value; 
	var city = document.getElementById('city').value; 
	var state = document.getElementById('state').value; 
	var zip = document.getElementById('zip').value; 
	var w=window.open();
	w.document.open();
	w.document.write("<h1>Success!</h1>");
	w.document.write("<p>Here is your order " + name +  ":</p>");
	w.document.write("<p>Address:</p>");
	w.document.write(street);
	w.document.write("<br>");
	w.document.write(city);
	w.document.write("<br>");
	w.document.write(state);
	w.document.write("<br>");
	w.document.write(zip);
	w.document.write("<br>");
	w.document.write("<p>Email: " + email + "</p>");
	w.document.write("<p>Phone: " + phone + "</p>");
	w.document.write('<a href="http://cscilab.bc.edu/~patelau/hw7/hw7.html">Place Another Order</a>');
	w.document.close();
}

function errorstyle(){
	document.body.style.backgroundColor = "yellow";
	if (checkname() == false) {
		var resultloc=document.getElementById("nameerror")
		resultloc.innerHTML = "No Name Entered!";
	}
	if (checkphone() == false) {
		var resultloc=document.getElementById("phoneerror")
		resultloc.innerHTML = "No Phone Number Entered!";
	}
	if (checkemail() == false) {
		var resultloc=document.getElementById("emailerror")
		resultloc.innerHTML = "No Email Entered!";
	}
	if (checkaddress() == false) {
		var resultloc=document.getElementById("addresserror")
		resultloc.innerHTML = "No Address Entered!";
	}
	if (checkpayment() == false) {
		var resultloc=document.getElementById("paymenterror")
		resultloc.innerHTML = "No Payment Method Entered!";
	}
}

