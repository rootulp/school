function checkOrder(){
          var namevalid = checkname();
          var ratingvalid = checkrating();
          var addressvalid = checkaddress();
          var phonevalid = checkphone();
          var URLvalid = checkURL();
          var USERIDvalid = checkUSERID();
          var categoryvalid = checkcategory();
          var commentvalid = checkcomment();
          if (namevalid && ratingvalid && addressvalid  && phonevalid && URLvalid && USERIDvalid && categoryvalid && commentvalid)
            return true;
          else {
            errorstyle();
            return false;
           }
}

function checkaddress(){ 
    var address = document.getElementById('Address').value;  
    if (address.length != 0)
        return true;
    else
        return false;
}
function checkphone(){
        var phoned = document.getElementById("Phone");
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
function checkURL(){
        var URLD = document.getElementById("URL");
        var URLent = URLD.value;
        var URLerror = document.getElementById("URLerror");
        var tomatchURL = /(\S+\.[^/\s]+(\/\S+|\/|))/;
        if (!tomatchURL.test(URLent)) {
            URLerror.innerHTML = "Invalid URL!";
            return false;
        }
        URLerror.innerHTML = "";
        return true;    
}
function checkname(){ 
    var name = document.getElementById('Name').value;  
    if (name.length != 0)
        return true;
    else
        return false;
}
function checkcategory(){ 
    var category = document.getElementById('Category').value;  
    if (category.length != 0)
        return true;
    else
        return false;
}
function checkUSERID(){ 
    var USERID = document.getElementById('userid').value; 
    if (USERID.length != 0)
        return true;
    else
        return false;
}
function checkcomment(){ 
    var comment = document.getElementById('Comment').value;  
    if (comment.length != 0)
        return true;
    else
        return false;
}
// function checkposition(){
//  if(document.getElementById('President').checked)
//      return true;
//  else if (document.getElementById('Treasurer').checked)
//      return true;
//  else if (document.getElementById('Secretary').checked)
//      return true;
//  else if (document.getElementById('Regular').checked)
//      return true;
//  else
//      return false;
// }
function checkrating(){
    if(document.InsertAttractionForm.rating.value == "FAIL")
        return false;
    else
        return true;
}
function checkprice(){
    if(document.InsertAttractionForm.Price.value == "FAILURE")
        return false;
    else
        return true;
}
function errorstyle(){
    document.body.style.backgroundColor = "red";
    if (checkname() == false) {
        var resultloc=document.getElementById("nameerror");
        resultloc.innerHTML = "No Name Entered!";
    }
    if (checkrating() == false) {
        var resultloc=document.getElementById("ratingerror");
        resultloc.innerHTML = "Select a Rating!";
    }
    if (checkprice() == false) {
        var resultloc=document.getElementById("priceerror");
        resultloc.innerHTML = "Select a Price Level!";
    }
    if (checkphone() == false) {
        var resultloc=document.getElementById("phoneerror");
        resultloc.innerHTML = "Invalid Phone Number!";
    }
    if (checkcomment() == false) {
        var resultloc=document.getElementById("commenterror");
        resultloc.innerHTML = "No Comments Entered!";
    }
    if (checkUSERID() == false) {
        var resultloc=document.getElementById("USERIDerror");
        resultloc.innerHTML = "No USERID Entered!";
    }
    if (checkURL() == false) {
        var resultloc=document.getElementById("URLerror");
        resultloc.innerHTML = "Invalid URL!";
    }
    if (checkaddress() == false) {
        var resultloc=document.getElementById("addresserror");
        resultloc.innerHTML = "No Address Entered!";
    }
    if (checkcategory() == false) {
        var resultloc=document.getElementById("categoryerror");
        resultloc.innerHTML = "No Category Entered!";
    }
}