<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>HW14</title>
    <style>
        body { 
        font-size: 100%; 
        font-family: sans-serif;
        }
        label {
        font-size: 110%;
        width:300px;
        display:block;
        float:left;
        padding-right: 10px;
        text-align: right;
        }    
        submit{
        float: right;
        }    
        img{ 
        max-height: 500px; 
        max-width: 500px;
        }
        fieldset {  
        color: Black;
        font-family: Garamond;
        font-size: 20px;
        background-color: Red;
        width:800px;
        }
        legend {
        background-color: Red; 
        width: 800px;
        font-size: 18px;
        color: black;
        font-family: Verdana;
        border: 3px red;
        border-color:red;
        }
        input{
        background-color:white;
        font-weight:bold;
        font-family:Garamond;
        font-size: 125%; 
        width: 500px;
      	}
    </style>
</head>
<body>
<?php
if (isset($_GET['submitted'])) {
    $address = $_GET['address'];
    handleform($address);
} else {
    $address = "";
    displayform();
}

$chipotle = array(
    "Brighton" => array(
        "address" => "1924 Beacon Street Brighton, MA",
        "location" => array(
            "latitude" => 42.336185,
            "longitude" => -71.149355
        )
    ),
    "Watertown" => array(
        "address" => "485 Arsenal St. Watertown, MA",
        "location" => array(
            "latitude" => 42.362902,
            "longitude" => -71.158054
        )
    ),
    "Brookline" => array(
        "address" => "876 Commonwealth Ave Brookline, MA",
        "location" => array(
            "latitude" => 42.351097,
            "longitude" => -71.116096
        )
    ),
    "Boston" => array(
        "address" => "148 Brookline Avenue Boston, MA",
        "location" => array(
            "latitude" => 42.344762,
            "longitude" => -71.101236
        )
    ),
    "Cambridge" => array(
        "address" => "598 Massachusetts Avenue Cambridge, MA",
        "location" => array(
            "latitude" => 42.364927,
            "longitude" => -71.103187
        )
    )
);

function displayForm()
{
    echo "<form>";
    echo "<fieldset>";
    echo "<legend>Find the closest Chipotle!</legend>";
    echo "<br>";
    echo "<img src=\"img/Chipotle.jpg\" alt=\"Chipotle logo\"/>";
    echo "<br>";
    echo "<br>";
    echo "<br>";
    echo "What is your Address?";
    echo "<br>";
    echo "<input type=\"text\" name=\"address\">";
    echo "<input type=\"submit\" name=\"submitted\" value=\"Submit!\">";
    echo "</fieldset>";
    echo "</form>";
}

function handleForm($address)
{
    $pattern   = "!\-?\d{2}\.\d{6}!";
    $userinput = urlencode($address);
    $page      = 'http://geocoder.us/demo.cgi?address=' . $userinput;
    getlocation($page, $pattern);
    $location = getlocation($page, $pattern);
    echo "<fieldset><legend>Results!</legend><br>";
    print_r($location);
    echo "<br>";
    echo "This is your address: " . $address;
    echo "<br>";
    echo "Here are the nearest Chipotles:";
    echo "<br>";
    displayDistances($location);
    echo "</fieldset>";
}

function getLocation($page, $pattern)
{
    $res     = array();
    $content = file_get_contents($page);
    preg_match_all($pattern, $content, $res);
    
    $location = array(
        "latitude" => htmlentities($res[0][0]),
        "longitude" => htmlentities($res[0][1])
    );
    return $location;
}

function displayDistances($location)
{
    $chipotle = array(
        "Brighton" => array(
            "address" => "1924 Beacon Street Brighton, MA",
            "location" => array(
                "latitude" => 42.336185,
                "longitude" => -71.149355
            )
        ),
        "Watertown" => array(
            "address" => "485 Arsenal St. Watertown, MA",
            "location" => array(
                "latitude" => 42.362902,
                "longitude" => -71.158054
            )
        ),
        "Brookline" => array(
            "address" => "876 Commonwealth Ave Brookline, MA",
            "location" => array(
                "latitude" => 42.351097,
                "longitude" => -71.116096
            )
        ),
        "Boston" => array(
            "address" => "148 Brookline Avenue Boston, MA",
            "location" => array(
                "latitude" => 42.344762,
                "longitude" => -71.101236
            )
        ),
        "Cambridge" => array(
            "address" => "598 Massachusetts Avenue Cambridge, MA",
            "location" => array(
                "latitude" => 42.364927,
                "longitude" => -71.103187
            )
        )
    );
    
    $testArray = array("Brighton", "Watertown", "Brookline", "Boston", "Cambridge");
	foreach ($testArray as $value) {
    	$d = dist($location, $chipotle[$value]['location']);
        echo number_format ($d, 2);
        echo " miles away to " . $value;
        echo "<br>";
	} 
}

function haversin($z)
{
    return 0.5 * (1 - cos($z));
}

function dist($location1, $location2)
{
    $R  = 3963; // earth radius, miles
    $dp = deg2rad($location2['latitude'] - $location1['latitude']);
    $dt = deg2rad($location2['longitude'] - $location1['longitude']);
    $h  = haversin($dp) + cos(deg2rad($location1['latitude'])) * cos(deg2rad($location2['latitude'])) * haversin($dt);
    $d  = 2 * $R * asin(sqrt($h));
    return $d;
}
?>
</body>
</html>
