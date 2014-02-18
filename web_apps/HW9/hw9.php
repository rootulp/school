<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>HW9</title>
	<style>
		.color1 {background-color: red;}
		.color2 {background-color: green;}
	</style>
</head>

<body>
<h1>Beginning and End of Lent</h1>
<table>
<tr>
	<th>Year</th>
	<th>Ash Wednesday</th>
	<th>Easter</th>
</tr>

<?php
	$color="color1";
	for ($year=2014; $year<2024; $year++){
		$color = swapcolor($color);
		$easterDate = easter_date($year); 
		$easterStr = date("M-d-Y", $easterDate);
		$ashWednesdayDate = easter_date($year) - 3970000;
		$ashWednesdayDateStr = date("M-d-Y", $ashWednesdayDate);
		maketablerow($ashWednesdayDateStr, $easterStr, $year, $color);		
	}
?>

</table>
</body>
</html>

<?php
	function swapcolor($color){
		if ($color == "color1")
			return "color2";
		return "color1";
	}
	function maketablerow($y, $z, $x, $color){
			echo "<tr class=\"$color\"><td>" . $x .
					"</td><td>".$y."</td><td>".$z."</td></tr>\n";
	}
?>