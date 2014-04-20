<?php
include_once ('dbconn.php');

function handleform($name, $email, $password, $type)
{	
    $con = connectToDB();
    checkemail($con,$email);
    $password = sha1($password);
    insertinto($con, $name, $email, $password, $type);
}

function passwordreset($Email)
{   
    $con = connectToDB();
    $checkquery="SELECT COUNT(*) FROM Members WHERE `email`='$Email'";
    $number = mysqli_query($con,$checkquery) or die("bad query".mysqli_error($con));
    $numberof=mysqli_fetch_array($number,MYSQLI_ASSOC);
    $numemail=$numberof['COUNT(*)'];
    
    if($numemail == 0) {
        echo "No such email exists";
?>
        <a href="http://cscilab.bc.edu/~patelau/HW16/index.php"> Click here to return to club page</a> 
<?php
    }

    if($numemail > 0){
        $chars='ABCDEFGHIJKLMOPQRZSTUVWXYZabcdefghijlmopqrstuvwxyz1234567890';
        $charleng=strlen($chars);
        $string2=NULL;
        for($x=1;$x<=40;$x++){ 
            $string1=rand(0,$charleng);
            $string2 .=substr($chars,$string1,1);
        }
        $newpassword=$string2;
        $subject = "Password Reset Email";
        $message = "Your new password is: " . $newpassword;
        mail($Email,$subject,$message,"From Admin");
        $newpassword = sha1($newpassword);
        $con = connectToDB();
        $update=mysqli_query($con,"UPDATE `Members` SET `Password`='$newpassword' WHERE `Email`='$Email'")or die("bad query".mysqli_error($con));
?> 
        <p>Email Reset successful! Please check your email.</p>
        <a href="http://cscilab.bc.edu/~patelau/HW16/index.php"> Click here to return to club page</a> 
<?php
        die();
    } 
}

function emailhandleform($subject, $message, $password, $type)
{   
    $con = connectToDB();
    foreach ($type as &$value) {
        $getemailquery=mysqli_query($con,"SELECT `Email` FROM `Members` WHERE Type='$value'");
        $storeArray = Array();
        while ($row = mysqli_fetch_array($getemailquery)) {
            $storeArray[] =  $row['Email'];  
        }
        //print_r($storeArray);
        foreach ($storeArray as $usersemails){
            mail($usersemails,$subject,$message,"From Admin");
        }
    }
    echo "<h1>Success!</h1>";
    echo "</p><br>";
    echo "<br>";
    echo "Your Message has been sent!";
    echo "<br>";
    echo "<a href='admin.php'>Return to Admin Page</a>";
}

function createDataTable(){
    $qry = "SELECT `Name`, `Email`, `Type`, `registrationDate` FROM `Members` WHERE 1";
        
    echo "<table class=\"fixed\">
                <tr>
                    <th class=\"Name\">Name</th>
                    <th class=\"Email\">Email</th>
                    <th class=\"Type\">Type</th>
                    <th class=\"Registration Date\">Registration Date</th>
                </tr>\n";
                
    $con = connectToDB();
    $result = performQuery($con, $qry);
    $class = "alt2";
    while (@extract(mysqli_fetch_array($result, MYSQLI_ASSOC))) {
        $class = ($class=='alt1' ? 'alt2':'alt1');
        echo "  <tr class=\"$class\">
                    <td>$Name</td>
                    <td>$Email</td>
                    <td>$Type</td>
                    <td>$registrationDate</td>
                </tr>\n";
    }
    echo "</table>\n";
}

function checkemail($con,$email){
	$checkquery="SELECT COUNT(*) FROM Members WHERE `email`='$email'";
	$number = mysqli_query($con,$checkquery) or die("bad query".mysqli_error($con));
	$numberof=mysqli_fetch_array($number,MYSQLI_ASSOC);
	$numemail=$numberof['COUNT(*)'];
	
	if($numemail > 0){
		?> <a href="http://cscilab.bc.edu/~patelau/HW16/index.php">Email already exists, please click here to try again if not redirected</a> 
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