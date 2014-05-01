<!DOCTYPE HTML>
    <head>
    <title>HW17</title>
        <link rel="stylesheet" type="text/css" href="css/hw17.css" />
        <script type="text/javascript" src="js/hw17.js"></script>
    </head>
    <body>
        <h1>CS254 Best of BC Page</h1>
        <br>
        <br>
        <fieldset>
            <legend>Options</legend>
            <form method='get'>
                <input type='text' name='searchtxt' id='searchtxt' />
                <input type='submit' value='Search' name='op' id='Search'/> 
                <br>
                <input type='text' name='distancetxt' id='distancetxt' />
                <input type='submit' value='Show Distance' name='op' id='Distance'/> 
                <br>
                <input type='submit' value='Add Attraction' name='op' id='Add Attraction' />
                <br>
            </form>
                <form method=get action='hw17.php'> 
                <input type='submit' value='Clear Omit' name='op' id='Clear Omit' />
                </form>
        </fieldset>

        <?php
         if (isset($_COOKIE['counting'])){
            $hidden_attractions = unserialize($_COOKIE['counting']);
            $list = "'". implode("', '", $hidden_attractions) ."'";
        }
        ?>
    </body>
</html>
<?php
    function displayInsertAttractionForm() {
        ?>
        <fieldset>
        <form action="hw17.php" onsubmit="return checkOrder();"  />
        <br>
        Name: <article id='nameerror'></article><input type='text' name='Name' id='Name'> <br>
        Address: <article id='addresserror'></article><input type='text' name='Address' id='Address'> <br>
        Category: <article id='categoryerror'></article><input type='text' name='Category' id='Category'> <br>
        Phone: <article id='phoneerror'></article><input type='text' name='Phone' id='Phone'> <br>
        URL: <article id='URLerror'></article><input type='text' name='URL' id='URL'> <br>
        Your UserID: <article id='USERIDerror'></article><input type='text' name='userid' id='userid'> <br>
        Comment: <article id='commenterror'></article><input type='textarea' name='Comment' id='Comment'> <br>
        Star Rating: <article id='ratingerror'></article><select name='rating' id='rating'>
            <option value='FAIL'>Select One!</option>
            <option value='onestar'>One Star</option>
            <option value='twostars'>Two Stars</option>
            <option value='threestars'>Three Stars</option>
            <option value='fourstars'>Four Stars</option>
            <option value='fivestars'>Five Stars</option> 
             </select>
            <br>
            <br>
        Price Level: <article id='priceerror'></article><select name='Price' id='Price'>    
            <option value='FAILURE'>Select One!</option>
            <option value='onesigns'>$</option>
            <option value='twosigns'>$$</option>
            <option value='threesigns'>$$$</option>
            <option value='foursigns'>$$$$</option>
            <option value='fivesigns'>$$$$$</option> 
           </select>
        <br>
        <br>
        <input type='submit' value='Add' name='Add' id='Add' />
        </form>
        </fieldset>
        <?php
        }
    function displayHomePage($string, $latitude, $longitude, $loc) {
        $hidden_attractions = array("");
        $list = "0";
         if (isset($_COOKIE['counting'])){
            $hidden_attractions = unserialize($_COOKIE['counting']);
            $list = "'". implode("', '", $hidden_attractions) ."'";
            //echo $list;
        }
        //$hidden_attractions = join(',' , $hidden_attractions);
        $dbc= @mysqli_connect("localhost", "patelau", "EFPSiQ9W", "CS254") or
                    die("Connect failed: ". mysqli_connect_error());
        if ($string == "" ) {
            $query="select * from `bestofbc` where `attraction_id` NOT IN ($list) ";
        } else {
            $string="%".$string."%";
            $query=   "(select * from `bestofbc` where `attraction_id` NOT IN ($list) AND `address` like '$string')  union
                            (select * from `bestofbc` where `entered_by` like '$string' AND `attraction_id` NOT IN ($list)) union
                            (select * from `bestofbc` where `category` like '$string' AND `attraction_id` NOT IN ($list)) union
                            (select * from `bestofbc` where `name` like '$string' AND `attraction_id` NOT IN ($list)) union
                            (select * from `bestofbc` where `phone` like '$string' AND `attraction_id` NOT IN ($list)) union
                            (select * from `bestofbc` where `comment` like '$string' AND `attraction_id` NOT IN ($list)) ";
            //$query=   "select * from `bestofbc` where `attraction_id` NOT IN ($list) AND `name` like '$string' ";
        } 
        $result = mysqli_query($dbc, $query) or die("bad query".mysqli_error($dbc));          
        $rows=mysqli_num_rows($result);
        echo "<p>Your search found: ".$rows." attractions</p>";            
        $data=array();
        $i=0;
        while ($x = mysqli_fetch_assoc($result)) {
            $data[$i]=$x;
            $i++;
        }
        echo "<table class='gridtable'>";
        echo "<th>Attraction</th><th>Comment</th><th>Distance</th>";              
        foreach($data as $display){  
            $dm = distanceBetween(floatval($display['latitude']), floatval($display['longitude']), floatval($longitude), floatval($latitude)); 
            if ($dm > 5000) {
                $dm = "";
            } else {
                $dm = $dm . " Miles";
            }
            echo"<tr><td>";
            echo "{$display['name']}<br>{$display['category']}<br>{$display['address']}<br>{$display['phone']}<br>
                {$display['url']}<br>{$display['price_range']}<br>{$display['stars']}<br>
                <form method=get action='hw17.php'>
                <input type='submit' value='Hide Attraction' name='op' id='Hide Attraction' />
                <input type='hidden' value='{$display['attraction_id']}' name='attraction_id' id='{$display['attraction_id']}' />
                </form>
                </td>
                <td>{$display['comment']}</td>
                <td>{$dm}";
            echo "</td></tr>";
        }
        echo "</table>";
    }
    function addAttraction($Name,$Category,$rating,$Address,$Phone,$URL,$userid,$Comment,$Price){
        getLoc($Address);
        $dbc= @mysqli_connect("localhost", "patelau", "EFPSiQ9W", "CS254") or
            die("Connect failed: ". mysqli_connect_error());      
        $insertsql="INSERT INTO bestofbc (insertion_date, entered_by, name, category,address,latitude,longitude,phone,url,stars,price_range,comment)
        VALUES
        (CURDATE(),'$userid','$Name', '$Category','$Address','$latitude','$longitude','$Phone','$URL','$rating','$Price','$Comment')";
        $result = mysqli_query($dbc, $insertsql) or die("bad query".mysqli_error($dbc)); 
    }
    function getLoc($loc, $string) {
        if ($loc!=""){
            $lowercaseaddress=strtolower($loc);
            $string2 = preg_replace("/[\s_]/", "+", $lowercaseaddress);
            $page = 'http://geocoder.us/demo.cgi?address='.$string2;
            $content=file_get_contents($page);
            $pattern="/(<td>)(\-?\d+.\d+)/";
            $pattern2="/<td>.\d+\.\d+?/";
            $latitude=0;
            $longitude=0;
            var_dump($longitude);
            if (preg_match_all ($pattern, $content, $matches) ) {
                $latitude= $matches[2][0];
                $longitude= $matches[2][1];
                if ($latitude==0){
                    $latitude = "Didn't work";
                }
                if ($longitude==0){
                    $longitude = "Didn't work";
                }
            } else {
                die("That Address Doesn't exist");
            } 
        } else {
            $loc = "Empty";
            $longitude = "Not Here";
            $latitude ="Not There";
        }
        //$string = "";
        //$string = $string;
        displayHomePage($string, $longitude, $latitude, $loc);
    }
    function distanceBetween($lat1, $lon1, $lat2, $lon2) {
        $Rm = 3961;
        $lat1 = deg2rad($lat1);
        $lon1 = deg2rad($lon1);
        $lat2 = deg2rad($lat2);
        $lon2 = deg2rad($lon2);
        $dlat = $lat2 - $lat1;
        $dlon = $lon2 - $lon1;
        $a = pow(sin($dlat/2),2) + cos($lat1) * cos($lat2) * pow(sin($dlon/2),2);
        $c = 2 * atan2(sqrt($a),sqrt(1-$a));
        $dm = $c * $Rm;
        $dm = round($dm, 2);
        return $dm;
    }
    function updateCookie($attraction_id){
        if (isset($_COOKIE['counting'])) {
            $hidden_attractions = unserialize($_COOKIE['counting']);
            if (in_array($attraction_id, $hidden_attractions)) {
                echo "In array already!";
            } else {
                array_push($hidden_attractions, $attraction_id);
                setcookie('counting', serialize($hidden_attractions));
            }
        } else {
            $hidden_attractions = array($attraction_id);
            setcookie("counting", serialize($hidden_attractions));
        }
        $host  = $_SERVER['HTTP_HOST'];
        $uri  = rtrim(dirname($_SERVER['PHP_SELF']), '/\\');
        header("Location: hw17.php");   
     }

    function deleteCookie(){
        setcookie("counting", 0, time() - 3600);
        $host  = $_SERVER['HTTP_HOST'];
        $uri  = rtrim(dirname($_SERVER['PHP_SELF']), '/\\');
        header("Location: hw17.php");
    }

    if (isset($_GET['Add'])) {
        $Name=$_GET['Name'];
        $Category=$_GET['Category'];
        $rating=$_GET['rating'];
        $Address=$_GET['Address'];
        $Phone=$_GET['Phone'];
        $URL=$_GET['URL'];
        $userid=$_GET['userid'];
        $Price=$_GET['Price'];
        $Comment=$_GET['Comment'];
        addAttraction($Name,$Category,$rating,$Address,$Phone,$URL,$userid,$Comment,$Price);
    }
    $op = isset($_GET['op']) ? $_GET['op'] : "";
    switch ($op) {
        case 'Hide Attraction':
            $attraction_id = isset($_GET['attraction_id']) ? $_GET['attraction_id'] : "";
            //echo $attraction_id;
            //$attraction_id = 21;
            updateCookie($attraction_id);
            break;
        case 'Clear Omit';
            deleteCookie();
            break;
        case 'Add Attraction':
            displayInsertAttractionForm();
            break;
        default:
        case 'Search':
        case 'Distance':
            $string = isset($_GET['searchtxt']) ? $_GET['searchtxt'] : "";
            $loc = isset($_GET['distancetxt']) ? $_GET['distancetxt'] : "";
            getLoc($loc, $string);
            break; 
    }
?>