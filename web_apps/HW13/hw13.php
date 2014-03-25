<?php
include("dbconn.php");
?>
<!DOCTYPE html>
<html lang="en">
<head>
     <meta charset="UTF-8" />
     <title>The Continentor</title>
<style type="text/css">
        body{ font-family: Verdana, sans-serif; }
        fieldset {  
                color: Red;
                font-family: Verdana;
                font-size: 24px;
                background-color: Blue;
                width:500px;
        }
        legend {
                background-color: green; 
                width: 800px;
                font-size: 18px;
                color: black;
                font-family: Verdana;
                border: 3px groove blue;
                border-color:gold;
        }
        input{
                background-color:Red;
                font-weight:bold;
                font-family:Verdana;
          }
</style>
</head>
<body>
<h1>World Fact Book 2007</h1>
<h3>Information By Continent </h3>
<pre><?php //print_r($_GET);
?></pre>
<?php

if (isset($_GET['favorites'])) {
    $currentfavs = $_GET['favorites'];
    handleform($currentfavs);
} else
    $currentfavs = "";

displayform($currentfavs);
?>
</body>
</html>
<?php

function handleform($currentfavs) //Handle form checks to see what info fields are being asked and then prints the data queried from CreateInfoQuery.
{
    echo "<fieldset>";
    $SelectedContinent = $_GET['continentMenu'];
    $dbc               = connectToDB();
    $query             = createinfoquery($SelectedContinent, $currentfavs);
    $result            = performQuery($dbc, $query);
    $row               = mysqli_fetch_array($result, MYSQLI_ASSOC);
    
    echo "Info regarding $SelectedContinent";
    echo "<br>";
    if (in_array("Airports", $currentfavs)) {
        $Airports = $row['Airports'];
        echo "This many airports: " . $Airports;
        echo "<br>";
    }
    if (in_array("Imports", $currentfavs)) {
        $Imports = $row['Imports'];
        echo "This many imports: " . $Imports;
        echo "<br>";
    }
    if (in_array("Exports", $currentfavs)) {
        $Exports = $row['Exports'];
        echo "This many exports: " . $Exports;
        echo "<br>";
    }
    if (in_array("GDP", $currentfavs)) {
        $GDP = $row['GDP'];
        echo "GDP is: " . $GDP;
        echo "<br>";
    }
    if (in_array("InternetUsers", $currentfavs)) {
        $InternetUsers = $row['InternetUsers'];
        echo "Internet Users: " . $InternetUsers;
        echo "<br>";
    }
    echo "</fieldset>";
}

function createinfoquery($continent, $info) //takes paramaters of the continent and info selected and builds a MYSQL query for the correct info
{
    $queryBeg = "SELECT";
    $queryMid = "";
    $queryEnd = " FROM `countries` WHERE Continent='$continent' GROUP BY `Continent`";
    foreach ($info as $value) {
        $queryMid = $queryMid . " `" . $value . "`,";
    }
    $queryMid = rtrim($queryMid, ",");
    $query    = $queryBeg . $queryMid . $queryEnd;
    return ($query);
}

function displayform($currentfavs) //display form creates the original page by calling make_continent_menu and make_info_checkboxes
{
    echo "<form method = \"get\">";
    echo "<fieldset>";
    make_continent_menu();
    echo "<br>";
    make_info_checkboxes($currentfavs);
    echo "<br>";
    echo "<input type=\"submit\" name=\"submitted\" value=\"Get the Continent Info!\"></form>\n";
    echo "</fieldset>";
}

function make_info_checkboxes($currentfavs) //Creates check boxes for the fields of info possible
{
    $infoCategories = array(
        "Airports",
        "Imports",
        "Exports",
        "GDP",
        "InternetUsers"
    );
    echo "<article id='languagereport'></article>";
    foreach ($infoCategories as $value) {
        if (!empty($currentfavs) and (FALSE !== array_search($value, $currentfavs)))
            echo "<input type=\"checkbox\" name=\"favorites[]\" value=\"$value\" checked=\"checked\"> $value<br>\n";
        else
            echo "<input type=\"checkbox\" name=\"favorites[]\" value=\"$value\"> $value<br>\n";
    }
}

function make_continent_menu() //Creates a dropdown of all possible continents that are selectable
{
	$menuname = "continentMenu";
    echo "<select name=\"$menuname\">\n";
    $dbc    = connectToDB();
    $query  = "SELECT Continent FROM countries GROUP BY Continent";
    $result = performQuery($dbc, $query);
    while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
        $continentName = $row['Continent'];
        if (isset($_GET[$menuname]) && ($_GET[$menuname] == $continentName))
            echo "<option value = \"$continentName\" selected>$continentName</option>\n";
        else
            echo "<option value = \"$continentName\">$continentName</option>\n";
    }
    echo "</select>";
    disconnectFromDB($dbc, $result);
}
?>