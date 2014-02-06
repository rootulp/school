var tfooter = '';
var theader = '';

function createTable()
{
	var tbody = '';
	var num_rows = document.getElementById('years').value;
	var principle = document.getElementById("principle").value;
	var num_cols = 2
	var interest;
	var alt;

    if(document.getElementById('regular').checked) {
	  	var interest = .08;
 	} else if (document.getElementById('premium').checked) {
 		var interest = .085;
 	}

	tableHeader();
    for( var i=0; i<num_rows;i++)
    {
		if (i % 2 == 0){
			tbody += '<tr class=lightblue>';
        	for( var j=0; j<num_cols;j++)
        	{
        		if (j==0){
        		tbody += '<td>';
        		tbody += i;
        		tbody += '</td>'
        		}else{
            	tbody += '<td>';
            	tbody += Math.round(principle*Math.pow(1+interest,i)*100)/100;
            	tbody += '</td>'
            	}
            }
        tbody += '</tr>\n';
        }else{ 
        	tbody += '<tr class=lightgreen>';
        	for( var j=0; j<num_cols;j++)
        	{
        		if (j==0){
        		tbody += '<td>';
        		tbody += i;
        		tbody += '</td>'
        		}else{
            	tbody += '<td>';
            	tbody += Math.round(principle*Math.pow(1+interest,i)*100)/100;
            	tbody += '</td>'
            	}
        	}
        tbody += '</tr>\n';
    	}
    }
    tableFooter();
    
    document.getElementById('result').innerHTML = theader + tbody + tfooter;
}

function tableHeader(){
	theader = '<table><tr><th>Year</th><th>Balance</th></tr>';
}

function tableFooter(){
	tfooter += '</table>';
}