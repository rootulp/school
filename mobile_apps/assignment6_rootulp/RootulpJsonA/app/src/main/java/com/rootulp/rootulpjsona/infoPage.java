package com.rootulp.rootulpjsona;

import android.support.v7.app.ActionBarActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.TextView;

public class infoPage extends ActionBarActivity {

    TextView art;
    TextView date;
    TextView description;
    TextView provenance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);
        art = (TextView)findViewById(R.id.art);
        date = (TextView) findViewById(R.id.date);
        description = (TextView) findViewById(R.id.description);
        provenance = (TextView) findViewById(R.id.provenance);

        Intent local = getIntent();
        Bundle localBundle = local.getExtras();

        artist selected = (artist) localBundle.getSerializable("selected");

        String nameStr = selected.getName();
        String titleStr = selected.getTitle();
        String dateStr = selected.getPaintingdate();
        String descriptionStr = selected.getDescription();
        String provenanceStr = selected.getProvenance();

        art.setText(titleStr + " by " + nameStr);
        date.setText("Completed in: " + dateStr);
        description.setText(descriptionStr);
        provenance.setText(provenanceStr);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

