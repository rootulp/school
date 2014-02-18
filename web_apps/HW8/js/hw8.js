function simplevalidate(){
     if (checkOrder() == true)
          newpage();
     else
          errorstyle();
}
	  
function checkOrder(){
          var namevalid    = validatename();
          var phonevalid   = validatephone();
          var emailvalid   = validateemail();
          var statevalid = validatestate();
          var zipvalid = validatezip();
          var paymentvalid = checkpayment();
          return namevalid && statevalid && emailvalid && phonevalid && paymentvalid && zipvalid;
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

function errorstyle(){
	document.body.style.backgroundColor = "yellow";
	if (checkpayment() == false) {
		var resultloc=document.getElementById("paymenterror");
		resultloc.innerHTML = "No Payment Method Entered!";
	}
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
	w.document.write('<a href="http://cscilab.bc.edu/~patelau/hw8/hw8.html">Place Another Order</a>');
	w.document.close();
}

function setmeup(){
		var named = document.getElementById("name");
		var phoned = document.getElementById("phone");
		var emailed = document.getElementById("email");
		var stated = document.getElementById("state");
		var zipd = document.getElementById("zip");
		named.onfocus = namecue;
		named.onblur = validatename;
		phoned.onfocus = phonecue;
		phoned.onblur = validatephone;
		emailed.onfocus = emailcue;
		emailed.onblur = validateemail;
		stated.onfocus = statecue;
		stated.onblur = validatestate;
		zipd.onfocus = zipcue;
		zipd.onblur = validatezip;
}
	
function namecue(){
		var nameerror = document.getElementById("nameerror");
		nameerror.innerHTML = "Enter a name (2 characters or longer)";
}

function validatename(){
		var named = document.getElementById("name");
		var nameent = named.value;
		var nameerror = document.getElementById("nameerror");
		var tomatchname = /^\w{2,}$/;
		if (!tomatchname.test(nameent)) {
			nameerror.innerHTML = "Invalid Name!";
			return false;
		}
		nameerror.innerHTML = "";
		return true;	
}

function phonecue(){
		var phoneerror = document.getElementById("phoneerror");
		phoneerror.innerHTML = "Enter a phone number (XXX-XXX-XXXX or (xxx)XXX-XXXX)";
}

function validatephone(){
		var phoned = document.getElementById("phone");
		var phoneent = phoned.value;
		var phoneerror = document.getElementById("phoneerror");
		var tomatchphone = /^\d{3}-\d{3}-\d{4}$/;
		if (!tomatchphone.test(phoneent)) {
			phoneerror.innerHTML = "Invalid Phone Number!";
			return false;
		}
		phoneerror.innerHTML = "";
		return true;	
}

function emailcue(){
		var emailerror = document.getElementById("emailerror");
		emailerror.innerHTML = "Enter an Email Address: (aaaa@bb.ccc)";
}

function validateemail(){
		var emailed = document.getElementById("email");
		var emailent = emailed.value;
		var emailerror = document.getElementById("emailerror");
		var tomatchemail = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{1,3}$/;
		if (!tomatchemail.test(emailent)) {
			emailerror.innerHTML = "Invalid Email Address!";
			return false;
		}
		emailerror.innerHTML = "";
		return true;
}

function statecue(){
		var stateerror = document.getElementById("stateerror");
		stateerror.innerHTML = "Enter a State (XX)";
}

function validatestate(){
		var stated = document.getElementById("state");
		var stateent = stated.value;
		var stateerror = document.getElementById("stateerror");
		var tomatchstate = /^[A-Z][A-Z]$/;
		if (!tomatchstate.test(stateent)) {
			stateerror.innerHTML = "Invalid State!";
			return false;
		}
		stateerror.innerHTML = "";
		return true;
}

function zipcue(){
		var ziperror = document.getElementById("ziperror");
		ziperror.innerHTML = "Enter a Zip Code (XXXXX or XXXXX-xxxx)";
}

function validatezip(){
		var zipd = document.getElementById("zip");
		var zipent = zipd.value;
		var ziperror = document.getElementById("ziperror");
		var tomatchzip = /(^\d{5}$)|(^\d{5}-\d{4}$)/;
		if (!tomatchzip.test(zipent)) {
			ziperror.innerHTML = "Invalid Zip Code!";
			return false;
		}
		ziperror.innerHTML = "";
		return true;
}