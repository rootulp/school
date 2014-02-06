<!DOCTYPE html>
<html lang="en">
	<head>
    	<meta charset="utf-8" />
    	<title>HW2</title>
	</head>
	<style>
		h1 {color:red; background-color:black;}
		section {background-color: black;}
		label {color:red;}
		img {float:right;}
		input {font-family:helvetica; background-color: red;}
	</style>
	<body>
		<h1>Who deserves the Oscars?</h1>
<!-- Oscar logo: http://mockingjay.net/wp-content/uploads/2013/12/oscars-logo.jpg -->
		
		<section>
			<fieldset>
				<form method="post" action="http://www.bc.edu/tools/formmail.cgi">
					<img src="oscars-logo.jpg" alt="Oscars Logo" height="400" width="800"/>
					<label>Name?</label><input type="text" name="name" size="30"/>
					<br>
					<label>Email Address?</label><input type="text" name="email" size="30"/>
					<br>
					<label>Sex?</label>
					<input type="radio" name="male" value="male"/><label>Male</label>
					<input type="radio" name="female" value="female"/><label>Female</label>
					<br>
					<label>Are you an actor?  Check if yes: </label><input type="checkbox" name="actor" value="yes"/>
					<br>
					<textarea name="movies" rows="10" cols="30">If yes, list any movies you're in:</textarea>
					<br>
					<label>Choose your favorite movie!</label>
					<br>
					<select name="movie" size="9">
					<option value="Dallas Buyer's Club">Dallas Buyer's Club</option>
					<option value="Philomena">Philomena</option>
					<option value="Her">Her</option>
					<option value="American Hustle">American Hustle</option>
					<option value="Nebraska">Nebraska</option>
					<option value="12 Years a Slave">12 Years a Slave</option>
					<option value="Gravity">Gravity</option>
					<option value="True Grit">True Grit</option>
					<option value="The Wolf of Wall Street">The Wolf of Wall Street</option>
					</select>
					<br>
					<input type="hidden" name="recipient" value="patelau@bc.edu"/>
					<input type="hidden" name="subject" value="Oscar Vote"/>
					<br>
					<input type="submit" name="my submit" value="Vote!"/>
					<input type="reset" name="my reset" value="Reset"/>
					<br>
				</form>
			</fieldset>
		</section>
	</body>
</html>