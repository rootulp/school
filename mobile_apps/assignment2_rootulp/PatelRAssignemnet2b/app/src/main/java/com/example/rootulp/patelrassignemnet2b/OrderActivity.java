package com.example.rootulp.patelrassignemnet2b;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class OrderActivity extends ActionBarActivity {

    TextView cost;
    EditText name;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        cost = (TextView) findViewById(R.id.cost);
        name = (EditText) findViewById(R.id.nameEnter);

        Intent intent = getIntent();
        Bundle total = intent.getExtras();

        int finalCost = total.getInt("price");

        cost.setText("$" + Integer.toString(finalCost));

    }

    public void orderSent(View v) {
        String str = name.getText().toString();

        if (str.length() == 0) {
            Toast.makeText(getApplicationContext(), "Please Enter Your Name", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Order Completed!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order, menu);
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
