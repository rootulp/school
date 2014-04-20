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
function checkEmail(){
          var subjectvalid = checksubject();
          var messagevalid = checkmessage();
          var mailpasswordvalid = checkmailpassword();
          var membershipvalid = checkmembership();
          if (subjectvalid && messagevalid && mailpasswordvalid && membershipvalid )
            return true;
          else {
            emailerrorstyle();
            return false;
           }
}
function checksubject(){ 
    var subject = document.getElementById('Subject').value;  
    if (subject.length != 0)
        return true;
    else
        return false;
}
function checkmessage(){ 
    var message = document.getElementById('Message').value;  
    if (message.length != 0)
        return true;
    else
        return false;
}

function checkmailpassword(){ 
    var mailpassword = document.getElementById('MailPassword').value;  
    if (mailpassword.length != 0)
        return true;
    else
        return false;
}

function checkmembership(){ 
	 if (document.getElementById('MembershipType').checked) 
            return true;
         else 
            return false;
        
    }

// 	
// 	var checkboxes = document.getElementById('MembershipType').value;
// 	var x = "";
// 	for (var i=0, n=checkboxes.length;i<n;i++) {
//  		 if (checkboxes[i].checked) {
//  		 x += ","+checkboxes[i].value;}}
//   if (x = 0)
//   	{ return false;}
//   else 
//   	{return true;}
//   
// }

    // var membershiptype = document.getElementById('MembershipType').value;  
//     if (membershiptype.length != 0)
//         return true;
//     else
//         return false;}

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

function emailerrorstyle(){
    if (checksubject() == false) {
        var resultloc=document.getElementById("subjecterror");
        resultloc.innerHTML = "No Subject Entered!";
    }
    if (checkmessage() == false) {
        var resultloc=document.getElementById("messageerror");
        resultloc.innerHTML = "No message Entered!";
    }
    if (checkmailpassword() == false) {
        var resultloc=document.getElementById("mailpassworderror");
        resultloc.innerHTML = "No password Entered!";
    }
    if (checkmembership() == false) {
        var resultloc=document.getElementById("membershiperror");
        resultloc.innerHTML = "No membership type Entered!";
    }
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
