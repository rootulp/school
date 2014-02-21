<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>HW10</title>
	<style>
 	fieldset {
		   width: 500px; 
		   background-color: gold;
		   color: maroon;
	  }
	  legend {
		   width:200px; 
		   background-color: gold;
		   color: maroon;
	  }

	  label {
		   text-align: left; 
		   float: left; 
		   color: maroon;
	  }
	</style>
</head>

<body>
<h1>State Capital Quiz</h1>
<!-- Use If Statement to handle the form  -->
<section>
	<fieldset >
		<legend>State Capital Quiz</legend>
		<form method="get">
			<label>The capital of 
			<?php
			$statecapital = Array(
			"Alabama" => "Montgomery", "Alaska" => "Juneau", "Arizona" => "Phoenix", "Arkansas" => "Little Rock", "California" => "Sacramento",
			"Colorado" => "Denver", "Connecticut" => "Hartford", "Delaware" => "Dover", "Florida" => "Tallahassee", "Georgia" => "Atlanta",
			"Hawaii" => "Honolulu", "Idaho" => "Boise", "Illinois" => "Springfield", "Indiana" => "Indianapolis", "Iowa" => "Des Moines",
			"Kansas" => "Topeka", "Kentucky" => "Frankfort", "Louisiana" => "Baton Rouge", "Maine" => "Augusta", "Maryland" => "Annapolis",
			"Massachusetts" => "Boston", "Michigan" => "Lansing", "Minnesota" => "St. Paul", "Mississippi" => "Jackson",
			"Missouri" => "Jefferson City", "Montana" => "Helena", "Nebraska" => "Lincoln", "Nevada" => "Carson City", "New Hampshire" => "Concord",
			"New Jersey" => "Trenton", "New Mexico" => "Santa Fe", "New York" => "Albany", "North Carolina" => "Raleigh",
			"North Dakota" => "Bismarck", "Ohio" => "Columbus", "Oklahoma" => "Oklahoma City", "Oregon" => "Salem", "Pennsylvania" => "Harrisburg",
			"Rhode Island" => "Providence", "South Carolina" => "Columbia", "South Dakota" => "Pierre", "Tennessee" => "Nashville",
			"Texas" => "Austin", "Utah" => "Salt Lake City", "Vermont" => "Montpelier", "Virginia" => "Richmond", "Washington" => "Olympia",
			"West Virginia" => "Charleston", "Wisconsin" => "Madison", "Wyoming" => "Cheyenne");
			?>
			$key = array_rand($statecapital);
			$value = $statecapital[$key];
			echo $key;
			
			is: </label>
			<select name="element">
			<?php
        	foreach($statecapital as $key => $value){
   			 	echo '<option value='.$value.'>'.$value.'</option>'."\n";
   			 }
   			 ?>
			</select>
			<br/>
			<input class="mybutton" type="submit" value="Submit!">
			<?php
			if (isset($_GET['formsubmitted']))  
				handleform($capitals); 			 
			$country = isset($_GET['country']) ? $_GET['country']: "";
			displayform($capitals, $country);  
			?>
		</form>
	</fieldset>
</section>	

</body>
</html>

<?php

?>