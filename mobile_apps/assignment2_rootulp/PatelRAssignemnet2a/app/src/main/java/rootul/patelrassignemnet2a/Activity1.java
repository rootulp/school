package rootul.patelrassignemnet2a;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// Multi-Activity Application
// Activity1: takes user input and error checks it
// Activity2: calculates trigonometric values

public class Activity1 extends ActionBarActivity {

    EditText Val1;
    EditText Val2;
    EditText txtResult;
    Button   btnCalc;
    Button   btnClear;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity1);
        Val1 = (EditText) findViewById(R.id.EditText01);
        Val2 = (EditText) findViewById(R.id.EditText02);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Val1.setText("");
                Val2.setText("");}
        });

        btnCalc = (Button) findViewById(R.id.btnCalc);

        btnCalc.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Double v1 = Double.parseDouble(Val1.getText().toString());
                Double v2 = Double.parseDouble(Val2.getText().toString());
                Double sin, cos, tan, h;

                Intent myIntent = new Intent (Activity1.this,
                        Activity2.class);
                Bundle myDataBundle = new Bundle();

                myDataBundle.putDouble("val1", v1);
                myDataBundle.putDouble("val2", v2);

                myIntent.putExtras(myDataBundle);

                if (v1 <= 0 || v2 <= 0) {
                    Toast.makeText(getApplicationContext(),
                            "Length Must Be Positive", Toast.LENGTH_LONG).show();
                } else {
                    startActivityForResult(myIntent, 101);
                }
            }

        });
    } //onCreate

    //////////////////////////////////////////////////////////////////////////////
    // local listener receives callbacks from other activities
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try	{
            if ((requestCode == 101 ) && (resultCode == Activity.RESULT_OK)){
                Bundle myResultBundle = data.getExtras();
                Double myResult = myResultBundle.getDouble("vresult");
//                txtResult.setText("Sum is " + myResult);
            }
        }
        catch (Exception e) {
            txtResult.setText("Problems - " + requestCode + " " + resultCode);
        }
    } //onActivityResult
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}