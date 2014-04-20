function simplevalidate(){
     if (checkOrder() == true)
          alert ("success");
     else
          errorstyle();
          return false;
}
	  
function checkOrder(){
          var namevalid = checkname();
          var emailvalid = checkemail();
          var passwordvalid = checkpassword();
          var password2valid = checkpassword2();
          var passwordmatch = checkpasswordmatch();
          var positionvalid = checkposition();
          if (namevalid && emailvalid && passwordvalid && positionvalid && password2valid && passwordmatch)
          	return true;
          else {
          	errorstyle();
            return false;
           }
}
function checkname(){ 
	var name = document.getElementById('Name').value;  
	if (name.length != 0)
		return true;
	else
		return false;
}
function checkpassword(){ 
	var password = document.getElementById('Password').value;  
	if (password.length != 0)
		return true;
	else
		return false;
}
function checkpassword2(){ 
	var password2 = document.getElementById('Password2').value;  
	if (password2.length != 0)
		return true;
	else
		return false;
}
function checkpasswordmatch(){ 
	var password = document.getElementById('Password').value; 
	var password2 = document.getElementById('Password2').value;  
	if (password == password2)
		return true;
	else
		return false;
}

function checkemail(){ 
	var email = document.getElementById('Email').value;  
	if (email.length != 0)
		return true;
	else
		return false;
}
function checkposition(){
	if(document.getElementById('President').checked)
		return true;
	else if (document.getElementById('Treasurer').checked)
		return true;
	else if (document.getElementById('Secretary').checked)
		return true;
	else if (document.getElementById('Regular').checked)
		return true;
	else
		return false;
}

function errorstyle(){
	document.body.style.backgroundColor = "yellow";
	if (checkname() == false) {
		var resultloc=document.getElementById("nameerror");
		resultloc.innerHTML = "No Name Entered!";
	}
	if (checkpassword() == false) {
		var resultloc=document.getElementById("passworderror");
		resultloc.innerHTML = "No password Entered!";
	}
	if (checkpassword2() == false) {
		var resultloc=document.getElementById("passworderror2");
		resultloc.innerHTML = "No password Entered!";
	}
	if (checkpasswordmatch() == false) {
		var resultloc=document.getElementById("passwordmatcherror");
		resultloc.innerHTML = "Passwords Don't Match!";
	}
	if (checkemail() == false) {
		var resultloc=document.getElementById("emailerror");
		resultloc.innerHTML = "No Email Entered!";
	}
	if (checkposition() == false) {
		var resultloc=document.getElementById("positionerror");
		resultloc.innerHTML = "No position Entered!";
	}
}
