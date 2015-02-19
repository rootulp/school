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


public class MeatActivity extends ActionBarActivity {

    Integer price;
    Integer meatMult;
    Integer vegMult;
    String size;
    TextView current;
    TextView updated;
    ListView listview;
    Button next;
    ArrayList<String> selectedRows = new ArrayList<String>();

    String [] options = {"Pepperoni", "Sausage", "Ham", "Duck", "Turkey"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meat);
        next = (Button) findViewById(R.id.nextButton);
        current = (TextView) findViewById(R.id.currentCost);
        updated = (TextView) findViewById(R.id.updatedCost);
        listview = (ListView) findViewById(R.id.meatOptions);

        Intent localIntent = getIntent();
        Bundle myLocalBundle = localIntent.getExtras();

        meatMult = myLocalBundle.getInt("meat");
        vegMult = myLocalBundle.getInt("veggie");
        price = myLocalBundle.getInt("price");
        size = myLocalBundle.getString("size");
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
                int meatPrices = meatMult * n;
                int plusToppings = meatPrices + price;
                updated.setText(Integer.toString(plusToppings));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeatActivity.this, VegActivity.class);
                Bundle newBundle = new Bundle();

                String str = updated.getText().toString();
                int currentPrice = Integer.parseInt(str);

                newBundle.putInt("veggie", vegMult);
                newBundle.putInt("price", currentPrice);

                intent.putExtras(newBundle);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_meat, menu);
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
