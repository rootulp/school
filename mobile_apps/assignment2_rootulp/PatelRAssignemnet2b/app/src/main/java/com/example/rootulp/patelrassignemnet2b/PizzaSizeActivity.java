package com.example.rootulp.patelrassignemnet2b;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class PizzaSizeActivity extends ActionBarActivity {

    Button nextStep;
    Integer price;
    Integer vegMult;
    Integer meatMult;
    String size = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_size);

        nextStep = (Button) findViewById(R.id.nextButton);
        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (size.length() != 0) {
                    Intent nextIntent = new Intent(PizzaSizeActivity.this, MeatActivity.class);

                    Bundle myBundle = new Bundle();
                    myBundle.putString("size", size);
                    myBundle.putInt("price", price);
                    myBundle.putInt("veggie", vegMult);
                    myBundle.putInt("meat", meatMult);

                    nextIntent.putExtras(myBundle);
                    startActivity(nextIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please select a Size", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void pickSize(View view) {
        ImageView img = (ImageView) findViewById(R.id.selectedSize);
        if ((view.getId()) == R.id.smallSize) {
            img.setImageResource(R.drawable.small);
            price = 5;
            size = "Small";
            vegMult = 1;
            meatMult = 2;
        } else if ((view.getId()) == R.id.mediumSize) {
            img.setImageResource(R.drawable.medium);
            price = 7;
            size = "Medium";
            vegMult = 2;
            meatMult = 4;
        } else if ((view.getId()) == R.id.largeSize) {
            img.setImageResource(R.drawable.large);
            price = 10;
            size = "Large";
            vegMult = 3;
            meatMult = 6;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pizza_size, menu);
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
