<?php
$cities = array(
    "Boston" => "http://w1.weather.gov/xml/current_obs/KBOS.xml",
    "New York" => "http://w1.weather.gov/xml/current_obs/KJFK.xml",
    "San Francisco" => "http://w1.weather.gov/xml/current_obs/KSFO.xml",
    "Houston" => "http://w1.weather.gov/xml/current_obs/KIAH.xml"
)?>

<?php
$sources = array(
    "The Guardian" => "http://feeds.theguardian.com/theguardian/technology/rss",
    "ESPN" => "http://sports.espn.go.com/espn/rss/news",
    "CNN" => "http://rss.cnn.com/rss/cnn_topstories.rss"
)?>

<!DOCTYPE HTML>
<head>
<Title>HW11</Title>
<style type="text/css">
    body { 
        font-family: Verdana, sans-serif; 
    }
    fieldset {  
        color: black;
        font-family: Garamond;
        font-size: 20px;
        background-color: red;
        width:900px;
    }
    legend {
        background-color: green; 
        width: 300px;
        font-size: 18px;
        color: black;
        font-family: Verdana;
        border: 3px groove yellow;
        border-color:yellow;
    }
    input {
        height:24px;
        float: right;
        font-size: 16px;
        background-color:green;
        font-weight:bold;
        font-family:Garamond;
        position:relative;
        bottom:-20px;
    }
    div {
        overflow: scroll;
        height: 300px;
        width: 900px;
        border: 3px groove blue;
    } 
    </style>
</head>

<form method="get">
<?php
displayform($cities, $sources);
if (isset($_GET['formsubmitted'])) {
    //     if (isset($GET['city']))
    //     	$xmlcity   = $_GET['city']; 
    //     else $xmlcity = "Boston";
    //     
    //     if (isset($_GET['source']))
    //     	$rsssource = $_GET['source'];
    //     else $rsssource = "Reddit";
    $xmlcity   = $_GET['city'];
    $rsssource = $_GET['source'];
    
    handleform($cities, $xmlcity, $sources, $rsssource);
}
?>
<input type="submit" value="Submit!" name="formsubmitted">
</form>

<?php
function displayform($cities, $sources)
{
    
    echo "<fieldset>";
    echo "<legend>Weather: Select Your City</legend>";
    echo "<select name=\"city\">";
    foreach ($cities as $key => $value) {
        if ($_GET['city'] == $key)
            echo "<option value=\"$key\" selected> $key </option>\n";
        else
            echo "<option value=\"$key\"> $key </option>\n";
    }
    echo "</select>";
    echo "</fieldset>";
    
    echo "<fieldset>";
    echo "<legend>News: Select Your Source</legend>";
    echo "<select name=\"source\">";
    foreach ($sources as $key => $value) {
        if ($_GET['source'] == $key)
            echo "<option value=\"$key\" selected> $key </option>\n";
        else
            echo "<option value=\"$key\"> $key </option>\n";
    }
    echo "</select>";
    echo "</fieldset>";
    
}

function handleform($cities, $xmlcity, $sources, $rsssource)
{
    echo "<fieldset>";
    echo "<legend>" . " The weather for $xmlcity is:" . "</legend>";
    $cityurl = $cities[$xmlcity];
    
    $xmlinfo   = file_get_contents($cityurl);
    $xmlobject = new SimpleXMLElement($xmlinfo);
    foreach ($xmlobject->temperature_string as $w) {
        
        echo "Temperature is: ";
        echo "$w";
        
        echo "<br>";
    }
    foreach ($xmlobject->windchill_string as $c) {
        echo "Winchill is: ";
        echo $c;
        echo "<br>";
    }
    
    foreach ($xmlobject->weather as $b) {
        echo "Weather is: ";
        echo $b;
        echo "<br>";
    }
    
    foreach ($xmlobject->wind_string as $wind) {
        echo "Wind is: ";
        echo $wind;
        echo "<br>";
    }
    foreach ($xmlobject->observation_time as $time) {
        
        echo $time;
        echo "<br>";
    }
    
    
    
    echo "</fieldset>";
    
    
    // Here is the news stuff
    echo "<br>";
    
    echo "<div>";
    echo "<fieldset>";
    echo "<legend>" . " The News from $rsssource is:" . "</legend>";
    
    $sourceurl = $sources[$rsssource];
    
    $rssinfo   = file_get_contents($sourceurl);
    $rssobject = new SimpleXMLElement($rssinfo);
    
    //Copied from her demo
    $title = $rssobject->channel->title;
    echo "<h1>$title</h1>";
    
    $items = $rssobject->channel->item; # try, works some versions
    if (!$items)
        $items = $rssobject->item; # works other versions
    
    foreach ($items as $item) {
        echo "<h2>$item->title<h2>\n";
        echo '<a href="' . $item->link . '">' . $item->title . '</a><br>';
        echo $item->description . "<br><br>\n";
        echo "<hr>";
    }
    echo "</fieldset>";
    echo "</div>";
}
?>