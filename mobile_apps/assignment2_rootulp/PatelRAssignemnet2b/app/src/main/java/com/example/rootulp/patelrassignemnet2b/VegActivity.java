package com.example.rootulp.patelrassignemnet2b;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class VegActivity extends ActionBarActivity {

    Integer price;
    Integer vegMult;
    TextView current;
    TextView updated;
    ListView listview;
    Button next;
    ArrayList<String> selectedRows = new ArrayList<String>();

    String [] options = {"Peppers", "Onions" ,"Spinach", "Mushrooms", "Zucchini"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veg);

        next = (Button) findViewById(R.id.nextButton);
        current = (TextView) findViewById(R.id.currentCostwMeat);
        updated = (TextView) findViewById(R.id.updatedCostforVeggie);
        listview = (ListView) findViewById(R.id.veggieOptions);

        Intent local = getIntent();
        Bundle localBundle = local.getExtras();

        vegMult = localBundle.getInt("veggie");
        price = localBundle.getInt("price");
        current.setText(Integer.toString(price));
        updated.setText(Integer.toString(price));

        final ArrayAdapter<String> adapter = new ArrayAdapter <String>(this,
                android.R.layout.simple_list_item_multiple_choice,
                android.R.id.text1, options);
        listview.setAdapter(adapter);
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> l, View v, int position, long id) {
                int n = listview.getCount();
                n = listview.getCheckedItemCount();
                int veggiePrices = vegMult * n;
                int plusToppings = veggiePrices + price;
                updated.setText(Integer.toString(plusToppings));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VegActivity.this, OrderActivity.class);
                Bundle newBundle = new Bundle();

                String str = updated.getText().toString();
                int currentPrice = Integer.parseInt(str);

                newBundle.putInt("price", currentPrice);

                intent.putExtras(newBundle);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_veg, menu);
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

