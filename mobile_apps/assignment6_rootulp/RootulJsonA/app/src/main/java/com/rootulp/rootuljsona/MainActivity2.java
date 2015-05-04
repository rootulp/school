package com.rootulp.rootuljsona;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.io.InputStream;




public class MainActivity2 extends ActionBarActivity {
    ProgressBar progressBar;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        info = (TextView) findViewById(R.id.info);
        Intent localIntent = getIntent();
        Bundle localBundle = localIntent.getExtras();
        PersonInfo selected = (PersonInfo) localBundle.getSerializable("person");
        String name = selected.getPerson();
        String link = selected.getlink();
        info.setText(name);

        new DownloadImageTask((ImageView) findViewById(R.id.link)).execute(link);
    }



    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView img;

        public DownloadImageTask(ImageView bmImage) {
            this.img = bmImage;
        }

        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            progressBar.setVisibility(View.INVISIBLE);
            img.setImageBitmap(result);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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