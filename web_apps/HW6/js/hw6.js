function doit()
{
	if(document.getElementById('average').checked)
	{
  		average()
	}
	if(document.getElementById('sum').checked) 
	{
  		sum()
  	}
  	if(document.getElementById('sort').checked)
  	{
		sort()
	}
}

function sum()
{
var data = document.getElementById('data').value;
if (data.indexOf(",")>0){
	var array = data.split(",");
}else{
	var array = data.split(" ");
}
var sum = 0;
for (i=0; i<array.length; i++)
  {
  sum += Number(array[i]);
  }
  var n=sum.toFixed(1);
document.getElementById('result').innerHTML = "The sum of your data is: " + n;
}

function average()
{
var data = document.getElementById('data').value;
if (data.indexOf(",")>0){
	var array = data.split(",");
}else{
	var array = data.split(" ");
}
var sum = 0;
for (i=0; i<array.length; i++)
  {
  sum += Number(array[i]);
  }
  sum = sum/array.length
  var n=sum.toFixed(1);
document.getElementById('result').innerHTML = "The average of your data is: " + n;
}

function sort()
{
var data = document.getElementById('data').value;
if (data.indexOf(",")>0){
	var array = data.split(",");
}else{
	var array = data.split(" ");
}
var num_cols = 2;
var num_rows = array.length;
var tfooter = '</table>';
var theader = '<table><tr><th>Index</th><th>Value</th></tr>';
var tbody = '';
array.sort(function(a,b) {return a-b});
for( var i=0; i<num_rows;i++)
    {
    	if (i % 2 ==0)
 		 {
		tbody += '<tr style="color: #fff; background: silver;">';
 		 }
		else
 		 {
 		 tbody += '<tr style="color: #fff; background: gold;">';
 		 }
        for( var j=0; j<num_cols;j++)
        {
        	if (j==0){
        	tbody += '<td>';
        	tbody += i;
        	tbody += '</td>'
        	}else{
            tbody += '<td>';
            tbody += array[i];
            tbody += '</td>'
            }
        }
        tbody += '</tr>\n';
    }
document.getElementById('result').innerHTML = theader + tbody + tfooter;
}