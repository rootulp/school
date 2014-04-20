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
if (isset($_POST['Send'])){
 // echo '<script language="javascript">';
 // echo 'alert("About to handleform")';
 // echo '</script>';
    $subject=$_POST['Subject'];
    $message=$_POST['Message'];
    $password= $_POST['MailPassword'];
    $type=$_POST['MembershipType'];
   // echo $subject . $message . $password . $type;  
    $testPassword = sha1($password);
    $truePassword = "1785ed6ccf537856a2e5d0935a1ffb2dde2d3ab5";
    $hobby = $_POST['MembershipType'];
     // foreach ($type as $types=>$value) {
     //    echo "MembershipType : ".$value."<br />";
     //     }
    if ($testPassword == $truePassword){
        emailhandleform($subject, $message, $password, $type);
    } else {
        echo "<h1>Failure!</h1>";
        echo "<br>";
        echo "Incorrect Password";
        echo "</p><br>";
        echo "<a href='admin.php'>Return to Admin Page</a>";
    }
} else {
    createDataTable();
    displayemailform();
}
?>
</body>
</html>

<?php
function displayemailform()
{
?>  
        <form name="email" method="post" action="admin.php" onsubmit="return checkEmail();">
            <fieldset>
                <legend id="Create Group Email">Create Group Email</legend>
                <br>
                <section id="subjecterror"></section>
               Subject: <input type="text" name="Subject" id="Subject" />
               <br>
                <section id="messageerror"></section>
                Your Message: <input type="textarea" name="Message" id="Message" />
                <br>      
                <section id="mailpassworderror"></section>
                Mail Password: <input type="password" name="MailPassword" id="MailPassword" />
                <br>
                <label>Membership Class</label>
                <section id="membershiperror"></section>
                <input type="checkbox" name="MembershipType[]" id="MembershipType" value="President" /> President
                <br>
                <input type="checkbox" name="MembershipType[]" id="MembershipType" value="Treasurer" /> Treasurer
                <br>
                <input type="checkbox" name="MembershipType[]" id="MembershipType" value="Secretary" /> Secretary
                <br>
                <input type="checkbox" name="MembershipType[]" id="MembershipType" value="Regular" /> Regular
                <br>
                <input type="submit" value="Send your message!" name="Send" id="Send"/>
            </fieldset>
        </form>
<?php
}
?>