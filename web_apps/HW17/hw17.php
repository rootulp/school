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
                <input type='submit' value='Search' name='op' id='Search'/> <br>
                <input type='submit' value='Add Attraction' name='op' id='Add Attraction' />
                
            </form>
        </fieldset>
    </body>
</html>
<?php
    function displayHomePage($string) {
        $dbc= @mysqli_connect("localhost", "patelau", "EFPSiQ9W", "CS254") or
                    die("Connect failed: ". mysqli_connect_error());
        if ($string == "" ) {
            $query="select * from `bestofbc` ";
        } else {
            $string="%".$string."%";
            $query=   "select * from `bestofbc` where `address` like '$string' union
                            select * from `bestofbc` where `entered_by` like '$string' union
                            select * from `bestofbc` where `category` like '$string' union
                            select * from `bestofbc` where `name` like '$string' union
                            select * from `bestofbc` where `phone` like '$string' union
                            select * from `bestofbc` where `comment` like '$string'";
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
        echo "<th>Attraction</th><th>Comment</th>";              
        foreach($data as $display){   
            echo"<tr><td>";
            echo " {$display['name']}<br>{$display['category']}<br>{$display['address']}<br>{$display['phone']}<br>
                {$display['url']}<br>{$display['price_range']}<br>{$display['stars']}</td><td>{$display['comment']}";
            echo "</td></tr>";
        }
        echo "</table>";
    }

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
                <br>
                <br>
            dummyinput: <article id='dummy'></article><input type='hidden' name='dummy' id='dummy'> <br>
            Price Level: <article id='priceerror'></article><select name='Price' id='Price'>    
                <option value='FAILURE'>Select One!</option>
                <option value='onesigns'>$</option>
                <option value='twosigns'>$$</option>
                <option value='threesigns'>$$$</option>
                <option value='foursigns'>$$$$</option>
                <option value='fivesigns'>$$$$$</option> 
                <br>
            dummyinput: <article id='dummy'></article><input type='hidden' name='dummy' id='dummy'> <br>
            <br><input type='submit' value='Add' name='Add' id='Add' />
            </form>
            </fieldset>
            <?php
        }

    function starter(){
        $Name=$_GET['Name'];
        $Category=$_GET['Category'];
        $rating=$_GET['rating'];
        $Address=$_GET['Address'];
        $Phone=$_GET['Phone'];
        $URL=$_GET['URL'];
        $userid=$_GET['userid'];
        $Price=$_GET['Price'];
        $Comment=$_GET['Comment'];
        test($Name,$Category,$rating,$Address,$Phone,$URL,$userid,$Comment,$Price);
    }
    function test($Name,$Category,$rating,$Address,$Phone,$URL,$userid,$Comment,$Price){
        $lowercaseaddress=strtolower($Address);
        $string = preg_replace("/[\s_]/", "+", $lowercaseaddress);
        $page = 'http://geocoder.us/demo.cgi?address='.$string;
        $content=file_get_contents($page);
        $pattern="/(<td>)(\-?\d+.\d+)/";
        $pattern2="/<td>.\d+\.\d+?/";
        $latitude=0;
        $longitude=0;
        var_dump($longitude);
            if (preg_match_all ($pattern, $content, $matches) ) {
                    echo 'Thank you for inserting into the table!</p>';
                    echo "<br>";
                    $latitude= $matches[2][0];
                    $longitude= $matches[2][1];
            } else {
                die("That Address Doesn't exist");
            }          
        $dbc= @mysqli_connect("localhost", "patelau", "EFPSiQ9W", "CS254") or
            die("Connect failed: ". mysqli_connect_error());      
        $insertsql="INSERT INTO bestofbc (insertion_date, entered_by, name, category,address,latitude,longitude,phone,url,stars,price_range,comment)
        VALUES
        (CURDATE(),'$userid','$Name', '$Category','$Address','$latitude','$longitude','$Phone','$URL','$rating','$Price','$Comment')";
        $result = mysqli_query($dbc, $insertsql) or die("bad query".mysqli_error($dbc)); 
    }

    if (isset($_GET['Add'])) {
        starter();
    }

    $op = isset($_GET['op']) ? $_GET['op'] : "";
    switch ($op) {
        case 'Add Attraction':
            displayInsertAttractionForm();
            break;
        default:
        case 'Search':
            $string = isset($_GET['searchtxt']) ? $_GET['searchtxt'] : "";
            displayHomePage($string);
            break; 
    }
?>