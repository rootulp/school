<!DOCTYPE html>
<html>
<head>
  <title>HW10</title>
  <link rel="stylesheet" type="text/css" href="css/baseA.css" />    
 </head>
<body>
<?php
$capitals = Array(
    "Alabama" => "Montgomery",
    "Alaska" => "Juneau",
    "Arizona" => "Phoenix",
    "Arkansas" => "Little Rock",
    "California" => "Sacramento",
    "Colorado" => "Denver",
    "Connecticut" => "Hartford",
    "Delaware" => "Dover",
    "Florida" => "Tallahassee",
    "Georgia" => "Atlanta",
    "Hawaii" => "Honolulu",
    "Idaho" => "Boise",
    "Illinois" => "Springfield",
    "Indiana" => "Indianapolis",
    "Iowa" => "Des Moines",
    "Kansas" => "Topeka",
    "Kentucky" => "Frankfort",
    "Louisiana" => "Baton Rouge",
    "Maine" => "Augusta",
    "Maryland" => "Annapolis",
    "Massachusetts" => "Boston",
    "Michigan" => "Lansing",
    "Minnesota" => "St. Paul",
    "Mississippi" => "Jackson",
    "Missouri" => "Jefferson City",
    "Montana" => "Helena",
    "Nebraska" => "Lincoln",
    "Nevada" => "Carson City",
    "New Hampshire" => "Concord",
    "New Jersey" => "Trenton",
    "New Mexico" => "Santa Fe",
    "New York" => "Albany",
    "North Carolina" => "Raleigh",
    "North Dakota" => "Bismarck",
    "Ohio" => "Columbus",
    "Oklahoma" => "Oklahoma City",
    "Oregon" => "Salem",
    "Pennsylvania" => "Harrisburg",
    "Rhode Island" => "Providence",
    "South Carolina" => "Columbia",
    "South Dakota" => "Pierre",
    "Tennessee" => "Nashville",
    "Texas" => "Austin",
    "Utah" => "Salt Lake City",
    "Vermont" => "Montpelier",
    "Virginia" => "Richmond",
    "Washington" => "Olympia",
    "West Virginia" => "Charleston",
    "Wisconsin" => "Madison",
    "Wyoming" => "Cheyenne"
);
asort($capitals);
?>

<?php
if (isset($_GET['formsubmitted']))
    handleform($capitals, $key, $value);

$country = isset($_GET['country']) ? $_GET['country'] : "";
displayform($capitals, $country);
?>

</body>
</html>

<?php
function displayform($capitals, $selectedcountry = "")
{
?>
<fieldset class="input">
<legend  class="input">State Capital Quiz</legend>
<label>The capital of 
<?php
    $key   = array_rand($capitals);
    $value = $capitals[$key];
    echo $key;
?>
 is: 
</label>
<form method="get">
<?php
    echo "<legend>$legend</legend>
    <input type=\"hidden\" name=\"key\" value=\"$key\" />
    <input type=\"hidden\" name=\"value\" value=\"$value\" />";
?>
  <select name="country">
     <?php // create the pull-down list
    foreach ($capitals as $key => $value)
        if ($selectedcountry == $key)
            echo "<option selected=\"selected\">$value</option>\n";
        else
            echo "<option>$value</option>\n";
?>
 </select>
  <input value="Submit!" name="formsubmitted" type="submit">
</form>
</fieldset>
<?php
}
?>



<?php
function handleform($capitals)
{
    
    if (isset($_GET["country"])) { // check the pull-down
        $country = $_GET['country'];
        $key     = $_GET['key'];
        $value   = $_GET['value'];
?>

<fieldset class="result">
<legend  class="result">Your answer is...</legend>

<?php
	if ($country == $value)
        echo "Correct! The capital of $key is $value </fieldset>";
    else
    	echo "Incorrect! The capital of $key is $value </fieldset>";
?>
<?php
    }
}
?>
