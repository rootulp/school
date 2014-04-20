<!DOCTYPE HTML>
<head>
<title>HW16</title>
	<link rel="stylesheet" type="text/css" href="css/hw16.css" />
	<script type="text/javascript" src="js/hw16.js"></script>
</head>
<body>
<h1>The Boston College Computer Science Society</h1>
<br>
<img src="img/logo.JPG" alt="Logo" />
<br>
<?php
include_once ('dboperation.php');
?>
	
<?php
if (isset($_POST['bla'])){
// 	echo '<script language="javascript">';
// 	echo 'alert("message successfully sent")';
// 	echo '</script>';
	$name=$_POST['Name'];
	$email=$_POST['Email'];
	$password= $_POST['Password'];
	$type=$_POST['Type'];
//echo $name . $email . $password . $type;	
echo "<h1>Success!</h1>";
echo "<p>Thank you, " . $name . " you are now registered as a: ";
switch ($type) {
    case "President";
    	echo "President.";
    	break;
    case "Treasurer":
    	echo "Treasurer.";
    	break;
    case "Secretary":
    	echo "Secretary";
    	break;
    case "Regular":
    	echo "Regular";
    	break;
    default:
    	echo "Member";
    break;
}
echo "</p><br>";
echo "<a href='index.php'>Return to Club Page</a>";
echo "<br>";
echo "<br>";
	handleform($name, $email, $password, $type);

} else if (isset($_POST['forgotpassword'])){
            $Email=$_POST['ForgotEmail'];
            passwordreset($Email);
} else if (isset($_POST['join'])){
	displayjoinform();
} else if (isset($_POST['forgot'])){
            displayforgotform();
} else {
	displayjoinbutton();
           displayforgotbutton();
}
?>
</body>
</html>
<?php
function displayjoinbutton(){
?>
	<form name="join" method="post">
	<input type="submit" name="join" value="Apply to the Club!" />
	</form>
<?php
}
function displayforgotbutton(){
?>
    <form name="forgot" method="post">
    <input type="submit" name="forgot" value="Forgot Password!"/>
    </form>

<?php
}
function displayforgotform(){
?>
    <form name="forgotpassword" method="post" action="index.php">
        <fieldset>
            <legend>Forgot Password Form</legend>
                Email: <input type="text" name="ForgotEmail" id="ForgotEmail" />
                <input type="submit" value="Send Password Reset Email!" name="forgotpassword" id="forgotpassword"/>
        </fieldset>
    </form>
<?php
}
function displayjoinform()
{
?>  
    	<form name="info" method="post" action="index.php" onsubmit="return checkOrder();">
			<fieldset>
			<legend id="purchase">Application Form</legend>
			<fieldset>
			<legend>Name</legend>
			<section id="nameerror"></section>
			Your Name: <input type="text" name="Name" id="Name" />
			</fieldset>
			
			<fieldset>
			<legend>Email</legend>
			<section id="emailerror"></section>
			Email Address: <input type="text" name="Email" id="Email" />
			</fieldset>
						
			<fieldset>
			<legend>Password</legend>
			<section id="passwordmatcherror"></section>
			<section id="passworderror"></section>
			Password: <input type="password" name="Password" id="Password" />
			<br>
			<section id="passworderror2"></section>
			Confirm Password: <input type="password" name="Password2" id="Password2" />
			</fieldset>

			<fieldset>
			<legend>Position</legend>
			<section id="positionerror"></section>
			<input type="radio" name="Type" id="President" value="President" /> President
			<br>
			<input type="radio" name="Type" id="Treasurer" value="Treasurer" /> Treasurer
			<br>
			<input type="radio" name="Type" id="Secretary" value="Secretary" /> Secretary
			<br>
			<input type="radio" name="Type" id="Regular" value="Regular" /> Regular
			</fieldset>
			<input type="submit" value="Join the BCCSS!" name="bla" id="bla"/>
			</fieldset>
		</form>
<?php
}
?>