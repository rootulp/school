<?php
include_once ('dbconn.php');

function handleform($name, $email, $password, $type)
{	
	$con = connectToDB();
	checkemail($con,$email);
	insertinto($con, $name, $email, $password, $type);
}

function checkemail($con,$email){
	$checkquery="SELECT COUNT(*) FROM Members WHERE `email`='$email'";
	$number = mysqli_query($con,$checkquery) or die("bad query".mysqli_error($con));
	$numberof=mysqli_fetch_array($number,MYSQLI_ASSOC);
	$numemail=$numberof['COUNT(*)'];
	
	if($numemail > 0){
		?> <a href="http://cslab.bc.edu/~patelau/HW15/index.php">Email already exists, please click here to try again if not redirected</a> 
		<?php
		die();
	} 
}

function insertinto($con, $name, $email, $password, $type){
	//$sql="CREATE TABLE Members(Name varchar(30), Email CHAR(30), Password char(40), registrationDate datetime, Type enum('CEO', 'CFO', 'VP', 'AVP'))";
	$insertsql="INSERT INTO Members (Name, Email, Password, registrationDate, Type)
	VALUES
	('$name','$email','$password',now(), '$type')";
	//performquery($con,$sql);
	performquery($con,$insertsql);
}
?>